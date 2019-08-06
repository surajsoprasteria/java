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

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.querypagination.PageInfo;
import org.wipo.connect.common.utils.ConstantUtils;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.common.vo.DatabaseInfoVO;
import org.wipo.connect.shared.exchange.business.CommonBusiness;
import org.wipo.connect.shared.exchange.dto.impl.ClientInfo;
import org.wipo.connect.shared.exchange.dto.impl.CreationClassFlat;
import org.wipo.connect.shared.exchange.dto.impl.CustomValidation;
import org.wipo.connect.shared.exchange.dto.impl.IdentifierFlat;
import org.wipo.connect.shared.exchange.dto.impl.ImportStatusFlat;
import org.wipo.connect.shared.exchange.dto.impl.IpiRightTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.IpiRoleFlat;
import org.wipo.connect.shared.exchange.dto.impl.Name;
import org.wipo.connect.shared.exchange.dto.impl.NameVO;
import org.wipo.connect.shared.exchange.dto.impl.ReferenceFlat;
import org.wipo.connect.shared.exchange.dto.impl.ReferenceTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.RightTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.RoleFlat;
import org.wipo.connect.shared.exchange.dto.impl.Territory;
import org.wipo.connect.shared.exchange.dto.impl.TerritoryNameIdentifierFlat;
import org.wipo.connect.shared.exchange.enumerator.TerritoryOrderTypeEnum;
import org.wipo.connect.shared.exchange.utils.DTOUtils;
import org.wipo.connect.shared.exchange.vo.NameSearchResultVO;
import org.wipo.connect.shared.exchange.vo.NameSearchVO;
import org.wipo.connect.shared.exchange.vo.TerritoryHierarchyVO;
import org.wipo.connect.shared.persistence.dao.ClientInfoDAO;
import org.wipo.connect.shared.persistence.dao.CommonDAO;
import org.wipo.connect.shared.persistence.dao.CreationClassDAO;
import org.wipo.connect.shared.persistence.dao.IdentifierFlatDAO;
import org.wipo.connect.shared.persistence.dao.NameDAO;
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
public class CommonBusinessImpl implements CommonBusiness {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(CommonBusinessImpl.class);

    /** The client info DAO. */
    @Autowired
    private ClientInfoDAO clientInfoDAO;

    /** The identifier flat dao. */
    @Autowired
    private IdentifierFlatDAO identifierFlatDAO;

    @Autowired
    private TerritoryDAO territoryDAO;

    @Autowired
    private CommonDAO commonDAO;

    /** The name dao. */
    @Autowired
    private NameDAO nameDAO;

    /** The role dao. */
    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private RightTypeDAO rightTypeDAO;

    @Autowired
    private CreationClassDAO creationClassDAO;

    @Autowired
    private ReferenceDAO referenceDAO;

    @Autowired
    private DataSource dataSource;

