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

package org.wipo.connect.shared.web.conversion;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.wipo.connect.common.caseconverter.CaseConversionEnum;
import org.wipo.connect.common.caseconverter.CaseConverter;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.shared.exchange.vo.RequestResultVO;

/**
 * The Class CustomDataBinder.
 */
@ControllerAdvice
public class CustomCaseConverter implements ResponseBodyAdvice<Object> {

    private static final Logger logger = WipoLoggerFactory.getLogger(CustomCaseConverter.class);

    @Value("#{configProperties['fieldsCaseConversion']}")
    private CaseConversionEnum caseConversion;

    @Value("#{configProperties['fieldsCaseConversionPackage']}")
    private String fieldsCaseConversionPackage;

    private String[] packageList;

    @InitBinder
    public void init() {
	packageList = StringUtils.split(fieldsCaseConversionPackage, ",");
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
	return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
	    Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

	if (body instanceof RequestResultVO) {
	    try {
		for (Object obj : ((RequestResultVO) body).getData().values()) {
		    CaseConverter.convertCase(obj, caseConversion, packageList);
		}
	    } catch (Exception e) {
		logger.error("Error: ", e);
	    }
	}

	return body;
    }
}