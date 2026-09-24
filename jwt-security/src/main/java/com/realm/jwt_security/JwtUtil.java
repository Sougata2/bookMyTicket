package com.realm.jwt_security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public class JwtUtil {

    private final SecretKey signingKey;
    private final long accessSeconds;
    private final long refreshSeconds;

    public JwtUtil(String secret, long accessSeconds, long refreshSeconds) {
        if (secret == null || secret.getBytes().length < 32) {
            throw new IllegalArgumentException(
                    "jwt.secret must be set and at least 32 bytes for HS256 — check application.yml / JWT_SECRET env var");
        }
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.accessSeconds = accessSeconds;
        this.refreshSeconds = refreshSeconds;
    }

    /**
     * Only services that issue tokens (e.g. auth-service) call this.
     */
    public String generateToken(String subject, Map<String, Object> claims) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + accessSeconds * 1000);

        return Jwts.builder()
                .subject(subject)
                .claims(claims)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(signingKey)
                .compact();
    }

    public long getAccessSeconds() {
        return accessSeconds;
    }

    public long getRefreshSeconds() {
        return refreshSeconds;
    }

    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractCurrentRole(String token) {
        return extractAllClaims(token).get("currentRole", String.class);
    }


    public boolean isTokenValid(String token) {
        try {
            return !extractExpiration(token).before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> resolver) {
        return resolver.apply(extractAllClaims(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}