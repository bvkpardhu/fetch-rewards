package com.bvkpardhu.fetchrewards.model;

public class User {
    private String payer;
    private int points;
    private String timeStamp;

    public User(String timeStamp, String payer, int points) {
        this.timeStamp = timeStamp;
        this.payer = payer;
        this.points = points;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
