/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Copyright Connection (WCC).
 *
 *
 * @author Fincons
 *
 */
package org.wipo.connect.serviceclient.utils;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;

/*
 * This simple SOAPHandler will output the contents of incoming
 * and outgoing messages.
 */
public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(LoggingHandler.class);


    @Override
    public boolean handleMessage(SOAPMessageContext smc) {
	log(smc);
	return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext smc) {
	log(smc);
	return true;
    }

    /*
     * Check the MESSAGE_OUTBOUND_PROPERTY in the context to see if this is an
     * outgoing or incoming message. Write a brief message to the print stream
     * and output the message. The writeTo() method can throw SOAPException or
     * IOException
     */
    private void log(MessageContext smc) {
	Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

	StringBuilder sb = new StringBuilder();
	if (outboundProperty.booleanValue()) {
	    sb.append("WS Message - outbound: ");
	} else {
	    sb.append("WS Message - inbound: ");
	}

	SOAPMessage message = ((SOAPMessageContext) smc).getMessage();
	try {
	    OutputStream out = new ByteArrayOutputStream();
	    message.writeTo(out);
	    sb.append(out.toString());
	    LOGGER.debug(sb.toString());
	} catch (Exception e) {
	    LOGGER.error("Exception in LoggingHandler: " + e);
	}
    }

    @Override
    public void close(MessageContext context) {
    }

    @Override
    public Set<QName> getHeaders() {
	return Collections.emptySet();
    }


}
