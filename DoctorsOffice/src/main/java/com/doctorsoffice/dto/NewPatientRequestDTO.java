package com.doctorsoffice.dto;

public class NewPatientRequestDTO {
	
	 	private String firstName;
	    private String lastName;  
	    private String phoneNumber;
	    private String personalID; 
	    
		public NewPatientRequestDTO() {
			super();
		}

		public NewPatientRequestDTO(String firstName, String lastName, String phoneNumber, String personalID) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.phoneNumber = phoneNumber;
			this.personalID = personalID;
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
