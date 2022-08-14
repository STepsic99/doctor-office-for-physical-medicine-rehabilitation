package com.doctorsoffice.dto;

import com.doctorsoffice.model.Patient;

public class NewPatientResponseDTO {
	
	private Long id;
	private String firstName;
    private String lastName;  
    private String phoneNumber;
    private String personalID;
    
    
	public NewPatientResponseDTO() {
		super();
	}
	
	public NewPatientResponseDTO(Patient patient) {
		this.id = patient.getId();
		this.firstName = patient.getFirstName();
		this.lastName = patient.getLastName();
		this.phoneNumber = patient.getPhoneNumber();
		this.personalID = patient.getPersonalID();
	}
	
	public Long getId() {
		return id;
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
