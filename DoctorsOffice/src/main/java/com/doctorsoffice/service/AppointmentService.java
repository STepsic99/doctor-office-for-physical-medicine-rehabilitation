package com.doctorsoffice.service;

import java.util.List;

import com.doctorsoffice.dto.NewAppointmentsRequestDTO;
import com.doctorsoffice.model.Appointment;

public interface AppointmentService {
	List<Appointment> create(NewAppointmentsRequestDTO dto);
	List<Appointment> findAllByPatientId(Long patientId);
}
