package com.hq.treasurechest.utils;

import com.hq.treasurechest.common.CustomConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JWTUtil {
    private static final String SECRET_KEY = CustomConstant.SECRET_KEY;
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 小时

    static {
        System.out.println(SECRET_KEY);
    }

    public static String generateToken(String username) {
        return createToken(new HashMap<>(), username);
    }

    private static String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                // The specified key byte array is 200 bits which is not secure enough for any JWT HMAC-SHA algorithm
                // 需要进行BASE64解码
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY)), SignatureAlgorithm.HS256)
                .compact();
    }

    public static boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private static boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY))) // Base64解码
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
