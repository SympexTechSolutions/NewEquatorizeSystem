package com.attendance.auth.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.attendance.auth.entity.User;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	
	@Value("$(jwt.secretkey)")
	private String secretkey;
	
	@Value("$(jwt.expiration)")
	private long expiration;
	
	//generate Token
	
	public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secretkey)
                .compact();
    }
	
	// Validate token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                   .setSigningKey(secretkey)
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
    }
	

}
