package com.abdallah.MOUWebsite.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Event {
    
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
    private Date date;
    @OneToMany
    private List<Registrant> registrants;

    public Event(){
        super();
    }

    public Event(long id, String name, Date date, List<Registrant> registrants) {
        super();
        this.id = id;
        this.name = name;
        this.date = date;
        this.registrants = registrants;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Registrant> getRegistrants() {
        return registrants;
    }

    public void setRegistrants(List<Registrant> registrants) {
        this.registrants = registrants;
    }
}