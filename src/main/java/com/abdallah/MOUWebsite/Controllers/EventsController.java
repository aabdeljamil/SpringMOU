package com.abdallah.MOUWebsite.Controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.format.annotation.DateTimeFormat;

import com.abdallah.MOUWebsite.models.Event;
import com.abdallah.MOUWebsite.repositories.EventRepository;

import jakarta.persistence.EntityNotFoundException;

@Controller
public class EventsController {
    
    @Autowired
	EventRepository eventRepo;

    @RequestMapping(value="/events/{eventId}", method=RequestMethod.GET)
    public String showEvent(@PathVariable long eventId, Model model, @AuthenticationPrincipal User user){
        boolean loggedIn = false;

        if (user != null){
            //admin view
            loggedIn = true;
            Event event = eventRepo.findById(eventId).orElseThrow(() -> new EntityNotFoundException());

            model.addAttribute("event", eventRepo.findById(eventId));
            model.addAttribute("registrants", event.getRegistrants());
        }else{
            //normal view
            model.addAttribute("event", eventRepo.findById(eventId));
        }
        model.addAttribute("loggedin", loggedIn);
        return "event";
    }

    @RequestMapping(value="/events/newevent", method=RequestMethod.POST)
    public String newEvent(Model model, @AuthenticationPrincipal User user, @RequestParam String name, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        boolean loggedIn = false;

        if (user != null){
            loggedIn = true;
        }

        if (name != null && date != null){
            Event event = new Event();
            event.setName(name);
            event.setDate(date);
            eventRepo.save(event);

            return "redirect:/events";
        }

        model.addAttribute("loggedin", loggedIn);
        return "newevent";
    }
}
