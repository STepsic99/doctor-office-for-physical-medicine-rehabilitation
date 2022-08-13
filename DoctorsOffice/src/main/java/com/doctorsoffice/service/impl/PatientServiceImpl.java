package com.doctorsoffice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doctorsoffice.model.Patient;
import com.doctorsoffice.repository.PatientRepository;
import com.doctorsoffice.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService{
	
private final PatientRepository patientRepository;
	
	public PatientServiceImpl(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	@Override
	public List<Patient> findAllByFirstNameAndLastName(String firstName, String lastName) {
		return patientRepository.findAllByFirstNameAndLastName(firstName, lastName);
	}

	@Override
	public List<Patient> findAllByPersonalID(String personalID) {
		return patientRepository.findAllByPersonalID(personalID);
	}

}
