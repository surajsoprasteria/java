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

package org.wipo.connect.shared.identifierprocessor.temp;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.logging.WipoLoggerFactory;

/**
 * Implementation of {@link org.wipo.connect.shared.business.task.WorkScheduledTask} interface.
 *
 * @author minervini
 *
 */
@Service
@Qualifier("identifierAssignerImpl")
public class IdentifierAssignerImpl implements IdentifierAssigner {
	
	private static final Logger LOGGER = WipoLoggerFactory.getLogger(IdentifierAssignerImpl.class);
	/*


    *//** The Constant LOGGER. *//*
    

    @Autowired
    private ImportIpService importIpService;

*/
    @Autowired
    @Qualifier("interestedPartyBusinessImpl")
    private InterestedPartyBusiness interestedPartyBusinessImpl;
/**/
   
    @Autowired
    private IpSftpClient ipSftpClient;

/*
    @Autowired
    @Qualifier("ipTaskDAOImpl")
    private IpTaskDAO ipTaskDAO;

    @Override
    public void processImport() throws Exception {
	importIpService.importInterestedParty();
    }
*/
    public void searchFilesToImport() throws WccException {
	try {

		
		System.out.println("Inside searchFilesToImport");
	    List<IpImport> ipImportList = ipSftpClient.getIpImportListFromSftp();
	    //System.out.println("Get list ipImportList" +ipImportList.toString() );
	    
	    for (IpImport ipImport : ipImportList) {
	    	System.out.println("ipImport file list element " +ipImport.toString() );
	    	interestedPartyBusinessImpl.insertNewImportFile(ipImport);
	    }

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }
    
    
/*
    @Override
    public void submitIp() throws WccException {

	List<IpTaskItem> taskItemList = ipTaskDAO.findIpTaskItemByStatus(SyncTaskStatusEnum.TO_SUBMIT.name());
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

	List<IpTaskItem> itemList = ipTaskDAO.findIpTaskItemByStatus(SyncTaskStatusEnum.SUBMITTED.name());

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

	    Long sharedIpId = ipList.getData().get(0).getId();

	    ipTaskDAO.updateTaskItemIp(item.getId(), sharedIpId);
	    ipTaskDAO.updateTaskItemResponseStatus(item.getId(), SyncTaskStatusEnum.COMPLETED);
	    ipTaskDAO.updateTaskItemStatus(item.getId(), SyncTaskStatusEnum.COMPLETED);
	}

    }

*/}
