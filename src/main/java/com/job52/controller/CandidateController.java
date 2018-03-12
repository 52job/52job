package com.job52.controller;

import com.alibaba.fastjson.JSON;
import com.job52.dto.packet1;
import com.job52.model.Candidate;
import com.job52.model.Job;
import com.job52.model.Person;
import com.job52.model.Resume;
import com.job52.service.CandidateInfoService;
import com.job52.service.JobService;
import com.job52.service.PersonService;
import com.job52.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CandidateInfoService candidateInfoService;
    @Autowired
    private ResumeService resumeService;
    @Autowired
    private JobService jobService;
    @Autowired
    private PersonService personService;

    @RequestMapping("/candidate_1")
    public String index() {
        return "/company-applymentmanageF";
    }

    /**
     * get uncheck list
     * @return
     */
    @RequestMapping(value = "/uncheckList",method = RequestMethod.GET)
    @ResponseBody
    public  String uncheckList() throws Exception {
        List<Candidate> candidates = candidateInfoService.queryContainsCandidate(new Candidate(0,0));
        for (Candidate tmp : candidates) {
            System.out.println(tmp);
            System.out.println("___________________________________________________________get");
        }

        List<packet1> packet1s = new ArrayList<packet1>();
        Map<String,Object> map = new HashMap<String, Object>();
        int len = candidates.size();
        String jid,pid,rid,name,job;
        for (int i = 0; i < len; i++) {
            Candidate c = candidates.get(i);
            jid = c.getJid();
            pid = c.getPid();
            rid = c.getRid();
            name = personService.queryPerson(c.getPid()).getUserName();
            job = jobService.getJob(c.getJid()).getJname();
            packet1s.add(i,new packet1(jid,pid,rid,name,job));
        }
        map.put("total",len);
        map.put("rows",packet1s);
        String jsonString = JSON.toJSONString(map);
        System.out.println("!!!"+jsonString);
        return jsonString;
    }


    /**
     * set candidate isread
     * @param jid
     * @param pid
     * @param ispass
     * @return
     */
    @RequestMapping(value = "/{jid}:{pid}/{ispass}/setIsRead/uncheckList",method = RequestMethod.GET)
    public String setIsRead(@PathVariable("jid") String jid, @PathVariable("pid") String pid,@PathVariable("ispass") Integer ispass ) {
        if(jid == null ||pid == null) {
            return "redirect:/uncheckList" ;
        }
        Candidate c=candidateInfoService.getCandidate(new Candidate(jid,pid));
        c.setIsread(1);
        c.setIspass(ispass);
        try {
            candidateInfoService.updateCandidate(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "uncheckList";
    }

    /**
     * get resume
     * @param rid
     * @return
     */
    @RequestMapping(value = "/{rid}/getResumeByIdInUncheck/uncheckList",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Resume getResumeByIdInUncheck(@PathVariable("rid") String rid) {
        Resume r = resumeService.getResume(rid);
        return r;
    }


    /**
     * get check list
     * @param model
     * @return
     */
    @RequestMapping(value = "/checkList",method = RequestMethod.GET)
    @ResponseBody
    public String checkList(Model model) throws Exception {
        List<Candidate> candidates = candidateInfoService.queryContainsCandidate(new Candidate(1));
        List<packet1> packet1s = new ArrayList<packet1>();
        Map<String,Object> map = new HashMap<String, Object>();
        int len = candidates.size();
        String jid,pid,rid,name,job;
        Integer ispass;
        for (int i = 0; i < len; i++) {
            Candidate c = candidates.get(i);
            jid = c.getJid();
            pid = c.getPid();
            rid = c.getRid();
            ispass = c.getIspass();
            name = personService.queryPerson(c.getPid()).getUserName();
            job = jobService.getJob(c.getJid()).getJname();
            packet1s.add(i,new packet1(jid,pid,rid,ispass,name,job));
        }
        map.put("total",len);
        map.put("rows",packet1s);
        String jsonString = JSON.toJSONString(map);
        return jsonString;
    }

    /**
     * get rusume
     * @param rid
     * @return
     */
    @RequestMapping(value = "/{rid}/getResumeByIdInCheck/uncheckList",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Resume getResumeByIdInCheck(@PathVariable("rid") String rid) {
        Resume r = resumeService.getResume(rid);
        return r;
    }

    /**
     * delect candidates
     * @param request
     */
    @RequestMapping(value = "/delectCandidates/checkList",method = RequestMethod.DELETE)
    @Transactional
    public void delectCandidates(HttpServletRequest request) {
        List<String> jids = new ArrayList<String>();
        List<String> pids = new ArrayList<String>();
        jids = (List<String>) request.getAttribute("jids");
        pids = (List<String>) request.getAttribute("pids");
        try {
            candidateInfoService.removeCandidates(jids,pids);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * delect candidate
     * @param request
     */
    @RequestMapping(value = "/delectCandidate/checkList",method = RequestMethod.DELETE)
    public void delectCandidate(HttpServletRequest request) {
        String jid = null;
        String pid = null;
        jid = (String) request.getAttribute("jid");
        pid = (String) request.getAttribute("pid");
        try {
            candidateInfoService.removeCandidate(new Candidate(jid,pid));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * query resumes
     * @param request
     * @param model
     */
    @RequestMapping(value = "/queryResumes",method = RequestMethod.GET)
    @ResponseBody
    public String queryResumes(HttpServletRequest request , Model model) {
        Resume resume = (Resume) request.getAttribute("resume");
        List<Resume> resumeList = resumeService.queryAll(resume);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("total",resumeList.size());
        map.put("rows",resumeList);
        String JsonString = JSON.toJSONString(map);
        return  JsonString;
    }


}
