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
import org.wipo.connect.common.import_model.IpHeaderFildConstant;
import org.wipo.connect.common.import_model.IpRow;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.enumerator.WccIpImportExceptionCodeEnum;

/**
 *
 * @author pasquale.minervini
 *
 */
public class IpReaderCsv implements IpReader {

    /** The csv file parser. */
    private CSVParser csvFileParser;

    private Integer ipImportPageSize;

    private Iterator<CSVRecord> itr;
    private IpRow last;
    private EnvironmentEnum env;

    /** The logger. */
    private final Logger LOGGER = WipoLoggerFactory.getLogger(IpReaderCsv.class);

    public IpReaderCsv() throws IOException {

    }

    @Override
    public void initializeReader(String fileName, EnvironmentEnum env, Integer ipImportPageSize) throws IOException {
	this.env = env;
	this.ipImportPageSize = ipImportPageSize;
	LOGGER.info("Temp filename:{}", fileName);
	BufferedReader br = new BufferedReader(new FileReader(fileName));
	LOGGER.debug("Inizializing parser reading file: {}", fileName);
	// csvFileParser = CSVFormat.RFC4180.withHeader().withIgnoreHeaderCase().parse(br);
	csvFileParser = CSVFormat.DEFAULT.withHeader().withIgnoreHeaderCase().parse(br);
	itr = csvFileParser.iterator();
    }

    @Override
    public synchronized boolean hasNextBlock() throws IOException {

	return itr.hasNext();
    }

