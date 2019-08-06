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
import java.util.Map;

import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.shared.exchange.dto.impl.BIParameterFlat;
import org.wipo.connect.shared.exchange.dto.impl.BISearch;
import org.wipo.connect.shared.exchange.utils.importexport.ExportBean;
import org.wipo.connect.shared.exchange.vo.BIResultSetVO;

public interface BIBusiness {

    Map<String, BIParameterFlat> getQueryParameters(String selectedQueryCode) throws WccException;

    List<String> getQueryCodeList() throws WccException;

    BIResultSetVO executeQuery(BISearch vo) throws WccException;

    ExportBean exportResults(BISearch vo) throws WccException;

}