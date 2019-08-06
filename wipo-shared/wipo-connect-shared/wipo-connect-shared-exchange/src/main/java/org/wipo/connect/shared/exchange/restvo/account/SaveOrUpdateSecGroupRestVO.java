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

import org.wipo.connect.shared.exchange.dto.impl.SecGroup;



/**
 * The Class SaveOrUpdateSecGroupRestVO.
 */
public class SaveOrUpdateSecGroupRestVO implements Serializable {

    private static final long serialVersionUID = -7561154206425489726L;

    private SecGroup  dto;



    /**
     * Gets the dto.
     *
     * @return the dto
     */
    public SecGroup getDto() {
        return this.dto;
    }



    /**
     * Sets the dto.
     *
     * @param dto the new dto
     */
    public void setDto(SecGroup dto) {
        this.dto = dto;
    }

}
