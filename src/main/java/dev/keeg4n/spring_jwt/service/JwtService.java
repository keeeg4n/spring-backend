package dev.keeg4n.spring_jwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "oot6ZUCdNtkR1r7tX1c1jp1s9oAYP/7maK6rRNbmSNQnjGAEaxB/0+PsX4P2VJ3eucWJESMK0qM/Zho3W10GuOVLRMd54nvNNQ20gx7lAlwl+jRu5YL9MARZMZTM8Xnslc/05osB+KNZMJ2S2dbroMP2uwSQFNMeo0kEpsXdbWu9fB6z9QLpoCy4HevHdVw4YW8+EjRINYx/ndgv/gqdf+iXXYpYzBfa4S4J81jL/SvKOCLxP8eurH5361ne7/pWYn/7kcLqL53w0i2Jv2iil7q1HU/5bDFY6AEEd2EHSCbfN7SyEzidCm1aoqldiB2GcJ45LyL4Pe7IreNN2t7YqM/TBqrq6swmw7mpz1S5l0Y=";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        // check how to change the algo

        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000* 60 * 24))
                .signWith(getSignInKey())
                .compact();
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private Boolean isTokenExpired(String token) {
        return getTokenExpirationDate(token).before(new Date());
    }

    private Date getTokenExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey()) // used to sign the signature part of the JWT
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }

}
