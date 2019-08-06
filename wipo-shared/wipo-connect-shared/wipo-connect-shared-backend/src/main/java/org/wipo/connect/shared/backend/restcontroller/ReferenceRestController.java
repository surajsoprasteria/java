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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.shared.exchange.business.CommonBusiness;
import org.wipo.connect.shared.exchange.business.ReferenceBusiness;
import org.wipo.connect.shared.exchange.dto.impl.Cmo;
import org.wipo.connect.shared.exchange.dto.impl.CreationClassFlat;
import org.wipo.connect.shared.exchange.dto.impl.IdentifierFlat;
import org.wipo.connect.shared.exchange.dto.impl.IpiRightTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.IpiRoleFlat;
import org.wipo.connect.shared.exchange.dto.impl.ReferenceFlat;
import org.wipo.connect.shared.exchange.dto.impl.ReferenceTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.RightTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.RoleFlat;
import org.wipo.connect.shared.exchange.dto.impl.Territory;
import org.wipo.connect.shared.exchange.dto.impl.TerritoryName;
import org.wipo.connect.shared.exchange.restvo.common.CheckUniquenessCodeRestVO;
import org.wipo.connect.shared.exchange.restvo.common.FindByIdRestVO;
import org.wipo.connect.shared.exchange.restvo.common.FindCmoRestVO;
import org.wipo.connect.shared.exchange.restvo.common.InsertOrUpdateCmoRestVO;
import org.wipo.connect.shared.exchange.restvo.common.InsertOrUpdateCreationClassRestVO;
import org.wipo.connect.shared.exchange.restvo.common.InsertOrUpdateIdentifierRestVO;
import org.wipo.connect.shared.exchange.restvo.common.InsertOrUpdateIpiRightTypeRestVO;
import org.wipo.connect.shared.exchange.restvo.common.InsertOrUpdateIpiRoleRestVO;
import org.wipo.connect.shared.exchange.restvo.common.InsertOrUpdateReferenceRestVO;
import org.wipo.connect.shared.exchange.restvo.common.InsertOrUpdateReferenceTypeRestVO;
import org.wipo.connect.shared.exchange.restvo.common.InsertOrUpdateRightTypeRestVO;
import org.wipo.connect.shared.exchange.restvo.common.InsertOrUpdateRoleRestVO;
import org.wipo.connect.shared.exchange.restvo.common.InsertOrUpdateTerritoryNameRestVO;
import org.wipo.connect.shared.exchange.restvo.common.InsertOrUpdateTerritoryRestVO;

/**
 * The Class CommonRestController.
 */
@RestController
@RequestMapping(value = "/reference")
public class ReferenceRestController extends BaseRestController {

    /** The common business. */
    @Autowired
    @Qualifier("commonBusinessImpl")
    private CommonBusiness commonBusiness;

    @Autowired
    @Qualifier("referenceBusinessImpl")
    private ReferenceBusiness referenceBusiness;

    // ---------------------------------------------------------------------
    // ---------------- CMO ------------------------------------------------
    // --------------------------------------------------------------------

    @ResponseBody
    @RequestMapping("getCmoById")
    public Cmo getCmoById(@RequestBody FindByIdRestVO reqVO) throws WccException {
	return referenceBusiness.getCmoById(reqVO.getId());
    }

    @ResponseBody
    @RequestMapping("getCmoList")
    public List<Cmo> getCmoList() throws WccException {
	return referenceBusiness.getCmoList();
    }

    @ResponseBody
    @RequestMapping("getCmoTypeList")
    public List<ReferenceFlat> getCmoTypeList() throws WccException {
	return referenceBusiness.getCmoTypeList();
    }

    @ResponseBody
    @RequestMapping("getCmoTypeMap")
    public List<ReferenceFlat> getCmoTypeMap() throws WccException {
	return getCmoTypeList();
    }

    @ResponseBody
    @RequestMapping("insertOrUpdateCmo")
    public Cmo insertOrUpdateCmo(@RequestBody InsertOrUpdateCmoRestVO reqVO) throws WccException {
	return referenceBusiness.insertOrUpdateCmo(reqVO.getDto());
    }

    @ResponseBody
    @RequestMapping("findCmo")
    public List<Cmo> findCmo(@RequestBody FindCmoRestVO vo) throws WccException {
	return referenceBusiness.findCmo(vo.getSearchVO());
    }

