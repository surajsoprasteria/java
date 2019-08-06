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



package org.wipo.connect.ruleengine.test.model;

import java.io.Serializable;

public class DynamicField implements Serializable{
	private static final long serialVersionUID = -3701723783057704233L;

	private final String name;
    private final FieldTypeEnum type;
    private String strValue;



    public DynamicField(String name,
            FieldTypeEnum type,
            Object value) {

        super();
        this.name = name;
        this.type = type;
        this.setValue(value);
    }



    public String getName() {
        return name;
    }



    public FieldTypeEnum getType() {
        return type;
    }



    public String getStrValue() {
        return strValue;
    }



    public Object getValue() {
        Object objOut;
        if (strValue == null) {
            objOut = null;
        } else {
            switch (type) {
            case DOUBLE:
                objOut = Double.parseDouble(strValue);
                break;
            case INTEGER:
                objOut = Integer.parseInt(strValue, 10);
                break;
            case STRING:
                objOut = strValue;
                break;
            default:
                throw new IllegalStateException("Unknown type " + type);
            }
        }

        return objOut;

    }



    public void setValue(Object value) {

        if (value == null) {
            strValue = null;
        } else {
            switch (type) {
            case DOUBLE:
                strValue = value.toString();
                break;
            case INTEGER:
                strValue = value.toString();
                break;
            case STRING:
                strValue = value.toString();
                break;
            default:
                throw new IllegalStateException("Unknown type " + type);
            }
        }

    }
}
