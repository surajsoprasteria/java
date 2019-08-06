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

package org.wipo.connect.shared.business.task;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.ConversionUtils;
import org.wipo.connect.enumerator.SyncTaskStatusEnum;
import org.wipo.connect.shared.business.import_work.ImportWorkService;
import org.wipo.connect.shared.exchange.business.WorkBusiness;
import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.dto.impl.WorkImport;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskItem;
import org.wipo.connect.shared.exchange.enumerator.ExportTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.FileFormatEnum;
import org.wipo.connect.shared.exchange.vo.WorkSearchResultVO;
import org.wipo.connect.shared.exchange.vo.WorkSearchVO;
import org.wipo.connect.shared.persistence.dao.CommonDAO;
import org.wipo.connect.shared.persistence.dao.TitleDAO;
import org.wipo.connect.shared.persistence.dao.WorkDAO;
import org.wipo.connect.shared.persistence.dao.WorkIdentifierFlatDAO;
import org.wipo.connect.shared.persistence.dao.WorkStatusFlatDAO;
import org.wipo.connect.shared.persistence.dao.WorkTaskDAO;
import org.wipo.connect.shared.persistence.sftp.WorkSftpClient;

/**
 * Implementation of {@link org.wipo.connect.shared.business.task.WorkScheduledTask} interface.
 *
 * @author minervini
 *
 */
@Service
@Qualifier("workScheduledTaskImpl")
public class WorkScheduledTaskImpl implements WorkScheduledTask {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(WorkScheduledTaskImpl.class);

    @Autowired
    @Qualifier("workDAOImpl")
    private WorkDAO workDAO;

    @Autowired
    @Qualifier("titleDAOImpl")
    private TitleDAO titleDAO;

    @Autowired
    @Qualifier("workStatusFlatDAOImpl")
    private WorkStatusFlatDAO workStatusFlatDAO;

    @Autowired
    @Qualifier("workIdentifierFlatDAOImpl")
    private WorkIdentifierFlatDAO workIdentifierFlatDAO;

    @Autowired
    @Qualifier("workTaskDAOImpl")
    private WorkTaskDAO workTaskDAO;

    @Autowired
    @Qualifier("commonDAOImpl")
    private CommonDAO commonDAO;

    @Autowired
    @Qualifier("workBusinessImpl")
    private WorkBusiness workBusiness;

    @Autowired
    private ImportWorkService importWorkServiceImpl;

    @Autowired
    private Properties configProperties;

    @Autowired
    private WorkSftpClient workSftpClient;

    @Override
    public void submitWorkToCISNET() throws WccException {

	DateFormat dateFormat = new SimpleDateFormat(ConversionUtils.DATE_TIME_STAMP_MILLI);

	try {
	    List<WorkTaskItem> taskItemList = workTaskDAO.findWorkTaskItemByStatus(SyncTaskStatusEnum.TO_SUBMIT.toString());
	    Map<String, List<Work>> mapByCmo = new HashMap<>();

	    // export to csv and save to file system
	    for (WorkTaskItem item : taskItemList) {

		try {

		    Work work = workBusiness.convertTaskDetail(item.getWorkTaskDetail());
		    workTaskDAO.updateTaskItemStatus(item.getId(), SyncTaskStatusEnum.SUBMITTED);

		    if (!mapByCmo.containsKey(item.getCmoCode())) {
			mapByCmo.put(item.getCmoCode(), new ArrayList<Work>());
		    }

		    List<Work> workList = mapByCmo.get(item.getCmoCode());
		    workList.add(work);
		    mapByCmo.put(item.getCmoCode(), workList);

		} catch (Exception e) {
		    LOGGER.error("Error converting workTaskDetail id:" + item.getWorkTaskDetail().getId());
		}
	    }

	    mapByCmo.forEach((key, value) -> {
		String csvPath = null;

		try {
		    if (!value.isEmpty()) {
			String filePath = configProperties.get(ExportTypeEnum.WORK_LIST_VIEW_EXPORT.getPathPropertyName()).toString();
			csvPath = workBusiness.createExportFileByWorkList(value, FileFormatEnum.CSV, filePath);
		    }

		    if (StringUtils.isNotEmpty(csvPath)) {
			String extension = FilenameUtils.getExtension(csvPath);
			String timestamp = dateFormat.format(new Date());
			workSftpClient.writeWorkExportFileToSftp(key + "-" + timestamp + "." + extension, csvPath);
			try {
			    File f = new File(csvPath);
			    f.delete();
			} catch (Exception e) {
			    LOGGER.error("Error deleting temporary file {} ,", csvPath, e);
			}
		    }
		} catch (Exception e) {
		    LOGGER.error("Error generete file for cmo {}", key);
		}
	    });

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

    }

    @Override
    public void searchFilesToImport() throws WccException {

	try {

	    List<WorkImport> workImportList = workSftpClient.getWorkImportListFromSftp();

	    for (WorkImport workImport : workImportList) {
		workBusiness.insertNewImportFile(workImport);
	    }

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    @Override
    public void processCSIResults() throws WccException {

	List<WorkTaskItem> itemList = workTaskDAO.findWorkTaskItemByStatus(SyncTaskStatusEnum.SUBMITTED.toString());

	for (WorkTaskItem item : itemList) {

	    Work workToCheck = workBusiness.convertTaskDetail(item.getWorkTaskDetail());
	    String wipoLocalId = workToCheck.getMainId();

	    WorkSearchVO searchVO = new WorkSearchVO();
	    searchVO.setWipoLocalId(wipoLocalId);
	    searchVO.setDisableOriginCheck(true);

	    WorkSearchResultVO workList = workBusiness.findWork(searchVO);

	    if (workList.getData().isEmpty()) {
		// this work has not been imported yet
		continue;
	    }

	    if (workList.getData().size() > 1) {
		// this should never happen... dirty data ???
		LOGGER.error("Too many results found ({}) for WIPO_CONNECT_LOCAL_ID: {}", workList.getData().size(), wipoLocalId);
		continue;
	    }

	    Work sharedWork = workList.getData().get(0);
	    sharedWork = workBusiness.findById(sharedWork.getId());

	    // WorkTaskCsiResult csiResult = new WorkTaskCsiResult();
	    // csiResult.setFkWorkTaskItem(item.getId());
	    // csiResult.setStatusCode(CsiResultStatusEnum.ASSIGNED.name());

	    workTaskDAO.updateTaskItemWork(item.getId(), sharedWork.getId());

	    // compare local and shared works
	    // boolean workCheck = WorkUtils.compareLocalAndSharedWork(workToCheck, sharedWork);
	    // if (workCheck) {
	    workTaskDAO.updateTaskItemResponseStatus(item.getId(), SyncTaskStatusEnum.COMPLETED);
	    // } else {
	    // workTaskDAO.updateTaskItemResponseStatus(item.getId(), TaskStatusEnum.ERROR);
	    // }

	    // workTaskDAO.insertWorkTaskCsiResult(csiResult);
	    workTaskDAO.updateTaskItemStatus(item.getId(), SyncTaskStatusEnum.COMPLETED);
	}

    }

    @Override
    public void processImport() throws Exception {
	importWorkServiceImpl.importWork();
    }

}
