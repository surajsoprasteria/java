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

import org.apache.ibatis.annotations.Param;
import org.wipo.connect.shared.exchange.dto.impl.ClientInfo;

public interface ClientInfoMapper extends Mapper<ClientInfo> {

    ClientInfo findByCode(@Param("code") String code);

    int updateClientKey(@Param("idClientInfo") Long idClientInfo, @Param("clientKey") String clientKey);

    int insertClientPermission(@Param("idClientInfo") Long idClientInfo, @Param("idCmo") Long idCmo);

    int deleteClientPermissionByClient(@Param("idClientInfo") Long idClientInfo);

    Boolean checkCodeUniqueness(@Param(value = "code") String code, @Param(value = "idToExclude") Long idToExclude);

}
