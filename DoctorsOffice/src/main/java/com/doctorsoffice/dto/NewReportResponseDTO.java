package com.doctorsoffice.dto;

import java.util.Set;

import com.doctorsoffice.model.AppointmentDoctor;
import com.doctorsoffice.model.Disease;
import com.doctorsoffice.model.Therapy;

public class NewReportResponseDTO {
	
	private int id;
	private Long appointmentId;
	private String anamnesis;
	private String status;
	private String medicalFindings;
	private String treatmentPlan;
	private String followUpExamination;
	private Set<Disease> diagnoses;
	private Set<Therapy>therapies;
	
	
	
	public NewReportResponseDTO() {
		super();
	}
	
	public NewReportResponseDTO(AppointmentDoctor app) {
		this.id = app.getReport().getId();
		this.appointmentId = app.getId();
		this.anamnesis = app.getReport().getAnamnesis();
		this.status = app.getReport().getStatus();
		this.medicalFindings = app.getReport().getMedicalFindings();
		this.treatmentPlan = app.getReport().getTreatmentPlan();
		this.followUpExamination = app.getReport().getFollowUpExamination();
		this.diagnoses = app.getReport().getDiagnoses();
		this.therapies = app.getReport().getTherapies();
	}
	
	public Long getAppointmentId() {
		return appointmentId;
	}
	public String getAnamnesis() {
		return anamnesis;
	}
	public String getStatus() {
		return status;
	}
	public String getMedicalFindings() {
		return medicalFindings;
	}
	public String getTreatmentPlan() {
		return treatmentPlan;
	}
	public String getFollowUpExamination() {
		return followUpExamination;
	}
	public Set<Disease> getDiagnoses() {
		return diagnoses;
	}
	public Set<Therapy> getTherapies() {
		return therapies;
	}
	public int getId() {
		return id;
	}
	
	

}
