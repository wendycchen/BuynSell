package com.cgi.accountservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter @AllArgsConstructor @ToString @NoArgsConstructor
public class RegistrationRequest {
	private  String firstName;
	private  String lastName;
	private  String email;
	private  String password;
	private  String username;
}
