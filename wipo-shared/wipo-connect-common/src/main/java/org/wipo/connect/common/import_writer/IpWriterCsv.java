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
import org.wipo.connect.common.import_model.IpHeaderFildConstant;
import org.wipo.connect.common.import_model.IpRow;
import org.wipo.connect.common.import_model.ResultLogHeader;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.ConstantUtils;
import org.wipo.connect.common.utils.ConversionUtils;
import org.wipo.connect.common.utils.WccUtils;

public class IpWriterCsv implements IpWriter<List<IpRow>> {

    private static ImportFileTypeEnum importFileTypeEnum = ImportFileTypeEnum.CSV;
    private CSVPrinter csvWriter;

    private final EnvironmentEnum env;
    private String fileName;
    private Integer rowCount;
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(IpWriterCsv.class);
    private final boolean isLogWriter;

    public IpWriterCsv(String fileName, EnvironmentEnum env, boolean isLogWriter) throws IOException {
	this.env = env;
	if (this.env.compareTo(EnvironmentEnum.LOCAL) == 0 && isLogWriter) {
	    String extensionOutput = StringUtils.join(SUFFIX_LOCAL, "." + FilenameUtils.getExtension(fileName));
	    this.fileName = StringUtils.replace(fileName, FilenameUtils.getExtension(fileName), extensionOutput);
	} else {
	    SimpleDateFormat sdf = new SimpleDateFormat(ConversionUtils.DATE_TIME_STAMP_MILLI);
	    this.fileName = fileName + "/" + SUFFIX_INTER + "." + sdf.format(new Date()) + ".csv";
	}
	LOGGER.info("Temp log filename:{}", this.fileName);
	rowCount = 0;
	this.isLogWriter = isLogWriter;

	FileWriter fileWriter = new FileWriter(this.fileName);
	BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

	CSVFormat csvFormat = CSVFormat.DEFAULT.withRecordSeparator(ConstantUtils.CSV_NEW_LINE_SEPARATOR);
	csvFormat = csvFormat.withIgnoreSurroundingSpaces(true).withNullString("");

	csvWriter = new CSVPrinter(bufferedWriter, csvFormat);

    }

