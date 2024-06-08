package br.com.caicara.backend.model.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
public class JwtUtils {


    public static final String JWT_BEARER = "Bearer ";
    public static final String JWT_AUTHORIZATION = "Authorization";
    public static final String SECRET_KEY = "0123456789-0123456789-0123456789";
    public static final long EXPIRE_DAYS = 0;
    public static final long EXPIRE_HOURS = 0;
    public static final long EXPIRE_MINUTES = 10;

    private JwtUtils(){}

    public static SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    private static Date toExpiredDate(Date start){
        LocalDateTime dateTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime end = dateTime.plusDays(EXPIRE_DAYS).plusHours(EXPIRE_HOURS).plusMinutes(EXPIRE_MINUTES);
        return Date.from(end.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static JwtToken createToken(String email, String role){
        Date issueAt = new Date();
        Date limit = toExpiredDate(issueAt);

        String token = Jwts.builder()
                .header().add("typ", "JWT")
                .and()
                .subject(email)
                .expiration(limit)
                .signWith(getSecretKey())
                .claim("role", role)
                .compact();

        return new JwtToken(token);
    }

    private static Claims getClaimsFromToken(String token){
        try{
            return Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(refactorToken(token))
                    .getPayload();
        }
        catch (JwtException e){
            log.error(String.format("Token invalido: %s", e.getMessage()));
        }
        return null;
    }

    private static String refactorToken(String token){
        if (token.contains(JWT_BEARER))
            return token.substring(JWT_BEARER.length());

        return token;
    }

    public static String getEmailFromToken(String token){
        return getClaimsFromToken(token).getSubject();
    }

    public static boolean isTokenValid(String token){
        try {
            Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(refactorToken(token));
            return true;
        }
        catch (JwtException e){
            log.error(String.format("Token invalid: %s", e.getMessage()));
        }
        return false;
    }
}
