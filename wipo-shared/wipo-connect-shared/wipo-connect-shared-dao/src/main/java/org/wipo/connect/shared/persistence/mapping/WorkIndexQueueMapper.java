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
import org.wipo.connect.shared.exchange.dto.impl.WorkIndexQueue;
import org.wipo.connect.shared.exchange.enumerator.IndexQueueActionEnum;





/**
 * The Interface WorkIndexQueueMapper.
 */
public interface WorkIndexQueueMapper extends Mapper<WorkIndexQueue> {

    /**
     * Find work by action.
     *
     * @param action the action
     * @return the list
     */
    List<Long> findWorkByAction(@Param("action") IndexQueueActionEnum action);

    /**
     * Delete by work.
     *
     * @param idList the id list
     * @return the int
     */
    int deleteByWork(@Param("idList") List<Long> idList);

}
