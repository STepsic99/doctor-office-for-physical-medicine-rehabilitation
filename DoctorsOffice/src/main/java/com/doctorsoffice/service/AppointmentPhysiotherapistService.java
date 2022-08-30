package com.doctorsoffice.service;

import java.util.List;

import com.doctorsoffice.dto.PhysiotherapistAppointmentDTO;
import com.doctorsoffice.model.AppointmentPhysiotherapist;

public interface AppointmentPhysiotherapistService {
	
	List<AppointmentPhysiotherapist> findAll();
	AppointmentPhysiotherapist findById(Long id);
	AppointmentPhysiotherapist edit(Long id, PhysiotherapistAppointmentDTO dto);
	
}
