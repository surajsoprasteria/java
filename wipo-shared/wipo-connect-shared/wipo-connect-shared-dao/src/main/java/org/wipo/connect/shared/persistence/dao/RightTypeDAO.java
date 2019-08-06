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

import org.wipo.connect.shared.exchange.dto.impl.IpiRightTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.RightTypeFlat;
import org.wipo.connect.shared.exchange.enumerator.IpiRightTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.RightTypeEnum;

/**
 * The RightTypeDAO interface provides methods that manipulate the data from the database.
 *
 * @author fumagalli
 *
 */
public interface RightTypeDAO {

    int insertIpiRightType(IpiRightTypeFlat irt);

    void updateIpiRightType(IpiRightTypeFlat irt);

    Boolean exsistIpiRightTypeCode(String code, Long idToExclude);

    int insertRightType(RightTypeFlat rightType);

    void updateRightType(RightTypeFlat rightType);

    Boolean exsistRightTypeCode(String code, Long idToExclude);

//    /**
//     * Find ipi right type by right type.
//     *
//     * @param fkRightType
//     *            the fk right type
//     * @return the list
//     */
//    List<IpiRightTypeFlat> findIpiRightTypeByRightType(Long fkRightType);

    /**
     * Find ipi right type by code.
     *
     * @param code
     *            the code
     * @return the IpiRightTypeFlat
     */
    IpiRightTypeFlat findIpiRightTypeByCode(IpiRightTypeEnum code);

    /**
     * Find ipi right type by code.
     *
     * @param code
     *            the code
     * @return the IpiRightTypeFlat
     */
    IpiRightTypeFlat findIpiRightTypeByCode(String code);

    /**
     * Find ipi right type by id.
     *
     * @param id
     *            the id
     * @return the IpiRightTypeFlat
     */
    IpiRightTypeFlat findIpiRightTypeById(Long id);

    /**
     * Find all ipi right type.
     *
     * @return the IpiRightTypeFlat list
     */
    List<IpiRightTypeFlat> findAllIpiRightType();

    int insertIpiRightTypeCreationClass(Long fkIpiRightType, Long fkCreationClass);

    int insertRightTypeCreationClass(Long fkRightType, Long fkCreationClass);

    /**
     * Find right type by code.
     *
     * @param code
     *            the code
     * @return the RightTypeFlat
     */
    RightTypeFlat findRightTypeByCode(String code);

    /**
     * Find right type by code.
     *
     * @param code
     *            the code
     * @return the right type flat
     */
    RightTypeFlat findRightTypeByCode(RightTypeEnum code);

    /**
     * Find right type by id.
     *
     * @param id
     *            the id
     * @return the RightTypeFlat
     */
    RightTypeFlat findRightTypeById(Long id);

    /**
     * Find all right type.
     *
     * @return the RightTypeFlat list
     */
    List<RightTypeFlat> findAllRightType();

}
