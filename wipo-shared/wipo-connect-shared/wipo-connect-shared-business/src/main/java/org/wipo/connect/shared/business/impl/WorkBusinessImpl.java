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
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.exception.DerivedWorkCycleException;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.import_model.EnvironmentEnum;
import org.wipo.connect.common.import_model.WorkRow;
import org.wipo.connect.common.import_writer.WorkWriter;
import org.wipo.connect.common.import_writer.WorkWriterCsv;
import org.wipo.connect.common.import_writer.WorkWriterExcel;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.mapping.DozerUtility;
import org.wipo.connect.common.utils.ConstantUtils;
import org.wipo.connect.common.utils.ConversionUtils;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.common.vo.PairVO;
import org.wipo.connect.enumerator.IdentifierManagerRefTableEnum;
import org.wipo.connect.enumerator.OrderByExpressionEnum;
import org.wipo.connect.enumerator.SyncTaskStatusEnum;
import org.wipo.connect.shared.business.utils.DozerHelper;
import org.wipo.connect.shared.exchange.business.WorkBusiness;
import org.wipo.connect.shared.exchange.dto.impl.AffiliationSplit;
import org.wipo.connect.shared.exchange.dto.impl.Cmo;
import org.wipo.connect.shared.exchange.dto.impl.CreationClassFlat;
import org.wipo.connect.shared.exchange.dto.impl.DerivedView;
import org.wipo.connect.shared.exchange.dto.impl.DerivedViewName;
import org.wipo.connect.shared.exchange.dto.impl.DerivedViewNameShare;
import org.wipo.connect.shared.exchange.dto.impl.DerivedWork;
import org.wipo.connect.shared.exchange.dto.impl.ImportStatusFlat;
import org.wipo.connect.shared.exchange.dto.impl.Name;
import org.wipo.connect.shared.exchange.dto.impl.ReferenceFlat;
import org.wipo.connect.shared.exchange.dto.impl.RightTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.RoleFlat;
import org.wipo.connect.shared.exchange.dto.impl.Territory;
import org.wipo.connect.shared.exchange.dto.impl.Title;
import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.dto.impl.WorkDate;
import org.wipo.connect.shared.exchange.dto.impl.WorkIdentifierFlat;
import org.wipo.connect.shared.exchange.dto.impl.WorkImport;
import org.wipo.connect.shared.exchange.dto.impl.WorkIndexQueue;
import org.wipo.connect.shared.exchange.dto.impl.WorkPerformer;
import org.wipo.connect.shared.exchange.dto.impl.WorkStatusFlat;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskDetail;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskElaborationResult;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskItem;
import org.wipo.connect.shared.exchange.enumerator.ExportTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.FileFormatEnum;
import org.wipo.connect.shared.exchange.enumerator.ImportStatusEnum;
import org.wipo.connect.shared.exchange.enumerator.IndexQueueActionEnum;
import org.wipo.connect.shared.exchange.enumerator.MassiveActionsEnum;
import org.wipo.connect.shared.exchange.enumerator.ReferenceTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.TerritoryOrderTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.WorkStatusEnum;
import org.wipo.connect.shared.exchange.exception.WorkAsComponentException;
import org.wipo.connect.shared.exchange.index.doc.WorkDoc;
import org.wipo.connect.shared.exchange.utils.DTOUtils;
import org.wipo.connect.shared.exchange.utils.DerivedWorkUtils;
import org.wipo.connect.shared.exchange.utils.importexport.ExportBean;
import org.wipo.connect.shared.exchange.utils.importexport.ImportExportHelper;
import org.wipo.connect.shared.exchange.vo.WorkSearchResultVO;
import org.wipo.connect.shared.exchange.vo.WorkSearchVO;
import org.wipo.connect.shared.indexing.dao.WorkIndexDao;
import org.wipo.connect.shared.persistence.dao.AffiliationDAO;
import org.wipo.connect.shared.persistence.dao.CmoDAO;
import org.wipo.connect.shared.persistence.dao.CommonDAO;
import org.wipo.connect.shared.persistence.dao.CreationClassDAO;
import org.wipo.connect.shared.persistence.dao.DerivedViewDAO;
import org.wipo.connect.shared.persistence.dao.DerivedWorkDAO;
import org.wipo.connect.shared.persistence.dao.IdentifierManagerDAO;
import org.wipo.connect.shared.persistence.dao.NameDAO;
import org.wipo.connect.shared.persistence.dao.ReferenceDAO;
import org.wipo.connect.shared.persistence.dao.RightTypeDAO;
import org.wipo.connect.shared.persistence.dao.RoleDAO;
import org.wipo.connect.shared.persistence.dao.TitleDAO;
import org.wipo.connect.shared.persistence.dao.WorkDAO;
import org.wipo.connect.shared.persistence.dao.WorkDateDAO;
import org.wipo.connect.shared.persistence.dao.WorkIdentifierFlatDAO;
import org.wipo.connect.shared.persistence.dao.WorkImportDAO;
import org.wipo.connect.shared.persistence.dao.WorkIndexQueueDAO;
import org.wipo.connect.shared.persistence.dao.WorkPerformerDAO;
import org.wipo.connect.shared.persistence.dao.WorkStatusFlatDAO;
import org.wipo.connect.shared.persistence.dao.WorkTaskDAO;
import org.wipo.connect.shared.persistence.sftp.WorkSftpClient;

import net.bull.javamelody.MonitoredWithSpring;

/**
 * The Class WorkBusinessImpl.
 */
