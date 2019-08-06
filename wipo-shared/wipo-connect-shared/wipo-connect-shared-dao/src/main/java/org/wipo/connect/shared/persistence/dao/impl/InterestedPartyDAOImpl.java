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
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.exception.TooManyResultsException;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.querypagination.PageInfo;
import org.wipo.connect.enumerator.IdentifierManagerRefTableEnum;
import org.wipo.connect.enumerator.OrderByExpressionEnum;
import org.wipo.connect.shared.exchange.dto.impl.GroupDTO;
import org.wipo.connect.shared.exchange.dto.impl.GroupDetailDTO;
import org.wipo.connect.shared.exchange.dto.impl.InterestedParty;
import org.wipo.connect.shared.exchange.dto.impl.InterestedPartyIdentifierFlat;
import org.wipo.connect.shared.exchange.dto.impl.InterestedPartyStatusFlat;
import org.wipo.connect.shared.exchange.enumerator.InterestedPartyStatusEnum;
import org.wipo.connect.shared.exchange.utils.Generator;
import org.wipo.connect.shared.exchange.utils.IdentifierGenerator.WccIdType;
import org.wipo.connect.shared.persistence.BaseDAO;
import org.wipo.connect.shared.persistence.dao.InterestedPartyDAO;
import org.wipo.connect.shared.persistence.mapping.IdentifierManagerMapper;
import org.wipo.connect.shared.persistence.mapping.InterestedPartyIdentifierFlatMapper;
import org.wipo.connect.shared.persistence.mapping.InterestedPartyMapper;
import org.wipo.connect.shared.persistence.mapping.InterestedPartyStatusFlatMapper;

