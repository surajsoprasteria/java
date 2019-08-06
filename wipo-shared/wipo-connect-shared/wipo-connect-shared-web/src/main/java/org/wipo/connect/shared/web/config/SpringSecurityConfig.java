/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Connect.
 *
 *
 * @author Fincons
 *
 */
package org.wipo.connect.shared.web.config;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.wipo.connect.common.utils.SpringSecurityAuthorization;
import org.wipo.connect.common.utils.SpringSecurityUtils;
import org.wipo.connect.shared.web.authentication.UserAuthenticationFailureHandler;
import org.wipo.connect.shared.web.authentication.UserAuthenticationFailureHandlerImpl;
import org.wipo.connect.shared.web.authentication.UserAuthenticationSuccessHandler;
import org.wipo.connect.shared.web.authentication.UserAuthenticationSuccessHandlerImpl;

/**
 * The Class SpringSecurityConfig.
 */
@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("#{configProperties['forceHttps']}")
    private String forceHttps;

    @Value("#{configProperties['httpPorts']}")
    private String httpPorts;

    @Value("#{configProperties['httpsPorts']}")
    private String httpsPorts;

    @Autowired
    private UserDetailsService customUserDetailsService;

    /**
     * Authentication success handler.
     *
     * @return the user authentication success handler
     */
    @Bean
    public UserAuthenticationSuccessHandler authenticationSuccessHandler() {
	UserAuthenticationSuccessHandlerImpl userAuthenticationSuccessHandler = new UserAuthenticationSuccessHandlerImpl();
	userAuthenticationSuccessHandler.setDefaultTargetUrl("/mvc/common/home");
	return userAuthenticationSuccessHandler;
    }

    @Bean
    public UserAuthenticationFailureHandler authenticationFailureHandler() {
	UserAuthenticationFailureHandlerImpl userAuthenticationFailureHandler = new UserAuthenticationFailureHandlerImpl();
	return userAuthenticationFailureHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

	// setup the url based authorization
	http.authorizeRequests()
		// FREE ACCESS
		.antMatchers("/mvc/common/login*").permitAll()
		.antMatchers("/mvc/common/logout").permitAll()
		.antMatchers("/res/**").permitAll()
		.antMatchers("/mvc/terms/**").permitAll()
		.antMatchers("/mvc/style/**").permitAll()
		.antMatchers("/mvc/common/index").permitAll()
		.antMatchers("/mvc/common/external.css").permitAll()
		.antMatchers("/**").hasRole(SpringSecurityAuthorization.ROLE_APP_USER)

		// Login form options
		.and()
		.formLogin()
		.loginPage("/mvc/common/login")
		.loginProcessingUrl("/mvc/common/executeAuthenticate")
		.defaultSuccessUrl("/mvc/common/home")
		.usernameParameter("email")
		.passwordParameter("password")
		.successHandler(authenticationSuccessHandler())
		.failureHandler(authenticationFailureHandler())

		// logout options
		.and()
		.logout()
		.logoutUrl("/mvc/common/executeLogout")
		.logoutSuccessUrl("/mvc/common/logout")

		// remember me options
		.and()
		.rememberMe()
		.key("remember-me-key")
		.rememberMeParameter("remember-me")
		.tokenValiditySeconds(1209600)

		// enable iframe for same origin
		.and().headers().frameOptions().sameOrigin()

		// Disable CSRF
		.and()
		.csrf().disable();

	SpringSecurityUtils.httpsSetup(http, BooleanUtils.toBoolean(forceHttps), httpPorts, httpsPorts);

    }

    /**
     * Configure global.
     *
     * @param auth
     *            the auth
     * @throws Exception
     *             the exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
