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
import org.wipo.connect.shared.exchange.dto.impl.IdentifierManager;

public interface IdentifierManagerMapper {

    IdentifierManager selectAndLock(@Param("refTable") String refTable);

    int update(@Param("refTable") String refTable, @Param("newVal") Long newVal);

    int createNewCounter(@Param("refTable") String refTable);

    boolean checkIdUniqueness(@Param("refTable") String refTable, @Param("mainId") String mainId, @Param("idsToExclude") List<Long> idsToExclude);

}
