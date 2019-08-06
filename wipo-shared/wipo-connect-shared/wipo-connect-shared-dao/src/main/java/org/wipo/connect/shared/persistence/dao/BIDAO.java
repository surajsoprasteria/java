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
import java.util.Map;

import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.shared.exchange.dto.impl.BIParameterFlat;
import org.wipo.connect.shared.exchange.dto.impl.BIResultSet;

public interface BIDAO {

    Map<String, BIParameterFlat> findQueryParameters(String selectedQueryCode) throws WccException;

    List<String> findQueryCodeList();

    String findQuerySql(String queryCode);

    BIResultSet executeQuery(String querySql, Map<String, BIParameterFlat> paramObject) throws WccException;

    void deleteAllQuery();

    void insertQuery(String queryName, String querySql, String queryParameter, String checksum);

    boolean findExistChecksum(String checksum);

}
