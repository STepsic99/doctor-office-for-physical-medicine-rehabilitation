package com.doctorsoffice.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class AppointmentPhysiotherapist extends Appointment{
	
	private Boolean patientPresent;
	private String note;
	
	public AppointmentPhysiotherapist() {
		super();
	}
	public AppointmentPhysiotherapist(Patient patient, Set<Service> services, MedicalWorker medicalWorker,
			LocalDateTime startTime, LocalDateTime endTime) {
		super(patient, services, medicalWorker, startTime, endTime);
	}

	
}
