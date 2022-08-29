package com.doctorsoffice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctorsoffice.dto.AppointmentDTO;
import com.doctorsoffice.model.AppointmentPhysiotherapist;
import com.doctorsoffice.service.AppointmentPhysiotherapistService;

@RestController
@RequestMapping(value = "/api/v1/physiotherapist-appointments")
public class AppointmentPhysiotherapistController {
	
private final AppointmentPhysiotherapistService appointmentPhysiotherapistService;
	
	@Autowired
	public AppointmentPhysiotherapistController(AppointmentPhysiotherapistService appointmentPhysiotherapistService) {
		this.appointmentPhysiotherapistService = appointmentPhysiotherapistService;
	}
	
	 @GetMapping
	 public ResponseEntity<List<AppointmentDTO>> findAll() {	
		 	List<AppointmentPhysiotherapist> appointments = appointmentPhysiotherapistService.findAll();
		 	List<AppointmentDTO> retVal = new ArrayList<AppointmentDTO>();
		 	for(AppointmentPhysiotherapist app: appointments) {
		 		retVal.add(new AppointmentDTO(app));
		 	}
	        return ResponseEntity.ok(retVal);
	        
	   }

}
