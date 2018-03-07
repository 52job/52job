package com.job52.filter;

import com.job52.dao.PersonMapper;
import com.job52.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

public class AutoLoginInterceptor   implements HandlerInterceptor {
    @Autowired
    private PersonMapper personMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //如果用户名和密码正确，则进行自动登录，否则返回到登录界面
        //return false，并且拦截，不向下执行
        Cookie[] cs = request.getCookies();
        Cookie c = null;
        if(cs!=null){
            //自动登录
            for(Cookie ctmp: cs){
                if("autoLogin".equals(ctmp.getName())){
                    c = ctmp;
                    break;
                }
            }
            if(c!=null) {
                //  String value = c.getValue();
                //如果有中文要进行url编码
                String value = URLDecoder.decode(c.getValue(), "utf-8");
                String username = value.split(":")[0];
                String password = value.split(":")[1];
                Person person = new Person();
                person.setUserName(username);
                person.setPassWord(password);
                Person p = null;
                p = personMapper.findPersonByNameAndPwd(person);
                if(p!=null){
                    request.setAttribute("person",p);
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
