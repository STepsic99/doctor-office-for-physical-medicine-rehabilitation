package com.doctorsoffice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doctorsoffice.dto.PhysiotherapistAppointmentDTO;
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

	@Override
	public AppointmentPhysiotherapist findById(Long id) {
		Optional<AppointmentPhysiotherapist> appointment= appointmentPhysiotherapistRepository.findById(id);
		if(appointment.isPresent()) return appointment.get();
		return null;
	}

	@Override
	public AppointmentPhysiotherapist edit(Long id, PhysiotherapistAppointmentDTO dto) {
		Optional<AppointmentPhysiotherapist> appointment= appointmentPhysiotherapistRepository.findById(id);
		if(appointment.isEmpty()) return null;
		AppointmentPhysiotherapist currentAppointment = appointment.get();
		currentAppointment.setPatientPresent(dto.getPatientPresent());
		currentAppointment.setNote(dto.getNote());
		return appointmentPhysiotherapistRepository.save(currentAppointment);
	}

	@Override
	public void delete(Long id) {
		appointmentPhysiotherapistRepository.deleteById(id);
	}

}
