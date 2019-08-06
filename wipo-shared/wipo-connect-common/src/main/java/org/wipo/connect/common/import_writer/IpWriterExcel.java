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
import org.wipo.connect.common.import_model.IpHeaderFildConstant;
import org.wipo.connect.common.import_model.IpRow;
import org.wipo.connect.common.import_model.ResultLogHeader;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.ConversionUtils;

public class IpWriterExcel implements IpWriter<List<IpRow>> {
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(IpWriterExcel.class);

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

    public IpWriterExcel(String fileName, EnvironmentEnum env, boolean isLogWriter) throws FileNotFoundException {
	// Create object of FileOutputStream
	this.env = env;
	if (this.env.compareTo(EnvironmentEnum.LOCAL) == 0 && isLogWriter) {
	    String extensionOutput = StringUtils.join(SUFFIX_LOCAL, "." + FilenameUtils.getExtension(fileName));
	    this.fileName = StringUtils.replace(fileName, FilenameUtils.getExtension(fileName), extensionOutput);
	} else {
	    SimpleDateFormat sdf = new SimpleDateFormat(ConversionUtils.DATE_TIME_STAMP_MILLI);
	    this.fileName = fileName + "/" + SUFFIX_INTER + "." + sdf.format(new Date()) + ".xlsx";
	}
	LOGGER.info("Temp log filename:{}", this.fileName);
	FileOutputStream fout = new FileOutputStream(this.fileName);
	outStream = new BufferedOutputStream(fout);
	this.workbook = new SXSSFWorkbook(300);
	this.workbook.setCompressTempFiles(true);
	this.isLogWriter = isLogWriter;
	rowCount = 0;
	sheetCount = 0;
	setDefaultCellStyle();

    }

