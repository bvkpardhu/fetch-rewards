package com.bvkpardhu.fetchrewards.controller;

import com.bvkpardhu.fetchrewards.model.User;
import com.bvkpardhu.fetchrewards.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/fetch-rewards")
public class FRController {
    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String getWelcomeMessage(){
        return "Welcome to Fetch Rewards assessment application!";
    }

    @PostMapping("/upload")
    public String upload(@RequestBody String user) {
        try {
            return userService.upload(user);
        }
        catch(Exception e) {
            return "Error";
        }
        finally {
            userService.updateBalance();
            userService.balance();
        }
    }

    @GetMapping("/balances")
    public HashMap<String,Integer> balance() {
        return userService.balance();
    }

    @GetMapping("/points/{points}")
    public LinkedHashMap<String,Integer> updatePoints(@PathVariable int points) {
        return userService.updatePoints(points);
    }

    @GetMapping("/al")
    public ArrayList<User> getTransactions() {
        return userService.getTransactions();
    }

}
