package com.doctorsoffice.service;

import java.util.List;

import com.doctorsoffice.dto.NewReportRequestDTO;
import com.doctorsoffice.model.AppointmentDoctor;

public interface AppointmentDoctorService {
	
	List<AppointmentDoctor> findAll();
	AppointmentDoctor findById(Long id);
	AppointmentDoctor addReport(NewReportRequestDTO dto, Long appointmentId);
	AppointmentDoctor findLastExaminationByPatientId(Long id);
	void delete(Long id);
}
