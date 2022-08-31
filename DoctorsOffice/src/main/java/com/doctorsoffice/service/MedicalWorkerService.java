package com.doctorsoffice.service;

import java.util.List;

import com.doctorsoffice.model.MedicalWorker;

public interface MedicalWorkerService {
	
	MedicalWorker findById(Long id);
	
	List<MedicalWorker> findAll();

}
