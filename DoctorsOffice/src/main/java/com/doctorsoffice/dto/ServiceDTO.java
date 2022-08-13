package com.doctorsoffice.dto;

import com.doctorsoffice.model.Service;
import com.doctorsoffice.model.StaffType;

public class ServiceDTO {
	
	 private int id;
	 private String name;
	 private double price;
	 private StaffType staffType;
	  
	 
	 public ServiceDTO() {
		super();
	}


	public ServiceDTO(int id, String name, double price, StaffType staffType) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.staffType = staffType;
	}
	
	public ServiceDTO(Service service) {
		super();
		this.id = service.getId();
		this.name = service.getName();
		this.price = service.getPrice();
		this.staffType = service.getStaffType();
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