@Service
@MonitoredWithSpring
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class WorkBusinessImpl implements WorkBusiness {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(WorkBusinessImpl.class);

    @Value("#{configProperties['solr.rebuild-page-size']}")
    private Integer solrRebuildPageSize;

    @Value("#{configProperties['export.work.pageSize']}")
    private Integer exportWorkPageSize;

    /** The work DAO. */
    @Autowired
    private WorkDAO workDAO;

    /** The work task DAO. */
    @Autowired
    private WorkTaskDAO workTaskDAO;

    /** The work performer DAO. */
    @Autowired
    private WorkPerformerDAO workPerformerDAO;

    /** The derived work DAO. */
    @Autowired
    private DerivedWorkDAO derivedWorkDAO;

    /** The work status flat DAO. */
    @Autowired
    private WorkStatusFlatDAO workStatusFlatDAO;

    /** The title DAO. */
    @Autowired
    private TitleDAO titleDAO;

    /** The work identifier flat DAO. */
    @Autowired
    private WorkIdentifierFlatDAO workIdentifierFlatDAO;

    /** The derived view DAO. */
    @Autowired
    private DerivedViewDAO derivedViewDAO;

    /** The common DAO. */
    @Autowired
    private CommonDAO commonDAO;

    /** The role DAO. */
    @Autowired
    private RoleDAO roleDAO;

    /** The cmo DAO. */
    @Autowired
    private CmoDAO cmoDAO;

    /** The Right Type DAO. */
    @Autowired
    private RightTypeDAO rightTypeDAO;

    /** The name DAO. */
    @Autowired
    private NameDAO nameDAO;

    /** The reference DAO. */
    @Autowired
    private ReferenceDAO referenceDAO;

    /** The dozer utility. */
    @Autowired
    private DozerUtility dozerUtility;

    /** The work import dao. */
    @Autowired
    private WorkImportDAO workImportDAO;

    /** The work index dao. */
    @Autowired
    private WorkIndexDao workIndexDao;

    /** The work index queue dao. */
    @Autowired
    private WorkIndexQueueDAO workIndexQueueDAO;

    /** The dozer helper. */
    @Autowired
    private DozerHelper dozerHelper;

    @Autowired
    private AffiliationDAO affiliationDAO;

    @Autowired
    private WorkDateDAO workDateDAO;

    @Autowired
    private CreationClassDAO creationClassDAO;

    @Autowired
    private IdentifierManagerDAO identifierManagerDAO;

    @Autowired
    private ImportExportHelper importExportHelper;

    @Autowired
    private Properties configProperties;

    @Autowired
    private WorkSftpClient workSftpClient;

    ///////////////////////////////////////////////////// CRUD/////////////////////////////////////////////////////
    @Override
    public WorkSearchResultVO findWork(WorkSearchVO searchVO) throws WccException {
	if (searchVO.getPaginationStart() == null || searchVO.getPaginationLength() == null) {
	    return findWork(searchVO, 0, ConstantUtils.DEFAULT_LIMIT);
	} else {
	    return findWork(searchVO, searchVO.getPaginationStart(), searchVO.getPaginationLength());
	}
    }

    @Override
    public WorkSearchResultVO findWorkForWs(WorkSearchVO searchVO) throws WccException {
	return findWork(searchVO, searchVO.getPaginationStart(), searchVO.getPaginationLength());
    }

    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    private WorkSearchResultVO findWork(WorkSearchVO searchVO, int start, int length) throws WccException {

	try {
	    List<Work> resultList = new ArrayList<>();
	    Integer totResults;
	    Integer draw;

	    if (BooleanUtils.isTrue(searchVO.getIsSimpleSearch())) {

		Page<WorkDoc> docList = workIndexDao.search(searchVO, start, length); // invoke search solr
		Map<Long, BigDecimal> scoreDocMap = docList.getContent().stream().collect(
			Collectors.toMap(WorkDoc::getId, WorkDoc::getScore, (u, v) -> {
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
		    if (!StringUtils.contains(searchVO.getOrderByExpression(), OrderByExpressionEnum.WORK_SCORE.getField())) {
			searchMap.put("orderByExpression", searchVO.getOrderByExpression());
		    }

		    resultList = workDAO.findWork(searchMap);
		    resultList.forEach(w -> w.setScore(scoreDocMap.getOrDefault(w.getId(), BigDecimal.ZERO)));

		    if (StringUtils.equalsIgnoreCase(searchVO.getOrderByExpression(), OrderByExpressionEnum.WORK_SCORE.getFieldAsc())) {
			resultList.sort(Comparator.comparing(Work::getScore));
		    } else if (StringUtils.equalsIgnoreCase(searchVO.getOrderByExpression(), OrderByExpressionEnum.WORK_SCORE.getFieldDesc())) {
			resultList.sort(Comparator.comparing(Work::getScore).reversed());
		    } else if (StringUtils.contains(searchVO.getOrderByExpression(), OrderByExpressionEnum.WORK_MAIN_TITLE.getField())) {
			List<Long> idList = new ArrayList<>(scoreDocMap.keySet());
			resultList.sort(Comparator.comparing(work -> idList.indexOf(work.getId())));
		    }
		}

	    } else {
		Map<String, Object> searchMap = WccUtils.objToMap(searchVO);
		resultList = workDAO.findWork(searchMap, start, length);
		totResults = workDAO.getCountFindWork(searchMap);
		draw = searchVO.getPaginationDraw();
	    }

	    for (Work w : resultList) {
		// Because of shows the Right Owner of the Ownership View in work list
		addAffiliationCmoDescriptionInDerivedViewName(w);
	    }

	    return new WorkSearchResultVO(resultList, draw, totResults);

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public Work findById(Long id, String code) throws WccException {
	Work work;
	try {
	    work = this.workDAO.findWorkById(id, code);

	    List<WorkPerformer> performerList = this.workPerformerDAO.findByWork(work.getId());
	    work.setWorkPerformerList(performerList);

	    List<DerivedWork> derivedWork = this.derivedWorkDAO.findByParent(work.getId());
	    work.setDerivedWorkList(derivedWork);

	    List<DerivedWork> derivedWorkParent = this.derivedWorkDAO.findParentWorkByIdChildWork(work.getId());
	    work.setDerivedWorkParentList(derivedWorkParent);

	    completeDerivedView(work);

	} catch (Exception e) {
	    throw new IllegalArgumentException(e);
	}
	return work;
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public Work findByMainId(String mainId) throws WccException {
	try {
	    Long idWork = this.workDAO.findWorkIdByMainId(mainId, false);
	    return findById(idWork);
	} catch (WccException e) {
	    throw e;
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public Work findById(Long id) throws WccException {
	Work work;
	try {
	    work = this.workDAO.find(id);

	    List<WorkPerformer> performerList = this.workPerformerDAO.findByWork(work.getId());
	    work.setWorkPerformerList(performerList);

	    List<DerivedWork> derivedWork = this.derivedWorkDAO.findByParent(work.getId());
	    work.setDerivedWorkList(derivedWork);

	    List<DerivedWork> derivedWorkParent = this.derivedWorkDAO.findParentWorkByIdChildWork(work.getId());
	    work.setDerivedWorkParentList(derivedWorkParent);

	    completeDerivedView(work);

	    work = convertAllCodesToId(work);

	} catch (Exception e) {
	    throw new IllegalArgumentException(e);
	}
	return work;
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public Work insertOrUpdate(Work work) throws WccException {
	Work wOut;

	if (work.getId() == null) {
	    wOut = insert(work);
	} else {
	    wOut = update(work);
	}

	wOut = findById(wOut.getId());
	updateSolrIndex(work);

	return wOut;
    }

    private Work insert(Work work) throws WccException {
	try {
	    // load default status if not specified
	    if (work.getFkStatus() == null) {
		WorkStatusFlat status = this.workStatusFlatDAO.findByCode(WorkStatusEnum.VALID);
		work.setFkStatus(status.getId());
	    }

	    if (StringUtils.isEmpty(work.getMainId())) {
		String mainId = identifierManagerDAO.getNewMainIdentifier(IdentifierManagerRefTableEnum.WORK);
		work.setMainId(mainId);
	    }
	    // main entity
	    this.workDAO.insertDummy(work);
	    this.workDAO.update(work);

	    // title list
	    for (Title t : work.getTitleList()) {
		t.setFkWork(work.getId());
		this.titleDAO.insert(t);
	    }

	    // identifiers
	    for (WorkIdentifierFlat idf : work.getWorkIdentifierList()) {
		workIdentifierFlatDAO.insertWorkId(work.getId(), idf.getCode(), idf.getValue());
	    }

	    // workPerformer
	    for (WorkPerformer wp : work.getWorkPerformerList()) {
		wp.setFkWork(work.getId());
		this.workPerformerDAO.insert(wp);
	    }

	    // DerivedWork
	    for (DerivedWork dw : work.getDerivedWorkList()) {
		dw.setFkParentWork(work.getId());
		this.derivedWorkDAO.insert(dw);
	    }
	    checkDerivedWorkCycle(work.getId());

	    // instruments
	    for (String ins : work.getInstrumentCodeList()) {
		this.workDAO.insertWorkInstrument(work.getId(), ins);
	    }

	    // derived view
	    insertOrUpdateDerivedView(work);

	    // work-date
	    for (WorkDate wd : work.getWorkDateList()) {
		wd.setFkWork(work.getId());
		this.workDateDAO.insert(wd);
	    }

	    return work;
	} catch (WccException e) {
	    if (work != null && work.getId() != null) {
		workDAO.deleteDummy(work.getId());
	    }
	    throw e;
	} catch (Exception e) {
	    if (work != null && work.getId() != null) {
		workDAO.deleteDummy(work.getId());
	    }
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    private void checkDerivedWorkCycle(Long workId) throws DerivedWorkCycleException {
	List<DerivedWork> dwChain = derivedWorkDAO.findAllChildrenChain(workId);
	Set<PairVO<Long, String>> dwCycles = DerivedWorkUtils.detectCycle(dwChain);
	if (!dwCycles.isEmpty()) {
	    LOGGER.debug("Cycles: {}", dwCycles);
	    throw new DerivedWorkCycleException("Cannot save derived works, cycle detected", dwCycles);
	}
    }

    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    private DerivedView insertDerivedView(Long idWork, DerivedView derivedView) {
	derivedView.setFkWork(idWork);
	this.derivedViewDAO.insert(derivedView);

	// DerivedViewName
	for (DerivedViewName dvn : derivedView.getDerivedViewNameList()) {
	    insertDerivedViewName(derivedView.getId(), dvn);
	}

	return derivedView;
    }

    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    private DerivedViewName insertDerivedViewName(Long idDerivedView, DerivedViewName derivedViewName) {
	derivedViewName.setFkDerivedView(idDerivedView);
	this.derivedViewDAO.insertDerivedViewName(derivedViewName);

	// DerivedViewNameShare
	for (DerivedViewNameShare dvns : derivedViewName.getDerivedViewNameShareList()) {
	    dvns.setFkDerivedViewName(derivedViewName.getId());
	    if (dvns.getFkRightType() == null) {
		RightTypeFlat rt = rightTypeDAO.findRightTypeByCode(dvns.getRightTypeCode());
		dvns.setFkRightType(rt.getId());
	    }
	    this.derivedViewDAO.insertDerivedViewNameShare(dvns);
	}
	return derivedViewName;
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<WorkStatusFlat> findAllWorkStatus() throws WccException {
	return this.workStatusFlatDAO.findAll();
    }

    private Work update(Work work) throws WccException {
	// update work entity
	this.workDAO.update(work);

	// update title
	for (Title t : work.getTitleList()) {
	    if (BooleanUtils.isTrue(t.getExecDelete())) {
		this.titleDAO.delete(t.getId());
	    } else if (t.getId() == null) {
		t.setFkWork(work.getId());
		this.titleDAO.insert(t);
	    } else {
		this.titleDAO.update(t);
	    }
	}

	// save new identifiers
	for (WorkIdentifierFlat idf : work.getWorkIdentifierList()) {
	    if (BooleanUtils.isTrue(idf.getExecDelete())) {
		this.workIdentifierFlatDAO.delete(idf.getId());
	    } else if (idf.getId() == null) {
		workIdentifierFlatDAO.insertWorkId(work.getId(), idf.getCode(), idf.getValue());
	    } else {
		this.workIdentifierFlatDAO.delete(idf.getId());
		workIdentifierFlatDAO.insertWorkId(work.getId(), idf.getCode(), idf.getValue());
	    }
	}

	// update workperformer
	for (WorkPerformer wp : work.getWorkPerformerList()) {
	    if (BooleanUtils.isTrue(wp.getExecDelete())) {
		this.workPerformerDAO.delete(wp.getId());
	    } else if (wp.getId() == null) {
		// insert
		wp.setFkWork(work.getId());
		this.workPerformerDAO.insert(wp);
	    } else {
		wp.setFkWork(work.getId());
		this.workPerformerDAO.update(wp);
	    }
	}

	// update derived work
	for (DerivedWork dw : work.getDerivedWorkList()) {
	    if (BooleanUtils.isTrue(dw.getExecDelete())) {
		this.derivedWorkDAO.delete(dw.getId());
	    } else if (dw.getId() == null) {
		// insert
		dw.setFkParentWork(work.getId());
		this.derivedWorkDAO.insert(dw);
	    } else {
		dw.setFkParentWork(work.getId());
		this.derivedWorkDAO.update(dw);
	    }
	}
	checkDerivedWorkCycle(work.getId());

	// update instruments
	this.workDAO.deleteWorkInstrumentByWork(work.getId());
	for (String ins : work.getInstrumentCodeList()) {
	    this.workDAO.insertWorkInstrument(work.getId(), ins);
	}

	// derived view
	insertOrUpdateDerivedView(work);

	// manage work date
	for (WorkDate wd : work.getWorkDateList()) {
	    if (BooleanUtils.isTrue(wd.getExecDelete())) {
		this.workDateDAO.delete(wd.getId());
	    } else if (wd.getId() == null) {
		// insert
		wd.setFkWork(work.getId());
		this.workDateDAO.insert(wd);
	    } else {
		wd.setFkWork(work.getId());
		this.workDateDAO.update(wd);
	    }
	}

	// reload data from database
	work = findById(work.getId());
	updateSolrIndex(work);
	// if(true){
	// throw new IllegalStateException("ROLL BACK");
	// }

	return work;
    }

    private void insertOrUpdateDerivedView(Work work) {
	for (DerivedView derivedView : work.getDerivedViewList()) {
	    if (BooleanUtils.isTrue(derivedView.getExecDelete())) {
		// delete derived view and sub-elements
		this.derivedViewDAO.delete(derivedView.getId());
	    } else if (derivedView.getId() == null) {
		// insert
		reindexDerivedWiew(derivedView);
		insertDerivedView(work.getId(), derivedView);
	    }
	}
    }

    @Override
    public void logicallyDeleteWork(Long workId) throws WccException {
	if (BooleanUtils.isTrue(derivedWorkDAO.checkWorkIsComponentByIdWork(workId))) {
	    throw new WorkAsComponentException("Work is used as Component in one or more Work - " + workId);
	}
	this.workDAO.logicallyDeleteWork(workId);
	this.workDAO.markAsDeleted(workId);
	derivedWorkDAO.deleteByParent(workId);

	updateSolrIndex(workId);
    }

    @Override
    public Map<Long, WorkStatusFlat> findAllWorkStatusMap() throws WccException {
	List<WorkStatusFlat> statusList;
	Map<Long, WorkStatusFlat> statusMap;
	try {
	    statusList = this.workStatusFlatDAO.findAll();
	    statusMap = DTOUtils.listToMapById(statusList);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return statusMap;
    }

    @Override
    public String getImportCodeByWorkId(Long workId) throws WccException {
	return this.workImportDAO.findImportCodeByWorkId(workId);
    }

    ///////////////////////////////////////////////////// Task management/////////////////////////////////////////////////////
    @Override
    public WorkImport insertNewImportFile(WorkImport workImport) throws WccException {
	WorkImport newObj = null;
	try {
	    String filename = workImport.getInputFile().getFileName();
	    Long count = workImportDAO.countWorkImportFileByName(filename);

	    if (count.compareTo(0L) == 0) {
		if (workImport.getFkStatus() == null) {
		    ImportStatusFlat status = commonDAO.findImportStatusByCode(ImportStatusEnum.QUEUED);
		    workImport.setFkStatus(status.getId());
		}
		newObj = workImportDAO.insertWorkImport(workImport);

	    } else {
		LOGGER.debug("The file {} is already present... skipping", filename);
	    }

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return newObj;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Throwable.class)
    public Work convertTaskDetail(WorkTaskDetail workTaskDetail) throws WccException {
	Work work = new Work();
	try {
	    work = dozerUtility.map(workTaskDetail, new Work());
	    work = convertAllCodesToId(work);

	    // remove all Local WCC-ID
	    for (int i = work.getWorkIdentifierList().size() - 1; i >= 0; i--) {
		WorkIdentifierFlat wid = work.getWorkIdentifierList().get(i);
		wid.setId(null);
	    }

	    for (DerivedView dv : work.getDerivedViewList()) {
		for (DerivedViewName dvn : dv.getDerivedViewNameList()) {
		    if (dvn != null && dvn.getName() != null && dvn.getName().getMainId() != null) {
			List<Name> nameList = nameDAO.findNameByNameMainId(dvn.getName().getMainId());
			if (nameList.size() == 1) {
			    dvn.setName(nameList.get(0));
			} else {
			    LOGGER.error("Name list size for Main Id '" + dvn.getName().getMainId() + "' must be 1, found:" + nameList.size());
			    throw new IllegalStateException("Name list size for Main Id '" + dvn.getName().getMainId() + "' must be 1, found:" + nameList.size());
			}
		    }
		}
	    }

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

	return work;
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public void storeTaskItemDetails(List<WorkTaskItem> itemList, String taskCode, String cmoCode) throws WccException {
	try {
	    Long idTask = this.workTaskDAO.insertWorkTask(taskCode, cmoCode);

	    for (WorkTaskItem taskItem : itemList) {
		Long idTaskItem = this.workTaskDAO.insertWorkTaskItem(idTask, null, SyncTaskStatusEnum.TO_SUBMIT.name(), taskItem.getProgr(), taskItem.getItemCode());
		WorkTaskDetail workTaskDetail = taskItem.getWorkTaskDetail();
		workTaskDetail.setFkWorkTaskItem(idTaskItem);
		this.workTaskDAO.insertWorkTaskDetail(workTaskDetail);
	    }
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    @Override
    public List<Work> insert(List<WorkTaskDetail> workTaskDetailList) throws WccException {
	List<Work> savedWorkList = new ArrayList<>();
	try {

	    for (WorkTaskDetail workTaskDetail : workTaskDetailList) {
		savedWorkList.add(insert(workTaskDetail));
	    }

	} catch (WccException e) {
	    throw e;
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

	return savedWorkList;
    }

    @Override
    public Work insert(WorkTaskDetail workTaskDetail) throws WccException {
	Work work = new Work();
	try {
	    work = convertTaskDetail(workTaskDetail);

	    work.setId(null);
	    work = insert(work);

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

	return work;
    }

    @Override
    public List<WorkTaskElaborationResult> getWorkTaskElaborationResultByItemCode(List<String> itemCodeList) throws IllegalStateException {
	List<WorkTaskElaborationResult> erList = workTaskDAO.getWorkTaskElaborationResultByItemCode(itemCodeList);
	for (WorkTaskElaborationResult er : erList) {
	    if (StringUtils.isBlank(er.getItemCode()) || StringUtils.isBlank(er.getTaskStatus())) {
		throw new IllegalStateException("Item Code or Task Status are blank");
	    }
	}
	return erList;
    }

    ///////////////////////////////////////////////////// Entity management/////////////////////////////////////////////////////
    private void completeDerivedView(Work work) throws WccException {

	if (work.getDerivedViewList().isEmpty()) {
	    return;
	}

	Map<Long, Cmo> cmoMap = new HashMap<>();

	// assuming only 1 ownership view is present
	DerivedView dv = work.getDerivedViewList().get(0);
	dv.getDerivedViewNameList().sort((DerivedViewName name1, DerivedViewName name2) -> name1.getRefIndex().compareTo(name2.getRefIndex()));
	for (DerivedViewName dvn : dv.getDerivedViewNameList()) {

	    Long creatorRefIndex = dvn.getCreatorRefIndex();
	    if (creatorRefIndex != null && creatorRefIndex > 0 && creatorRefIndex <= dv.getDerivedViewNameList().size()) {
		dvn.setCreatorRefIndex(creatorRefIndex);
		dvn.setCreatorRefMainId(dv.getDerivedViewNameList().get(creatorRefIndex.intValue() - 1).getName().getMainId());
	    }

	    for (DerivedViewNameShare dvns : dvn.getDerivedViewNameShareList()) {

		List<AffiliationSplit> splitList = affiliationDAO.findAffiliationSplit(dvn.getName().getId(), dvn.getRoleCode(), dvns.getRightTypeCode());

		if (splitList != null) {
		    for (AffiliationSplit afs : splitList) {
			Long idCmo = afs.getIdCmo();
			if (!cmoMap.containsKey(idCmo)) {
			    Cmo cmo = cmoDAO.find(idCmo);
			    cmoMap.put(idCmo, cmo);
			}
			dvns.getCmoList().add(cmoMap.get(idCmo));
		    }
		}
	    }
	}
    }

    private void reindexDerivedWiew(DerivedView derivedView) {
	Map<Long, Long> refMap = new HashMap<>();
	Long newIndex = 1L;
	derivedView.getDerivedViewNameList().sort(new Comparator<DerivedViewName>() {
	    @Override
	    public int compare(DerivedViewName o1, DerivedViewName o2) {
		Long i1 = ObjectUtils.defaultIfNull(o1.getRefIndex(), 0L);
		Long i2 = ObjectUtils.defaultIfNull(o2.getRefIndex(), 0L);

		return i1.compareTo(i2);
	    }
	});
	for (DerivedViewName dvn : derivedView.getDerivedViewNameList()) {
	    if (BooleanUtils.isTrue(dvn.getExecDelete())) {
		dvn.setRefIndex(-1L);
		continue;
	    }
	    refMap.put(dvn.getRefIndex(), newIndex);
	    dvn.setRefIndex(newIndex);
	    newIndex++;
	}
	for (DerivedViewName dvn : derivedView.getDerivedViewNameList()) {
	    Long cRef = null;
	    if (dvn.getCreatorRefIndex() != null && refMap.containsKey(dvn.getCreatorRefIndex())) {
		cRef = refMap.get(dvn.getCreatorRefIndex());
	    }
	    dvn.setCreatorRefIndex(cRef);
	}
    }

    private void addAffiliationCmoDescriptionInDerivedViewName(Work work) {
	if (work.getDerivedViewList().isEmpty()) {
	    return;
	}
	// assuming only 1 ownership view is present
	DerivedView dv = work.getDerivedViewList().get(0);
	for (DerivedViewName dvn : dv.getDerivedViewNameList()) {
	    dvn.setAffiliatedCmos(affiliationDAO.findAffiliatedCmosByName(dvn.getName().getId()));
	}
    }

    /**
     * Convert all codes to id.
     *
     * @param work
     *            the work
     * @return the work
     * @throws WccException
     *             the wcc exception
     */
    public Work convertAllCodesToId(Work work) throws WccException {
	List<Work> auxList = new ArrayList<>();
	auxList.add(work);

	auxList = convertAllCodesToId(auxList);

	return auxList.get(0);
    }

    private List<Work> convertAllCodesToId(List<Work> workList) throws WccException {

	// create a map to decode workStatus
	List<WorkStatusFlat> workStatusList = findAllWorkStatus();
	Map<String, Long> workStatusMap = new HashMap<>();
	for (WorkStatusFlat item : workStatusList) {
	    workStatusMap.put(item.getCode().toString(), item.getId());
	}

	// create a map to decode work type
	List<ReferenceFlat> workTypeList = this.referenceDAO.findReferenceByCode(ReferenceTypeEnum.WORK_TYPE.name());
	Map<String, Long> workTypeMap = new HashMap<>();
	for (ReferenceFlat item : workTypeList) {
	    workTypeMap.put(item.getRefCode(), item.getId());
	}

	// create a map to decode Source Type
	List<ReferenceFlat> sourceTypeList = this.referenceDAO.findReferenceByCode(ReferenceTypeEnum.WORK_SOURCE_TYPE.name());
	Map<String, Long> sourceTypeMap = new HashMap<>();
	for (ReferenceFlat item : sourceTypeList) {
	    sourceTypeMap.put(item.getRefCode(), item.getId());
	}

	// create a map to decode Excerpt Type
	List<ReferenceFlat> excerptTypeList = this.referenceDAO.findReferenceByCode(ReferenceTypeEnum.CWR_EXCERPT_TYPE.name());
	Map<String, Long> excerptTypeMap = new HashMap<>();
	for (ReferenceFlat item : excerptTypeList) {
	    excerptTypeMap.put(item.getRefCode(), item.getId());
	}

	// create a map to decode Composite Type
	List<ReferenceFlat> compositeTypeList = this.referenceDAO.findReferenceByCode(ReferenceTypeEnum.CWR_COMPOSITE_TYPE.name());
	Map<String, Long> compositeTypeMap = new HashMap<>();
	for (ReferenceFlat item : compositeTypeList) {
	    compositeTypeMap.put(item.getRefCode(), item.getId());
	}

	// create a map to decode title type
	List<ReferenceFlat> titleTypeList = this.referenceDAO.findReferenceByCode(ReferenceTypeEnum.TITLE_TYPE.name());
	Map<String, Long> titleTypeMap = new HashMap<>();
	for (ReferenceFlat item : titleTypeList) {
	    titleTypeMap.put(item.getRefCode(), item.getId());
	}

	// create a map to decode territory
	List<Territory> territoryList = this.commonDAO.findAllTerritory(TerritoryOrderTypeEnum.TRASL_name);
	Map<Long, String> territoryTisaMap = new HashMap<>();
	Map<String, Long> territoryCodeMap = new HashMap<>();
	for (Territory item : territoryList) {
	    territoryCodeMap.put(item.getCode(), item.getId());
	    territoryTisaMap.put(item.getId(), item.getTisa());
	}

	// create a map to decode right type
	List<RightTypeFlat> rightTypeList = this.rightTypeDAO.findAllRightType();
	Map<String, Long> rightTypeMap = new HashMap<>();
	for (RightTypeFlat item : rightTypeList) {
	    rightTypeMap.put(item.getCode().toString(), item.getId());
	}

	// create a map to decode right type
	List<RoleFlat> roleList = this.roleDAO.findAllRole();
	Map<String, Long> roleMap = new HashMap<>();
	for (RoleFlat item : roleList) {
	    roleMap.put(item.getCode().toString(), item.getId());
	}

	// create a map to decode creation class
	List<CreationClassFlat> ccList = this.creationClassDAO.findAllCreationClass();
	Map<String, Long> creationClassMap = new HashMap<>();
	for (CreationClassFlat item : ccList) {
	    creationClassMap.put(item.getCode().toString(), item.getId());
	}

	List<ReferenceFlat> addFieldList = this.commonDAO.findReferenceByCode(ReferenceTypeEnum.ADDITIONAL_FIELD);
	Map<String, Long> addFieldMap = new HashMap<>();
	for (ReferenceFlat item : addFieldList) {
	    addFieldMap.put(item.getRefCode(), item.getId());
	}

	// decode work list
	for (Work work : workList) {

	    if (StringUtils.isNotEmpty(work.getStatusCode())) {
		work.setFkStatus(workStatusMap.get(work.getStatusCode()));
	    }

	    if (StringUtils.isNotEmpty(work.getTypeCode())) {
		work.setFkType(workTypeMap.get(work.getTypeCode()));
	    }
	    if (StringUtils.isNotEmpty(work.getSourceTypeCode())) {
		work.setFkSourceType(sourceTypeMap.get(work.getSourceTypeCode()));
	    }

	    // decode title list
	    for (Title title : work.getTitleList()) {
		if (StringUtils.isNotEmpty(title.getTypeCode())) {
		    title.setFkType(titleTypeMap.get(title.getTypeCode()));
		}
	    }

	    // decode creation class
	    if (StringUtils.isNotEmpty(work.getCreationClassCode())) {
		work.setFkCreationClass(creationClassMap.get(work.getCreationClassCode()));
	    }

	    if (StringUtils.isNotEmpty(work.getCountryOfProductionCode())) {
		work.setFkCountryOfProduction(territoryCodeMap.get(work.getCountryOfProductionCode()));
		// work.setCountryOfProductionCode(territoryTisaMap.get(work.getFkCountryOfProduction()));
	    }

	    // TODO component work
	    for (DerivedWork dw : work.getDerivedWorkList()) {
		if (StringUtils.isNotEmpty(dw.getMainIdWork())) {
		    Long derivedWorkId = this.workDAO.findWorkIdByMainId(dw.getMainIdWork(), false);
		    dw.setFkWork(derivedWorkId);
		    dw.setFkParentWork(work.getId());
		}
	    }

	    // decode derived view
	    for (DerivedView derivedView : work.getDerivedViewList()) {

		for (DerivedViewName derivedViewName : derivedView.getDerivedViewNameList()) {

		    // decode role
		    if (StringUtils.isNotEmpty(derivedViewName.getRoleCode())) {
			derivedViewName.setFkRole(roleMap.get(derivedViewName.getRoleCode()));
		    }

		    if (StringUtils.isNotEmpty(derivedViewName.getSourceTypeCode())) {
			derivedViewName.setFkSourceType(sourceTypeMap.get(derivedViewName.getSourceTypeCode()));
		    }

		    for (DerivedViewNameShare derivedViewNameShare : derivedViewName.getDerivedViewNameShareList()) {
			// decode right type
			if (derivedViewNameShare.getRightTypeCode() != null) {
			    derivedViewNameShare.setFkRightType(rightTypeMap.get(derivedViewNameShare.getRightTypeCode()));
			}

			List<Cmo> auxCmoList = new ArrayList<>();
			for (Cmo cmo : derivedViewNameShare.getCmoList()) {
			    Cmo aux = cmoDAO.findByCode(cmo.getCode());
			    auxCmoList.add(aux);
			}
			derivedViewNameShare.setCmoList(auxCmoList);
		    }
		}
	    }

	}

	return workList;
    }

    ///////////////////////////////////////////////////// Solr Management/////////////////////////////////////////////////////
    @ExecutionTimeTrackable
    private void updateSolrIndex(Long id) {
	Work work = workDAO.find(id);
	updateSolrIndex(work);
    }

    @ExecutionTimeTrackable
    private void updateSolrIndex(Work w) {
	WorkDoc wDoc = dozerHelper.toWorkDoc(w);
	String action = null;

	if (StringUtils.equals(wDoc.getStatusCode(), WorkStatusEnum.DELETED.name())) {
	    action = IndexQueueActionEnum.DELETE.name();
	} else {
	    action = IndexQueueActionEnum.UPDATE.name();
	}

	try {

	    if (StringUtils.equals(action, WorkStatusEnum.DELETED.name())) {
		workIndexDao.delete(wDoc.getId());
	    } else {
		workIndexDao.save(wDoc);
	    }

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    WorkIndexQueue wq = new WorkIndexQueue();
	    wq.setAction(action);
	    wq.setFkWork(w.getId());
	    workIndexQueueDAO.insert(wq);
	}
    }

    @Override
    @ExecutionTimeTrackable
    public Integer rebuildSolrIndex() throws WccException {
	int queryOffset = 0;
	int items = 0;

	try {
	    LOGGER.info("Work solr index rebuilt, start");

	    // truncate current index
	    workIndexDao.deleteAll();
	    workIndexDao.forceCommit();

	    List<Work> wList;
	    do {
		// read a page of items from DB
		wList = workDAO.findWorkForReindex(queryOffset, solrRebuildPageSize);

		// if the list is empty, stop
		if (wList.isEmpty())
		    break;

		// convert DTOs to Solr DOCs
		List<WorkDoc> wDocList = dozerHelper.toWorkDoc(wList);

		// Save items in the index
		workIndexDao.save(wDocList);
		workIndexDao.forceCommit();

		queryOffset += solrRebuildPageSize;
		items += wDocList.size();

		LOGGER.info("Work solr index rebuilt, items: {}", items);
	    } while (true);

	    LOGGER.info("Work solr index rebuilt end, indexed {} items", items);

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
	    List<Long> itemsToUpdate = workIndexQueueDAO.findWorkByAction(IndexQueueActionEnum.UPDATE);
	    if (!itemsToUpdate.isEmpty()) {
		for (Long id : itemsToUpdate) {
		    Work work = workDAO.find(id);
		    WorkDoc ipDoc = dozerHelper.toWorkDoc(work);
		    workIndexDao.save(ipDoc);
		    items++;
		}
		workIndexQueueDAO.deleteByWork(itemsToUpdate);
	    }

	    List<Long> itemsToDelete = workIndexQueueDAO.findWorkByAction(IndexQueueActionEnum.DELETE);
	    if (!itemsToDelete.isEmpty()) {
		for (Long id : itemsToDelete) {
		    workIndexDao.delete(id);
		    items++;
		}
		workIndexQueueDAO.deleteByWork(itemsToDelete);
	    }

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

	return items;
    }

    ///////////////////////////////////////////////////// Actions/////////////////////////////////////////////////////
    @Override
    public Integer executeMassiveAction(List<Long> idList, MassiveActionsEnum action) throws WccException {

	Integer count = 0;
	try {
	    switch (action) {
		case WORK_DELETE:
		    count = massiveLogicallyDelete(idList);
		    break;
		default:
		    throw new WccException(WccExceptionCodeEnum.APPLICATION_ERROR, "Unhandled action: " + action);
	    }

	    return count;
	} catch (WccException e) {
	    throw e;
	} catch (Exception e) {
	    LOGGER.error(e.getMessage());
	    throw e;
	}
    }

    private Integer massiveLogicallyDelete(List<Long> idList) throws WccException {
	Integer count = 0;

	for (int i = 0; i < idList.size(); i++) {
	    Long id = idList.get(i);
	    try {
		logicallyDeleteWork(id);
		count++;
	    } catch (Exception e) {
		LOGGER.error("Error processing massiveLogicallyDelete [id:{}]", id, e);
	    }
	}

	return count;
    }

    /**************** Import ****************/

    @Override
    public List<WorkImport> findWorkImport() throws WccException {
	List<WorkImport> resList;
	try {
	    resList = workImportDAO.findAll();
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return resList;
    }

    /**************** Export ****************/

    @Override
    public ExportBean getFullExportBean(FileFormatEnum fileFormatEnum) throws WccException {

	try {
	    List<Long> idList = new ArrayList<>();
	    ExportBean exportBean = getExportBean(idList, ExportTypeEnum.WORK_FULL_EXPORT, fileFormatEnum);

	    DateFormat dateFormat = new SimpleDateFormat(ConversionUtils.DATE_TIME_STAMP_MILLI);

	    String filePath = exportBean.getCompletePath();
	    if (StringUtils.isNotEmpty(filePath)) {
		String extension = FilenameUtils.getExtension(filePath);
		String timestamp = dateFormat.format(new Date());
		workSftpClient.writeWorkFullExportFileToSftp(timestamp + "." + extension, filePath, configProperties.get(ExportTypeEnum.WORK_FULL_EXPORT.getSftpPathPropertyName()).toString());
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

	try {
	    Path path = Paths.get(createExportFile(idList, null, fileFormatEnum));
	    byte[] bFile = Files.readAllBytes(path);

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

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private String createExportFile(List<Long> idList, ExportTypeEnum exportType, FileFormatEnum fileFormatEnum) throws WccException, IOException {

	int queryOffset = 0;
	// int items = 0;

	String filePath = configProperties.get(ExportTypeEnum.WORK_FULL_EXPORT.getPathPropertyName()).toString();

	WorkWriter workWriter = null;
	switch (fileFormatEnum) {

	    case EXCEL:// Build the Excel File
		workWriter = new WorkWriterExcel(filePath, EnvironmentEnum.SHARED, false);
		break;
	    case CSV:
		workWriter = new WorkWriterCsv(filePath, EnvironmentEnum.SHARED, false);
		break;
	    default:
		throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, "Not implemented file type");
	}

	do {
	    List<Work> workList = loadWorksToExport(idList, exportType, queryOffset, exportWorkPageSize);

	    // if the list is empty, stop
	    if (workList.isEmpty()) {
		break;
	    }

	    List<WorkRow> workRows = importExportHelper.toWorkRowList(workList);

	    workWriter.writeRows(workRows);

	    if (workList.size() < exportWorkPageSize) {
		break;
	    } else {
		workWriter.flush();
	    }

	    queryOffset += exportWorkPageSize;

	    LOGGER.info("WORK EXPORT: {}", queryOffset);

	} while (true);

	workWriter.closeStream();

	return workWriter.getFileName();
    }

    private List<Work> loadWorksToExport(List<Long> idList, ExportTypeEnum exportType, int queryOffset, int PAGE_SIZE) throws WccException {
	// Load Works
	// ----------------------------------------------------------------------
	WorkSearchVO searchVO = new WorkSearchVO();
	searchVO.setIdList(idList);
	searchVO.setDisableOriginCheck(true);

	List<Work> workList = workDAO.findWork(WccUtils.objToMap(searchVO), queryOffset, PAGE_SIZE);

	for (Work work : workList) { // load performer list
	    List<WorkPerformer> performerList = this.workPerformerDAO.findByWork(work.getId());
	    work.setWorkPerformerList(performerList);
	    List<DerivedWork> derivedWork = this.derivedWorkDAO.findByParent(work.getId());
	    work.setDerivedWorkList(derivedWork);
	}
	return workList;
    }

    @Override
    public String createExportFileByWorkList(List<Work> workList, FileFormatEnum fileFormatEnum, String filePath) throws WccException, IOException {

	List<WorkRow> workRows = importExportHelper.toWorkRowList(workList);

	WorkWriter<List<WorkRow>> workWriter = null;
	switch (fileFormatEnum) {

	    case EXCEL:// Build the Excel File
		workWriter = new WorkWriterExcel(filePath, EnvironmentEnum.SHARED, false);
		workWriter.writeRows(workRows);
		workWriter.closeStream();
		break;
	    case CSV:
		workWriter = new WorkWriterCsv(filePath, EnvironmentEnum.SHARED, false);
		workWriter.writeRows(workRows);
		workWriter.closeStream();
		break;

	    default:
		throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, "Not implemented file type");
	}

	return workWriter.getFileName();

    }

}
