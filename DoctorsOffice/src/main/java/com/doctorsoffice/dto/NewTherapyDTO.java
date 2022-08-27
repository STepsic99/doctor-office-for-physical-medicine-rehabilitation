package com.doctorsoffice.dto;

import com.doctorsoffice.model.Service;

public class NewTherapyDTO {
	
	private Service service;
	private String note;
	
	public NewTherapyDTO() {
		super();
	}

	public NewTherapyDTO(Service service, String note) {
		super();
		this.service = service;
		this.note = note;
	}

	public Service getService() {
		return service;
	}

	public String getNote() {
		return note;
	}
	
	
	
}
