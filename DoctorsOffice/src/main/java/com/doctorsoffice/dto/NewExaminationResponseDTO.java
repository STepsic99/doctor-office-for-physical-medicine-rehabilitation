package com.doctorsoffice.dto;

import java.time.LocalDateTime;

import com.doctorsoffice.model.AppointmentDoctor;

public class NewExaminationResponseDTO {
	
	private Long id;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private Long medicalWorkerID;
	
	
	public NewExaminationResponseDTO() {
		super();
	}
	
	public NewExaminationResponseDTO(AppointmentDoctor app) {
		super();
		this.id = app.getId();
		this.startTime = app.getStartTime();
		this.endTime =app.getEndTime();
		this.medicalWorkerID =app.getMedicalWorker().getId();
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public Long getMedicalWorkerID() {
		return medicalWorkerID;
	}
	
	

}
