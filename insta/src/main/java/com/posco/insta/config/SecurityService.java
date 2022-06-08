package com.posco.insta.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service
public class SecurityService {
    private static final String SECRET_KEY = "123jh124kjlfkjafdasdfasdflkjkldkajsdkjdfjhasdjflksjdflkjsdlfakjskjklfdjkajdkajdskfjkf";
    public String createToken(String subject, long expTime){
        if(expTime <= 0){
            throw new RuntimeException();
        }
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signaturekey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        return Jwts.builder().setSubject(subject).signWith(signaturekey).setExpiration(new Date(System.currentTimeMillis()+expTime)).compact();
        //java11부터 builder 사용가능
    }

    public String getSubject(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
