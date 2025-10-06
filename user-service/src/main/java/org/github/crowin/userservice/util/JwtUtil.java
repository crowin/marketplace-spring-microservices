package org.github.crowin.userservice.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class JwtUtil {
    //hardcoded secret key for demo purposes
    private static final String SECRET_BASE64 = "bXlzdXBlcnNlY3JldGtleTEyMzQ1Njc4OTAxMjM0NTY3ODkw";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_BASE64));
    private static final long EXPIRATION_TIME = TimeUnit.DAYS.toMillis(7);

    public static String generateToken(String username, Long userId) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .claim("userId", userId)
                .signWith(SECRET_KEY)
                .compact();
    }

    public static SecretKey getSecretKey() {
        return SECRET_KEY;
    }
}
