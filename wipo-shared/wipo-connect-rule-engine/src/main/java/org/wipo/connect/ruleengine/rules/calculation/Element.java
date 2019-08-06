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



package org.wipo.connect.ruleengine.rules.calculation;



import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;



/**
 * The Class Element.
 */
public class Element extends HashMap<String, Object> implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8802408568988993977L;

    /** The id. */
    private Serializable id;



    /**
     * Instantiates a new element.
     */
    public Element() {
        super();
    }



    /**
     * Instantiates a new element.
     *
     * @param map
     *            the map
     */
    public Element(Map<String, Object> map) {
        super(map);
    }



    /**
     * Instantiates a new element.
     *
     * @param attr
     *            the attr
     */
    public Element(Object... attr) {
        if (attr != null) {
            if (attr.length % 2 != 0) {
                throw new IllegalArgumentException(
                        "parameters should be couple of [String, Object]");
            }

            for (int i = 0; i < attr.length; i += 2) {
                Object key = attr[i];
                Object val = attr[i + 1];

                if (key == null || val == null) {
                    throw new NullPointerException("null values not allowed");
                }
                if (!(key instanceof String)) {
                    throw new IllegalArgumentException(
                            "parameters should be couple of [String, Object]");
                }

                put(key.toString(), val);
            }
        }
    }



    /**
     * Adds the.
     *
     * @param name
     *            the name
     * @param value
     *            the value
     */
    public void add(String name, Object value) {
        super.put(name, value);
    }




    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }



    /**
     * Gets the attributes.
     *
     * @return the attributes
     */
    public Map<String, Object> getAttributes() {
        return this;
    }



    /**
     * Gets the id.
     *
     * @return the id
     */
    public Serializable getId() {
        return this.id;
    }




    @Override
    public int hashCode() {
        return super.hashCode();
    }



    /**
     * Sets the attributes.
     *
     * @param map
     *            the map
     */
    public void setAttributes(Map<String, Object> map) {
        super.putAll(map);
    }



    /**
     * Sets the id.
     *
     * @param id
     *            the new id
     */
    public void setId(Serializable id) {
        this.id = id;
    }




    @Override
    public String toString() {
        return "[id:" + this.id + ", attributes:" + super.toString() + "]";
    }

}