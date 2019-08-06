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

import java.io.BufferedOutputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.import_model.EnvironmentEnum;
import org.wipo.connect.common.import_model.ImportFileTypeEnum;
import org.wipo.connect.common.import_model.WorkHeaderFildConstant;
import org.wipo.connect.common.import_model.WorkResultLogHeader;
import org.wipo.connect.common.import_model.WorkRow;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.ConversionUtils;

public class WorkWriterExcel implements WorkWriter<List<WorkRow>> {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(WorkWriterExcel.class);

    private ImportFileTypeEnum importFileTypeEnum = ImportFileTypeEnum.EXCEL;
    private SXSSFWorkbook workbook;
    private final int maxRowForSheet = SpreadsheetVersion.EXCEL2007.getMaxRows();

    private EnvironmentEnum env;
    private SXSSFSheet currentSheet;
    private BufferedOutputStream outStream;
    private String fileName;
    private int rowCount;
    private int sheetCount;
    private final boolean isLogWriter;

    public WorkWriterExcel(String importTempDir, EnvironmentEnum env, boolean isLogWriter) throws FileNotFoundException {
	// Create object of FileOutputStream
	this.env = env;
	if (this.env.compareTo(EnvironmentEnum.LOCAL) == 0 && isLogWriter) {
	    String extensionOutput = StringUtils.join(SUFFIX_LOCAL, "." + FilenameUtils.getExtension(importTempDir));
	    this.fileName = StringUtils.replace(importTempDir, FilenameUtils.getExtension(importTempDir), extensionOutput);
	} else {
	    SimpleDateFormat sdf = new SimpleDateFormat(ConversionUtils.DATE_TIME_STAMP_MILLI);
	    this.fileName = importTempDir + "/" + SUFFIX_INTER + "." + sdf.format(new Date()) + ".xlsx";
	}
	FileOutputStream fout = new FileOutputStream(this.fileName);
	this.workbook = new SXSSFWorkbook(300);
	this.workbook.setCompressTempFiles(true);
	outStream = new BufferedOutputStream(fout);
	this.isLogWriter = isLogWriter;
	rowCount = 0;
	sheetCount = 0;
	setDefaultCellStyle();
    }

