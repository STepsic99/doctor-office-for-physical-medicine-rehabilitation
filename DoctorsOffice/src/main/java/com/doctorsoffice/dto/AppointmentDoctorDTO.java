package com.doctorsoffice.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.doctorsoffice.model.AppointmentDoctor;
import com.doctorsoffice.model.Service;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AppointmentDoctorDTO {
	
	private Long appID;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime start;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime end;
	private String services;
    
    
	public AppointmentDoctorDTO(AppointmentDoctor app) {
		this.appID = app.getId();
		this.start = app.getStartTime();
		this.end = app.getEndTime();
		this.services = formatServicesNames(app.getServices());		
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

	private String formatServicesNames(Set<Service>services) {
		StringBuilder str = new StringBuilder();
		for(Service service:services) {
			str.append(service.getName()+ ", ");
		}
		str.delete(str.length()-2,str.length());
		return str.toString();
	}
	
    
    
}
