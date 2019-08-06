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

import java.util.Collection;

import org.wipo.connect.shared.exchange.dto.impl.ClientInfo;
import org.wipo.connect.shared.persistence.Dao;

public interface ClientInfoDAO extends Dao<ClientInfo> {

    ClientInfo findByCode(String code);

    int updateClientKey(Long idClientInfo, String clientKey);

    int deleteClientPermissionByClient(Long idClientInfo);

    int insertClientPermission(Long idClientInfo, Collection<Long> sourceList);

    Boolean checkCodeUniqueness(String code, Long idClientInfo);

}
