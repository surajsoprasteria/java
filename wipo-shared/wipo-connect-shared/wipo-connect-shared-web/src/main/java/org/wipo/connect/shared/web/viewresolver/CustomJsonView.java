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

package org.wipo.connect.shared.web.viewresolver;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.wipo.connect.common.conversion.JacksonObjectMapper;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.ConstantUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentFamily;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

/**
 * The Class CustomJsonView.
 */
@Component
public class CustomJsonView extends MappingJackson2JsonView {

    /** The Constant LOG. */
    private static final Logger LOG = WipoLoggerFactory.getLogger(CustomJsonView.class);

    /** The Constant BROWSER_USER_AGENT. */
    private static final String BROWSER_USER_AGENT = "User-Agent";

    /** The Constant RESPONSE_TYPE_TEXT. */
    private static final String RESPONSE_TYPE_TEXT = "text/html";

    /** The Constant RESPONSE_TYPE_JSON. */
    private static final String RESPONSE_TYPE_JSON = "application/json";

    /** The object mapper. */
    private ObjectMapper objectMapper;

    /** The ua parser. */
    private UserAgentStringParser uaParser = UADetectorServiceFactory.getResourceModuleParser();

    @Autowired
    private JacksonObjectMapper jacksonObjectMapper;

    @PostConstruct
    public void init() {
	this.setObjectMapper(jacksonObjectMapper);
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
	Object value = filterModel(model);
	// String jsonString = this.objectMapper.writeValueAsString(value);

	ReadableUserAgent agent = this.uaParser.parse(request.getHeader(BROWSER_USER_AGENT));

	response.setContentType(RESPONSE_TYPE_JSON);
	response.setCharacterEncoding(ConstantUtils.CHARSET_UTF8);
	response.getOutputStream().write(this.objectMapper.writeValueAsBytes(value));

	try {
	    if (agent.getFamily().equals(UserAgentFamily.IE) && Integer.parseInt(agent.getVersionNumber().getMajor()) < 10) {
		response.setContentType(RESPONSE_TYPE_TEXT);
	    }
	} catch (NumberFormatException e) {
	    LOG.error("NumberFormatException", e);
	}

	LOG.trace("CustomJsonView - {} - {} - {}", new Object[] { agent.getName(), agent.getFamily(), agent.toString() });
    }

    @Override
    public void setObjectMapper(ObjectMapper objectMapper) {
	this.objectMapper = objectMapper;
    }
}