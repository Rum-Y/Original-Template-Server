package com.hq.treasurechest.vo;

import com.hq.treasurechest.entity.AwardInfo;

public class AwardInfoVo extends AwardInfo {
    private String username;

    private String projectName;

    public AwardInfoVo(String username, String projectName) {
        this.username = username;
        this.projectName = projectName;
    }

    public AwardInfoVo(int id, String schoolCode, String projectCode, String teamIndex, String award, String remark, String username, String projectName) {
        super(id, schoolCode, projectCode, teamIndex, award, remark);
        this.username = username;
        this.projectName = projectName;
    }

    public AwardInfoVo(String schoolCode, String projectCode, String teamIndex, String award, String remark, String username, String projectName) {
        super(schoolCode, projectCode, teamIndex, award, remark);
        this.username = username;
        this.projectName = projectName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "AwardInfoVo{" +
                "username='" + username + '\'' +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}
