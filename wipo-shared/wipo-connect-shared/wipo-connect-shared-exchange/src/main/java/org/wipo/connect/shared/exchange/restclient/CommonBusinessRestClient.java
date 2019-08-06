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

package org.wipo.connect.shared.exchange.restclient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.wipo.connect.common.crypto.CryptoHelper;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.exception.WccExceptionFactory;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.restclient.BasicAuthRestTemplate;
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
import org.wipo.connect.shared.exchange.enumerator.TerritoryOrderTypeEnum;
import org.wipo.connect.shared.exchange.restvo.common.CheckClientIdentityRestVO;
import org.wipo.connect.shared.exchange.restvo.common.FindByIdRestVO;
import org.wipo.connect.shared.exchange.restvo.common.FindNameByIdRestVO;
import org.wipo.connect.shared.exchange.restvo.common.FindNameRestVO;
import org.wipo.connect.shared.exchange.restvo.common.FindRoleByIdRestVO;
import org.wipo.connect.shared.exchange.restvo.common.GetCustomValidationListBySectionRestVO;
import org.wipo.connect.shared.exchange.restvo.common.GetReferenceByCodeRestVO;
import org.wipo.connect.shared.exchange.restvo.common.GetTerritoryListRestVO;
import org.wipo.connect.shared.exchange.restvo.common.RightTypeSearchRestVO;
import org.wipo.connect.shared.exchange.restvo.common.TerritoryWithOrderRestVO;
import org.wipo.connect.shared.exchange.utils.DTOUtils;
import org.wipo.connect.shared.exchange.vo.NameSearchResultVO;
import org.wipo.connect.shared.exchange.vo.NameSearchVO;
import org.wipo.connect.shared.exchange.vo.TerritoryHierarchyVO;

/**
 * The Class CommonBusinessRestClient.
 */
@Service
@Qualifier("commonBusinessRestClient")
public class CommonBusinessRestClient implements CommonBusiness {

    /** The Constant LOGGER. */
    @SuppressWarnings("unused")
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(CommonBusinessRestClient.class);

    /** The Constant PROP_URL. */
    private static final String PROP_URL = "backendUrl";

    /** The Constant PROP_PATH. */
    private static final String PROP_PATH = "restPath";

    /** The Constant CONTROLLER_PATH. */
    private static final String CONTROLLER_PATH = "common";

    /** The base url. */
    private String baseUrl;

    /** The shared remoting properties. */
    @Autowired
    private Properties configProperties;

    private RestTemplate restTemplate;

    @Value("#{configProperties.restWsUser}")
    private String restWsUser;

    @Value("#{configProperties.restWsPass}")
    private String restWsPass;

    @Autowired
    private CryptoHelper cryptoHelper;

    /**
     * Inits the.
     */
    @PostConstruct
    public void init() {
	baseUrl = configProperties.getProperty(PROP_URL) + "/" + configProperties.getProperty(PROP_PATH) + "/" + CONTROLLER_PATH + "/";

	restWsPass = cryptoHelper.decrypt(restWsPass);
	restTemplate = new BasicAuthRestTemplate(restWsUser, restWsPass);
    }

