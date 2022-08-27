package com.doctorsoffice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doctorsoffice.model.Disease;
import com.doctorsoffice.repository.DiseaseRepository;
import com.doctorsoffice.service.DiseaseService;

@Service
public class DiseaseServiceImpl implements DiseaseService{
	
	private final DiseaseRepository diseaseRepository;
	
	public DiseaseServiceImpl(DiseaseRepository diseaseRepository) {
		this.diseaseRepository = diseaseRepository;
	}
	
	@Override
	public List<Disease> findAllByName(String name) {
		return diseaseRepository.findAllByName(name);
	}

	@Override
	public List<Disease> findAllByCode(String code) {
		return diseaseRepository.findAllByCode(code);
	}

}
