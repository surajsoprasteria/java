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
package org.wipo.connect.common.restclient;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.wipo.connect.common.authentication.ISecurityUserDetail;
import org.wipo.connect.common.utils.ConstantUtils;

public class RestClientRequestInterceptor implements HttpRequestInterceptor{


	@Override
	public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if(auth == null){
			return;
		}

		String username;

		if(auth.getPrincipal() == null){
			return;
		}

		username = auth.getName();
		request.addHeader(ConstantUtils.HTTP_HEADER_USER_NAME, username);

		if(auth.getPrincipal() instanceof ISecurityUserDetail){
			ISecurityUserDetail userDetail = (ISecurityUserDetail) auth.getPrincipal();
			request.addHeader(ConstantUtils.HTTP_HEADER_USER_ID, userDetail.getId().toString());
		}

	}

}
