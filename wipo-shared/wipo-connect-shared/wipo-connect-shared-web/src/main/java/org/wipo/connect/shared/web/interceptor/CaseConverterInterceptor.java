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

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.wipo.connect.common.caseconverter.CaseConversionEnum;
import org.wipo.connect.common.caseconverter.CaseConverter;

/**
 * The Class CaseConverterInterceptor.
 */
public class CaseConverterInterceptor implements HandlerInterceptor {

    @Value("#{configProperties['fieldsCaseConversion']}")
    private CaseConversionEnum caseConversion;

    @Value("#{configProperties['fieldsCaseConversionPackage']}")
    private String fieldsCaseConversionPackage;

    private String[] packageList;

    /**
     * Inits the.
     */
    @PostConstruct
    public void init() {
	packageList = StringUtils.split(fieldsCaseConversionPackage, ",");
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception exception)
	    throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object handler, ModelAndView mv)
	    throws Exception {

	// post process model objects and convert case of annotated field
	if (mv != null && mv.getModel() != null) {
	    for (Object obj : mv.getModel().values()) {
		CaseConverter.convertCase(obj, caseConversion, packageList);
	    }
	}

	// RequestResultVO convertCase in CustomCaseConverter method

    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
	return true;
    }

}
