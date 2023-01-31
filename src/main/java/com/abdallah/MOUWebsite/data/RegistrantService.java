package com.abdallah.MOUWebsite.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdallah.MOUWebsite.models.Registrant;

@Service
public class RegistrantService {

    @Autowired  
    RegistrantRepository registrantRepository;  

    public List<Registrant> getAllRegistrants(){  
        List<Registrant> registrants = new ArrayList<Registrant>();  
        registrantRepository.findAll().forEach(registrant -> registrants.add(registrant));  
        return registrants;  
    }  

    public Registrant getRegistrantById(long id){  
        return registrantRepository.findById(id).get();  
    }  
    public void saveOrUpdate(Registrant registrant){  
        registrantRepository.save(registrant);  
    }  

    public void delete(long id){  
        registrantRepository.deleteById(id);;  
    }  
}
