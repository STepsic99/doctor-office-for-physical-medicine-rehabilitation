package com.doctorsoffice.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
public abstract class Appointment {
	@Id
    @SequenceGenerator(name = "mySeqGenApps", sequenceName = "mySeqApps", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenApps")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	private Patient patient;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Service> services = new HashSet<>();
	@ManyToOne(fetch = FetchType.EAGER)
	private MedicalWorker medicalWorker;
	private LocalDateTime startTime;
    private LocalDateTime endTime;
    
	public Appointment() {
		super();
	}

	public Appointment(Patient patient, Set<Service> services, MedicalWorker medicalWorker, LocalDateTime startTime,
			LocalDateTime endTime) {
		super();
		this.patient = patient;
		this.services = services;
		this.medicalWorker = medicalWorker;
		this.startTime = startTime;
		this.endTime = endTime;
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
