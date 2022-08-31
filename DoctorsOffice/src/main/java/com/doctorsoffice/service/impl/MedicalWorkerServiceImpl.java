package com.doctorsoffice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doctorsoffice.model.MedicalWorker;
import com.doctorsoffice.repository.MedicalWorkerRepository;
import com.doctorsoffice.service.MedicalWorkerService;

@Service
public class MedicalWorkerServiceImpl implements MedicalWorkerService{
	
private final MedicalWorkerRepository medicalWorkerRepository;
	
	public MedicalWorkerServiceImpl(MedicalWorkerRepository medicalWorkerRepository) {
		this.medicalWorkerRepository = medicalWorkerRepository;
	}


	@Override
	public MedicalWorker findById(Long id) {
		Optional<MedicalWorker> medicalWorker= medicalWorkerRepository.findById(id);
		if(medicalWorker.isPresent()) return medicalWorker.get();
		return null;
	}


	@Override
	public List<MedicalWorker> findAll() {
		return medicalWorkerRepository.findAll();
	}

}
