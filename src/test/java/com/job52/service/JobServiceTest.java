package com.job52.service;

import com.job52.model.Enterprise;
import com.job52.model.Job;
import com.job52.util.CommonUtil;
import jdk.nashorn.internal.scripts.JO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class JobServiceTest {
    @Autowired
    JobService jobService;
    @Autowired
    EnterpriseService enterpriseService;
    @Test
    public void addJob() throws Exception {
        Job job = new Job();
        job.setJid(CommonUtil.getJobId());
        job.setJname("销售2222");
        job.setMinSalary(6);
        job.setMaxSalary(8);
        job.setJobStatue(1);
        job.setEnterprise(enterpriseService.getEnterprise("ccc"));
        jobService.addJob(job);
    }

    @Test
    public void removeJob() throws Exception {
    }

    @Test
    public void updateJob() throws Exception {
    }

    @Test
    public void queryByJobIds() throws Exception {
    }


    @Test
    public void queryJobs() throws Exception {
        Job job = new Job("aaa");
       // System.out.println(job);
        job.setRequiredEducation(3);
        List<Job> list = jobService.queryJobs(job);
        for (Job j : list) {
            System.out.println(j.toString());
            System.out.println(j.getEnterprise().toString());
        }
    }

}