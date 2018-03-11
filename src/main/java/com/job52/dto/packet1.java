package com.job52.dto;

public class packet1 {
    String jid;
    String pid;
    String rid;
    Integer ispass;

    public packet1(String jid, String pid, String rid, Integer ispass, String name, String job) {
        this.jid = jid;
        this.pid = pid;
        this.rid = rid;
        this.ispass = ispass;
        this.name = name;
        this.job = job;
    }

    public packet1(String jid, String pid, String rid, String name, String job) {
        this.jid = jid;
        this.pid = pid;
        this.rid = rid;
        this.name = name;
        this.job = job;
    }

    String name;
    String job;

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
