package com.doctorsoffice.dto;

import java.util.Set;

import com.doctorsoffice.model.Disease;
import com.doctorsoffice.model.Therapy;

public class NewReportRequestDTO {
	
	
	private String anamnesis;
	private String status;
	private String medicalFindings;
	private String treatmentPlan;
	private String followUpExamination;
	private Set<Disease> diagnoses;
	private Set<NewTherapyDTO>therapies;
	
	
	

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
	public Set<NewTherapyDTO> getTherapies() {
		return therapies;
	}
	
	

}
