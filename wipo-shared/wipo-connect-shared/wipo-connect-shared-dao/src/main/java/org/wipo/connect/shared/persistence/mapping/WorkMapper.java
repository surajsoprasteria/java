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

package org.wipo.connect.shared.persistence.mapping;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.wipo.connect.shared.exchange.dto.impl.Title;
import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.enumerator.WorkStatusEnum;

public interface WorkMapper extends Mapper<Work> {

    int deleteWorkInstrumentByWork(@Param(value = "idWork") Long idWork);

    List<Work> findByIdList(@Param(value = "idList") List<Long> idList, @Param("map") Map<String, Object> map);

    List<Work> findDuplicateWork(@Param(value = "work") Work work);

    List<Work> findWork(Map<String, Object> map);

    Work findWorkById(@Param("id") Long id, @Param("code") String code);

    List<Work> findWorkToRegister();

    List<Work> findWorkToRegisterInternationally();

    int insertWorkInstrument(@Param("idWork") Long idWork, @Param("code") String code);

    int updateWorkStatus(@Param(value = "idWork") Long idWork, @Param(value = "code") WorkStatusEnum code);

    List<Title> findWorkTitles(@Param(value = "idWork") Long idWork);

    Work findWorkByIdentifier(@Param(value = "identifierValue") String identifierValue, @Param(value = "identifierType") String identifierType,
	    @Param(value = "includeDeleted") boolean includeDeleted);

    List<Long> findIdsPage(Map<String, Object> map, RowBounds rowBounds);

    List<Long> findIdsPage(Map<String, Object> map);

    Long findWorkIdByIdentifier(@Param(value = "identifierValue") String identifierValue, @Param(value = "identifierType") String identifierType,
	    @Param(value = "includeDeleted") boolean includeDeleted);

    int insertDummy(Work out);

    int deleteDummy(@Param(value = "idWork") Long idWork);

    int markAsDeleted(@Param(value = "idWork") Long idWork);

    Work findByMainId(@Param(value = "mainId") String mainId);

    Long findWorkIdByMainId(@Param(value = "mainId") String mainId, @Param(value = "excludeDeleted") boolean excludeDeleted);

    List<Long> findIdsPageForReindex(RowBounds rowBounds);

    List<Work> findWorkForReindex(@Param("idList") List<Long> idList);

    Integer selectCountFindWork(Map<String, Object> searchMap);

    String findCreationClassByMainId(@Param("mainId") String mainId);
}
