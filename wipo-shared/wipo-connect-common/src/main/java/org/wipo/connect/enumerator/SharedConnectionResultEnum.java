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
package org.wipo.connect.enumerator;

public enum SharedConnectionResultEnum {
    SUCCESS("sharedWS.test.success"),
    CONNECTION_ERROR("sharedWS.test.connection-error"),
    CLIENT_AUTHENTICATION_ERROR("sharedWS.test.client-auth-fail"),
    UNKNOWN_ERROR("sharedWS.test.generic-error");

    /** The message code. */
    private String messageCode;

    SharedConnectionResultEnum(String messageCode) {
	this.messageCode = messageCode;
    }

    public String getMessageCode() {
	return this.messageCode;
    }

}
