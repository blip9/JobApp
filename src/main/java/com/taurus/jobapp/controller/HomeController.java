package com.taurus.jobapp.controller;

import com.taurus.jobapp.model.Jobpost;
import com.taurus.jobapp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    JobService service;
    @RequestMapping({"/","home","/error"})
    public String home(){
        return "home.jsp";
    }

    @RequestMapping("viewalljobs")
    public String alljobs(Model model){
        List<Jobpost>alljobs = service.viewAllJobs();
        model.addAttribute("jobPosts",alljobs);
        return "viewalljobs.jsp";
    }
    @RequestMapping("addjob")
    public String addjob(){
        return "addjob.jsp";
    }

    @RequestMapping("handleForm")
    public String handleForm(@ModelAttribute Jobpost jobpost, Model model) throws SQLException {

        model.addAttribute("jobPost", jobpost);

        service.addJob(jobpost);
        return "success.jsp";
    }


}
