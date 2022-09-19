package com.doctorsoffice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doctorsoffice.dto.DiseaseDTO;
import com.doctorsoffice.model.Disease;
import com.doctorsoffice.service.DiseaseService;

@RestController
@RequestMapping(value = "/api/v1/diseases")
public class DiseaseController {
	
	private final DiseaseService diseaseService;
	
	@Autowired
	public DiseaseController(DiseaseService diseaseService) {
		this.diseaseService = diseaseService;
	}
	 
	 @PreAuthorize("hasAuthority('FIND_DISEASE_PERMISSION')")
	 @GetMapping(params = "name")
	 public ResponseEntity<List<DiseaseDTO>> findByName(@RequestParam("name") String name) {	
		 	List<Disease> diseases = diseaseService.findAllByName(name);
	        List<DiseaseDTO> responseDTOS = new ArrayList<>();
	        for (Disease disease : diseases) {
	        	DiseaseDTO diseaseDTO = new DiseaseDTO(disease);
	            responseDTOS.add(diseaseDTO);
	        }
	        return ResponseEntity.ok(responseDTOS);
	        
	   }
	 
	 @PreAuthorize("hasAuthority('FIND_DISEASE_PERMISSION')")
	 @GetMapping(params = "code" )
	 public ResponseEntity<List<DiseaseDTO>> findByCode(@RequestParam("code") String code) {	
		 	List<Disease> diseases = diseaseService.findAllByCode(code);
		 	List<DiseaseDTO> responseDTOS = new ArrayList<>();
		 	for (Disease disease : diseases) {
	        	DiseaseDTO diseaseDTO = new DiseaseDTO(disease);
	            responseDTOS.add(diseaseDTO);
	        }
	        return ResponseEntity.ok(responseDTOS);
	        
	    }
	
}
