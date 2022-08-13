package com.doctorsoffice.service;

import java.util.List;

import com.doctorsoffice.model.Patient;

public interface PatientService {
	
	public List<Patient>findAllByFirstNameAndLastName(String firstName, String lastName);
	public List<Patient>findAllByPersonalID(String personalID);
}
