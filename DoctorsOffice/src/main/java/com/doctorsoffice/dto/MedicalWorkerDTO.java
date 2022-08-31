package com.doctorsoffice.dto;

import com.doctorsoffice.model.Doctor;
import com.doctorsoffice.model.MedicalWorker;
import com.doctorsoffice.model.Nurse;
import com.doctorsoffice.model.Physiotherapist;
import com.doctorsoffice.model.StaffType;

public class MedicalWorkerDTO {
	
	 	private Long id;
	    private String username;  
	    private String firstName;
	    private String lastName;  
	    private String phoneNumber;
	    private String personalID;
	    private StaffType type;
	      
	    
	    public MedicalWorkerDTO() {
			super();
		}
	    
	    public MedicalWorkerDTO(MedicalWorker worker) {
	    	this.id = worker.getId();
			this.username = worker.getUsername();
			this.firstName = worker.getFirstName();
			this.lastName = worker.getLastName();
			this.phoneNumber = worker.getPhoneNumber();
			this.personalID = worker.getPersonalID();
			if(worker instanceof Doctor) {
				this.type = StaffType.DOCTOR;
			} else if(worker instanceof Nurse) {
				this.type = StaffType.NURSE;
			} else if(worker instanceof Physiotherapist) {
				this.type = StaffType.PHYSIOTHERAPIST;
			}
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

		public StaffType getType() {
			return type;
		}
		
		

}
