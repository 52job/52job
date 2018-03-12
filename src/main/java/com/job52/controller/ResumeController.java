package com.job52.controller;

import com.job52.model.Resume;
import com.job52.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    //@RequestMapping("/addResume" , method = RequestMethod.POST)
    @RequestMapping("/add")
    public String addResume(HttpServletRequest request) throws Exception {
        Resume resume = new Resume("r2","t2",request.getParameter("name"),
                request.getParameter("sex"),request.getParameter("portrait"),
                request.getParameter("birthday"),request.getParameter("tel"),
                request.getParameter("startWorkTime"),request.getParameter("jobState"),
                request.getParameter("email"),request.getParameter("address"),
                request.getParameter("highestEducation"),request.getParameter("graduationUniversity"),
                request.getParameter("graduationTime"),request.getParameter("careerIntention"),
                request.getParameter("major"),request.getParameter("workExp"),
                Integer.parseInt(request.getParameter("isPublic")),request.getParameter("accessory"));
//        resume.setRid("r2");
//        resume.setPid("t2");
//        resume.setName(request.getParameter("name"));
//        resume.setSex(request.getParameter("sex"));
//        resume.setPortrait(request.getParameter("portrait"));
//        resume.setBirthday(request.getParameterNames("birthday"));
//        resume.setName(request.getParameter("tel"));
//        resume.setName(request.getParameter("startWorkTime"));
//        resume.setName(request.getParameter("jobState"));
//        resume.setName(request.getParameter("email"));
//        resume.setName(request.getParameter("address"));
//        resume.setName(request.getParameter("highestEducation"));
//        resume.setName(request.getParameter("graduationUniversity"));
//        resume.setName(request.getParameter("name"));
//        resume.setName(request.getParameter("name"));
//        resume.setName(request.getParameter("name"));
//        resume.setName(request.getParameter("name"));
//        resume.setName(request.getParameter("name"));
//        resume.setName(request.getParameter("name"));
        System.out.println(resume.toString());
        if(resumeService.addResume(resume)>0){
            return "success!";
        }else{
            return "fail";
        }
    }

    @RequestMapping("/create")
    public String create(){
        return "/person-resumecreate";
    }

    @RequestMapping("/delete/{rid}")
    public int removeResume(@PathVariable("rid") String rid){
        return resumeService.removeResume(rid);
    }

    @RequestMapping("/update")
    public int updateResume(Resume resume){
        return resumeService.updateResume(resume);
    }

    @RequestMapping("/searchRid/{rid}")
    @ResponseBody
    public Resume selectResumeByRid(@PathVariable("rid") String rid){
        return resumeService.getResume(rid);
    }

    @RequestMapping("/search")
    @ResponseBody
//    public String searchResume(HttpServletRequest request){
//        Map<String, Object> map = new HashMap();
//        Resume resume = null;
//        resume.setRid(request.getParameter("careerIntention"));
//        resume.setRid(request.getParameter("graduationUniversity"));
//        resume.setRid(request.getParameter("highestEducation"));
//        resume.setRid(request.getParameter("workExp"));
//        System.out.println("--------");
//        System.out.println(request.getParameter("careerIntention").toString());
//        System.out.println(request.getParameter("graduationUniversity").toString());
//        System.out.println(request.getParameter("searchKey1").toString());
//        System.out.println(request.getParameter("highestEducation").toString());
//        System.out.println(request.getParameter("workExp").toString());
//        List<Resume> list = resumeService.queryAll(resume);
//
//        map.put("total", list.size());
//        map.put("rows", list);
//        System.out.println(map.toString());
//        String jsonString = JSON.toJSONString(map);
//        return jsonString;
//    }
    public String resumeList(Resume resume){
        Resume resume1 = null;
        Map<String, Object> map = new HashMap();
        List<Resume> resumeList =  resumeService.queryAll(resume1);
        map.put("total", resumeList.size());
        map.put("rows", resumeList);
        String jsonString = JSON.toJSONString(map);
        return jsonString;
    }

    @RequestMapping("/setState")
    public int setResumeState(String rid,int ispublic){
        return resumeService.setResumeState(rid,ispublic);
    }

    @RequestMapping("s")
    public String sss(){
        return "/ResumeSearch";
    }
}
