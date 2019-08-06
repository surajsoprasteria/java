/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Copyright Connection (WCC).
 *
 *
 * @author Fincons
 *
 */
package org.wipo.connect.shared.persistence.dao;

import java.util.List;

import org.wipo.connect.shared.exchange.dto.impl.IpIndexQueue;
import org.wipo.connect.shared.exchange.enumerator.IndexQueueActionEnum;



/**
 * The Interface IpIndexQueueDAO.
 */
public interface IpIndexQueueDAO {

    /**
     * Find ip by action.
     *
     * @param action the action
     * @return the list
     */
    List<Long> findIpByAction(IndexQueueActionEnum action);

    /**
     * Delete by ip.
     *
     * @param idList the id list
     * @return the int
     */
    int deleteByIp(List<Long> idList);

    /**
     * Insert.
     *
     * @param dto the dto
     * @return the int
     */
    int insert(IpIndexQueue dto);

    /**
     * Find.
     *
     * @param id the id
     * @return the ip index queue
     */
    IpIndexQueue find(Long id);

}
