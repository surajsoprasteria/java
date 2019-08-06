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

package org.wipo.connect.shared.exchange.business;

import java.util.List;
import java.util.Map;

import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.vo.DatabaseInfoVO;
import org.wipo.connect.shared.exchange.dto.impl.CreationClassFlat;
import org.wipo.connect.shared.exchange.dto.impl.CustomValidation;
import org.wipo.connect.shared.exchange.dto.impl.IdentifierFlat;
import org.wipo.connect.shared.exchange.dto.impl.ImportStatusFlat;
import org.wipo.connect.shared.exchange.dto.impl.IpiRightTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.IpiRoleFlat;
import org.wipo.connect.shared.exchange.dto.impl.Name;
import org.wipo.connect.shared.exchange.dto.impl.ReferenceFlat;
import org.wipo.connect.shared.exchange.dto.impl.ReferenceTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.RightTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.RoleFlat;
import org.wipo.connect.shared.exchange.dto.impl.Territory;
import org.wipo.connect.shared.exchange.dto.impl.TerritoryNameIdentifierFlat;
import org.wipo.connect.shared.exchange.enumerator.TerritoryOrderTypeEnum;
import org.wipo.connect.shared.exchange.vo.NameSearchResultVO;
import org.wipo.connect.shared.exchange.vo.NameSearchVO;
import org.wipo.connect.shared.exchange.vo.TerritoryHierarchyVO;

/**
 * The WorkBusiness interface provides business methods orchestrating the functions made available by the layer DAO.
 *
 * @author fumagalli
 */
public interface CommonBusiness {

    /**
     * Check client identity.
     *
     * @param clientCode
     *            the client code
     * @param clientKey
     *            the client key
     * @return the boolean
     * @throws WccException
     *             the wcc exception
     */
    Boolean checkClientIdentity(String clientCode, String clientKey) throws WccException;

    /**
     * Gets the countries from territory list.
     *
     * @return the countries from territory list
     * @throws WccException
     *             the wcc exception
     */
    List<Territory> getCountriesFromTerritoryList(Boolean includeWorld) throws WccException;

    /**
     * Find territory names by id.
     *
     * @param idTerritory
     *            the id territory
     * @return the list
     * @throws WccException
     *             the wcc exception
     */
    List<Territory> findTerritoryNamesById(Long idTerritory) throws WccException;

    /**
     * Get all identifiers.
     *
     * @return the all identifier
     * @throws WccException
     *             the wcc exception
     */
    List<IdentifierFlat> getAllIdentifier() throws WccException;

    /**
     * Gets the identifier by id.
     *
     * @param idIdentifier
     *            the id identifier
     * @return the identifier by id
     * @throws WccException
     *             the wcc exception
     */
    IdentifierFlat getIdentifierById(Long idIdentifier) throws WccException;

    /**
     * Find territory.
     *
     * @param terrOrderType
     *            the terr order type
     * @return the map
     * @throws WccException
     *             the wcc exception
     */
    Map<Long, Territory> getTerritoryMap(TerritoryOrderTypeEnum terrOrderType) throws WccException;

    Map<String, String> getCountriesFromTerritoryMap() throws WccException;

    /**
     * Find right type.
     *
     * @return the list
     * @throws WccException
     *             the wcc exception
     */
    List<RightTypeFlat> getRightTypeList() throws WccException;

    // /**
    // * Find ipi right type by right type.
    // *
    // * @param fkRightType
    // * the fk right type
    // * @return the list
    // * @throws WccException
    // * the wcc exception
    // */
    // List<IpiRightTypeFlat> findIpiRightTypeByRightType(Long id) throws WccException;

    /**
     * Gets the role list.
     *
     * @return the role list
     * @throws WccException
     *             the wcc exception
     */
    List<RoleFlat> getRoleList() throws WccException;

    /**
     * Gets the reference by code.
     *
     * @param code
     *            the code
     * @return the reference by code
     * @throws WccException
     *             the wcc exception
     */
    List<ReferenceFlat> getReferenceByCode(String code) throws WccException;

    /**
     * Find role by id.
     *
     * @param id
     *            the id
     * @return the role flat
     * @throws WccException
     *             the wcc exception
     */
    RoleFlat findRoleById(Long id) throws WccException;

    /**
     * Gets the territory list.
     *
     * @param territoryOrderType
     *            the territory order type
     * @return the territory list
     * @throws WccException
     *             the wcc exception
     */
    List<Territory> getTerritoryList(TerritoryOrderTypeEnum territoryOrderType) throws WccException;

    /**
     * Gets the territory hierarchy.
     *
     * @return the territory hierarchy
     * @throws WccException
     *             the wcc exception
     */
    TerritoryHierarchyVO getTerritoryHierarchy() throws WccException;

