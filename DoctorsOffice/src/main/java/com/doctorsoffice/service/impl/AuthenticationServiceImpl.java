package com.doctorsoffice.service.impl;

import com.doctorsoffice.dto.TokenDTO;
import com.doctorsoffice.model.User;
import com.doctorsoffice.security.util.TokenUtils;
import com.doctorsoffice.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
	
	private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, TokenUtils tokenUtils) {
        this.authenticationManager = authenticationManager;
        this.tokenUtils = tokenUtils;
    }
    
	@Override
	public TokenDTO login(String username, String password) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        return new TokenDTO(getToken(user), user.getRoles().get(0).getName());
	}
	
	 private String getToken(User user) {
	        return tokenUtils.generateToken(user.getUsername(), user.getRoles().get(0).getName(), user.getId());
	    }

}
