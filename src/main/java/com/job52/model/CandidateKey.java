package com.job52.model;

public class CandidateKey {
    public CandidateKey(String jid, String pid) {
        this.jid = jid;
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "CandidateKey{" +
                "jid='" + jid + '\'' +
                ", pid='" + pid + '\'' +
                '}';
    }

    public CandidateKey() {
    }

    /**
     * 公司查看职位申请人列表

     */
    private String jid;

    private String pid;

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid == null ? null : jid.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }
}