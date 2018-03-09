package com.job52.controller;

import com.alibaba.fastjson.JSON;
import com.job52.model.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/test")
public class TestContoller {


    @RequestMapping("/test1")
    @ResponseBody
    /**
     *   pageSize: 10,   //页面大小
     pageNumber: 20,  //页码
     name:10
     */
    public  String Test(int pageSize,int pageNumber,int name){
        Map<String,Object> map = new HashMap<String,Object>();
      List<Test> list = new ArrayList<Test>();
        System.out.println(pageNumber);
        System.out.println(name);
      Test test = new Test();
      test.setID("1");
      test.setName("马云");
      test.setSex("男");
      list.add(test);

      Test test1 = new Test();
      test1.setID("2");
      test1.setName("范冰冰");
      test1.setSex("女");

      list.add(test1);
      map.put("total",200);
      map.put("rows",list);
      String jsonString = JSON.toJSONString(map);

      return jsonString;




   /*  Map<String,Object> map = new HashMap<String, Object>();
     Map<String,String> map1 = new HashMap<String, String>();
        List<  Map<String,String>> list = new ArrayList<  Map<String,String>>();
     map1.put("ID","1");
     map1.put("Name","tom");
     list.add(map1);
        map1.put("ID","2");
        map1.put("Name","om");
        list.add(map1);
      map.put("total",200);
      map.put("rows",list);
        System.out.println(map);*/

       // {total=200, rows=[{ID=2, Name=om}, {ID=2, Name=om}]}
        //{total=200, rows=[{ID='test1', Name='name1', Sex='sex1'}, {ID='test2', Name='name2', Sex='sex2'}]}
       //{total=200, rows=[{ID='2', Name='om', Sex='null'}, {ID='2', Name='om', Sex='null'}]}
    }
}
