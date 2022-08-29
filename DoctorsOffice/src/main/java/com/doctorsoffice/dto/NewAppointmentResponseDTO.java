package com.doctorsoffice.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.doctorsoffice.model.Appointment;
import com.doctorsoffice.model.Service;

public class NewAppointmentResponseDTO {
	
    private Long id;
	private Set<Service> services = new HashSet<>();
	private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    public NewAppointmentResponseDTO(Appointment appointment) {
    	this.id = appointment.getId();
    	this.services = appointment.getServices();
    	this.startTime = appointment.getStartTime();
    	this.endTime = appointment.getEndTime();
    }
    
	public Long getId() {
		return id;
	}
	
	public Set<Service> getServices() {
		return services;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
    
    
}
