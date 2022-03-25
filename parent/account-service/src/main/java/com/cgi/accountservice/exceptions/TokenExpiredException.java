package com.cgi.accountservice.exceptions;

public class TokenExpiredException extends Exception{
	
	public TokenExpiredException(String message) {
		super(message);
	}

}
