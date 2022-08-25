package com.doctorsoffice.dto;


public class UpdatePatientDTO {
	
	private Long id;
	private String firstName;
    private String lastName;  
    private String phoneNumber;
    private String personalID;
    private String username;
    
    
	public UpdatePatientDTO() {
		super();
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

	public String getUsername() {
		return username;
	} 
	
	
    
    
}
