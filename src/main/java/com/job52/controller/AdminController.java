package com.job52.controller;

import com.alibaba.fastjson.JSON;
import com.job52.model.Enterprise;
import com.job52.model.Job;
import com.job52.model.Person;
import com.job52.service.EnterpriseService;
import com.job52.service.JobService;
import com.job52.service.PersonService;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    JobService jobService;

    @Autowired
    PersonService personService;

    @Autowired
    EnterpriseService enterpriseService;

    @RequestMapping("/index")
    public String init() {
        return "/admin-index";
    }

    @RequestMapping("/job")
    public String job() {
        return "/admin-position";
    }

    @RequestMapping("/person")
    public String person() {
        return "/admin-person";
    }

    @RequestMapping("/company")
    public String company() {
        return "/admin-company";
    }

    @RequestMapping("/job/search/{value}")
    @ResponseBody
    public String searchJob(@PathVariable("value") String value) {
        List<Job> list = jobService.queryString(value);
        Map<String, Object> map = new HashMap();
        map.put("total", list.size());
        map.put("rows", list);
        System.out.println(map.toString());
        String jsonString = JSON.toJSONString(map);
        return jsonString;
    }

    @RequestMapping("/job/delete")
    @ResponseBody
    public void deleteJob(HttpServletRequest request) {
        //TODO
        //JSONParser json = new JSONParser();
        List<Job> jobs = new ArrayList<Job>();
        jobService.removeJobs(jobs);
    }
    @RequestMapping("/person/search/{value}")
    public String searchPerson(@PathVariable("value") String value) throws Exception {
       //TODO List<Person> list = personService.;
        return "";
    }

    @RequestMapping("/person/delete")
    @ResponseBody
    public void deletePerson(HttpServletRequest request) {

    }

    @RequestMapping("/enterprise/search/{value}")
    @ResponseBody
    public String searchEnterprise(@PathVariable("value") String value) {
        Enterprise enterprise = new Enterprise();
        enterprise.setEname(value);
        List<Enterprise> list = enterpriseService.queryContainsEnterprise(enterprise);
        Map<String, Object> map = new HashMap();
        map.put("total", list.size());
        map.put("rows", list);
        System.out.println(map.toString());
        String jsonString = JSON.toJSONString(map);
        return jsonString;
    }
    @RequestMapping("/enterprise/delete")
    @ResponseBody
    public void deleteEnterprise() {
        //TODO
        List<String> eids = new ArrayList();
        enterpriseService.removeEnterpeises(eids);
    }

}