    @ResponseBody
    @RequestMapping("checkCmoCodeUniqueness")
    public Boolean checkCmoCodeUniqueness(@RequestBody CheckUniquenessCodeRestVO reqVO) throws WccException {
	return referenceBusiness.checkCmoCodeUniqueness(reqVO.getCode(), reqVO.getId());
    }

    // ---------------------------------------------------------------------
    // ---------------- TERRITORY ------------------------------------------
    // --------------------------------------------------------------------

    @ResponseBody
    @RequestMapping("getAllTerritoryList")
    public List<Territory> getAllTerritoryList() throws WccException {
	return referenceBusiness.getAllTerritoryList();
    }

    @ResponseBody
    @RequestMapping("findTerritoryById")
    public Territory findTerritoryById(@RequestBody Long id) throws WccException {
	return referenceBusiness.findTerritoryById(id);
    }

    @ResponseBody
    @RequestMapping("findTerritoryNamesByCode")
    public List<TerritoryName> findTerritoryNamesByCode(@RequestBody String code) throws WccException {
	return referenceBusiness.findTerritoryNamesByCode(code);
    }

    @ResponseBody
    @RequestMapping("findTerritoryNamesById")
    public TerritoryName findTerritoryNamesById(@RequestBody Long id) throws WccException {
	return referenceBusiness.findTerritoryNamesById(id);
    }

    @ResponseBody
    @RequestMapping("insertOrUpdateTerritory")
    public Territory insertOrUpdateTerritory(@RequestBody InsertOrUpdateTerritoryRestVO reqVO) throws WccException {
	return referenceBusiness.insertOrUpdateTerritory(reqVO.getDto());
    }

    @ResponseBody
    @RequestMapping("insertOrUpdateTerritoryName")
    public TerritoryName insertOrUpdateTerritoryName(@RequestBody InsertOrUpdateTerritoryNameRestVO reqVO) throws WccException {
	return referenceBusiness.insertOrUpdateTerritoryName(reqVO.getDto());
    }

    @ResponseBody
    @RequestMapping("checkTisnCodeUniqueness")
    public Boolean checkTisnCodeUniqueness(@RequestBody CheckUniquenessCodeRestVO reqVO) throws WccException {
	return referenceBusiness.checkTisnCodeUniqueness(reqVO.getCode(), reqVO.getId());
    }

    @ResponseBody
    @RequestMapping("checkTisaCodeUniqueness")
    public Boolean checkTisaCodeUniqueness(@RequestBody CheckUniquenessCodeRestVO reqVO) throws WccException {
	return referenceBusiness.checkTisaCodeUniqueness(reqVO.getCode(), reqVO.getId());
    }

    // ---------------------------------------------------------------------
    // ---------------- CREATION CLASS -------------------------------------
    // --------------------------------------------------------------------

    @ResponseBody
    @RequestMapping("findCreationClassById")
    public CreationClassFlat findCreationClassById(@RequestBody Long id) throws WccException {
	return referenceBusiness.findCreationClassById(id);
    }

    @ResponseBody
    @RequestMapping("insertOrUpdateCreationClass")
    public CreationClassFlat insertOrUpdateCreationClass(@RequestBody InsertOrUpdateCreationClassRestVO reqVO) throws WccException {
	return referenceBusiness.insertOrUpdateCreationClass(reqVO.getDto());
    }

    @ResponseBody
    @RequestMapping("checkCcCodeUniqueness")
    public Boolean checkCcCodeUniqueness(@RequestBody CheckUniquenessCodeRestVO reqVO) throws WccException {
	return referenceBusiness.checkCcCodeUniqueness(reqVO.getCode(), reqVO.getId());
    }

    // ---------------------------------------------------------------------
    // ---------------- ROLE -----------------------------------------------
    // --------------------------------------------------------------------

    @ResponseBody
    @RequestMapping("insertOrUpdateRole")
    public RoleFlat insertOrUpdateRole(@RequestBody InsertOrUpdateRoleRestVO reqVO) throws WccException {
	return referenceBusiness.insertOrUpdateRole(reqVO.getDto());
    }

