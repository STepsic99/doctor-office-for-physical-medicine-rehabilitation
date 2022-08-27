package com.doctorsoffice.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.doctorsoffice.dto.NewReportRequestDTO;
import com.doctorsoffice.dto.NewTherapyDTO;
import com.doctorsoffice.model.AppointmentDoctor;
import com.doctorsoffice.model.Report;
import com.doctorsoffice.model.Therapy;
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

	@Override
	public AppointmentDoctor findById(Long id) {
		Optional<AppointmentDoctor> appointment= appointmentDoctorRepository.findById(id);
		if(appointment.isPresent()) return appointment.get();
		return null;
	}

	@Override
	public AppointmentDoctor addReport(NewReportRequestDTO dto, Long appointmentId) {
		Optional<AppointmentDoctor> appointment= appointmentDoctorRepository.findById(appointmentId);		
		if(appointment.isEmpty()) return null;
		AppointmentDoctor appointmentDoctor = appointment.get();
		Set<Therapy> therapies = new HashSet<Therapy>();
		for(NewTherapyDTO therapyDTO: dto.getTherapies()) {
			therapies.add(new Therapy(therapyDTO.getService(),therapyDTO.getNote()));
		}
		Report report = new Report(dto.getAnamnesis(), dto.getStatus(), dto.getMedicalFindings(), dto.getTreatmentPlan(), dto.getFollowUpExamination(), dto.getDiagnoses(), therapies);
		appointmentDoctor.setReport(report);
		return appointmentDoctorRepository.save(appointmentDoctor);
	}

}
