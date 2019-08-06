/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Copyright Connection (WCC).
 *
 *
 * @author Fincons
 *
 */

package org.wipo.connect.shared.backend.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.vo.DatabaseInfoVO;
import org.wipo.connect.shared.exchange.business.CommonBusiness;
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
import org.wipo.connect.shared.exchange.restvo.common.CheckClientIdentityRestVO;
import org.wipo.connect.shared.exchange.restvo.common.FindByIdRestVO;
import org.wipo.connect.shared.exchange.restvo.common.FindNameByIdRestVO;
import org.wipo.connect.shared.exchange.restvo.common.FindNameRestVO;
import org.wipo.connect.shared.exchange.restvo.common.GetCustomValidationListBySectionRestVO;
import org.wipo.connect.shared.exchange.restvo.common.GetReferenceByCodeRestVO;
import org.wipo.connect.shared.exchange.restvo.common.RightTypeSearchRestVO;
import org.wipo.connect.shared.exchange.restvo.common.TerritoryWithOrderRestVO;
import org.wipo.connect.shared.exchange.restvo.work.FindRoleByIdRestVO;
import org.wipo.connect.shared.exchange.vo.NameSearchResultVO;
import org.wipo.connect.shared.exchange.vo.NameSearchVO;

/**
 * The Class CommonRestController.
 */
@RestController
@RequestMapping(value = "/common")
public class CommonRestController extends BaseRestController {

    /** The common business. */
    @Autowired
    @Qualifier("commonBusinessImpl")
    private CommonBusiness commonBusiness;

    /**
     * Find by id.
     *
     * @param reqVO
     *            the req VO
     * @return the boolean
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("checkClientIdentity")
    public Boolean findById(@RequestBody CheckClientIdentityRestVO reqVO) throws WccException {
	return commonBusiness.checkClientIdentity(reqVO.getClientCode(), reqVO.getClientKey());
    }

    /**
     * Gets the countries from territory list.
     *
     * @return the countries from territory list
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("getCountriesFromTerritoryList")
    public List<Territory> getCountriesFromTerritoryList(@RequestBody Boolean includeWorld) throws WccException {
	return commonBusiness.getCountriesFromTerritoryList(includeWorld);
    }

    @ResponseBody
    @RequestMapping("getCountriesFromTerritoryMap")
    public Map<String, String> getCountriesFromTerritoryMap() throws WccException {
	return commonBusiness.getCountriesFromTerritoryMap();
    }

    /**
     * Find territory names by id.
     *
     * @param reqVO
     *            the req VO
     * @return the list
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("findTerritoryNamesById")
    public List<Territory> findTerritoryNamesById(@RequestBody FindByIdRestVO reqVO) throws WccException {
	return commonBusiness.findTerritoryNamesById(reqVO.getId());
    }

    // /**
    // * Find ipi right type by right type.
    // *
    // * @param reqVO
    // * the req VO
    // * @return the list
    // * @throws WccException
    // * the wcc exception
    // */
    // @ResponseBody
    // @RequestMapping("findIpiRightTypeByRightType")
    // public List<IpiRightTypeFlat> findIpiRightTypeByRightType(@RequestBody FindByIdRestVO reqVO) throws WccException {
    // return commonBusiness.findIpiRightTypeByRightType(reqVO.getId());
    // }

