package com.astra.learning.management.system.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret_key;

    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }



    public <T> T extractClaim(String token , Function<Claims,T> claimsFunction){
        final Claims claims = extractAllClaims(token);
        return claimsFunction.apply(claims);


    }

    public String genToken(UserDetails userDetails){
        return Jwts.builder()
                .claims(new HashMap<>())
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000 * 60 * 60 * 24 ))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();


    }

    public boolean isTokenValid(String token , UserDetails userDetails){
        final String userName = extractUsername(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token,
                Claims::getExpiration)
                .before(new Date());
    }



    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(secret_key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
