package com.doctorsoffice.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Report {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String anamnesis;
	private String status;
	private String medicalFindings;
	private String treatmentPlan;
	private String followUpExamination;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Disease> diagnoses;
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Set<Therapy>therapies;
	
	public Report() {
		super();
	}

	public Report(String anamnesis, String status, String medicalFindings, String treatmentPlan,
			String followUpExamination, Set<Disease> diagnoses, Set<Therapy> therapies) {
		super();
		this.anamnesis = anamnesis;
		this.status = status;
		this.medicalFindings = medicalFindings;
		this.treatmentPlan = treatmentPlan;
		this.followUpExamination = followUpExamination;
		this.diagnoses = diagnoses;
		this.therapies = therapies;
	}

	public int getId() {
		return id;
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
	
	
	
	

}
