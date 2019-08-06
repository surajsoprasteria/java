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



/**
 * The Class GetAccountByMailRestVO.
 */
public class GetAccountByMailRestVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -9139188606700834584L;
    
    /** The email. */
    private String email;



    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }



    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
