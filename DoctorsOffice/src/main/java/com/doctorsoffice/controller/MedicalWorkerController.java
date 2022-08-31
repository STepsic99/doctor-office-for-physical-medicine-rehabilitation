package com.doctorsoffice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctorsoffice.dto.MedicalWorkerDTO;
import com.doctorsoffice.model.MedicalWorker;
import com.doctorsoffice.service.MedicalWorkerService;

@RestController
@RequestMapping(value = "/api/v1/medical-workers")
public class MedicalWorkerController {
	
private final MedicalWorkerService medicalWorkerService;
	
	@Autowired
	public MedicalWorkerController(MedicalWorkerService medicalWorkerService) {
		this.medicalWorkerService = medicalWorkerService;
	}
	
	@GetMapping
	 public ResponseEntity<List<MedicalWorkerDTO>> findAll() {	
		 	List<MedicalWorker> medicalWorkers = medicalWorkerService.findAll();
		 	List<MedicalWorkerDTO> responseDTOS = new ArrayList<>();
	        for (MedicalWorker medicalWorker : medicalWorkers) {
	        	MedicalWorkerDTO medicalWorkerDTO = new MedicalWorkerDTO(medicalWorker);
	            responseDTOS.add(medicalWorkerDTO);
	        }
	        return ResponseEntity.ok(responseDTOS);
	        
	   }

}
