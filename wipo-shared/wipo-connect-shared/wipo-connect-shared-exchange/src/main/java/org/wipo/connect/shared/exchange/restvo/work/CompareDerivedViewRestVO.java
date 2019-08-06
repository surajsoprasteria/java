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

import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskDetail;



/**
 * The Class CompareDerivedViewRestVO.
 */
public class CompareDerivedViewRestVO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7100766931394397072L;

    /** The work 1. */
    private Work work1;
    
    /** The work 2. */
    private WorkTaskDetail work2;

    /**
     * Gets the work 1.
     *
     * @return the work 1
     */
    public Work getWork1() {
        return work1;
    }
    
    /**
     * Sets the work 1.
     *
     * @param work1 the new work 1
     */
    public void setWork1(Work work1) {
        this.work1 = work1;
    }
    
    /**
     * Gets the work 2.
     *
     * @return the work 2
     */
    public WorkTaskDetail getWork2() {
        return work2;
    }
    
    /**
     * Sets the work 2.
     *
     * @param work2 the new work 2
     */
    public void setWork2(WorkTaskDetail work2) {
        this.work2 = work2;
    }

}
