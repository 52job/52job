package com.job52.service.impl;

import com.job52.dao.JobMapper;
import com.job52.model.Enterprise;
import com.job52.model.Job;
import com.job52.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    JobMapper jobMapper;
    public boolean addJob(Job job) {
        if(jobMapper.insert(job) > 0) {
            return true;
        }
        return false;
    }

    public boolean removeJob(Job job) {
        if (jobMapper.deleteByPrimaryKey(job.getJid()) > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    public void removeJobs(List<Job> jobs) {
        for (Job job : jobs) {
            removeJob(job);
        }

    }

    public boolean updateJob(Job job) {
        if (jobMapper.updateByPrimaryKeySelective(job) > 0) {
            return true;
        }
        return false;
    }

    public List<Job> queryAll() {

        return jobMapper.query(new Job());
    }

    public List<Job> query(Job job) {
        return jobMapper.query(job);
    }

    public List<Job> queryContains(Job job) {
        return jobMapper.queryContains(job);
    }

    public Job getJob(String id) {
        return jobMapper.selectByPrimaryKey(id);
    }

    public List<Job> queryByJobIds(String[] jobIds) {
        List<Job> list = new ArrayList<Job>();
        for (int i = 0; i < jobIds.length; i++) {
            list.add(getJob(jobIds[i]));
        }
        return list;

    }

    public List<Job> queryString(String str) {
        return jobMapper.queryString(str);
    }

    public List<Job> queryJobs(Job job) {
        return jobMapper.queryJobs(job);
    }
}
