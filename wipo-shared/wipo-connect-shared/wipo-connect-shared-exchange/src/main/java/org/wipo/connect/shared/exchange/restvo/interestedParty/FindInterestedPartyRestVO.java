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



package org.wipo.connect.shared.exchange.restvo.interestedParty;



import java.io.Serializable;

import org.wipo.connect.shared.exchange.vo.InterestedPartySearchVO;



/**
 * The Class FindInterestedPartyRestVO.
 */
public class FindInterestedPartyRestVO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8284906314618314105L;

    /** The search VO. */
    private InterestedPartySearchVO searchVO;

    /**
     * Gets the search VO.
     *
     * @return the search VO
     */
    public InterestedPartySearchVO getSearchVO() {
        return searchVO;
    }

    /**
     * Sets the search VO.
     *
     * @param searchVO the new search VO
     */
    public void setSearchVO(InterestedPartySearchVO searchVO) {
        this.searchVO = searchVO;
    }

    
    
}
