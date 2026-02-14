package com.dee.employee_management.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expirationMs}")
    private long expirationMs;

    private Key key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // will replace with email
    public String getUsernameFromToken(String token){
        return getClaimsFromToken(token).get("valid", String.class);
    }
    public Boolean extractValidFlag(String token) {
        return getClaimsFromToken(token).get("valid", Boolean.class);
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token);

            Boolean isValid = claims.get("valid", Boolean.class);
            if (isValid == null || !isValid) return false;

            return !claims.getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
    public String validateEmail(String token){
        try{
            Claims claims = getClaimsFromToken(token);
            return claims.get("email", String.class);
        } catch (JwtException | IllegalArgumentException e){
            return "Email not valid";
        }
    }

    private Claims getClaimsFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenExpired(String token) {
        Date expiration = getClaimsFromToken(token).getExpiration(); // JJWT: exp claim
        return expiration.before(new Date()); // true if expiration date < now
    }

    public long getExpirationMs() {
        return expirationMs;
    }
}