    @Override
    public void writeRows(List<IpRow> ipRows) throws WccException {
	try {
	    String headerStr = getHeader();
	    if (rowCount == 0) {
		csvWriter.printRecord(Arrays.asList(StringUtils.split(headerStr, ',')));
	    }

	    for (IpRow ipRow : ipRows) {
		rowCount++;
		List<String> row = new ArrayList<>();

		row.add(ipRow.getTransaction() != null ? ipRow.getTransaction() : StringUtils.EMPTY);
		row.add(ipRow.getIdField());
		row.add(ipRow.getRowType());

		row.add(ipRow.getMainId() != null ? ipRow.getMainId() : StringUtils.EMPTY);
		row.add(ipRow.getType() != null ? ipRow.getType() : StringUtils.EMPTY);
		row.add(ipRow.getCitizenshipList() != null ? StringUtils.join(ipRow.getCitizenshipList(), "|") : StringUtils.EMPTY);
		row.add(ipRow.getSex() != null ? ipRow.getSex() : StringUtils.EMPTY);
		row.add(ipRow.getBirthDate() != null ? ipRow.getBirthDate() : StringUtils.EMPTY);
		row.add(ipRow.getDeathDate() != null ? ipRow.getDeathDate() : StringUtils.EMPTY);
		row.add(ipRow.getCountryOfBirth() != null ? ipRow.getCountryOfBirth() : StringUtils.EMPTY);

		row.add(ipRow.getNameType() != null ? ipRow.getNameType() : StringUtils.EMPTY);
		row.add(ipRow.getLastCompanyName() != null ? ipRow.getLastCompanyName() : StringUtils.EMPTY);
		row.add(ipRow.getFirstName() != null ? ipRow.getFirstName() : StringUtils.EMPTY);

		row.add(ipRow.getCreationClass() != null ? ipRow.getCreationClass() : StringUtils.EMPTY);
		row.add(ipRow.getRightType() != null ? ipRow.getRightType() : StringUtils.EMPTY);
		row.add(ipRow.getRole() != null ? ipRow.getRole() : StringUtils.EMPTY);
		row.add(ipRow.getAffiliationFrom() != null ? ipRow.getAffiliationFrom() : StringUtils.EMPTY);
		row.add(ipRow.getAffiliationTo() != null ? ipRow.getAffiliationTo() : StringUtils.EMPTY);
		row.add(ipRow.getSignatureDate() != null ? ipRow.getSignatureDate() : StringUtils.EMPTY);
		row.add(ipRow.getShare() != null ? ipRow.getShare() : StringUtils.EMPTY);
		row.add(ipRow.getTerritory() != null ? ipRow.getTerritory() : StringUtils.EMPTY);
		row.add(ipRow.getCmo() != null ? ipRow.getCmo() : StringUtils.EMPTY);

		row.add(ipRow.getValue() != null ? ipRow.getValue() : StringUtils.EMPTY);

		if (env.compareTo(EnvironmentEnum.LOCAL) == 0) {
		    row.add(ipRow.getAddressLine1() != null ? ipRow.getAddressLine1() : StringUtils.EMPTY);
		    row.add(ipRow.getAddressLine2() != null ? ipRow.getAddressLine2() : StringUtils.EMPTY);
		    row.add(ipRow.getAddressLine3() != null ? ipRow.getAddressLine3() : StringUtils.EMPTY);
		    row.add(ipRow.getAddressCity() != null ? ipRow.getAddressCity() : StringUtils.EMPTY);
		    row.add(ipRow.getAddressProvince() != null ? ipRow.getAddressProvince() : StringUtils.EMPTY);
		    row.add(ipRow.getAddressZipCode() != null ? ipRow.getAddressZipCode() : StringUtils.EMPTY);
		    row.add(ipRow.getAddressCountry() != null ? ipRow.getAddressCountry() : StringUtils.EMPTY);

		    row.add(ipRow.getBankName() != null ? ipRow.getBankName() : StringUtils.EMPTY);
		    row.add(ipRow.getBranch() != null ? ipRow.getBranch() : StringUtils.EMPTY);
		    row.add(ipRow.getAddress() != null ? ipRow.getAddress() : StringUtils.EMPTY);
		    row.add(ipRow.getAccountName() != null ? ipRow.getAccountName() : StringUtils.EMPTY);
		    row.add(ipRow.getSwift() != null ? ipRow.getSwift() : StringUtils.EMPTY);
		    row.add(ipRow.getAccountNumber() != null ? ipRow.getAccountNumber() : StringUtils.EMPTY);
		    row.add(ipRow.getTypeOfPayment() != null ? ipRow.getTypeOfPayment() : StringUtils.EMPTY);

		    row.add(StringUtils.join(ipRow.getTags(), "|"));
		}

		if (isLogWriter) {
		    row.add(ipRow.getStatus() != null ? ipRow.getStatus() : StringUtils.EMPTY);
		    row.add(ipRow.getIds() != null ? ipRow.getIds() : StringUtils.EMPTY);
		}

		csvWriter.printRecord(row);
	    }

	} catch (Exception e) {
	    LOGGER.error("Error writing csv file", e);
	    if (csvWriter != null) {
		try {
		    csvWriter.flush();
		    csvWriter.close();
		} catch (IOException e1) {
		    LOGGER.error("close csv writer Stream error", e1);
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
	    headerStr = StringUtils.join(IpHeaderFildConstant.values(), ",");
	    if (isLogWriter) {
		headerStr += ",";
	    }
	} else {
	    for (IpHeaderFildConstant field : IpHeaderFildConstant.values()) {
		if (field.isLocal().equals(false)) {
		    headerStr = StringUtils.join(headerStr, field.name() + ",");
		}
	    }
	}

	if (isLogWriter) {
	    headerStr = StringUtils.join(headerStr, StringUtils.join(ResultLogHeader.values(), ","));
	}
	return headerStr;
    }

}
