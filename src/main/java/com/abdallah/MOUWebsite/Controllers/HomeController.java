package com.abdallah.MOUWebsite.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abdallah.MOUWebsite.repositories.EventRepository;

@Controller
public class HomeController {

    @Autowired
	EventRepository eventRepo;

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("user", "Abdallah");
        return "home";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value="/events", method=RequestMethod.GET)
    public String showEvents(Model model){
        model.addAttribute("events", eventRepo.findAll());
        return "events";
    }

    @RequestMapping("/aboutus")
    public String aboutUs(){
        return "aboutus";
    }
}
