package com.job52.model;

import java.util.Date;

/**
 * InnoDB free: 3072 kB
 * 
 * @author wcyong
 * 
 * @date 2018-03-06
 */
public class Job {
    /**
     * ְλid
     */
    private String jid;

    /**
     * 公司信息
     */
    private Enterprise enterprise;

    private Integer requiredNumber;

    private String jname;

    /**
     * 工作经验要求
     */
    private Integer requiredWorkyear;

    /**
     * 学历要求
     */
    private String requiredEducation;

    /**
     * 月薪范围
     */
    private Integer minSalary = 0;

    private Integer maxSalary;

    private String benefit;

    public Job(Integer jobStatue) {
        this.jobStatue = jobStatue;
    }

    /**
     * ְ
     */

    private String jobDesc;


    private String jobType;

    private String workPlace;

    /**
     * 2:草稿
     * 1:有效
     * 0:过期
     * -1:无效
     */
    private Integer jobStatue;

    private Date createTime;

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid == null ? null : jid.trim();
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public Integer getRequiredNumber() {
        return requiredNumber;
    }

    public void setRequiredNumber(Integer requiredNumber) {
        this.requiredNumber = requiredNumber;
    }

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname == null ? null : jname.trim();
    }

    public Integer getRequiredWorkyear() {
        return requiredWorkyear;
    }

    public void setRequiredWorkyear(Integer requiredWorkyear) {
        this.requiredWorkyear = requiredWorkyear;
    }

    public String getRequiredEducation() {
        return requiredEducation;
    }

    public void setRequiredEducation(String requiredEducation) {
        this.requiredEducation = requiredEducation == null ? null : requiredEducation.trim();
    }

    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit == null ? null : benefit.trim();
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc == null ? null : jobDesc.trim();
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType == null ? null : jobType.trim();
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace == null ? null : workPlace.trim();
    }

    public Integer getJobStatue() {
        return jobStatue;
    }

    public void setJobStatue(Integer jobStatue) {
        this.jobStatue = jobStatue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}