@Service
@Qualifier("interestedPartyDAOImpl")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class InterestedPartyDAOImpl extends BaseDAO<InterestedParty> implements InterestedPartyDAO {

    /** The interested party mapper. */
    @Autowired
    private InterestedPartyMapper interestedPartyMapper;

    /** The interested party identifier flat mapper. */
    @Autowired
    private InterestedPartyIdentifierFlatMapper interestedPartyIdentifierFlatMapper;

    /** The interested party status flat mapper. */
    @Autowired
    private InterestedPartyStatusFlatMapper interestedPartyStatusFlatMapper;

    /** The identifier generator. */
    private Generator identifierGenerator;

    @Autowired
    private IdentifierManagerMapper identifierManagerMapper;

    /** The logger. */
    private static Logger LOGGER = WipoLoggerFactory.getLogger(InterestedPartyDAOImpl.class);

    @Override
    @Loggable(level = "DEBUG")
    @ExecutionTimeTrackable
    public List<InterestedParty> findInterestedParty(Map<String, Object> map) {
	return interestedPartyMapper.findInterestedParty(map);
    }

    @Override
    public InterestedParty findInterestedPartiesByIpMainId(String ipMainId, boolean loadDetail) {
	InterestedParty ip = interestedPartyMapper.findInterestedPartyByMainId(ipMainId);
	if (ip != null && loadDetail) {
	    // no detail to load
	}
	return ip;
    }

    @Override
    @Loggable(level = "DEBUG")
    @ExecutionTimeTrackable
    public Integer countInterestedParty(Map<String, Object> map) {
	return interestedPartyMapper.countInterestedParty(map);
    }

    /**
     * Gets the identifier generator.
     *
     * @return the identifier generator
     */
    public Generator getIdentifierGenerator() {
	return identifierGenerator;
    }

    /**
     * Sets the identifier generator.
     *
     * @param identifierGenerator
     *            the new identifier generator
     */
    public void setIdentifierGenerator(Generator identifierGenerator) {
	this.identifierGenerator = identifierGenerator;
    }

    @Override
    public InterestedParty findInterestedPartyById(Long id, String code) {
	return this.interestedPartyMapper.findInterestedPartyById(id, code);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public int insertInterestedPartyId(Long idInterestedParty, String code) {
	String ipId = this.identifierGenerator.generateWccIdentifier(WccIdType.INTERESTED_PARTY, idInterestedParty);
	return this.interestedPartyIdentifierFlatMapper.insert(idInterestedParty, code, ipId);
    }

    @Override
    public int insertInterestedPartyId(Long idInterestedParty, String code, String value) {
	return this.interestedPartyIdentifierFlatMapper.insert(idInterestedParty, code, value);

    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<InterestedPartyStatusFlat> findAllIPStatus() {
	return this.interestedPartyStatusFlatMapper.findAll();
    }

    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    @Override
    public InterestedParty findInterestedPartiesByNameMainId(String nameMainId) {
	InterestedParty ipToReturn = null;
	List<InterestedParty> ipList = this.interestedPartyMapper.findInterestedPartiesByNameMainId(nameMainId);
	if (ipList.size() == 0) {
	    ipToReturn = null;
	} else if (ipList.size() == 1) {
	    ipToReturn = ipList.get(0);
	} else {
	    ipToReturn = ipList.get(0);
	}
	return ipToReturn;
    }

    @Override
    public void updateIsAffiliated(Long id, boolean b) {

    }

    @Override
    public int deleteIPName(Long interestedPartyId, Long nameId) {
	return interestedPartyMapper.deleteIPName(interestedPartyId, nameId);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public int deleteIPCitizenship(Long interestedPartyId) {
	return this.interestedPartyMapper.deleteIPCitizenship(interestedPartyId, null);
    }

    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    @Override
    public int insertIPCitizenship(Long interestedPartyId, Long territoryId) {
	return this.interestedPartyMapper.insertIPCitizenship(interestedPartyId, territoryId);
    }

    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    @Override
    public int insertIPName(Long interestedPartyId, Long nameId) {
	return this.interestedPartyMapper.insertIPName(interestedPartyId, nameId);
    }

    @Override
    public Long findInterestedPartyIdByIpMainId(String mainId, boolean excludeDeleted) {
	return interestedPartyMapper.findInterestedPartyIdByMainId(mainId, excludeDeleted);
    }

    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    @Override
    public int logicallyDeleteIp(Long ipId) throws WccException {
	int i = this.interestedPartyMapper.updateStatus(ipId, InterestedPartyStatusEnum.DELETED);
	interestedPartyMapper.markAsDeleted(ipId);
	if (0 == i) {
	    LOGGER.warn("No interestedParty id was found", ipId);
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, "No interestedParty id was found");
	}
	return i;
    }

    @Override
    public boolean checkIfExistsAnotherIp(String nameMainId, Long idIp) {
	return interestedPartyMapper.checkIfExistsAnotherIp(nameMainId, idIp);
    }

    @Override
    @ExecutionTimeTrackable
    public List<InterestedParty> findInterestedParty(Map<String, Object> map, Integer offset, Integer limit) {
	List<InterestedParty> interestedPartyList;
	String orderByExpression = (String) map.get("orderByExpression");

	if (StringUtils.contains(orderByExpression, OrderByExpressionEnum.RO_SCORE.getField())) {
	    map.remove("orderByExpression");
	}

	List<Long> idList = interestedPartyMapper.findIdsPage(map, new RowBounds(offset, limit));

	if (!idList.isEmpty()) {
	    if (StringUtils.contains(orderByExpression, OrderByExpressionEnum.RO_MAIN_NAME.getFieldsSplitted()[0])) {
		map.remove("orderByExpression");
	    }

	    interestedPartyList = interestedPartyMapper.findByIdList(idList, map);

	    if (StringUtils.contains(orderByExpression, OrderByExpressionEnum.RO_MAIN_NAME.getFieldsSplitted()[0])) {
		interestedPartyList.sort(Comparator.comparing(w -> idList.indexOf(w.getId())));
	    }

	} else {
	    interestedPartyList = new ArrayList<>();
	}

	return interestedPartyList;
    }

    @Override
    @ExecutionTimeTrackable
    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = { TooManyResultsException.class })
    public List<InterestedParty> findInterestedParty(Map<String, Object> map, Integer maxResults) {
	return findInterestedParty(map, 0, maxResults);
    }

    @Override
    @ExecutionTimeTrackable
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public int insertDummy(InterestedParty dto) {
	boolean check = identifierManagerMapper.checkIdUniqueness(IdentifierManagerRefTableEnum.INTERESTED_PARTY.getRefTable(), dto.getMainId(), null);

	if (!check) {
	    throw new IllegalStateException("The table " + IdentifierManagerRefTableEnum.INTERESTED_PARTY.getRefTable() + " already contains a valid mainId: " + dto.getMainId());
	}

	return interestedPartyMapper.insertDummy(dto);
    }

    @Override
    @ExecutionTimeTrackable
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public Future<Integer> deleteDummy(Long idInterestedParty) {
	Integer res = interestedPartyMapper.deleteDummy(idInterestedParty);
	return new AsyncResult<>(res);
    }

    @Override
    public int markAsDeleted(Long idInterestedParty) {
	return interestedPartyMapper.markAsDeleted(idInterestedParty);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<InterestedParty> find(List<Long> idList) {
	return this.interestedPartyMapper.findByIdList(idList, new HashMap<>());
    }

    @Override
    public List<GroupDTO> findNameGroups(Map<String, Object> searchMap) throws WccException {
	return interestedPartyMapper.selectGroups(searchMap);
    }

    public List<GroupDTO> findNameGroupByIdList(List<Long> idList, PageInfo pageInfo) throws WccException {
	return interestedPartyMapper.findNameGroupByIdList(idList, pageInfo);
    }

    @Override
    public List<Long> findNameGroupIdList(Map<String, Object> searchMap, Integer offset, Integer limit) throws WccException {
	return interestedPartyMapper.findNameGroupIdList(searchMap, new RowBounds(offset, limit));
    }

    public Integer countNameGroup(Map<String, Object> searchMap) throws WccException {
	return interestedPartyMapper.countNameGroup(searchMap);
    }

    @Override
    public GroupDTO findGroupById(Long idGroup, List<String> groupNameTypeList) {
	return interestedPartyMapper.selectGroupById(idGroup, groupNameTypeList);
    }

    @Override
    public List<GroupDetailDTO> findGroupDetailsByIdGroup(Long idGroup, List<String> groupNameTypeList) {
	return interestedPartyMapper.selectGroupDetailsByIdGroup(idGroup, groupNameTypeList);
    }

    @Override
    public int deleteIpIdentifierByIp(Long idInterestedParty) {
	return interestedPartyIdentifierFlatMapper.deleteIpIdentifierByIp(idInterestedParty);
    }

    @Override
    public List<InterestedPartyIdentifierFlat> findIdentifierByValueAndType(String value, String type) {
	return interestedPartyIdentifierFlatMapper.findByValueAndType(value, type);
    }

    @Override
    public Boolean isIdentifierValueAlreadyPresent(String code, String value, Long ipId) {
	return interestedPartyIdentifierFlatMapper.isIdentifierValueAlreadyPresent(code, value, ipId);
    }

    @Override
    public List<InterestedParty> findByName(Long idName) {
	return this.interestedPartyMapper.findByName(idName);
    }

    @Override
    @ExecutionTimeTrackable
    public List<InterestedParty> findInterestedPartyForReindex(Integer offset, Integer limit) {
	List<InterestedParty> ipList;
	List<Long> idList = interestedPartyMapper.findIdsPageForReindex(new RowBounds(offset, limit));
	if (CollectionUtils.isNotEmpty(idList)) {
	    ipList = interestedPartyMapper.findInterestedPartyForReindex(idList);
	} else {
	    ipList = new ArrayList<>();
	}
	return ipList;
    }

    @Override
    public Integer getCountFindInterestedParty(Map<String, Object> searchMap) {
	if (StringUtils.contains((String) searchMap.get("orderByExpression"), OrderByExpressionEnum.RO_SCORE.getField())) {
	    searchMap.remove("orderByExpression");
	}
	return interestedPartyMapper.selectCountFindInterestedParty(searchMap);
    }
}
