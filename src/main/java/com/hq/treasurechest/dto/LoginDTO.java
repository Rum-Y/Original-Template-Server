package com.hq.treasurechest.dto;

public class LoginDTO extends BaseDTO {

    private String schoolCode;
    private String password;
    private String checkCode;
    public LoginDTO() {}

    public LoginDTO(String schoolCode, String password, String checkCode) {
        this.schoolCode = schoolCode;
        this.password = password;
        this.checkCode = checkCode;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "schoolCode='" + schoolCode + '\'' +
                ", password='" + password + '\'' +
                ", checkCode='" + checkCode + '\'' +
                '}';
    }
}
