package com.pitang.desafio.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pitang.desafio.api.model.service.CustomUserDetailService;

/**
 * SecurityConfig
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.cors().and().csrf()//
                .disable()//
                .authorizeRequests()//
                .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll()//
                .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_IN_URL).permitAll()//
                .anyRequest().authenticated()//
                .and()//
                .addFilter(new JWTAuthenticatorFilter(authenticationManager()))//
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailService));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService)//
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    // @Autowired
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception
    // {
    // auth.inMemoryAuthentication()//
    // .withUser("carlos").password("{noop}12345").roles("USER", "ADMIN")//
    // .and()//
    // .withUser("henrique").password("{noop}12345").roles("USER");
    // }
}