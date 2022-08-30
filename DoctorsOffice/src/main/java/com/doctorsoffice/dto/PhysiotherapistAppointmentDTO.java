package com.doctorsoffice.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.doctorsoffice.model.PatientPresence;
import com.doctorsoffice.model.Service;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.doctorsoffice.model.AppointmentPhysiotherapist;

public class PhysiotherapistAppointmentDTO {
	
	private Long appID;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime start;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime end;
	private List<Service> services=new ArrayList<Service>();
	private Long patientId;
	private String patientFirstName;
	private String patientLastName;
	private String patientPersonalID;
	private Long physiotherapistID;
	private String physiotherapistFirstName;
	private String physiotherapistLastName;
	private PatientPresence patientPresent = PatientPresence.UNSET;
	private String note;
	
	public PhysiotherapistAppointmentDTO() {
		super();
	}
	
	 public PhysiotherapistAppointmentDTO(AppointmentPhysiotherapist appointment) {
		 	this.appID = appointment.getId();
			this.start = appointment.getStartTime();
			this.end = appointment.getEndTime();
			this.services.addAll(appointment.getServices());
			this.patientFirstName = appointment.getPatient().getFirstName();
			this.patientId = appointment.getPatient().getId();
			this.patientLastName = appointment.getPatient().getLastName();
			this.patientPersonalID = appointment.getPatient().getPersonalID();
			this.physiotherapistID = appointment.getMedicalWorker().getId();
			this.physiotherapistFirstName = appointment.getMedicalWorker().getFirstName();
			this.physiotherapistLastName = appointment.getMedicalWorker().getLastName();
			this.patientPresent = appointment.getPatientPresent();
			this.note = appointment.getNote();
	}

	public PhysiotherapistAppointmentDTO(Long appID, LocalDateTime start, LocalDateTime end, List<Service> services,
			Long patientId, String patientFirstName, String patientLastName, String patientPersonalID, Long physiotherapistID,
			String physiotherapistFirstName, String physiotherapistLastName, PatientPresence patientPresent, String note) {
		super();
		this.appID = appID;
		this.start = start;
		this.end = end;
		this.services = services;
		this.patientId = patientId;
		this.patientFirstName = patientFirstName;
		this.patientLastName = patientLastName;
		this.patientPersonalID = patientPersonalID;
		this.physiotherapistID = physiotherapistID;
		this.physiotherapistFirstName = physiotherapistFirstName;
		this.physiotherapistLastName = physiotherapistLastName;
		this.patientPresent = patientPresent;
		this.note = note;
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

	public List<Service> getServices() {
		return services;
	}

	public Long getPatientId() {
		return patientId;
	}

	public String getPatientFirstName() {
		return patientFirstName;
	}

	public String getPatientLastName() {
		return patientLastName;
	}

	public String getPatientPersonalID() {
		return patientPersonalID;
	}

	public Long getphysiotherapistID() {
		return physiotherapistID;
	}

	public String getphysiotherapistFirstName() {
		return physiotherapistFirstName;
	}

	public String getphysiotherapistLastName() {
		return physiotherapistLastName;
	}

	public PatientPresence getPatientPresent() {
		return patientPresent;
	}

	public String getNote() {
		return note;
	}
	
	
	
}
