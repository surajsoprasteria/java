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
import org.wipo.connect.common.import_model.IpHeaderFildConstant;
import org.wipo.connect.common.import_model.IpRow;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.ExcelUtils;

public class IpReaderExcel implements IpReader {

    private Workbook workbook;
    private Sheet sheet;
    private Integer ipImportPageSize;

    private Iterator<Row> itr;
    private IpRow last;
    private Row headerRow;
    private String[] headerColumn;

    private EnvironmentEnum env;
    /** The logger. */
    private final Logger LOGGER = WipoLoggerFactory.getLogger(IpReaderExcel.class);

    public IpReaderExcel() {

    }

    @Override
    public void initializeReader(String fileName, EnvironmentEnum env, Integer ipImportPageSize) throws Exception {
	this.env = env;
	this.ipImportPageSize = ipImportPageSize;

	this.workbook = WorkbookFactory.create(new File(fileName), null, true);
	this.sheet = workbook.getSheetAt(0);

	LOGGER.debug("Inizialize parser reading from file: {}", fileName);
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
    public synchronized List<IpRow> getBlockEntity() throws IOException {

	String idRow = StringUtils.EMPTY;
	String previousIdRow = null;
	Row record = null;
	int entityCount = 0;
	List<IpRow> ipRowList = new ArrayList<>();
	if (last != null) {
	    ipRowList.add(last);
	    LOGGER.trace("IpRow: {}", last);
	} else {
	    record = itr.next();
	}
	while (itr.hasNext()) {
	    record = itr.next();
	    IpRow row = new IpRow();
	    for (int cn = headerRow.getFirstCellNum(); cn < headerRow.getLastCellNum(); cn++) {
		if (StringUtils.equalsIgnoreCase(headerColumn[cn], IpHeaderFildConstant.ID.name())) {
		    idRow = ExcelUtils.readCellValue(record, cn);

		    String rowCheck = isValidIdRow(idRow);
		    if (StringUtils.isNotEmpty(rowCheck)) {
			row.setErrorCode(rowCheck);
		    }

		    break;
		}
	    }
	    // if current row num is grater then last row num read
	    if (entityCount < ipImportPageSize) {

		if (null != previousIdRow && !previousIdRow.equalsIgnoreCase(idRow)) {
		    entityCount++;
		}

		// if is the first row to read or if is same entity of previous row or entityCount<ip_import_page_size
		if (null == previousIdRow || entityCount < ipImportPageSize) {
		    loadRow(row, record);
		    ipRowList.add(row);
		    LOGGER.trace("IpRow: {}", row);
		    previousIdRow = idRow;
		} else {
		    loadRow(row, record);
		    last = row;
		    break;
		}

	    }

	}

	return ipRowList;
    }

    private void loadRow(IpRow row, Row record) {
	for (int cn = headerRow.getFirstCellNum(); cn < headerRow.getLastCellNum(); cn++) {
	    String value = ExcelUtils.readCellValue(record, cn);
	    IpHeaderFildConstant cellName = EnumUtils.getEnum(IpHeaderFildConstant.class, headerColumn[cn]);
	    loadIp(row, cellName, value);
	}

    }

    private void loadIp(IpRow row, IpHeaderFildConstant cellName, String cellValue) {
	if (null != cellName) {
	    switch (cellName) {
		case ID:
		    row.setIdField(cellValue);
		    break;
		case ROW_TYPE:
		    row.setRowType(cellValue);
		    break;
		case TRANSACTION:
		    row.setTransaction(cellValue);
		    break;
		case MAIN_ID:
		    row.setMainId(cellValue);
		    break;
		case TYPE:
		    row.setType(cellValue);
		    break;
		case CITIZENSHIP:
		    row.setCitizenshipList(new ArrayList<>(Arrays.asList(StringUtils.split(StringUtils.defaultString(cellValue), "|"))));
		    break;
		case COUNTRY_OF_BIRTH:
		    row.setCountryOfBirth(cellValue);
		    break;
		// TODO: WCONNECT-63 ------------------------------
		case BIRTH_PLACE:
		    row.setBirthPlace(cellValue);
		    break;
		// -------------------------------------------------
		case SEX:
		    row.setSex(cellValue);
		    break;
		case BIRTH_DATE:
		    row.setBirthDate(cellValue);
		    break;
		case DEATH_DATE:
		    row.setDeathDate(cellValue);
		    break;
		case NAME_TYPE:
		    row.setNameType(cellValue);
		    break;
		case FIRST_NAME:
		    row.setFirstName(cellValue);
		    break;
		case LAST_COMPANY_NAME:
		    row.setLastCompanyName(cellValue);
		    break;
		case CREATION_CLASS:
		    row.setCreationClass(cellValue);
		    break;
		case RIGHT_TYPE:
		    row.setRightType(cellValue);
		    break;
		case ROLE:
		    row.setRole(cellValue);
		    break;
		case AFFILIATION_FROM:
		    row.setAffiliationFrom(cellValue);
		    break;
		case AFFILIATION_TO:
		    row.setAffiliationTo(cellValue);
		    break;
		case SIGNATURE_DATE:
		    row.setSignatureDate(cellValue);
		    break;
		case SHARE:
		    row.setShare(cellValue);
		    break;
		case TERRITORY:
		    row.setTerritory(cellValue);
		    break;
		case CMO:
		    row.setCmo(cellValue);
		    break;
		case VALUE:
		    row.setValue(cellValue);
		    break;
		case ADDRESS_LINE_1:
		    if (env.equals(EnvironmentEnum.LOCAL)) {
			row.setAddressLine1(cellValue);
		    }
		    break;
		case ADDRESS_LINE_2:
		    if (env.equals(EnvironmentEnum.LOCAL)) {
			row.setAddressLine2(cellValue);
		    }
		    break;
		case ADDRESS_LINE_3:
		    if (env.equals(EnvironmentEnum.LOCAL)) {
			row.setAddressLine3(cellValue);
		    }
		    break;
		case ADDRESS_CITY:
		    if (env.equals(EnvironmentEnum.LOCAL)) {
			row.setAddressCity(cellValue);
		    }
		    break;
		case ADDRESS_PROVINCE:
		    if (env.equals(EnvironmentEnum.LOCAL)) {
			row.setAddressProvince(cellValue);
		    }
		    break;
		case ADDRESS_ZIP_CODE:
		    if (env.equals(EnvironmentEnum.LOCAL)) {
			row.setAddressZipCode(cellValue);
		    }
		    break;
		case ADDRESS_COUNTRY:
		    if (env.equals(EnvironmentEnum.LOCAL)) {
			row.setAddressCountry(cellValue);
		    }
		    break;
		case BANK_NAME:
		    if (env.equals(EnvironmentEnum.LOCAL)) {
			row.setBankName(cellValue);
		    }
		    break;
		case BRANCH:
		    if (env.equals(EnvironmentEnum.LOCAL)) {
			row.setBranch(cellValue);
		    }
		    break;
		case ADDRESS:
		    if (env.equals(EnvironmentEnum.LOCAL)) {
			row.setAddress(cellValue);
		    }
		    break;
		case ACCOUNT_NAME:
		    if (env.equals(EnvironmentEnum.LOCAL)) {
			row.setAccountName(cellValue);
		    }
		    break;
		case SWIFT:
		    if (env.equals(EnvironmentEnum.LOCAL)) {
			row.setSwift(cellValue);
		    }
		    break;
		case ACCOUNT_NUMBER:
		    if (env.equals(EnvironmentEnum.LOCAL)) {
			row.setAccountNumber(cellValue);
		    }
		    break;
		case TYPE_OF_PAYMENT:
		    if (env.equals(EnvironmentEnum.LOCAL)) {
			row.setTypeOfPayment(cellValue);
		    }
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
}
