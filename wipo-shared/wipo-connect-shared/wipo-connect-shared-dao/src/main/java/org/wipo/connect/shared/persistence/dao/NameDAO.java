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

import org.wipo.connect.common.exception.NameDuplicateException;
import org.wipo.connect.shared.exchange.dto.impl.Name;
import org.wipo.connect.shared.exchange.dto.impl.NameVO;
import org.wipo.connect.shared.persistence.Dao;

public interface NameDAO extends Dao<Name> {

    List<Name> findName(Map<String, Object> map);

    List<Name> findNameByIp(Long idInterestedParty);

    List<Name> findNameByNameMainId(String nameMainId);

    Long findIpByName(Long idName);

    int markAsDeletedByIp(Long idInterestedParty);

    int markAsDeleted(Long idName);

    Future<Integer> deleteDummy(Long idName);

    int insertDummy(Name dto) throws NameDuplicateException;

    List<Name> findByNameMainId(String nameMainId);

    Long findIdByNameMainId(String nameMainId);

    List<NameVO> findNameVO(Map<String, Object> map);

    List<NameVO> findNameVO(Map<String, Object> map, Integer offset, Integer limit);

    Integer getCountFindNameVO(Map<String, Object> searchMap);

}
