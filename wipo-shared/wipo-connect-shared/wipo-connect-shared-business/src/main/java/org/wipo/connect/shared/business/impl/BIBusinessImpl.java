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

package org.wipo.connect.shared.business.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.ConstantUtils;
import org.wipo.connect.common.utils.ConversionUtils;
import org.wipo.connect.shared.exchange.business.BIBusiness;
import org.wipo.connect.shared.exchange.dto.impl.BIParameterFlat;
import org.wipo.connect.shared.exchange.dto.impl.BIResultSet;
import org.wipo.connect.shared.exchange.dto.impl.BISearch;
import org.wipo.connect.shared.exchange.enumerator.ExportTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.FileFormatEnum;
import org.wipo.connect.shared.exchange.utils.importexport.ExportBean;
import org.wipo.connect.shared.exchange.vo.BIResultSetVO;
import org.wipo.connect.shared.persistence.dao.BIDAO;

import net.bull.javamelody.MonitoredWithSpring;

@Service
@MonitoredWithSpring
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
@Qualifier("bIBusinessImpl")
public class BIBusinessImpl implements BIBusiness {

    /** The logger. */
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(BIBusinessImpl.class);
    @Autowired
    private BIDAO biDAO;

    @Autowired
    private Properties configProperties;

    @Override
    public Map<String, BIParameterFlat> getQueryParameters(String selectedQueryCode) throws WccException {
	Map<String, BIParameterFlat> params = biDAO.findQueryParameters(selectedQueryCode);
	if (params == null) {
	    params = new HashMap<>();
	}
	return params;
    }

    @Override
    public List<String> getQueryCodeList() {
	LOGGER.debug("Loading all query code");
	List<String> queryCodeList = biDAO.findQueryCodeList();
	return queryCodeList;
    }

    @Override
    public BIResultSetVO executeQuery(BISearch vo) throws WccException {
	String querySql = biDAO.findQuerySql(vo.getQueryCode());
	Map<String, BIParameterFlat> paramObject = vo.getQueryParameterObject();

	if (BooleanUtils.isTrue(vo.getGetColumnsOnly())) {
	    querySql = querySql.replace(";", "");
	    // querySql = "SELECT * FROM (" + querySql + ") WHERE ROWNUM = 0";
	    querySql = "SELECT * FROM (" + querySql + ") WHERE LIMIT 0";
	}

	BIResultSet resList = biDAO.executeQuery(querySql, paramObject);
	return new BIResultSetVO(resList, 1, 500);

    }

    @Override
    public ExportBean exportResults(BISearch vo) throws WccException {
	try {
	    String querySql = biDAO.findQuerySql(vo.getQueryCode());
	    Map<String, BIParameterFlat> paramObject = vo.getQueryParameterObject();

	    BIResultSet resList = biDAO.executeQuery(querySql, paramObject);

	    String fileName = configProperties.get(ExportTypeEnum.BI_EXPORT.getPathPropertyName()).toString();

	    SimpleDateFormat sdf = new SimpleDateFormat(ConversionUtils.DATE_TIME_STAMP_MILLI);
	    fileName = fileName + "/" + sdf.format(new Date()) + "-bi.out.csv";

	    generateCsv(resList, fileName, null);

	    Path path = Paths.get(fileName);
	    byte[] bFile = Files.readAllBytes(path);

	    // convert file to byte[]
	    ExportBean exportBean = new ExportBean();
	    exportBean.setFileName(path.toFile().getName());
	    exportBean.setFileSize((long) bFile.length);
	    exportBean.setDocument(bFile);
	    exportBean.setCompletePath(path.toString());
	    exportBean.setContentType(FileFormatEnum.CSV.getContentType());
	    return exportBean;

	} catch (Exception e) {
	    throw new WccException(e);
	}

    }

    private void generateCsv(BIResultSet resultSet, String fileName, CSVPrinter csvPrinter) throws WccException {

	try {

	    if (csvPrinter == null) {
		File file = new File(fileName);
		if (!file.exists()) {
		    File directory = new File(file.getParent());
		    directory.mkdirs();
		    file.createNewFile();
		}

		OutputStreamWriter osw = new OutputStreamWriter((new FileOutputStream(file)), StandardCharsets.UTF_8);
		BufferedWriter bufferedWriter = new BufferedWriter(osw);
		bufferedWriter.write(ConstantUtils.BOM_CHAR); // Write BOM

		CSVFormat csvFormat = CSVFormat.DEFAULT.withRecordSeparator(ConstantUtils.CSV_NEW_LINE_SEPARATOR);
		csvFormat = csvFormat.withIgnoreSurroundingSpaces(true).withNullString("").withFirstRecordAsHeader();
		csvPrinter = new CSVPrinter(bufferedWriter, csvFormat);
	    }

	    if (resultSet != null && !resultSet.getData().isEmpty()) {
		csvPrinter.printRecord(resultSet.getColumns().keySet());

		for (Map<String, Object> row : resultSet.getData()) {
		    csvPrinter.printRecord(row.values());
		}
	    }

	    csvPrinter.flush();
	    csvPrinter.close();
	    csvPrinter = null;

	} catch (Exception e) {

	    if (csvPrinter != null) {
		try {
		    csvPrinter.close();
		    csvPrinter.flush();
		    csvPrinter = null;
		} catch (IOException e1) {
		    csvPrinter = null;
		}
	    }
	    LOGGER.error("Error: {}", e.getMessage());
	    throw new WccException(e);
	}
    }
}
