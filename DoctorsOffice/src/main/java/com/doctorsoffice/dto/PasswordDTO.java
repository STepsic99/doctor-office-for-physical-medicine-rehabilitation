package com.doctorsoffice.dto;

public class PasswordDTO {
	
	private String oldPassword;
	private String newPassword;
	
	
	public PasswordDTO() {
		super();
	}


	public PasswordDTO(String oldPassword, String newPassword) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}


	public String getOldPassword() {
		return oldPassword;
	}


	public String getNewPassword() {
		return newPassword;
	}
	
	
	

}
