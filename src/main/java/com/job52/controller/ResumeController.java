package com.job52.controller;

import com.job52.model.Person;
import com.job52.model.Resume;
import com.job52.service.ResumeService;
import com.job52.util.CommonUtil;
import com.job52.util.UploadUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Controller
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    /**
     * 创建个人简历入口
     * @return
     */
    @RequestMapping("/create")
    public String create(){
        return "/person-resumecreate";
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addResume(HttpServletRequest request) throws ServletException, IOException, FileUploadException, InvocationTargetException, IllegalAccessException {
         Map<String,String> map = new HashMap<String, String>();
       /* Resume resume = new Resume(CommonUtil.getResumeId(),"t3",request.getParameter("pname"),
                request.getParameter("resumeName"),request.getParameter("sex"),null,
                request.getParameter("birthday"),request.getParameter("tel"),
                request.getParameter("startWorkTime"),request.getParameter("jobState"),
                request.getParameter("email"),request.getParameter("address"),
                request.getParameter("highestEducation"),request.getParameter("graduationUniversity"),
                request.getParameter("graduationTime"),request.getParameter("careerIntention"),
                request.getParameter("major"),request.getParameter("workExp"),
                request.getParameter("isPublic"),"未读",null);*/
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
                }else {
                    String name = item.getFieldName();
                    String value= item.getString("UTF-8");
                    map.put(name,value);
                    System.out.println(name+":"+value);
                }
            }
            Resume resume = new Resume();
           //BeanUtils.copyProperties(resume,map);
            org.apache.commons.beanutils.BeanUtils.populate(resume,map);
            resume.setRid(CommonUtil.getResumeId());
            resume.setPid("test");
            resume.setResumeState("未读");
            resume.setPortrait(urls[0]);
            resume.setAccessory(urls[1]);
        if(resumeService.addResume(resume)>0){
            return "<script>alert(\"create success!\");window.history.back();</script>";
        }else{
            return "<script>alert(\"create fail!\");window.history.back();</script>";
        }
    }

    /**
     * 删除简历
     * @param rid
     * @return
     */
    @RequestMapping("/delete/{rid}")
    @ResponseBody
    public String removeResume(@PathVariable("rid") String rid){
        if(resumeService.removeResume(rid)>0){
            return "<script>alert(\"delete success!\");window.history.back();</script>";
        }else{
            return "<script>alert(\"delete fail!\");window.history.back();</script>";
        }
    }

    /**
     * 更新个人简历
     * @param request
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws FileUploadException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping("/update")
    @ResponseBody
    public String updateResume(HttpServletRequest request) throws ServletException, IOException, FileUploadException, InvocationTargetException, IllegalAccessException {
        Resume resume = new Resume();
        if(request.getParameter("portrait") != null && request.getParameter("accessory") != null){
            Map<String,String> map = new HashMap<String, String>();
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
                } else {
                    String name = item.getFieldName();
                    String value = item.getString("UTF-8");
                    map.put(name, value);
                    System.out.println(name + ":" + value);
                }
            }
            //BeanUtils.copyProperties(resume,map);
            org.apache.commons.beanutils.BeanUtils.populate(resume,map);
            resume.setRid(CommonUtil.getResumeId());
            resume.setPid("test");
            resume.setResumeState("未读");
            resume.setPortrait(urls[0]);
            resume.setAccessory(urls[1]);
        }else{
            resume.setResumeName(request.getParameter("resumeName"));
            resume.setSex(request.getParameter("sex"));
            resume.setBirthday(request.getParameter("birthday"));
            resume.setTel(request.getParameter("tel"));
            resume.setStartWorkTime(request.getParameter("startWorkTime"));
            resume.setJobState(request.getParameter("jobState"));
            resume.setEmail(request.getParameter("email"));
            resume.setAddress(request.getParameter("address"));
            resume.setHighestEducation(request.getParameter("highestEducation"));
            resume.setGraduationUniversity(request.getParameter("graduationUniversity"));
            resume.setGraduationTime(request.getParameter("graduationTime"));
            resume.setCareerIntention(request.getParameter("careerIntention"));
            resume.setMajor(request.getParameter("major"));
            resume.setWorkExp(request.getParameter("workExp"));
            resume.setIsPublic(request.getParameter("isPublic"));
            resume.setResumeState(request.getParameter("resumeState"));

        }

        if(resumeService.updateResume(resume)>0){
            return "<script>alert(\"update success!\");window.history.back();</script>";
        }else{
            return "<script>alert(\"update fail!\");window.history.back();</script>";
        }
    }

    /**
     * 个人简历管理入口
     * @param session
     * @return
     */
    @RequestMapping("/personResumeManage")
    public String personResumeManage(HttpSession session){
        return "/person-resumemanage";
    }

    /**
     * 获取个人简历
     * @param pid
     * @param session
     * @return
     */
    @RequestMapping("/personResumes")
    @ResponseBody
    public String personResumes( String pid, HttpSession session){
        //System.out.println("---------"+pid);
        pid = ((Person)session.getAttribute("person")).getPid();
        //pid = (String)session.getAttribute("pid");
        System.out.println("---------"+pid);
        Resume resume = new Resume();
        resume.setPid(pid);
        Map<String, Object> map = new HashMap();
        List<Resume> resumeList =  resumeService.queryAll(resume);
        map.put("total", resumeList.size());
        map.put("rows", resumeList);
        String jsonString = JSON.toJSONString(map);
        return jsonString;
    }

    /**
     * 获取简历详细信息
     * @param request
     * @return
     */
    @RequestMapping("/resumeDetail")
    @ResponseBody
    public Resume resumeDetail(HttpServletRequest request){
        String rid = request.getParameter("rid");
        //System.out.println(rid);
        //pid = ((Person)session.getAttribute("person")).getPid();
        return resumeService.getResume(rid);

    }

    /**
     * 简历搜索入口
     * @return
     */
    @RequestMapping("/search")
    public String search(){
        return "/ResumeSearch";
    }

    /**
     * 简历搜索查询，返回json数据
     * @param careerIntention
     * @param graduationUniversity
     * @param highestEducation
     * @return
     */
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


    @RequestMapping("/setState")
    public int setResumeState(String rid,int ispublic){
        return resumeService.setResumeState(rid,ispublic);
    }

    @RequestMapping("searchRid/{rid}")
    @ResponseBody
    public Resume getresume(@PathVariable("rid")  String rid){
        return resumeService.getResume(rid);
    }

}
