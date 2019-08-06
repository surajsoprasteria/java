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



package org.wipo.connect.shared.web.interceptor;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.wipo.connect.shared.web.utils.SecurityUtils;



/**
 * The Class LogParamsHandlerInterceptor.
 */
public class LogParamsHandlerInterceptor implements HandlerInterceptor {

    /** The security utils. */
    @Autowired
    private SecurityUtils securityUtils;




    @Override
    public void afterCompletion(HttpServletRequest arg0,
            HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {

        MDC.remove("USERNAME");
        MDC.remove("SESSION_ID");
        MDC.remove("IP_ADDRESS");
    }




    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
            Object arg2, ModelAndView arg3) throws Exception {
        // EMPTY
    }




    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {

        // SESSION INFORMATIONS
        String username = this.securityUtils.getLoggedUsername();
        HttpSession httpSession = request.getSession(false);
        String ipAddress = request.getRemoteAddr();

        // LOG PARAMETERS        
        MDC.put("USERNAME", username);
        MDC.put("SESSION_ID", httpSession != null ? httpSession.getId() : "");
        MDC.put("IP_ADDRESS", ipAddress != null ? ipAddress : "");

        return true;
    }

}
