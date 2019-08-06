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

package org.wipo.connect.shared.business.import_work;

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
import org.wipo.connect.common.import_model.TransactionTypeEnum;
import org.wipo.connect.common.import_model.WorkRow;
import org.wipo.connect.common.import_model.WorkRowTypeEnum;
import org.wipo.connect.common.import_reader.WorkReader;
import org.wipo.connect.common.import_reader.WorkReaderCsv;
import org.wipo.connect.common.import_reader.WorkReaderExcel;
import org.wipo.connect.common.import_writer.WorkWriter;
import org.wipo.connect.common.import_writer.WorkWriterCsv;
import org.wipo.connect.common.import_writer.WorkWriterExcel;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.ConstantUtils;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.enumerator.WccWorkImportExceptionCodeEnum;
import org.wipo.connect.shared.exchange.dto.impl.Cmo;
import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.dto.impl.WorkImport;
import org.wipo.connect.shared.exchange.dto.impl.WorkImportDetail;
import org.wipo.connect.shared.exchange.enumerator.ImportStatusEnum;
import org.wipo.connect.shared.persistence.sftp.WorkSftpClient;

import net.bull.javamelody.MonitoredWithSpring;

@Service
@MonitoredWithSpring
public class ImportWorkServiceImpl implements ImportWorkService {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(ImportWorkServiceImpl.class);

    @Value("#{configProperties['import.work.pageSize']}")
    private Integer work_import_page_size;

    @Autowired
    private WorkSftpClient workSftpClient;

    @Autowired
    private WorkLoader workLoaderImpl;

    @Autowired
    private WorkRowValidator workRowValidatorImpl;

    @Autowired
    private ImportWorkDAOHelper importWorkDAOHelperImpl;

    @Autowired
    private ExecutorWorkAction executorWorkActionImpl;

    @Value("#{configProperties['env']}")
    private EnvironmentEnum env;
    @Value("#{configProperties['path.temp-dir']}")
    private String importTempDir;

    @Autowired
    private WorkCodeMapper codeMapperImpl;

    @Autowired
    private ImportMessagesDecoder workImportMessagesDecoder;

    @Override
    public void importWork() throws IOException, WccException {
	LOGGER.info("Start Work import");
	codeMapperImpl.init();
	List<WorkImport> importList = importWorkDAOHelperImpl.findWorkImportFromStatus(ImportStatusEnum.QUEUED);
	LOGGER.info("Find {} Work import file to processed", importList.size());
	WorkReader workReader = null;
	WorkWriter<List<WorkRow>> workWriter = null;
	List<WorkImportDetail> workImportDetailList = new ArrayList<>();
	int rowCount = 0;
	int insertedItems = 0;
	int lineWithError = 0;
	ImportStatusEnum statusToUpdate = null;

	for (WorkImport workImport : importList) {
	    try {
		Long importId = workImport.getIdWorkImport();
		importWorkDAOHelperImpl.updateWorkImportFromStatus(importId, ImportStatusEnum.IN_PROGRESS);
		LOGGER.info("Update WorkImport: setting status in {} ", ImportStatusEnum.IN_PROGRESS);
		String inputFileName = workImport.getInputFile().getFileName();
		String workImportDataOrigin = getCmoOriginCode(inputFileName);

		if (FilenameUtils.getExtension(inputFileName).equalsIgnoreCase(ImportFileTypeEnum.CSV.getExtension())) {
		    workReader = new WorkReaderCsv();
		    workWriter = new WorkWriterCsv(importTempDir, env, true);
		} else if (FilenameUtils.getExtension(inputFileName).equalsIgnoreCase(ImportFileTypeEnum.EXCEL.getExtension())) {
		    workReader = new WorkReaderExcel();
		    workWriter = new WorkWriterExcel(importTempDir, env, true);
		} else {
		    LOGGER.error("Invalid file type!");
		    throw new WccWorkImportException(WccWorkImportExceptionCodeEnum.GENERIC_ERROR);
		}

		String tempInputFile = getTempFile(workImport.getInputFile().getFileName());
		rowCount = 0;
		insertedItems = 0;
		lineWithError = 0;

		workReader.initializeReader(tempInputFile, EnvironmentEnum.SHARED, work_import_page_size);
		while (workReader.hasNextBlock()) {

		    // Load first work_import_page_size entity
		    List<WorkRow> workRows = workReader.getBlockEntity();
		    Queue<List<WorkRow>> entityRowQueue = loadQueuedEntityRow(workRows);
		    while (!entityRowQueue.isEmpty()) {
			List<WorkRow> entityRows = entityRowQueue.poll();
			Work work = null;
			Boolean rejectEntityRow = false;
			try {

			    TransactionTypeEnum transaction = workRowValidatorImpl.validateTransaction(entityRows);
			    workRowValidatorImpl.validateRows(transaction, entityRows);
			    workRowValidatorImpl.checkIfRejectAllRow(transaction, entityRows);
			    work = workLoaderImpl.loadRowsEntity(transaction, entityRows, workImportDataOrigin);
			    executorWorkActionImpl.executeAction(transaction, work, entityRows);
			    workWriter.writeRows(entityRows);

			} catch (WccWorkImportException e) {
			    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
			    rejectEntityRow = true;
			    setStatusError(e, entityRows);
			    workWriter.writeRows(entityRows);

			} catch (Exception e) {
			    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
			    setStatusErrorGeneric(entityRows);
			    workWriter.writeRows(entityRows);
			} finally {
			    rowCount += entityRows.size();

			    if (BooleanUtils.isFalse(rejectEntityRow)) {
				insertedItems += entityRows.stream().filter(it -> StringUtils.equalsIgnoreCase(it.getRowType(), WorkRowTypeEnum.MA.name()) &&
					StringUtils.equalsAnyIgnoreCase(it.getErrorCode(), WccWorkImportExceptionCodeEnum.NO_ERROR.getCode(),
						WccWorkImportExceptionCodeEnum.DURATION_FORMAT_ERROR.getCode(),
						WccWorkImportExceptionCodeEnum.GENRE_ERROR.getCode(),
						WccWorkImportExceptionCodeEnum.INSTRUMENT_UNKNOWN.getCode(),
						WccWorkImportExceptionCodeEnum.COUNTRY_CODE_ERROR.getCode(),
						WccWorkImportExceptionCodeEnum.COUNTRY_CODE_ERROR.getCode(),
						WccWorkImportExceptionCodeEnum.AF_CREATION_CLASS.getCode()))
					.count();
			    }
			    lineWithError += entityRows.stream().filter(it -> !it.getErrorCode().equalsIgnoreCase(WccWorkImportExceptionCodeEnum.NO_ERROR.getCode()))
				    .count();

			    WorkImportDetail wid = new WorkImportDetail();
			    wid.setFkWorkImport(importId);
			    wid.setFkWork(work == null ? null : work.getId());
			    workImportDetailList.add(wid);
			}

			workImport.setRowCount(rowCount);
			workImport.setInsertedItems(insertedItems);
			workImport.setLinesWithErrors(lineWithError);
			importWorkDAOHelperImpl.updateRowResult(workImport);
		    }

		    if (workWriter != null) {
			workWriter.flush();
		    }
		}
		try {
		    new File(tempInputFile).delete();
		} catch (Exception e) {
		    LOGGER.error("Error deleting temporary file {} ,", tempInputFile, e);
		}

		statusToUpdate = ImportStatusEnum.COMPLETED;

	    } catch (Exception e) {
		LOGGER.error("Error in " + WccUtils.getMethodName(), e);
		statusToUpdate = ImportStatusEnum.ERROR;
		importWorkDAOHelperImpl.saveOutputFile(workImport, e.getMessage().getBytes(ConstantUtils.CHARSET_UTF8));
	    } finally {
		if (workReader != null) {
		    workReader.closeReader();
		}

		if (workWriter != null) {
		    workWriter.closeStream();
		}

		workImport.setEndDate(new Date());
		importWorkDAOHelperImpl.updateRowResult(workImport);
		importWorkDAOHelperImpl.updateWorkImportFromStatus(workImport.getId(), statusToUpdate);

		LOGGER.info("Write output file for IpImport {}: ", workImport.getId());
		importWorkDAOHelperImpl.saveOutputFile(workImport, workWriter.getFileName());

		LOGGER.info("Update WorkImport {} : setting status to {} ", workImport.getId(), statusToUpdate);
	    }
	}
    }

