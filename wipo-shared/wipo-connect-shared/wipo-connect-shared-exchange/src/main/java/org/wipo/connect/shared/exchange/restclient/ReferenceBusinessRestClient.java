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
import org.wipo.connect.common.restclient.BasicAuthRestTemplate;
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
import org.wipo.connect.shared.exchange.utils.DTOUtils;
import org.wipo.connect.shared.exchange.vo.CmoSearchVO;

/**
 * The Class ReferenceBusinessRestClient.
 */
@Service
@Qualifier("referenceBusinessRestClient")
public class ReferenceBusinessRestClient implements ReferenceBusiness {

    /** The Constant PROP_URL. */
    private static final String PROP_URL = "backendUrl";

    /** The Constant PROP_PATH. */
    private static final String PROP_PATH = "restPath";

    /** The Constant CONTROLLER_PATH. */
    private static final String CONTROLLER_PATH = "reference";

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

    // ---------------------------------------------------------------------
    // ---------------- CMO ------------------------------------------------
    // --------------------------------------------------------------------

    @Override
    public Cmo getCmoById(Long idCmo) throws WccException {
	String endpoint = baseUrl + "getCmoById";

	try {
	    FindByIdRestVO idVO = new FindByIdRestVO();
	    idVO.setId(idCmo);
	    Cmo results = restTemplate.postForObject(endpoint, idVO, Cmo.class);
	    return (results);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Cmo insertOrUpdateCmo(Cmo cmo) throws WccException {
	String endpoint = baseUrl + "insertOrUpdateCmo";

	try {
	    InsertOrUpdateCmoRestVO reqVO = new InsertOrUpdateCmoRestVO();
	    reqVO.setDto(cmo);
	    return restTemplate.postForObject(endpoint, reqVO, Cmo.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<Cmo> getCmoList() throws WccException {
	String endpoint = baseUrl + "getCmoList";

	try {
	    Cmo[] results = restTemplate.getForObject(endpoint, Cmo[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<ReferenceFlat> getCmoTypeList() throws WccException {
	String endpoint = baseUrl + "getCmoTypeList";

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
    public Map<Long, ReferenceFlat> getCmoTypeMap() throws WccException {
	String endpoint = baseUrl + "getCmoTypeMap";

	try {
	    ReferenceFlat[] results = restTemplate.getForObject(endpoint, ReferenceFlat[].class);
	    List<ReferenceFlat> temp = new ArrayList<>(Arrays.asList(results));
	    Map<Long, ReferenceFlat> referenceMap = DTOUtils.listToMapById(temp);
	    return referenceMap;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<Cmo> findCmo(CmoSearchVO searchVO) throws WccException {
	String endpoint = baseUrl + "findCmo";
	try {
	    FindCmoRestVO reqVO = new FindCmoRestVO();
	    reqVO.setSearchVO(searchVO);

	    Cmo[] results = restTemplate.postForObject(endpoint, reqVO, Cmo[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Boolean checkCmoCodeUniqueness(String code, Long id) throws WccException {
	String endpoint = baseUrl + "checkCmoCodeUniqueness";
	try {
	    CheckUniquenessCodeRestVO reqVO = new CheckUniquenessCodeRestVO();
	    reqVO.setCode(code);
	    reqVO.setId(id);
	    return restTemplate.postForObject(endpoint, reqVO, Boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    // ---------------------------------------------------------------------
    // ---------------- TERRITORY ------------------------------------------
    // --------------------------------------------------------------------

    @Override
    public List<Territory> getAllTerritoryList() throws WccException {
	String endpoint = baseUrl + "getAllTerritoryList";

	try {
	    Territory[] results = restTemplate.getForObject(endpoint, Territory[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Territory findTerritoryById(Long id) throws WccException {
	String endpoint = baseUrl + "findTerritoryById";
	try {
	    Territory results = restTemplate.postForObject(endpoint, id, Territory.class);
	    return results;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<TerritoryName> findTerritoryNamesByCode(String code) throws WccException {
	String endpoint = baseUrl + "findTerritoryNamesByCode";
	try {
	    TerritoryName[] results = restTemplate.postForObject(endpoint, code, TerritoryName[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public TerritoryName findTerritoryNamesById(Long id) throws WccException {
	String endpoint = baseUrl + "findTerritoryNamesById";
	try {
	    TerritoryName results = restTemplate.postForObject(endpoint, id, TerritoryName.class);
	    return results;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Territory insertOrUpdateTerritory(Territory territory) throws WccException {
	String endpoint = baseUrl + "insertOrUpdateTerritory";

	try {
	    InsertOrUpdateTerritoryRestVO reqVO = new InsertOrUpdateTerritoryRestVO();
	    reqVO.setDto(territory);
	    return restTemplate.postForObject(endpoint, reqVO, Territory.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public TerritoryName insertOrUpdateTerritoryName(TerritoryName territoryName) throws WccException {
	String endpoint = baseUrl + "insertOrUpdateTerritoryName";

	try {
	    InsertOrUpdateTerritoryNameRestVO reqVO = new InsertOrUpdateTerritoryNameRestVO();
	    reqVO.setDto(territoryName);
	    return restTemplate.postForObject(endpoint, reqVO, TerritoryName.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Boolean checkTisnCodeUniqueness(String code, Long id) throws WccException {
	String endpoint = baseUrl + "checkTisnCodeUniqueness";
	try {
	    CheckUniquenessCodeRestVO reqVO = new CheckUniquenessCodeRestVO();
	    reqVO.setCode(code);
	    reqVO.setId(id);
	    return restTemplate.postForObject(endpoint, reqVO, Boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Boolean checkTisaCodeUniqueness(String code, Long id) throws WccException {
	String endpoint = baseUrl + "checkTisaCodeUniqueness";
	try {
	    CheckUniquenessCodeRestVO reqVO = new CheckUniquenessCodeRestVO();
	    reqVO.setCode(code);
	    reqVO.setId(id);
	    return restTemplate.postForObject(endpoint, reqVO, Boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    // ---------------------------------------------------------------------
    // ---------------- CREATION CLASS -------------------------------------
    // --------------------------------------------------------------------

    @Override
    public CreationClassFlat findCreationClassById(Long id) throws WccException {
	String endpoint = baseUrl + "findCreationClassById";
	try {
	    CreationClassFlat results = restTemplate.postForObject(endpoint, id, CreationClassFlat.class);
	    return results;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public CreationClassFlat insertOrUpdateCreationClass(CreationClassFlat creationClass) throws WccException {
	String endpoint = baseUrl + "insertOrUpdateCreationClass";

	try {
	    InsertOrUpdateCreationClassRestVO reqVO = new InsertOrUpdateCreationClassRestVO();
	    reqVO.setDto(creationClass);
	    return restTemplate.postForObject(endpoint, reqVO, CreationClassFlat.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Boolean checkCcCodeUniqueness(String code, Long id) throws WccException {
	String endpoint = baseUrl + "checkCcCodeUniqueness";
	try {
	    CheckUniquenessCodeRestVO reqVO = new CheckUniquenessCodeRestVO();
	    reqVO.setCode(code);
	    reqVO.setId(id);
	    return restTemplate.postForObject(endpoint, reqVO, Boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    // ---------------------------------------------------------------------
    // ---------------- ROLES ----------------------------------------------
    // --------------------------------------------------------------------

    @Override
    public RoleFlat insertOrUpdateRole(RoleFlat role) throws WccException {
	String endpoint = baseUrl + "insertOrUpdateRole";

	try {
	    InsertOrUpdateRoleRestVO reqVO = new InsertOrUpdateRoleRestVO();
	    reqVO.setDto(role);
	    return restTemplate.postForObject(endpoint, reqVO, RoleFlat.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public IpiRoleFlat insertOrUpdateIpiRole(IpiRoleFlat ipi) throws WccException {
	String endpoint = baseUrl + "insertOrUpdateIpiRole";

	try {
	    InsertOrUpdateIpiRoleRestVO reqVO = new InsertOrUpdateIpiRoleRestVO();
	    reqVO.setDto(ipi);
	    return restTemplate.postForObject(endpoint, reqVO, IpiRoleFlat.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public IpiRoleFlat findIpiRoleById(Long id) throws WccException {
	String endpoint = baseUrl + "findIpiRoleById";
	try {
	    IpiRoleFlat results = restTemplate.postForObject(endpoint, id, IpiRoleFlat.class);
	    return results;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Boolean checkRoleCodeUniqueness(String code, Long id) throws WccException {
	String endpoint = baseUrl + "checkRoleCodeUniqueness";
	try {
	    CheckUniquenessCodeRestVO reqVO = new CheckUniquenessCodeRestVO();
	    reqVO.setCode(code);
	    reqVO.setId(id);
	    return restTemplate.postForObject(endpoint, reqVO, Boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Boolean checkIpiRoleCodeUniqueness(String code, Long id) throws WccException {
	String endpoint = baseUrl + "checkIpiRoleCodeUniqueness";
	try {
	    CheckUniquenessCodeRestVO reqVO = new CheckUniquenessCodeRestVO();
	    reqVO.setCode(code);
	    reqVO.setId(id);
	    return restTemplate.postForObject(endpoint, reqVO, Boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    // ---------------------------------------------------------------------
    // ---------------- RIGHT TYPE -----------------------------------------
    // --------------------------------------------------------------------

    @Override
    public RightTypeFlat insertOrUpdateRightType(RightTypeFlat rightType) throws WccException {
	String endpoint = baseUrl + "insertOrUpdateRightType";

	try {
	    InsertOrUpdateRightTypeRestVO reqVO = new InsertOrUpdateRightTypeRestVO();
	    reqVO.setDto(rightType);
	    return restTemplate.postForObject(endpoint, reqVO, RightTypeFlat.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public IpiRightTypeFlat insertOrUpdateIpiRightType(IpiRightTypeFlat irt) throws WccException {
	String endpoint = baseUrl + "insertOrUpdateIpiRightType";

	try {
	    InsertOrUpdateIpiRightTypeRestVO reqVO = new InsertOrUpdateIpiRightTypeRestVO();
	    reqVO.setDto(irt);
	    return restTemplate.postForObject(endpoint, reqVO, IpiRightTypeFlat.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Boolean checkRightTypeCodeUniqueness(String code, Long id) throws WccException {
	String endpoint = baseUrl + "checkRightTypeCodeUniqueness";
	try {
	    CheckUniquenessCodeRestVO reqVO = new CheckUniquenessCodeRestVO();
	    reqVO.setCode(code);
	    reqVO.setId(id);
	    return restTemplate.postForObject(endpoint, reqVO, Boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Boolean checkIpiRightTypeCodeUniqueness(String code, Long id) throws WccException {
	String endpoint = baseUrl + "checkIpiRightTypeCodeUniqueness";
	try {
	    CheckUniquenessCodeRestVO reqVO = new CheckUniquenessCodeRestVO();
	    reqVO.setCode(code);
	    reqVO.setId(id);
	    return restTemplate.postForObject(endpoint, reqVO, Boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    // ---------------------------------------------------------------------
    // ---------------- IDENTIFIER -----------------------------------------
    // --------------------------------------------------------------------

    @Override
    public IdentifierFlat insertOrUpdateIdentifier(IdentifierFlat identifier) throws WccException {
	String endpoint = baseUrl + "insertOrUpdateIdentifier";

	try {
	    InsertOrUpdateIdentifierRestVO reqVO = new InsertOrUpdateIdentifierRestVO();
	    reqVO.setDto(identifier);
	    return restTemplate.postForObject(endpoint, reqVO, IdentifierFlat.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    // ---------------------------------------------------------------------
    // ---------------- REFERENCE ------------------------------------------
    // --------------------------------------------------------------------

    @Override
    public ReferenceFlat insertOrUpdateReference(ReferenceFlat reference) throws WccException {
	String endpoint = baseUrl + "insertOrUpdateReference";

	try {
	    InsertOrUpdateReferenceRestVO reqVO = new InsertOrUpdateReferenceRestVO();
	    reqVO.setDto(reference);
	    ReferenceFlat result = restTemplate.postForObject(endpoint, reqVO, ReferenceFlat.class);
	    return result;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public ReferenceTypeFlat insertOrUpdateReferenceType(ReferenceTypeFlat referenceType) throws WccException {
	String endpoint = baseUrl + "insertOrUpdateReferenceType";

	try {
	    InsertOrUpdateReferenceTypeRestVO reqVO = new InsertOrUpdateReferenceTypeRestVO();
	    reqVO.setDto(referenceType);
	    ReferenceTypeFlat result = restTemplate.postForObject(endpoint, reqVO, ReferenceTypeFlat.class);
	    return result;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Boolean checkReferenceTypeCodeUniqueness(String code, Long id) throws WccException {
	String endpoint = baseUrl + "checkReferenceTypeCodeUniqueness";
	try {
	    CheckUniquenessCodeRestVO reqVO = new CheckUniquenessCodeRestVO();
	    reqVO.setCode(code);
	    reqVO.setId(id);
	    return restTemplate.postForObject(endpoint, reqVO, Boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

}
