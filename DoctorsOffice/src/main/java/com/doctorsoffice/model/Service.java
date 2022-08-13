package com.doctorsoffice.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Service {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String name;
	private double price;
	@Enumerated(EnumType.STRING)
	private StaffType staffType;
	
	
	
	public Service() {
		super();
	}

	public Service(String name, double price, StaffType staffType) {
		super();
		this.name = name;
		this.price = price;
		this.staffType = staffType;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public StaffType getStaffType() {
		return staffType;
	}
	
	
	
	
}
