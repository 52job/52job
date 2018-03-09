package com.job52.model;

public class Test {
    private String ID;
    private String Name;
    private String Sex;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    @Override
    public String toString() {
        return "{" +
                "ID='" + ID + '\'' +
                ", Name='" + Name + '\'' +
                ", Sex='" + Sex + '\'' +
                '}';
    }
}
