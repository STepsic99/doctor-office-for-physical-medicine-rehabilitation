package com.doctorsoffice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.doctorsoffice.dto.NewPatientRequestDTO;
import com.doctorsoffice.dto.UpdatePatientDTO;
import com.doctorsoffice.model.Patient;
import com.doctorsoffice.repository.PatientRepository;
import com.doctorsoffice.service.PatientService;
import com.doctorsoffice.service.RoleService;

@Service
public class PatientServiceImpl implements PatientService{
	
private final PatientRepository patientRepository;

private final RoleService roleService;

private final PasswordEncoder passwordEncoder;
	
	public PatientServiceImpl(PatientRepository patientRepository,RoleService roleService,PasswordEncoder passwordEncoder) {
		this.patientRepository = patientRepository;
		this.roleService = roleService;
		this.passwordEncoder = passwordEncoder;
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
		patient.setPassword(passwordEncoder.encode(dto.getFirstName().toLowerCase()+"."+dto.getLastName().toLowerCase()));
		return patientRepository.save(patient);
	}

	@Override
	public Patient update(UpdatePatientDTO dto) {
		Optional<Patient> patient= patientRepository.findById(dto.getId());
		if(patient.isEmpty()) return null;
		Patient foundPatient = patient.get();
		foundPatient.setFirstName(dto.getFirstName());
		foundPatient.setLastName(dto.getLastName());
		foundPatient.setPhoneNumber(dto.getPhoneNumber());
		foundPatient.setPersonalID(dto.getPersonalID());
		foundPatient.setUsername(dto.getUsername());
		return patientRepository.save(foundPatient);
	}

}
