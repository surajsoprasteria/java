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
 * The Enum TitleTypeCodeEnum.
 */
public enum TitleTypeCodeEnum {

    /** The at. */
    AT("Alternative Title"),

    /** The te. */
    TE("First Line of Text"),

    /** The ft. */
    FT("Formal Title"),

    /** The it. */
    IT("Incorrect Title"),

    /** The ot. */
    OT("Original Title"),

    /** The tt. */
    TT("Original Title Translated"),

    /** The pt. */
    PT("Part Title"),

    /** The rt. */
    RT("Restricted Title"),

    /** The et. */
    ET("Extra Search Title"),

    /** The ol. */
    OL("Original Title with National Characters"),

    /** The al. */
    AL("Alternative Title with National Characters");

    /** The description. */
    private final String description;



    /**
     * Instantiates a new title type code enum.
     *
     * @param desctiption
     *            the desctiption
     */
    TitleTypeCodeEnum(String desctiption) {
        this.description = desctiption;
    }



    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }
}
