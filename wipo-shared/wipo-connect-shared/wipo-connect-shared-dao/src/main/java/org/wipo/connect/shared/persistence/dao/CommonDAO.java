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
import java.util.Set;

import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.shared.exchange.dto.impl.Identifier;
import org.wipo.connect.shared.exchange.dto.impl.ImportStatusFlat;
import org.wipo.connect.shared.exchange.dto.impl.IpiRightTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.IpiRoleFlat;
import org.wipo.connect.shared.exchange.dto.impl.ReferenceFlat;
import org.wipo.connect.shared.exchange.dto.impl.RightTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.RoleFlat;
import org.wipo.connect.shared.exchange.dto.impl.Territory;
import org.wipo.connect.shared.exchange.dto.impl.Translation;
import org.wipo.connect.shared.exchange.enumerator.ImportStatusEnum;
import org.wipo.connect.shared.exchange.enumerator.ReferenceTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.TerritoryOrderTypeEnum;
import org.wipo.connect.shared.exchange.vo.TerritoryHierarchyVO;

/**
 * The CommonDAO interface provides common methods that manipulate the data from the database.
 *
 * @author fumagalli
 *
 */
public interface CommonDAO {

    /**
     * Find all territory.
     *
     * @return the list
     */
    List<Territory> findAllTerritory();

    /**
     * Find all territory by country.
     *
     * @return the list
     */
    List<Territory> findAllCountriesFromTerritory();

    /**
     * Find territory names by id.
     *
     * @param idTerritory
     *            the id territory
     * @return the list
     */
    List<Territory> findTerritoryNamesById(Long idTerritory);

    /**
     * Find all territory.
     *
     * @return Territory list
     */
    List<Territory> findAllTerritoryWithIdValue();

    /**
     * Find ipi role by code.
     *
     * @param code
     *            the code
     * @return IpiRoleFlat
     */
    IpiRoleFlat findIpiRoleByCode(String code);

    /**
     * Find ipi role by id.
     *
     * @param id
     *            the id
     * @return IpiRoleFlat
     */
    IpiRoleFlat findIpiRoleById(Long id);

    /**
     * Find all ipi role.
     *
     * @return IpiRoleFlat list
     */
    List<IpiRoleFlat> findAllIpiRole();

    /**
     * Find territory item using id parameter.
     *
     * @param id
     *            the id
     * @return the territory
     */
    Territory findTerritoryById(Long id);

    List<IpiRightTypeFlat> findAllIpiRightTypeByCc(Long idCreationClass);

    /**
     * Find territory by parent.
     *
     * @param idParent
     *            the id parent
     * @return the list
     */
    List<Territory> findTerritoryByParent(Long idParent);

    /**
     * Find territory by parent tisa.
     *
     * @param parentCode
     *            the parent code
     * @return the list
     */
    List<Territory> findTerritoryByParentTisa(String parentCode);

    Territory findTerritoryByTisa(String parentCode);

    TerritoryHierarchyVO getTerritoryHierarchy(String tisaCode);

    Set<Long> resolveTerritoryFormula(String territoryFormula) throws WccException;

    List<Territory> findAllTerritory(TerritoryOrderTypeEnum territoryOrderType);

    int insertTranslationLabel(Translation label);

    void updateTranslationLabel(Translation label);

    int deleteTranslationLabel(Long idTranslation);

    List<Identifier> selectAllIdentifier();

    void evictMainCache();

    List<ReferenceFlat> findReferenceByCode(ReferenceTypeEnum code);

    List<RightTypeFlat> findRightTypeListByCC(List<String> ccCodeList);

    List<RoleFlat> findWorkRoleListByCC(String cc);

    List<ImportStatusFlat> findAllImportStatus();

    ImportStatusFlat findImportStatusByCode(ImportStatusEnum code);

    List<Territory> getTerritoryByParentTisa(String tisaCode);

}
