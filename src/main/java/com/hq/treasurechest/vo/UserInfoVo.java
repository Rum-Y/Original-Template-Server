package com.hq.treasurechest.vo;

public class UserInfoVo {
    private int id;
    private String username;
    private String schoolCode;
    private Character isLocked;
    private String firstLoginTime;
    private String lastLoginTime;

    public UserInfoVo() {}

    public UserInfoVo(int id, String username, String schoolCode, Character isLocked, String firstLoginTime, String lastLoginTime) {
        this.id = id;
        this.username = username;
        this.schoolCode = schoolCode;
        this.isLocked = isLocked;
        this.firstLoginTime = firstLoginTime;
        this.lastLoginTime = lastLoginTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public Character getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Character isLocked) {
        this.isLocked = isLocked;
    }

    public String getFirstLoginTime() {
        return firstLoginTime;
    }

    public void setFirstLoginTime(String firstLoginTime) {
        this.firstLoginTime = firstLoginTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
