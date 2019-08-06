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



package org.wipo.connect.ruleengine.rules.model;



import java.math.BigDecimal;
import java.util.Date;



/**
 * The Class DataType.
 */
public class DataType {

    /** The Constant TEXT. */
    public static final DataType TEXT = new DataType(String.class);

    /** The Constant INTEGER. */
    public static final DataType INTEGER = new DataType(Integer.class);

    /** The Constant DOUBLE. */
    public static final DataType DOUBLE = new DataType(Double.class);

    /** The Constant LONG. */
    public static final DataType LONG = new DataType(Long.class);

    /** The Constant BIGDECIMAL. */
    public static final DataType BIGDECIMAL = new DataType(BigDecimal.class);

    /** The Constant PERCENT. */
    public static final DataType PERCENT = new DataType(BigDecimal.class);

    /** The Constant AMOUNT. */
    public static final DataType AMOUNT = new DataType(BigDecimal.class);

    /** The Constant DATE. */
    public static final DataType DATE = new DataType(Date.class);

    /** The Constant BOOL. */
    public static final DataType BOOL = new DataType(Boolean.class);

    /** The Constant OBJECT. */
    public static final DataType OBJECT = new DataType(Object.class);

    /** The type reference. */
    Class<?> typeReference;



    /**
     * Instantiates a new data type.
     *
     * @param typeReference
     *            the type reference
     */
    public DataType(Class<?> typeReference) {
        this.typeReference = typeReference;
    }



    /**
     * Gets the type reference.
     *
     * @return the type reference
     */
    public Class<?> getTypeReference() {
        return this.typeReference;
    }

}
