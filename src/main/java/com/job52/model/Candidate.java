package com.job52.model;

import java.util.Date;

/**
 * InnoDB free: 3072 kB
 * 
 * @author wcyong
 * 
 * @date 2018-02-27
 */
public class Candidate extends CandidateKey {
    private String rid;

    /**
     * 描述
     */
    private String descriptionte;

    /**
     * 创建时间
     */
    private Date createTime;

    public Candidate(Integer isread) {
        this.isread = isread;
        descriptionte = "";
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "rid='" + rid + '\'' +
                ", descriptionte='" + descriptionte + '\'' +
                ", createTime=" + createTime +
                ", isread=" + isread +
                ", ispass=" + ispass +
                '}';
    }

    public Candidate() {
        descriptionte = "";
    }

    public Candidate(String jid, String pid) {
        super(jid, pid);
        descriptionte = "";
    }

    private Integer isread;

    public Integer getIspass() {
        return ispass;
    }

    public void setIspass(Integer ispass) {
        this.ispass = ispass;
    }

    private Integer ispass;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
    }

    public String getDescriptionte() {
        return descriptionte;
    }

    public void setDescriptionte(String descriptionte) {
        this.descriptionte = descriptionte == null ? null : descriptionte.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsread() {
        return isread;
    }

    public void setIsread(Integer isread) {
        this.isread = isread;
    }
}