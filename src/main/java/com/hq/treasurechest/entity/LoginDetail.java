package com.hq.treasurechest.entity;

import java.sql.Timestamp;

public class LoginDetail {
    private int id;
    private String userId;
    private String ipAddress;
    private String loginLocation;
    private String browser;
    private String os;
    private Timestamp loginTime;

    public LoginDetail() {}

    public LoginDetail(int id, String userId, String ipAddress, String loginLocation, String browser, String os, Timestamp loginTime) {
        this.id = id;
        this.userId = userId;
        this.ipAddress = ipAddress;
        this.loginLocation = loginLocation;
        this.browser = browser;
        this.os = os;
        this.loginTime = loginTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }
}
