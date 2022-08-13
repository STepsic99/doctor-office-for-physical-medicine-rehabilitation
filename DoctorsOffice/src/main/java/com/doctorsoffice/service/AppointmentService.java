package com.doctorsoffice.service;

import com.doctorsoffice.dto.NewAppointmentRequestDTO;
import com.doctorsoffice.model.Appointment;

public interface AppointmentService {
	Appointment create(NewAppointmentRequestDTO dto); 
}
