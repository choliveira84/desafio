package com.pitang.desafio.api.config;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JWTUtil
 */
public class JWTUtil {

    public static String generateToken(String username) {
        String token = Jwts//
                .builder()//
                .setSubject(username)//
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))//
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET).compact();

        return token;
    }
}