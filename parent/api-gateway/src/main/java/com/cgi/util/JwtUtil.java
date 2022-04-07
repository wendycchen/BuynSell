package com.cgi.util;

import com.cgi.exceptions.JwtTokenInvalidException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component @Slf4j
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public Claims getClaims(final String token) {
        try {
            Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            return body;
        } catch (Exception e) {
            log.info("Error getting jwt claims {}",e.getMessage());
        }
        return null;
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