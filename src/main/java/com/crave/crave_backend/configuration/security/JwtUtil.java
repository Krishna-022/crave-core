package com.crave.crave_backend.configuration.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.crave.crave_backend.constant.SecurityConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private String secretKey = SecurityConstants.SECRET_KEY;
	
	private SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	}
	
	public String getAccessToken(Long userId) {
		
		String accessToken = Jwts.builder()
				.subject(userId.toString())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000L))
				.issuer(SecurityConstants.APPLICATION_NAME)
				.signWith(getSecretKey())
				.compact();
		
		return accessToken;
	}
	
	public String verifyAccessToken(String accessToken) {
		
		String id = Jwts.parser()
				.requireIssuer(SecurityConstants.APPLICATION_NAME)
				.verifyWith(getSecretKey())
				.build()
				.parseSignedClaims(accessToken)
				.getPayload()
				.getSubject();
		
		return id;
	}
}
