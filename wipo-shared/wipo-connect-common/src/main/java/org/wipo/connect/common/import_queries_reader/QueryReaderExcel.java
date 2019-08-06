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
package org.wipo.connect.common.import_queries_reader;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.wipo.connect.common.exception.WccValidationException;
import org.wipo.connect.common.utils.ExcelUtils;

public class QueryReaderExcel implements QueryReader {

    private Workbook workbook;

    private Sheet sheet;

    public QueryReaderExcel() {

    }

    @Override
    public void initializeReader(String fileName) throws Exception {
	workbook = WorkbookFactory.create(new File(fileName), null, true);
	sheet = workbook.getSheetAt(0);
    }

    @Override
    public void initializeReader(byte[] file) throws Exception {
	ByteArrayInputStream is = new ByteArrayInputStream(file);
	workbook = WorkbookFactory.create(is);
	sheet = workbook.getSheetAt(0);
    }

    @Override
    public synchronized List<QueryRow> loadQueryList() throws Exception {

	Iterator<Row> itr = sheet.rowIterator();
	Map<String, QueryRow> queryList = new LinkedHashMap<>();
	Row record = itr.next();
	while (itr.hasNext()) {
	    record = itr.next();
	    QueryRow row = loadQueryRow(record);
	    if (queryList.containsKey(row.getQueryName())) {
		throw new WccValidationException("Duplicate query name in BI import file");
	    }
	    queryList.put(row.getQueryName(), row);
	}
	return new ArrayList<>(queryList.values());

    }

    private QueryRow loadQueryRow(Row record) {
	QueryRow row = new QueryRow();
	Row headerRow = sheet.getRow(0);
	for (int cn = headerRow.getFirstCellNum(); cn < headerRow.getLastCellNum(); cn++) {
	    String header = ExcelUtils.readCellValue(headerRow, cn);
	    String value = ExcelUtils.readCellValue(record, cn);
	    QueryHeaderFildEnum cellName = EnumUtils.getEnum(QueryHeaderFildEnum.class, StringUtils.upperCase(header));
	    loadQueryColumn(row, cellName, value);
	}
	return row;
    }

    private void loadQueryColumn(QueryRow row, QueryHeaderFildEnum cellName, String cellValue) {
	if (null != cellName) {
	    switch (cellName) {
		case QUERY_NAME:
		    row.setQueryName(cellValue);
		    break;
		case QUERY_SQL:
		    row.setQuerySql(cellValue);
		    break;
		case QUERY_PARAMETERS:
		    row.setQueryParameter(cellValue);
		    break;
		default:
		    break;
	    }
	}
    }

    @Override
    public void closeReader() throws IOException {
	this.sheet = null;
	this.workbook.close();
	this.workbook = null;
    }

}
