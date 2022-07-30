package com.doctorsoffice.service;

import com.doctorsoffice.dto.TokenDTO;

public interface AuthenticationService {
	TokenDTO login(String username, String password);
}
