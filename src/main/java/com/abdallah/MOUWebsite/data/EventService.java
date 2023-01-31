package com.abdallah.MOUWebsite.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdallah.MOUWebsite.models.Event;

@Service
public class EventService {

    @Autowired  
    EventRepository eventRepository;  

    public List<Event> getAllEvents(){  
        List<Event> events = new ArrayList<Event>();  
        eventRepository.findAll().forEach(event -> events.add(event));  
        return events;  
    }  

    public Event getEventById(long id){  
        return eventRepository.findById(id).get();  
    }  
    public void saveOrUpdate(Event event){  
        eventRepository.save(event);  
    }  

    public void delete(long id){  
        eventRepository.deleteById(id);;  
    }  
}
