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



package org.wipo.connect.shared.exchange.dto.impl;



import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Creatable;
import org.wipo.connect.common.dto.Deletable;
import org.wipo.connect.common.dto.Identifiable;
import org.wipo.connect.common.dto.Updatable;



/**
 * The Class Address.
 *
 * @author minervini
 */
public class Address extends BaseDTO implements Identifiable, Creatable,
        Updatable, Deletable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7785313727406869876L;

    /** The id address. */
    private Long idAddress;

    /** The line1. */
    private String line1;

    /** The line2. */
    private String line2;

    /** The line3. */
    private String line3;

    /** The city. */
    private String city;

    /** The province. */
    private String province;

    /** The postcode. */
    private String postcode;

    /** The address type. */
    private String addressType;

    /** The country code. */
    private String countryCode;

    /** The exec delete. */
    private Boolean execDelete = false;



    /**
     * Gets the address type.
     *
     * @return the address type
     */
    public String getAddressType() {
        return this.addressType;
    }



    /**
     * Gets the city.
     *
     * @return the city
     */
    public String getCity() {
        return this.city;
    }



    /**
     * Gets the country code.
     *
     * @return the country code
     */
    public String getCountryCode() {
        return this.countryCode;
    }




    @Override
    public Boolean getExecDelete() {
        return this.execDelete;
    }




    @Override
    public Long getId() {
        return this.idAddress;
    }



    /**
     * Gets the id address.
     *
     * @return the id address
     */
    public Long getIdAddress() {
        return this.idAddress;
    }



    /**
     * Gets the line1.
     *
     * @return the line1
     */
    public String getLine1() {
        return this.line1;
    }



    /**
     * Gets the line2.
     *
     * @return the line2
     */
    public String getLine2() {
        return this.line2;
    }



    /**
     * Gets the line3.
     *
     * @return the line3
     */
    public String getLine3() {
        return this.line3;
    }



    /**
     * Gets the postcode.
     *
     * @return the postcode
     */
    public String getPostcode() {
        return this.postcode;
    }



    /**
     * Gets the province.
     *
     * @return the province
     */
    public String getProvince() {
        return this.province;
    }



    /**
     * Sets the address type.
     *
     * @param addressType
     *            the new address type
     */
    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }



    /**
     * Sets the city.
     *
     * @param city
     *            the new city
     */
    public void setCity(String city) {
        this.city = city;
    }



    /**
     * Sets the country code.
     *
     * @param countryCode
     *            the new country code
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }




    @Override
    public void setExecDelete(Boolean execDelete) {
        this.execDelete = execDelete;
    }




    @Override
    public void setId(Long id) {
        this.idAddress = id;
    }



    /**
     * Sets the id address.
     *
     * @param idAddress
     *            the new id address
     */
    public void setIdAddress(Long idAddress) {
        this.idAddress = idAddress;
    }



    /**
     * Sets the line1.
     *
     * @param line1
     *            the new line1
     */
    public void setLine1(String line1) {
        this.line1 = line1;
    }



    /**
     * Sets the line2.
     *
     * @param line2
     *            the new line2
     */
    public void setLine2(String line2) {
        this.line2 = line2;
    }



    /**
     * Sets the line3.
     *
     * @param line3
     *            the new line3
     */
    public void setLine3(String line3) {
        this.line3 = line3;
    }



    /**
     * Sets the postcode.
     *
     * @param postcode
     *            the new postcode
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }



    /**
     * Sets the province.
     *
     * @param province
     *            the new province
     */
    public void setProvince(String province) {
        this.province = province;
    }




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
