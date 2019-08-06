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
import org.wipo.connect.shared.exchange.dto.impl.ClientInfo;

/**
 * The ExternalSiteBusiness interface provides business common methods orchestrating the functions made available by the layer DAO.
 *
 */
public interface DataAccessBusiness {

    List<ClientInfo> findAll() throws WccException;

    ClientInfo findById(Long idClientInfo) throws WccException;

    ClientInfo insertOrUpdate(ClientInfo clientInfo) throws WccException;

    void delete(Long idClientInfo) throws WccException;

    Boolean checkCodeUniqueness(String code, Long idClientInfo) throws WccException;

}
