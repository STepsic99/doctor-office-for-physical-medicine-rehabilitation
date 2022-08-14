package com.doctorsoffice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doctorsoffice.dto.NewPatientRequestDTO;
import com.doctorsoffice.model.Patient;
import com.doctorsoffice.repository.PatientRepository;
import com.doctorsoffice.service.PatientService;
import com.doctorsoffice.service.RoleService;

@Service
public class PatientServiceImpl implements PatientService{
	
private final PatientRepository patientRepository;

private final RoleService roleService;
	
	public PatientServiceImpl(PatientRepository patientRepository,RoleService roleService) {
		this.patientRepository = patientRepository;
		this.roleService = roleService;
	}

	@Override
	public List<Patient> findAllByFirstNameAndLastName(String firstName, String lastName) {
		return patientRepository.findAllByFirstNameAndLastName(firstName, lastName);
	}

	@Override
	public List<Patient> findAllByPersonalID(String personalID) {
		return patientRepository.findAllByPersonalID(personalID);
	}

	@Override
	public Patient findById(Long id) {
		Optional<Patient> patient= patientRepository.findById(id);
		if(patient.isPresent()) return patient.get();
		return null;
	}

	@Override
	public Patient create(NewPatientRequestDTO dto) {
		Patient patient = new Patient(dto.getFirstName(),dto.getLastName(),dto.getPhoneNumber(),dto.getPersonalID(),roleService.findByName("ROLE_PATIENT"));
		return patientRepository.save(patient);
	}

}
