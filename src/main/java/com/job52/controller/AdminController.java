package com.job52.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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

    @RequestMapping("/enterprise")
    public String company() {
        return "/admin-company";
    }

    @RequestMapping("/job/search/{value}")
    @ResponseBody
    public String searchJob(@PathVariable("value") String value) {
        System.out.println("------");
        System.out.println(value);
        List<Job> list = jobService.queryString(value);
        Map<String, Object> map = new HashMap();
        map.put("total", list.size());
        map.put("rows", list);
        System.out.println(map.toString());
        String jsonString = JSON.toJSONString(map);
        return jsonString;
    }

    @RequestMapping("/job/delete")
    public String deleteJob(String jids) {
        System.out.println(jids);
        jobService.removeJobs(jobService.queryByJobIds(jids.split(",")));
        return "/admin-position";
    }
    @RequestMapping("/person/search/{value}")
    @ResponseBody
    public String searchPerson(@PathVariable("value") String value) throws Exception {
        System.out.println("---");
        System.out.println(value);
        List<Person> list = personService.queryPersonByCondition(value);
        Map<String, Object> map = new HashMap();
        map.put("total", list.size());
        map.put("rows", list);
        System.out.println(map.toString());
        String jsonString = JSON.toJSONString(map);
        return jsonString;
    }

    @RequestMapping("/person/delete")
    @ResponseBody
    public void deletePerson(String pids) throws Exception {
        System.out.println("---");
        System.out.println(pids);
        personService.deletePersonById(pids.split(","));
    }

    @RequestMapping("/enterprise/search/{value}")
    @ResponseBody
    public String searchEnterprise(@PathVariable("value") String value) {
        System.out.println(value);
        Enterprise enterprise = new Enterprise();
        enterprise.setEname(value);
        enterprise.setEid(value);
        enterprise.seteType(value);
        enterprise.setAdddress(value);
        enterprise.setDescriptionte(value);
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
    public void deleteEnterprise(String eids) {
        System.out.println(eids);
        List<String> list = new ArrayList<String>();
        for (String s : eids.split(",")) {
            list.add(s);
        }
        enterpriseService.removeEnterpeises(list);
    }

}
