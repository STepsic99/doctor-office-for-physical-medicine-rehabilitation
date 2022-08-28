package com.doctorsoffice.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Therapy {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	@ManyToOne(fetch = FetchType.EAGER)
	private Service service;
	private String note;
	
	public Therapy() {
		super();
	}

	public Therapy(Service service, String note) {
		super();
		this.service = service;
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public Service getService() {
		return service;
	}

	public String getNote() {
		return note;
	}
	
	
	
	

}
