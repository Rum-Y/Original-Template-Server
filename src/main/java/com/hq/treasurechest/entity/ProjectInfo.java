package com.hq.treasurechest.entity;

public class ProjectInfo {
    private int id;
    private String projectName;
    private String projectCode;

    public ProjectInfo() {}

    public ProjectInfo(int id, String projectName, String projectCode) {
        this.id = id;
        this.projectName = projectName;
        this.projectCode = projectCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }
}
