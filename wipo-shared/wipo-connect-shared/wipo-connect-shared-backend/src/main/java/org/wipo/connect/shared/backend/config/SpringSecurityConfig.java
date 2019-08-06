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
package org.wipo.connect.shared.backend.config;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.wipo.connect.common.crypto.CryptoHelper;
import org.wipo.connect.common.utils.SpringSecurityAuthorization;
import org.wipo.connect.common.utils.SpringSecurityUtils;

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

    @Value("#{configProperties['javaMelodyUser']}")
    private String javaMelodyUser;

    @Value("#{configProperties['javaMelodyPass']}")
    private String javaMelodyPass;

    @Value("#{configProperties['restWsUser']}")
    private String restWsUser;

    @Value("#{configProperties['restWsPass']}")
    private String restWsPass;

    @Autowired
    private CryptoHelper cryptoHelper;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

	// setup the url based authorization
	http.authorizeRequests()
		.antMatchers("/monitoring*").hasAuthority(SpringSecurityAuthorization.AUTH_JAVA_MELODY)
		.antMatchers("/**").hasAuthority(SpringSecurityAuthorization.AUTH_REST_WS)
		.and()
		.httpBasic()
		.and()
		.csrf().disable();

	SpringSecurityUtils.httpsSetup(http, BooleanUtils.toBoolean(forceHttps), httpPorts, httpsPorts);

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.inMemoryAuthentication()
		.withUser(javaMelodyUser)
		.password("{noop}" + cryptoHelper.decrypt(javaMelodyPass))
		.authorities(SpringSecurityAuthorization.AUTH_JAVA_MELODY)
		.and()
		.withUser(restWsUser)
		.password("{noop}" + cryptoHelper.decrypt(restWsPass))
		.authorities(SpringSecurityAuthorization.AUTH_REST_WS);
    }

}
