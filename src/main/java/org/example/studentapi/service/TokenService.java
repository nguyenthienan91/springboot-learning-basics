package org.example.studentapi.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.studentapi.entity.Account;
import org.example.studentapi.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class TokenService {
    private final String SECRET_KEY ="nguyenthienananannananananaanaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    private AuthenticationRepository authenticationRepository;

    public SecretKey getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //generate token
    public String generateToken(Account account) {
        String token = Jwts.builder()
                .subject(account.getId() + "")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .signWith(getSignInKey())
                .compact();
        return token;
    }

    //verify token
//    public Account extractToken(String token) {
//
//        return null;
//    }

    public Account extractToken(String token) {
        String value = extractClaim(token, Claims::getSubject);
        long id = Long.parseLong(value);
        return authenticationRepository.findAccountById(id);
    }

    public Claims extractAllClaims(String token) {
        return  Jwts.parser().
                verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public <T> T extractClaim(String token, Function<Claims,T> resolver){
        Claims claims = extractAllClaims(token);
        return  resolver.apply(claims);
    }
}
