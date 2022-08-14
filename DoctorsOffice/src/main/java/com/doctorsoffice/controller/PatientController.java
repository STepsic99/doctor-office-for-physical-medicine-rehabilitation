package com.doctorsoffice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doctorsoffice.dto.NewPatientRequestDTO;
import com.doctorsoffice.dto.NewPatientResponseDTO;
import com.doctorsoffice.dto.PatientDTO;
import com.doctorsoffice.model.Patient;
import com.doctorsoffice.service.PatientService;

@RestController
@RequestMapping(value = "/api/v1/patients")
public class PatientController {
	
	private final PatientService patientService;
	
	@Autowired
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}
	
	 @GetMapping(params = {"first-name", "last-name"})
	 public ResponseEntity<List<PatientDTO>> findByFirstLastName(@RequestParam("first-name") String firstName,@RequestParam("last-name") String lastName) {	
		 	List<Patient> patients = patientService.findAllByFirstNameAndLastName(firstName, lastName);
	        List<PatientDTO> responseDTOS = new ArrayList<>();
	        for (Patient patient : patients) {
	        	PatientDTO patientDTO = new PatientDTO(patient);
	            responseDTOS.add(patientDTO);
	        }
	        return ResponseEntity.ok(responseDTOS);
	        
	   }
	 
	 @GetMapping(params = "personal-ID" )
	 public ResponseEntity<List<PatientDTO>> findByPersonalID(@RequestParam("personal-ID") String personalID) {	
		 	List<Patient> patients = patientService.findAllByPersonalID(personalID);
	        List<PatientDTO> responseDTOS = new ArrayList<>();
	        for (Patient patient : patients) {
	        	PatientDTO patientDTO = new PatientDTO(patient);
	            responseDTOS.add(patientDTO);
	        }
	        return ResponseEntity.ok(responseDTOS);
	        
	    }
	 
	 @PostMapping
	    public ResponseEntity<NewPatientResponseDTO> create(@RequestBody NewPatientRequestDTO dto) {
	        return ResponseEntity.status(HttpStatus.CREATED).body(new NewPatientResponseDTO(patientService.create(dto)));
		}
}
