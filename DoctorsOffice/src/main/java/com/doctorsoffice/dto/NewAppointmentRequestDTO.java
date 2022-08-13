package com.doctorsoffice.dto;

import java.time.LocalDateTime;
import java.util.HashSet;

import com.doctorsoffice.model.Service;

public class NewAppointmentRequestDTO {
	
	private Long patientID;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
    private HashSet<Service> services;
    private Long medicalWorkerID;
    
	public NewAppointmentRequestDTO() {
		super();
	}

	public NewAppointmentRequestDTO(Long patientID, LocalDateTime startTime, LocalDateTime endTime, HashSet<Service> services,
			Long medicalWorkerID) {
		super();
		this.patientID = patientID;
		this.startTime = startTime;
		this.endTime = endTime;
		this.services = services;
		this.medicalWorkerID = medicalWorkerID;
	}

	public Long getPatientID() {
		return patientID;
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

	public Long getMedicalWorkerID() {
		return medicalWorkerID;
	}
    
    
    
    
}
