package com.example.ode.ui;

import android.app.Application;

public class OdeApi extends Application {
    private String username;
    private String userId;
    private static OdeApi instance;

    public static OdeApi getInstance(){
        if (instance == null){
            instance = new OdeApi();
            return instance;
        }
        return instance;
    }
    public OdeApi(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

