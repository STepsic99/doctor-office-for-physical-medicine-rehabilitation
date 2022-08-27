package com.doctorsoffice.service;

import java.util.List;

import com.doctorsoffice.model.Disease;

public interface DiseaseService {
	
	public List<Disease> findAllByName(String name);
	public List<Disease> findAllByCode(String code);

}
