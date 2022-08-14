package com.doctorsoffice.service;

import java.util.List;

import com.doctorsoffice.dto.NewPatientRequestDTO;
import com.doctorsoffice.model.Patient;

public interface PatientService {
	
	public List<Patient>findAllByFirstNameAndLastName(String firstName, String lastName);
	public List<Patient>findAllByPersonalID(String personalID);
	public Patient findById(Long id);
	public Patient create(NewPatientRequestDTO dto);
}
