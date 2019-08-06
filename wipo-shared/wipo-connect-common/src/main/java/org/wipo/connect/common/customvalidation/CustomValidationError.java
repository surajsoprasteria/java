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



package org.wipo.connect.common.customvalidation;



import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * The Class CustomValidationError.
 */
@SuppressWarnings({"squid:S1948","squid:S1165"})
public class CustomValidationError implements Serializable {

    private static final long serialVersionUID = 1617038473242170309L;


    private String fieldCode;
    private String field;
    private CustomValidationErrorTypeEnum type;
    private Boolean forced;
    private Object data;
    private Object value;







    /**
     * Instantiates a new custom validation error.
     *
     * @param field the field
     * @param fieldCode the field code
     * @param type the type
     * @param forced the forced
     * @param data the data
     * @param value the value
     */
    public CustomValidationError(String field, 
            String fieldCode,
            CustomValidationErrorTypeEnum type,
            Boolean forced,
            Object data,
            Object value) {
        super();
        this.type = type;
        this.data = data;
        this.value = value;
        this.field = field;
        this.fieldCode = fieldCode;
        this.forced = forced;
    }

    /**
     * Instantiates a new custom validation error.
     *
     * @param field the field
     * @param fieldCode the field code
     * @param type the type
     * @param data the data
     * @param value the value
     */
    public CustomValidationError(String field,
            String fieldCode,
            CustomValidationErrorTypeEnum type,
            Object data,
            Object value) {
        super();
        this.type = type;
        this.data = data;
        this.value = value;
        this.field = field;
        this.fieldCode = fieldCode;
    }

    /**
     * Instantiates a new custom validation error.
     *
     * @param field the field
     * @param fieldCode the field code
     * @param type the type
     * @param value the value
     */
    public CustomValidationError(String field,
            String fieldCode,
            CustomValidationErrorTypeEnum type,
            Object value) {
        super();
        this.type = type;
        this.value = value;
        this.field = field;
        this.fieldCode = fieldCode;
    }

    /**
     * Instantiates a new custom validation error.
     *
     * @param field the field
     * @param fieldCode the field code
     * @param type the type
     */
    public CustomValidationError(String field,
            String fieldCode,
            CustomValidationErrorTypeEnum type) {
        super();
        this.type = type;
        this.field = field;
        this.fieldCode = fieldCode;
    }



    /**
     * Gets the type.
     *
     * @return the type
     */
    public CustomValidationErrorTypeEnum getType() {
        return type;
    }



    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(CustomValidationErrorTypeEnum type) {
        this.type = type;
    }



    /**
     * Gets the data.
     *
     * @return the data
     */
    public Object getData() {
        return data;
    }



    /**
     * Sets the data.
     *
     * @param data the new data
     */
    public void setData(Object data) {
        this.data = data;
    }



    /**
     * Gets the value.
     *
     * @return the value
     */
    public Object getValue() {
        return value;
    }



    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(Object value) {
        this.value = value;
    }



    /**
     * Gets the field.
     *
     * @return the field
     */
    public String getField() {
        return field;
    }



    /**
     * Sets the field.
     *
     * @param field the new field
     */
    public void setField(String field) {
        this.field = field;
    }



    /**
     * Gets the field code.
     *
     * @return the field code
     */
    public String getFieldCode() {
        return fieldCode;
    }



    /**
     * Sets the field code.
     *
     * @param fieldCode the new field code
     */
    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }


    @Override
    public String toString(){
        return ReflectionToStringBuilder.toString(this,ToStringStyle.JSON_STYLE);
    }

    /**
     * Gets the forced.
     *
     * @return the forced
     */
    public Boolean getForced() {
        return forced;
    }

    /**
     * Sets the forced.
     *
     * @param forced the new forced
     */
    public void setForced(Boolean forced) {
        this.forced = forced;
    }

}
