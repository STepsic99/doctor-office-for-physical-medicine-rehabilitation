package com.doctorsoffice.service;

import java.util.List;

import com.doctorsoffice.dto.NewAppointmentRequestDTO;
import com.doctorsoffice.model.Appointment;

public interface AppointmentService {
	Appointment create(NewAppointmentRequestDTO dto); 
	List<Appointment> findAllByPatientId(Long patientId);
}