    @ResponseBody
    @RequestMapping("insertOrUpdateIpiRole")
    public IpiRoleFlat insertOrUpdateIpiRole(@RequestBody InsertOrUpdateIpiRoleRestVO reqVO) throws WccException {
	return referenceBusiness.insertOrUpdateIpiRole(reqVO.getDto());
    }

    @ResponseBody
    @RequestMapping("findIpiRoleById")
    IpiRoleFlat findIpiRoleById(@RequestBody Long id) throws WccException {
	return referenceBusiness.findIpiRoleById(id);
    }

    @ResponseBody
    @RequestMapping("checkRoleCodeUniqueness")
    public Boolean checkRoleCodeUniqueness(@RequestBody CheckUniquenessCodeRestVO reqVO) throws WccException {
	return referenceBusiness.checkRoleCodeUniqueness(reqVO.getCode(), reqVO.getId());
    }

    @ResponseBody
    @RequestMapping("checkIpiRoleCodeUniqueness")
    public Boolean checkIpiRoleCodeUniqueness(@RequestBody CheckUniquenessCodeRestVO reqVO) throws WccException {
	return referenceBusiness.checkIpiRoleCodeUniqueness(reqVO.getCode(), reqVO.getId());
    }

    // ---------------------------------------------------------------------
    // ---------------- RIGHT TYPE -----------------------------------------
    // --------------------------------------------------------------------

    @ResponseBody
    @RequestMapping("insertOrUpdateRightType")
    public RightTypeFlat insertOrUpdateRightType(@RequestBody InsertOrUpdateRightTypeRestVO reqVO) throws WccException {
	return referenceBusiness.insertOrUpdateRightType(reqVO.getDto());
    }

    @ResponseBody
    @RequestMapping("insertOrUpdateIpiRightType")
    public IpiRightTypeFlat insertOrUpdateIpiRightType(@RequestBody InsertOrUpdateIpiRightTypeRestVO reqVO) throws WccException {
	return referenceBusiness.insertOrUpdateIpiRightType(reqVO.getDto());
    }

    @ResponseBody
    @RequestMapping("checkRightTypeCodeUniqueness")
    public Boolean checkRightTypeCodeUniqueness(@RequestBody CheckUniquenessCodeRestVO reqVO) throws WccException {
	return referenceBusiness.checkRightTypeCodeUniqueness(reqVO.getCode(), reqVO.getId());
    }

    @ResponseBody
    @RequestMapping("checkIpiRightTypeCodeUniqueness")
    public Boolean checkIpiRightTypeCodeUniqueness(@RequestBody CheckUniquenessCodeRestVO reqVO) throws WccException {
	return referenceBusiness.checkIpiRightTypeCodeUniqueness(reqVO.getCode(), reqVO.getId());
    }

    // ---------------------------------------------------------------------
    // ---------------- IDENTIFIER -----------------------------------------
    // --------------------------------------------------------------------

    @ResponseBody
    @RequestMapping("insertOrUpdateIdentifier")
    public IdentifierFlat insertOrUpdateLocalIdentifier(@RequestBody InsertOrUpdateIdentifierRestVO reqVO) throws WccException {
	return referenceBusiness.insertOrUpdateIdentifier(reqVO.getDto());
    }

    // ---------------------------------------------------------------------
    // ---------------- REFERENCE ------------------------------------------
    // --------------------------------------------------------------------

    @ResponseBody
    @RequestMapping("insertOrUpdateReference")
    public ReferenceFlat insertOrUpdateReference(@RequestBody InsertOrUpdateReferenceRestVO reqVO) throws WccException {
	return referenceBusiness.insertOrUpdateReference(reqVO.getDto());
    }

    @ResponseBody
    @RequestMapping("insertOrUpdateReferenceType")
    public ReferenceTypeFlat insertOrUpdateReferenceType(@RequestBody InsertOrUpdateReferenceTypeRestVO reqVO) throws WccException {
	return referenceBusiness.insertOrUpdateReferenceType(reqVO.getDto());
    }

    @ResponseBody
    @RequestMapping("checkReferenceTypeCodeUniqueness")
    public Boolean checkReferenceTypeCodeUniqueness(@RequestBody CheckUniquenessCodeRestVO reqVO) throws WccException {
	return referenceBusiness.checkReferenceTypeCodeUniqueness(reqVO.getCode(), reqVO.getId());
    }

}
