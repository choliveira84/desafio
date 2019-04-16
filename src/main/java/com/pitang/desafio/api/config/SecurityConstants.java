package com.pitang.desafio.api.config;

import java.util.concurrent.TimeUnit;

/**
 * SecurityConstants
 */
public class SecurityConstants {

    static final String SECRET = "senhaparawebtoken";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    static final String SIGN_UP_URL = "/signup";
    static final String SIGN_IN_URL = "/signin";
    // static final String SIGN_LOGIN_URL = "/login";
    static final long EXPIRATION_TIME = 10000;

    public static void main(String[] args) {
        System.out.println(TimeUnit.MILLISECONDS.convert(10, TimeUnit.SECONDS));
    }
}