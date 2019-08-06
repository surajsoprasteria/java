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
import org.wipo.connect.shared.exchange.dto.impl.Permission;

public interface PermissionMapper extends Mapper<Permission> {

    List<Permission> selectPermissionsByIdGroup(@Param("idSecGroup") Long idSecGroup);

    int insertSecGroupPermission(@Param("fkSecGroup") Long fkSecGroup, @Param("fkPermission") Long fkPermission);

    int deleteSecGroupPermission(@Param("fkSecGroup") Long fkSecGroup, @Param("fkPermission") Long fkPermission);

    int deleteSecGroupPermissionByIdSecGroup(@Param("fkSecGroup") Long fkSecGroup);

}
