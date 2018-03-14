package com.job52.service.impl;

import com.job52.dao.ApplicationMapper;
import com.job52.dao.CollectionMapper;
import com.job52.dao.JobMapper;
import com.job52.model.*;
import com.job52.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    ApplicationMapper applicationMapper;
    @Autowired
    CollectionMapper collectionMapper;
    @Autowired
    JobMapper jobMapper;

    public boolean addApplication(Application application) {
        CollectionKey collectionKey  = new CollectionKey();
        collectionKey.setGid(application.getGid());
        collectionKey.setPid(application.getPid());
        if (applicationMapper.insert(application) > 0&&collectionMapper.deleteByPrimaryKey(collectionKey)>0) {
            return true;
        }
        return false;
    }

    public boolean removeApplication(ApplicationKey applicationKey) {
        if (applicationMapper.deleteByPrimaryKey(applicationKey) > 0) {
            return true;
        }
        return false;
    }

    public boolean updateApplication(Application application) {
        if (applicationMapper.updateByPrimaryKeySelective(application) > 0) {
            return true;
        }
        return false;
    }

    public List<Application> queryAll() {
        return applicationMapper.query(new Application());
    }

    public List<Application> query(Application application) {
        return applicationMapper.query(application);
    }

    public List<Application> queryContains(Application application) {
        return applicationMapper.queryContains(application);
    }

    @Override
    public List<Job> findAllApplication(String pid) throws Exception {
        List<Job> jobs = new ArrayList<Job>();
        Application application = new Application();
        application.setPid(pid);
        List<Application> apps= applicationMapper.query(application);
        for(Application app : apps){
            Job job= jobMapper.selectByPrimaryKey(app.getGid());
            jobs.add(job);
        }
        return jobs;
    }
}
