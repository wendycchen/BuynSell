package com.cgi.accountservice.security;

import com.cgi.accountservice.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
//TODO: put secret and validity in yaml
    //@Value("${jwt.secret}")
    private String jwtSecret = "secret";

   // @Value("${jwt.token.validity}")
    private long tokenValidity =180000;

    public String getEmail(final String token) {
        try {
            Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            return body.getSubject();
        } catch (Exception e) {
            // TODO: 2022-03-24
            throw new IllegalArgumentException();
        }
    }

    public String generateToken(org.springframework.security.core.userdetails.User user) {

        Claims claims = Jwts.claims().setSubject(user.getUsername());

        final long nowMillis = System.currentTimeMillis();
        final long expMillis = nowMillis + tokenValidity;

        Date exp = new Date(expMillis);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }



}