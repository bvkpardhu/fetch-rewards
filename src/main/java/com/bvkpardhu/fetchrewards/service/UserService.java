package com.bvkpardhu.fetchrewards.service;

import com.bvkpardhu.fetchrewards.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserService {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    ArrayList<User> al = new ArrayList<User>();
    HashMap<String,Integer> hm = new HashMap<>();
    HashMap<String,Integer> negBalance = null;
    JSONObject jso = new JSONObject();
    JSONArray jsonArray = new JSONArray();
    int totalBalance = 0;
    public String upload(String userData) {
        if(al.isEmpty() == true || al.isEmpty() == false) {
            try {
                jso = new JSONObject(userData);
                jsonArray = jso.getJSONArray("users");
                User  user = null;
                int balance =0;
                for (Object i : jsonArray) {
                    JSONObject temp = (JSONObject) i;
                    if(temp.getString("payer").equals("")) {
                        throw new Exception();
                    }
                    int points = Integer.parseInt(temp.get("points").toString());
                    Date date = formatter.parse(temp.getString("timeStamp"));
                    String strDate = formatter.format(date);
                    user = new User(strDate, temp.getString("payer"), points);
                    if(hm.get(user.getPayer())==null) hm.put(user.getPayer(),0);
                    balance = hm.get(user.getPayer())+ user.getPoints();
                    if(balance<0) return "Error";
                    hm.put(user.getPayer(),balance);
                    al.add(user);
                }
                if(al.size()>1)
                    Collections.sort(al, new SortComp());
                return "Successfully uploaded!";
            } catch (Exception e) {
                return "Error!, check the input format from readme";
            }
        }
        return "Error!, check the input format from readme";
    }

    public HashMap<String,Integer> balance() {
        return hm;
    }

    public LinkedHashMap<String,Integer> updatePoints(int pointsToUpdate) {
        LinkedHashMap<String,Integer> pointsSub = new LinkedHashMap<>();
        if(pointsToUpdate <= 0 || pointsToUpdate > totalBalance) return new LinkedHashMap<>();
        try {
            int points = 0;
            int pointsToSub = 0;
            for (User user : al) {
                if (user.getPoints() < 0) continue;
                if (negBalance.get(user.getPayer()) == null) points = 0;
                else points = negBalance.get(user.getPayer());
                points += user.getPoints();
                if (points > 0) {
                    pointsToSub = pointsToUpdate > points ? points : pointsToUpdate;
                    pointsToUpdate -= pointsToSub;
                    if (pointsSub.get(user.getPayer()) == null) pointsSub.put(user.getPayer(), 0);
                    pointsSub.put(user.getPayer(), pointsSub.get(user.getPayer()) - pointsToSub);
                    points = 0;
                }
                negBalance.put(user.getPayer(), points);
                if (pointsToUpdate <= 0) return pointsSub;
            }
        }
        finally{
            for(String payer : pointsSub.keySet())
                al.add(new User(formatter.format(new Date()),payer,pointsSub.get(payer)));
            updateBalance();
        }
        return pointsSub;
    }
    public void updateBalance() {
        totalBalance = 0;
        negBalance = new HashMap<>();
        hm = new HashMap<>();
        for (User user : al) {
            if (user.getPoints() < 0) {
                if (negBalance.get(user.getPayer()) == null) {
                    negBalance.put(user.getPayer(), 0);
                }
                negBalance.put(user.getPayer(), negBalance.get(user.getPayer()) + user.getPoints());
            }
            if (hm.get(user.getPayer()) == null) {
                hm.put(user.getPayer(), 0);
            }
            hm.put(user.getPayer(), hm.get(user.getPayer()) + user.getPoints());
            totalBalance += user.getPoints();
        }
    }

}

class SortComp implements Comparator<User>{
    @Override
    public int compare(User user1, User user2) {
        String d1 = user1.getTimeStamp();
        String d2 = user2.getTimeStamp();
        return d1.compareTo(d2);
    }
}
