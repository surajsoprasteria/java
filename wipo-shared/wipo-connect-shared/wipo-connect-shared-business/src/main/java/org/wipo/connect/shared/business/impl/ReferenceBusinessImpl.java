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

package org.wipo.connect.shared.business.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.exception.DateOverlapException;
import org.wipo.connect.common.exception.DuplicatedItemException;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.common.utils.ConversionUtils;
import org.wipo.connect.common.utils.WccUtils;
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
import org.wipo.connect.shared.exchange.dto.impl.Translation;
import org.wipo.connect.shared.exchange.enumerator.IpiRightTypeEnum;
import org.wipo.connect.shared.exchange.utils.DTOUtils;
import org.wipo.connect.shared.exchange.vo.CmoSearchVO;
import org.wipo.connect.shared.persistence.dao.CmoDAO;
import org.wipo.connect.shared.persistence.dao.CommonDAO;
import org.wipo.connect.shared.persistence.dao.CreationClassDAO;
import org.wipo.connect.shared.persistence.dao.IdentifierFlatDAO;
import org.wipo.connect.shared.persistence.dao.ReferenceDAO;
import org.wipo.connect.shared.persistence.dao.RightTypeDAO;
import org.wipo.connect.shared.persistence.dao.RoleDAO;
import org.wipo.connect.shared.persistence.dao.TerritoryDAO;

import net.bull.javamelody.MonitoredWithSpring;

/**
 * The Class CommonBusinessImpl.
 */
