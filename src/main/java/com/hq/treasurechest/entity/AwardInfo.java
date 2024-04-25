package com.hq.treasurechest.entity;

public class AwardInfo {
    private int id;
    private String schoolCode;
    private String projectCode;
    private String teamIndex;
    private String award;
    private String remark;

    public AwardInfo() {}

    public AwardInfo(int id, String schoolCode, String projectCode, String teamIndex, String award, String remark) {
        this.id = id;
        this.schoolCode = schoolCode;
        this.projectCode = projectCode;
        this.teamIndex = teamIndex;
        this.award = award;
        this.remark = remark;
    }

    public AwardInfo(String schoolCode, String projectCode, String teamIndex, String award, String remark) {
        this.schoolCode = schoolCode;
        this.projectCode = projectCode;
        this.teamIndex = teamIndex;
        this.award = award;
        this.remark = remark;
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

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AwardInfo{" +
                "id=" + id +
                ", schoolCode='" + schoolCode + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", teamIndex='" + teamIndex + '\'' +
                ", award='" + award + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
