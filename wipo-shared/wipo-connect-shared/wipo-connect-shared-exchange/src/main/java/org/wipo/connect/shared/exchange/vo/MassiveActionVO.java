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




/**
 * The Class MassiveActionVO.
 */
public class MassiveActionVO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5017759197434033609L;

    /** The id list. */
    private List<Long> idList;
    
    /** The domain list. */
    private List<String> domainList;

    /**
     * Gets the id list.
     *
     * @return the id list
     */
    public List<Long> getIdList() {
        if(idList == null){
            idList = new ArrayList<Long>();
        }
        return idList;
    }

    /**
     * Sets the id list.
     *
     * @param idList the new id list
     */
    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    /**
     * Gets the domain list.
     *
     * @return the domain list
     */
    public List<String> getDomainList() {
        if(domainList == null){
            domainList = new ArrayList<String>();
        }
        return domainList;
    }

    /**
     * Sets the domain list.
     *
     * @param domainList the new domain list
     */
    public void setDomainList(List<String> domainList) {
        this.domainList = domainList;
    }


}
