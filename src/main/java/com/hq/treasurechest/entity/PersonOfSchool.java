package com.hq.treasurechest.entity;

// 作为student和teacher的父类用来给前端返回统一数据
public class PersonOfSchool {
    private int id;
    private String schoolCode;
    private String projectCode;
    private String teamIndex;
    private String name;
    private String idCard;
    private String tel;

    public PersonOfSchool() {}

    public PersonOfSchool(int id, String schoolCode, String projectCode, String teamIndex, String name, String idCard, String tel) {
        this.id = id;
        this.schoolCode = schoolCode;
        this.projectCode = projectCode;
        this.teamIndex = teamIndex;
        this.name = name;
        this.idCard = idCard;
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getTeamIndex() {
        return teamIndex;
    }

    public void setTeamIndex(String teamIndex) {
        this.teamIndex = teamIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "PersonOfSchool{" +
                "id=" + id +
                ", schoolCode='" + schoolCode + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", teamIndex='" + teamIndex + '\'' +
                ", name='" + name + '\'' +
                ", idCard='" + idCard + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
