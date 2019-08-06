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
package org.wipo.connect.common.import_writer;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.import_model.EnvironmentEnum;
import org.wipo.connect.common.import_model.ImportFileTypeEnum;
import org.wipo.connect.common.import_model.WorkHeaderFildConstant;
import org.wipo.connect.common.import_model.WorkResultLogHeader;
import org.wipo.connect.common.import_model.WorkRow;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.ConstantUtils;
import org.wipo.connect.common.utils.ConversionUtils;
import org.wipo.connect.common.utils.WccUtils;

public class WorkWriterCsv implements WorkWriter<List<WorkRow>> {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(WorkWriterCsv.class);

    private static ImportFileTypeEnum importFileTypeEnum = ImportFileTypeEnum.CSV;
    private CSVPrinter csvWriter;

    private EnvironmentEnum env;
    private String fileName;
    private Integer rowCount;
    private final boolean isLogWriter;

    public WorkWriterCsv(String importTempDir, EnvironmentEnum env, boolean isLogWriter) throws IOException {
	this.env = env;
	if (this.env.compareTo(EnvironmentEnum.LOCAL) == 0 && isLogWriter) {
	    String extensionOutput = StringUtils.join(SUFFIX_LOCAL, "." + FilenameUtils.getExtension(importTempDir));
	    this.fileName = StringUtils.replace(importTempDir, FilenameUtils.getExtension(importTempDir), extensionOutput);
	} else {
	    SimpleDateFormat sdf = new SimpleDateFormat(ConversionUtils.DATE_TIME_STAMP_MILLI);
	    this.fileName = importTempDir + "/" + SUFFIX_INTER + "." + sdf.format(new Date()) + ".csv";
	}
	rowCount = 0;
	this.isLogWriter = isLogWriter;

	FileWriter fileWriter = new FileWriter(this.fileName);
	BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

	CSVFormat csvFormat = CSVFormat.DEFAULT.withRecordSeparator(ConstantUtils.CSV_NEW_LINE_SEPARATOR);
	csvFormat = csvFormat.withIgnoreSurroundingSpaces(true).withNullString("");

	csvWriter = new CSVPrinter(bufferedWriter, csvFormat);
    }

    @Override
    public void writeRows(List<WorkRow> workRows) throws WccException {
	String headerStr = getHeader();
	try {
	    if (rowCount == 0) {
		csvWriter.printRecord(Arrays.asList(StringUtils.split(headerStr, ",")));
	    }
	    for (WorkRow workRow : workRows) {
		rowCount++;

		List<String> row = new ArrayList<>();

		row.add(workRow.getTransaction() != null ? StringUtils.trimToNull(workRow.getTransaction()) : null);
		row.add(workRow.getId());
		row.add(workRow.getRowType());

		row.add(workRow.getWorkMainId() != null ? workRow.getWorkMainId() : StringUtils.EMPTY);
		row.add(workRow.getWorkTitle() != null ? workRow.getWorkTitle() : StringUtils.EMPTY);
		// row.add(workRow.getWorkTitleType() != null ? workRow.getWorkTitleType() : StringUtils.EMPTY);
		row.add(workRow.getDuration() != null ? workRow.getDuration() : StringUtils.EMPTY);
		row.add(workRow.getGenre() != null ? workRow.getGenre() : StringUtils.EMPTY);
		row.add(workRow.getInstrument() != null ? workRow.getInstrument() : StringUtils.EMPTY);

		row.add(workRow.getType() != null ? workRow.getType() : StringUtils.EMPTY);
		row.add(workRow.getValue() != null ? workRow.getValue() : StringUtils.EMPTY);

		row.add(workRow.getPerformer() != null ? workRow.getPerformer() : StringUtils.EMPTY);

		row.add(workRow.getTerritory() != null ? workRow.getTerritory() : StringUtils.EMPTY);
		row.add(workRow.getNameMainId() != null ? workRow.getNameMainId() : StringUtils.EMPTY);
		row.add(workRow.getRole() != null ? workRow.getRole() : StringUtils.EMPTY);
		row.add(workRow.getCreatorRef() != null ? workRow.getCreatorRef() : StringUtils.EMPTY);
		row.add(workRow.getRightCategory() != null ? workRow.getRightCategory() : StringUtils.EMPTY);
		// row.add(workRow.getShareValue() != null ? workRow.getShareValue() : StringUtils.EMPTY);

		row.add(workRow.getCreationClass() != null ? workRow.getCreationClass() : StringUtils.EMPTY);

		row.add(workRow.getCatalogueNumber() != null ? workRow.getCatalogueNumber() : StringUtils.EMPTY);
		row.add(workRow.getSupport() != null ? workRow.getSupport() : StringUtils.EMPTY);
		row.add(workRow.getCountryOfProduction() != null ? workRow.getCountryOfProduction() : StringUtils.EMPTY);
		row.add(workRow.getCategory() != null ? workRow.getCategory() : StringUtils.EMPTY);
		row.add(workRow.getLabel() != null ? workRow.getLabel() : StringUtils.EMPTY);
		row.add(workRow.getLanguage() != null ? workRow.getLanguage() : StringUtils.EMPTY);

		// row.add(workRow.getDateType() != null ? workRow.getDateType() : StringUtils.EMPTY);
		// row.add(workRow.getDateValue() != null ? workRow.getDateValue() : StringUtils.EMPTY);

		row.add(workRow.getWeight() != null ? workRow.getWeight() : StringUtils.EMPTY);
		if (env.compareTo(EnvironmentEnum.LOCAL) == 0) {
		    row.add(StringUtils.join(workRow.getTags(), "|"));
		}
		if (isLogWriter) {
		    row.add(workRow.getStatus() != null ? workRow.getStatus() : StringUtils.EMPTY);
		    row.add(workRow.getIds() != null ? workRow.getIds() : StringUtils.EMPTY);
		}

		csvWriter.printRecord(row);
	    }

	} catch (Exception e) {
	    LOGGER.error("Error writing csv file", e);

	    if (csvWriter != null) {
		try {
		    csvWriter.close();
		} catch (IOException e1) {
		    LOGGER.error("Error in " + WccUtils.getMethodName() + " : " + e.getMessage());
		    throw new WccException(e);
		}
	    }
	    throw new WccException(e);
	}

    }

    @Override
    public void closeStream() {
	if (csvWriter != null) {
	    try {
		csvWriter.flush();
		csvWriter.close();
		rowCount = 0;
	    } catch (IOException e) {
		LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    }
	}
    }

    @Override
    public void flush() throws IOException {
	csvWriter.flush();
    }

    @Override
    public String getFileName() {
	return fileName;
    }

    @Override
    public ImportFileTypeEnum getImportFileTypeEnum() {
	return importFileTypeEnum;
    }

    private String getHeader() {
	boolean isLocal = env.compareTo(EnvironmentEnum.LOCAL) == 0;
	String headerStr = StringUtils.EMPTY;

	if (isLocal) {
	    headerStr = StringUtils.join(WorkHeaderFildConstant.values(), ",");
	    if (isLogWriter) {
		headerStr += ",";
	    }
	} else {
	    for (WorkHeaderFildConstant field : WorkHeaderFildConstant.values()) {
		if (field.isLocal().equals(false)) {
		    headerStr = StringUtils.join(headerStr, field.name() + ",");
		}
	    }
	}

	if (isLogWriter) {
	    headerStr = StringUtils.join(headerStr, StringUtils.join(WorkResultLogHeader.values(), ","));
	}
	return headerStr;
    }

}
