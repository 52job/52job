package com.job52.controller;

import com.alibaba.fastjson.JSON;
import com.job52.model.*;
import com.job52.service.ApplicationService;
import com.job52.service.CollectionService;
import com.job52.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;
    @Autowired
    private ResumeService resumeService;
    @Autowired
    private ApplicationService applicationService;



    /**
     * 查找用户的所有收藏列表
     * @param session
     * @return 用户的收藏列表
     * @throws Exception 异常信息
     */

    @RequestMapping("/findAllCollection")
    @ResponseBody
   public String findAllCollection(HttpSession session) throws Exception{
        /**
         * 1.从session中得到用户信息
         * 2.根据用户的id查找到用户的收藏职位id
         * 3.根据收藏职位的id找到具体的职位信息
         */
        Map<String ,Object> map = new HashMap<String, Object>();
        Person p = (Person) session.getAttribute("person");
        List<Job> jobs = collectionService.findAllCollection(p.getPid());
        map.put("total",jobs.size());
        map.put("rows",jobs);
        String jsonString = JSON.toJSONString(map);
        return jsonString;
   }

    /**
     * 删除收藏信息
     * @param jids 职位信息的id
     * @return
     * @throws Exception 异常信息
     */
    @RequestMapping("/deleteCollection")
    @ResponseBody
    public Map<String,String> deleteCollection(String jids,HttpSession session) throws Exception{
        System.out.println(jids);
        Map<String,String> map = new HashMap<String, String>();
        Person p = (Person) session.getAttribute("person");
        long count =  collectionService.deleteCollection(jids,p.getPid());
        if(count>0){
            map.put("msg","成功删除"+count+"条记录");
        }else{
            map.put("msg","删除失败,请稍后再试");
        }
        return map;
    }

    @RequestMapping("/getResume")
    @ResponseBody
    public String queryResumeByPid(HttpSession session) throws Exception{
        Resume r = new Resume();
        Person p = (Person) session.getAttribute("person");
        r.setPid(p.getPid());
        List<Resume> resumes = new ArrayList<Resume>();
        Resume r1 = new Resume();
        r1.setRid("12");
        resumes.add(r1);
        Resume r2 = new Resume();
        r2.setRid("23");
        resumes.add(r2);
        String jsonString = JSON.toJSONString(resumes);
        return jsonString;

    }

    @RequestMapping("/addApplication")
    @ResponseBody
    public Map<String,String> addApplication(HttpSession session,String rid,String jid) {
        Map<String,String> map = new HashMap<String, String>();
        try {
            Application application = new Application();
            Person p = (Person) session.getAttribute("person");
            application.setPid(p.getPid());
            application.setRid(rid);
            application.setGid(jid);
            application.setIsPass(0);
            application.setIsRead(0);
            applicationService.addApplication(application);
            map.put("msg","申请成功");
         }catch (Exception e){
            map.put("msg","申请失败");
        }
        return  map;
    }
}
