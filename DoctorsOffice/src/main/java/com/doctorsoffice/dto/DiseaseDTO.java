package com.doctorsoffice.dto;

import com.doctorsoffice.model.Disease;

public class DiseaseDTO {
	
	private int id;
	private String name;
	private String code;
	
	public DiseaseDTO() {
		super();
	}

	public DiseaseDTO(int id, String name, String code) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
	}
	
	public DiseaseDTO(Disease disease) {
		this.id = disease.getId();
		this.name = disease.getName();
		this.code = disease.getCode();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}
	
	
	

}
