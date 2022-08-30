package com.doctorsoffice.dto;

import com.doctorsoffice.model.StaffType;

public abstract class AppointmentDTO {
	private StaffType type;

	public AppointmentDTO() {
		super();
	}

	public AppointmentDTO(StaffType type) {
		super();
		this.type = type;
	}

	public StaffType getType() {
		return type;
	}
	
	
	
	
}
