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
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    /**
     * 0:未通过
     * 1:已通过
     */

    private Integer isPass;

    /**
     * 0:未查看
     * 1:已查看
     */
    private Integer isRead;

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
        return isPass;
    }

    public void setIsPass(Integer ispass) {
        this.isPass = ispass;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}