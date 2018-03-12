package com.job52.model;


/**
 * InnoDB free: 3072 kB
 *
 * @author wcyong
 * @String 2018-02-27
 */
public class Resume {
    /**
     * 简历id
     */
    private String rid;
    private String pid;
    private String name;
    private String sex;
    private String portrait;
    private String birthday;
    private String tel;
    private String startWorkTime;
    private String jobState;
    private String email;
    private String address;
    private String highestEducation;
    private String graduationUniversity;
    private String graduationTime;
    private String careerIntention;
    private String major;
    private String workExp;
    private int isPublic;
    private String accessory;

    public Resume() {
    }

    public Resume(String rid, String pid, String name, String sex, String portrait, String birthday, String tel, String startWorkTime, String jobState, String email, String address, String highestEducation, String graduationUniversity, String graduationTime, String careerIntention, String major, String workExp, int isPublic, String accessory) {
        this.rid = rid;
        this.pid = pid;
        this.name = name;
        this.sex = sex;
        this.portrait = portrait;
        this.birthday = birthday;
        this.tel = tel;
        this.startWorkTime = startWorkTime;
        this.jobState = jobState;
        this.email = email;
        this.address = address;
        this.highestEducation = highestEducation;
        this.graduationUniversity = graduationUniversity;
        this.graduationTime = graduationTime;
        this.careerIntention = careerIntention;
        this.major = major;
        this.workExp = workExp;
        this.isPublic = isPublic;
        this.accessory = accessory;
    }

    public Resume(String rid, String pid, String name, String sex, String portrait, String birthday, String tel, String startWorkTime, String jobState, String email, String address, String highestEducation, String graduationUniversity, String graduationTime, String major, String workExp, int isPublic, String accessory) {
        this.rid = rid;
        this.pid = pid;
        this.name = name;
        this.sex = sex;
        this.portrait = portrait;
        this.birthday = birthday;
        this.tel = tel;
        this.startWorkTime = startWorkTime;
        this.jobState = jobState;
        this.email = email;
        this.address = address;
        this.highestEducation = highestEducation;
        this.graduationUniversity = graduationUniversity;
        this.graduationTime = graduationTime;
        this.major = major;
        this.workExp = workExp;
        this.isPublic = isPublic;
        this.accessory = accessory;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getStartWorkTime() {
        return startWorkTime;
    }

    public void setStartWorkTime(String startWorkTime) {
        this.startWorkTime = startWorkTime;
    }

    public String getJobState() {
        return jobState;
    }

    public void setJobState(String jobState) {
        this.jobState = jobState;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHighestEducation() {
        return highestEducation;
    }

    public void setHighestEducation(String highestEducation) {
        this.highestEducation = highestEducation;
    }

    public String getGraduationUniversity() {
        return graduationUniversity;
    }

    public void setGraduationUniversity(String graduationUniversity) {
        this.graduationUniversity = graduationUniversity;
    }

    public String getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(String graduationTime) {
        this.graduationTime = graduationTime;
    }

    public String getCareerIntention() {
        return careerIntention;
    }

    public void setCareerIntention(String careerIntention) {
        this.careerIntention = careerIntention;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getWorkExp() {
        return workExp;
    }

    public void setWorkExp(String workExp) {
        this.workExp = workExp;
    }

    public int getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "rid='" + rid + '\'' +
                ", pid='" + pid + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", portrait='" + portrait + '\'' +
                ", birthday=" + birthday +
                ", tel='" + tel + '\'' +
                ", startWorkTime=" + startWorkTime +
                ", jobState='" + jobState + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", highestEducation='" + highestEducation + '\'' +
                ", graduationUniversity='" + graduationUniversity + '\'' +
                ", graduationTime=" + graduationTime +
                ", careerIntention='" + careerIntention + '\'' +
                ", major='" + major + '\'' +
                ", workExp='" + workExp + '\'' +
                ", isPublic=" + isPublic +
                ", accessory='" + accessory + '\'' +
                '}';
    }
}
