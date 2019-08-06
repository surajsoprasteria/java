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
import org.wipo.connect.common.querypagination.PageInfo;
import org.wipo.connect.shared.exchange.dto.impl.GroupDTO;
import org.wipo.connect.shared.exchange.dto.impl.GroupDetailDTO;
import org.wipo.connect.shared.exchange.dto.impl.InterestedParty;
import org.wipo.connect.shared.exchange.enumerator.InterestedPartyStatusEnum;

public interface InterestedPartyMapper extends Mapper<InterestedParty> {

    List<InterestedParty> findInterestedParty(Map<String, Object> map);

    Integer countInterestedParty(Map<String, Object> map);

    InterestedParty findInterestedPartyById(@Param("id") Long id, @Param("code") String code);

    Long findInterestedPartyIdByMainId(@Param("mainId") String mainId, @Param("excludeDeleted") boolean excludeDeleted);

    List<InterestedParty> findInterestedPartiesByNameMainId(@Param("nameMainId") String nameMainId);

    int deleteIPName(@Param("interestedPartyId") Long interestedPartyId, @Param("nameId") Long nameId);

    int deleteIPCitizenship(@Param("interestedPartyId") Long interestedPartyId, @Param("citizenshipId") Long citizenshipId);

    int insertIPCitizenship(@Param("interestedPartyId") Long interestedPartyId, @Param("territoryId") Long territoryId);

    int insertIPName(@Param("interestedPartyId") Long interestedPartyId, @Param("nameId") Long nameId);

    int updateStatus(@Param("interestedPartyId") Long ipId, @Param("code") InterestedPartyStatusEnum code);

    boolean checkIfExistsAnotherIp(@Param("nameMainId") String nameMainId, @Param("idIp") Long idIp);

    List<Long> findIdsPage(Map<String, Object> map, RowBounds rowBounds);

    List<Long> findIdsPage(Map<String, Object> map);

    int insertDummy(InterestedParty ip);

    int deleteDummy(@Param(value = "idInterestedParty") Long idInterestedParty);

    int markAsDeleted(@Param(value = "idInterestedParty") Long idInterestedParty);

    List<InterestedParty> findByIdList(@Param("idList") List<Long> idList, @Param("map") Map<String, Object> map);

    List<GroupDTO> selectGroups(@Param("map") Map<String, Object> map);

    List<GroupDTO> findNameGroupByIdList(@Param("idList") List<Long> idList, @Param("pageInfo") PageInfo pageInfo);

    List<Long> findNameGroupIdList(@Param("map") Map<String, Object> map, RowBounds rowBounds);

    Integer countNameGroup(@Param("map") Map<String, Object> map);

    GroupDTO selectGroupById(@Param("idGroup") Long idGroup, @Param("nameTypeList") List<String> groupNameTypeList);

    List<GroupDetailDTO> selectGroupDetailsByIdGroup(@Param("idGroup") Long idGroup, @Param("nameTypeList") List<String> groupNameTypeList);

    List<InterestedParty> findByName(@Param("idName") Long idName);

    List<Long> findIdsPageForReindex(RowBounds rowBounds);

    List<InterestedParty> findInterestedPartyForReindex(@Param("idList") List<Long> idList);

    Integer selectCountFindInterestedParty(Map<String, Object> searchMap);

    InterestedParty findInterestedPartyByMainId(@Param("mainId") String mainId);

}
