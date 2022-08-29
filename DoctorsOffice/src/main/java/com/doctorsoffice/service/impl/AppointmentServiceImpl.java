package com.doctorsoffice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.doctorsoffice.dto.NewAppointmentRequestDTO;
import com.doctorsoffice.dto.NewAppointmentsRequestDTO;
import com.doctorsoffice.model.Appointment;
import com.doctorsoffice.model.AppointmentDoctor;
import com.doctorsoffice.model.AppointmentPhysiotherapist;
import com.doctorsoffice.model.Doctor;
import com.doctorsoffice.model.MedicalWorker;
import com.doctorsoffice.model.Physiotherapist;
import com.doctorsoffice.repository.AppointmentRepository;
import com.doctorsoffice.service.AppointmentService;
import com.doctorsoffice.service.MedicalWorkerService;
import com.doctorsoffice.service.PatientService;

@Service
public class AppointmentServiceImpl implements AppointmentService{
	
	private final AppointmentRepository appointmentRepository;
	
	private final MedicalWorkerService medicalWorkerService;
	
	private final PatientService patientService;
	
	public AppointmentServiceImpl(AppointmentRepository appointmentRepository,MedicalWorkerService medicalWorkerService,PatientService patientService) {
		this.appointmentRepository = appointmentRepository;
		this.medicalWorkerService = medicalWorkerService;
		this.patientService = patientService;
	}


	@Override
	public List<Appointment> create(NewAppointmentsRequestDTO dto) {
		MedicalWorker medicalWorker = medicalWorkerService.findById(dto.getMedicalWorkerID());
		List<Appointment> appointments = new ArrayList<Appointment>();
		if(medicalWorker instanceof Doctor) {
			for(NewAppointmentRequestDTO appRequest: dto.getAppointments()) {
				appointments.add(new AppointmentDoctor(patientService.findById(dto.getPatientID()),appRequest.getServices(),medicalWorker,appRequest.getStartTime(),appRequest.getEndTime()));
			}
		} else if(medicalWorker instanceof Physiotherapist) {
			for(NewAppointmentRequestDTO appRequest: dto.getAppointments()) {
				appointments.add(new AppointmentPhysiotherapist(patientService.findById(dto.getPatientID()),appRequest.getServices(),medicalWorker,appRequest.getStartTime(),appRequest.getEndTime()));
			}
		}
		return appointmentRepository.saveAll(appointments);
	}


	@Override
	public List<Appointment> findAllByPatientId(Long patientId) {
		return appointmentRepository.findAllByPatientId(patientId);
	}

}
