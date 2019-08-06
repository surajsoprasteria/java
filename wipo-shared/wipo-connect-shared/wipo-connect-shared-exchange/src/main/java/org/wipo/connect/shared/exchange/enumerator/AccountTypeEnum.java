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
 * The Enum AccountTypeEnum.
 */
public enum AccountTypeEnum {

    /** The interested party. */
    INTERESTED_PARTY("accountType.interested-party"),

    /** The operator. */
    OPERATOR("accountType.operator"),

    /** The licensee. */
    LICENSEE("accountType.licensee");

    /** The message code. */
    private final String messageCode;



    /**
     * Instantiates a new account type enum.
     *
     * @param messageCode
     *            the message code
     */
    AccountTypeEnum(String messageCode) {
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
}