package com.doctorsoffice.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class AppointmentDoctor extends Appointment{
	
	@OneToOne
	private Report report;
}
