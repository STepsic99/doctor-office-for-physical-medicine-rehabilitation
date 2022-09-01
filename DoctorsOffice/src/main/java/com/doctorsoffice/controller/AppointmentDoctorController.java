package com.doctorsoffice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctorsoffice.dto.AppointmentTermDTO;
import com.doctorsoffice.dto.DoctorAppointmentDTO;
import com.doctorsoffice.dto.NewReportRequestDTO;
import com.doctorsoffice.dto.NewReportResponseDTO;
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
	 public ResponseEntity<List<AppointmentTermDTO>> findAll() {	
		 	List<AppointmentDoctor> appointments = appointmentDoctorService.findAll();
		 	List<AppointmentTermDTO> retVal = new ArrayList<AppointmentTermDTO>();
		 	for(AppointmentDoctor app: appointments) {
		 		retVal.add(new AppointmentTermDTO(app));
		 	}
	        return ResponseEntity.ok(retVal);
	        
	   }
	
	@GetMapping(value = "{appointmentId}")
	 public ResponseEntity<DoctorAppointmentDTO> find(@PathVariable Long appointmentId) {	
		 	AppointmentDoctor appointment = appointmentDoctorService.findById(appointmentId);		 	
	        return ResponseEntity.ok(new DoctorAppointmentDTO(appointment));
	        
	   }
	
	 	@PostMapping(value = "{appointmentId}/report")
	    public ResponseEntity<NewReportResponseDTO> create(@RequestBody NewReportRequestDTO dto,@PathVariable Long appointmentId) {
	        return ResponseEntity.status(HttpStatus.CREATED).body(new NewReportResponseDTO(appointmentDoctorService.addReport(dto,appointmentId)));
		}
	 	
	 	 @DeleteMapping(value = "{appointmentId}")
		 public ResponseEntity<HttpStatus> delete(@PathVariable Long appointmentId) {	
	 		 	appointmentDoctorService.delete(appointmentId);		 	
		        return ResponseEntity.noContent().build();     
		   }

}