    /**
     * Gets the right type list.
     *
     * @return the right type list
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("getRightTypeList")
    public List<RightTypeFlat> getRightTypeList() throws WccException {
	List<RightTypeFlat> temp = commonBusiness.getRightTypeList();
	return temp;

    }

    /**
     * Gets the role list.
     *
     * @return the role list
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("getRoleList")
    public List<RoleFlat> getRoleList() throws WccException {
	return commonBusiness.getRoleList();
    }

    /**
     * Gets the role map.
     *
     * @return the role map
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("getRoleMap")
    public List<RoleFlat> getRoleMap() throws WccException {
	return getRoleList();
    }

    /**
     * Find role by id.
     *
     * @param vo
     *            the vo
     * @return the role flat
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("findRoleById")
    RoleFlat findRoleById(@RequestBody FindRoleByIdRestVO vo) throws WccException {
	return commonBusiness.findRoleById(vo.getId());
    }

    /**
     * Find name.
     *
     * @param reqVO
     *            the req VO
     * @return the list
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("findName")
    public List<Name> findName(@RequestBody FindNameRestVO reqVO) throws WccException {

	return commonBusiness.findName(reqVO.getSearchVO());

    }

    /**
     * Find name by id.
     *
     * @param reqVO
     *            the req VO
     * @return the name
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("findNameById")
    public Name findNameById(@RequestBody FindNameByIdRestVO reqVO) throws WccException {

	return commonBusiness.findNameById(reqVO.getIdName());

    }

    /**
     * Gets the creation class flat list.
     *
     * @return the creation class flat list
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("getCreationClassFlatList")
    public List<CreationClassFlat> getCreationClassFlatList() throws WccException {
	return commonBusiness.getCreationClassFlatList();
    }

    /**
     * Gets the Creation Class map.
     *
     * @return the Creation Class map
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("getCreationClassMap")
    public List<CreationClassFlat> getCreationClassMap() throws WccException {
	return getCreationClassFlatList();
    }

    /**
     * Gets the Ipi Right Type map.
     *
     * @return the Ipi Right Type map
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("getIpiRightTypeMap")
    public List<IpiRightTypeFlat> getIpiRightTypeMap() throws WccException {
	return getIpiRightTypeList();
    }

    /**
     * Gets the ipi right type list.
     *
     * @return the ipi right type list
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("getIpiRightTypeList")
    public List<IpiRightTypeFlat> getIpiRightTypeList() throws WccException {
	List<IpiRightTypeFlat> temp = commonBusiness.getIpiRightTypeList();
	return temp;

    }

    /**
     * Find findIpiRightTypeById.
     *
     * @param reqVO
     *            the req VO
     * @return the list
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("findIpiRightTypeById")
    public IpiRightTypeFlat findIpiRightTypeById(@RequestBody Long id) throws WccException {
	return commonBusiness.findIpiRightTypeById(id);
    }

    /**
     * Find findRightTypeById.
     *
     * @param reqVO
     *            the req VO
     * @return the list
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("findRightTypeById")
    public RightTypeFlat findRightTypeById(@RequestBody Long id) throws WccException {
	return commonBusiness.findRightTypeById(id);
    }

    /**
     * Gets the ipi role list.
     *
     * @return the ipi role list
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("getIpiRoleList")
    public List<IpiRoleFlat> getIpiRoleList() throws WccException {

	return commonBusiness.getIpiRoleList();

    }

    /**
     * Gets the ipi role map.
     *
     * @return the ipi role map
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("getIpiRoleMap")
    public List<IpiRoleFlat> getIpiRoleMap() throws WccException {
	return getIpiRoleList();
    }

    /**
     * Gets the territory list.
     *
     * @param vo
     *            the vo
     * @return the territory list
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("getTerritoryList")
    public List<Territory> getTerritoryList(@RequestBody TerritoryWithOrderRestVO vo) throws WccException {

	return commonBusiness.getTerritoryList(vo.getTerrOrderType());

    }

    /**
     * Gets the territory map.
     *
     * @param vo
     *            the vo
     * @return the territory map
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("getTerritoryMap")
    public List<Territory> getTerritoryMap(@RequestBody TerritoryWithOrderRestVO vo) throws WccException {

	return getTerritoryList(vo);

    }

    /**
     * Gets the all identifier.
     *
     * @return the all identifier
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("getAllIdentifier")
    List<IdentifierFlat> getAllIdentifier() throws WccException {
	return commonBusiness.getAllIdentifier();
    }

    /**
     * Gets the identifier by id.
     *
     * @param reqVO
     *            the req VO
     * @return the identifier by id
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("getIdentifierById")
    public IdentifierFlat getIdentifierById(@RequestBody FindByIdRestVO reqVO) throws WccException {
	return commonBusiness.getIdentifierById(reqVO.getId());
    }

    /**
     * Gets the all reference.
     *
     * @return the all reference
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("getAllReference")
    public List<ReferenceFlat> getAllReference() throws WccException {
	return commonBusiness.getAllReference();
    }

    /**
     * Gets the all reference type.
     *
     * @return the all reference type
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("getAllReferenceType")
    public List<ReferenceTypeFlat> getAllReferenceType() throws WccException {
	return commonBusiness.getAllReferenceType();
    }

    @ResponseBody
    @RequestMapping("getReferenceTypeByCode")
    public ReferenceTypeFlat getReferenceTypeByCode(@RequestBody GetReferenceByCodeRestVO reqVO) throws WccException {
	return commonBusiness.getReferenceTypeByCode(reqVO.getCode());
    }

    @ResponseBody
    @RequestMapping("getReferenceTypeById")
    public ReferenceTypeFlat getReferenceTypeById(@RequestBody Long id) throws WccException {
	return commonBusiness.getReferenceTypeById(id);
    }

    /**
     * Gets the reference by code.
     *
     * @param reqVO
     *            the req VO
     * @return the reference by code
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("getReferenceByCode")
    public List<ReferenceFlat> getReferenceByCode(@RequestBody GetReferenceByCodeRestVO reqVO) throws WccException {
	return commonBusiness.getReferenceByCode(reqVO.getCode());
    }

    /**
     * Gets the reference map by code.
     *
     * @param reqVO
     *            the req VO
     * @return the reference map by code
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("getReferenceMapByCode")
    public List<ReferenceFlat> getReferenceMapByCode(@RequestBody GetReferenceByCodeRestVO reqVO) throws WccException {
	return getReferenceByCode(reqVO);
    }

    /**
     * Gets the custom validation list.
     *
     * @return the custom validation list
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("getCustomValidationList")
    public List<CustomValidation> getCustomValidationList() throws WccException {
	return commonBusiness.getCustomValidationList();
    }

    /**
     * Gets the custom validation list.
     *
     * @param vo
     *            the vo
     * @return the custom validation list
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("getCustomValidationListBySection")
    public List<CustomValidation> getCustomValidationList(@RequestBody GetCustomValidationListBySectionRestVO vo) throws WccException {
	return commonBusiness.getCustomValidationListBySection(vo.getSectionCode());
    }

    @ResponseBody
    @RequestMapping("findNameVO")
    public NameSearchResultVO findNameVO(@RequestBody NameSearchVO reqVO) throws WccException {
	return commonBusiness.findNameVO(reqVO);
    }

    @ResponseBody
    @RequestMapping("getDBConnectionInfo")
    public DatabaseInfoVO getDBConnectionInfo() throws WccException {
	return commonBusiness.getDBConnectionInfo();
    }

    @ResponseBody
    @RequestMapping("findIdentifierFromTerritoryCode")
    public TerritoryNameIdentifierFlat findIdentifierFromTerritoryCode(@RequestBody String code) throws WccException {
	return commonBusiness.findIdentifierFromTerritoryCode(code);
    }

    @ResponseBody
    @RequestMapping("getRightTypeListByCC")
    public List<RightTypeFlat> getRightTypeListByCC(@RequestBody RightTypeSearchRestVO vo) throws WccException {
	return commonBusiness.getRightTypeListByCC(vo.getCcCodeList());
    }

    @ResponseBody
    @RequestMapping("getImportStatusList")
    public List<ImportStatusFlat> getImportStatusList() throws WccException {
	return commonBusiness.getImportStatusList();
    }

    @ResponseBody
    @RequestMapping("getTerritoryByParentTisa")
    List<Territory> getTerritoryByParentTisa(@RequestBody String tisaCode) throws WccException {
	return commonBusiness.getTerritoryByParentTisa(tisaCode);
    }

}
