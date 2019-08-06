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



package org.wipo.connect.shared.persistence.mapping;



import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.wipo.connect.shared.exchange.dto.impl.IpIndexQueue;
import org.wipo.connect.shared.exchange.enumerator.IndexQueueActionEnum;






/**
 * The Interface IpIndexQueueMapper.
 */
public interface IpIndexQueueMapper extends Mapper<IpIndexQueue> {

    /**
     * Find ip by action.
     *
     * @param action the action
     * @return the list
     */
    List<Long> findIpByAction(@Param("action") IndexQueueActionEnum action);

    /**
     * Delete by ip.
     *
     * @param idList the id list
     * @return the int
     */
    int deleteByIp(@Param("idList") List<Long> idList);

}
