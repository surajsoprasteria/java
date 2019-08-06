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
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.enumerator.SyncTaskStatusEnum;
import org.wipo.connect.shared.business.import_ip.ImportIpService;
import org.wipo.connect.shared.exchange.business.InterestedPartyBusiness;
import org.wipo.connect.shared.exchange.dto.impl.InterestedParty;
import org.wipo.connect.shared.exchange.dto.impl.IpImport;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskItem;
import org.wipo.connect.shared.exchange.enumerator.ExportTypeEnum;
import org.wipo.connect.shared.exchange.vo.InterestedPartySearchResultVO;
import org.wipo.connect.shared.exchange.vo.InterestedPartySearchVO;
import org.wipo.connect.shared.persistence.dao.IpTaskDAO;
import org.wipo.connect.shared.persistence.sftp.IpSftpClient;

/**
 * Implementation of {@link org.wipo.connect.shared.business.task.WorkScheduledTask} interface.
 *
 * @author minervini
 *
 */
@Service
@Qualifier("ipScheduledTaskImpl")
public class IpScheduledTaskImpl implements IpScheduledTask {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(IpScheduledTaskImpl.class);

    @Autowired
    private ImportIpService importIpService;

    @Autowired
    @Qualifier("interestedPartyBusinessImpl")
    private InterestedPartyBusiness interestedPartyBusiness;

    @Autowired
    private IpSftpClient ipSftpClient;

    @Autowired
    @Qualifier("ipTaskDAOImpl")
    private IpTaskDAO ipTaskDAO;

    @Override
    public void processImport() throws Exception {
	importIpService.importInterestedParty();
    }

    @Override
    public void searchFilesToImport() throws WccException {
	try {

	    List<IpImport> ipImportList = ipSftpClient.getIpImportListFromSftp();

	    for (IpImport ipImport : ipImportList) {
		interestedPartyBusiness.insertNewImportFile(ipImport);
	    }

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    @Override
    public void submitIp() throws WccException {

	List<IpTaskItem> taskItemList = ipTaskDAO.findIpTaskItemByStatus(SyncTaskStatusEnum.TO_SUBMIT.toString());
	Map<String, List<IpTaskItem>> ipTaskItemForCMO = new HashMap<>();
	DateFormat dateFormat = new SimpleDateFormat(ConversionUtils.DATE_TIME_STAMP_MILLI);

	for (IpTaskItem item : taskItemList) {
	    if (!ipTaskItemForCMO.containsKey(item.getCmoCode())) {
		List<IpTaskItem> temp = new ArrayList<>();
		temp.add(item);
		ipTaskItemForCMO.put(item.getCmoCode(), temp);
	    } else {
		ipTaskItemForCMO.get(item.getCmoCode()).add(item);
	    }
	}

	for (String k : ipTaskItemForCMO.keySet()) {
	    List<IpTaskItem> v = ipTaskItemForCMO.get(k);

	    String csvPath = null;
	    try {
		List<InterestedParty> toSubmitList = new ArrayList<>();
		for (IpTaskItem item : v) {
		    try {
			InterestedParty ip = interestedPartyBusiness.convertTaskDetail(item.getIpTaskItemDetail());
			ipTaskDAO.updateTaskItemStatus(item.getId(), SyncTaskStatusEnum.SUBMITTED);
			toSubmitList.add(ip);
		    } catch (Exception e) {
			LOGGER.error("Error converting workTaskDetail id:" + item.getIpTaskItemDetail().getId(), e);
		    }
		}

		if (!toSubmitList.isEmpty()) {
		    csvPath = interestedPartyBusiness.createExportFileByIpList(toSubmitList, ExportTypeEnum.IP_LIST_VIEW_EXPORT);
		}

		if (StringUtils.isNotEmpty(csvPath)) {
		    String extension = FilenameUtils.getExtension(csvPath);
		    String timestamp = dateFormat.format(new Date());
		    ipSftpClient.writeIpExportFileSubmitToSftp(k + "-" + timestamp + "." + extension, csvPath);
		    try {
			File f = new File(csvPath);
			f.delete();
		    } catch (Exception e) {
			LOGGER.error("Error deleting temporary file {} ,", csvPath, e);
		    }
		}
	    } catch (Exception e) {
		LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    }
	}

    }

    @Override
    public void processExternalResults() throws WccException {

	List<IpTaskItem> itemList = ipTaskDAO.findIpTaskItemByStatus(SyncTaskStatusEnum.SUBMITTED.toString());

	for (IpTaskItem item : itemList) {

	    InterestedParty ipToCheck = interestedPartyBusiness.convertTaskDetail(item.getIpTaskItemDetail());
	    String wipoLocalId = ipToCheck.getMainId();

	    InterestedPartySearchVO searchVO = new InterestedPartySearchVO();
	    searchVO.setWipoLocalId(wipoLocalId);
	    searchVO.setDisableOriginCheck(true);

	    InterestedPartySearchResultVO ipList = interestedPartyBusiness.findInterestedParty(searchVO);

	    if (ipList.getData().isEmpty()) {
		// this ip has not been imported yet
		continue;
	    }

	    if (ipList.getData().size() > 1) {
		// this should never happen... dirty data ???
		LOGGER.error("Too many results found ({}) for WIPO_CONNECT_LOCAL_ID: {}", ipList.getData().size(), wipoLocalId);
		continue;
	    }

	    InterestedParty sharedIp = ipList.getData().get(0);
	    sharedIp = interestedPartyBusiness.findByIdIp(sharedIp.getId());

	    ipTaskDAO.updateTaskItemIp(item.getId(), sharedIp.getId());

	    // compare local and shared works
	    // boolean ipCheck = InterestedPartyUtils.compareLocalAndSharedIp(ipToCheck, sharedIp);
	    // if (ipCheck) {
	    ipTaskDAO.updateTaskItemResponseStatus(item.getId(), SyncTaskStatusEnum.COMPLETED);
	    // } else {
	    // ipTaskDAO.updateTaskItemResponseStatus(item.getId(), TaskStatusEnum.ERROR);
	    // }

	    // workTaskDAO.insertWorkTaskCsiResult(csiResult);
	    ipTaskDAO.updateTaskItemStatus(item.getId(), SyncTaskStatusEnum.COMPLETED);
	}

    }

}
