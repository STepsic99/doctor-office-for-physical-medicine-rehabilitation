package com.doctorsoffice.model;

import javax.persistence.Entity;

@Entity
public class AppointmentNurse extends Appointment{
	
	private Boolean patientPresent;
	private String note;

}