    @Override
    public Boolean checkClientIdentity(String clientCode, String clientKey) throws WccException {

	boolean res = false;

	try {

	    ClientInfo client = clientInfoDAO.findByCode(clientCode);

	    res = client != null && StringUtils.equalsIgnoreCase(client.getCmo().getCode(), clientCode) && StringUtils.equals(client.getClientKey(), clientKey);

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

	return res;

    }

    @Override
    public List<Territory> getCountriesFromTerritoryList(Boolean includeWorld) {
	if (includeWorld) {
	    return territoryDAO.findCountriesFromTerritoryWithWorld();
	} else {
	    return territoryDAO.findCountriesFromTerritory();
	}
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public TerritoryNameIdentifierFlat findIdentifierFromTerritoryCode(String code) throws WccException {
	return this.territoryDAO.findIdentifierFromTerritoryCode(code);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<Territory> findTerritoryNamesById(Long idTerritory) throws WccException {
	return this.commonDAO.findTerritoryNamesById(idTerritory);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public Map<Long, Territory> getTerritoryMap(TerritoryOrderTypeEnum terrOrderType) throws WccException {
	List<Territory> territories;
	Map<Long, Territory> territoryMap;
	try {
	    territories = getTerritoryList(terrOrderType);
	    territoryMap = DTOUtils.listToMapById(territories);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return territoryMap;
    }

    @Override
    public Map<String, String> getCountriesFromTerritoryMap() throws WccException {
	List<Territory> territoryList = this.commonDAO.findAllCountriesFromTerritory();
	Map<String, String> territoryMap = new HashMap<>();
	for (Territory item : territoryList) {
	    territoryMap.put(item.getCode(), item.getTisa());
	}
	return territoryMap;
    }

    @Override
    @ExecutionTimeTrackable
    public List<RoleFlat> getRoleList() throws WccException {
	return roleDAO.findAllRole();
    }

    @Override
    public RoleFlat findRoleById(Long id) throws WccException {
	return this.roleDAO.findRoleById(id);
    }

    @Override
    @ExecutionTimeTrackable
    public List<Territory> getTerritoryList(TerritoryOrderTypeEnum territoryOrderType) throws WccException {
	return this.commonDAO.findAllTerritory(territoryOrderType);
    }

    @Override
    @ExecutionTimeTrackable
    public TerritoryHierarchyVO getTerritoryHierarchy() throws WccException {
	return commonDAO.getTerritoryHierarchy(ConstantUtils.WORLD_TISA_CODE);
    }

    @Override
    @ExecutionTimeTrackable
    public Map<Long, RoleFlat> getRoleMap() throws WccException {
	List<RoleFlat> list;
	Map<Long, RoleFlat> map;
	try {
	    list = getRoleList();
	    map = DTOUtils.listToMapById(list);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return map;
    }

    // @Override
    // @Loggable(level = "TRACE")
    // @ExecutionTimeTrackable
    // public List<IpiRightTypeFlat> findIpiRightTypeByRightType(Long fkRightType) throws WccException {
    // return this.rightTypeDAO.findIpiRightTypeByRightType(fkRightType);
    // }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<RightTypeFlat> getRightTypeList() throws WccException {
	return this.rightTypeDAO.findAllRightType();
    }

    @Override
    @ExecutionTimeTrackable
    public List<Name> findName(NameSearchVO searchVO) throws WccException {
	List<Name> list;
	try {
	    Map<String, Object> searchMap = WccUtils.objToMap(searchVO);
	    list = this.nameDAO.findName(searchMap);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return list;
    }

    // ------------------------------------------------------------------------------------------
    // ---------- Identifiers Managment ---------------------------------------------------------
    // ------------------------------------------------------------------------------------------
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<IdentifierFlat> getAllIdentifier() throws WccException {
	List<IdentifierFlat> identifiers;
	try {
	    identifiers = identifierFlatDAO.findAll();
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return identifiers;
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public IdentifierFlat getIdentifierById(Long idIdentifier) throws WccException {
	return identifierFlatDAO.find(idIdentifier);
    }

    @Override
    public Name findNameById(Long idName) {
	return this.nameDAO.find(idName);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<CreationClassFlat> getCreationClassFlatList() throws WccException {
	return this.creationClassDAO.findAllCreationClass();
    }

    @Override
    @ExecutionTimeTrackable
    public Map<Long, CreationClassFlat> getCreationClassMap() throws WccException {
	Map<Long, CreationClassFlat> map;
	try {
	    List<CreationClassFlat> list = getCreationClassFlatList();
	    map = DTOUtils.listToMapById(list);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return map;
    }

    @Override
    @ExecutionTimeTrackable
    public Map<Long, IpiRightTypeFlat> getIpiRightTypeMap() throws WccException {
	Map<Long, IpiRightTypeFlat> map;
	try {
	    List<IpiRightTypeFlat> list = getIpiRightTypeList();
	    map = DTOUtils.listToMapById(list);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return map;
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<IpiRightTypeFlat> getIpiRightTypeList() throws WccException {
	return this.rightTypeDAO.findAllIpiRightType();
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public IpiRightTypeFlat findIpiRightTypeById(Long id) throws WccException {
	return this.rightTypeDAO.findIpiRightTypeById(id);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public RightTypeFlat findRightTypeById(Long id) throws WccException {
	return this.rightTypeDAO.findRightTypeById(id);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<IpiRoleFlat> getIpiRoleList() throws WccException {
	return this.commonDAO.findAllIpiRole();
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public Map<Long, IpiRoleFlat> getIpiRoleMap() throws WccException {
	List<IpiRoleFlat> list;
	Map<Long, IpiRoleFlat> map;
	try {
	    list = getIpiRoleList();
	    map = DTOUtils.listToMapById(list);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return map;
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public ReferenceTypeFlat getReferenceTypeByCode(String code) throws WccException {
	return referenceDAO.findReferenceTypeByCode(code);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public ReferenceTypeFlat getReferenceTypeById(Long id) throws WccException {
	return referenceDAO.findReferenceTypeById(id);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<ReferenceFlat> getReferenceByCode(String code) throws WccException {
	return this.referenceDAO.findReferenceByCode(code);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<ReferenceFlat> getAllReference() throws WccException {
	return this.referenceDAO.findAllReference();
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<ReferenceTypeFlat> getAllReferenceType() throws WccException {
	return this.referenceDAO.findAllReferenceType();
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public Map<Long, ReferenceFlat> getReferenceMapByCode(String code) throws WccException {
	List<ReferenceFlat> refType;
	Map<Long, ReferenceFlat> licenseTypeMap;
	try {
	    refType = this.referenceDAO.findReferenceByCode(code);
	    licenseTypeMap = DTOUtils.listToMapById(refType);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return licenseTypeMap;
    }

    // ------------------------------------------------------------------------------------------
    // ---------- Custom Validation -------------------------------------------------------------
    // ------------------------------------------------------------------------------------------

    @Override
    public List<CustomValidation> getCustomValidationList() throws WccException {
	// return customValidationDAO.findAll();
	return null;
    }

    @Override
    public List<CustomValidation> getCustomValidationListBySection(String sectionCode) throws WccException {
	// return customValidationDAO.findBySection(sectionCode);
	return null;
    }

    @Override
    @ExecutionTimeTrackable
    public NameSearchResultVO findNameVO(NameSearchVO searchVO) throws WccException {
	if (searchVO.getPageInfo() == null) {
	    searchVO.setPageInfo(new PageInfo(ConstantUtils.DEFAULT_OFFSET, ConstantUtils.DEFAULT_LIMIT));
	}

	try {
	    Map<String, Object> searchMap = WccUtils.objToMap(searchVO);

	    List<NameVO> nameList = nameDAO.findNameVO(searchMap, searchVO.getPageInfo().getStart(), searchVO.getPageInfo().getLength());
	    Integer totResults = nameDAO.getCountFindNameVO(searchMap);
	    Integer draw = (searchVO.getPageInfo() != null) ? searchVO.getPageInfo().getDraw() : 0;

	    return new NameSearchResultVO(nameList, draw, totResults);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    @Override
    @Loggable(level = "TRACE")
    public DatabaseInfoVO getDBConnectionInfo() throws WccException {
	try {
	    return new DatabaseInfoVO(dataSource.getConnection());
	} catch (SQLException e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    @Override
    public List<RightTypeFlat> getRightTypeListByCC(List<String> ccCodeList) {
	return commonDAO.findRightTypeListByCC(ccCodeList);
    }

    @Override
    public List<ImportStatusFlat> getImportStatusList() throws WccException {
	return commonDAO.findAllImportStatus();
    }

    @Override
    @ExecutionTimeTrackable
    public List<Territory> getTerritoryByParentTisa(String tisaCode) throws WccException {
	return commonDAO.getTerritoryByParentTisa(tisaCode);
    }

}
