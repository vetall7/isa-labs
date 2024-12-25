package org.example.gateway.JWT;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${secret.key}")
    private String secretKey;

    public String getUserNameFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).build().parseSignedClaims(token).getBody().getSubject();
    }

    public String getRoleFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).build().parseSignedClaims(token).getBody().get("role").toString();
    }

    private Date extractExpiration(String token) {
        return Jwts.parser().setSigningKey(secretKey).build().parseSignedClaims(token).getBody().getExpiration();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}
