package com.doctorsoffice.service;

import java.util.List;

import com.doctorsoffice.model.Service;
import com.doctorsoffice.model.StaffType;

public interface ServiceService {
	List<Service> findAllByStaffType (StaffType staffType);
	List<Service> findAll ();
}
