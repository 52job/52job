package com.job52.service.impl;

import com.job52.dao.ApplicationMapper;
import com.job52.dao.CollectionMapper;
import com.job52.dao.JobMapper;
import com.job52.model.*;
import com.job52.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private ApplicationMapper applicationMapper;
    @Autowired
    private JobMapper jobMapper;
    public List<Job> findAllCollection(String pid) throws Exception {
        List<Job> jobs = new ArrayList<Job>();
        List<String> jids = collectionMapper.findAllCollection(pid);
        for(String jid : jids){
            Job job= jobMapper.selectByPrimaryKey(jid);
            jobs.add(job);
        }
        return jobs;
    }

    public boolean deleteCollection(List<CollectionKey> ids) throws Exception {
        for (CollectionKey id: ids) {
            collectionMapper.deleteByPrimaryKey(id);
        }
        return true;
    }

    @Override
    public boolean addApplication(List<Application> ids) throws Exception {
        for (Application id: ids) {
            applicationMapper.insert(id);
        }
        return true;
    }

}
