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
 * The Enum IPDeleteErrorEnum.
 *
 */
public enum IPDeleteErrorEnum {

    /** The code  error for delete ip. */
    ERROR_RIGHT_OWNERS("interestedParty.ip-delete-error-right-owner"),

    /** The error for delete ip. */
    ERROR_ALLOCATED("interestedParty.ip-delete-error-allocated"),
    
    /** The error for delete ip. */
    ERROR_AGREEMENT("interestedParty.ip-delete-error-agreement");

    /** The message code. */
    private String messageCode;



    /**
     * Instantiates a new  error enum.
     *
     * @param messageCode
     *            the message code
     */
    IPDeleteErrorEnum(String messageCode) {
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
