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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.import_model.EnvironmentEnum;
import org.wipo.connect.common.import_model.IpRow;
import org.wipo.connect.common.import_writer.IpWriter;
import org.wipo.connect.common.import_writer.IpWriterCsv;
import org.wipo.connect.common.import_writer.IpWriterExcel;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.mapping.DozerUtility;
import org.wipo.connect.common.querypagination.PageInfo;
import org.wipo.connect.common.utils.ConstantUtils;
import org.wipo.connect.common.utils.ConversionUtils;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.enumerator.IdentifierManagerRefTableEnum;
import org.wipo.connect.enumerator.OrderByExpressionEnum;
import org.wipo.connect.enumerator.SyncTaskStatusEnum;
import org.wipo.connect.shared.business.utils.DozerHelper;
import org.wipo.connect.shared.exchange.business.InterestedPartyBusiness;
import org.wipo.connect.shared.exchange.dto.impl.Affiliation;
import org.wipo.connect.shared.exchange.dto.impl.AffiliationDomain;
import org.wipo.connect.shared.exchange.dto.impl.Cmo;
import org.wipo.connect.shared.exchange.dto.impl.CreationClassFlat;
import org.wipo.connect.shared.exchange.dto.impl.GroupDTO;
import org.wipo.connect.shared.exchange.dto.impl.GroupDetailDTO;
import org.wipo.connect.shared.exchange.dto.impl.ImportStatusFlat;
import org.wipo.connect.shared.exchange.dto.impl.InterestedParty;
import org.wipo.connect.shared.exchange.dto.impl.InterestedPartyIdentifierFlat;
import org.wipo.connect.shared.exchange.dto.impl.InterestedPartyStatusFlat;
import org.wipo.connect.shared.exchange.dto.impl.IpImport;
import org.wipo.connect.shared.exchange.dto.impl.IpIndexQueue;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskElaborationResult;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskItem;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskItemDetail;
import org.wipo.connect.shared.exchange.dto.impl.IpiRightTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.IpiRoleFlat;
import org.wipo.connect.shared.exchange.dto.impl.Name;
import org.wipo.connect.shared.exchange.dto.impl.Territory;
import org.wipo.connect.shared.exchange.enumerator.ExportTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.FileFormatEnum;
import org.wipo.connect.shared.exchange.enumerator.IPDeleteErrorEnum;
import org.wipo.connect.shared.exchange.enumerator.ImportStatusEnum;
import org.wipo.connect.shared.exchange.enumerator.IndexQueueActionEnum;
import org.wipo.connect.shared.exchange.enumerator.InterestedPartyStatusEnum;
import org.wipo.connect.shared.exchange.enumerator.IpiRightTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.MassiveActionsEnum;
import org.wipo.connect.shared.exchange.enumerator.TerritoryOrderTypeEnum;
import org.wipo.connect.shared.exchange.exception.IpDeleteErrorException;
import org.wipo.connect.shared.exchange.index.doc.InterestedPartyDoc;
import org.wipo.connect.shared.exchange.utils.DTOUtils;
import org.wipo.connect.shared.exchange.utils.importexport.ExportBean;
import org.wipo.connect.shared.exchange.utils.importexport.ImportExportHelper;
import org.wipo.connect.shared.exchange.vo.InterestedPartySearchResultVO;
import org.wipo.connect.shared.exchange.vo.InterestedPartySearchVO;
import org.wipo.connect.shared.exchange.vo.NameGroupResultVO;
import org.wipo.connect.shared.exchange.vo.NameSearchVO;
import org.wipo.connect.shared.indexing.dao.InterestedPartyIndexDao;
import org.wipo.connect.shared.persistence.dao.AffiliationDAO;
import org.wipo.connect.shared.persistence.dao.CmoDAO;
import org.wipo.connect.shared.persistence.dao.CommonDAO;
import org.wipo.connect.shared.persistence.dao.CreationClassDAO;
import org.wipo.connect.shared.persistence.dao.DerivedViewDAO;
import org.wipo.connect.shared.persistence.dao.IdentifierManagerDAO;
import org.wipo.connect.shared.persistence.dao.InterestedPartyDAO;
import org.wipo.connect.shared.persistence.dao.IpImportDAO;
import org.wipo.connect.shared.persistence.dao.IpIndexQueueDAO;
import org.wipo.connect.shared.persistence.dao.IpTaskDAO;
import org.wipo.connect.shared.persistence.dao.NameDAO;
import org.wipo.connect.shared.persistence.dao.RightTypeDAO;
import org.wipo.connect.shared.persistence.sftp.IpSftpClient;

import net.bull.javamelody.MonitoredWithSpring;

