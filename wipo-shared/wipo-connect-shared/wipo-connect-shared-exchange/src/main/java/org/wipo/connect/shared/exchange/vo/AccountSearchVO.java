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



package org.wipo.connect.shared.exchange.vo;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Identifiable;



/**
 * The Class AccountSearchVO.
 */
public class AccountSearchVO implements Identifiable, Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6130449831080434475L;

    /** The id account. */
    private Long idAccount;

    /** The email. */
    private String email;

    /** The type. */
    private String type;

    /** The active. */
    private Boolean active;

    /** The wcc id. */
    private String wccId;

    private List<Long> idsAccount;



    /**
     * Gets the active.
     *
     * @return the active
     */
    public Boolean getActive() {
        return this.active;
    }



    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }




    @Override
    public Long getId() {
        return getIdAccount();
    }



    /**
     * Gets the id account.
     *
     * @return the id account
     */
    public Long getIdAccount() {
        return this.idAccount;
    }

    /**
     * Gets the wcc id.
     *
     * @return the wcc id
     */
    public String getWccId() {
        return this.wccId;
    }



    /**
     * Sets the active.
     *
     * @param active
     *            the new active
     */
    public void setActive(Boolean active) {
        this.active = active;
    }



    /**
     * Sets the email.
     *
     * @param email
     *            the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }




    @Override
    public void setId(Long id) {
        setIdAccount(id);
    }



    /**
     * Sets the id account.
     *
     * @param idAccount
     *            the new id account
     */
    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }




    /**
     * Sets the wcc id.
     *
     * @param wccId
     *            the new wcc id
     */
    public void setWccId(String wccId) {
        this.wccId = wccId;
    }




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }





	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}



	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}



	/**
	 * Gets the ids account.
	 *
	 * @return the ids account
	 */
	public List<Long> getIdsAccount() {
		if(idsAccount==null){
			idsAccount=new ArrayList<>();
		}
		return idsAccount;
	}



	/**
	 * Sets the ids account.
	 *
	 * @param idsAccount the new ids account
	 */
	public void setIdsAccount(List<Long> idsAccount) {
		this.idsAccount = idsAccount;
	}



}
