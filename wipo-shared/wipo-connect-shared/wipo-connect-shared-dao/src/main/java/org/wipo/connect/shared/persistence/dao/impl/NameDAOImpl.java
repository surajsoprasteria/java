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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.exception.NameDuplicateException;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.enumerator.IdentifierManagerRefTableEnum;
import org.wipo.connect.shared.exchange.dto.impl.Name;
import org.wipo.connect.shared.exchange.dto.impl.NameVO;
import org.wipo.connect.shared.persistence.BaseDAO;
import org.wipo.connect.shared.persistence.dao.NameDAO;
import org.wipo.connect.shared.persistence.mapping.IdentifierManagerMapper;
import org.wipo.connect.shared.persistence.mapping.NameMapper;

/**
 * The Class NameDAOImpl.
 *
 * @author Minervini
 */
@Service
@Qualifier("nameDAOImpl")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class NameDAOImpl extends BaseDAO<Name> implements NameDAO {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(NameDAOImpl.class);

    /** The name mapper. */
    @Autowired
    private NameMapper nameMapper;

    @Autowired
    private IdentifierManagerMapper identifierManagerMapper;

    @Override
    @ExecutionTimeTrackable
    public List<Name> findName(Map<String, Object> map) {
	return this.nameMapper.findName(map);
    }

    @Override
    public List<Name> findNameByIp(Long idInterestedParty) {
	return this.nameMapper.findNameByIp(idInterestedParty);
    }

    @Override
    public List<Name> findNameByNameMainId(String nameMainId) {
	return this.nameMapper.findNameByNameMainId(nameMainId);
    }

    @Override
    public Long findIpByName(Long idName) {
	return nameMapper.findIpByName(idName);
    }

    @Override
    public int markAsDeleted(Long idName) {
	return nameMapper.markAsDeleted(idName);
    }

    @Override
    public int markAsDeletedByIp(Long idInterestedParty) {
	return nameMapper.markAsDeletedByIp(idInterestedParty);
    }

    @Override
    @Async
    @ExecutionTimeTrackable
    @Transactional(propagation = Propagation.REQUIRES_NEW, /* isolation = Isolation.SERIALIZABLE, */ rollbackFor = Throwable.class)
    public Future<Integer> deleteDummy(Long idName) {
	Integer res = nameMapper.deleteDummy(idName);
	return new AsyncResult<>(res);
    }

    @Override
    @ExecutionTimeTrackable
    @Transactional(propagation = Propagation.REQUIRES_NEW, /* isolation = Isolation.SERIALIZABLE, */ rollbackFor = Throwable.class)
    public int insertDummy(Name dto) throws NameDuplicateException {
	boolean check = identifierManagerMapper.checkIdUniqueness(IdentifierManagerRefTableEnum.NAME.getRefTable(), dto.getMainId(), null);

	if (!check) {
	    LOGGER.error("The table " + IdentifierManagerRefTableEnum.NAME.getRefTable() + " already contains a valid mainId: " + dto.getMainId());
	    throw new NameDuplicateException(dto.getMainId());
	}

	return nameMapper.insertDummy(dto);
    }

    @Override
    public List<Name> findByNameMainId(String nameMainId) {
	return nameMapper.findByNameMainId(nameMainId);
    }

    @Override
    public Long findIdByNameMainId(String nameMainId) {
	return nameMapper.findIdByNameMainId(nameMainId);
    }

    @Override
    @ExecutionTimeTrackable
    public List<NameVO> findNameVO(Map<String, Object> map) {
	return this.nameMapper.findNameVO(map);
    }

    @Override
    public List<NameVO> findNameVO(Map<String, Object> map, Integer offset, Integer limit) {

	List<Long> idList = nameMapper.findIdsPage(map, new RowBounds(offset, limit));

	if (!idList.isEmpty()) {

	    Map<String, Object> auxMap = new HashMap<>(map);
	    auxMap.put("idList", idList);
	    return nameMapper.findNameVO(auxMap);
	} else {
	    return new ArrayList<>();
	}

    }

    @Override
    public Integer getCountFindNameVO(Map<String, Object> searchMap) {
	return nameMapper.selectCountFindNameVO(searchMap);
    }

}