@Service
@MonitoredWithSpring
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class InterestedPartyBusinessImpl implements InterestedPartyBusiness {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(InterestedPartyBusinessImpl.class);

    @Value("#{configProperties['workGroup.nameType'].split(',')}")
    private List<String> groupNameTypeList;

    @Value("#{configProperties['solr.rebuild-page-size']}")
    private Integer solrRebuildPageSize;

    @Value("#{configProperties['export.rightOwner.pageSize']}")
    private Integer exportRightOwnerPageSize;

    /** The interested party DAO. */
    @Autowired
    private InterestedPartyDAO interestedPartyDAO;

    /** The affiliation DAO. */
    @Autowired
    private AffiliationDAO affiliationDAO;

    /** The ip task DAO. */
    @Autowired
    private IpTaskDAO ipTaskDAO;

    /** The name DAO. */
    @Autowired
    private NameDAO nameDAO;

    /** The common DAO. */
    @Autowired
    private CommonDAO commonDAO;

    /** The ip import DAO. */
    @Autowired
    private IpImportDAO ipImportDAO;

    /** The ip index queue dao. */
    @Autowired
    private IpIndexQueueDAO ipIndexQueueDAO;

    /** The interested party index dao. */
    @Autowired
    private InterestedPartyIndexDao interestedPartyIndexDao;

    /** The dozer helper. */
    @Autowired
    private DozerHelper dozerHelper;

    /** The derived view dao. */
    @Autowired
    private DerivedViewDAO derivedViewDAO;

    @Autowired
    private IdentifierManagerDAO identifierManagerDAO;

    @Autowired
    private ImportExportHelper importExportHelper;

    /** The dozer utility. */
    @Autowired
    private DozerUtility dozerUtility;

    @Autowired
    private CmoDAO cmoDAO;

    @Autowired
    private RightTypeDAO rightTypeDAO;

    @Autowired
    private CreationClassDAO creationClassDAO;

    @Autowired
    private Properties configProperties;

    @Autowired
    private IpSftpClient ipSftpClient;

    ///////////////////////////////////////////////////// CRUD Interested Party/////////////////////////////////////////////////////
    @ExecutionTimeTrackable
    @Loggable
    @Override
    public InterestedParty findById(Long idInterestedParty, String code, List<String> creationClassCodeList) throws WccException {
	try {
	    InterestedParty ip = interestedPartyDAO.findInterestedPartyById(idInterestedParty, code);
	    ip.setAffiliationList(affiliationDAO.findByInterestedParty(ip.getId(), creationClassCodeList));
	    return ip;
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

    }

    @ExecutionTimeTrackable
    @Loggable
    @Override
    public InterestedParty findByIdIp(Long idInterestedParty) throws WccException {
	try {
	    InterestedParty ip = interestedPartyDAO.find(idInterestedParty);
	    ip.setAffiliationList(affiliationDAO.findByInterestedParty(ip.getId(), null));
	    return ip;
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    @Override
    public InterestedParty findByMainId(String mainId) throws WccException {
	try {
	    Long idIp = interestedPartyDAO.findInterestedPartyIdByIpMainId(mainId, false);
	    return findByIdIp(idIp);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

    }

    @Override
    public InterestedPartySearchResultVO findInterestedPartyForWs(InterestedPartySearchVO searchVO) throws WccException {
	return findInterestedParty(searchVO, searchVO.getPaginationStart(), searchVO.getPaginationLength());
    }

    @Override
    public InterestedPartySearchResultVO findInterestedParty(InterestedPartySearchVO searchVO) throws WccException {
	if (searchVO.getPaginationStart() == null || searchVO.getPaginationLength() == null) {
	    return findInterestedParty(searchVO, 0, ConstantUtils.DEFAULT_LIMIT);
	} else {
	    return findInterestedParty(searchVO, searchVO.getPaginationStart(), searchVO.getPaginationLength());
	}
    }

    @Override
    public InterestedParty insertOrUpdate(InterestedParty interestedParty) throws WccException {
	InterestedParty ipOut;
	try {
	    if (interestedParty.getId() == null) {

		ipOut = insert(interestedParty);

	    } else {

		ipOut = update(interestedParty);

	    }
	    updateSolrIndex(ipOut);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return ipOut;
    }

    @ExecutionTimeTrackable
    @Loggable
    @Override
    public boolean isTaskComplete(String taskCode) throws WccException {
	int rows;
	try {
	    rows = this.ipTaskDAO.selectTaskByCodeAndStatus(taskCode, SyncTaskStatusEnum.COMPLETED);
	    if (rows > 0) {
		return true;
	    }
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return false;
    }

    @ExecutionTimeTrackable
    @Loggable
    @Override
    public void storeTaskItemDetails(List<IpTaskItem> ipTaskItemList, String taskCode, String cmoCode) throws WccException {
	try {

	    Long idTask = this.ipTaskDAO.insertTask(taskCode, cmoCode);

	    for (IpTaskItem ipTaskItem : ipTaskItemList) {
		Long idTaskItem = this.ipTaskDAO.insertTaskItem(idTask, null, SyncTaskStatusEnum.TO_SUBMIT.name(), ipTaskItem.getProgr(), ipTaskItem.getItemCode());
		IpTaskItemDetail ipTaskitemDetail = ipTaskItem.getIpTaskItemDetail();
		ipTaskitemDetail.setFkIpTaskItem(idTaskItem);
		this.ipTaskDAO.insertIpTaskItemDetail(ipTaskitemDetail);
	    }
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    @Override
    public IpImport insertNewImportFile(IpImport ipImport) throws WccException {
	try {
	    String filename = ipImport.getInputFile().getFileName();
	    Long count = ipImportDAO.countIpImportFileByName(filename);

	    if (Long.compare(count, 0L) == 0) {
		if (ipImport.getFkStatus() == null) {
		    ImportStatusFlat status = commonDAO.findImportStatusByCode(ImportStatusEnum.QUEUED);
		    ipImport.setFkStatus(status.getId());
		}
		return ipImportDAO.insertIpImport(ipImport);

	    } else {
		LOGGER.debug("The file {} is already present... skipping", filename);
	    }

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return null;
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<InterestedPartyStatusFlat> findAllIPStatus() {
	return this.interestedPartyDAO.findAllIPStatus();
    }

    @Override
    public Long findIpByName(Long idName) throws WccException {
	return this.nameDAO.findIpByName(idName);
    }

    @Loggable(level = "INFO")
    @ExecutionTimeTrackable
    private InterestedPartySearchResultVO findInterestedParty(InterestedPartySearchVO searchVO, int start, int length) throws WccException {

	try {
	    List<InterestedParty> resultList = new ArrayList<>();
	    Integer totResults;
	    Integer draw;

	    if (BooleanUtils.isTrue(searchVO.getIsSimpleSearch())) {
		Page<InterestedPartyDoc> docList = interestedPartyIndexDao.search(searchVO, start, length); // invoke search solr
		Map<Long, BigDecimal> scoreDocMap = docList.getContent().stream().collect(
			Collectors.toMap(InterestedPartyDoc::getId, InterestedPartyDoc::getScore, (u, v) -> {
			    throw new IllegalStateException(String.format("Duplicate key %s", u));
			}, LinkedHashMap::new));

		totResults = Math.toIntExact(docList.getTotalElements());
		draw = searchVO.getPaginationDraw();

		if (!scoreDocMap.isEmpty()) {

		    Map<String, Object> searchMap = new HashMap<>();
		    searchMap.put("code", searchVO.getCode());
		    searchMap.put("idList", scoreDocMap.keySet());
		    searchMap.put("lightSearch", searchVO.getLightSearch());
		    searchMap.put("disableOriginCheck", searchVO.getDisableOriginCheck());
		    if (!StringUtils.contains(searchVO.getOrderByExpression(), OrderByExpressionEnum.RO_SCORE.getField())) {
			searchMap.put("orderByExpression", searchVO.getOrderByExpression());
		    }

		    resultList = interestedPartyDAO.findInterestedParty(searchMap);
		    resultList.forEach(w -> w.setScore(scoreDocMap.getOrDefault(w.getId(), BigDecimal.ZERO)));

		    if (StringUtils.equalsIgnoreCase(searchVO.getOrderByExpression(), OrderByExpressionEnum.RO_SCORE.getFieldAsc())) {
			resultList.sort(Comparator.comparing(InterestedParty::getScore));
		    } else if (StringUtils.equalsIgnoreCase(searchVO.getOrderByExpression(), OrderByExpressionEnum.RO_SCORE.getFieldDesc())) {
			resultList.sort(Comparator.comparing(InterestedParty::getScore).reversed());
		    } else if (StringUtils.contains(searchVO.getOrderByExpression(), OrderByExpressionEnum.RO_MAIN_NAME.getFieldsSplitted()[0])) {
			List<Long> idList = new ArrayList<>(scoreDocMap.keySet());
			resultList.sort(Comparator.comparing(ip -> idList.indexOf(ip.getId())));
		    }
		}

	    } else {
		Map<String, Object> searchMap = WccUtils.objToMap(searchVO);
		resultList = interestedPartyDAO.findInterestedParty(searchMap, start, length);
		totResults = interestedPartyDAO.getCountFindInterestedParty(searchMap);
		draw = searchVO.getPaginationDraw();
	    }

	    return new InterestedPartySearchResultVO(resultList, draw, totResults);

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

    }

    private void manageIPNames(List<Name> nameList, Long interestedPartyId) throws Exception {
	for (Name name : nameList) {
	    if (BooleanUtils.isTrue(name.getExecDelete())) {
		// deletes removed objects
		interestedPartyDAO.deleteIPName(interestedPartyId, name.getId());
		nameDAO.delete(name.getId());
	    } else if (!WccUtils.objectFildsAreNull(name)) {
		if (name.getId() == null) {
		    // inserts new objects

		    if (StringUtils.isEmpty(name.getMainId())) {
			String mainId = identifierManagerDAO.getNewMainIdentifier(IdentifierManagerRefTableEnum.NAME);
			name.setMainId(mainId);
		    }

		    nameDAO.insertDummy(name);
		    nameDAO.merge(name);

		    interestedPartyDAO.insertIPName(interestedPartyId, name.getId());

		} else {
		    if (StringUtils.isEmpty(name.getMainId())) {
			String mainId = identifierManagerDAO.getNewMainIdentifier(IdentifierManagerRefTableEnum.NAME);
			name.setMainId(mainId);
		    }
		    nameDAO.merge(name);
		}
	    }
	}
    }

    private void manageIPCitizenshipTerritory(List<Long> citizenshipIdList, Long interestedPartyId) {
	// Add new Citizenship and update reference for interestedparty
	this.interestedPartyDAO.deleteIPCitizenship(interestedPartyId);
	for (Long id : citizenshipIdList) {
	    this.interestedPartyDAO.insertIPCitizenship(interestedPartyId, id);
	}
    }

    private void manageIpAffiliation(InterestedParty ip) throws WccException {

	for (Affiliation affiliation : ip.getAffiliationList()) {
	    affiliation.setFkInterestedParty(ip.getId());
	    if (affiliation.getId() == null) {
		if (BooleanUtils.isNotTrue(affiliation.getExecDelete())) {
		    affiliation = this.affiliationDAO.insert(affiliation);

		    // INSERT OR DELETE AFFILIATION DOMAIN
		    manageAffiliationDomain(affiliation);
		}
	    } else {

		if (BooleanUtils.isTrue(affiliation.getExecDelete())) {
		    affiliation.getAffiliationDomainList().forEach(ad -> ad.setExecDelete(true));
		}

		// INSERT OR DELETE AFFILIATION DOMAIN
		manageAffiliationDomain(affiliation);

		// If List of AffiliationDomain is empty remove Affiliation
		if (!affiliationDAO.checkIfExistAffiliationDomain(affiliation.getId())) {
		    this.affiliationDAO.deleteAffiliation(affiliation.getId());
		} else {
		    this.affiliationDAO.updateEndDate(affiliation.getId(), affiliation.getEndDate());
		}
	    }
	}

	List<Affiliation> affList = this.affiliationDAO.findByInterestedParty(ip.getId(), null);
	if (affList.size() > 0) {
	    interestedPartyDAO.updateIsAffiliated(ip.getId(), true);
	} else {
	    interestedPartyDAO.updateIsAffiliated(ip.getId(), false);
	}

	ip.setAffiliationList(affList);
    }

    private void manageAffiliationDomain(Affiliation affiliation) throws WccException {
	for (int i = affiliation.getAffiliationDomainList().size() - 1; i >= 0; i--) {
	    AffiliationDomain ad = affiliation.getAffiliationDomainList().get(i);
	    ad.setFkAffiliation(affiliation.getId());
	    if (ad.getId() == null && !ad.getExecDelete()) {
		if (ad.getIsExcluded() == null) {
		    ad.setIsExcluded(false);
		}
		this.affiliationDAO.insertAffiliationDomain(ad);
	    } else if (ad.getExecDelete()) {
		this.affiliationDAO.deleteAffiliationDomain(ad.getId());
		affiliation.getAffiliationDomainList().remove(ad);
	    }
	}
    }

    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    private InterestedParty update(InterestedParty interestedParty) throws Exception {

	try {
	    // update interestedParty field with following reference:
	    // fk_birthCountry, fk_account, fk_status
	    Long interestedPartyId = interestedParty.getId();
	    interestedParty = this.interestedPartyDAO.merge(interestedParty);

	    manageIPNames(interestedParty.getNameList(), interestedPartyId);
	    manageIPCitizenshipTerritory(interestedParty.getCitizenshipIdList(), interestedPartyId);
	    manageIpAffiliation(interestedParty);

	    // save new identifiers
	    for (InterestedPartyIdentifierFlat idf : interestedParty.getInterestedPartyIdentifierFlatList()) {
		if (idf.getId() == null) {
		    interestedPartyDAO.insertInterestedPartyId(interestedPartyId, idf.getCode(), idf.getValue());
		}
	    }

	    return findByIdIp(interestedParty.getId());
	} catch (WccException e) {
	    deleteDummyIpAndNames(interestedParty);
	    throw e;
	} catch (Exception e) {
	    deleteDummyIpAndNames(interestedParty);
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

    }

    @ExecutionTimeTrackable
    @Loggable(level = "TRACE")
    private InterestedParty insert(InterestedParty interestedParty) throws WccException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

	try {
	    if (interestedParty.getFkStatus() == null) {
		Long idStatus = DTOUtils.getIdByCode(this.interestedPartyDAO.findAllIPStatus(), InterestedPartyStatusEnum.VALID.name());
		interestedParty.setFkStatus(idStatus);
	    }

	    if (interestedParty.getIsAffiliated() == null) {
		interestedParty.setIsAffiliated(false);
	    }

	    // insert interestedParty field with following reference:
	    // fk_birthCountry, fk_account, fk_status
	    if (StringUtils.isEmpty(interestedParty.getMainId())) {
		String mainId = identifierManagerDAO.getNewMainIdentifier(IdentifierManagerRefTableEnum.INTERESTED_PARTY);
		interestedParty.setMainId(mainId);
	    }

	    interestedPartyDAO.insertDummy(interestedParty);
	    interestedParty = this.interestedPartyDAO.merge(interestedParty);
	    Long interestedPartyId = interestedParty.getId();

	    // save new identifiers
	    for (InterestedPartyIdentifierFlat idf : interestedParty.getInterestedPartyIdentifierFlatList()) {
		if (idf.getId() == null) {
		    interestedPartyDAO.insertInterestedPartyId(interestedPartyId, idf.getCode(), idf.getValue());
		}
	    }

	    manageIPNames(interestedParty.getNameList(), interestedPartyId);
	    manageIPCitizenshipTerritory(interestedParty.getCitizenshipIdList(), interestedPartyId);
	    manageIpAffiliation(interestedParty);

	    return findByIdIp(interestedParty.getId());
	} catch (WccException e) {
	    deleteDummyIpAndNames(interestedParty);
	    throw e;
	} catch (Exception e) {
	    deleteDummyIpAndNames(interestedParty);
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    private void deleteDummyIpAndNames(InterestedParty interestedParty) {
	if (interestedParty != null && interestedParty.getId() != null) {
	    interestedPartyDAO.deleteDummy(interestedParty.getId());
	}
	if (interestedParty != null && !interestedParty.getNameList().isEmpty()) {
	    for (Name name : interestedParty.getNameList()) {
		if (name != null && name.getId() != null) {
		    nameDAO.deleteDummy(name.getId());
		}
	    }
	}
    }

    ///////////////////////////////////////////////////// Action Method/////////////////////////////////////////////////////
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public void logicallyDeleteIp(Long ipId) throws WccException {
	List<Name> names = nameDAO.findNameByIp(ipId);
	List<String> error = new ArrayList<>();
	for (Name name : names) {
	    if (derivedViewDAO.isExistsByNameId(name.getId())) {
		error.add(IPDeleteErrorEnum.ERROR_RIGHT_OWNERS.getMessageCode());
		break;
	    }
	}
	if (error.size() > 0) {
	    throw new IpDeleteErrorException("Delete Ip Error", error);
	}
	this.interestedPartyDAO.logicallyDeleteIp(ipId);
	nameDAO.markAsDeletedByIp(ipId);

	updateSolrIndex(ipId);
    }

    @Override
    public Integer executeMassiveAction(List<Long> idList, MassiveActionsEnum action) throws WccException {

	Integer count = 0;
	try {
	    if (idList == null) {
		throw new WccException(WccExceptionCodeEnum.APPLICATION_ERROR, "idList and domainList must contain at least one item and have the same size");
	    }

	    switch (action) {
		case IP_DELETE:
		    count = massiveLogicallyDelete(idList);
		    break;
		default:
		    throw new WccException(WccExceptionCodeEnum.APPLICATION_ERROR, "Unhandled action: " + action);
	    }

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

	return count;
    }

    private Integer massiveLogicallyDelete(List<Long> idList) throws WccException {
	Integer count = 0;
	for (int i = 0; i < idList.size(); i++) {
	    Long id = idList.get(i);

	    try {
		logicallyDeleteIp(id);

		count++;
	    } catch (IpDeleteErrorException e) {
		LOGGER.error("Error processing massiveLogicallyDelete [id:{}]", id, e);
	    } catch (Exception e) {
		LOGGER.error("Error processing massiveLogicallyDelete [id:{}]", id, e);
	    }

	}

	return count;
    }

    ///////////////////////////////////////////////////// Solr Method/////////////////////////////////////////////////////
    @ExecutionTimeTrackable
    @Override
    public Integer rebuildSolrIndex() throws WccException {
	int queryOffset = 0;
	int items = 0;

	try {
	    LOGGER.info("IP solr index rebuilt, start");

	    // truncate current index
	    interestedPartyIndexDao.deleteAll();
	    interestedPartyIndexDao.forceCommit();

	    List<InterestedParty> ipList;
	    do {
		// read a page of items from DB
		ipList = interestedPartyDAO.findInterestedPartyForReindex(queryOffset, solrRebuildPageSize);

		// if the list is empty, stop
		if (ipList.isEmpty())
		    break;

		// convert DTOs to Solr DOCs
		List<InterestedPartyDoc> wDocList = dozerHelper.toInterestedPartyDoc(ipList);

		// Save items in the index
		interestedPartyIndexDao.save(wDocList);
		interestedPartyIndexDao.forceCommit();

		queryOffset += solrRebuildPageSize;
		items += wDocList.size();

		LOGGER.info("IP solr index rebuilt, items: {}", items);
	    } while (true);

	    LOGGER.info("IP solr index rebuilt end, indexed {} items", items);

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

	return items;
    }

    @Override
    @ExecutionTimeTrackable
    public Integer indexQueuedItems() throws WccException {
	Integer items = 0;

	try {
	    List<Long> itemsToUpdate = ipIndexQueueDAO.findIpByAction(IndexQueueActionEnum.UPDATE);
	    if (!itemsToUpdate.isEmpty()) {
		for (Long id : itemsToUpdate) {
		    InterestedParty ip = interestedPartyDAO.find(id);
		    InterestedPartyDoc ipDoc = dozerHelper.toInterestedPartyDoc(ip);
		    interestedPartyIndexDao.save(ipDoc);
		    items++;
		}
		ipIndexQueueDAO.deleteByIp(itemsToUpdate);
	    }

	    List<Long> itemsToDelete = ipIndexQueueDAO.findIpByAction(IndexQueueActionEnum.DELETE);
	    if (!itemsToDelete.isEmpty()) {
		for (Long id : itemsToDelete) {
		    interestedPartyIndexDao.delete(id);
		    items++;
		}
		ipIndexQueueDAO.deleteByIp(itemsToDelete);
	    }

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

	return items;
    }

    @ExecutionTimeTrackable
    private void updateSolrIndex(Long id) {
	InterestedParty ip = interestedPartyDAO.find(id);
	updateSolrIndex(ip);
    }

    @ExecutionTimeTrackable
    private void updateSolrIndex(InterestedParty ip) {
	InterestedPartyDoc ipDoc = dozerHelper.toInterestedPartyDoc(ip);
	String action = null;

	if (StringUtils.equals(ipDoc.getStatusCode(), InterestedPartyStatusEnum.DELETED.name())) {
	    action = IndexQueueActionEnum.DELETE.name();
	} else {
	    action = IndexQueueActionEnum.UPDATE.name();
	}

	try {
	    if (StringUtils.equals(action, InterestedPartyStatusEnum.DELETED.name())) {
		interestedPartyIndexDao.delete(ipDoc.getId());
	    } else {
		interestedPartyIndexDao.save(ipDoc);
	    }

	} catch (Exception e) {
	    LOGGER.error("Unable to update solr index, the item will be enqueued", e);
	    IpIndexQueue iq = new IpIndexQueue();
	    iq.setAction(action);
	    iq.setFkInterestedParty(ip.getId());
	    ipIndexQueueDAO.insert(iq);
	}
    }

    ///////////////////////////////////////////////////// Group Management/////////////////////////////////////////////////////
    @Override
    public List<GroupDetailDTO> findGroupDetailsByIdGroup(Long idGroup) {
	return interestedPartyDAO.findGroupDetailsByIdGroup(idGroup, groupNameTypeList);
    }

    @Override
    public NameGroupResultVO findGroups(NameSearchVO searchVO) throws WccException {

	searchVO.setNameTypeList(groupNameTypeList);

	Map<String, Object> searchMap = WccUtils.objToMap(searchVO);
	List<GroupDTO> resList;
	Integer totResults;
	Integer draw;
	PageInfo pageInfo = searchVO.getPageInfo();

	if (pageInfo == null) {
	    resList = interestedPartyDAO.findNameGroups(searchMap);
	    totResults = resList.size();
	    draw = 0;
	} else {
	    List<Long> idList = interestedPartyDAO.findNameGroupIdList(searchMap, pageInfo.getStart(), pageInfo.getLength());
	    if (idList == null || idList.isEmpty()) {
		draw = 0;
		totResults = 0;
		resList = new ArrayList<>();
	    } else {
		resList = interestedPartyDAO.findNameGroupByIdList(idList, pageInfo);
		totResults = interestedPartyDAO.countNameGroup(searchMap);
		draw = searchVO.getPageInfo().getDraw();
		resList.sort(Comparator.comparing(g -> idList.indexOf(g.getId())));
	    }
	}

	return new NameGroupResultVO(resList, draw, totResults);

    }

    @Override
    public GroupDTO findGroupById(Long idGroup) {
	return interestedPartyDAO.findGroupById(idGroup, groupNameTypeList);
    }

    ///////////////////////////////////////////////////// Affiliation Management/////////////////////////////////////////////////////
    @Override
    public List<Affiliation> findInterestedPartyAffiliation(Long idInterestedParty) throws WccException {
	return this.affiliationDAO.findByInterestedParty(idInterestedParty, null);
    }

    @ExecutionTimeTrackable
    @Loggable
    @Override
    public List<Affiliation> explodeIpiRightType(List<Affiliation> affiliationList, boolean removeAd) throws WccException {

	String ipiRightTypeAll = IpiRightTypeEnum.ALL.name();

	for (Affiliation a : affiliationList) {

	    List<AffiliationDomain> listToSplit = a.getAffiliationDomainList().stream().filter(ad -> ipiRightTypeAll.equalsIgnoreCase(ad.getIpiRightTypeCode())).collect(Collectors.toList());

	    a.getAffiliationDomainList().stream().filter(ad -> ad.getIpiRightTypeCode().equalsIgnoreCase(ipiRightTypeAll)).forEach(item -> item.setExecDelete(true));

	    for (AffiliationDomain ad : listToSplit) {

		// Get list of all irt and remove code ALL
		List<IpiRightTypeFlat> irtList = commonDAO.findAllIpiRightTypeByCc(ad.getFkCreationClass());
		irtList.removeIf(irt -> irt.getCode().equalsIgnoreCase(ipiRightTypeAll));

		for (IpiRightTypeFlat irt : irtList) {
		    AffiliationDomain affiliationDomain = new AffiliationDomain();
		    affiliationDomain.setFkAffiliation(a.getId());
		    affiliationDomain.setFkCreationClass(ad.getFkCreationClass());
		    affiliationDomain.setCreationClassCode(ad.getCreationClassCode());
		    affiliationDomain.setFkIpiRole(ad.getFkIpiRole());
		    affiliationDomain.setIpiRoleCode(ad.getIpiRoleCode());
		    affiliationDomain.setFkIpiRightType(irt.getId());
		    affiliationDomain.setIpiRightTypeCode(irt.getCode());

		    a.getAffiliationDomainList().add(affiliationDomain);
		}

		if (irtList.size() > 0 && removeAd) {
		    // removeAd = true is for export file only
		    // because normally affiliationDomain are removed with
		    // flag setExecDelete set to true
		    a.getAffiliationDomainList().remove(ad);
		}
	    }
	}

	return affiliationList;
    }

    ///////////////////////////////////////////////////// Code conversion Management/////////////////////////////////////////////////////
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Throwable.class)
    public InterestedParty convertTaskDetail(IpTaskItemDetail ipTaskItemDetail) throws WccException {
	InterestedParty interestedParty = new InterestedParty();
	try {
	    interestedParty = dozerUtility.map(ipTaskItemDetail, new InterestedParty());
	    interestedParty = convertAllCodesToId(interestedParty);

	    // remove all Local WCC-ID
	    for (int i = interestedParty.getInterestedPartyIdentifierFlatList().size() - 1; i >= 0; i--) {
		InterestedPartyIdentifierFlat ipId = interestedParty.getInterestedPartyIdentifierFlatList().get(i);
		ipId.setId(null);
	    }

	    for (int i = interestedParty.getNameList().size() - 1; i >= 0; i--) {
		Name ipName = interestedParty.getNameList().get(i);
		ipName.setId(null);
	    }

	    for (Affiliation aff : interestedParty.getAffiliationList()) {
		for (AffiliationDomain affDom : aff.getAffiliationDomainList()) {
		    affDom.setFkAffiliation(null);
		    affDom.setIdAffiliationDomain(null);
		}
		aff.setIdAffiliation(null);
	    }

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

	return interestedParty;
    }

    private InterestedParty convertAllCodesToId(InterestedParty ip) throws WccException {
	List<InterestedParty> auxList = new ArrayList<>();
	auxList.add(ip);

	auxList = convertAllCodesToId(auxList);

	return auxList.get(0);
    }

    private List<InterestedParty> convertAllCodesToId(List<InterestedParty> interestedPartyList) throws WccException {

	// create a map to decode status
	List<InterestedPartyStatusFlat> statusList = findAllIPStatus();
	Map<String, Long> statusMap = new HashMap<>();
	for (InterestedPartyStatusFlat item : statusList) {
	    statusMap.put(item.getCode().toString(), item.getId());
	}

	// create a map to decode territory
	List<Territory> territoryList = commonDAO.findAllTerritory(TerritoryOrderTypeEnum.TRASL_name);
	Map<String, Long> territoryCodeMap = new HashMap<>();
	for (Territory item : territoryList) {
	    territoryCodeMap.put(item.getCode(), item.getId());
	}

	Map<Long, String> territoryTisaMap = new HashMap<>();
	for (Territory item : territoryList) {
	    territoryTisaMap.put(item.getId(), item.getTisa());
	}

	// create a map to decode CMO
	List<Cmo> cmoList = cmoDAO.findAll();
	Map<String, Cmo> cmoMap = new HashMap<>();
	for (Cmo item : cmoList) {
	    cmoMap.put(item.getCode(), item);
	}

	// create a map to decode creation class
	List<CreationClassFlat> creationClassList = creationClassDAO.findAllCreationClass();
	Map<String, Long> creationClassMap = new HashMap<>();
	for (CreationClassFlat item : creationClassList) {
	    creationClassMap.put(item.getCode(), item.getId());
	}

	// create a map to decode role
	List<IpiRoleFlat> ipiRoleList = commonDAO.findAllIpiRole();
	Map<String, Long> roleMap = new HashMap<>();
	for (IpiRoleFlat item : ipiRoleList) {
	    roleMap.put(item.getCode(), item.getId());
	}

	// create a map to decode right type
	List<IpiRightTypeFlat> ipiRightTypeList = rightTypeDAO.findAllIpiRightType();
	Map<String, Long> ipiRightTypeMap = new HashMap<>();
	for (IpiRightTypeFlat item : ipiRightTypeList) {
	    ipiRightTypeMap.put(item.getCode(), item.getId());
	}

	// decode IP list
	for (InterestedParty ip : interestedPartyList) {

	    if (ip.getStatusCode() != null) {
		ip.setFkStatus(statusMap.get(ip.getStatusCode()));
	    }

	    if (ip.getBirthCountryCode() != null) {
		ip.setFkBirthCountry(territoryCodeMap.get(ip.getBirthCountryCode()));
	    }

	    for (String code : ip.getCitizenshipCodeList()) {
		if (code != null) {
		    ip.getCitizenshipIdList().add(territoryCodeMap.get(code));
		}
	    }

	    List<String> newCitizenshipCodeList = new ArrayList<>();
	    for (Long id : ip.getCitizenshipIdList()) {
		newCitizenshipCodeList.add(territoryTisaMap.get(id));
	    }

	    ip.setCitizenshipCodeList(newCitizenshipCodeList);
	    ip.setBirthCountryCode(territoryTisaMap.get(ip.getFkBirthCountry()));

	    // decode affiliation list
	    for (Affiliation aff : ip.getAffiliationList()) {
		if (null != aff.getCmo() && null != aff.getCmo().getCode()) {
		    aff.setCmo(cmoMap.get(aff.getCmo().getCode()));
		}

		// decode affiliation domain list
		for (AffiliationDomain affDom : aff.getAffiliationDomainList()) {
		    if (null != affDom.getCreationClassCode()) {
			affDom.setFkCreationClass(creationClassMap.get(affDom.getCreationClassCode()));
		    }

		    if (null != affDom.getIpiRightTypeCode()) {
			affDom.setFkIpiRightType(ipiRightTypeMap.get(affDom.getIpiRightTypeCode()));
		    }

		    if (null != affDom.getIpiRoleCode()) {
			affDom.setFkIpiRole(roleMap.get(affDom.getIpiRoleCode()));
		    }

		}
	    }

	}

	return interestedPartyList;
    }

    @Override
    public List<IpTaskElaborationResult> getIpTaskElaborationResultByItemCode(List<String> itemCodeList) throws IllegalStateException {
	List<IpTaskElaborationResult> erList = ipTaskDAO.getIpTaskElaborationResultByItemCode(itemCodeList);
	for (IpTaskElaborationResult er : erList) {
	    if (StringUtils.isBlank(er.getItemCode()) || StringUtils.isBlank(er.getItemStatus())) {
		throw new IllegalStateException("Item Code or Task Status are blank");
	    }
	}
	return erList;
    }

    /****************************** Import ******************************/

    @Override
    public List<IpImport> findIpImport() throws WccException {
	List<IpImport> resList;
	try {
	    resList = ipImportDAO.findAll();
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return resList;
    }

    /****************************** Export ******************************/

    @Override
    public ExportBean getFullExportBean(FileFormatEnum fileFormatEnum) throws WccException {

	try {
	    List<Long> idList = new ArrayList<>();
	    ExportBean exportBean = getExportBean(idList, ExportTypeEnum.IP_FULL_EXPORT, fileFormatEnum);

	    String filePath = exportBean.getCompletePath();

	    if (StringUtils.isNotEmpty(filePath)) {
		DateFormat dateFormat = new SimpleDateFormat(ConversionUtils.DATE_TIME_STAMP_MILLI);
		String extension = FilenameUtils.getExtension(filePath);
		String timestamp = dateFormat.format(new Date());
		ipSftpClient.writeIpFullExportToSftp(timestamp + "." + extension, filePath, configProperties.get(ExportTypeEnum.IP_FULL_EXPORT.getSftpPathPropertyName()).toString());
		try {
		    File f = new File(filePath);
		    f.delete();
		} catch (Exception e) {
		    LOGGER.error("Error deleting temporary file {} ,", filePath, e);
		}
	    }

	    return exportBean;
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    @Override
    public ExportBean getExportBean(List<Long> idList, ExportTypeEnum exportType, FileFormatEnum fileFormatEnum) throws WccException {

	Path path = null;
	byte[] bFile;

	try {

	    path = Paths.get(createExportFile(idList, exportType, fileFormatEnum));
	    bFile = Files.readAllBytes(path);

	} catch (IOException e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	// convert file to byte[]
	ExportBean exportBean = new ExportBean();
	exportBean.setFileName(path.toFile().getName());
	exportBean.setFileSize((long) bFile.length);
	exportBean.setDocument(bFile);
	exportBean.setCompletePath(path.toString());

	// set content type
	if (StringUtils.equals(FileFormatEnum.EXCEL.getFileExtention(), FilenameUtils.getExtension(path.toFile().getName()))) {
	    exportBean.setContentType(FileFormatEnum.EXCEL.getContentType());

	} else if (StringUtils.equals(FileFormatEnum.CSV.getFileExtention(), FilenameUtils.getExtension(path.toFile().getName()))) {
	    exportBean.setContentType(FileFormatEnum.CSV.getContentType());

	} else {
	    throw new IllegalArgumentException("Invalid content type");
	}

	return exportBean;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public String createExportFileByIpList(List<InterestedParty> ipList, ExportTypeEnum exportType) throws WccException, IOException {
	// loadIpsToExport(ipList);
	List<IpRow> ipRows = importExportHelper.toIpRowList(ipList);

	String filePath = configProperties.get(exportType.getPathPropertyName()).toString();
	// Build the Csv File
	IpWriter ipWriterCsv = new IpWriterCsv(filePath, EnvironmentEnum.SHARED, false);
	ipWriterCsv.writeRows(ipRows);
	ipWriterCsv.closeStream();

	return ipWriterCsv.getFileName();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private String createExportFile(List<Long> idList, ExportTypeEnum exportType, FileFormatEnum fileFormatEnum) throws WccException, IOException {

	int queryOffset = 0;
	// int items = 0;

	String filePath = configProperties.get(exportType.getPathPropertyName()).toString();

	IpWriter ipWriter = null;
	switch (fileFormatEnum) {

	    case EXCEL:// Build the Excel File
		ipWriter = new IpWriterExcel(filePath, EnvironmentEnum.SHARED, false);
		break;
	    case CSV:
		ipWriter = new IpWriterCsv(filePath, EnvironmentEnum.SHARED, false);
		break;

	    default:
		throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, "Not implemented file type");
	}

	do {
	    List<InterestedParty> ipList = loadIpsToExport(idList, exportType, queryOffset, exportRightOwnerPageSize);

	    // if the list is empty, stop
	    if (ipList.isEmpty()) {
		break;
	    }

	    List<IpRow> ipRows = importExportHelper.toIpRowList(ipList);

	    ipWriter.writeRows(ipRows);

	    if (ipList.size() < exportRightOwnerPageSize) {
		break;
	    } else {
		ipWriter.flush();
	    }

	    queryOffset += exportRightOwnerPageSize;

	    LOGGER.info("RO EXPORT: {}", queryOffset);

	} while (true);

	ipWriter.closeStream();

	return ipWriter.getFileName();
    }

    private List<InterestedParty> loadIpsToExport(List<Long> idList, ExportTypeEnum exportType, int queryOffset, int PAGE_SIZE) throws WccException {
	// Load IPs --------------------------------------------------------
	InterestedPartySearchVO searchVO = new InterestedPartySearchVO();
	searchVO.setIdList(idList);
	searchVO.setDisableOriginCheck(true);

	List<InterestedParty> ipList = interestedPartyDAO.findInterestedParty(WccUtils.objToMap(searchVO), queryOffset, PAGE_SIZE);

	List<Territory> territoryList = commonDAO.findAllTerritory(TerritoryOrderTypeEnum.TRASL_name);
	Map<Long, String> territoryMap = new HashMap<>();
	for (Territory item : territoryList) {
	    territoryMap.put(item.getId(), item.getTisa());
	}
	for (InterestedParty interestedParty : ipList) {
	    // birthday country code
	    interestedParty.setBirthCountryCode(territoryMap.get(interestedParty.getFkBirthCountry()));
	    // citizenship code list
	    List<String> newCitizenshipCodeList = new ArrayList<>();
	    for (Long id : interestedParty.getCitizenshipIdList()) {
		newCitizenshipCodeList.add(territoryMap.get(id));
	    }
	    interestedParty.setCitizenshipCodeList(newCitizenshipCodeList);
	    // affiliation
	    List<Affiliation> affiliationList = findInterestedPartyAffiliation(interestedParty.getId());
	    interestedParty.setAffiliationList(explodeIpiRightType(affiliationList, true));
	}

	return ipList;
    }

    /*
     * @Override public InterestedParty getRightOwnerByMainId(String ipMainId) throws WccException {
     * 
     * InterestedParty interestedParty = interestedPartyDAO.findInterestedPartiesByIpMainId(ipMainId);
     * 
     * if (interestedParty != null) { List<Territory> territoryList = commonDAO.findAllTerritory(TerritoryOrderTypeEnum.TRASL_name); Map<Long, String> territoryMap = new HashMap<>(); for (Territory
     * item : territoryList) { territoryMap.put(item.getId(), item.getTisa()); }
     * 
     * interestedParty.setBirthCountryCode(territoryMap.get(interestedParty.getFkBirthCountry())); // citizenship code list List<String> newCitizenshipCodeList = new ArrayList<>(); for (Long id :
     * interestedParty.getCitizenshipIdList()) { newCitizenshipCodeList.add(territoryMap.get(id)); } interestedParty.setCitizenshipCodeList(newCitizenshipCodeList); List<Affiliation> affiliationList =
     * findInterestedPartyAffiliation(interestedParty.getId()); interestedParty.setAffiliationList(explodeIpiRightType(affiliationList, true)); }
     * 
     * return interestedParty; }
     */

    @Override
    public Name getNameByMainId(String mainId) {
	List<Name> nameList = nameDAO.findByNameMainId(mainId);
	if (!nameList.isEmpty()) {
	    return nameList.get(0);
	} else {
	    return null;
	}
    }
}
