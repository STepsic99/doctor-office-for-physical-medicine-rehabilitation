package com.doctorsoffice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doctorsoffice.model.AppointmentPhysiotherapist;
import com.doctorsoffice.repository.AppointmentPhysiotherapistRepository;
import com.doctorsoffice.service.AppointmentPhysiotherapistService;

@Service
public class AppointmentPhysiotherapistServiceImpl implements AppointmentPhysiotherapistService{
	
private final AppointmentPhysiotherapistRepository appointmentPhysiotherapistRepository;
	
	public AppointmentPhysiotherapistServiceImpl(AppointmentPhysiotherapistRepository appointmentPhysiotherapistRepository) {
		this.appointmentPhysiotherapistRepository = appointmentPhysiotherapistRepository;
	}
	
	@Override
	public List<AppointmentPhysiotherapist> findAll() {
		return appointmentPhysiotherapistRepository.findAll();
	}

}