    @Override
    public void writeRows(List<IpRow> ipRows) throws WccException {
	try {
	    if (rowCount + ipRows.size() > maxRowForSheet) {
		rowCount = 0;
	    }

	    if (rowCount == 0) {
		currentSheet = createNewSheet();
		setHeader();
	    }

	    for (IpRow ipRow : ipRows) {
		SXSSFRow row = currentSheet.createRow(++rowCount);
		row.createCell(IpHeaderFildConstant.ID.getCode()).setCellValue(ipRow.getIdField());
		row.createCell(IpHeaderFildConstant.ROW_TYPE.getCode()).setCellValue(ipRow.getRowType());

		row.createCell(IpHeaderFildConstant.TRANSACTION.getCode()).setCellValue(ipRow.getTransaction() != null ? ipRow.getTransaction() : StringUtils.EMPTY);

		row.createCell(IpHeaderFildConstant.MAIN_ID.getCode()).setCellValue(ipRow.getMainId() != null ? ipRow.getMainId() : StringUtils.EMPTY);
		row.createCell(IpHeaderFildConstant.TYPE.getCode()).setCellValue(ipRow.getType() != null ? ipRow.getType() : "");
		row.createCell(IpHeaderFildConstant.CITIZENSHIP.getCode()).setCellValue(ipRow.getCitizenshipList() != null ? StringUtils.join(ipRow.getCitizenshipList(), "|") : StringUtils.EMPTY);
		row.createCell(IpHeaderFildConstant.SEX.getCode()).setCellValue(ipRow.getSex() != null ? ipRow.getSex() : StringUtils.EMPTY);
		row.createCell(IpHeaderFildConstant.BIRTH_DATE.getCode()).setCellValue(ipRow.getBirthDate() != null ? ipRow.getBirthDate() : StringUtils.EMPTY);
		row.createCell(IpHeaderFildConstant.DEATH_DATE.getCode()).setCellValue(ipRow.getDeathDate() != null ? ipRow.getDeathDate() : StringUtils.EMPTY);
		row.createCell(IpHeaderFildConstant.COUNTRY_OF_BIRTH.getCode()).setCellValue(ipRow.getCountryOfBirth() != null ? ipRow.getCountryOfBirth() : StringUtils.EMPTY);
		// row.createCell(IpHeaderFildConstant.NAME_MAIN_ID.getCode()).setCellValue(ipRow.getNameMainId() != null ? ipRow.getNameMainId() : StringUtils.EMPTY);
		row.createCell(IpHeaderFildConstant.NAME_TYPE.getCode()).setCellValue(ipRow.getNameType() != null ? ipRow.getNameType() : StringUtils.EMPTY);
		row.createCell(IpHeaderFildConstant.LAST_COMPANY_NAME.getCode()).setCellValue(ipRow.getLastCompanyName() != null ? ipRow.getLastCompanyName() : StringUtils.EMPTY);
		row.createCell(IpHeaderFildConstant.FIRST_NAME.getCode()).setCellValue(ipRow.getFirstName() != null ? ipRow.getFirstName() : StringUtils.EMPTY);
		row.createCell(IpHeaderFildConstant.CREATION_CLASS.getCode()).setCellValue(ipRow.getCreationClass() != null ? ipRow.getCreationClass() : StringUtils.EMPTY);
		row.createCell(IpHeaderFildConstant.RIGHT_TYPE.getCode()).setCellValue(ipRow.getRightType() != null ? ipRow.getRightType() : StringUtils.EMPTY);
		row.createCell(IpHeaderFildConstant.ROLE.getCode()).setCellValue(ipRow.getRole() != null ? ipRow.getRole() : StringUtils.EMPTY);
		row.createCell(IpHeaderFildConstant.AFFILIATION_FROM.getCode()).setCellValue(ipRow.getAffiliationFrom() != null ? ipRow.getAffiliationFrom() : StringUtils.EMPTY);
		row.createCell(IpHeaderFildConstant.AFFILIATION_TO.getCode()).setCellValue(ipRow.getAffiliationTo() != null ? ipRow.getAffiliationTo() : StringUtils.EMPTY);
		row.createCell(IpHeaderFildConstant.SIGNATURE_DATE.getCode()).setCellValue(ipRow.getSignatureDate() != null ? ipRow.getSignatureDate() : StringUtils.EMPTY);

		row.createCell(IpHeaderFildConstant.SHARE.getCode()).setCellValue(ipRow.getShare() != null ? ipRow.getShare() : StringUtils.EMPTY);
		row.createCell(IpHeaderFildConstant.TERRITORY.getCode()).setCellValue(ipRow.getTerritory() != null ? ipRow.getTerritory() : StringUtils.EMPTY);
		row.createCell(IpHeaderFildConstant.CMO.getCode()).setCellValue(ipRow.getCmo() != null ? ipRow.getCmo() : StringUtils.EMPTY);
		row.createCell(IpHeaderFildConstant.VALUE.getCode()).setCellValue(ipRow.getValue() != null ? ipRow.getValue() : StringUtils.EMPTY);

		if (env.compareTo(EnvironmentEnum.LOCAL) == 0) {
		    row.createCell(IpHeaderFildConstant.ADDRESS_LINE_1.getCode()).setCellValue(ipRow.getAddressLine1() != null ? ipRow.getAddressLine1() : StringUtils.EMPTY);
		    row.createCell(IpHeaderFildConstant.ADDRESS_LINE_2.getCode()).setCellValue(ipRow.getAddressLine2() != null ? ipRow.getAddressLine2() : StringUtils.EMPTY);
		    row.createCell(IpHeaderFildConstant.ADDRESS_LINE_3.getCode()).setCellValue(ipRow.getAddressLine3() != null ? ipRow.getAddressLine3() : StringUtils.EMPTY);
		    row.createCell(IpHeaderFildConstant.ADDRESS_CITY.getCode()).setCellValue(ipRow.getAddressCity() != null ? ipRow.getAddressCity() : StringUtils.EMPTY);
		    row.createCell(IpHeaderFildConstant.ADDRESS_PROVINCE.getCode()).setCellValue(ipRow.getAddressProvince() != null ? ipRow.getAddressProvince() : StringUtils.EMPTY);
		    row.createCell(IpHeaderFildConstant.ADDRESS_ZIP_CODE.getCode()).setCellValue(ipRow.getAddressZipCode() != null ? ipRow.getAddressZipCode() : StringUtils.EMPTY);
		    row.createCell(IpHeaderFildConstant.ADDRESS_COUNTRY.getCode()).setCellValue(ipRow.getAddressCountry() != null ? ipRow.getAddressCountry() : StringUtils.EMPTY);

		    row.createCell(IpHeaderFildConstant.BANK_NAME.getCode()).setCellValue(ipRow.getBankName() != null ? ipRow.getBankName() : StringUtils.EMPTY);
		    row.createCell(IpHeaderFildConstant.BRANCH.getCode()).setCellValue(ipRow.getBranch() != null ? ipRow.getBranch() : StringUtils.EMPTY);
		    row.createCell(IpHeaderFildConstant.ADDRESS.getCode()).setCellValue(ipRow.getAddress() != null ? ipRow.getAddress() : StringUtils.EMPTY);
		    row.createCell(IpHeaderFildConstant.ACCOUNT_NAME.getCode()).setCellValue(ipRow.getAccountName() != null ? ipRow.getAccountName() : StringUtils.EMPTY);
		    row.createCell(IpHeaderFildConstant.SWIFT.getCode()).setCellValue(ipRow.getSwift() != null ? ipRow.getSwift() : StringUtils.EMPTY);
		    row.createCell(IpHeaderFildConstant.ACCOUNT_NUMBER.getCode()).setCellValue(ipRow.getAccountNumber() != null ? ipRow.getAccountNumber() : StringUtils.EMPTY);
		    row.createCell(IpHeaderFildConstant.TYPE_OF_PAYMENT.getCode()).setCellValue(ipRow.getTypeOfPayment() != null ? ipRow.getTypeOfPayment() : StringUtils.EMPTY);

		    row.createCell(IpHeaderFildConstant.TAGS.getCode()).setCellValue(StringUtils.join(ipRow.getTags(), "|"));
		}
		if (isLogWriter && env.compareTo(EnvironmentEnum.LOCAL) == 0) {
		    row.createCell(ResultLogHeader.STATUS.getCode()).setCellValue(ipRow.getStatus() != null ? ipRow.getStatus() : StringUtils.EMPTY);
		    row.createCell(ResultLogHeader.OUTPUT_ID.getCode()).setCellValue(ipRow.getIds() != null ? ipRow.getIds() : StringUtils.EMPTY);
		} else if (isLogWriter && env.compareTo(EnvironmentEnum.SHARED) == 0) {
		    row.createCell(ResultLogHeader.STATUS.getSharedCode()).setCellValue(ipRow.getStatus() != null ? ipRow.getStatus() : StringUtils.EMPTY);
		    row.createCell(ResultLogHeader.OUTPUT_ID.getSharedCode()).setCellValue(ipRow.getIds() != null ? ipRow.getIds() : StringUtils.EMPTY);
		}
	    }

	} catch (Exception e) {
	    LOGGER.error("Error writing excel file", e);
	    throw new WccException(e);
	}

    }

