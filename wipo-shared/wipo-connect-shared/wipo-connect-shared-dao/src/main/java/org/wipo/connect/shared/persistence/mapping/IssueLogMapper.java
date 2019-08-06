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

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.wipo.connect.common.logging.IssueLog;

public interface IssueLogMapper {

    int insert(IssueLog dto);

    List<IssueLog> findAll();

    int deleteAll();

    IssueLog find(@Param("idIssueLog") Long idIssueLog);

    int deleteOld(@Param("minDate") Date minDate);

}
