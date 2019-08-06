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
package org.wipo.connect.common.import_reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.wipo.connect.common.import_model.EnvironmentEnum;
import org.wipo.connect.common.import_model.WorkHeaderFildConstant;
import org.wipo.connect.common.import_model.WorkRow;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.ExcelUtils;

public class WorkReaderExcel implements WorkReader {

    private Workbook workbook;
    private Sheet sheet;
    private Integer work_import_page_size;

    private Row headerRow;
    private String[] headerColumn;

    private Iterator<Row> itr;
    private WorkRow last;
    private EnvironmentEnum env;

    /** The logger. */
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(WorkReaderExcel.class);

    public WorkReaderExcel() throws IOException {

    }

    @Override
    public void initializeReader(String fileName, EnvironmentEnum env, Integer work_import_page_size) throws Exception {
	this.env = env;
	this.work_import_page_size = work_import_page_size;
	this.workbook = WorkbookFactory.create(new File(fileName), null, true);
	this.sheet = workbook.getSheetAt(0);
	this.itr = sheet.iterator();
	this.headerRow = sheet.getRow(0);

	headerColumn = new String[headerRow.getLastCellNum()];
	for (int cn = headerRow.getFirstCellNum(); cn < headerRow.getLastCellNum(); cn++) {
	    String header = headerRow.getCell(cn).getStringCellValue();
	    headerColumn[cn] = StringUtils.upperCase(header);
	}

    }

    @Override
    public synchronized boolean hasNextBlock() throws IOException {
	return itr.hasNext();
    }

    @Override
    public synchronized List<WorkRow> getBlockEntity() throws IOException {

	String idRow = StringUtils.EMPTY;
	String previousIdRow = null;
	int entityCount = 0;
	Row record = null;
	List<WorkRow> workRowList = new ArrayList<>();
	if (last != null) {
	    workRowList.add(last);
	    LOGGER.trace("WorkRow: {}", last);
	} else {
	    record = itr.next();
	}

	while (itr.hasNext()) {
	    record = itr.next();
	    WorkRow row = new WorkRow();
	    for (int cn = headerRow.getFirstCellNum(); cn < headerRow.getLastCellNum(); cn++) {
		if (StringUtils.equalsIgnoreCase(headerColumn[cn], WorkHeaderFildConstant.ID.name())) {
		    idRow = ExcelUtils.readCellValue(record, cn);

		    String rowCheck = isValidIdRow(idRow);
		    if (StringUtils.isNotEmpty(rowCheck)) {
			row.setErrorCode(rowCheck);
		    }

		    break;
		}
	    }
	    // if current row num is grater then last row num read
	    if (entityCount < work_import_page_size) {

		if (null != previousIdRow && !previousIdRow.equalsIgnoreCase(idRow)) {
		    entityCount++;
		}

		// if is the first row to read or if is same entity of previous row or entityCount<ip_import_page_size
		if (null == previousIdRow || entityCount < work_import_page_size) {
		    loadRow(row, record);
		    workRowList.add(row);
		    LOGGER.trace("WorkRow: {}", row);
		    previousIdRow = idRow;
		} else {
		    loadRow(row, record);
		    last = row;
		    break;
		}

	    }

	}

	return workRowList;
    }

    private String isValidIdRow(String rowId) {
	String code = StringUtils.EMPTY;
	if (StringUtils.isEmpty(rowId)) {
	    return code = "2";
	} else {
	    String id = StringUtils.remove(rowId, '\"');
	    id = StringUtils.trimToNull(id);
	    if (null != id) {
		try {
		    Long.parseLong(id);
		} catch (NumberFormatException e) {
		    return code = "1";
		}
	    } else {
		return code = "2";
	    }
	}

	return code;
    }

    private void loadRow(WorkRow row, Row record) {
	for (int cn = headerRow.getFirstCellNum(); cn < headerRow.getLastCellNum(); cn++) {
	    String value = ExcelUtils.readCellValue(record, cn);
	    WorkHeaderFildConstant cellName = EnumUtils.getEnum(WorkHeaderFildConstant.class, headerColumn[cn]);
	    loadWork(row, cellName, value);
	}
    }

    private void loadWork(WorkRow row, WorkHeaderFildConstant cellName, String cellValue) {
	if (null != cellName) {
	    switch (cellName) {
		case ID:
		    row.setId(cellValue);
		    break;
		case ROW_TYPE:
		    row.setRowType(cellValue);
		    break;
		case TRANSACTION:
		    row.setTransaction(cellValue);
		    break;
		case WORK_MAIN_ID:
		    row.setWorkMainId(cellValue);
		    break;
		case WORK_TITLE:
		    row.setWorkTitle(cellValue);
		    break;
		case DURATION:
		    row.setDuration(cellValue);
		    break;
		case GENRE:
		    row.setGenre(cellValue);
		    break;
		case INSTRUMENT:
		    row.setInstrument(cellValue);
		    break;
		case TYPE:
		    row.setType(cellValue);
		    break;
		case VALUE:
		    row.setValue(cellValue);
		    break;
		case PERFORMER:
		    row.setPerformer(cellValue);
		    break;
		case TERRITORY:
		    row.setTerritory(cellValue);
		    break;
		case NAME_MAIN_ID:
		    row.setNameMainId(cellValue);
		    break;
		case ROLE:
		    row.setRole(cellValue);
		    break;
		case CREATOR_REF:
		    row.setCreatorRef(cellValue);
		    break;
		case RIGHT_CATEGORY:
		    row.setRightCategory(cellValue);
		    break;
		case CREATION_CLASS:
		    row.setCreationClass(cellValue);
		    break;
		case CATALOGUE_NUMBER:
		    row.setCatalogueNumber(cellValue);
		    break;
		case SUPPORT:
		    row.setSupport(cellValue);
		    break;
		case COUNTRY_OF_PRODUCTION:
		    row.setCountryOfProduction(cellValue);
		    break;
		case CATEGORY:
		    row.setCategory(cellValue);
		    break;
		case LABEL:
		    row.setLabel(cellValue);
		    break;
		case LANGUAGE:
		    row.setLanguage(cellValue);
		    break;
		case WEIGHT:
		    row.setWeight(cellValue);
		    break;
		case TAGS:
		    if (env.equals(EnvironmentEnum.LOCAL)) {
			row.setTags(new ArrayList<>(Arrays.asList(StringUtils.defaultString(cellValue).split("[,|]"))));
		    }
		    break;
		default:
		    break;
	    }
	}
    }

    @Override
    public void closeReader() throws IOException {
	this.workbook.close();
    }

}
