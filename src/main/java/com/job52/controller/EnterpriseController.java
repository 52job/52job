package com.job52.controller;

import com.alibaba.fastjson.JSON;
import com.job52.model.Enterprise;
import com.job52.model.Job;
import com.job52.service.EnterpriseService;
import com.job52.service.JobService;
import com.job52.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private JobService jobService;

    @RequestMapping("/enterprise_1")
    public String index1() {
        return "/company-existpositioninfo";
    }

    @RequestMapping("/enterprise_2")
    public String index2() {
        return "/company-pastpositioninfo";
    }

    @RequestMapping("/enterprise_3")
    public String index3() {
        return "/company-roughpositioninfo";
    }

    @RequestMapping("/enterprise_4")
    public String index(HttpSession session) {
        session.setAttribute("eid","1");
        return "/FirmInfoManagement";
    }

    /**
     * show joblist
     * @return
     */
    @RequestMapping(value = "/jobList",method = RequestMethod.GET)
    @ResponseBody
    public String jobList() {
        List<Job> jobs = jobService.query(new Job(1));
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("total",jobs.size());
        map.put("rows",jobs);
        String JsonString = JSON.toJSONString(map);
        System.out.println(JsonString);
        return  JsonString;
    }

    /**
     * add job
     * @param request
     */
    @RequestMapping(value = "/addJob",method = RequestMethod.GET)
    @ResponseBody
    public String addJobs(HttpServletRequest request ,HttpSession session) {
        String jid = CommonUtil.getJobId();
        String eid = (String) session.getAttribute("eid");
        Enterprise enterprise  = new Enterprise(eid);
        Integer requiredNumber = Integer.parseInt(request.getParameter("requiredNumber"));
        String jname = request.getParameter("jname");
        Integer requiredWorkyear = Integer.parseInt(request.getParameter("requiredWorkyear"));
        Integer requiredEducation = Integer.parseInt(request.getParameter("requiredEducation"));
        Integer minSalary = Integer.parseInt(request.getParameter("minSalary"));
        Integer maxSalary = Integer.parseInt(request.getParameter("maxSalary"));
        String benefit = request.getParameter("benefit");
        String jobDesc = request.getParameter("jobDesc");
        String jobType = request.getParameter("jobType");
        String workPlace = request.getParameter("workPlace");
        Integer jobStatue = Integer.parseInt(request.getParameter("jobStatue"));
        Job j = new Job(jid,enterprise,"0",requiredNumber,jname,requiredWorkyear,requiredEducation,minSalary,
                maxSalary,benefit,jobDesc,jobType,workPlace,jobStatue,new Date(1,1,1));
        try{
            jobService.addJob(j);
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
        return "true";
    }

    /**
     * delect jobs
     * @param request
     */
    @RequestMapping(value = "/delectJobs",method = RequestMethod.DELETE)
    @ResponseBody
    public String delectJobs(HttpServletRequest request) {
        List<Job> jobs = new ArrayList<Job>();
        jobs = (List<Job>) request.getAttribute("jobs");
        try{
            jobService.removeJobs(jobs);
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
        return "true";
    }


    /**
     * show passed joblist
     * @return
     */
    @RequestMapping(value = "/jobPassedList",method = RequestMethod.GET)
    @ResponseBody
    public String jobPassedList() {
        List<Job> jobs = jobService.query(new Job(0));
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("total",jobs.size());
        map.put("rows",jobs);
        String JsonString = JSON.toJSONString(map);
        System.out.println(JsonString);
        return  JsonString;
    }

    /**
     * delect passed jobs
     * @param request
     */
    @RequestMapping(value = "/delectPassedJobs",method = RequestMethod.DELETE)
    @ResponseBody
    public String delectPassedJobs(HttpServletRequest request) {
        List<Job> jobs = new ArrayList<Job>();
        jobs = (List<Job>) request.getAttribute("jobs");
        try{
            jobService.removeJobs(jobs);
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
        return "true";
    }

    /**
     * delect passed job
     * @param jid
     * @param request
     */
    @RequestMapping(value = "/delectPassedJob",method = RequestMethod.DELETE)
    @ResponseBody
    public String delectPassedJob(@PathVariable("jid") String jid,HttpServletRequest request) {
        Job j = (Job) request.getAttribute("job");
        try{
            jobService.removeJob(j);
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
        return "true";
    }

    /**
     * show prepared joblist
     * @param model
     * @return
     */
    @RequestMapping(value = "/jobPrepareddList",method = RequestMethod.GET)
    @ResponseBody
    public String jobPrepareddList(Model model) {
        List<Job> jobs = jobService.query(new Job(2));
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("total",jobs.size());
        map.put("rows",jobs);
        String JsonString = JSON.toJSONString(map);
        return  JsonString;
    }

//    /**
//     * delect prepared jobs
//     * @param request
//     * @param request
//     */
//    @RequestMapping(value = "/delectPreparedJobs",method = RequestMethod.DELETE)
//    @ResponseBody
//    public String delectPreparedJobs(HttpServletRequest request) {
//        List<Job> jobs = new ArrayList<Job>();
//        jobs = (List<Job>) request.getAttribute("jobs");
//        try{
//            jobService.removeJobs(jobs);
//        }catch (Exception e){
//            e.printStackTrace();
//            return "false";
//        }
//        return "true";
//    }

//    /**
//     * update job1
//     * @return
//     */
//    @RequestMapping(value = "/updateJob1",method = RequestMethod.GET,
//            produces = {"application/json;charset=UTF-8"})
//    @ResponseBody
//    public String updatePreparedJob1(HttpServletRequest request) {
//        String jid = request.getParameter("jid");
//        Job j = jobService.getJob(jid);
//        String JsonString = JSON.toJSONString(j);
//        return JsonString;
//    }

    /**
     * update job2
     * @param request
     */
    @RequestMapping(value = "/updateJob2",method = RequestMethod.POST)
    @ResponseBody
    public String updatePreparedJob2(HttpServletRequest request) {
        System.out.println("in--------------------");
        String jid = request.getParameter("jid");
        Enterprise enterprise  = new Enterprise(request.getParameter("eid"));
        String pid = request.getParameter("pid");
        String jname = request.getParameter("jname");
        System.out.println("---------------------requiredNumber:"+request.getParameter("requiredNumber"));
        Integer requiredNumber = Integer.parseInt(request.getParameter("requiredNumber"));

        Integer requiredWorkyear = Integer.parseInt(request.getParameter("requiredWorkyear"));
        Integer requiredEducation = Integer.parseInt(request.getParameter("requiredEducation"));
        Integer minSalary = Integer.parseInt(request.getParameter("minSalary"));
        Integer maxSalary = Integer.parseInt(request.getParameter("maxSalary"));
        String benefit = request.getParameter("benefit");
        String jobDesc = request.getParameter("jobDesc");
        String jobType = request.getParameter("jobType");
        String workPlace = request.getParameter("workPlace");
        Integer jobStatue = Integer.parseInt(request.getParameter("jobStatue"));
        System.out.println("Date-------------"+request.getParameter("createTime"));
        //Date createTime = new Date(request.getParameter("createTime"));

        try{
            System.out.println("out1----------------------------------");
            jobService.updateJob(new Job(jid,enterprise,pid,requiredNumber,jname,requiredWorkyear,requiredEducation,minSalary
                    ,maxSalary,benefit,jobDesc,jobType,workPlace,jobStatue,new Date()));

            System.out.println("out2----------------------------------");
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
        return "true";
    }

    /**
     * update prepared job jobState
     */
    @RequestMapping(value = "/updatePreparedJobJobState",method = RequestMethod.GET)
    @ResponseBody
    public  String updatePreparedJobJobState(HttpServletRequest request) {
        String jid = request.getParameter("jid");
        Job j = jobService.getJob(jid);
        j.setJobStatue(1);
        try{
            jobService.updateJob(j);
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
        return "true";
    }

    @RequestMapping(value = "/getEnterpriseInfo",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Enterprise enterprise(HttpServletRequest request) {
        String eid = request.getParameter("eid");
        Enterprise enterprise = enterpriseService.getEnterprise(eid);
        return enterprise;
    }

    @RequestMapping("/MyEnterprise")
    @ResponseBody
    public Enterprise getEnterprise(HttpSession session) {
        String eid = String.valueOf(session.getAttribute("eid"));
        Enterprise enterprise = enterpriseService.getEnterprise(eid);
        return enterprise;
    }

    @RequestMapping(value = "/updateEnterpriseInfo",method = RequestMethod.POST)
    public String updateEnterpriseInfo(HttpServletRequest request,HttpSession session) {
        String eid = String.valueOf(session.getAttribute("eid"));
        System.out.println("------------------" + eid);
        Enterprise enterprise = enterpriseService.getEnterprise(eid);
        enterprise.seteUsername(request.getParameter("eUsername"));
        enterprise.setePassword(request.getParameter("ePassword"));
        enterprise.setEname(request.getParameter("ename"));
        enterprise.setAdddress(request.getParameter("adddress"));
        enterprise.seteType(request.getParameter("eType"));
        enterprise.seteNumber(Integer.parseInt(request.getParameter("eNumber")));
        enterprise.setEmail(request.getParameter("email"));
        enterprise.setContact(request.getParameter("contact"));
        enterprise.setImageUrl(String.valueOf(request.getParameter("imgurl")));
        enterprise.setDescriptionte(request.getParameter("descriptionte"));
        System.out.println(enterprise);
        try{
            enterpriseService.updateEnterprise(enterprise);
        }catch (Exception e){
            e.printStackTrace();
            return "/FirmInfoManagement";
        }
        return "/FirmInfoManagement";
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delectCandidate(String ids) {
        System.out.println(ids+"--------------------------------------------------");
        List<Job> jids = new ArrayList<Job>();
        for (String s : ids.split(",")) {
            Job job= new Job();
            job.setJid(s);
            jids.add(job);
        }
        try {
            jobService.removeJobs(jids);
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
        return "true";
    }

}
