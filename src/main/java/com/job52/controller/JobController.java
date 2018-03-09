package com.job52.controller;

import com.alibaba.fastjson.JSON;
import com.job52.enums.EducationLevel;
import com.job52.model.Job;
import com.job52.service.JobService;
import com.job52.util.CommonUtil;
import jdk.nashorn.internal.scripts.JO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping("/init")
    public String jobPage() {
        return "/PositionInfoSearch";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String searchJob(HttpServletRequest request) {
        Map<String, Object> map = new HashMap();
        Job job = new Job(request.getParameter("searchKey1").toString());
        System.out.println("--------");
        System.out.println(request.getParameter("searchKey1").toString());
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(request.getParameter("educationKey1").toString());
        job = setSalary(job, String.valueOf(request.getParameter("salaryKey1")).trim());
        job = setEtype(job, String.valueOf(request.getParameter("firmCharacterKey1")).trim());
        job = setExp(job, String.valueOf(request.getParameter("experienceKey1")).trim());
        job = setEdu(job, String.valueOf(request.getParameter("educationKey1")).trim());
        List<Job> list = jobService.queryJobs(job);

        map.put("total", list.size());
        map.put("rows", list);
        System.out.println(map.toString());
        String jsonString = JSON.toJSONString(map);
        return jsonString;
    }

    private Job setSalary(Job job, String salary) {
        if (salary.equals("2千以下")) {
            job.setMinSalary(0);
            job.setMaxSalary(2);
        } else if (salary.equals("2-3千")) {
            job.setMinSalary(2);
            job.setMaxSalary(3);

        } else if (salary.equals("3-4.5千")) {
            job.setMinSalary(3);
            job.setMaxSalary(4);

        } else if (salary.equals("4.5-6千")) {
            job.setMinSalary(4);
            job.setMaxSalary(6);
        } else if (salary.equals("6-8千")) {
            job.setMinSalary(6);
            job.setMaxSalary(8);

        } else if (salary.equals("0.8-1万")) {
            job.setMinSalary(8);
            job.setMaxSalary(10);

        } else if (salary.equals("1-1.5万")) {
            job.setMinSalary(10);
            job.setMaxSalary(15);

        } else if (salary.equals("1.5-2万")) {
            job.setMinSalary(15);
            job.setMaxSalary(20);

        } else if (salary.equals("2-3万")) {
            job.setMinSalary(20);
            job.setMaxSalary(30);

        } else if (salary.equals("3-4万")) {
            job.setMinSalary(30);
            job.setMaxSalary(40);

        } else if (salary.equals("4-5万")) {
            job.setMinSalary(40);
            job.setMaxSalary(50);

        } else if (salary.equals("5万以上")) {
            job.setMinSalary(50);
            job.setMaxSalary(999);

        } else {
            job.setMinSalary(0);
            job.setMaxSalary(999);

        }
        return job;
    }


    private Job setEtype(Job job, String etype) {
        if (etype!= "" ) {
            job.getEnterprise().seteType(etype);
        }
        return job;
    }

    private Job setExp(Job job, String exp) {
        if (exp.equals("1-3年")) {
            job.setRequiredWorkyear(1);
        } else if (exp.equals("3-5年")) {
            job.setRequiredWorkyear(3);
        } else if (exp.equals("5-10年")) {
            job.setRequiredWorkyear(5);
        } else if (exp.equals("10年以上")) {
            job.setRequiredWorkyear(10);
        } else {
            job.setRequiredWorkyear(0);
        }
        return job;
    }

    private Job setEdu(Job job, String edu) {
        if (edu.equals("初中及以下")) {
            job.setRequiredEducation(1);
        } else if (edu.equals("高中/中技/中专")) {
            job.setRequiredEducation(2);
        } else if (edu.equals("大专")) {
            job.setRequiredEducation(3);
        } else if (edu.equals("本科")) {
            job.setRequiredEducation(4);
        } else if (edu.equals("硕士")) {
            job.setRequiredEducation(5);
        } else if (edu.equals("博士")) {
            job.setRequiredEducation(6);
        } else {
            job.setRequiredEducation(0);
        }
        return  job;
    }


}
