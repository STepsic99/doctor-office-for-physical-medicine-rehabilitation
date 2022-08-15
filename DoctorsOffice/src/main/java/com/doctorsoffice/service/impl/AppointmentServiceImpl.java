package com.doctorsoffice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doctorsoffice.dto.NewAppointmentRequestDTO;
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
	public Appointment create(NewAppointmentRequestDTO dto) {
		MedicalWorker medicalWorker = medicalWorkerService.findById(dto.getMedicalWorkerID());
		System.out.println(medicalWorker.getClass());
		if(medicalWorker instanceof Doctor) {
			AppointmentDoctor newAppointment = new AppointmentDoctor(patientService.findById(dto.getPatientID()),dto.getServices(),medicalWorker,dto.getStartTime(),dto.getEndTime());
			appointmentRepository.save(newAppointment);
			return newAppointment;
		} else if(medicalWorker instanceof Physiotherapist) {
			AppointmentPhysiotherapist newAppointment = new AppointmentPhysiotherapist(patientService.findById(dto.getPatientID()),dto.getServices(),medicalWorker,dto.getStartTime(),dto.getEndTime());
			appointmentRepository.save(newAppointment);
			return newAppointment;
		}
		return null;
	}


	@Override
	public List<Appointment> findAllByPatientId(Long patientId) {
		return appointmentRepository.findAllByPatientId(patientId);
	}

}
