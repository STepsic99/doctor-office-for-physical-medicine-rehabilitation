package com.doctorsoffice.dto;

import java.time.LocalDateTime;

import com.doctorsoffice.model.AppointmentDoctor;

public class AppointmentDoctorDTO {
	
	private Long appID;
	private LocalDateTime start;
    private LocalDateTime end;
    
    
	public AppointmentDoctorDTO(AppointmentDoctor app) {
		this.appID = app.getId();
		this.start = app.getStartTime();
		this.end = app.getEndTime();
	
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
	
	
    
    
}
