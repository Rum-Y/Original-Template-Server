package com.hq.treasurechest.entity;

public class StudentInfo extends PersonOfSchool {
    private String studentId;
    public StudentInfo(){}

    public StudentInfo(String studentId) {
        this.studentId = studentId;
    }

    public StudentInfo(int id, String schoolCode, String projectCode, String teamIndex, String name, String idCard, String tel, String studentId) {
        super(id, schoolCode, projectCode, teamIndex, name, idCard, tel);
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "studentId='" + studentId + '\'' +
                '}';
    }
}
