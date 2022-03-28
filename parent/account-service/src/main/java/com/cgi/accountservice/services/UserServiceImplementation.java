package com.cgi.accountservice.services;

import com.cgi.accountservice.exceptions.EmailAlreadyExistsException;
import com.cgi.accountservice.exceptions.EmailAndUsernameExists;
import com.cgi.accountservice.exceptions.UsernameAlreadyExistsException;
import com.cgi.accountservice.models.Role;
import com.cgi.accountservice.models.User;
import com.cgi.accountservice.repository.UserRepository;
import com.cgi.accountservice.security.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService, UserDetailsService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService tokenService;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder, ConfirmationTokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    //In this case the username will be the email
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Email Not found");
        }

        User user = userOptional.get();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.setUserRole(Role.USER);
        authorities.add(new SimpleGrantedAuthority(user.getUserRole().name()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);

    }

    public String addUser(User user) throws EmailAndUsernameExists, EmailAlreadyExistsException, UsernameAlreadyExistsException {
        boolean emailExists = userRepository.findByEmail(user.getEmail()).isPresent();
        boolean userNameExists = userRepository.findByUsername(user.getUsername()).isPresent();

        if (emailExists && userNameExists) {
            throw new EmailAndUsernameExists("Email and username already in use");
        } else if (emailExists) {
            throw new EmailAlreadyExistsException("Email already in use");
        } else if (userNameExists) {
            throw new UsernameAlreadyExistsException("Username already in use");
        }
        //Encoding password to be stored
        String encodedPassword = passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);

        //saving user
        User savedUser = userRepository.save(user);
        //saving token
        return tokenService.generateToken(savedUser);
    }

    public void enableUser(String email) {
        userRepository.enableAppUser(email);
    }


}
