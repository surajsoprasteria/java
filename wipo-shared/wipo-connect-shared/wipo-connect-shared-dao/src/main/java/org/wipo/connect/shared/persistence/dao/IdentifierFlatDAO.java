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

import org.wipo.connect.shared.exchange.dto.impl.IdentifierFlat;
import org.wipo.connect.shared.exchange.enumerator.IdentifierTypeEnum;

public interface IdentifierFlatDAO {

    IdentifierFlat find(Long id);

    IdentifierFlat findByCode(IdentifierTypeEnum code);

    List<IdentifierFlat> findAll();

    int insertIdentifier(IdentifierFlat identifier);

    void updateIdentifier(IdentifierFlat identifier);

    int deleteIdentifier(Long idIdentifier);

    Boolean exsistIdentifierCode(String code, Long idToExclude);

    Boolean isIdentifierUsedInAnyEntity(Long fkIdentifier);

    int insertIdentifierCreationClass(Long fkIdentifier, Long fkCreationClass);

}
