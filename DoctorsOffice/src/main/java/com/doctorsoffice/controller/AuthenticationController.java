package com.doctorsoffice.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctorsoffice.dto.LoginDTO;
import com.doctorsoffice.dto.TokenDTO;
import com.doctorsoffice.service.AuthenticationService;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationController {
	
	private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
	
	@PostMapping(value = "/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
        TokenDTO tokenDTO = authenticationService.login(loginDTO.getUsername(), loginDTO.getPassword());
        return ResponseEntity.ok(tokenDTO);
    }

}
