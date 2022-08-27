package com.doctorsoffice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doctorsoffice.dto.AppointmentDTO;
import com.doctorsoffice.dto.NewPatientRequestDTO;
import com.doctorsoffice.dto.NewPatientResponseDTO;
import com.doctorsoffice.dto.PatientDTO;
import com.doctorsoffice.dto.UpdatePatientDTO;
import com.doctorsoffice.model.Appointment;
import com.doctorsoffice.model.Patient;
import com.doctorsoffice.service.AppointmentService;
import com.doctorsoffice.service.PatientService;

@RestController
@RequestMapping(value = "/api/v1/patients")
public class PatientController {
	
	private final PatientService patientService;
	
	private final AppointmentService appointmentService;
	
	@Autowired
	public PatientController(PatientService patientService, AppointmentService appointmentService) {
		this.patientService = patientService;
		this.appointmentService = appointmentService;
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
	 
	 @GetMapping(value = "{patientId}/appointments")
	 public ResponseEntity<List<AppointmentDTO>> findByPatientId(@PathVariable Long patientId) {	
		 	List<Appointment> appointments = appointmentService.findAllByPatientId(patientId);
		 	List<AppointmentDTO> retVal = new ArrayList<AppointmentDTO>();
		 	for(Appointment app: appointments) {
		 		retVal.add(new AppointmentDTO(app));
		 	}
	        return ResponseEntity.ok(retVal);
	        
	   }
	 
	 @PutMapping
	 public ResponseEntity<HttpStatus> updatePatient(@RequestBody UpdatePatientDTO dto) {	
		 	Patient patient = patientService.update(dto);
		 	if(patient == null) return ResponseEntity.notFound().build();
		 	return ResponseEntity.ok().build();        
	   }
	 
	 @GetMapping(value = "{patientId}")
	 public ResponseEntity<PatientDTO> getPatientInfo(@PathVariable Long patientId) {	
		 	Patient patient = patientService.findById(patientId);
	        return ResponseEntity.ok(new PatientDTO(patient));
	        
	   }
}