    /**
     * Gets the role map.
     *
     * @return the role map
     * @throws WccException
     *             the wcc exception
     */
    Map<Long, RoleFlat> getRoleMap() throws WccException;

    /**
     * Find name.
     *
     * @param searchVO
     *            the search VO
     * @return the list
     * @throws WccException
     *             the wcc exception
     */
    List<Name> findName(NameSearchVO searchVO) throws WccException;

    /**
     * Find name by id.
     *
     * @param idName
     *            the id name
     * @return the name
     * @throws WccException
     *             the wcc exception
     */
    Name findNameById(Long idName) throws WccException;

    /**
     * Gets the creation class flat list.
     *
     * @return the creation class flat list
     * @throws WccException
     *             the wcc exception
     */
    List<CreationClassFlat> getCreationClassFlatList() throws WccException;

    /**
     * Gets the Creation Class map.
     *
     * @return the Creation Class map
     * @throws WccException
     *             the wcc exception
     */
    Map<Long, CreationClassFlat> getCreationClassMap() throws WccException;

    /**
     * Gets the Ipi Right Type map.
     *
     * @return the Ipi Right Type map
     * @throws WccException
     *             the wcc exception
     */
    Map<Long, IpiRightTypeFlat> getIpiRightTypeMap() throws WccException;

    /**
     * Gets the ipi right type list.
     *
     * @return the ipi right type list
     * @throws WccException
     *             the wcc exception
     */
    List<IpiRightTypeFlat> getIpiRightTypeList() throws WccException;

    /**
     * Gets the IpiRightType by id.
     *
     * @param id
     * @return the IpiRightType by id
     * @throws WccException
     *             the wcc exception
     */
    IpiRightTypeFlat findIpiRightTypeById(Long id) throws WccException;

    /**
     * Gets the RightType by id.
     *
     * @param id
     * @return the RightType by id
     * @throws WccException
     *             the wcc exception
     */
    RightTypeFlat findRightTypeById(Long id) throws WccException;

    /**
     * Gets the ipi role list.
     *
     * @return the ipi role list
     * @throws WccException
     *             the wcc exception
     */
    List<IpiRoleFlat> getIpiRoleList() throws WccException;

    /**
     * Find role.
     *
     * @return the map
     * @throws WccException
     *             the wcc exception
     */
    Map<Long, IpiRoleFlat> getIpiRoleMap() throws WccException;

    /**
     * Gets the all reference.
     *
     * @return the all reference
     * @throws WccException
     *             the wcc exception
     */
    List<ReferenceFlat> getAllReference() throws WccException;

    /**
     * Gets the all reference type.
     *
     * @return the all reference type
     * @throws WccException
     *             the wcc exception
     */
    List<ReferenceTypeFlat> getAllReferenceType() throws WccException;

    /**
     * Gets the reference type by code.
     *
     * @param code
     *            the code
     * @return the reference type by code
     * @throws WccException
     *             the wcc exception
     */
    ReferenceTypeFlat getReferenceTypeByCode(String code) throws WccException;

    /**
     * Gets the reference type by id.
     *
     * @param code
     *            the code
     * @return the reference type by id
     * @throws WccException
     *             the wcc exception
     */
    ReferenceTypeFlat getReferenceTypeById(Long id) throws WccException;

    /**
     * Find reference items using the parameter code.
     *
     * @param code
     *            the code
     * @return the map
     * @throws WccException
     *             the wcc exception
     */
    Map<Long, ReferenceFlat> getReferenceMapByCode(String code) throws WccException;

    /**
     * Gets the list of CustomValidation objects used to manage validation rules.
     *
     * @return the custom validation list
     * @throws WccException
     *             the wcc exception
     */
    List<CustomValidation> getCustomValidationList() throws WccException;

    /**
     * Gets the list of CustomValidation objects used to manage validation rules, filtered by section code.
     *
     * @param sectionCode
     *            the section code
     * @return the custom validation list by section
     * @throws WccException
     *             the wcc exception
     */
    List<CustomValidation> getCustomValidationListBySection(String sectionCode) throws WccException;

    NameSearchResultVO findNameVO(NameSearchVO searchVO) throws WccException;

    DatabaseInfoVO getDBConnectionInfo() throws WccException;

    TerritoryNameIdentifierFlat findIdentifierFromTerritoryCode(String code) throws WccException;

    List<RightTypeFlat> getRightTypeListByCC(List<String> ccCodeList) throws WccException;

    List<ImportStatusFlat> getImportStatusList() throws WccException;

    List<Territory> getTerritoryByParentTisa(String tisaCode) throws WccException;

}
