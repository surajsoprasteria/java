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
package org.wipo.connect.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * The Class SpringSecurityUtils.
 */
public class SpringSecurityUtils {

	private SpringSecurityUtils(){
		super();
	}



	/**
	 * Https setup.
	 *
	 * @param http the http
	 * @param forceHttps the force https
	 * @param httpPorts the http ports
	 * @param httpsPorts the https ports
	 * @throws Exception the exception
	 */
	public static void httpsSetup(final HttpSecurity http, boolean forceHttps, String httpPorts, String httpsPorts) throws Exception {
		// force https protocol
		if (forceHttps) {
			http.requiresChannel().anyRequest().requiresSecure();
		}

		//read http and https port configuration
		String[] httpPortList = StringUtils.split(httpPorts, ",");
		String[] httpsPortList = StringUtils.split(httpsPorts, ",");

		if(httpPortList == null
			|| httpsPortList == null
			|| httpPortList.length == 0
			|| httpsPortList.length == 0
			|| httpPortList.length != httpsPortList.length){

			throw new IllegalArgumentException("httpPorts and httpsPorts must contain at least 1 value and have the same length");
		}

		// http and https port mapping
		for(int i=0 ; i < httpPortList.length; i++){
			http.portMapper()
				.http(Integer.parseInt(httpPortList[i]))
				.mapsTo(Integer.parseInt(httpsPortList[i]));
		}
	}


}
