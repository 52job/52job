package com.job52.controller;

import com.alibaba.fastjson.JSON;
import com.job52.enums.EducationLevel;
import com.job52.model.Job;
import com.job52.service.JobService;
import com.job52.util.CommonUtil;
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
        System.out.println(request.getParameter("salaryKey1").toString());
        System.out.println(request.getParameter("firmCharacterKey1").toString());
        System.out.println(request.getParameter("experienceKey1").toString());
        System.out.println(request.getParameter("educationKey1").toString());
        List<Job> list = jobService.queryJobs(job);
//        List<Map> jobs = new ArrayList<Map>();
//        for (int i = 0; i < list.size() ;i++) {
//            jobs.add(CommonUtil.getJson(list.get(i),new HashMap()));
//        }
//        map.put("total", jobs.size());
//        map.put("rows", jobs);
        map.put("total", list.size());
        map.put("rows", list);
        System.out.println(map.toString());
        String jsonString = JSON.toJSONString(map);
        return jsonString;
    }

//    @RequestMapping("/list")
//    @ResponseBody
//    public List<Job> searchJob(HttpServletRequest request) {
//        Job job = new Job(request.getParameter("searchKey1").toString());
//        System.out.println("--------");
//        System.out.println(request.getParameter("searchKey1").toString());
//        List<Job> list = jobService.queryJobs(job);
//
//        return list;
//    }




}
