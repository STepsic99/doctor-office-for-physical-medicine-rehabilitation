package com.doctorsoffice.dto;

import java.time.LocalDateTime;
import java.util.HashSet;

import com.doctorsoffice.model.Service;

public class NewAppointmentRequestDTO {
	
	
	private LocalDateTime startTime;
	private LocalDateTime endTime;
    private HashSet<Service> services;
    
    
	public NewAppointmentRequestDTO() {
		super();
	}

	public NewAppointmentRequestDTO(LocalDateTime startTime, LocalDateTime endTime, HashSet<Service> services) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.services = services;
		
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public HashSet<Service> getServices() {
		return services;
	}

    
    
    
    
}
