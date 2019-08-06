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



package org.wipo.connect.shared.exchange.restvo.work;



import java.io.Serializable;


/**
 * The Class FindByIdRestVO.
 */
public class FindByIdRestVO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2881303384280965565L;

    /** The id work. */
    private Long idWork;
    
    /** The code. */
    private String code;
    
    /**
     * Gets the id work.
     *
     * @return the id work
     */
    public Long getIdWork() {
        return idWork;
    }
    
    /**
     * Sets the id work.
     *
     * @param idWork the new id work
     */
    public void setIdWork(Long idWork) {
        this.idWork = idWork;
    }
	
	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}
  

}
