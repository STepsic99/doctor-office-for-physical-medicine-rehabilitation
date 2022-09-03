package com.doctorsoffice.dto;

import java.time.LocalDateTime;

public class NewExaminationRequestDTO {
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private Long medicalWorkerID;
	
	
	
	public NewExaminationRequestDTO() {
		super();
	}

	public NewExaminationRequestDTO(LocalDateTime startTime, LocalDateTime endTime, Long medicalWorkerID) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.medicalWorkerID = medicalWorkerID;
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
