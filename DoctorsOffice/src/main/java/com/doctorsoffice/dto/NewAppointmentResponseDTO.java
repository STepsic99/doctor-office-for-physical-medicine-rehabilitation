package com.doctorsoffice.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.doctorsoffice.model.Appointment;
import com.doctorsoffice.model.MedicalWorker;
import com.doctorsoffice.model.Patient;
import com.doctorsoffice.model.Service;

public class NewAppointmentResponseDTO {
	
    private Long id;
	private Patient patient;
	private Set<Service> services = new HashSet<>();
	private MedicalWorker medicalWorker;
	private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    public NewAppointmentResponseDTO(Appointment appointment) {
    	this.id = appointment.getId();
    	this.patient = appointment.getPatient();
    	this.services = appointment.getServices();
    	this.medicalWorker = appointment.getMedicalWorker();
    	this.startTime = appointment.getStartTime();
    	this.endTime = appointment.getEndTime();
    }
    
	public Long getId() {
		return id;
	}
	public Patient getPatient() {
		return patient;
	}
	public Set<Service> getServices() {
		return services;
	}
	public MedicalWorker getMedicalWorker() {
		return medicalWorker;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
    
    
}
