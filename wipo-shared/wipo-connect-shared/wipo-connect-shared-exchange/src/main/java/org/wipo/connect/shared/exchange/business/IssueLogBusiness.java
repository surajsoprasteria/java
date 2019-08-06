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

package org.wipo.connect.shared.exchange.business;

import java.util.List;

import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.logging.IssueLog;

public interface IssueLogBusiness {

    void saveLog(IssueLog dto);

    List<IssueLog> findAll() throws WccException;

    IssueLog find(Long idIssueLog) throws WccException;

    void deleteAll() throws WccException;

    void removeOldIssues() throws WccException;
}
