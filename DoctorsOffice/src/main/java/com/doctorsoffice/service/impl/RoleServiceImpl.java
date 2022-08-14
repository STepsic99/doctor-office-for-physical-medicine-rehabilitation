package com.doctorsoffice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doctorsoffice.model.Role;
import com.doctorsoffice.repository.RoleRepository;
import com.doctorsoffice.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	 private final RoleRepository repository;

	    public RoleServiceImpl(RoleRepository repository) {
	        this.repository = repository;
	    }

		@Override
		public List<Role> findByName(String name) {
			 return repository.findByName(name);
		}
	    
	  

}
