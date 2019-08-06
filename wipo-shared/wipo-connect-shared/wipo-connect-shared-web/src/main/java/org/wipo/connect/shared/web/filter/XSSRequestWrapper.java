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
package org.wipo.connect.shared.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.wipo.connect.common.utils.XSSStripper;

/**
 * The Class XSSRequestWrapper.
 */
public class XSSRequestWrapper extends HttpServletRequestWrapper {

    /**
     * Instantiates a new XSS request wrapper.
     *
     * @param servletRequest the servlet request
     */
    public XSSRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);

        if (values == null) {
            return null;
        }

        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = XSSStripper.strip(values[i]);
        }

        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);

        return XSSStripper.strip(value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return XSSStripper.strip(value);
    }

}