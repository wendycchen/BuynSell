package com.cgi.accountservice.security;

import com.cgi.accountservice.models.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Configuration @Slf4j
public class JwtUtil {
//TODO: put secret and validity in yaml
    //@Value("${jwt.secret}")
    private String jwtSecret = "secret";

   // @Value("${jwt.token.validity}")
    private long tokenValidity = 180000;

    public String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername()).setSubject(user.getAuthorities().toString());

        //Creating JWT, using the users email and role as claims
        long nowMillis = System.currentTimeMillis();
        long expiresMillis = nowMillis + tokenValidity;
        Date exp = new Date(expiresMillis);
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

        log.info("JWT token generated for user: {}, role: {}",user.getUsername(),user.getAuthorities());
        return token;
    }



}