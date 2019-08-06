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

package org.wipo.connect.shared.business.import_ip;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.i18n.ImportMessagesDecoder;
import org.wipo.connect.common.import_model.EnvironmentEnum;
import org.wipo.connect.common.import_model.ImportFileTypeEnum;
import org.wipo.connect.common.import_model.IpRow;
import org.wipo.connect.common.import_model.IpRowTypeEnum;
import org.wipo.connect.common.import_model.TransactionTypeEnum;
import org.wipo.connect.common.import_reader.IpReader;
import org.wipo.connect.common.import_reader.IpReaderCsv;
import org.wipo.connect.common.import_reader.IpReaderExcel;
import org.wipo.connect.common.import_writer.IpWriter;
import org.wipo.connect.common.import_writer.IpWriterCsv;
import org.wipo.connect.common.import_writer.IpWriterExcel;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.ConstantUtils;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.enumerator.WccIpImportExceptionCodeEnum;
import org.wipo.connect.shared.exchange.dto.impl.Cmo;
import org.wipo.connect.shared.exchange.dto.impl.InterestedParty;
import org.wipo.connect.shared.exchange.dto.impl.IpImport;
import org.wipo.connect.shared.exchange.dto.impl.IpImportDetail;
import org.wipo.connect.shared.exchange.enumerator.ImportStatusEnum;

import net.bull.javamelody.MonitoredWithSpring;

/**
 *
 * @author pasquale.minervini
 *
 */
@Service
@MonitoredWithSpring
public class ImportIpServiceImpl implements ImportIpService {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(ImportIpServiceImpl.class);

    @Autowired
    private ImportIpDAOHelper importIpDAOHelper;
    @Autowired
    private ExecutorIpAction executorIpAction;
    @Autowired
    private ImportServiceHelper importServiceHelper;
    @Autowired
    private IpRowValidator ipRowValidator;
    @Autowired
    private IpRowLoader ipRowLoader;
    @Autowired
    private IpCodeMapper ipCodeMapper;
    @Autowired
    private ImportMessagesDecoder ipImportMessagesDecoder;

    @Value("#{configProperties['import.rightOwner.pageSize']}")
    private Integer ip_import_page_size;
    @Value("#{configProperties['env']}")
    private EnvironmentEnum env;
    @Value("#{configProperties['path.temp-dir']}")
    private String importTempDir;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void importInterestedParty() throws IOException, WccException {

	LOGGER.info("Start RightOwner import");
	ipCodeMapper.init();
	List<IpImport> importList = importIpDAOHelper.findIpImportFromStatus(ImportStatusEnum.QUEUED);
	LOGGER.info("Find {} RO import file to processed", importList.size());
	IpReader ipReader = null;
	IpWriter ipWriter = null;
	int rowCount = 0;
	int insertedItems = 0;
	int lineWithError = 0;
	ImportStatusEnum statusToUpdate = null;
	List<IpImportDetail> ipImportDetailList = new ArrayList<>();
	for (IpImport ipImport : importList) {

	    try {
		Long importId = ipImport.getIdIpImport();
		statusToUpdate = ImportStatusEnum.IN_PROGRESS;
		importIpDAOHelper.updateIpImportFromStatus(importId, statusToUpdate);
		LOGGER.info("Update IpImport: setting status in {} ", statusToUpdate);
		String inputFileName = ipImport.getInputFile().getFileName();
		String ipImportDataOrigin = getCmoOriginCode(inputFileName);

		if (FilenameUtils.getExtension(inputFileName).equalsIgnoreCase(ImportFileTypeEnum.CSV.getExtension())) {
		    ipReader = new IpReaderCsv();
		    ipWriter = new IpWriterCsv(importTempDir, env, true);
		} else if (FilenameUtils.getExtension(inputFileName).equalsIgnoreCase(ImportFileTypeEnum.EXCEL.getExtension())) {
		    ipReader = new IpReaderExcel();
		    ipWriter = new IpWriterExcel(importTempDir, env, true);
		} else {
		    LOGGER.error("Invalid file type!");
		    throw new WccInterestedPartyImportException(WccIpImportExceptionCodeEnum.GENERIC_ERROR);
		}

		String tempInputFile = importServiceHelper.getTempFile(inputFileName);
		rowCount = 0;
		insertedItems = 0;
		lineWithError = 0;

		ipReader.initializeReader(tempInputFile, EnvironmentEnum.SHARED, ip_import_page_size);

		while (ipReader.hasNextBlock()) {
		    List<IpRow> ipRows = ipReader.getBlockEntity();
		    Queue<List<IpRow>> entityRowQueue = loadQueuedEntityRow(ipRows);
		    while (!entityRowQueue.isEmpty()) {
			List<IpRow> entityRows = entityRowQueue.poll();
			Boolean rejectEntityRow = false;
			InterestedParty ip = null;

			try {
			    TransactionTypeEnum transaction = ipRowValidator.validateTransaction(entityRows);
			    ipRowValidator.validate(transaction, entityRows);
			    ipRowValidator.checkIfRejectAllRow(transaction, entityRows);
			    ip = ipRowLoader.load(transaction, entityRows, ipImportDataOrigin);
			    executorIpAction.executeTransaction(transaction, ip, entityRows);

			    ipWriter.writeRows(entityRows);
			} catch (WccInterestedPartyImportException e) {
			    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
			    rejectEntityRow = true;
			    setErrorCode(entityRows, e.getErrorCode());
			    ipWriter.writeRows(entityRows);
			} catch (Exception e) {
			    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
			    setGenericErrorCode(entityRows, e.getMessage());
			    ipWriter.writeRows(entityRows);
			} finally {
			    rowCount += entityRows.size();
			    if (BooleanUtils.isFalse(rejectEntityRow)) {
				insertedItems += entityRows.stream()
					.filter(it -> StringUtils.equalsIgnoreCase(it.getRowType(), IpRowTypeEnum.MA.name()) && StringUtils.equalsAnyIgnoreCase(it.getErrorCode(),
						WccIpImportExceptionCodeEnum.NO_ERROR.getCode(),
						WccIpImportExceptionCodeEnum.COUNTRY_OF_BIRTH_UNKNOWN_ERROR.getCode(),
						WccIpImportExceptionCodeEnum.DEATH_DATE_FORMAT_ERROR.getCode(),
						WccIpImportExceptionCodeEnum.INVALID_DEATH_DATE_ERROR.getCode(),
						WccIpImportExceptionCodeEnum.BIRTH_DATE_FORMAT_ERROR.getCode(),
						WccIpImportExceptionCodeEnum.SEX_UNKNOWN_ERROR.getCode(),
						WccIpImportExceptionCodeEnum.CITIZENSHIP_UNKNOWN_ERROR.getCode()))
					.count();
			    }
			    lineWithError += entityRows.stream().filter(it -> !it.getErrorCode().equalsIgnoreCase(WccIpImportExceptionCodeEnum.NO_ERROR.getCode())).count();

			    // Save ipImportDetail -----------
			    IpImportDetail ipId = new IpImportDetail();
			    ipId.setFkIpImport(ipImport.getId());
			    ipId.setFkInterestedParty(null == ip ? null : ip.getId());
			    ipImportDetailList.add(ipId);
			    // ----------------------------------

			}

			ipImport.setRowCount(rowCount);
			ipImport.setInsertedItems(insertedItems);
			ipImport.setLinesWithErrors(lineWithError);
			importIpDAOHelper.updateRowResult(ipImport);
		    }

		    if (ipWriter != null) {
			ipWriter.flush();
		    }
		}

		try {
		    LOGGER.debug("deleting temporary file {} ,", tempInputFile);
		    new File(tempInputFile).delete();
		} catch (Exception e) {
		    LOGGER.error("Error deleting temporary file {} ,", tempInputFile, e);
		}

		statusToUpdate = ImportStatusEnum.COMPLETED;
	    } catch (Exception e) {
		LOGGER.error("Error in " + WccUtils.getMethodName(), e);
		statusToUpdate = ImportStatusEnum.ERROR;
		importServiceHelper.saveOutputFile(ipImport, e.getMessage().getBytes(ConstantUtils.CHARSET_UTF8));
	    } finally {

		if (ipReader != null) {
		    ipReader.closeReader();
		}

		if (ipWriter != null) {
		    ipWriter.closeStream();
		}

		ipImport.setEndDate(new Date());
		importIpDAOHelper.updateRowResult(ipImport);
		importIpDAOHelper.updateIpImportFromStatus(ipImport.getId(), statusToUpdate);

		LOGGER.info("Write output file for IpImport {}: ", ipImport.getId());
		importServiceHelper.saveOutputFile(ipImport, ipWriter.getFileName());

		LOGGER.info("Update IpImport {} : setting status to {} ", ipImport.getId(), statusToUpdate);

	    }
	}
    }

