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

package org.wipo.connect.shared.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.enumerator.IdentifierManagerRefTableEnum;
import org.wipo.connect.enumerator.OrderByExpressionEnum;
import org.wipo.connect.shared.exchange.dto.impl.DerivedWork;
import org.wipo.connect.shared.exchange.dto.impl.Title;
import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.dto.impl.WorkPerformer;
import org.wipo.connect.shared.exchange.enumerator.WorkStatusEnum;
import org.wipo.connect.shared.persistence.BaseDAO;
import org.wipo.connect.shared.persistence.dao.WorkDAO;
import org.wipo.connect.shared.persistence.mapping.DerivedWorkMapper;
import org.wipo.connect.shared.persistence.mapping.IdentifierManagerMapper;
import org.wipo.connect.shared.persistence.mapping.WorkMapper;
import org.wipo.connect.shared.persistence.mapping.WorkPerformerMapper;

@Service
@Qualifier("workDAOImpl")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class WorkDAOImpl extends BaseDAO<Work> implements WorkDAO {

    /** The work mapper. */
    @Autowired
    private WorkMapper workMapper;

    @Autowired
    private IdentifierManagerMapper identifierManagerMapper;

    @Autowired
    private WorkPerformerMapper workPerformerMapper;

    @Autowired
    private DerivedWorkMapper workDerivedMapper;

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public int deleteWorkInstrumentByWork(Long idWork) {
	return this.workMapper.deleteWorkInstrumentByWork(idWork);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<Work> find(List<Long> idList) {
	return this.workMapper.findByIdList(idList, new HashMap<>());
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public Work findWorkById(@Param("id") Long id, @Param("code") String code) {
	return this.workMapper.findWorkById(id, code);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public Work find(Long id) {
	return super.find(id);
    }

    @Override
    public List<Work> findDuplicateWork(Work work) {
	return this.workMapper.findDuplicateWork(work);
    }

    @Override
    @ExecutionTimeTrackable
    public List<Work> findWork(Map<String, Object> map) {
	return workMapper.findWork(map);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<Work> findWorkToRegister() {
	return this.workMapper.findWorkToRegister();
    }

    @Override
    public List<Work> findWorkToRegisterInternationally() {
	return this.workMapper.findWorkToRegisterInternationally();
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public int insert(Work dto) {
	return this.workMapper.insert(dto);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public int insertWorkInstrument(Long idWork, String code) {
	return this.workMapper.insertWorkInstrument(idWork, code);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public void update(Work dto) {
	this.workMapper.updateByPrimaryKey(dto);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public void updateWorkStatus(Long idWork, WorkStatusEnum code) {
	this.workMapper.updateWorkStatus(idWork, code);
    }

    @Override
    public List<Title> findWorkTitles(Long idWork) {
	return this.workMapper.findWorkTitles(idWork);
    }

    @Override
    public Work findWorkByIdentifier(String identifierValue, String identifierType) {
	return this.workMapper.findWorkByIdentifier(identifierValue, identifierType, false);
    }

    @Override
    public Work findWorkByIdentifier(String identifierValue, String identifierType, boolean includeDeleted) {
	return this.workMapper.findWorkByIdentifier(identifierValue, identifierType, includeDeleted);
    }

    @Override
    public Long findWorkIdByIdentifier(String identifierValue, String identifierType) {
	return this.workMapper.findWorkIdByIdentifier(identifierValue, identifierType, false);
    }

    @Override
    public Long findWorkIdByIdentifier(String identifierValue, String identifierType, boolean includeDeleted) {
	return this.workMapper.findWorkIdByIdentifier(identifierValue, identifierType, includeDeleted);
    }

    @Override
    @ExecutionTimeTrackable
    public int logicallyDeleteWork(Long idWork) throws WccException {

	int i = workMapper.updateWorkStatus(idWork, WorkStatusEnum.DELETED);
	if (0 == i) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, "No Work id was found");
	}
	return i;
    }

    @Override
    @ExecutionTimeTrackable
    public List<Work> findWork(Map<String, Object> map, Integer offset, Integer limit) {
	List<Work> workList;

	List<Long> idList = workMapper.findIdsPage(map, new RowBounds(offset, limit));

	if (!idList.isEmpty()) {

	    String orderByExpression = (String) map.get("orderByExpression");
	    if (StringUtils.contains(orderByExpression, OrderByExpressionEnum.WORK_MAIN_TITLE.getField())) {
		map.remove("orderByExpression");
	    }

	    workList = workMapper.findByIdList(idList, map);

	    if (StringUtils.contains(orderByExpression, OrderByExpressionEnum.WORK_MAIN_TITLE.getField())) {
		workList.sort(Comparator.comparing(w -> idList.indexOf(w.getId())));
	    }

	} else {
	    workList = new ArrayList<>();
	}

	return workList;
    }

    @Override
    @ExecutionTimeTrackable
    // @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = { TooManyResultsException.class })
    public List<Work> findWork(Map<String, Object> map, Integer maxResults) {
	return findWork(map, 0, maxResults);
    }

    @Override
    @ExecutionTimeTrackable
    @Transactional(propagation = Propagation.REQUIRES_NEW, /* isolation = Isolation.SERIALIZABLE, */ rollbackFor = Throwable.class)
    public int insertDummy(Work dto) {
	boolean check = identifierManagerMapper.checkIdUniqueness(IdentifierManagerRefTableEnum.WORK.getRefTable(), dto.getMainId(), null);

	if (!check) {
	    throw new IllegalStateException("The table " + IdentifierManagerRefTableEnum.WORK.getRefTable() + " already contains a valid mainId: " + dto.getMainId());
	}

	return workMapper.insertDummy(dto);
    }

    @Override
    @Async
    @ExecutionTimeTrackable
    @Transactional(propagation = Propagation.REQUIRES_NEW, /* isolation = Isolation.SERIALIZABLE, */ rollbackFor = Throwable.class)
    public Future<Integer> deleteDummy(Long idWork) {
	Integer res = workMapper.deleteDummy(idWork);
	return new AsyncResult<>(res);
    }

    @Override
    public int markAsDeleted(Long idWork) {
	return workMapper.markAsDeleted(idWork);
    }

    @Override
    public Work findByMainId(String mainId, boolean loadDetail) {
	Work work = workMapper.findByMainId(mainId);

	if (work != null && loadDetail) {
	    List<WorkPerformer> performerList = this.workPerformerMapper.findByWork(work.getId());
	    work.setWorkPerformerList(performerList);

	    List<DerivedWork> derivedWork = this.workDerivedMapper.findByParent(work.getId());
	    work.setDerivedWorkList(derivedWork);
	}

	return work;
    }

    @Override
    public Long findWorkIdByMainId(String mainId, boolean excludeDeleted) {
	return workMapper.findWorkIdByMainId(mainId, excludeDeleted);
    }

    @Override
    @ExecutionTimeTrackable
    public List<Work> findWorkForReindex(Integer offset, Integer limit) {
	List<Work> workList;
	List<Long> idList = workMapper.findIdsPageForReindex(new RowBounds(offset, limit));
	if (CollectionUtils.isNotEmpty(idList)) {
	    workList = workMapper.findWorkForReindex(idList);
	} else {
	    workList = new ArrayList<>();
	}
	return workList;
    }

    @Override
    public Integer getCountFindWork(Map<String, Object> searchMap) {
	return workMapper.selectCountFindWork(searchMap);
    }

    @Override
    public String findCreationClassByMainId(String mainId) {
	return workMapper.findCreationClassByMainId(mainId);
    }

}