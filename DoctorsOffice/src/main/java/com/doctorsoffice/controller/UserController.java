package com.doctorsoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctorsoffice.dto.PasswordDTO;
import com.doctorsoffice.model.User;
import com.doctorsoffice.service.UserService;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PreAuthorize("hasAuthority('CHANGE_PASSWORD_PERMISSION')")
	@PutMapping("/updatePassword")
	public Boolean updatPassword(@RequestBody PasswordDTO dto, Authentication authentication) {
		UserDetails detUser = (UserDetails)authentication.getPrincipal();
		User user = (User) detUser;
		return this.userService.updatePassword(dto, user.getId());
	}

}
