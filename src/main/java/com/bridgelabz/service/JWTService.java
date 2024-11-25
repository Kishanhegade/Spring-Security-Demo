package com.bridgelabz.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JWTService {

    private String secret = "9XxWswbRoSemZ9Fs0Yn2QsdhBp9hQg+ICgF9QXA6hNQ=";

    private String createJwt(String username){
        return Jwts.builder()
                .setClaims(Map.of("username",username))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+60*60*1000))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigninKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String generateAccessToken(String username){
        return createJwt(username);
    }
    public Claims parseJwt(String token) {
        JwtParser parser = Jwts.parserBuilder().setSigningKey(getSigninKey()).build();
        return parser.parseClaimsJws(token).getBody();
    }
}
