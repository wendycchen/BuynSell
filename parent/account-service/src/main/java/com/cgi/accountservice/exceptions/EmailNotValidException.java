package com.cgi.accountservice.exceptions;

public class EmailNotValidException extends Exception{
    public EmailNotValidException(String message){
        super(message);
    }
}
