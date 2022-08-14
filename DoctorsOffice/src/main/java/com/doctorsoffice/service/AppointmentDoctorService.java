package com.doctorsoffice.service;

import java.util.List;

import com.doctorsoffice.model.AppointmentDoctor;

public interface AppointmentDoctorService {
	
	List<AppointmentDoctor> findAll();
	
}
