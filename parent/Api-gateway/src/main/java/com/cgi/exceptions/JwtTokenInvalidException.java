package com.cgi.exceptions;

public class JwtTokenInvalidException extends Exception{

    public JwtTokenInvalidException(String message){
        super(message);
    }
}
