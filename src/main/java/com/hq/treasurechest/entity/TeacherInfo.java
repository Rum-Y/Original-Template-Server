package com.hq.treasurechest.entity;

public class TeacherInfo extends PersonOfSchool {
    private String teacherId;

    public TeacherInfo() {}

    public TeacherInfo(String teacherId) {
        this.teacherId = teacherId;
    }

    public TeacherInfo(int id, String schoolCode, String projectCode, String teamIndex, String name, String idCard, String tel, String teacherId) {
        super(id, schoolCode, projectCode, teamIndex, name, idCard, tel);
        this.teacherId = teacherId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "TeacherInfo{" +
                "teacherId='" + teacherId + '\'' +
                '}';
    }
}
