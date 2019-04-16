package com.pitang.desafio.api.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.pitang.desafio.api.exceptions.ExpiredJWTException;
import com.pitang.desafio.api.model.service.CustomUserDetailService;

import io.jsonwebtoken.Jwts;

/**
 * JWTAuthorizationFilter
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

  @Autowired
  private CustomUserDetailService customUserDetailService;

  public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
      CustomUserDetailService customUserDetailService) {
    super(authenticationManager);
    this.customUserDetailService = customUserDetailService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    try {
      String header = request.getHeader(SecurityConstants.HEADER_STRING);

      if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
        chain.doFilter(request, response);
        return;
      }

      UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      chain.doFilter(request, response);
    } catch (ExpiredJWTException e) {
      throw new ExpiredJWTException(e.getMessage());
    }
  }

  private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
    String token = request.getHeader(SecurityConstants.HEADER_STRING);

    if (token == null) {
      return null;
    }
    String username = "";
    username = Jwts//
        .parser()//
        .setSigningKey(SecurityConstants.SECRET)//
        .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))//
        .getBody()//
        .getSubject();

    UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

    if (username != null) {
      return new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
    }
    return null;
  }
}