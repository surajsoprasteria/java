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

import org.wipo.connect.shared.exchange.dto.impl.Permission;
import org.wipo.connect.shared.persistence.Dao;

public interface PermissionDAO extends Dao<Permission> {

    List<Permission> selectPermissionsByIdGroup(Long idSecGroup);

    int insertSecGroupPermission(Long fkSecGroup, Long fkPermission);

    int deleteSecGroupPermission(Long fkSecGroup, Long fkPermission);

    int deleteAccountSecGroupByIdSecGroup(Long fkSecGroup);
}
