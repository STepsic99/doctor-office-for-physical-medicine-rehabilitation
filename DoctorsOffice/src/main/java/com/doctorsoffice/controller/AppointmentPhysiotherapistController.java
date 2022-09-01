package com.doctorsoffice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctorsoffice.dto.AppointmentTermDTO;
import com.doctorsoffice.dto.PhysiotherapistAppointmentDTO;
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
	 public ResponseEntity<List<AppointmentTermDTO>> findAll() {	
		 	List<AppointmentPhysiotherapist> appointments = appointmentPhysiotherapistService.findAll();
		 	List<AppointmentTermDTO> retVal = new ArrayList<AppointmentTermDTO>();
		 	for(AppointmentPhysiotherapist app: appointments) {
		 		retVal.add(new AppointmentTermDTO(app));
		 	}
	        return ResponseEntity.ok(retVal);
	        
	   }
	 
	 @GetMapping(value = "{appointmentId}")
	 public ResponseEntity<PhysiotherapistAppointmentDTO> find(@PathVariable Long appointmentId) {	
		 AppointmentPhysiotherapist appointment = appointmentPhysiotherapistService.findById(appointmentId);		 	
	        return ResponseEntity.ok(new PhysiotherapistAppointmentDTO(appointment));
	        
	   }
	 
	 @PutMapping(value = "{appointmentId}")
	 public ResponseEntity<HttpStatus> edit(@PathVariable Long appointmentId, @RequestBody PhysiotherapistAppointmentDTO dto) {	
		 AppointmentPhysiotherapist appointment = appointmentPhysiotherapistService.edit(appointmentId, dto);		 	
	      if(appointment!=null) return ResponseEntity.ok().build();
	      return ResponseEntity.notFound().build();
	   }
	 
	 @DeleteMapping(value = "{appointmentId}")
	 public ResponseEntity<HttpStatus> delete(@PathVariable Long appointmentId) {	
		    appointmentPhysiotherapistService.delete(appointmentId);		 	
	        return ResponseEntity.noContent().build();     
	   }
}
