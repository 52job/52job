package com.job52.controller;

import com.job52.dao.PersonMapper;
import com.job52.model.Person;
import com.job52.service.PersonService;
import com.job52.util.CaptchaUtil;
import com.job52.util.FormatUtil;
import com.job52.util.MD5Util;
import com.job52.util.SecurityCodeUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    //跳转到index界面
    @RequestMapping("/index")
    public String index() {
        return "/index";
    }


    //跳转到login界面
    @RequestMapping("/goLogin")
    public String login(){
        return "/login";
    }

    //跳转到密码重置页面
    @RequestMapping("resetPwd")
    public String resetPwd(){
        return "/resetPwd";
    }

    //跳转到信息修改页面
    @RequestMapping("/updatePersonInfo")
    public String updatePersonInfo(){
        return "/updateinfo";
    }

    //跳转到我的收藏页面
    @RequestMapping("/mycollection")
    public String myCollection() {
        return  "/mycollection";
    }

    /**
     * 得到图形验证码
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    @ResponseBody
    public void captcha(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        CaptchaUtil.outputCaptcha(request, response);
    }

    /**
     * 向用户发送验证码
     * @param userName 用户手机/邮箱
     * @return 操作结果，向前段ajax返回json串。
     * @throws Exception 异常信息
     */
    @RequestMapping("/sendCode")
    @ResponseBody
    public  Map<String,Object> sendCode(String userName, HttpSession session) throws Exception{
        Map<String, Object> modelMap = new HashMap<String, Object>();
        /**
         * 1.判断用户是邮箱还是手机，如果格式不正确，则认为输入出错
         * 2.如果是邮箱，则向用户的邮箱发送验证码
         * 3.如果是手机，则向用户的手机发送验证码
         */
        int format = FormatUtil.verifyPhoneOrEmailFormat(userName);
        String verifyCode = null;
        if(format==0) {//格式错误
            modelMap.put("msg","账号格式出错");
            return modelMap;
        }else{
            if(format == 1){//手机
                //得到手机验证码
                verifyCode= SecurityCodeUtil.getPhoneCode(userName);
               //将验证码放入session域中
               session.setAttribute("verifyCode",verifyCode);
            }else if(format == 2){//邮箱
                //得到邮箱验证码
                 verifyCode = SecurityCodeUtil.getEmailCode(userName);
                //将验证码放入session域中
                session.setAttribute("verifyCode",verifyCode);
            }
        }
        modelMap.put("msg","失败");
        return modelMap;
    }

    /**
     * 接收用户的注册信息
     * @param person  注册信息
     * @param request  请求
     * @param session  会话
     * @return 如果成功注册，跳转到登录界面。如果失败，回到注册页面。
     */
    @RequestMapping("/register")
    public String registerPerson(Person person, HttpServletRequest request, HttpSession session) throws Exception {
        String verifyCode = (String) session.getAttribute("verifyCode");
        String verifyNum = request.getParameter("verifyNum");
        String pwd = request.getParameter("passWord");
        String pwd2 = request.getParameter("passWord2");
        //Person p1 = personService.queryPersonByNameCondition(person.getEmail());
        if(!pwd.equals(pwd2)){
            request.setAttribute("verifyMsg","两次密码不一致");
            return "/index";
        }else {
            if (!verifyNum.equals(verifyCode)) {
                request.setAttribute("verifyMsg", "验证码错误");
                throw new Exception("验证码错误");
            } else {
                if (personService.registerPerson(person)) {
                    request.getSession().removeAttribute("verifyNum");
                    return "redirect:/person/index";
                } else {
                    throw new Exception("注册失败");
                }
            }
        }
    }


    /**
     * 个人用户登录
     * @param passWordLogin 用户密码
     * @param userNameLogin 登录名
     * @param request 请求域
     * @param response 响应域
     * @param randomNum 图形验证码
     * @return 跳转地址
     * 无需判断用户账号是什么类型的，直接获取到用户的信息到数据库中进行查找即可
     * 另外该方法还需要处理自动登录的操作
     */
    @RequestMapping("/loginPerson")
    @ResponseBody
    public String  loginPerson(String userNameLogin,String passWordLogin,String randomNum,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Person person = new Person();
        person.setEmail(userNameLogin);  //用户使用邮箱登录
        person.setUserName(userNameLogin);//用户使用用户名登录
        person.setPhone(userNameLogin);//用户使用手机登录
        person.setPassWord(passWordLogin);
        String randomString = (String) request.getSession().getAttribute("randomString");
        if(!randomString.toLowerCase().equals(randomNum.toLowerCase())){
            throw new Exception("验证码错误");
        }else{
            Person p = personService.personLogin(person);
            if(p==null){
                //登录失败，跳转到登录页面
                return "fail";
            }else {
                //登录成功，1.要把用户的主键放到session域中 2.跳转到主页面
                request.getSession().setAttribute("person", p);
                //判断是否有自动登录的标志，如果有，则进行自动登录
                String autoLogin = request.getParameter("autoLogin");
                if ("on".equals(autoLogin)) {
                    Cookie cookie = new Cookie("autoLogin", URLEncoder.encode(p.getUserName() + ":" + p.getPassWord(), "utf-8"));
                    cookie.setMaxAge(3600 * 24 * 7);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                } else {//如果不选择自动登录，则要清除原来的cookie信息
                    Cookie cookie = new Cookie("autoLogin", "");
                    cookie.setMaxAge(0);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                }
            }
            return "success";
        }
    }

    /**
     * 重置用户密码
     * @param userName 登录名
     * @param passWord  密码
     * @param passWord2  重输密码
     * @param verifyNum  验证码
     * @param session  会话
     * @return
     * @throws Exception
     */
    @RequestMapping("/setPwd")
    public String setPwd(String userName,String passWord,String passWord2,String verifyNum,HttpSession session) throws Exception{
        /**
         * 1.在数据库中查找是否有该用户，如果有，则下一步
         * 2.校验密码格式
         * 3.校验两次密码是否一致
         * 4.校验验证码
         */
        //1.在数据库中查找是否有该用户，如果有，则下一步,这一要通过ajax执行
        Person p =personService.queryPersonByNameCondition(userName);
        String verifyCode = (String) session.getAttribute("verifyCode");
        //2.校验密码格式
        //3.校验两次密码是否一致
        if(passWord.equals(passWord2)){//两次密码一致
            //操作后返回登录界面
           //判断用户名的格式
            if(verifyCode.equals(verifyNum)) {
                p.setPassWord(passWord);
                personService.updatePersonInfo(p.getPid(),p);
                return "/index";
            }else{
                return "/resetPwd";
            }
        }else{
            return "/resetPwd";
        }
    }



    /**
     * 更新用户基本信息
     * @param userName 用户名
     * @param imgUrl 用户头像
     * @return  跳转地址
     * @throws Exception 异常信息
     */
    @RequestMapping("/setPersonInfo")
    @ResponseBody
   public Map<String ,String> updatePersonInfo(String userName,String imgUrl,HttpSession session) throws  Exception{
        Map<String ,String> map = new HashMap<String, String>();
        Person person = (Person) session.getAttribute("person");
        if(imgUrl != null) {
            person.setImgUrl(imgUrl);
        }
        person.setUserName(userName);
       String pid = person.getPid();
        if(personService.updatePersonInfo(pid,person)){
             //更新成功，给出正确提示，不跳转
            map.put("status","success");
            map.put("msg","信息修改成功");
            return map;
        }else{
            map.put("status","fail");
            map.put("msg","信息修改失败");
            return map;
        }
   }

    /**
     * 更新用户手机
     * @param newPhone 新手机号
     * @param verifyNum  验证码
     * @return
     * @throws Exception 异常信息
     */
   @RequestMapping("/updatePersonPhone")
   @ResponseBody
   public Map<String,String> UpdatePersonPhone(String newPhone,String verifyNum,HttpSession session)throws Exception{
        /*
           1.先判断验证码是否正确
           2.验证码正确更新手机
         */
       Map<String ,String> map = new HashMap<String, String>();
       Person person = (Person) session.getAttribute("person");
       String verifyCode = (String) session.getAttribute("verifyCode");
       if(verifyCode.equals(verifyNum)){
           //更新手机
           String pid = person.getPid();
           personService.UpdatePersonPhone(pid,newPhone);
           map.put("status","success");
           map.put("msg","手机绑定成功");
           return map;
       }else{
           //提示失败
           map.put("status","fail");
           map.put("msg","手机绑定失败");
           return map;
       }
   }


    @RequestMapping("/updatePersonPwd")
    @ResponseBody
    public Map<String ,String> updateUserPwd(String oldPassword,String newPassword,String newPassword2,HttpSession session) throws Exception {
        Map<String ,String> map = new HashMap<String, String>();
       Person p = (Person) session.getAttribute("person");
        if(p.getPassWord().equals(MD5Util.md5(oldPassword))){
           if(newPassword.equals(newPassword2)){
               p.setPassWord(MD5Util.md5(newPassword));
               personService.updatePersonInfo(p.getPid(),p);
               map.put("status","success");
               map.put("msg","密码修改成功");
               return map;
           }else{
               map.put("status","success");
               map.put("msg","两次密码不一致");
               return map;
           }
        }
        map.put("status","success");
        map.put("msg","原密码不正确");
        return map;
    }



    /**
     * 更新用户邮箱
     * @param newEmail 新邮箱
     * @param verifyNum  验证码
     * @return
     * @throws Exception 异常信息
     */
    @RequestMapping("/updatePersonEmail")
    @ResponseBody
    public Map<String,String> UpdatePersonEmail(String newEmail,String verifyNum,HttpSession session)throws Exception{
        /*
           1.先判断验证码是否正确
           2.验证码正确更新邮箱
         */
        Map<String ,String> map = new HashMap<String, String>();
        Person p = (Person) session.getAttribute("person");
        String verifyCode = (String) session.getAttribute("verifyCode");
        if(verifyCode.equals(verifyNum)){
            //更新手机
            String pid = p.getPid();
            personService.UpdatePersonPhone(pid,newEmail);
            map.put("status","success");
            map.put("msg","邮箱绑定成功");
            return map;
        }else{
            //提示失败
            map.put("status","fail");
            map.put("msg","邮箱绑定成失败");
            return map;
        }
    }

}
