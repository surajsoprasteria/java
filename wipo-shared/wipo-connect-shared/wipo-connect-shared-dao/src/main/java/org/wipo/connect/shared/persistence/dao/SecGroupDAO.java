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

import org.wipo.connect.shared.exchange.dto.impl.SecGroup;
import org.wipo.connect.shared.persistence.Dao;

public interface SecGroupDAO extends Dao<SecGroup> {

    SecGroup getSecGroupById(Long idSecGroup);

    List<SecGroup> getSecGroupList();

    List<SecGroup> findSecGroupByIdAccount(Long fkAccount);

}
