package com.backbase.test.atmlocator.config;

import com.backbase.test.atmlocator.handlers.RestAuthenticationEntryPoint;
import com.backbase.test.atmlocator.handlers.RestAuthenticationFailureHandler;
import com.backbase.test.atmlocator.handlers.RestAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by luizsantana on 10/6/15.
 */
@EnableWebSecurity
@Configuration
public class AtmLocatorAuthenticationConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;
    @Autowired
    RestAuthenticationFailureHandler restAuthenticationFailureHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("backbase")
                .password("password")
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests().anyRequest().fullyAuthenticated()
            .and()
            .httpBasic()
            .and()
            .csrf().disable();
        http.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint);
        http.formLogin().successHandler(restAuthenticationSuccessHandler);
        http.formLogin().failureHandler(restAuthenticationFailureHandler);
    }
}
