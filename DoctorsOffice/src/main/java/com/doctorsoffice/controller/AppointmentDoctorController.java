package com.doctorsoffice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctorsoffice.dto.AppointmentDoctorDTO;
import com.doctorsoffice.model.AppointmentDoctor;
import com.doctorsoffice.service.AppointmentDoctorService;

@RestController
@RequestMapping(value = "/api/v1/doctor-appointments")
public class AppointmentDoctorController {
	
private final AppointmentDoctorService appointmentDoctorService;
	
	@Autowired
	public AppointmentDoctorController(AppointmentDoctorService appointmentDoctorService) {
		this.appointmentDoctorService = appointmentDoctorService;
	}
	
	@GetMapping
	 public ResponseEntity<List<AppointmentDoctorDTO>> findAll() {	
		 	List<AppointmentDoctor> appointments = appointmentDoctorService.findAll();
		 	List<AppointmentDoctorDTO> retVal = new ArrayList<AppointmentDoctorDTO>();
		 	for(AppointmentDoctor app: appointments) {
		 		retVal.add(new AppointmentDoctorDTO(app));
		 	}
	        return ResponseEntity.ok(retVal);
	        
	   }

}
