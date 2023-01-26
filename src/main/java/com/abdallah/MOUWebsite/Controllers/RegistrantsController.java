package com.abdallah.MOUWebsite.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.abdallah.MOUWebsite.models.Registrant;
import com.abdallah.MOUWebsite.repositories.EventRepository;
import com.abdallah.MOUWebsite.repositories.RegistrantRepository;

import jakarta.persistence.EntityNotFoundException;

@Controller
public class RegistrantsController {
    
    @Autowired
	EventRepository eventRepo;

	@Autowired
	RegistrantRepository registrantRepo;

    @RequestMapping(value="events/{eventId}/registration", method=RequestMethod.POST)
    public String register(@PathVariable long eventId, @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String parentName, @RequestParam String email, @RequestParam String parentEmail, 
            @RequestParam int number, @RequestParam int age, @RequestParam String city, @RequestParam boolean carpool, 
            @RequestParam List<String> allergies, @RequestParam List<String> medications, Model model){
        Registrant registrant = new Registrant();
        
        registrant.setFirstName(firstName);
        registrant.setLastName(lastName);
        registrant.setAge(age);
        registrant.setAllergies(allergies);
        registrant.setCarpool(carpool);
        registrant.setCity(city);
        registrant.setEmail(email);
        registrant.setMedications(medications);
        registrant.setNumber(number);
        registrant.setParentEmail(parentEmail);
        registrant.setParentName(parentName);

        registrantRepo.save(registrant);

        //send email confirmation

        return "redirect:/events/" + eventId + "/confirmation/" + registrant.getId();
    }

    @RequestMapping(value="events/{eventId}/confirmation/{registrantId}", method=RequestMethod.POST)
    public String confirmRegistration(@PathVariable long registrantId, Model model){
        Registrant registrant = registrantRepo.findById(registrantId).orElseThrow(() -> new EntityNotFoundException());
        String name = registrant.getFirstName() + " " + registrant.getLastName();
        String email = registrant.getEmail();
        model.addAttribute("name", name);
        model.addAttribute("email", email);

        return "confirmation";
    }
}
