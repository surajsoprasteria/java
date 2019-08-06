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
package org.wipo.connect.shared.integration.service;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.wipo.connect.common.input.ContextType;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.echoservice.EchoServicePort;
import org.wipo.connect.echoservice.types.EchoRequest;
import org.wipo.connect.echoservice.types.EchoResponse;
import org.wipo.connect.shared.exchange.business.CommonBusiness;
import org.wipo.connect.shared.integration.utils.WSHelper;

@WebService(targetNamespace = "http://echoService.connect.wipo.org", name = "echoServicePort")
public class EchoServicePortImpl implements EchoServicePort {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(EchoServicePortImpl.class);

    @Autowired
    private CommonBusiness commonBusiness;

    @PostConstruct
    public void init() {
	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public EchoResponse echo(EchoRequest parameters) {
	EchoResponse echoResponse = new EchoResponse();
	try {
	    ContextType reqContext = parameters.getContext();
	    String code = reqContext.getClientId();
	    if (!commonBusiness.checkClientIdentity(reqContext.getClientId(), reqContext.getClientKey())) {
		LOGGER.warn("Invalid client code/key ({}/{})", reqContext.getClientId(), reqContext.getClientKey());
		echoResponse.setError(WSHelper.generateWsAuthErrorResponse(reqContext));
		return echoResponse;
	    }

	    String msgIn = parameters.getRequest().getMsgIn();
	    String msgOut = StringUtils.reverse(msgIn);
	    LOGGER.debug("[ClientCode:{}] - Echo: {} --> {}", code, msgIn, msgOut);

	    echoResponse.setResponse(new EchoResponse.Response());
	    echoResponse.getResponse().setMsgOut(msgOut);
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    echoResponse.setError(WSHelper.generateWsErrorResponse(e.getMessage()));
	}

	return echoResponse;
    }

}
