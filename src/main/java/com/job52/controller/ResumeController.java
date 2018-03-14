package com.job52.controller;

import com.job52.model.Resume;
import com.job52.service.ResumeService;
import com.job52.util.CommonUtil;
import com.job52.util.UploadUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    //@RequestMapping("/addResume" , method = RequestMethod.POST)
    @RequestMapping("/add")
    @ResponseBody
    public String addResume(HttpServletRequest request) throws ServletException, IOException, FileUploadException {
        Resume resume = new Resume(CommonUtil.getResumeId(),"t3",request.getParameter("pname"),
                request.getParameter("resumeName"),request.getParameter("sex"),null,
                request.getParameter("birthday"),request.getParameter("tel"),
                request.getParameter("startWorkTime"),request.getParameter("jobState"),
                request.getParameter("email"),request.getParameter("address"),
                request.getParameter("highestEducation"),request.getParameter("graduationUniversity"),
                request.getParameter("graduationTime"),request.getParameter("careerIntention"),
                request.getParameter("major"),request.getParameter("workExp"),
                request.getParameter("isPublic"),"未读",null);
            //Map<String,String> uploadMap = new HashMap<String, String>();
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024*100);
            factory.setRepository(new File(request.getServletContext().getRealPath("img")));
            //得到文件上传对象
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(1024*1024*1);
            upload.setSizeMax(1024*1024*10);
            upload.setHeaderEncoding("utf-8");
            //得到文件项列表
            List<FileItem> list = upload.parseRequest(request);
            //遍历文件列表
            String[] urls = new String[2];
            int i = 0;
            for(FileItem item : list) {
                if (!item.isFormField()) { //如果不是普通文本，则认为是文件，进行保存
                    String realname = item.getName();
                    String uuidname = UUID.randomUUID().toString() + "_" + realname;
                    String hash = Integer.toHexString(uuidname.hashCode());
                    String uploadpath = request.getServletContext().getRealPath("img");
                    String imgurl = "img";
                    for (char c : hash.toCharArray()) {
                        uploadpath += "/" + c;
                        imgurl += "/" + c;
                    }
                    imgurl += "/" + uuidname;
                    //uploadMap.put("imgurl", imgurl);
                    File file = new File(uploadpath);
                    if (!file.isDirectory())
                        file.mkdirs();
                    System.out.println("imgrul:" + imgurl);
                    System.out.println("uploadpath:" + uploadpath);
                    System.out.println("uuidname:" + uuidname);
                    InputStream in = item.getInputStream();
                    OutputStream out = new FileOutputStream(new File(uploadpath, uuidname));
                    //调用工具类进行读写
                    UploadUtils.In2Out(in, out);
                    //调用工具类进行关闭流操作
                    UploadUtils.close(in, out);
                    item.delete();
                    //--生成缩略图
                    urls[i] = imgurl;
                    i++;
                    // b_info.setImgurl(imgurl);
                    //   response.getWriter().write("更新图片成功");
                    //  response.setHeader("Refresh", "1;url="+request.getContextPath()+"/addpic.jsp");
                }
            }
            resume.setPortrait(urls[0]);
            resume.setAccessory(urls[1]);
        if(resumeService.addResume(resume)>0){
            return "success";
        }else{
            return "fail";
        }
    }

    @RequestMapping("/create")
    public String create(){
        return "/person-resumecreate";
    }

    @RequestMapping("/delete/{rid}")
    @ResponseBody
    public String removeResume(@PathVariable("rid") String rid){
        if(resumeService.removeResume(rid)>0){
            return "success!";
        }else{
            return "fail";
        }
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

    @RequestMapping("/manage")
    @ResponseBody
    public String personResumeManage(String pid){
        Resume resume = new Resume();
        resume.setPid(pid);
        Map<String, Object> map = new HashMap();
        List<Resume> resumeList =  resumeService.queryAll(resume);
        map.put("total", resumeList.size());
        map.put("rows", resumeList);
        String jsonString = JSON.toJSONString(map);
        return jsonString;
    }

    @RequestMapping("/personResumeManage")
    public String personResumeManage(){
        return "/person-resumemanage";
    }

    @RequestMapping("/query")
    @ResponseBody
    public String query(String careerIntention,String graduationUniversity,String highestEducation){
        Resume resume = new Resume();
        resume.setCareerIntention(careerIntention);
        resume.setGraduationUniversity(graduationUniversity);
        resume.setHighestEducation(highestEducation);
        Map<String, Object> map = new HashMap();
        List<Resume> resumeList =  resumeService.queryAll(resume);
        map.put("total", resumeList.size());
        map.put("rows", resumeList);
        String jsonString = JSON.toJSONString(map);
        return jsonString;
    }
    @RequestMapping("/search")
    public String search(){
        return "/ResumeSearch";
    }

    @RequestMapping("/setState")
    public int setResumeState(String rid,int ispublic){
        return resumeService.setResumeState(rid,ispublic);
    }

}
