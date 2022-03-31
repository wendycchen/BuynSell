package com.cgi.util;

import com.cgi.exceptions.JwtTokenInvalidException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.token.validity}")
    private long tokenValidity;

    public Claims getClaims(final String token) {
        try {
            Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            return body;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " => " + e);
        }
        return null;
    }

    public String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(id).setSubject(email);
        long nowMillis = System.currentTimeMillis();
        long expiresMillis = nowMillis + tokenValidity;
        Date exp = new Date(expiresMillis);
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis)).setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public void validateToken(final String token) throws JwtTokenInvalidException {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        } catch (SignatureException ex) {
            throw new JwtTokenInvalidException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new JwtTokenInvalidException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new JwtTokenInvalidException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new JwtTokenInvalidException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new JwtTokenInvalidException("JWT claims string is empty.");
        }
    }

}