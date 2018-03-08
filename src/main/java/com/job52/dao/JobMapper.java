package com.job52.dao;

import com.job52.model.Job;

import java.util.List;

public interface JobMapper {
    int deleteByPrimaryKey(String jid);

    int insert(Job record);

    int insertSelective(Job record);

    Job selectByPrimaryKey(String jid);

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);

    /**
     * 根据job对象精确查询
     * @param job
     * @return
     */
    List<Job> query(Job job);

    /**
     * 根据job对象模糊查询
     * @param job
     * @return
     */
    List<Job> queryContains(Job job);

    /**
     * 返回包含有这个str在的记录，如下字段进入搜索：
     * @param str 搜索的选项
     * @return
     */
    List<Job> queryString(String str);

    /**
     * *****主要查询算法*****
     * @param job
     * @return
     */
    List<Job> queryJobs(Job job);
}