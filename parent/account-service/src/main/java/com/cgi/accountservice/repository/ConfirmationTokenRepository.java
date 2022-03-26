package com.cgi.accountservice.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import com.cgi.accountservice.models.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
	@Query
	Optional<ConfirmationToken> findByToken(String token);

	//For confirming the token
	@Transactional @Modifying @Query("UPDATE ConfirmationToken c " +"SET c.confirmedAt = ?2 " +"WHERE c.token = ?1")
	void updateConfirmedAt(String token,LocalDateTime confirmedAt);
}
