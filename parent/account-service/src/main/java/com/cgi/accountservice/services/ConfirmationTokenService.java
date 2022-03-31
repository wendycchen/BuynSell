package com.cgi.accountservice.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.cgi.accountservice.models.ConfirmationToken;
import com.cgi.accountservice.models.User;
import com.cgi.accountservice.repository.ConfirmationTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service @Slf4j
public class ConfirmationTokenService {
	
	private final ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
	public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository) {
		this.confirmationTokenRepository = confirmationTokenRepository;
	}
	public void saveConfirmationToken(ConfirmationToken token) {
		confirmationTokenRepository.save(token);
	}
    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }
    public void setConfirmedAt(String token) {
		confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
	public String generateToken(User user) {
		String token = UUID.randomUUID().toString();
		ConfirmationToken confirmationtoken = new ConfirmationToken(
				token,
				LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(10),
				user
		);
		saveConfirmationToken(confirmationtoken);
		log.info("Confirmation token: {} ,created for user with email: {}, token expires at: {}",confirmationtoken.getToken(),user.getEmail(),confirmationtoken.getExpiresAt());
		return token;
	}

}



