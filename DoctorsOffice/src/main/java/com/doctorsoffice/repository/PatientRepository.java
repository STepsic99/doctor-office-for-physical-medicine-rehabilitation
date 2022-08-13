package com.doctorsoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doctorsoffice.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{
	
	@Query(value = "SELECT * FROM PATIENT WHERE LOWER(FIRST_NAME) LIKE LOWER(CONCAT('%', ?1, '%')) AND LOWER(LAST_NAME) LIKE LOWER(CONCAT('%', ?2, '%'))", nativeQuery = true)
    List<Patient> findAllByFirstNameAndLastName(String firstName, String lastName);
	
	List<Patient> findAllByPersonalID(String personalID);
	
}
