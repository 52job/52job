package com.job52.service.impl;

import com.job52.dao.ApplicationMapper;
import com.job52.model.Application;
import com.job52.model.ApplicationKey;
import com.job52.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    ApplicationMapper applicationMapper;
    public boolean addApplication(Application application) {
        if (applicationMapper.insert(application) > 0) {
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
}
