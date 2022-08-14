package com.doctorsoffice.service;

import java.util.List;

import com.doctorsoffice.model.Role;

public interface RoleService {
	List<Role> findByName(String name);
}
