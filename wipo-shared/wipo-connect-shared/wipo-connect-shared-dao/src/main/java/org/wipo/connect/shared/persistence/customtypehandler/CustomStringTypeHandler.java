/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Copyright Connection (WCC).
 *
 *
 * @author Fincons
 *
 */
package org.wipo.connect.shared.persistence.customtypehandler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;


@MappedJdbcTypes( value=JdbcType.VARCHAR ,includeNullJdbcType = true)
@MappedTypes(String.class)
public class CustomStringTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(java.sql.PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
	ps.setString(i,StringUtils.trimToNull(parameter));

    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
	return rs.getString(columnName);
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
	 return rs.getString(columnIndex);
    }

    @Override
    public String getNullableResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
	 return cs.getString(columnIndex);
    }

}