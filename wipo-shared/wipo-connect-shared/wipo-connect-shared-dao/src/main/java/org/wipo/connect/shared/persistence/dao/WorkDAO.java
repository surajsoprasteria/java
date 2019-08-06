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

package org.wipo.connect.shared.persistence.dao;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.shared.exchange.dto.impl.Title;
import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.enumerator.WorkStatusEnum;
import org.wipo.connect.shared.persistence.Dao;

public interface WorkDAO extends Dao<Work> {

    int deleteWorkInstrumentByWork(Long idWork);

    List<Title> findWorkTitles(Long idWork);

    List<Work> find(List<Long> idList);

    Work findWorkById(Long id, String code);

    List<Work> findDuplicateWork(Work work);

    List<Work> findWork(Map<String, Object> map);

    List<Work> findWorkToRegister();

    List<Work> findWorkToRegisterInternationally();

    int insert(Work dto);

    int insertWorkInstrument(Long idWork, String code);

    void update(Work dto);

    void updateWorkStatus(Long idWork, WorkStatusEnum code);

    Work findWorkByIdentifier(String identifierValue, String identifierType);

    int logicallyDeleteWork(Long idWork) throws WccException;

    Work findWorkByIdentifier(String identifierValue, String identifierType, boolean includeDeleted);

    Long findWorkIdByIdentifier(String identifierValue, String identifierType);

    Long findWorkIdByIdentifier(String identifierValue, String identifierType, boolean includeDeleted);

    List<Work> findWork(Map<String, Object> map, Integer offset, Integer limit);

    List<Work> findWork(Map<String, Object> map, Integer maxResults);

    int insertDummy(Work dto);

    Future<Integer> deleteDummy(Long idWork);

    int markAsDeleted(Long idWork);

    Work findByMainId(String mainId, boolean loadDetail);

    Long findWorkIdByMainId(String mainId, boolean excludeDeleted);

    List<Work> findWorkForReindex(Integer offset, Integer limit);

    Integer getCountFindWork(Map<String, Object> searchMap);

    String findCreationClassByMainId(String mainId);
}
