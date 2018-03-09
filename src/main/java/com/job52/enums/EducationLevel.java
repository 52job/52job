package com.job52.enums;

public enum EducationLevel {
    All("全部", 0),
    MIDDLE_SCHOOL("初中及以下", 1),
    HIGH_SCHOOL("高中/中技/中专", 2),
    ACADEMY("大专", 3),
    UNIVERSITY("本科", 4),
    GRADUATE("硕士", 5),
    DOCTOR("博士", 6);


    private String name;
    private int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    EducationLevel(String name, int value) {
        this.name = name;
        this.value = value;
    }

    EducationLevel(){

    }


}
