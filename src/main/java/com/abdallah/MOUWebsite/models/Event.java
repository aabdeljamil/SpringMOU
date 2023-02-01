package com.abdallah.MOUWebsite.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Event {
    
    @Id
    @Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

    @Nonnull
    @Column(length = 255)
	private String name;

    @Nonnull
    @Column(length = 255)
    private LocalDate date;

    @Column(length = 10000)
    private String description;
    
    @Column
    @OneToMany
    private List<Registrant> registrants;

    public Event(){
        super();
    }

    public Event(long id, String name, LocalDate date, List<Registrant> registrants) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Registrant> getRegistrants() {
        return registrants;
    }

    public void setRegistrants(List<Registrant> registrants) {
        this.registrants = registrants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
