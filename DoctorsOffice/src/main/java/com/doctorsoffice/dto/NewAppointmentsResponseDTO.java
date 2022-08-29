package com.doctorsoffice.dto;

import java.util.ArrayList;
import java.util.List;

import com.doctorsoffice.model.Appointment;
import com.doctorsoffice.model.MedicalWorker;
import com.doctorsoffice.model.Patient;

public class NewAppointmentsResponseDTO {
	
	List<NewAppointmentResponseDTO> appointments = new ArrayList<NewAppointmentResponseDTO>();
	private Patient patient;
	private MedicalWorker medicalWorker;

	public NewAppointmentsResponseDTO() {
		super();
	}
	
	public NewAppointmentsResponseDTO(List<NewAppointmentResponseDTO> appointments, Patient patient,
			MedicalWorker medicalWorker) {
		super();
		this.appointments = appointments;
		this.patient = patient;
		this.medicalWorker = medicalWorker;
	}
	
	public NewAppointmentsResponseDTO(List<Appointment> appointments) {
		super();
		if(appointments.size() > 0) {
			this.patient = appointments.get(0).getPatient();
			this.medicalWorker = appointments.get(0).getMedicalWorker();
		}
		for(Appointment app : appointments) {
			NewAppointmentResponseDTO newAppointmentDTO = new NewAppointmentResponseDTO(app);
			this.appointments.add(newAppointmentDTO);
		}
	}

	public List<NewAppointmentResponseDTO> getAppointments() {
		return appointments;
	}

	public Patient getPatient() {
		return patient;
	}

	public MedicalWorker getMedicalWorker() {
		return medicalWorker;
	}
	
	
}
