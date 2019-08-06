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



import java.util.HashMap;
import java.util.Map;



/**
 * The Enum NameTypeEnum.
 */
public enum NameTypeEnum {

	/** The pa. */
    PA("Patronym"),

    /** The pp. */
    PP("Pseudonym"),

    /** The pg. */
    PG("Group Pseudonym"),
    
    /** The no. */
    MO("Name Modification"),
    
    /** The different spelling. */
    DF("Different Spelling"),
    
    /**  The standardized spelling. */
    ST("Standardized Spelling"),

    /** The or. */
    OR("Other Reference"),
    
    /** The hr. */
    HR("Holding Reference");

    /**
     * Gets the as map.
     *
     * @return the as map
     */
    public static Map<String, String> getAsMap() {
        Map<String, String> map = new HashMap<>();

        for (NameTypeEnum auxEnum : NameTypeEnum.values()) {
            map.put(auxEnum.toString(), auxEnum.getValue());
        }

        return map;
    }

    /** The value. */
    private final String value;



    /**
     * Instantiates a new name type enum.
     *
     * @param value
     *            the value
     */
    NameTypeEnum(String value) {
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
