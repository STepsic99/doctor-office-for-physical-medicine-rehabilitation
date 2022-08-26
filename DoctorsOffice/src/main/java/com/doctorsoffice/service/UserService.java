package com.doctorsoffice.service;

import com.doctorsoffice.dto.PasswordDTO;

public interface UserService {
	Boolean updatePassword(PasswordDTO dto, Long userId);
}
