package com.doctorsoffice.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class AppointmentDoctor extends Appointment{
	
	@OneToOne(cascade = CascadeType.ALL)
	private Report report;

	public AppointmentDoctor() {
		super();
	}

	public AppointmentDoctor(Patient patient, Set<Service> services, MedicalWorker medicalWorker,
			LocalDateTime startTime, LocalDateTime endTime) {
		super(patient, services, medicalWorker, startTime, endTime);
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}
	
	
	
	
}
