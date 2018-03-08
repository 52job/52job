package com.job52.model;

import java.util.Date;

/**
 * InnoDB free: 3072 kB
 * 
 * @author wcyong
 * 
 * @date 2018-02-27
 */
public class Application extends ApplicationKey {
    private String rid;

    public Integer getIsRead() {
        return isread;
    }

    public void setIsRead(Integer isRead) {
        this.isread = isRead;
    }

    /**
     * 0:未通过
     * 1:已通过
     */

    private Integer ispass;

    /**
     * 0:未查看
     * 1:已查看
     */
    private Integer isread;

    /**
     * 创建时间
     */
    private Date createTime;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
    }

    public Integer getIsPass() {
        return ispass;
    }

    public void setIsPass(Integer ispass) {
        this.ispass = ispass;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}