package com.abdallah.MOUWebsite.models;

import java.util.List;


import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Registrant {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

    @Nonnull
    @Column(length = 255)
	private String firstName;
    
    @Nonnull
    @Column(length = 255)
	private String lastName;
    
    @Column(length = 255)
    private String emergencyName;
    
    @Column(length = 255)
	private String emergencyEmail;
    
    @Nonnull
    @Column(length = 255)
    private String email;

    @Nonnull
    @Column
    private long phoneNumber;
    
    @Nonnull
    @Column
    private int age;
    
    @Column(length = 255)
    private String city;
    
    @Column
    private boolean carpool;
    
    @Column
    private List<String> allergies;
    
    @Column
    private List<String> medications;

    @Column(columnDefinition = "JAVA_OBJECT")
    private Object otherQuestions;
    
    public Registrant() {
        super();
    }

    public Registrant(long id, String firstName, String lastName, String parentName, String email, String parentEmail,
            int number, int age, String city, boolean carpool, List<String> allergies, List<String> medications) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emergencyName = parentName;
        this.email = email;
        this.emergencyEmail = parentEmail;
        this.phoneNumber = number;
        this.age = age;
        this.city = city;
        this.carpool = carpool;
        this.allergies = allergies;
        this.medications = medications;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmergencyName() {
        return emergencyName;
    }

    public void setParentName(String emergencyName) {
        this.emergencyName = emergencyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmergencyEmail() {
        return emergencyEmail;
    }

    public void setParentEmail(String emergencyEmail) {
        this.emergencyEmail = emergencyEmail;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int number) {
        this.phoneNumber = number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isCarpool() {
        return carpool;
    }

    public void setCarpool(boolean carpool) {
        this.carpool = carpool;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public Object getOtherQuestions() {
        return otherQuestions;
    }

    public void setOtherQuestions(Object otherQuestions) {
        this.otherQuestions = otherQuestions;
    }

    
}