@Service
@MonitoredWithSpring
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class ReferenceBusinessImpl implements ReferenceBusiness {

    @Autowired
    private CommonDAO commonDAO;

    @Autowired
    private CmoDAO cmoDAO;

    @Autowired
    private TerritoryDAO territoryDAO;

    @Autowired
    private CreationClassDAO creationClassDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private RightTypeDAO rightTypeDAO;

    @Autowired
    private IdentifierFlatDAO identifierFlatDAO;

    @Autowired
    private ReferenceDAO referenceDAO;

    // ------------------------------------------------------------------------------------------
    // ---------- Cmo Managment -----------------------------------------------------------------
    // ------------------------------------------------------------------------------------------

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public Cmo getCmoById(Long idCmo) throws WccException {
	return cmoDAO.find(idCmo);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<ReferenceFlat> getCmoTypeList() throws WccException {
	return cmoDAO.findCmoType();
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public Map<Long, ReferenceFlat> getCmoTypeMap() throws WccException {
	Map<Long, ReferenceFlat> keyMap;
	List<ReferenceFlat> list = getCmoTypeList();
	try {
	    keyMap = DTOUtils.listToMapById(list);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return keyMap;
    }

    @Override
    public List<Cmo> getCmoList() {
	return cmoDAO.findAll();
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public Cmo insertOrUpdateCmo(Cmo cmo) throws WccException {
	try {

	    if (cmoDAO.exsistCmoCode(cmo.getCode(), cmo.getId())) {
		throw new DuplicatedItemException("Duplicate CMO: " + cmo.getCode());
	    }

	    if (cmo.getId() == null) {
		cmo.setIsDataSource(true);
		cmoDAO.insertCmo(cmo);
	    } else {
		cmoDAO.updateCmo(cmo);
	    }
	} catch (WccException e) {
	    throw e;
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return cmo;
    }

    @Override
    @ExecutionTimeTrackable
    public List<Cmo> findCmo(CmoSearchVO searchVO) throws WccException {
	List<Cmo> list;
	try {
	    Map<String, Object> searchMap = WccUtils.objToMap(searchVO);
	    list = this.cmoDAO.findCmo(searchMap);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return list;
    }

    @Override
    public Boolean checkCmoCodeUniqueness(String code, Long id) {
	Boolean result = cmoDAO.exsistCmoCode(code, id);
	return !result;
    }

    // ----------------------------------------------------------------------------------------
    // ---------- Territory -------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------

    @Override
    @ExecutionTimeTrackable
    public List<Territory> getAllTerritoryList() {
	return territoryDAO.findAllTerritory();
    }

    @Override
    @ExecutionTimeTrackable
    public Territory findTerritoryById(Long id) throws WccException {
	Territory t = territoryDAO.findTerritoryById(id);
	t.setTerritoryNameList(territoryDAO.findTerritoryNamesByCode(t.getCode()));
	return t;
    }

    @Override
    @ExecutionTimeTrackable
    public List<TerritoryName> findTerritoryNamesByCode(String code) throws WccException {
	return this.territoryDAO.findTerritoryNamesByCode(code);
    }

    @Override
    @ExecutionTimeTrackable
    public TerritoryName findTerritoryNamesById(Long id) throws WccException {
	return this.territoryDAO.findTerritoryNameById(id);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public Territory insertOrUpdateTerritory(Territory territory) throws WccException {
	try {

	    if (territory.getTerritoryNameList().isEmpty()) {
		throw new WccException(WccExceptionCodeEnum.VALIDATION_ERROR, "Add at least a Name before saving the Territory");
	    }

	    if (territory.getId() == null) {
		territoryDAO.insertTerritory(territory);
	    } else {
		territoryDAO.updateTerritory(territory);
	    }

	    for (TerritoryName tn : territory.getTerritoryNameList()) {
		tn.setFkTerritory(territory.getId());
		insertOrUpdateTerritoryName(tn);
	    }

	    // commonDAO.evictMainCache();
	    territory = findTerritoryById(territory.getId());

	    checkTerritoryNameDateOverlaps(territory.getTerritoryNameList());

	} catch (WccException e) {
	    throw e;
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

	return territory;
    }

    @Override
    public Boolean checkTisnCodeUniqueness(String code, Long id) {
	Boolean result = territoryDAO.exsistTisnCode(code, id);
	return !result;
    }

    @Override
    public Boolean checkTisaCodeUniqueness(String code, Long id) {
	Boolean result = territoryDAO.exsistTisaCode(code, id);
	return !result;
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public TerritoryName insertOrUpdateTerritoryName(TerritoryName territoryName) throws WccException {
	try {

	    if (territoryDAO.exsistTisaCode(territoryName.getTisa(), territoryName.getId())) {
		throw new DuplicatedItemException("Duplicate TerrytoryName: " + territoryName.getTisa());
	    }

	    // add or update Translation label
	    Long trFk = insertOrUpdateTranslation(territoryName.getFkName(), territoryName.getName());
	    territoryName.setFkName(trFk);

	    if (territoryName.getId() == null) {
		territoryDAO.insertTerritoryName(territoryName);
	    } else {
		territoryDAO.updateTerritoryName(territoryName);
	    }

	} catch (WccException e) {
	    throw e;
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return territoryName;
    }

    private void checkTerritoryNameDateOverlaps(List<TerritoryName> territoryNameList) throws WccException {

	try {
	    DateFormat sdf = new SimpleDateFormat(ConversionUtils.DATE_STAMP);

	    Date minDate = sdf.parse("00010101");
	    Date maxDate = sdf.parse("99991231");

	    for (int i = 0; i < territoryNameList.size(); i++) {
		for (int y = 0; y < territoryNameList.size(); y++) {
		    if (i == y) {
			continue;
		    }
		    TerritoryName t1 = territoryNameList.get(i);
		    TerritoryName t2 = territoryNameList.get(y);

		    Date s1 = ObjectUtils.defaultIfNull(t1.getStartDate(), minDate);
		    Date e1 = ObjectUtils.defaultIfNull(t1.getEndDate(), maxDate);
		    Date s2 = ObjectUtils.defaultIfNull(t2.getStartDate(), minDate);
		    Date e2 = ObjectUtils.defaultIfNull(t2.getEndDate(), maxDate);

		    boolean overlapStart = s1.compareTo(s2) >= 0 && s1.compareTo(e2) < 0;
		    boolean overlapEnd = e1.compareTo(s2) > 0 && e1.compareTo(e2) <= 0;

		    if (overlapStart || overlapEnd) {
			throw new DateOverlapException("Territory " + t1.getTisa() + " and " + t2.getTisa() + " period overlaps");
		    }

		}
	    }
	} catch (WccException e) {
	    throw e;
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

    }

    // ------------------------------------------------------------------------------------
    // ---------- Creation Class ----------------------------------------------------------
    // ------------------------------------------------------------------------------------

    @Override
    @ExecutionTimeTrackable
    public CreationClassFlat findCreationClassById(Long id) throws WccException {
	return this.creationClassDAO.findCreationClassById(id);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public CreationClassFlat insertOrUpdateCreationClass(CreationClassFlat creationClass) throws WccException {
	try {

	    if (creationClassDAO.exsistCode(creationClass.getCode(), creationClass.getId())) {
		throw new DuplicatedItemException("Duplicate CreationClass: " + creationClass.getCode());
	    }

	    // add or update Translation label
	    Long trFk1 = insertOrUpdateTranslation(creationClass.getFkName(), creationClass.getName());
	    creationClass.setFkName(trFk1);

	    Long trFk2 = insertOrUpdateTranslation(creationClass.getFkDescription(), creationClass.getDescription());
	    creationClass.setFkDescription(trFk2);

	    if (creationClass.getId() == null) {
		creationClassDAO.insertCreationClass(creationClass);

		// TODO new CreationClass linked to IpiRightType ALL
		IpiRightTypeFlat irt = rightTypeDAO.findIpiRightTypeByCode(IpiRightTypeEnum.ALL.name());
		rightTypeDAO.insertIpiRightTypeCreationClass(irt.getId(), creationClass.getId());
	    } else {
		creationClassDAO.updateCreationClass(creationClass);
	    }
	} catch (WccException e) {
	    throw e;
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return creationClass;
    }

    @Override
    public Boolean checkCcCodeUniqueness(String code, Long id) {
	Boolean result = creationClassDAO.exsistCode(code, id);
	return !result;
    }

    // ------------------------------------------------------------------------------------
    // ---------- Roles -------------------------------------------------------------------
    // ------------------------------------------------------------------------------------

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public RoleFlat insertOrUpdateRole(RoleFlat role) throws WccException {

	try {
	    if (roleDAO.exsistWorkCode(role.getCode(), role.getId())) {
		throw new DuplicatedItemException("Duplicate Work Role: " + role.getCode());
	    }

	    // add or update Translation label
	    Long trFk1 = insertOrUpdateTranslation(role.getFkName(), role.getName());
	    role.setFkName(trFk1);

	    Long trFk2 = insertOrUpdateTranslation(role.getFkDescription(), role.getDescription());
	    role.setFkDescription(trFk2);

	    if (role.getId() == null) {
		roleDAO.insertRole(role);
	    } else {
		roleDAO.updateRole(role);
	    }

	} catch (WccException e) {
	    throw e;
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

	return role;
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public IpiRoleFlat insertOrUpdateIpiRole(IpiRoleFlat ipi) throws WccException {

	try {
	    if (roleDAO.exsistIpiCode(ipi.getCode(), ipi.getId())) {
		throw new DuplicatedItemException("Duplicate Ipi Role: " + ipi.getCode());
	    }

	    // add or update Translation label
	    Long trFk1 = insertOrUpdateTranslation(ipi.getFkName(), ipi.getName());
	    ipi.setFkName(trFk1);

	    Long trFk2 = insertOrUpdateTranslation(ipi.getFkDescription(), ipi.getDescription());
	    ipi.setFkDescription(trFk2);

	    if (ipi.getId() == null) {
		roleDAO.insertIpiRole(ipi);

		for (Long fkCreationClass : ipi.getFkCcList()) {
		    roleDAO.insertIpiCreationClass(ipi.getIdIpiRole(), fkCreationClass);
		}

	    } else {
		roleDAO.updateIpiRole(ipi);
	    }

	} catch (WccException e) {
	    throw e;
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

	return ipi;
    }

    @Override
    @ExecutionTimeTrackable
    public IpiRoleFlat findIpiRoleById(Long id) throws WccException {
	return roleDAO.findIpiRoleById(id);
    }

    @Override
    public Boolean checkRoleCodeUniqueness(String code, Long id) {
	Boolean result = roleDAO.exsistWorkCode(code, id);
	return !result;
    }

    @Override
    public Boolean checkIpiRoleCodeUniqueness(String code, Long id) {
	Boolean result = roleDAO.exsistIpiCode(code, id);
	return !result;
    }

    // ------------------------------------------------------------------------------------
    // ---------- Right Type --------------------------------------------------------------
    // ------------------------------------------------------------------------------------

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public RightTypeFlat insertOrUpdateRightType(RightTypeFlat rightType) throws WccException {

	try {
	    if (rightTypeDAO.exsistRightTypeCode(rightType.getCode(), rightType.getId())) {
		throw new DuplicatedItemException("Duplicate Right Type: " + rightType.getCode());
	    }

	    // add or update Translation label
	    Long trFk1 = insertOrUpdateTranslation(rightType.getFkName(), rightType.getName());
	    rightType.setFkName(trFk1);

	    Long trFk2 = insertOrUpdateTranslation(rightType.getFkDescription(), rightType.getDescription());
	    rightType.setFkDescription(trFk2);

	    if (rightType.getId() == null) {
		rightTypeDAO.insertRightType(rightType);

		for (Long fkCreationClass : rightType.getFkCcList()) {
		    rightTypeDAO.insertRightTypeCreationClass(rightType.getId(), fkCreationClass);
		}

	    } else {
		rightTypeDAO.updateRightType(rightType);
		;
	    }

	} catch (WccException e) {
	    throw e;
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

	return rightType;
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public IpiRightTypeFlat insertOrUpdateIpiRightType(IpiRightTypeFlat irt) throws WccException {

	try {
	    if (rightTypeDAO.exsistIpiRightTypeCode(irt.getCode(), irt.getId())) {
		throw new DuplicatedItemException("Duplicate Ipi Right Type: " + irt.getCode());
	    }

	    // add or update Translation label
	    Long trFk1 = insertOrUpdateTranslation(irt.getFkName(), irt.getName());
	    irt.setFkName(trFk1);

	    Long trFk2 = insertOrUpdateTranslation(irt.getFkDescription(), irt.getDescription());
	    irt.setFkDescription(trFk2);

	    if (irt.getId() == null) {
		rightTypeDAO.insertIpiRightType(irt);

		for (Long fkCreationClass : irt.getFkCcList()) {
		    rightTypeDAO.insertIpiRightTypeCreationClass(irt.getId(), fkCreationClass);
		}

	    } else {
		rightTypeDAO.updateIpiRightType(irt);
		;
	    }
	} catch (WccException e) {
	    throw e;
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

	return irt;
    }

    @Override
    public Boolean checkRightTypeCodeUniqueness(String code, Long id) {
	Boolean result = rightTypeDAO.exsistRightTypeCode(code, id);
	return !result;
    }

    @Override
    public Boolean checkIpiRightTypeCodeUniqueness(String code, Long id) {
	Boolean result = rightTypeDAO.exsistIpiRightTypeCode(code, id);
	return !result;
    }

    // ------------------------------------------------------------------------------------
    // ---------- Identifier --------------------------------------------------------------
    // ------------------------------------------------------------------------------------

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public IdentifierFlat insertOrUpdateIdentifier(IdentifierFlat identifier) throws WccException {
	try {

	    if (identifierFlatDAO.exsistIdentifierCode(identifier.getCode(), identifier.getId())) {
		throw new DuplicatedItemException("Duplicate identifier: " + identifier.getCode());
	    }

	    // add or update Translation label
	    Long trFk1 = insertOrUpdateTranslation(identifier.getFkName(), identifier.getName());
	    identifier.setFkName(trFk1);

	    if (identifier.getId() == null) {
		identifierFlatDAO.insertIdentifier(identifier);

		for (Long fkCreationClass : identifier.getFkCcList()) {
		    identifierFlatDAO.insertIdentifierCreationClass(identifier.getId(), fkCreationClass);
		}
	    } else {
		identifierFlatDAO.updateIdentifier(identifier);
	    }
	} catch (WccException e) {
	    throw e;
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return identifier;
    }

    // ------------------------------------------------------------------------------------
    // ---------- Reference --------------------------------------------------------------
    // ------------------------------------------------------------------------------------

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public ReferenceFlat insertOrUpdateReference(ReferenceFlat reference) throws WccException {
	try {

	    if (referenceDAO.existReferenceCode(reference.getRefCode(), reference.getCode(), reference.getId())) {
		throw new DuplicatedItemException("Duplicate reference: " + reference.getCode());
	    }

	    // add new row in Translation Table
	    Long trFk1 = insertOrUpdateTranslation(reference.getFkName(), reference.getName());
	    reference.setFkName(trFk1);

	    Long trFk2 = insertOrUpdateTranslation(reference.getFkDescription(), reference.getDescription());
	    reference.setFkDescription(trFk2);

	    if (reference.getId() == null) {
		referenceDAO.insertReference(reference);
	    } else {
		referenceDAO.updateReference(reference);
	    }
	} catch (WccException e) {
	    throw e;
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return reference;
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public ReferenceTypeFlat insertOrUpdateReferenceType(ReferenceTypeFlat referenceType) throws WccException {
	try {

	    if (referenceDAO.exsistReferenceTypeCode(referenceType.getCode(), referenceType.getId())) {
		throw new DuplicatedItemException("Duplicate reference type: " + referenceType.getCode());
	    }

	    // add new row in Translation Table
	    Long trFk1 = insertOrUpdateTranslation(referenceType.getFkName(), referenceType.getName());
	    referenceType.setFkName(trFk1);

	    Long trFk2 = insertOrUpdateTranslation(referenceType.getFkDescription(), referenceType.getDescription());
	    referenceType.setFkDescription(trFk2);

	    if (referenceType.getId() == null) {
		referenceDAO.insertReferenceType(referenceType);
	    } else {
		referenceDAO.updateReferenceType(referenceType);
	    }
	} catch (WccException e) {
	    throw e;
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return referenceType;
    }

    @Override
    public Boolean checkReferenceTypeCodeUniqueness(String code, Long id) {
	Boolean result = referenceDAO.exsistReferenceTypeCode(code, id);
	return !result;
    }

    // ----------------------------------------------------------------------------------------
    // ---------- Translation -----------------------------------------------------------------
    // ----------------------------------------------------------------------------------------

    private Long insertOrUpdateTranslation(Long id, String label) {

	// if TranslationLabel not exist and label is not null then insert
	Translation tran = new Translation();
	tran.setDefaultValue(label);
	if (id == null) {
	    if (label != null && !label.equalsIgnoreCase("")) {
		commonDAO.insertTranslationLabel(tran);
		return tran.getIdTranslation();
	    }
	    return null;
	} else {
	    tran.setIdTranslation(id);
	    if (label == null || label.equalsIgnoreCase("")) {
		tran.setDefaultValue("");
	    }
	    commonDAO.updateTranslationLabel(tran);
	    return id;
	}
    }

    @SuppressWarnings("unused")
    private Long deleteTranslation(Long id) {
	commonDAO.deleteTranslationLabel(id);
	return id;
    }

}
