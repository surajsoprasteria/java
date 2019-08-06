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

package org.wipo.connect.ws_factory;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.crypto.CryptoHelper;
import org.wipo.connect.common.exception.WccRuntimeException;
import org.wipo.connect.common.vo.ServiceClientInfoVO;
import org.wipo.connect.echoservice.EchoService;
import org.wipo.connect.echoservice.EchoServicePort;
import org.wipo.connect.echoservice.types.EchoRequest;
import org.wipo.connect.echoservice.types.EchoResponse;
import org.wipo.connect.serviceclient.utils.ServiceClientUtils;

@Service
public class EchoServiceFactoryImpl implements EchoServiceFactory {

    private static Logger LOGGER = WipoLoggerFactory.getLogger(EchoServiceFactoryImpl.class);

    @Value("#{configProperties['echoServicePort']}")
    private String wsPortName;

    @Value("#{configProperties['wsBasicAuthUser']}")
    private String username;

    @Value("#{configProperties['wsBasicAuthPass']}")
    private String password;

    private EchoService service;

    private EchoServicePort servicePort;

    @Autowired
    private CryptoHelper cryptoHelper;

    @PostConstruct
    private void init() {
	// service instantiation
	this.service = new EchoService();
	if (this.service == null) {
	    throw new WccRuntimeException("service is null");
	}
	this.servicePort = this.service.getEchoServiceSOAP();

	if (this.servicePort == null) {
	    throw new WccRuntimeException("servicePort is null");
	}
	// getting BindingProvider
	BindingProvider bindingProvider = (BindingProvider) this.servicePort;

	// set property for WS-I
	Map<String, Object> reqContext = bindingProvider.getRequestContext();
	reqContext.put(BindingProvider.USERNAME_PROPERTY, this.username);
	reqContext.put(BindingProvider.PASSWORD_PROPERTY, cryptoHelper.decrypt(this.password));

    }

    @Override
    public void changeBaseUrl(String baseUrl) {
	BindingProvider bindingProvider = (BindingProvider) this.servicePort;

	String endPoint = StringUtils.removeEnd(baseUrl, "/");
	endPoint += "/" + this.wsPortName;
	// set endPoint
	bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);
    }

    @Override
    public EchoResponse callEcho(String msgIn, ServiceClientInfoVO clientInfoVO) {
	BindingProvider bindingProvider = (BindingProvider) this.servicePort;
	String endPoint = bindingProvider.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY).toString();
	if (endPoint == null || endPoint.equals("")) {
	    LOGGER.error("Endpoint base url not set");
	}

	return callEcho(msgIn, clientInfoVO, servicePort);
    }

    private EchoResponse callEcho(String msgIn, ServiceClientInfoVO clientInfoVO, EchoServicePort echoServicePort) {
	EchoRequest echoRequest = new EchoRequest();
	echoRequest.setRequest(new EchoRequest.Request());
	echoRequest.getRequest().setMsgIn(msgIn);

	echoRequest.setContext(ServiceClientUtils.generateServiceContext(clientInfoVO));

	EchoResponse echoResponse = echoServicePort.echo(echoRequest);

	return echoResponse;
    }

    @Override
    public EchoResponse callEcho(String msgIn, ServiceClientInfoVO clientInfoVO, String baseUrl) {
	// service instantiation
	EchoService tmpService = new EchoService();
	EchoServicePort tmpServicePort = tmpService.getEchoServiceSOAP();

	// getting BindingProvider
	BindingProvider tmpBindingProvider = (BindingProvider) tmpServicePort;

	String endPoint = StringUtils.removeEnd(baseUrl, "/");
	endPoint += "/" + this.wsPortName;
	// set endPoint
	tmpBindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);

	// set property for WS-I
	Map<String, Object> reqContext = tmpBindingProvider.getRequestContext();
	reqContext.put(BindingProvider.USERNAME_PROPERTY, this.username);
	reqContext.put(BindingProvider.PASSWORD_PROPERTY, cryptoHelper.decrypt(this.password));

	return callEcho(msgIn, clientInfoVO, tmpServicePort);
    }

}
