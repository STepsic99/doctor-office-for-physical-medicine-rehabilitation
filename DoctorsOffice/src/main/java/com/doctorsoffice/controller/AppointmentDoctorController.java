package com.doctorsoffice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctorsoffice.dto.AppointmentDTO;
import com.doctorsoffice.dto.DoctorAppointmentDTO;
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
	 public ResponseEntity<List<AppointmentDTO>> findAll() {	
		 	List<AppointmentDoctor> appointments = appointmentDoctorService.findAll();
		 	List<AppointmentDTO> retVal = new ArrayList<AppointmentDTO>();
		 	for(AppointmentDoctor app: appointments) {
		 		retVal.add(new AppointmentDTO(app));
		 	}
	        return ResponseEntity.ok(retVal);
	        
	   }
	
	@GetMapping(value = "{appointmentId}")
	 public ResponseEntity<DoctorAppointmentDTO> findAll(@PathVariable Long appointmentId) {	
		 	AppointmentDoctor appointment = appointmentDoctorService.findById(appointmentId);		 	
	        return ResponseEntity.ok(new DoctorAppointmentDTO(appointment));
	        
	   }

}
