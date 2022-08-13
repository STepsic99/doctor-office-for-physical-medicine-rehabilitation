package com.doctorsoffice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doctorsoffice.model.StaffType;
import com.doctorsoffice.repository.ServiceRepository;
import com.doctorsoffice.service.ServiceService;

@Service
public class ServiceServiceImpl implements ServiceService{
	
	private final ServiceRepository serviceRepository;
	
	public ServiceServiceImpl(ServiceRepository serviceRepository) {
		this.serviceRepository = serviceRepository;
	}
	
	
	@Override
	public List<com.doctorsoffice.model.Service> findAllByStaffType(StaffType staffType) {
		return serviceRepository.findAllByStaffType(staffType);
	}

}
