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
package org.wipo.connect.common.dto.impl;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.collections.map.MultiKeyMap;

public class DynamicResultSet implements Serializable {

    private static final long serialVersionUID = -1331622868114843856L;

    private Map<String, Integer> headers = new LinkedHashMap<>();

    private Integer rows = 0;

    private MultiKeyMap map = MultiKeyMap.decorate(new LinkedMap());

    public DynamicResultSet(ResultSet rs) throws SQLException {
	init(rs);
    }

    private void put(Integer col, Integer row, Object value) {
	if (row < 0) {
	    throw new IllegalArgumentException("Row must be a positive number");
	}

	if (col < 0) {
	    throw new IllegalArgumentException("Col must be a positive number");
	}

	map.put(col.toString(), row.toString(), value);

	if (rows < (row + 1)) {
	    rows = row + 1;
	}
    }

    public Object get(String header, Integer row) {
	return get(headers.get(header), row);
    }

    public Object get(Integer col, Integer row) {
	// if(!headers.contains(header)){
	// throw new IllegalArgumentException("Invalid header: " + header);
	// }

	if (row < 0) {
	    throw new IllegalArgumentException("Row must be a positive number");
	}

	if (row >= rows) {
	    throw new IndexOutOfBoundsException("Row:" + row + " Size:" + rows);
	}

	Object value = map.get(col.toString(), row.toString());

	if (null != value) {
	    return value;
	} else {
	    return null;
	}
    }

    public Integer getRows() {
	return rows;
    }

    public List<String> getHeaders() {
	List<String> out = new ArrayList<>();
	out.addAll(headers.keySet());
	return out;
    }

    public Object[][] getValues() {
	int maxCols = headers.size();
	int maxRows = rows;
	Object[][] res = new Object[maxRows][maxCols];

	for (int c = 0; c < maxCols; c++) {
	    for (int r = 0; r < maxRows; r++) {
		res[r][c] = this.get(c, r);
	    }
	}

	return res;
    }

    private void init(ResultSet rs) throws SQLException {
	ResultSetMetaData md = rs.getMetaData();
	int columns = md.getColumnCount();
	int row = 0;

	for (int i = 1; i <= columns; ++i) {
	    headers.put(md.getColumnLabel(i), i - 1);
	}

	while (rs.next()) {
	    for (int i = 1; i <= columns; ++i) {
		put(i - 1, row, rs.getObject(i));
	    }
	    row++;
	}
    }

}
