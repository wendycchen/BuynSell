package com.cgi.accountservice.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import java.util.Date;


@Configuration @Slf4j
public class JwtUtil {
//TODO: put secret and validity in yaml
    //@Value("${jwt.secret}")
    private String jwtSecret = "secret";

   // @Value("${jwt.token.validity}")
    private long tokenValidity = 1800000;

    public String generateToken(User user) {
        long nowMillis = System.currentTimeMillis();
        long expiresMillis = nowMillis + tokenValidity;
        Date exp = new Date(expiresMillis);
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role",user.getAuthorities())
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

        log.info("JWT token generated for user: {}, role: {}",user.getUsername(),user.getAuthorities());
        return token;
    }



}