package com.doctorsoffice.dto;

import com.doctorsoffice.model.Patient;

public class PatientDTO {
	
    private Long id;
    private String username;  
    private String firstName;
    private String lastName;  
    private String phoneNumber;
    private String personalID;
    
	public PatientDTO() {
		super();
	}

	public PatientDTO(Long id, String username, String firstName, String lastName, String phoneNumber,
			String personalID) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.personalID = personalID;
	}
    
	public PatientDTO(Patient patient) {
		this.id = patient.getId();
		this.username = patient.getUsername();
		this.firstName = patient.getFirstName();
		this.lastName = patient.getLastName();
		this.phoneNumber = patient.getPhoneNumber();
		this.personalID = patient.getPersonalID();
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPersonalID() {
		return personalID;
	}
	
	
    

}
