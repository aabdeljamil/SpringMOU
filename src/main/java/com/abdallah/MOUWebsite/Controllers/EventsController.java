package com.abdallah.MOUWebsite.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abdallah.MOUWebsite.models.Event;
import com.abdallah.MOUWebsite.repositories.EventRepository;

import jakarta.persistence.EntityNotFoundException;

@Controller
public class EventsController {
    
    @Autowired
	EventRepository eventRepo;

    @RequestMapping(value="/events/{eventId}", method=RequestMethod.GET)
    public String showEvent(@PathVariable long eventId, Model model){
        model.addAttribute("event", eventRepo.findById(eventId));
        return "event";
    }

    //same as showEvent() but also passes the registrants list to display for admins
    @RequestMapping(value="/events/{eventId}", method=RequestMethod.GET)
    public String showEventAdmin(@PathVariable long eventId, Model model){
        Event event = eventRepo.findById(eventId).orElseThrow(() -> new EntityNotFoundException());

        model.addAttribute("event", eventRepo.findById(eventId));
        model.addAttribute("registrants", event.getRegistrants());
        return "eventAdmin";
    }
}
