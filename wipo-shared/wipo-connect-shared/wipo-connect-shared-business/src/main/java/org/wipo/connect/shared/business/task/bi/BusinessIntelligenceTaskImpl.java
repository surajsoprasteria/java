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

package org.wipo.connect.shared.business.task.bi;

import java.io.IOException;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccValidationException;
import org.wipo.connect.common.import_queries_reader.QueryReaderExcel;
import org.wipo.connect.common.import_queries_reader.QueryRow;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.shared.persistence.dao.BIDAO;
import org.wipo.connect.shared.persistence.sftp.BiSftpClient;

import net.bull.javamelody.MonitoredWithSpring;

@Service
@MonitoredWithSpring
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class BusinessIntelligenceTaskImpl implements BusinessIntelligenceTask {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(BusinessIntelligenceTaskImpl.class);

    @Value("#{configProperties['biQuery.import-file']}")
    private String importQueryFile;

    @Autowired
    private BIDAO biDAO;

    @Autowired
    private BiSftpClient biSftpClient;

    @Override
    public void importQueries() throws WccException, IOException {
	try {

	    byte[] file = biSftpClient.readQueryImportFileFromSftp(importQueryFile);

	    if (file == null) {
		LOGGER.info("No BI File found to import");
	    } else if (file.length == 0) {
		LOGGER.info("BI import file is empty");
	    } else {

		String checksum = getMD5ByFileName(file);
		if (!biDAO.findExistChecksum(checksum)) {

		    List<QueryRow> queriesToLoad = loadQueryToImport(file);
		    if (!queriesToLoad.isEmpty()) {

			LOGGER.info("Found {} query to import", queriesToLoad.size());

			biDAO.deleteAllQuery();

			for (QueryRow query : queriesToLoad) {
			    biDAO.insertQuery(query.getQueryName(), query.getQuerySql(), query.getQueryParameter(), checksum);
			}
		    } else {
			LOGGER.info("No query found to import in BI File");
		    }

		} else {
		    LOGGER.info("BI File already processed");

		}

	    }
	} catch (WccValidationException e) {
	    LOGGER.error("Error: {}", e.getMessage());
	} catch (Exception e) {
	    LOGGER.error("Error: ", e);
	}

    }

    private String getMD5ByFileName(byte[] file) {
	try {
	    return DigestUtils.md5Hex(file);
	} catch (Exception e) {
	    LOGGER.warn("File not found");
	    return null;
	}
    }

    private List<QueryRow> loadQueryToImport(byte[] file) throws WccException {

	try {
	    QueryReaderExcel queryReader = new QueryReaderExcel();
	    queryReader.initializeReader(file);

	    List<QueryRow> queriesToLoad = queryReader.loadQueryList();
	    queryReader.closeReader();

	    return queriesToLoad;
	} catch (WccException e) {
	    throw e;
	} catch (Exception e) {
	    throw new WccException(e);
	}
    }

}
