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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.wipo.connect.common.import_model.EnvironmentEnum;
import org.wipo.connect.common.import_model.WorkHeaderFildConstant;
import org.wipo.connect.common.import_model.WorkRow;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.enumerator.WccWorkImportExceptionCodeEnum;

/**
 *
 * @author pasquale.minervini
 *
 */
public class WorkReaderCsv implements WorkReader {

    /** The csv file parser. */
    private CSVParser csvFileParser = null;

    private Integer work_import_page_size;

    private Iterator<CSVRecord> itr;
    private WorkRow last;

    private EnvironmentEnum env;
    /** The logger. */
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(WorkReaderCsv.class);

    public WorkReaderCsv() throws IOException {

    }

    @Override
    public void initializeReader(String fileName, EnvironmentEnum env, Integer work_import_page_size) throws IOException {
	this.env = env;
	this.work_import_page_size = work_import_page_size;
	BufferedReader br = new BufferedReader(new FileReader(fileName));
	LOGGER.debug("Inizializing parser reading file: {}", fileName);
	csvFileParser = CSVFormat.DEFAULT.withHeader().withIgnoreHeaderCase().parse(br);

	itr = csvFileParser.iterator();

    }

    @Override
    public synchronized boolean hasNextBlock() throws IOException {

	return itr.hasNext();
    }

    @Override
    public synchronized List<WorkRow> getBlockEntity() throws IOException {

	String idRow;
	String previousIdRow = null;
	int entityCount = 0;
	List<WorkRow> workRowList = new ArrayList<>();
	if (last != null) {
	    workRowList.add(last);
	    LOGGER.trace("WorkRow: {}", last);
	}
	while (itr.hasNext()) {
	    CSVRecord record = itr.next();
	    WorkRow row = new WorkRow();
	    idRow = record.get(WorkHeaderFildConstant.ID.name().toLowerCase());

	    String rowCheck = isValidIdRow(idRow);
	    if (StringUtils.isNotEmpty(rowCheck)) {
		row.setErrorCode(rowCheck);
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
	    return code = WccWorkImportExceptionCodeEnum.ID_EMPTY_ERROR.getCode();
	} else {
	    String id = StringUtils.remove(rowId, '\"');
	    id = StringUtils.trimToNull(id);
	    if (null != id) {
		try {
		    Long.parseLong(id);
		} catch (NumberFormatException e) {
		    return code = WccWorkImportExceptionCodeEnum.ID_FORMAT_ERROR.getCode();
		}
	    } else {
		return code = WccWorkImportExceptionCodeEnum.ID_EMPTY_ERROR.getCode();
	    }
	}

	return code;
    }

    private void loadRow(WorkRow row, CSVRecord record) {

	row.setId(record.isMapped(WorkHeaderFildConstant.ID.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.ID.name().toLowerCase()) : StringUtils.EMPTY);
	row.setRowType(record.isMapped(WorkHeaderFildConstant.ROW_TYPE.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.ROW_TYPE.name().toLowerCase()) : StringUtils.EMPTY);
	row.setTransaction(record.isMapped(WorkHeaderFildConstant.TRANSACTION.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.TRANSACTION.name().toLowerCase()) : StringUtils.EMPTY);
	row.setWorkMainId(record.isMapped(WorkHeaderFildConstant.WORK_MAIN_ID.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.WORK_MAIN_ID.name().toLowerCase()) : StringUtils.EMPTY);
	row.setWorkTitle(record.isMapped(WorkHeaderFildConstant.WORK_TITLE.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.WORK_TITLE.name().toLowerCase()) : StringUtils.EMPTY);
	row.setDuration(record.isMapped(WorkHeaderFildConstant.DURATION.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.DURATION.name().toLowerCase()) : StringUtils.EMPTY);
	row.setGenre(record.isMapped(WorkHeaderFildConstant.GENRE.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.GENRE.name().toLowerCase()) : StringUtils.EMPTY);
	row.setInstrument(record.isMapped(WorkHeaderFildConstant.INSTRUMENT.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.INSTRUMENT.name().toLowerCase()) : StringUtils.EMPTY);
	row.setType(record.isMapped(WorkHeaderFildConstant.TYPE.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.TYPE.name().toLowerCase()) : StringUtils.EMPTY);
	row.setValue(record.isMapped(WorkHeaderFildConstant.VALUE.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.VALUE.name().toLowerCase()) : StringUtils.EMPTY);
	row.setPerformer(record.isMapped(WorkHeaderFildConstant.PERFORMER.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.PERFORMER.name().toLowerCase()) : StringUtils.EMPTY);
	row.setTerritory(record.isMapped(WorkHeaderFildConstant.TERRITORY.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.TERRITORY.name().toLowerCase()) : StringUtils.EMPTY);
	row.setNameMainId(record.isMapped(WorkHeaderFildConstant.NAME_MAIN_ID.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.NAME_MAIN_ID.name().toLowerCase()) : StringUtils.EMPTY);
	row.setRole(record.isMapped(WorkHeaderFildConstant.ROLE.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.ROLE.name().toLowerCase()) : StringUtils.EMPTY);
	row.setCreatorRef(record.isMapped(WorkHeaderFildConstant.CREATOR_REF.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.CREATOR_REF.name().toLowerCase()) : StringUtils.EMPTY);
	row.setRightCategory(record.isMapped(WorkHeaderFildConstant.RIGHT_CATEGORY.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.RIGHT_CATEGORY.name().toLowerCase()) : StringUtils.EMPTY);
	// row.setShareValue(record.isMapped(WorkHeaderFildConstant.SHARE_VALUE.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.SHARE_VALUE.name().toLowerCase()) : StringUtils.EMPTY);
	row.setCreationClass(record.isMapped(WorkHeaderFildConstant.CREATION_CLASS.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.CREATION_CLASS.name().toLowerCase()) : StringUtils.EMPTY);

	row.setCatalogueNumber(
		record.isMapped(WorkHeaderFildConstant.CATALOGUE_NUMBER.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.CATALOGUE_NUMBER.name().toLowerCase()) : StringUtils.EMPTY);
	row.setSupport(record.isMapped(WorkHeaderFildConstant.SUPPORT.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.SUPPORT.name().toLowerCase()) : StringUtils.EMPTY);
	row.setCountryOfProduction(
		record.isMapped(WorkHeaderFildConstant.COUNTRY_OF_PRODUCTION.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.COUNTRY_OF_PRODUCTION.name().toLowerCase()) : StringUtils.EMPTY);
	row.setCategory(record.isMapped(WorkHeaderFildConstant.CATEGORY.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.CATEGORY.name().toLowerCase()) : StringUtils.EMPTY);
	row.setLabel(record.isMapped(WorkHeaderFildConstant.LABEL.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.LABEL.name().toLowerCase()) : StringUtils.EMPTY);
	row.setLanguage(record.isMapped(WorkHeaderFildConstant.LANGUAGE.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.LANGUAGE.name().toLowerCase()) : StringUtils.EMPTY);

	row.setWeight(record.isMapped(WorkHeaderFildConstant.WEIGHT.name().toLowerCase()) ? record.get(WorkHeaderFildConstant.WEIGHT.name().toLowerCase()) : StringUtils.EMPTY);

	if (env.equals(EnvironmentEnum.LOCAL)) {
	    row.setTags(new ArrayList<>(Arrays.asList(StringUtils.defaultString(record.get(WorkHeaderFildConstant.TAGS.name().toLowerCase())).split("[,|]"))));
	}
    }

    @Override
    public void closeReader() throws IOException {
	this.csvFileParser.close();
    }

}
