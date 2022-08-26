package com.doctorsoffice.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.doctorsoffice.dto.PasswordDTO;
import com.doctorsoffice.model.User;
import com.doctorsoffice.repository.UserRepository;
import com.doctorsoffice.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository repository;
	
	private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

	@Override
	public Boolean updatePassword(PasswordDTO dto, Long userId) {
		Optional<User> user = repository.findById(userId);
		if(user.isEmpty()) return false;
		User foundUser = user.get();
		if (!passwordEncoder.matches(dto.getOldPassword(), foundUser.getPassword())) return false;
		foundUser.setPassword(passwordEncoder.encode(dto.getNewPassword()));
		repository.save(foundUser);
		return true;
	}
	
}
