package com.doctorsoffice.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.doctorsoffice.model.Appointment;
import com.doctorsoffice.model.AppointmentDoctor;
import com.doctorsoffice.model.AppointmentPhysiotherapist;
import com.doctorsoffice.model.PatientPresence;
import com.doctorsoffice.model.Report;
import com.doctorsoffice.model.Service;
import com.doctorsoffice.model.StaffType;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AppointmentWithMedicalDocumentationDTO {
	
	private Long appID;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime start;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime end;
	private String services;
	private PatientPresence patientPresent = PatientPresence.UNSET;
	private String note;
	private Report report;
	private StaffType staffType;
	
	public AppointmentWithMedicalDocumentationDTO(AppointmentDoctor app) {
		this.appID = app.getId();
		this.start = app.getStartTime();
		this.end = app.getEndTime();
		this.services = formatServicesNames(app.getServices());
		this.report = app.getReport();	
		this.staffType = StaffType.DOCTOR;
	}
	
	public AppointmentWithMedicalDocumentationDTO(AppointmentPhysiotherapist app) {
		this.appID = app.getId();
		this.start = app.getStartTime();
		this.end = app.getEndTime();
		this.services = formatServicesNames(app.getServices());
		this.patientPresent = app.getPatientPresent();
		this.note = app.getNote();
		this.staffType = StaffType.PHYSIOTHERAPIST;
	}
	
	
	
	public AppointmentWithMedicalDocumentationDTO(Appointment app) {

		
	}

	public Long getAppID() {
		return appID;
	}



	public LocalDateTime getStart() {
		return start;
	}



	public LocalDateTime getEnd() {
		return end;
	}



	public String getServices() {
		return services;
	}



	public PatientPresence getPatientPresent() {
		return patientPresent;
	}



	public String getNote() {
		return note;
	}



	public Report getReport() {
		return report;
	}


	public StaffType getStaffType() {
		return staffType;
	}

	private String formatServicesNames(Set<Service>services) {
		StringBuilder str = new StringBuilder();
		for(Service service:services) {
			str.append(service.getName()+ ", ");
		}
		str.delete(str.length()-2,str.length());
		return str.toString();
	}

}
