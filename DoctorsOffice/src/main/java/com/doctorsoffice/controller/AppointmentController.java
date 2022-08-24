package com.doctorsoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctorsoffice.dto.NewAppointmentRequestDTO;
import com.doctorsoffice.dto.NewAppointmentResponseDTO;
import com.doctorsoffice.service.AppointmentService;

@RestController
@RequestMapping(value = "/api/v1/appointments")
public class AppointmentController {
	
private final AppointmentService appointmentService;
	
	@Autowired
	public AppointmentController(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
	@PostMapping
    public ResponseEntity<NewAppointmentResponseDTO> create(@RequestBody NewAppointmentRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new NewAppointmentResponseDTO(appointmentService.create(dto)));
	}
}