    @Override
    public Boolean checkClientIdentity(String clientCode, String clientKey) throws WccException {
	String endpoint = baseUrl + "checkClientIdentity";

	try {
	    CheckClientIdentityRestVO reqVO = new CheckClientIdentityRestVO();
	    reqVO.setClientCode(clientCode);
	    reqVO.setClientKey(clientKey);
	    return restTemplate.postForObject(endpoint, reqVO, Boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Map<Long, Territory> getTerritoryMap(TerritoryOrderTypeEnum terrOrderType) throws WccException {
	String endpoint = baseUrl + "getTerritoryMap";

	TerritoryWithOrderRestVO fkVO = new TerritoryWithOrderRestVO();
	fkVO.setTerrOrderType(terrOrderType);
	try {
	    Territory[] results = restTemplate.postForObject(endpoint, fkVO, Territory[].class);
	    List<Territory> temp = new ArrayList<>(Arrays.asList(results));
	    Map<Long, Territory> territory = DTOUtils.listToMapById(temp);
	    return territory;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> getCountriesFromTerritoryMap() throws WccException {
	String endpoint = baseUrl + "getCountriesFromTerritoryMap";

	try {
	    Map<String, String> results = restTemplate.getForObject(endpoint, Map.class);
	    return results;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<Territory> findTerritoryNamesById(Long idTerritory) throws WccException {
	String endpoint = baseUrl + "findTerritoryNamesById";

	FindByIdRestVO fkVO = new FindByIdRestVO();
	fkVO.setId(idTerritory);
	try {
	    Territory[] results = restTemplate.postForObject(endpoint, fkVO, Territory[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<Territory> getCountriesFromTerritoryList(Boolean includeWorld) throws WccException {
	String endpoint = baseUrl + "getCountriesFromTerritoryList";

	try {
	    Territory[] results = restTemplate.postForObject(endpoint, includeWorld, Territory[].class);

	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    // @Override
    // public List<IpiRightTypeFlat> findIpiRightTypeByRightType(Long fkRightType) throws WccException {
    // String endpoint = baseUrl + "findIpiRightTypeByRightType";
    //
    // FindByIdRestVO fkVO = new FindByIdRestVO();
    // fkVO.setId(fkRightType);
    // try {
    // IpiRightTypeFlat[] results = restTemplate.postForObject(endpoint, fkVO, IpiRightTypeFlat[].class);
    // return new ArrayList<>(Arrays.asList(results));
    // } catch (HttpServerErrorException e) {
    // throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
    // } catch (Exception e) {
    // throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
    // }
    // }

    @Override
    public List<RightTypeFlat> getRightTypeList() throws WccException {
	String endpoint = baseUrl + "getRightTypeList";

	try {
	    RightTypeFlat[] results = restTemplate.getForObject(endpoint, RightTypeFlat[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<RoleFlat> getRoleList() throws WccException {
	String endpoint = baseUrl + "getRoleList";

	try {
	    RoleFlat[] results = restTemplate.getForObject(endpoint, RoleFlat[].class);

	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public RoleFlat findRoleById(Long id) throws WccException {
	String endpoint = baseUrl + "findRoleById";

	try {
	    FindRoleByIdRestVO reqVO = new FindRoleByIdRestVO();
	    reqVO.setId(id);
	    return restTemplate.postForObject(endpoint, reqVO, RoleFlat.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<Territory> getTerritoryList(TerritoryOrderTypeEnum territoryOrderType) throws WccException {
	String endpoint = baseUrl + "getTerritoryList";

	try {
	    GetTerritoryListRestVO reqVO = new GetTerritoryListRestVO();
	    reqVO.setTerritoryOrderType(territoryOrderType);
	    Territory[] results = restTemplate.postForObject(endpoint, reqVO, Territory[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public TerritoryHierarchyVO getTerritoryHierarchy() throws WccException {
	String endpoint = baseUrl + "getTerritoryHierarchy";

	try {
	    return restTemplate.getForObject(endpoint, TerritoryHierarchyVO.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Map<Long, RoleFlat> getRoleMap() throws WccException {
	String endpoint = baseUrl + "getRoleMap";

	try {
	    RoleFlat[] results = restTemplate.getForObject(endpoint, RoleFlat[].class);
	    List<RoleFlat> temp = new ArrayList<>(Arrays.asList(results));
	    Map<Long, RoleFlat> roleFlat = DTOUtils.listToMapById(temp);
	    return roleFlat;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<Name> findName(NameSearchVO searchVO) throws WccException {
	String endpoint = baseUrl + "findName";
	try {
	    FindNameRestVO reqVO = new FindNameRestVO();
	    reqVO.setSearchVO(searchVO);
	    Name[] results = restTemplate.postForObject(endpoint, reqVO, Name[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Name findNameById(Long idName) throws WccException {
	String endpoint = baseUrl + "findNameById";
	try {
	    FindNameByIdRestVO reqVO = new FindNameByIdRestVO();
	    reqVO.setIdName(idName);
	    return restTemplate.postForObject(endpoint, reqVO, Name.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<CreationClassFlat> getCreationClassFlatList() throws WccException {
	String endpoint = baseUrl + "getCreationClassFlatList";
	try {
	    CreationClassFlat[] results = restTemplate.getForObject(endpoint, CreationClassFlat[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Map<Long, CreationClassFlat> getCreationClassMap() throws WccException {
	String endpoint = baseUrl + "getCreationClassMap";

	try {
	    CreationClassFlat[] results = restTemplate.getForObject(endpoint, CreationClassFlat[].class);
	    List<CreationClassFlat> temp = new ArrayList<>(Arrays.asList(results));
	    Map<Long, CreationClassFlat> map = DTOUtils.listToMapById(temp);
	    return map;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Map<Long, IpiRightTypeFlat> getIpiRightTypeMap() throws WccException {
	String endpoint = baseUrl + "getIpiRightTypeMap";

	try {
	    IpiRightTypeFlat[] results = restTemplate.getForObject(endpoint, IpiRightTypeFlat[].class);
	    List<IpiRightTypeFlat> temp = new ArrayList<>(Arrays.asList(results));
	    Map<Long, IpiRightTypeFlat> map = DTOUtils.listToMapById(temp);
	    return map;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<IpiRightTypeFlat> getIpiRightTypeList() throws WccException {
	String endpoint = baseUrl + "getIpiRightTypeList";
	try {
	    IpiRightTypeFlat[] results = restTemplate.getForObject(endpoint, IpiRightTypeFlat[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public IpiRightTypeFlat findIpiRightTypeById(Long id) throws WccException {
	String endpoint = baseUrl + "findIpiRightTypeById";

	try {
	    IpiRightTypeFlat results = restTemplate.postForObject(endpoint, id, IpiRightTypeFlat.class);
	    return results;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public RightTypeFlat findRightTypeById(Long id) throws WccException {
	String endpoint = baseUrl + "findRightTypeById";

	try {
	    RightTypeFlat results = restTemplate.postForObject(endpoint, id, RightTypeFlat.class);
	    return results;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<IpiRoleFlat> getIpiRoleList() throws WccException {
	String endpoint = baseUrl + "getIpiRoleList";
	try {
	    IpiRoleFlat[] results = restTemplate.getForObject(endpoint, IpiRoleFlat[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Map<Long, IpiRoleFlat> getIpiRoleMap() throws WccException {
	String endpoint = baseUrl + "getIpiRoleMap";

	try {
	    IpiRoleFlat[] results = restTemplate.getForObject(endpoint, IpiRoleFlat[].class);
	    List<IpiRoleFlat> temp = new ArrayList<>(Arrays.asList(results));
	    Map<Long, IpiRoleFlat> roleFlat = DTOUtils.listToMapById(temp);
	    return roleFlat;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<ReferenceFlat> getAllReference() throws WccException {
	String endpoint = baseUrl + "getAllReference";

	try {
	    ReferenceFlat[] results = restTemplate.getForObject(endpoint, ReferenceFlat[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<ReferenceTypeFlat> getAllReferenceType() throws WccException {
	String endpoint = baseUrl + "getAllReferenceType";

	try {
	    ReferenceTypeFlat[] results = restTemplate.getForObject(endpoint, ReferenceTypeFlat[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public ReferenceTypeFlat getReferenceTypeByCode(String code) throws WccException {
	String endpoint = baseUrl + "getReferenceTypeByCode";

	try {
	    GetReferenceByCodeRestVO reqVO = new GetReferenceByCodeRestVO();
	    reqVO.setCode(code);
	    ReferenceTypeFlat result = restTemplate.postForObject(endpoint, reqVO, ReferenceTypeFlat.class);
	    return result;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public ReferenceTypeFlat getReferenceTypeById(Long id) throws WccException {
	String endpoint = baseUrl + "getReferenceTypeById";

	try {
	    ReferenceTypeFlat result = restTemplate.postForObject(endpoint, id, ReferenceTypeFlat.class);
	    return result;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<ReferenceFlat> getReferenceByCode(String code) throws WccException {
	String endpoint = baseUrl + "getReferenceByCode";

	try {
	    GetReferenceByCodeRestVO reqVO = new GetReferenceByCodeRestVO();
	    reqVO.setCode(code);
	    ReferenceFlat[] results = restTemplate.postForObject(endpoint, reqVO, ReferenceFlat[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Map<Long, ReferenceFlat> getReferenceMapByCode(String code) throws WccException {
	String endpoint = baseUrl + "getReferenceMapByCode";

	try {
	    GetReferenceByCodeRestVO reqVO = new GetReferenceByCodeRestVO();
	    reqVO.setCode(code);
	    ReferenceFlat[] results = restTemplate.postForObject(endpoint, reqVO, ReferenceFlat[].class);
	    List<ReferenceFlat> temp = new ArrayList<>(Arrays.asList(results));
	    Map<Long, ReferenceFlat> referenceFlat = DTOUtils.listToMapById(temp);
	    return referenceFlat;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    // ------------------------------------------------------------------------------------------
    // ---------- Identifiers Managment ---------------------------------------------------------
    // ------------------------------------------------------------------------------------------
    @Override
    public List<IdentifierFlat> getAllIdentifier() throws WccException {
	String endpoint = baseUrl + "getAllIdentifier";

	try {
	    IdentifierFlat[] results = restTemplate.getForObject(endpoint, IdentifierFlat[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public IdentifierFlat getIdentifierById(Long idIdentifier) throws WccException {
	String endpoint = baseUrl + "getIdentifierById";

	try {
	    FindByIdRestVO idVO = new FindByIdRestVO();
	    idVO.setId(idIdentifier);
	    IdentifierFlat results = restTemplate.postForObject(endpoint, idVO, IdentifierFlat.class);
	    return (results);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    // ------------------------------------------------------------------------------------------
    // ---------- Custom Validation -------------------------------------------------------------
    // ------------------------------------------------------------------------------------------

    @Override
    public List<CustomValidation> getCustomValidationList() throws WccException {
	String endpoint = baseUrl + "getCustomValidationList";

	try {
	    CustomValidation[] results = restTemplate.getForObject(endpoint, CustomValidation[].class);
	    List<CustomValidation> objOut = new ArrayList<>(Arrays.asList(results));
	    return objOut;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<CustomValidation> getCustomValidationListBySection(String sectionCode) throws WccException {
	String endpoint = baseUrl + "getCustomValidationListBySection";

	GetCustomValidationListBySectionRestVO restVo = new GetCustomValidationListBySectionRestVO();
	restVo.setSectionCode(sectionCode);
	try {
	    CustomValidation[] results = restTemplate.postForObject(endpoint, restVo, CustomValidation[].class);
	    List<CustomValidation> objOut = new ArrayList<>(Arrays.asList(results));
	    return objOut;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public NameSearchResultVO findNameVO(NameSearchVO searchVO) throws WccException {
	String endpoint = baseUrl + "findNameVO";

	try {
	    return restTemplate.postForObject(endpoint, searchVO, NameSearchResultVO.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public DatabaseInfoVO getDBConnectionInfo() throws WccException {
	String endpoint = baseUrl + "getDBConnectionInfo";

	try {
	    return restTemplate.getForObject(endpoint, DatabaseInfoVO.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public TerritoryNameIdentifierFlat findIdentifierFromTerritoryCode(String code) throws WccException {
	String endpoint = baseUrl + "findIdentifierFromTerritoryCode";

	try {
	    return restTemplate.postForObject(endpoint, code, TerritoryNameIdentifierFlat.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<RightTypeFlat> getRightTypeListByCC(List<String> ccCodeList) throws WccException {
	String endpoint = baseUrl + "getRightTypeListByCC";
	RightTypeSearchRestVO vo = new RightTypeSearchRestVO();
	vo.setCcCodeList(ccCodeList);
	try {
	    RightTypeFlat[] results = restTemplate.postForObject(endpoint, vo, RightTypeFlat[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<ImportStatusFlat> getImportStatusList() throws WccException {
	String endpoint = baseUrl + "getImportStatusList";

	try {
	    ImportStatusFlat[] results = restTemplate.getForObject(endpoint, ImportStatusFlat[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<Territory> getTerritoryByParentTisa(String tisaCode) throws WccException {
	String endpoint = baseUrl + "getTerritoryByParentTisa";
	try {
	    Territory[] results = restTemplate.postForObject(endpoint, tisaCode, Territory[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

}