    @Override
    public void writeRows(List<WorkRow> workRows) throws WccException {
	try {
	    if (rowCount + workRows.size() > maxRowForSheet) {
		rowCount = 0;
	    }

	    if (rowCount == 0) {
		currentSheet = createNewSheet();
		setHeader();
	    }

	    for (WorkRow workRow : workRows) {
		SXSSFRow row = currentSheet.createRow(++rowCount);
		row.createCell(WorkHeaderFildConstant.TRANSACTION.getCode()).setCellValue(workRow.getTransaction() != null ? workRow.getTransaction() : StringUtils.EMPTY);
		row.createCell(WorkHeaderFildConstant.ID.getCode()).setCellValue(workRow.getId());
		row.createCell(WorkHeaderFildConstant.ROW_TYPE.getCode()).setCellValue(workRow.getRowType());

		row.createCell(WorkHeaderFildConstant.WORK_MAIN_ID.getCode()).setCellValue(workRow.getWorkMainId() != null ? workRow.getWorkMainId() : StringUtils.EMPTY);
		row.createCell(WorkHeaderFildConstant.WORK_TITLE.getCode()).setCellValue(workRow.getWorkTitle() != null ? workRow.getWorkTitle() : StringUtils.EMPTY);
		// row.createCell(WorkHeaderFildConstant.WORK_TITLE_TYPE.getCode()).setCellValue(workRow.getWorkTitleType() != null ? workRow.getWorkTitleType() : StringUtils.EMPTY);
		row.createCell(WorkHeaderFildConstant.DURATION.getCode()).setCellValue(workRow.getDuration() != null ? workRow.getDuration() : StringUtils.EMPTY);
		row.createCell(WorkHeaderFildConstant.GENRE.getCode()).setCellValue(workRow.getGenre() != null ? workRow.getGenre() : StringUtils.EMPTY);
		row.createCell(WorkHeaderFildConstant.INSTRUMENT.getCode()).setCellValue(workRow.getInstrument() != null ? workRow.getInstrument() : StringUtils.EMPTY);

		row.createCell(WorkHeaderFildConstant.TYPE.getCode()).setCellValue(workRow.getType() != null ? workRow.getType() : StringUtils.EMPTY);
		row.createCell(WorkHeaderFildConstant.VALUE.getCode()).setCellValue(workRow.getValue() != null ? workRow.getValue() : StringUtils.EMPTY);

		row.createCell(WorkHeaderFildConstant.PERFORMER.getCode()).setCellValue(workRow.getPerformer() != null ? workRow.getPerformer() : StringUtils.EMPTY);

		row.createCell(WorkHeaderFildConstant.TERRITORY.getCode()).setCellValue(workRow.getTerritory() != null ? workRow.getTerritory() : StringUtils.EMPTY);
		row.createCell(WorkHeaderFildConstant.NAME_MAIN_ID.getCode()).setCellValue(workRow.getNameMainId() != null ? workRow.getNameMainId() : StringUtils.EMPTY);
		row.createCell(WorkHeaderFildConstant.ROLE.getCode()).setCellValue(workRow.getRole() != null ? workRow.getRole() : StringUtils.EMPTY);
		row.createCell(WorkHeaderFildConstant.CREATOR_REF.getCode()).setCellValue(workRow.getCreatorRef() != null ? workRow.getCreatorRef() : StringUtils.EMPTY);
		row.createCell(WorkHeaderFildConstant.RIGHT_CATEGORY.getCode()).setCellValue(workRow.getRightCategory() != null ? workRow.getRightCategory() : StringUtils.EMPTY);
		// row.createCell(WorkHeaderFildConstant.SHARE_VALUE.getCode()).setCellValue(workRow.getShareValue() != null ? workRow.getShareValue() : StringUtils.EMPTY);

		row.createCell(WorkHeaderFildConstant.CREATION_CLASS.getCode()).setCellValue(workRow.getCreationClass() != null ? workRow.getCreationClass() : StringUtils.EMPTY);

		row.createCell(WorkHeaderFildConstant.CATALOGUE_NUMBER.getCode()).setCellValue(workRow.getCatalogueNumber() != null ? workRow.getCatalogueNumber() : StringUtils.EMPTY);
		row.createCell(WorkHeaderFildConstant.SUPPORT.getCode()).setCellValue(workRow.getSupport() != null ? workRow.getSupport() : StringUtils.EMPTY);
		row.createCell(WorkHeaderFildConstant.COUNTRY_OF_PRODUCTION.getCode()).setCellValue(workRow.getCountryOfProduction() != null ? workRow.getCountryOfProduction() : StringUtils.EMPTY);
		row.createCell(WorkHeaderFildConstant.CATEGORY.getCode()).setCellValue(workRow.getCategory() != null ? workRow.getCategory() : StringUtils.EMPTY);
		row.createCell(WorkHeaderFildConstant.LABEL.getCode()).setCellValue(workRow.getLabel() != null ? workRow.getLabel() : StringUtils.EMPTY);
		row.createCell(WorkHeaderFildConstant.LANGUAGE.getCode()).setCellValue(workRow.getLanguage() != null ? workRow.getLanguage() : StringUtils.EMPTY);

		// row.createCell(WorkHeaderFildConstant.DATE_TYPE.getCode()).setCellValue(workRow.getDateType() != null ? workRow.getDateType() : StringUtils.EMPTY);
		// row.createCell(WorkHeaderFildConstant.DATE_VALUE.getCode()).setCellValue(workRow.getDateValue() != null ? workRow.getDateValue() : StringUtils.EMPTY);

		row.createCell(WorkHeaderFildConstant.WEIGHT.getCode()).setCellValue(workRow.getWeight() != null ? workRow.getWeight() : StringUtils.EMPTY);

		if (env.compareTo(EnvironmentEnum.LOCAL) == 0) {
		    row.createCell(WorkHeaderFildConstant.TAGS.getCode()).setCellValue(StringUtils.join(workRow.getTags(), "|"));
		}

		if (isLogWriter) {
		    if (env.compareTo(EnvironmentEnum.LOCAL) == 0) {
			row.createCell(WorkResultLogHeader.STATUS.getCode()).setCellValue(workRow.getStatus() != null ? workRow.getStatus() : StringUtils.EMPTY);
			row.createCell(WorkResultLogHeader.OUTPUT_ID.getCode()).setCellValue(workRow.getIds() != null ? workRow.getIds() : StringUtils.EMPTY);
		    } else {
			row.createCell(WorkResultLogHeader.STATUS.getSharedCode()).setCellValue(workRow.getStatus() != null ? workRow.getStatus() : StringUtils.EMPTY);
			row.createCell(WorkResultLogHeader.OUTPUT_ID.getSharedCode()).setCellValue(workRow.getIds() != null ? workRow.getIds() : StringUtils.EMPTY);
		    }
		}
	    }

	} catch (Exception e) {
	    LOGGER.error("Error writing excel file", e);
	    throw new WccException(e);
	}

    }

    @Override
    public ImportFileTypeEnum getImportFileTypeEnum() {
	return importFileTypeEnum;
    }

    @Override
    public String getFileName() {
	return fileName;
    }

    @Override
    public void closeStream() throws IOException {
	LOGGER.debug("close excel writer Stream ");
	workbook.write(outStream);
	workbook.close();
	outStream.flush();
	outStream.close();
	rowCount = 0;
	sheetCount = 0;
    }

    @Override
    public void flush() throws IOException {
	LOGGER.debug("Flush in Apache POI not necessary");
    }

    private SXSSFSheet createNewSheet() {
	sheetCount++;
	SXSSFSheet sheet = workbook.createSheet("Work Export " + sheetCount);
	sheet.setDefaultColumnWidth(30);
	return sheet;
    }

    private CellStyle setDefaultCellStyle() {
	Font font = workbook.createFont();
	font.setFontName("Arial");
	font.setBold(true);
	font.setColor(IndexedColors.WHITE.getIndex());

	CellStyle style = workbook.createCellStyle();
	// style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	style.setFont(font);
	return style;
    }

    private void setHeader() {
	SXSSFRow headerRow = currentSheet.createRow(rowCount);
	boolean isLocal = env.compareTo(EnvironmentEnum.LOCAL) == 0;

	if (isLocal) {
	    for (WorkHeaderFildConstant header : WorkHeaderFildConstant.values()) {
		headerRow.createCell(header.getCode()).setCellValue(header.name());
	    }
	} else {
	    for (WorkHeaderFildConstant header : WorkHeaderFildConstant.values()) {
		if (header.isLocal().equals(false)) {
		    headerRow.createCell(header.getCode()).setCellValue(header.name());
		}
	    }
	}

	if (isLogWriter) {
	    if (isLocal) {
		for (WorkResultLogHeader header : WorkResultLogHeader.values()) {
		    headerRow.createCell(header.getCode()).setCellValue(header.name());
		}
	    } else {
		for (WorkResultLogHeader header : WorkResultLogHeader.values()) {
		    headerRow.createCell(header.getSharedCode()).setCellValue(header.name());
		}
	    }
	}
    }

}
