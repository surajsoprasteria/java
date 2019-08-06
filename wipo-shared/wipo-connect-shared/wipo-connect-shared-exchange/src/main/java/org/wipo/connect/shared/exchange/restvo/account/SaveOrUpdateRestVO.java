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



package org.wipo.connect.shared.exchange.restvo.account;



import java.io.Serializable;

import org.wipo.connect.shared.exchange.dto.impl.Account;



/**
 * The Class SaveOrUpdateRestVO.
 */
public class SaveOrUpdateRestVO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7561154206425489726L;

    /** The dto. */
    private Account dto;



    /**
     * Gets the dto.
     *
     * @return the dto
     */
    public Account getDto() {
        return this.dto;
    }



    /**
     * Sets the dto.
     *
     * @param dto the new dto
     */
    public void setDto(Account dto) {
        this.dto = dto;
    }

}