    @Override
    public void flush() throws IOException {
	LOGGER.trace("Flush in Apache POI not necessary");
    }

    private void setHeader() {
	SXSSFRow headerRow = currentSheet.createRow(rowCount);
	boolean isLocal = env.compareTo(EnvironmentEnum.LOCAL) == 0;

	if (isLocal) {
	    for (IpHeaderFildConstant header : IpHeaderFildConstant.values()) {
		headerRow.createCell(header.getCode()).setCellValue(header.name());
	    }
	} else {
	    for (IpHeaderFildConstant header : IpHeaderFildConstant.values()) {
		if (header.isLocal().equals(false)) {
		    headerRow.createCell(header.getCode()).setCellValue(header.name());
		}
	    }
	}

	if (isLogWriter) {
	    if (isLocal) {
		for (ResultLogHeader header : ResultLogHeader.values()) {
		    headerRow.createCell(header.getCode()).setCellValue(header.name());
		}
	    } else {
		for (ResultLogHeader header : ResultLogHeader.values()) {
		    headerRow.createCell(header.getSharedCode()).setCellValue(header.name());
		}
	    }
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
	rowCount = 0;
	sheetCount = 0;

    }

    private SXSSFSheet createNewSheet() {
	sheetCount++;
	SXSSFSheet sheet = workbook.createSheet("RightOwner Export " + sheetCount);
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

}