    private Queue<List<IpRow>> loadQueuedEntityRow(List<IpRow> ipRows) {
	String key = null;
	Queue<List<IpRow>> queue = new LinkedList<>();
	List<IpRow> ipRowList = new ArrayList<>();
	queue.offer(ipRowList);
	for (IpRow row : ipRows) {
	    if (null == key || StringUtils.equalsIgnoreCase(key, row.getIdField())) {
		key = row.getIdField();
		ipRowList.add(row);
	    } else {
		key = row.getIdField();
		ipRowList = new ArrayList<>();
		ipRowList.add(row);
		queue.offer(ipRowList);
	    }

	}
	return queue;
    }

    private void setErrorCode(List<IpRow> entityRow, WccIpImportExceptionCodeEnum errorCode) {
	entityRow.forEach(ipRow -> {
	    if (StringUtils.isEmpty(ipRow.getErrorCode()) || StringUtils.equals(ipRow.getErrorCode(), WccIpImportExceptionCodeEnum.NO_ERROR.getCode())) {
		ipRow.setErrorCode(errorCode.getCode());
	    }
	    ipRow.setStatus(ipImportMessagesDecoder.getErrorMessage(ipRow.getErrorCode()));
	});
    }

    private void setGenericErrorCode(List<IpRow> entityRow, String errorCode) {
	entityRow.forEach(ipRow -> {
	    ipRow.setErrorCode(WccIpImportExceptionCodeEnum.GENERIC_ERROR.getCode());
	    ipRow.setStatus(ipImportMessagesDecoder.getErrorMessage(ipRow.getErrorCode()));
	});
    }

    private String getCmoOriginCode(String inputFileName) throws WccInterestedPartyImportException {
	String ipImportDataOriginFromFile = StringUtils.substringBefore(inputFileName, "-").trim();

	Cmo cmo = ipCodeMapper.getCmoByCode(ipImportDataOriginFromFile);
	String cmoOriginCode;
	if (cmo != null) {
	    cmoOriginCode = cmo.getCode();
	} else {
	    throw new WccInterestedPartyImportException(WccIpImportExceptionCodeEnum.FILENAME_ERROR);
	}
	return cmoOriginCode;
    }

    @Override
    public void markAllPendingAsError() {
	importIpDAOHelper.markAllPendingAsError();
    }

}
