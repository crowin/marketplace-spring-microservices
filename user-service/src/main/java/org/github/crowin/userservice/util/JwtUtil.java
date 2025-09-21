package org.github.crowin.userservice.util;

import io.jsonwebtoken.Jwts;
import lombok.Getter;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static io.jsonwebtoken.Jwts.SIG.HS256;


public class JwtUtil {
    private static final SecretKey SECRET_KEY = HS256.key().build();
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
