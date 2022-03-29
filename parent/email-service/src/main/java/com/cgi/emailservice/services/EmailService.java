package com.cgi.emailservice.services;

public interface EmailService {
    void sendConfirmation(String to, String email);
    String buildConfirmationEmail(String name, String link);
}
