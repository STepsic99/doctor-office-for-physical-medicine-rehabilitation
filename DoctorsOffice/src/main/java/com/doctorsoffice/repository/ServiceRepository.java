package com.doctorsoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctorsoffice.model.Service;
import com.doctorsoffice.model.StaffType;

public interface ServiceRepository extends JpaRepository<Service, Long>{
	
	List<Service> findAllByStaffType (StaffType staffType);
}
