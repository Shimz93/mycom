package com.jw.springweb.dto;

public class loginDTO {
	String email;
	String password;
	
	public loginDTO(String email, String passord) {
		this.email = email;
		this.password=passord;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
