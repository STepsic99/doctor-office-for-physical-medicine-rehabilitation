package com.doctorsoffice.model;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class Patient extends User {

	public Patient() {
		super();
	}

	public Patient(String firstName, String lastName, String phoneNumber, String personalID, List<Role> roles) {
		super(firstName, lastName, phoneNumber, personalID, roles);
	}

	

	

	
	
}
