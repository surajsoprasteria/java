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
package org.wipo.connect.shared.integration.config;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
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

    @Value("#{configProperties['wsBasicAuthUser']}")
    private String wsBasicAuthUser;

    @Value("#{configProperties['wsBasicAuthPass']}")
    private String wsBasicAuthPass;

    @Value("#{configProperties['javaMelodyUser']}")
    private String javaMelodyUser;

    @Value("#{configProperties['javaMelodyPass']}")
    private String javaMelodyPass;

    @Value("#{configProperties['wsRestIntegrationAuth']}")
    private String wsRestIntegrationAuth;

    @Value("#{configProperties['workServicePort']}")
    private String workServicePort;

    @Value("#{configProperties['echoServicePort']}")
    private String echoServicePort;

    @Value("#{configProperties['interestedPartyServicePort']}")
    private String interestedPartyServicePort;

    @Autowired
    private CryptoHelper cryptoHelper;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

	// setup the url based authorization
	http.authorizeRequests()
		.antMatchers("/monitoring*").hasAuthority(SpringSecurityAuthorization.AUTH_JAVA_MELODY)
		.antMatchers("/" + workServicePort + "*").hasAuthority(SpringSecurityAuthorization.AUTH_SOAP_WS)
		.antMatchers("/" + echoServicePort + "*").hasAuthority(SpringSecurityAuthorization.AUTH_SOAP_WS)
		.antMatchers("/" + interestedPartyServicePort + "*").hasAuthority(SpringSecurityAuthorization.AUTH_SOAP_WS)
		.and().httpBasic().and().csrf().disable();

	if (null != wsRestIntegrationAuth && !wsRestIntegrationAuth.isEmpty()) {

	    Arrays.asList(StringUtils.split(wsRestIntegrationAuth, "+")).forEach(serviceRestAut -> {
		List<String> serviceAuth = Arrays.asList(StringUtils.split(serviceRestAut, "|"));
		try {
		    setDynamicRestWSPermission(http, serviceAuth.get(0), SpringSecurityAuthorization.AUTH_REST_WS + "_" + serviceAuth.get(1).toUpperCase());
		} catch (Exception e) {
		}
	    });
	}

	SpringSecurityUtils.httpsSetup(http, BooleanUtils.toBoolean(forceHttps), httpPorts, httpsPorts);

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

	auth.inMemoryAuthentication()
		.withUser(javaMelodyUser)
		.password("{noop}" + cryptoHelper.decrypt(javaMelodyPass))
		.authorities(SpringSecurityAuthorization.AUTH_JAVA_MELODY)
		.and().withUser(wsBasicAuthUser)
		.password("{noop}" + cryptoHelper.decrypt(wsBasicAuthPass))
		.authorities(SpringSecurityAuthorization.AUTH_SOAP_WS);

	if (null != wsRestIntegrationAuth && !wsRestIntegrationAuth.isEmpty()) {

	    Arrays.asList(StringUtils.split(wsRestIntegrationAuth, "+")).forEach(serviceRestAut -> {
		List<String> serviceAuth = Arrays.asList(StringUtils.split(serviceRestAut, "|"));
		try {
		    setDynamicRestWSAuth(auth, serviceAuth.get(1), serviceAuth.get(2), SpringSecurityAuthorization.AUTH_REST_WS + "_" + serviceAuth.get(1).toUpperCase());
		} catch (Exception e) {
		}
	    });
	}
    }

    private AuthenticationManagerBuilder setDynamicRestWSAuth(AuthenticationManagerBuilder auth, String user, String password, String authCode) throws Exception {
	auth.inMemoryAuthentication()
		.withUser(user)
		.password("{noop}" + cryptoHelper.decrypt(password))
		.authorities(authCode);

	return auth;
    }

    private HttpSecurity setDynamicRestWSPermission(HttpSecurity http, String restUrl, String authCode) throws Exception {
	http.authorizeRequests().antMatchers("/" + restUrl).hasAuthority(authCode);

	return http;
    }

}
