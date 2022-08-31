package com.doctorsoffice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctorsoffice.dto.ServiceDTO;
import com.doctorsoffice.model.Service;
import com.doctorsoffice.model.StaffType;
import com.doctorsoffice.service.ServiceService;

@RestController
@RequestMapping(value = "/api/v1/services")
public class ServiceController {
	
	private final ServiceService serviceService;
	
	@Autowired
	public ServiceController(ServiceService serviceService) {
		this.serviceService = serviceService;
	}
	
	@GetMapping(value = "{role}")
    public ResponseEntity<List<ServiceDTO>> getServicesByStaffType(@PathVariable String role) {
        List<Service> services = serviceService.findAllByStaffType(StaffType.valueOf(role.toUpperCase()));
        List<ServiceDTO> responseDTOS = new ArrayList<>();
        for (Service service : services) {
        	ServiceDTO serviceDTO = new ServiceDTO(service);
            responseDTOS.add(serviceDTO);
        }
        return ResponseEntity.ok(responseDTOS);
    }
	
	@GetMapping
	 public ResponseEntity<List<ServiceDTO>> findAll() {	
		 	List<Service> services = serviceService.findAll();
		 	List<ServiceDTO> responseDTOS = new ArrayList<>();
	        for (Service service : services) {
	        	ServiceDTO serviceDTO = new ServiceDTO(service);
	            responseDTOS.add(serviceDTO);
	        }
	        return ResponseEntity.ok(responseDTOS);
	        
	   }
}
