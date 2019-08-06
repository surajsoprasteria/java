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

package org.wipo.connect.shared.persistence.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.shared.exchange.dto.impl.Permission;
import org.wipo.connect.shared.persistence.BaseDAO;
import org.wipo.connect.shared.persistence.dao.PermissionDAO;
import org.wipo.connect.shared.persistence.mapping.PermissionMapper;

@Service
@Qualifier("permissionDAOImpl")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PermissionDAOImpl extends BaseDAO<Permission> implements PermissionDAO {

    /** The logger. */
    @SuppressWarnings("unused")
    private static Logger logger = WipoLoggerFactory.getLogger(PermissionDAOImpl.class);

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> selectPermissionsByIdGroup(Long idSecGroup) {
	return permissionMapper.selectPermissionsByIdGroup(idSecGroup);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public int insertSecGroupPermission(Long fkSecGroup, Long fkPermission) {
	return permissionMapper.insertSecGroupPermission(fkSecGroup, fkPermission);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public int deleteSecGroupPermission(Long fkSecGroup, Long fkPermission) {
	return permissionMapper.deleteSecGroupPermission(fkSecGroup, fkPermission);
    }

    @Override
    public int deleteAccountSecGroupByIdSecGroup(Long fkSecGroup) {
	return this.permissionMapper.deleteSecGroupPermissionByIdSecGroup(fkSecGroup);
    }
}
