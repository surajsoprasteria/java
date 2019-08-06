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
 * The Enum SpecialCmoEnum.
 */
public enum SpecialCmoEnum {
	
	/**
	 * The cmo000.
	 */
	CMO000("000"),
	
	/**
	 * The cmo099.
	 */
	CMO099("099"),
	
	/**
	 * The cmo999.
	 */
	CMO999("999");

    /** The value. */
    private final String value;


    /**
     * Instantiates a new special cmo enum.
     *
     * @param value the value
     */
    SpecialCmoEnum(String value) {
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
