package com.job52.service;

import com.job52.model.Job;

import java.util.List;

public interface JobService {

    /**
     * 查询一条职位信息
     * @param job
     * @return
     */
    boolean addJob(Job job);

    /**
     * 删除一条职位信息
     * @param job
     * @return
     */
    boolean removeJob(Job job);

    /**
     * 批量删除job
     * @param jobs
     * @return
     */
    void removeJobs(List<Job> jobs);

    /**
     * 更新一条职位信息
     * @param job
     * @return
     */
    boolean updateJob(Job job);

    /**
     * 查询所有职位信息
     * @return
     */
    List<Job> queryAll();

    /**
     * 传一个job对象作为参数，根据这个对象已有的值作为条件进行精确匹配查询
     * @param job
     * @return
     */
    List<Job> query(Job job);

    /**
     * 传一个job对象作为参数，根据这个对象已有的值作为条件进行模糊查询
     * @param job
     * @return
     */
    List<Job> queryContains(Job job);

    /**
     * 根据id查询一个具体的job对象
     * @param id
     * @return
     */
    Job getJob(String id);

    /**
     * 根据jobid数组查询所有这个数组里所有jobid对应的job
     * @param jobIds 多个job的id组成的字符数组
     * @return
     */
    List<Job> queryByJobIds(String[] jobIds);

    /**
     * 返回包含有这个str在的记录，如下字段进入搜索：
     * @param str 搜索的选项
     * @return
     */
    List<Job> queryString(String str);

    /**
     * 主要查询算法
     * @param job
     * @return
     */
    List<Job> queryJobs(Job job);
}
