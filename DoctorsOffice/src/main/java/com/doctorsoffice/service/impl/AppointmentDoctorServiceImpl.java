package com.doctorsoffice.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.doctorsoffice.dto.NewExaminationRequestDTO;
import com.doctorsoffice.dto.NewReportRequestDTO;
import com.doctorsoffice.dto.NewTherapyDTO;
import com.doctorsoffice.model.AppointmentDoctor;
import com.doctorsoffice.model.MedicalWorker;
import com.doctorsoffice.model.Report;
import com.doctorsoffice.model.Therapy;
import com.doctorsoffice.repository.AppointmentDoctorRepository;
import com.doctorsoffice.service.AppointmentDoctorService;
import com.doctorsoffice.service.MedicalWorkerService;
import com.doctorsoffice.service.PatientService;
import com.doctorsoffice.service.ServiceService;

@Service
public class AppointmentDoctorServiceImpl implements AppointmentDoctorService{

	private static final String EXAMINATION_NAME = "PREGLED";
	
	private final AppointmentDoctorRepository appointmentDoctorRepository;
	
	private final ServiceService serviceService;
	
	private final PatientService patientService;
	
	private final MedicalWorkerService medicalWorkerService;
	
	public AppointmentDoctorServiceImpl(AppointmentDoctorRepository appointmentDoctorRepository,ServiceService serviceService,PatientService patientService,MedicalWorkerService medicalWorkerService) {
		this.appointmentDoctorRepository = appointmentDoctorRepository;
		this.serviceService = serviceService;
		this.patientService = patientService;
		this.medicalWorkerService = medicalWorkerService;
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

	@Override
	public AppointmentDoctor findLastExaminationByPatientId(Long id) {
		List<AppointmentDoctor> appointments = appointmentDoctorRepository.findAllByPatientId(id);
		AppointmentDoctor lastAppointment = null;
		for(int i = 0;i<appointments.size();i++) {
			if(appointments.get(i).getServices().contains(serviceService.findByName(EXAMINATION_NAME))) {
				
				if(lastAppointment == null) lastAppointment = appointments.get(i);
				else {
					if(lastAppointment.getEndTime().isBefore(appointments.get(i).getEndTime())) {
						lastAppointment = appointments.get(i);
					}
				}
							
			}		
		}
		return lastAppointment;
	}

	@Override
	public void delete(Long id) {
		appointmentDoctorRepository.deleteById(id);
	}

	@Override
	public AppointmentDoctor addExaminationByPatient(NewExaminationRequestDTO dto, Long patientId) {
		MedicalWorker medicalWorker = medicalWorkerService.findById(dto.getMedicalWorkerID());
		HashSet<com.doctorsoffice.model.Service> services = new HashSet<com.doctorsoffice.model.Service>();
		services.add(serviceService.findByName(EXAMINATION_NAME));
		AppointmentDoctor appointment = new AppointmentDoctor(patientService.findById(patientId),services,medicalWorker,dto.getStartTime(),dto.getEndTime());	
		return appointmentDoctorRepository.save(appointment);
	}

}
