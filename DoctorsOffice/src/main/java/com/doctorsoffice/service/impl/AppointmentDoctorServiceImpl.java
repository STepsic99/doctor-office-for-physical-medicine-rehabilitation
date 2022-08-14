package com.doctorsoffice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doctorsoffice.model.AppointmentDoctor;
import com.doctorsoffice.repository.AppointmentDoctorRepository;
import com.doctorsoffice.service.AppointmentDoctorService;

@Service
public class AppointmentDoctorServiceImpl implements AppointmentDoctorService{

private final AppointmentDoctorRepository appointmentDoctorRepository;
	
	public AppointmentDoctorServiceImpl(AppointmentDoctorRepository appointmentDoctorRepository) {
		this.appointmentDoctorRepository = appointmentDoctorRepository;
	}
	
	@Override
	public List<AppointmentDoctor> findAll() {
		return appointmentDoctorRepository.findAll();
	}

}
