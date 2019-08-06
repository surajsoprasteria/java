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
 * The Enum InterestedPartyTypeEnum.
 */
public enum InterestedPartyTypeEnum {

    /** The n. */
    N("N", "Natural person"),

    /** The l. */
    L("L", "Legal entity");

    /** The code. */
    private final String code;

    /** The description. */
    private final String description;



    /**
     * Instantiates a new interested party type enum.
     *
     * @param code
     *            the code
     * @param description
     *            the description
     */
    InterestedPartyTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }



    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }



    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return this.code;
    }

}