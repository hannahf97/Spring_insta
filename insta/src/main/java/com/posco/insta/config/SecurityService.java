package com.posco.insta.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service
public class SecurityService {
    @Value("${jwt.secret_key}")
    String SECRET_KEY;

    @Value("${jwt.expTime}")
    long expTime;
    public String createToken(String subject){
        if(expTime <= 0){
            throw new RuntimeException();
        }
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signaturekey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        return Jwts.builder().setSubject(subject).signWith(signaturekey).setExpiration(new Date(System.currentTimeMillis()+expTime)).compact();
        //java11부터 builder 사용가능
    }

    public String getSubject(String tokenBearer){
        String token = tokenBearer.substring("Bearer ".length());
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