    private Queue<List<WorkRow>> loadQueuedEntityRow(List<WorkRow> workRows) {
	String key = null;
	Queue<List<WorkRow>> queue = new LinkedList<>();
	List<WorkRow> workRowList = new ArrayList<>();
	queue.offer(workRowList);
	for (WorkRow row : workRows) {
	    if (null == key || StringUtils.equalsIgnoreCase(key, row.getId())) {
		key = row.getId();
		workRowList.add(row);
	    } else {
		key = row.getId();
		workRowList = new ArrayList<>();
		workRowList.add(row);
		queue.offer(workRowList);
	    }

	}
	return queue;
    }

    private String getTempFile(String filename) throws WccException {
	return workSftpClient.getWorkImportFileFromSftp(filename);
    }

    private void setStatusErrorGeneric(List<WorkRow> workRows) {
	for (WorkRow row : workRows) {
	    row.setErrorCode(WccWorkImportExceptionCodeEnum.GENERIC_ERROR.getCode());
	    row.setStatus(workImportMessagesDecoder.getErrorMessage(row.getErrorCode()));
	}
    }

    private void setStatusError(WccWorkImportException ex, List<WorkRow> workRows) {
	for (WorkRow row : workRows) {
	    if (StringUtils.isEmpty(row.getErrorCode()) || StringUtils.equals(row.getErrorCode(), WccWorkImportExceptionCodeEnum.NO_ERROR.getCode())) {
		row.setErrorCode(ex.getErrorCode().getCode());
	    }
	    row.setStatus(workImportMessagesDecoder.getErrorMessage(row.getErrorCode()));
	}
    }

    private String getCmoOriginCode(String inputFileName) throws WccWorkImportException {
	String ipImportDataOriginFromFile = StringUtils.substringBefore(inputFileName, "-").trim();

	Cmo cmo = codeMapperImpl.getCmoByCode(ipImportDataOriginFromFile);
	String cmoOriginCode;
	if (cmo != null) {
	    cmoOriginCode = cmo.getCode();
	} else {
	    throw new WccWorkImportException(WccWorkImportExceptionCodeEnum.FILENAME_ERROR);
	}
	return cmoOriginCode;
    }

    @Override
    public void markAllPendingAsError() {
	importWorkDAOHelperImpl.markAllPendingAsError();
    }

}
