package com.doctorsoffice.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class AppointmentPhysiotherapist extends Appointment{
	
	@Enumerated(EnumType.STRING)
	private PatientPresence patientPresent = PatientPresence.UNSET;
	private String note;
	
	public AppointmentPhysiotherapist() {
		super();
	}
	public AppointmentPhysiotherapist(Patient patient, Set<Service> services, MedicalWorker medicalWorker,
			LocalDateTime startTime, LocalDateTime endTime) {
		super(patient, services, medicalWorker, startTime, endTime);
	}
	public PatientPresence getPatientPresent() {
		return patientPresent;
	}
	public String getNote() {
		return note;
	}
	public void setPatientPresent(PatientPresence patientPresent) {
		this.patientPresent = patientPresent;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
	

	
}
