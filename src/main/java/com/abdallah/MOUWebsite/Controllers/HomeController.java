package com.abdallah.MOUWebsite.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abdallah.MOUWebsite.data.EventService;

@Controller
public class HomeController {

    @Autowired
	EventService eventService;

    @RequestMapping("/")
    public String home(Model model, @AuthenticationPrincipal User user){
        boolean loggedIn = false;
        String username;
        if (user != null) {
            loggedIn = true;
            username = user.getUsername();
        }else{
            username = "brother";
        }

        model.addAttribute("loggedin", loggedIn);
        model.addAttribute("user", username);
        return "home";
    }

    @RequestMapping("/login")
    public String login(Model model, @AuthenticationPrincipal User user){
        boolean loggedIn = false;
        if (user != null) {
            loggedIn = true;
        }
        
        model.addAttribute("loggedin", loggedIn);
        return "login";
    }

    @RequestMapping(value="/events", method=RequestMethod.GET)
    public String showEvents(Model model, @AuthenticationPrincipal User user){
        boolean loggedIn = false;
        if (user != null) {
            loggedIn = true;
        }
        
        model.addAttribute("loggedin", loggedIn);
        model.addAttribute("events", eventService.getAllEvents());
        return "events";
    }

    @RequestMapping("/aboutus")
    public String aboutUs(Model model, @AuthenticationPrincipal User user){
        boolean loggedIn = false;
        if (user != null) {
            loggedIn = true;
        }
        
        model.addAttribute("loggedin", loggedIn);
        return "aboutus";
    }
}
