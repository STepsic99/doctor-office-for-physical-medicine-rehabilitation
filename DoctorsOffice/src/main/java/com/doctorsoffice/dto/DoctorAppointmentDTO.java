package com.doctorsoffice.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.doctorsoffice.model.AppointmentDoctor;
import com.doctorsoffice.model.Report;
import com.doctorsoffice.model.Service;
import com.doctorsoffice.model.StaffType;
import com.fasterxml.jackson.annotation.JsonFormat;

public class DoctorAppointmentDTO extends AppointmentDTO{
	
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
	private Long doctorID;
	private String doctorFirstName;
	private String doctorLastName;
	private Report report;
	
	public DoctorAppointmentDTO() {
		super(StaffType.DOCTOR);
	}
	
	public DoctorAppointmentDTO(AppointmentDoctor appoitnment) {
		super(StaffType.DOCTOR);
		this.appID = appoitnment.getId();
		this.start = appoitnment.getStartTime();
		this.end = appoitnment.getEndTime();
		this.services.addAll(appoitnment.getServices());
		this.patientFirstName = appoitnment.getPatient().getFirstName();
		this.patientId = appoitnment.getPatient().getId();
		this.patientLastName = appoitnment.getPatient().getLastName();
		this.patientPersonalID = appoitnment.getPatient().getPersonalID();
		this.doctorID = appoitnment.getMedicalWorker().getId();
		this.doctorFirstName = appoitnment.getMedicalWorker().getFirstName();
		this.doctorLastName = appoitnment.getMedicalWorker().getLastName();
		this.report = appoitnment.getReport();
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

	public Long getDoctorID() {
		return doctorID;
	}

	public String getDoctorFirstName() {
		return doctorFirstName;
	}

	public String getDoctorLastName() {
		return doctorLastName;
	}

	public Report getReport() {
		return report;
	}
	
	
	

}
