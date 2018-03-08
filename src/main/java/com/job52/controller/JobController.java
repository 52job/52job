package com.job52.controller;

import com.job52.model.Job;
import com.job52.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/job")
public class JobController {

    @Autowired
    JobService jobService;

    @RequestMapping("/{value}")
    @ResponseBody
    public List<Job> searchJobByString(@PathVariable("value") String value) {
        List<Job> jobs = jobService.queryString(value);
        return jobs;
    }

    @RequestMapping("/test/{phone}")
    @ResponseBody
    public String test(@PathVariable("phone") String name) {
        return name;
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<Job> searchJob(HttpServletRequest request) {
        Job job = new Job();
        return jobService.queryJobs(job);
    }



}
