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



package org.wipo.connect.shared.exchange.enumerator;



/**
 * The Enum DomainEnum.
 */
public enum DomainEnum {

    /** The local. */
    LOCAL("LOCAL", "global.domain-local"),

    /** The international. */
    INTERNATIONAL("INTERNATIONAL", "global.domain-international");

    /** The value. */
    private final String value;

    /** The message code. */
    private final String messageCode;



    /**
     * Instantiates a new domain enum.
     *
     * @param value
     *            the value
     * @param messageCode
     *            the message code
     */
    private DomainEnum(String value, String messageCode) {
        this.value = value;
        this.messageCode = messageCode;
    }



    /**
     * Gets the message code.
     *
     * @return the message code
     */
    public String getMessageCode() {
        return this.messageCode;
    }



    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return this.value;
    }
}
