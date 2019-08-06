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
package org.wipo.connect.shared.persistence.dao;

import java.util.List;

import org.wipo.connect.shared.exchange.dto.impl.WorkIndexQueue;
import org.wipo.connect.shared.exchange.enumerator.IndexQueueActionEnum;


/**
 * The Interface WorkIndexQueueDAO.
 */
public interface WorkIndexQueueDAO {

    /**
     * Find work by action.
     *
     * @param action the action
     * @return the list
     */
    List<Long> findWorkByAction(IndexQueueActionEnum action);

    /**
     * Delete by work.
     *
     * @param idList the id list
     * @return the int
     */
    int deleteByWork(List<Long> idList);

    /**
     * Insert.
     *
     * @param dto the dto
     * @return the int
     */
    int insert(WorkIndexQueue dto);

    /**
     * Find.
     *
     * @param id the id
     * @return the work index queue
     */
    WorkIndexQueue find(Long id);

}