    @Override
    public synchronized List<IpRow> getBlockEntity() throws IOException {

	String idRow;
	String previousIdRow = null;
	int entityCount = 0;
	List<IpRow> ipRowList = new ArrayList<>();
	if (last != null) {

	    ipRowList.add(last);
	    LOGGER.trace("IpRow: {}", last);
	}
	while (itr.hasNext()) {
	    CSVRecord record = itr.next();
	    IpRow row = new IpRow();
	    idRow = record.get(IpHeaderFildConstant.ID.name().toLowerCase());
	    String rowCheck = isValidIdRow(idRow);
	    if (StringUtils.isNotEmpty(rowCheck)) {
		row.setErrorCode(rowCheck);
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

    private void loadRow(IpRow row, CSVRecord record) {
	row.setIdField(record.isMapped(IpHeaderFildConstant.ID.name().toLowerCase()) ? record.get(IpHeaderFildConstant.ID.name().toLowerCase()) : StringUtils.EMPTY);
	row.setRowType(record.isMapped(IpHeaderFildConstant.ROW_TYPE.name().toLowerCase()) ? record.get(IpHeaderFildConstant.ROW_TYPE.name().toLowerCase()) : StringUtils.EMPTY);
	row.setTransaction(record.isMapped(IpHeaderFildConstant.TRANSACTION.name().toLowerCase()) ? record.get(IpHeaderFildConstant.TRANSACTION.name().toLowerCase()) : StringUtils.EMPTY);
	// Ip field
	row.setMainId(record.isMapped(IpHeaderFildConstant.MAIN_ID.name().toLowerCase()) ? record.get(IpHeaderFildConstant.MAIN_ID.name().toLowerCase()) : StringUtils.EMPTY);
	row.setType(record.isMapped(IpHeaderFildConstant.TYPE.name().toLowerCase()) ? record.get(IpHeaderFildConstant.TYPE.name().toLowerCase()) : StringUtils.EMPTY);
	row.setCitizenshipList(new ArrayList<>(Arrays.asList(StringUtils.split(record.get(IpHeaderFildConstant.CITIZENSHIP.name().toLowerCase()), "|"))));
	row.setSex(record.isMapped(IpHeaderFildConstant.SEX.name().toLowerCase()) ? record.get(IpHeaderFildConstant.SEX.name().toLowerCase()) : StringUtils.EMPTY);
	row.setBirthDate(record.isMapped(IpHeaderFildConstant.BIRTH_DATE.name().toLowerCase()) ? record.get(IpHeaderFildConstant.BIRTH_DATE.name().toLowerCase()) : StringUtils.EMPTY);
	row.setDeathDate(record.isMapped(IpHeaderFildConstant.DEATH_DATE.name().toLowerCase()) ? record.get(IpHeaderFildConstant.DEATH_DATE.name().toLowerCase()) : StringUtils.EMPTY);
	row.setCountryOfBirth(record.isMapped(IpHeaderFildConstant.COUNTRY_OF_BIRTH.name().toLowerCase()) ? record.get(IpHeaderFildConstant.COUNTRY_OF_BIRTH.name().toLowerCase()) : StringUtils.EMPTY);
	// TODO: WCONNECT-63 ------------------------------
	row.setBirthPlace(record.isMapped(IpHeaderFildConstant.BIRTH_PLACE.name().toLowerCase()) ? record.get(IpHeaderFildConstant.BIRTH_PLACE.name().toLowerCase()) : StringUtils.EMPTY);
	// ------------------------------------------------
	// Name field
	// row.setNameMainId(record.isMapped(IpHeaderFildConstant.NAME_MAIN_ID.name().toLowerCase()) ? record.get(IpHeaderFildConstant.NAME_MAIN_ID.name().toLowerCase()) : StringUtils.EMPTY);
	row.setNameType(record.isMapped(IpHeaderFildConstant.NAME_TYPE.name().toLowerCase()) ? record.get(IpHeaderFildConstant.NAME_TYPE.name().toLowerCase()) : StringUtils.EMPTY);
	row.setLastCompanyName(
		record.isMapped(IpHeaderFildConstant.LAST_COMPANY_NAME.name().toLowerCase()) ? record.get(IpHeaderFildConstant.LAST_COMPANY_NAME.name().toLowerCase()) : StringUtils.EMPTY);
	row.setFirstName(record.isMapped(IpHeaderFildConstant.FIRST_NAME.name().toLowerCase()) ? record.get(IpHeaderFildConstant.FIRST_NAME.name().toLowerCase()) : StringUtils.EMPTY);
	// Affiliation field
	row.setCreationClass(record.isMapped(IpHeaderFildConstant.CREATION_CLASS.name().toLowerCase()) ? record.get(IpHeaderFildConstant.CREATION_CLASS.name().toLowerCase()) : StringUtils.EMPTY);
	row.setRole(record.isMapped(IpHeaderFildConstant.ROLE.name().toLowerCase()) ? record.get(IpHeaderFildConstant.ROLE.name().toLowerCase()) : StringUtils.EMPTY);
	row.setRightType(record.isMapped(IpHeaderFildConstant.RIGHT_TYPE.name().toLowerCase()) ? record.get(IpHeaderFildConstant.RIGHT_TYPE.name().toLowerCase()) : StringUtils.EMPTY);
	row.setAffiliationFrom(
		record.isMapped(IpHeaderFildConstant.AFFILIATION_FROM.name().toLowerCase()) ? record.get(IpHeaderFildConstant.AFFILIATION_FROM.name().toLowerCase()) : StringUtils.EMPTY);
	row.setAffiliationTo(record.isMapped(IpHeaderFildConstant.AFFILIATION_TO.name().toLowerCase()) ? record.get(IpHeaderFildConstant.AFFILIATION_TO.name().toLowerCase()) : StringUtils.EMPTY);
	row.setSignatureDate(record.isMapped(IpHeaderFildConstant.SIGNATURE_DATE.name().toLowerCase()) ? record.get(IpHeaderFildConstant.SIGNATURE_DATE.name().toLowerCase()) : StringUtils.EMPTY);
	row.setShare(record.isMapped(IpHeaderFildConstant.SHARE.name().toLowerCase()) ? record.get(IpHeaderFildConstant.SHARE.name().toLowerCase()) : StringUtils.EMPTY);
	row.setTerritory(record.isMapped(IpHeaderFildConstant.TERRITORY.name().toLowerCase()) ? record.get(IpHeaderFildConstant.TERRITORY.name().toLowerCase()) : StringUtils.EMPTY);
	row.setCmo(record.isMapped(IpHeaderFildConstant.CMO.name().toLowerCase()) ? record.get(IpHeaderFildConstant.CMO.name().toLowerCase()) : StringUtils.EMPTY);

	row.setValue(record.isMapped(IpHeaderFildConstant.VALUE.name().toLowerCase()) ? record.get(IpHeaderFildConstant.VALUE.name().toLowerCase()) : StringUtils.EMPTY);

	if (env.equals(EnvironmentEnum.LOCAL)) {
	    row.setAddressLine1(record.isMapped(IpHeaderFildConstant.ADDRESS_LINE_1.name().toLowerCase()) ? record.get(IpHeaderFildConstant.ADDRESS_LINE_1.name().toLowerCase()) : StringUtils.EMPTY);
	    row.setAddressLine2(record.isMapped(IpHeaderFildConstant.ADDRESS_LINE_2.name().toLowerCase()) ? record.get(IpHeaderFildConstant.ADDRESS_LINE_2.name().toLowerCase()) : StringUtils.EMPTY);
	    row.setAddressLine3(record.isMapped(IpHeaderFildConstant.ADDRESS_LINE_3.name().toLowerCase()) ? record.get(IpHeaderFildConstant.ADDRESS_LINE_3.name().toLowerCase()) : StringUtils.EMPTY);
	    row.setAddressCity(record.isMapped(IpHeaderFildConstant.ADDRESS_CITY.name().toLowerCase()) ? record.get(IpHeaderFildConstant.ADDRESS_CITY.name().toLowerCase()) : StringUtils.EMPTY);
	    row.setAddressProvince(
		    record.isMapped(IpHeaderFildConstant.ADDRESS_PROVINCE.name().toLowerCase()) ? record.get(IpHeaderFildConstant.ADDRESS_PROVINCE.name().toLowerCase()) : StringUtils.EMPTY);
	    row.setAddressZipCode(
		    record.isMapped(IpHeaderFildConstant.ADDRESS_ZIP_CODE.name().toLowerCase()) ? record.get(IpHeaderFildConstant.ADDRESS_ZIP_CODE.name().toLowerCase()) : StringUtils.EMPTY);
	    row.setAddressCountry(
		    record.isMapped(IpHeaderFildConstant.ADDRESS_COUNTRY.name().toLowerCase()) ? record.get(IpHeaderFildConstant.ADDRESS_COUNTRY.name().toLowerCase()) : StringUtils.EMPTY);

	    row.setBankName(record.isMapped(IpHeaderFildConstant.BANK_NAME.name().toLowerCase()) ? record.get(IpHeaderFildConstant.BANK_NAME.name().toLowerCase()) : StringUtils.EMPTY);
	    row.setBranch(record.isMapped(IpHeaderFildConstant.BRANCH.name().toLowerCase()) ? record.get(IpHeaderFildConstant.BRANCH.name().toLowerCase()) : StringUtils.EMPTY);
	    row.setAddress(record.isMapped(IpHeaderFildConstant.ADDRESS.name().toLowerCase()) ? record.get(IpHeaderFildConstant.ADDRESS.name().toLowerCase()) : StringUtils.EMPTY);
	    row.setAccountName(record.isMapped(IpHeaderFildConstant.ACCOUNT_NAME.name().toLowerCase()) ? record.get(IpHeaderFildConstant.ACCOUNT_NAME.name().toLowerCase()) : StringUtils.EMPTY);
	    row.setSwift(record.isMapped(IpHeaderFildConstant.SWIFT.name().toLowerCase()) ? record.get(IpHeaderFildConstant.SWIFT.name().toLowerCase()) : StringUtils.EMPTY);
	    row.setAccountNumber(record.isMapped(IpHeaderFildConstant.ACCOUNT_NUMBER.name().toLowerCase()) ? record.get(IpHeaderFildConstant.ACCOUNT_NUMBER.name().toLowerCase()) : StringUtils.EMPTY);
	    row.setTypeOfPayment(
		    record.isMapped(IpHeaderFildConstant.TYPE_OF_PAYMENT.name().toLowerCase()) ? record.get(IpHeaderFildConstant.TYPE_OF_PAYMENT.name().toLowerCase()) : StringUtils.EMPTY);
	    row.setTags(new ArrayList<>(Arrays.asList(StringUtils.defaultString(record.get(IpHeaderFildConstant.TAGS.name().toLowerCase())).split("[,|]"))));
	}
    }

    private String isValidIdRow(String rowId) {
	String code = StringUtils.EMPTY;
	if (StringUtils.isEmpty(rowId)) {
	    return code = WccIpImportExceptionCodeEnum.ID_EMPTY_ERROR.getCode();
	} else {
	    String id = StringUtils.remove(rowId, '\"');
	    id = StringUtils.trimToNull(id);
	    if (null != id) {
		try {
		    Long.parseLong(id);
		} catch (NumberFormatException e) {
		    return code = WccIpImportExceptionCodeEnum.ID_FORMAT_ERROR.getCode();
		}
	    } else {
		return code = WccIpImportExceptionCodeEnum.ID_EMPTY_ERROR.getCode();
	    }
	}

	return code;
    }

    @Override
    public void closeReader() throws IOException {
	this.csvFileParser.close();
    }

}
