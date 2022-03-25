package com.cgi.accountservice.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import com.cgi.accountservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query
	Optional <User> findByEmail(String email);
	@Query
	Optional <User> findByUsername(String username);

	//Query that enables a user once they have confirmed their account
	@Transactional @Modifying @Query("UPDATE User a " + "SET a.isEnabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);
}
