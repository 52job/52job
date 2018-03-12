package com.job52.controller;

import com.alibaba.fastjson.JSON;
import com.job52.model.Collection;
import com.job52.model.CollectionKey;
import com.job52.model.Job;
import com.job52.model.Person;
import com.job52.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    /**
     * 查找用户的所有收藏列表
     * @param model
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
        map.put("total",200);
        map.put("rows",jobs);
        String jsonString = JSON.toJSONString(map);
        return jsonString;
   }

    /**
     * 删除收藏信息
     * @param model
     * @return
     * @throws Exception 异常信息
     */
    @RequestMapping("/deleteCollection")
    public String deleteCollection(Model model) throws Exception{
        List<CollectionKey> ids = null;
        collectionService.deleteCollection(ids);
        return null;
    }
}
