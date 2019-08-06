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



package org.wipo.connect.shared.exchange.restvo.common;



import java.io.Serializable;



/**
 * The Class GetCustomValidationListBySectionRestVO.
 */
public class GetCustomValidationListBySectionRestVO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3463812304445691536L;

    /** The section code. */
    private String sectionCode;


    /**
     * Gets the section code.
     *
     * @return the section code
     */
    public String getSectionCode() {
        return sectionCode;
    }

    /**
     * Sets the section code.
     *
     * @param sectionCode the new section code
     */
    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

}
