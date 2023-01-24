package com.abdallah.MOUWebsite;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("user", "Abdallah");
        return "home";
    }

    @RequestMapping("/headerfooter")
    public String headerfooter(){
        return "headerfooter";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
