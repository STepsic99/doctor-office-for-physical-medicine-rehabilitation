package com.doctorsoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctorsoffice.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
	
	List<Appointment> findAllByPatientId(Long patientId);

}
