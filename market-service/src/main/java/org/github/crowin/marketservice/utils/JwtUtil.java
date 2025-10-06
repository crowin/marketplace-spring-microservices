package org.github.crowin.marketservice.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;


public class JwtUtil {
    // hardcoded secret key for demo purposes
    private static final String SECRET_BASE64 = "bXlzdXBlcnNlY3JldGtleTEyMzQ1Njc4OTAxMjM0NTY3ODkw";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_BASE64));

    /**
     * Extracts user id from JWT token.
     * @param token JWT token.
     * @return User id.
     */
    public static Integer extractUserId(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload().get("userId", Integer.class);
    }
}
