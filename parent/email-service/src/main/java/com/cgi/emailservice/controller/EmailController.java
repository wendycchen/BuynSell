package com.cgi.emailservice.controller;

import com.cgi.emailservice.models.Confirmation;
import com.cgi.emailservice.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController @RequestMapping("api/v1/email")
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/confirmation")
    public void sendEmailConfirmation(@RequestBody Confirmation confirmation){
        String link = "http://localhost:9006/api/v1/account/confirm?token="+confirmation.getToken();


    }
}
