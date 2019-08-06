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

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccValidationException;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.ConversionUtils;
import org.wipo.connect.shared.exchange.dto.impl.BIParameterFlat;
import org.wipo.connect.shared.exchange.dto.impl.BIResultSet;
import org.wipo.connect.shared.exchange.enumerator.QueryParameterTypeEnum;
import org.wipo.connect.shared.persistence.dao.BIDAO;
import org.wipo.connect.shared.persistence.mapping.BIMapper;

@Service
@Qualifier("biDAOImpl")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class BIDAOImpl implements BIDAO {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(BIDAOImpl.class);

    @Autowired
    private BIMapper biMapper;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Map<String, BIParameterFlat> findQueryParameters(String queryCode) throws WccException {
	String queryParameters = biMapper.findQueryParameters(queryCode);
	return populateParametersMap(queryParameters);
    }

    @Override
    public void deleteAllQuery() {
	biMapper.deleteAllQuery();
    }

    @Override
    public List<String> findQueryCodeList() {
	return biMapper.findQueryCodeList();
    }

    @Override
    public String findQuerySql(String queryCode) {
	return biMapper.findQuerySql(queryCode);
    }

    @Override
    public BIResultSet executeQuery(String querySql, Map<String, BIParameterFlat> paramObject) throws WccException {
	MapSqlParameterSource params = new MapSqlParameterSource();

	for (Map.Entry<String, BIParameterFlat> entry : paramObject.entrySet()) {
	    params.addValue(entry.getKey(), entry.getValue().getFormValue());
	}

	try {
	    List<Map<String, Object>> result = jdbcTemplate.query(querySql, params, new ResultMapper());

	    BIResultSet resultVO = new BIResultSet();
	    resultVO.setColumns(result.get(0));
	    result.remove(0);
	    resultVO.setData(result);

	    return resultVO;

	} catch (BadSqlGrammarException | InvalidDataAccessApiUsageException e) {
	    throw new WccValidationException("SQL query is not correct: " + e.getCause().getMessage());
	}

    }

    private static final class ResultMapper implements ResultSetExtractor<List<Map<String, Object>>> {
	@Override
	public List<Map<String, Object>> extractData(ResultSet rs) throws SQLException, DataAccessException {
	    List<Map<String, Object>> resultList = new ArrayList<>();
	    ResultSetMetaData meta = rs.getMetaData();

	    Map<String, Object> headerMap = new LinkedHashMap<>();
	    int colCount = meta.getColumnCount();

	    for (int col = 1; col <= colCount; col++) {
		String name = meta.getColumnLabel(col);
		Object value = meta.getColumnTypeName(col);
		headerMap.put(name, value);
	    }
	    resultList.add(headerMap);

	    if (rs.getType() != ResultSet.TYPE_FORWARD_ONLY) {
		rs.beforeFirst();
	    }
	    while (rs.next()) {
		Map<String, Object> rowMap = new LinkedHashMap<>();

		for (int col = 1; col <= colCount; col++) {
		    Object value = rs.getObject(col);
		    value = convertResult(value);
		    String name = meta.getColumnLabel(col);
		    rowMap.put(name, value);
		}
		resultList.add(rowMap);
	    }
	    return resultList;
	}

	private Object convertResult(Object value) {
	    if (value == null) {
		return null;
	    }

	    if (value instanceof Date || value instanceof Timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat(ConversionUtils.DATE_PATTERN_ISO8601);
		return sdf.format(value);
	    }

	    return value;
	}
    }

    @Override
    public void insertQuery(String queryName, String querySql, String queryParameter, String checksum) {
	biMapper.insertQuery(queryName, querySql, queryParameter, checksum);

    }

    @Override
    public boolean findExistChecksum(String checksum) {
	return biMapper.findExistChecksum(checksum);
    }

    private Map<String, BIParameterFlat> populateParametersMap(String queryParameters) throws WccValidationException {

	if (queryParameters == null) {
	    return null;
	}

	try {
	    Map<String, BIParameterFlat> parametersMap = new HashMap<String, BIParameterFlat>();

	    queryParameters = StringUtils.trimToEmpty(queryParameters);
	    for (String param : StringUtils.split(queryParameters, "|")) {
		param = StringUtils.trimToEmpty(param);
		if (StringUtils.isNotEmpty(param)) {
		    String[] fields = Arrays.stream(StringUtils.split(param, ":")).map(String::trim).toArray(String[]::new);
		    BIParameterFlat parameterFlat = new BIParameterFlat();
		    parameterFlat.setCode(fields[0]);
		    parameterFlat.setHtmlType(QueryParameterTypeEnum.valueOf(fields[1].toUpperCase()).getHtmlType());
		    parameterFlat.setType(QueryParameterTypeEnum.valueOf(fields[1].toUpperCase()).name());
		    parameterFlat.setDescription(fields[2]);
		    parametersMap.put(parameterFlat.getCode(), parameterFlat);
		}
	    }

	    return parametersMap;
	} catch (Exception e) {
	    LOGGER.error("Error: ", e);
	    throw new WccValidationException("BI query params are not valid");
	}
    }
}