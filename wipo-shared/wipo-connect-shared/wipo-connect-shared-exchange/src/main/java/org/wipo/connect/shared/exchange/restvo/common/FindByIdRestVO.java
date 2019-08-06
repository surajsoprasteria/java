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
 * The Class FindByIdRestVO.
 */
public class FindByIdRestVO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2881303384280965565L;
    /** The id. */
    private Long id;
    
    /** The fetch document list. */
    private boolean fetchDocumentList;


    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return this.id;
    }



    /**
     * Sets the id.
     *
     * @param id
     *            the new id
     */
    public void setId(Long id) {
        this.id = id;
    }

	/**
	 * Checks if is fetch document list.
	 *
	 * @return true, if is fetch document list
	 */
	public boolean isFetchDocumentList() {
		return fetchDocumentList;
	}



	/**
	 * Sets the fetch document list.
	 *
	 * @param fetchDocumentList the new fetch document list
	 */
	public void setFetchDocumentList(boolean fetchDocumentList) {
		this.fetchDocumentList = fetchDocumentList;
	}
	
	

}
