package com.job52.service;

import com.job52.model.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class ApplicationServiceTest {

    @Autowired
    ApplicationService applicationService;
    @Test
    public void addApplication() throws Exception {
        Application application = new Application();
        application.setPid("1");
        application.setGid("1");
        applicationService.addApplication(application);
    }

    @Test
    public void removeApplication() throws Exception {
    }

    @Test
    public void query() throws Exception {
    }

    @Test
    public void queryContains() throws Exception {
    }

}