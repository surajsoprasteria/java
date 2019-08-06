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
 * The Enum GenderTypeEnum.
 */
public enum GenderTypeEnum {

    /** The m. */
    M("Male"),

    /** The f. */
    F("Female");

    /** The value. */
    private final String value;



    /**
     * Instantiates a new gender type enum.
     *
     * @param value
     *            the value
     */
    GenderTypeEnum(String value) {
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
