package com.doctorsoffice.dto;

import java.util.List;

public class NewAppointmentsRequestDTO {
	
	List<NewAppointmentRequestDTO> appointments;
	private Long patientID;
	private Long medicalWorkerID;

	public NewAppointmentsRequestDTO() {
		super();
	}

	public NewAppointmentsRequestDTO(List<NewAppointmentRequestDTO> appointments, Long patientID,
			Long medicalWorkerID) {
		super();
		this.appointments = appointments;
		this.patientID = patientID;
		this.medicalWorkerID = medicalWorkerID;
	}

	public List<NewAppointmentRequestDTO> getAppointments() {
		return appointments;
	}

	public Long getPatientID() {
		return patientID;
	}

	public Long getMedicalWorkerID() {
		return medicalWorkerID;
	}
	
	
	
}
