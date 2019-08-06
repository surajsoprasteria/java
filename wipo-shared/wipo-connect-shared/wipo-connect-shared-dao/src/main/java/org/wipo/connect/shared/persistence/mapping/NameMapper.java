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
import org.wipo.connect.shared.exchange.dto.impl.Name;
import org.wipo.connect.shared.exchange.dto.impl.NameVO;

/**
 * The Interface NameMapper.
 *
 * @author fumagalli
 */
public interface NameMapper extends Mapper<Name> {

    /**
     * Find name.
     *
     * @param map
     *                the map
     * @return the list
     */
    List<Name> findName(Map<String, Object> map);

    /**
     * Find name.
     *
     * @param idInterestedParty
     *                              the id interested party
     * @return the list
     */
    List<Name> findNameByIp(@Param(value = "idInterestedParty") Long idInterestedParty);

    /**
     * Find name by ipi name number.
     *
     * @param ipNameNumber
     *                         the ip name number
     * @return the list
     */
    List<Name> findNameByNameMainId(@Param(value = "nameMainId") String nameMainId);

    /**
     * Find ip by name.
     *
     * @param idName
     *                   the id name
     * @return the long
     */
    Long findIpByName(Long idName);

    /**
     * Mark as deleted.
     *
     * @param idInterestedParty
     *                              the id interested party
     * @return the int
     */
    int markAsDeleted(@Param(value = "idName") Long idName);

    /**
     * Mark as deleted by ip.
     *
     * @param idInterestedParty
     *                              the id interested party
     * @return the int
     */
    int markAsDeletedByIp(@Param(value = "idInterestedParty") Long idInterestedParty);

    /**
     * Delete dummy.
     *
     * @param idInterestedParty
     *                              the id interested party
     * @return the int
     */
    int deleteDummy(@Param(value = "idName") Long idName);

    /**
     * Insert dummy.
     *
     * @param out
     *                the out
     * @return the int
     */
    int insertDummy(Name name);

    /**
     * Find name by import id.
     *
     * @param importId
     *                     the import id
     * @return the list
     */
    List<Name> findNameByImportId(@Param(value = "importId") String importId);

    List<Name> findByNameMainId(@Param(value = "nameMainId") String nameMainId);

    Long findIdByNameMainId(@Param(value = "nameMainId") String nameMainId);

    List<NameVO> findNameVO(Map<String, Object> map);

    List<Long> findIdsPage(Map<String, Object> map, RowBounds rowBounds);

    Integer selectCountFindNameVO(Map<String, Object> searchMap);
}
