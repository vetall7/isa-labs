package org.example.users.entities.User.JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${secret.key}")
    private String secretKey;

    public String generateToken(String username, String role) {
        long expirationTime = 1000 * 60 * 60 * 10; // 10 hours expiration
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiryDate)
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).build().parseSignedClaims(token).getBody().getSubject();
    }

    public String getRoleFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).build().parseSignedClaims(token).getBody().get("role").toString();
    }
}
