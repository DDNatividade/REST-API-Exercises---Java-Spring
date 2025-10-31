package com.apis.apisjwtswagger.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${SECRET_KEY}")
    private String secretKey;

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora

    // AÑADE ESTE MÉTODO PARA VERIFICAR
    @PostConstruct
    public void init() {
        System.out.println("=== DEBUG JWT ===");
        System.out.println("Secret Key cargada: " + (secretKey != null ? "SÍ" : "NO"));
        System.out.println("Longitud: " + (secretKey != null ? secretKey.length() : 0));
        System.out.println("Primeros chars: " + (secretKey != null ? secretKey.substring(0, Math.min(10, secretKey.length())) : "NULL"));

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        System.out.println("Tamaño decodificado: " + keyBytes.length * 8 + " bits");
        System.out.println("================");
    }


    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .claims(extraClaims)              // Claims custom (roles, etc)
                .subject(userDetails.getUsername()) /*Username del usuario.
                 En este caso es el correo electrónico*/
                .issuedAt(new Date(System.currentTimeMillis())) // Fecha creación
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Expiración
                .signWith(getSignInKey()) // Firma con tu clave (ya no necesitas especificar el algoritmo)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())  // Reemplaza setSigningKey
                .build()
                .parseSignedClaims(token)     // Reemplaza parseClaimsJws
                .getPayload();                // Reemplaza getBody
    }

    private SecretKey getSignInKey() {
        System.out.println(secretKey);
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        System.out.println("Tamaño real de la key: " + keyBytes.length * 8 + " bits");
        return Keys.hmacShaKeyFor(keyBytes);
    }
}