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
import org.wipo.connect.common.querypagination.PageInfo;
import org.wipo.connect.shared.exchange.dto.impl.GroupDTO;
import org.wipo.connect.shared.exchange.dto.impl.GroupDetailDTO;
import org.wipo.connect.shared.exchange.dto.impl.InterestedParty;
import org.wipo.connect.shared.exchange.dto.impl.InterestedPartyIdentifierFlat;
import org.wipo.connect.shared.exchange.dto.impl.InterestedPartyStatusFlat;
import org.wipo.connect.shared.persistence.Dao;

public interface InterestedPartyDAO extends Dao<InterestedParty> {

    InterestedParty findInterestedPartiesByIpMainId(String ipMainId, boolean loadDetail);

    List<InterestedParty> findInterestedParty(Map<String, Object> map);

    Integer countInterestedParty(Map<String, Object> map);

    InterestedParty findInterestedPartyById(Long id, String code);

    InterestedParty findInterestedPartiesByNameMainId(String ipiNameNumber);

    void updateIsAffiliated(Long id, boolean b);

    List<InterestedPartyStatusFlat> findAllIPStatus();

    int insertInterestedPartyId(Long interestedPartyId, String name);

    int insertInterestedPartyId(Long interestedPartyId, String code, String value);

    int deleteIPName(Long interestedPartyId, Long id);

    int deleteIPCitizenship(Long interestedPartyId);

    int insertIPCitizenship(Long interestedPartyId, Long id);

    int insertIPName(Long interestedPartyId, Long id);

    int logicallyDeleteIp(Long ipId) throws WccException;

    boolean checkIfExistsAnotherIp(String ipiNameNumber, Long idIp);

    List<InterestedParty> findInterestedParty(Map<String, Object> map, Integer offset, Integer limit);

    List<InterestedParty> findInterestedParty(Map<String, Object> map, Integer maxResults);

    int insertDummy(InterestedParty dto);

    Future<Integer> deleteDummy(Long idInterestedParty);

    int markAsDeleted(Long idInterestedParty);

    List<InterestedParty> find(List<Long> idList);

    Long findInterestedPartyIdByIpMainId(String mainId, boolean excludeDeleted);

    List<GroupDTO> findNameGroups(Map<String, Object> searchMap) throws WccException;

    List<GroupDTO> findNameGroupByIdList(List<Long> idList, PageInfo pageInfo) throws WccException;

    List<Long> findNameGroupIdList(Map<String, Object> searchMap, Integer offset, Integer limit) throws WccException;

    Integer countNameGroup(Map<String, Object> searchMap) throws WccException;

    GroupDTO findGroupById(Long idGroup, List<String> groupNameTypeList);

    List<GroupDetailDTO> findGroupDetailsByIdGroup(Long idGroup, List<String> groupNameTypeList);

    int deleteIpIdentifierByIp(Long idInterestedParty);

    List<InterestedPartyIdentifierFlat> findIdentifierByValueAndType(String value, String type);

    Boolean isIdentifierValueAlreadyPresent(String code, String value, Long ipId);

    List<InterestedParty> findByName(Long idName);

    List<InterestedParty> findInterestedPartyForReindex(Integer offset, Integer limit);

    Integer getCountFindInterestedParty(Map<String, Object> searchMap);
}
