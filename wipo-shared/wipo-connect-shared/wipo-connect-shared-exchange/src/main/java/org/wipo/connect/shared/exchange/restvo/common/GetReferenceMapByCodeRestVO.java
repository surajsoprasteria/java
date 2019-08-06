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

import org.wipo.connect.shared.exchange.enumerator.ReferenceTypeEnum;



/**
 * The Class GetReferenceMapByCodeRestVO.
 */
public class GetReferenceMapByCodeRestVO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8284906314618314105L;

    /** The code. */
    private ReferenceTypeEnum code;

    /**
     * Gets the code.
     *
     * @return the code
     */
    public ReferenceTypeEnum getCode() {
        return code;
    }

    /**
     * Sets the code.
     *
     * @param code the new code
     */
    public void setCode(ReferenceTypeEnum code) {
        this.code = code;
    }



}
