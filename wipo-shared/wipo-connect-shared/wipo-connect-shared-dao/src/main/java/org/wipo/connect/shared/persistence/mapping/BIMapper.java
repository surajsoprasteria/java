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

public interface BIMapper {

    String findQueryParameters(String queryCode);

    List<String> findQueryCodeList();

    String findQuerySql(String queryCode);

    void deleteAllQuery();

    void insertQuery(@Param("queryName") String queryName, @Param("querySql") String querySql, @Param("queryParameter") String queryParameter, @Param("checksum") String checksum);

    boolean findExistChecksum(@Param("checksum") String checksum);

}
