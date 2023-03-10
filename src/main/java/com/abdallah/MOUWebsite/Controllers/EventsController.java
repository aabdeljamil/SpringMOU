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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

    @RequestMapping(value="/events/newevent", method={RequestMethod.GET, RequestMethod.POST})
    public String postEvent(Model model, @AuthenticationPrincipal User user, RedirectAttributes redirAttrs, @RequestParam(required = false) String name, @RequestParam(required = false) String desc, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        //check if inputted name already exists in database (must be unique)
        Event duplicate = eventService.getEventByName(name);

        if (duplicate != null){
            redirAttrs.addFlashAttribute("flashmessage", "Event already exists, try another name.");
            return "redirect:/events/newevent";
        }
        
        //user submitted form
        if (name != null && desc != null && date != null){
            Event event = new Event();
            event.setName(name);
            event.setDate(date);
            event.setDescription(desc);
            eventService.saveOrUpdate(event);

            return "redirect:/events";
        }

        boolean loggedIn = false;

        if (user != null){
            loggedIn = true;
        }

        model.addAttribute("loggedin", loggedIn);

        return "newevent";
    }

    @RequestMapping(value="/events/editevent/{id}", method={RequestMethod.GET, RequestMethod.POST})
    public String postEditedEvent(Model model, @AuthenticationPrincipal User user, @PathVariable long id, @RequestParam(required = false) String name, @RequestParam(required = false) String desc, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        Event event = eventService.getEventById(id);

        //user submitted form
        if (name != null && desc != null && date != null){
            event.setName(name);
            event.setDate(date);
            event.setDescription(desc);
            eventService.saveOrUpdate(event);

            return "redirect:/events";
        }

        boolean loggedIn = false;

        if (user != null){
            loggedIn = true;
        }

        model.addAttribute("loggedin", loggedIn);
        model.addAttribute("eventdescription", event.getDescription());            
        model.addAttribute("eventname", event.getName());
        model.addAttribute("eventdate", event.getDate());

        return "newevent";
    }

    @RequestMapping("/events/deleteevent/{id}")
    public String deleteEvent(@PathVariable long id){
        eventService.delete(id);
        
        return "redirect:/events";
    }
}
