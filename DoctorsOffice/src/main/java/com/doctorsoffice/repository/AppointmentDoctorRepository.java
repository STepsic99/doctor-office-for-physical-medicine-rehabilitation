package com.doctorsoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctorsoffice.model.AppointmentDoctor;

public interface AppointmentDoctorRepository  extends JpaRepository<AppointmentDoctor, Long>{

}
