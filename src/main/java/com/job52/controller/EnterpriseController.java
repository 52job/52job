package com.job52.controller;

import com.job52.model.Enterprise;
import com.job52.model.Job;
import com.job52.service.EnterpriseService;
import com.job52.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/enterpeise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private JobService jobService;

    @RequestMapping("/enterpeise")
    public String index() {
        return "/company-applymentmanageF";
    }

    /**
     * show joblist
     * @param model
     * @return
     */
    @RequestMapping(value = "/jobList",method = RequestMethod.GET)
    public String jobList(Model model) {
        List<Job> jobs = jobService.query(new Job(1));
        model.addAttribute("jobs",jobs);
        return "jobList";
    }

    /**
     * add job
     * @param request
     * @param model
     */
    @RequestMapping(value = "/addJob",method = RequestMethod.GET)
    public void addJobs(HttpServletRequest request , Model model) {
        Job j = null;
        j = (Job) request.getAttribute("job");
        try{
            jobService.addJob(j);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error",e);
            throw new RuntimeException();
        }
    }

    /**
     * delect jobs
     * @param request
     * @param model
     */
    @RequestMapping(value = "/delectJobs",method = RequestMethod.DELETE)
    public void delectJobs(HttpServletRequest request , Model model) {
        List<Job> jobs = new ArrayList<Job>();
        jobs = (List<Job>) request.getAttribute("jobs");
        try{
            jobService.removeJobs(jobs);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error",e);
            throw new RuntimeException();
        }
    }

    /**
     * update job1
     * @param jid
     * @return
     */
    @RequestMapping(value = "/{jid}/updateJob1",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Job updateJob1(@PathVariable("jid") String jid) {
        Job j = jobService.getJob(jid);
        return j;
    }

    /**
     * update job2
     * @param jid
     * @param request
     * @param model
     */
    @RequestMapping(value = "/{jid}/updateJob2",method = RequestMethod.GET)
    public void updateJob2(@PathVariable("jid") String jid,HttpServletRequest request,Model model) {
        Job j = (Job) request.getAttribute("job");
        try{
            jobService.updateJob(j);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error",e);
            throw new RuntimeException();
        }
    }

    /**
     * show passed joblist
     * @param model
     * @return
     */
    @RequestMapping(value = "/jobPassedList",method = RequestMethod.GET)
    public String jobPassedList(Model model) {
        List<Job> jobs = jobService.query(new Job(0));
        model.addAttribute("jobs",jobs);
        return "jobPassedList";
    }

    /**
     * delect passed jobs
     * @param request
     * @param model
     */
    @RequestMapping(value = "/delectPassedJobs",method = RequestMethod.DELETE)
    public void delectPassedJobs(HttpServletRequest request , Model model) {
        List<Job> jobs = new ArrayList<Job>();
        jobs = (List<Job>) request.getAttribute("jobs");
        try{
            jobService.removeJobs(jobs);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error",e);
            throw new RuntimeException();
        }
    }

    /**
     * delect passed job
     * @param jid
     * @param request
     * @param model
     */
    @RequestMapping(value = "/delectPassedJob",method = RequestMethod.DELETE)
    public void delectPassedJob(@PathVariable("jid") String jid,HttpServletRequest request , Model model) {
        Job j = (Job) request.getAttribute("job");
        try{
            jobService.removeJob(j);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error",e);
            throw new RuntimeException();
        }
    }

    /**
     * show prepared joblist
     * @param model
     * @return
     */
    @RequestMapping(value = "/jobPrepareddList",method = RequestMethod.GET)
    public String jobPrepareddList(Model model) {
        List<Job> jobs = jobService.query(new Job(2));
        model.addAttribute("jobs",jobs);
        return "jobPassedList";
    }

    /**
     * add prepared job
     * @param request
     * @param model
     */
    @RequestMapping(value = "/addPreparedJobs",method = RequestMethod.GET)
    public void addPreparedJobs(HttpServletRequest request , Model model) {
        Job j = null;
        j = (Job) request.getAttribute("job");
        try{
            jobService.addJob(j);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error",e);
            throw new RuntimeException();
        }
    }

    /**
     * delect prepared jobs
     * @param request
     * @param model
     */
    @RequestMapping(value = "/delectPreparedJobs",method = RequestMethod.DELETE)
    public void delectPreparedJobs(HttpServletRequest request , Model model) {
        List<Job> jobs = new ArrayList<Job>();
        jobs = (List<Job>) request.getAttribute("jobs");
        try{
            jobService.removeJobs(jobs);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error",e);
            throw new RuntimeException();
        }
    }

    /**
     * update prepared job1
     * @param jid
     * @return
     */
    @RequestMapping(value = "/{jid}/updatePreparedJob1",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Job updatePreparedJob1(@PathVariable("jid") String jid) {
        Job j = jobService.getJob(jid);
        return j;
    }

    /**
     * update Prepared job2
     * @param jid
     * @param request
     * @param model
     */
    @RequestMapping(value = "/{jid}/updatePreparedJob2",method = RequestMethod.GET)
    public void updatePreparedJob2(@PathVariable("jid") String jid,HttpServletRequest request,Model model) {
        Job j = (Job) request.getAttribute("job");
        try{
            jobService.updateJob(j);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error",e);
            throw new RuntimeException();
        }
    }

    /**
     * update prepared job jobState
     * @param jid
     * @param model
     */
    @RequestMapping(value = "/{jid}/updatePreparedJobJobState",method = RequestMethod.GET)
    public  void updatePreparedJobJobState(@PathVariable("jid") String jid,Model model) {
        Job j = jobService.getJob(jid);
        j.setJobStatue(1);
        try{
            jobService.updateJob(j);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error",e);
            throw new RuntimeException();
        }
    }

    @RequestMapping(value = "/{eid}/getEnterpriseInfo",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Enterprise enterprise(@PathVariable("eid") String eid) {
        Enterprise enterprise = enterpriseService.getEnterprise(eid);
        return enterprise;
    }

    @RequestMapping(value = "/{eid}/updateEnterpriseInfo",method = RequestMethod.GET)
    public void updateEnterpriseInfo(@PathVariable("eid") String eid, HttpServletRequest request,Model model) {
        String username = (String) request.getAttribute("username");
        String password = (String) request.getAttribute("password");
        String name = (String) request.getAttribute("name");
        String address = (String) request.getAttribute("address");
        String type = (String) request.getAttribute("type");
        Integer number = (Integer) request.getAttribute("number");
        String email = (String) request.getAttribute("email");
        String contact = (String) request.getAttribute("contact");
        String imgurl = (String) request.getAttribute("imgurl");
        String description = (String) request.getAttribute("description");
        Enterprise enterprise = new Enterprise(eid,username,password,name,address,type,number,email,contact,imgurl,description);
        try{
            enterpriseService.updateEnterprise(enterprise);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error",e);
            throw new RuntimeException();
        }
    }
}
