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
 * The Enum ImportTransactionEnum.
 */
public enum ImportTransactionEnum {

    /** The insert operation. */
    INSERT("insert"),

    /** The update operation. */
    UPDATE("update"),

    /** The delete operation. */
    DELETE("delete");

    /** The value. */
    private final String value;



    /**
     * Instantiates a new operation enum.
     *
     * @param value
     *            the value
     */
    ImportTransactionEnum(String value) {
        this.value = value;
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
