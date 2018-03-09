package com.job52.controller;

import com.job52.model.Application;
import com.job52.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/application")
public class ApplicationController {
    @Autowired
    ApplicationService applicationService;
    @RequestMapping("/list")
    public String queryApplication(HttpSession session, Model model) {
        Application application = new Application();
        application.setPid((String)session.getAttribute("pid"));
        model.addAttribute(applicationService.query(application));
        return "application";
    }
    @RequestMapping("/list/{isRead}")
    @ResponseBody
    public List<Application> queryApplicationByRead(HttpSession session, @PathVariable("isRead") int isRead) {
        if (isRead != 0 || isRead !=1) {
            return null;
        }
        Application application = new Application();
        application.setPid((String)session.getAttribute("pid"));
        application.setIsRead(isRead);
        return  applicationService.query(application);
    }

    @RequestMapping("/list/{isRead}/{isPass}")
    @ResponseBody
    public List<Application> queryApplicationByPass(HttpSession session, @PathVariable("isRead") int isRead, @PathVariable("isPass") int isPass) {
        if (isRead !=1) {
            return null;
        }
        Application application = new Application();
        application.setPid((String)session.getAttribute("pid"));
        application.setIsPass(isPass);
        return  applicationService.query(application);
    }
}
