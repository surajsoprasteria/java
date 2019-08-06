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



package org.wipo.connect.shared.backend.interceptor;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.wipo.connect.common.authentication.ContextUserInfo;
import org.wipo.connect.common.utils.ConstantUtils;



public class ContextUserInfoInterceptor implements HandlerInterceptor {

	@Autowired
	private ContextUserInfo contextUserInfo;


    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception exception)
            throws Exception {

    	// EMPTY

    }




    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
            Object arg2, ModelAndView arg3) throws Exception {
        // EMPTY
    }




    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {

    	// HEADER INFORMATION
    	Long id = NumberUtils.toLong(request.getHeader(ConstantUtils.HTTP_HEADER_USER_ID));
        String username = request.getHeader(ConstantUtils.HTTP_HEADER_USER_NAME);

        contextUserInfo.initialize(id, username);

        return true;
    }

}
