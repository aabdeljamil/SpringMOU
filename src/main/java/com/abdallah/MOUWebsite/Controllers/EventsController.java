package com.abdallah.MOUWebsite.Controllers;

import java.time.LocalDate;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import com.abdallah.MOUWebsite.data.EventService;
import com.abdallah.MOUWebsite.models.Event;

@Controller
public class EventsController {
    
    @Autowired
	EventService eventService;

    @RequestMapping(value="/events/{eventId}", method=RequestMethod.GET)
    public String showEvent(@PathVariable long eventId, Model model, @AuthenticationPrincipal User user){
        boolean loggedIn = false;

        if (user != null){
            //admin view
            loggedIn = true;
            Event event = eventService.getEventById(eventId);

            model.addAttribute("event", eventService.getEventById(eventId));
            model.addAttribute("registrants", event.getRegistrants());
        }else{
            //normal view
            model.addAttribute("event", eventService.getEventById(eventId));
        }
        model.addAttribute("loggedin", loggedIn);
        return "event";
    }

    @RequestMapping(value="/events/newevent")
    public String newEvent(Model model, @AuthenticationPrincipal User user){
        boolean loggedIn = false;

        if (user != null){
            loggedIn = true;
        }

        model.addAttribute("loggedin", loggedIn);
        return "newevent";
    }

    @RequestMapping(value="/events/newevent", method=RequestMethod.POST)
    public String postEvent(@RequestParam String name, @RequestParam String desc, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        Event event = new Event();
        event.setName(name);
        event.setDate(date);
        event.setDescription(desc);
        eventService.saveOrUpdate(event);

        return "redirect:/events";
    }
}
