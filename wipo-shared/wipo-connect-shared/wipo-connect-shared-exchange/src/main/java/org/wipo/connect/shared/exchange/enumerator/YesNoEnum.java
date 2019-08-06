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
 * The Enum YesNoEnum.
 */
public enum YesNoEnum {

    /** The yes. */
	YES(true, "Yes"),

    /** The no. */
	NO(false, "No");

    /** The value. */
    private final Boolean value;

    private final String label;

    /**
     * Instantiates a new yes no enum.
     *
     * @param value
     *            the value
     */
    YesNoEnum(Boolean value, String label) {
        this.value = value;
        this.label = label;
    }



    /**
     * Gets the value.
     *
     * @return the value
     */
    public Boolean getValue() {
        return this.value;
    }
    
    public String getLabel() {
		return label;
	}

}
