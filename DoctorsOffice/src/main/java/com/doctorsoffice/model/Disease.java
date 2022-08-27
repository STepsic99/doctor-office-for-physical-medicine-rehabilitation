package com.doctorsoffice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Disease {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String name;
	private String code;
	
	
	public Disease() {
		super();
	}
	
	
	
	public Disease(int id, String name, String code) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
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
