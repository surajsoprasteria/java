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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import org.wipo.connect.shared.exchange.business.InterestedPartyBusiness;
import org.wipo.connect.shared.exchange.dto.impl.Affiliation;
import org.wipo.connect.shared.exchange.dto.impl.GroupDTO;
import org.wipo.connect.shared.exchange.dto.impl.GroupDetailDTO;
import org.wipo.connect.shared.exchange.dto.impl.InterestedParty;
import org.wipo.connect.shared.exchange.dto.impl.InterestedPartyStatusFlat;
import org.wipo.connect.shared.exchange.dto.impl.IpImport;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskElaborationResult;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskItem;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskItemDetail;
import org.wipo.connect.shared.exchange.dto.impl.Name;
import org.wipo.connect.shared.exchange.enumerator.ExportTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.FileFormatEnum;
import org.wipo.connect.shared.exchange.enumerator.MassiveActionsEnum;
import org.wipo.connect.shared.exchange.restvo.common.ConvertTaskDetailInIpRestVO;
import org.wipo.connect.shared.exchange.restvo.common.ExecuteMassiveActionRestVO;
import org.wipo.connect.shared.exchange.restvo.common.FindIpByNameRestVO;
import org.wipo.connect.shared.exchange.restvo.interestedParty.ExplodeIpiRightTypeRestVO;
import org.wipo.connect.shared.exchange.restvo.interestedParty.FindByIdRestVO;
import org.wipo.connect.shared.exchange.restvo.interestedParty.FindByIpIdListRestVO;
import org.wipo.connect.shared.exchange.restvo.interestedParty.FindByIpListRestVO;
import org.wipo.connect.shared.exchange.restvo.interestedParty.FindInterestedPartyRestVO;
import org.wipo.connect.shared.exchange.restvo.interestedParty.GetIpTaskElabResultByItemCodeRestVO;
import org.wipo.connect.shared.exchange.restvo.interestedParty.InsertNewImportFileRestVO;
import org.wipo.connect.shared.exchange.restvo.interestedParty.InsertOrUpdateRestVO;
import org.wipo.connect.shared.exchange.restvo.interestedParty.IsTaskCompleteRestVO;
import org.wipo.connect.shared.exchange.restvo.interestedParty.StoreTaskItemDetailsRestVO;
import org.wipo.connect.shared.exchange.utils.importexport.ExportBean;
import org.wipo.connect.shared.exchange.vo.InterestedPartySearchResultVO;
import org.wipo.connect.shared.exchange.vo.InterestedPartySearchVO;
import org.wipo.connect.shared.exchange.vo.NameGroupResultVO;
import org.wipo.connect.shared.exchange.vo.NameSearchVO;

/**
 * The Class InterestedPartyBusinessRestClient.
 */
@Service
@Qualifier("interestedPartyBusinessRestClient")
public class InterestedPartyBusinessRestClient implements InterestedPartyBusiness {

    /** The Constant PROP_URL. */
    private static final String PROP_URL = "backendUrl";

    /** The Constant PROP_PATH. */
    private static final String PROP_PATH = "restPath";

    /** The Constant CONTROLLER_PATH. */
    private static final String CONTROLLER_PATH = "interestedParty";

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
    public InterestedParty findById(Long idInterestedParty, String code, List<String> creationClassCodeList) throws WccException {
	String endpoint = baseUrl + "findById";

	try {
	    FindByIdRestVO reqVO = new FindByIdRestVO();
	    reqVO.setIdInterestedParty(idInterestedParty);
	    reqVO.setCode(code);
	    reqVO.setCreationClassCodeList(creationClassCodeList);
	    return restTemplate.postForObject(endpoint, reqVO, InterestedParty.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public InterestedParty findByMainId(String mainId) throws WccException {
	String endpoint = baseUrl + "findByMainId";

	try {
	    return restTemplate.postForObject(endpoint, mainId, InterestedParty.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public InterestedPartySearchResultVO findInterestedParty(InterestedPartySearchVO searchVO) throws WccException {
	String endpoint = baseUrl + "findInterestedParty";

	try {
	    FindInterestedPartyRestVO reqVO = new FindInterestedPartyRestVO();
	    reqVO.setSearchVO(searchVO);
	    InterestedPartySearchResultVO results = restTemplate.postForObject(endpoint, reqVO, InterestedPartySearchResultVO.class);

	    return results;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public boolean isTaskComplete(String taskCode) throws WccException {
	String endpoint = baseUrl + "isTaskComplete";

	try {
	    IsTaskCompleteRestVO reqVO = new IsTaskCompleteRestVO();
	    reqVO.setTaskCode(taskCode);
	    return restTemplate.postForObject(endpoint, reqVO, boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public void storeTaskItemDetails(List<IpTaskItem> ipTaskItemList, String taskCode, String cmoCode) throws WccException {
	String endpoint = baseUrl + "storeTaskItemDetails";

	try {
	    StoreTaskItemDetailsRestVO reqVO = new StoreTaskItemDetailsRestVO();
	    reqVO.setIpTaskItemList(ipTaskItemList);
	    reqVO.setTaskCode(taskCode);
	    reqVO.setCmoCode(cmoCode);
	    restTemplate.postForObject(endpoint, reqVO, boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}

    }

    @Override
    public InterestedParty insertOrUpdate(InterestedParty ipFromSession) throws WccException {
	String endpoint = baseUrl + "insertOrUpdate";

	try {
	    InsertOrUpdateRestVO reqVO = new InsertOrUpdateRestVO();
	    reqVO.setInterestedParty(ipFromSession);
	    return restTemplate.postForObject(endpoint, reqVO, InterestedParty.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public InterestedPartySearchResultVO findInterestedPartyForWs(InterestedPartySearchVO searchVO) throws WccException {
	String endpoint = baseUrl + "findInterestedPartyForWs";

	try {
	    FindInterestedPartyRestVO reqVO = new FindInterestedPartyRestVO();
	    reqVO.setSearchVO(searchVO);
	    InterestedPartySearchResultVO results = restTemplate.postForObject(endpoint, reqVO, InterestedPartySearchResultVO.class);

	    return results;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public IpImport insertNewImportFile(IpImport ipImport) throws WccException {
	String endpoint = baseUrl + "insertNewImportFile";

	try {
	    InsertNewImportFileRestVO reqVO = new InsertNewImportFileRestVO();
	    reqVO.setpImport(ipImport);
	    return restTemplate.postForObject(endpoint, reqVO, IpImport.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<InterestedPartyStatusFlat> findAllIPStatus() throws WccException {
	String endpoint = baseUrl + "findAllIPStatus";

	try {
	    InterestedPartyStatusFlat[] results = restTemplate.getForObject(endpoint, InterestedPartyStatusFlat[].class);

	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Integer rebuildSolrIndex() throws WccException {
	String endpoint = baseUrl + "rebuildSolrIndex";

	try {
	    Integer result = restTemplate.getForObject(endpoint, Integer.class);
	    return result;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Integer indexQueuedItems() throws WccException {
	String endpoint = baseUrl + "indexQueuedItems";

	try {
	    Integer result = restTemplate.getForObject(endpoint, Integer.class);
	    return result;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public InterestedParty findByIdIp(Long idInterestedParty) throws WccException {
	String endpoint = baseUrl + "findByIdIp";

	try {
	    FindByIdRestVO reqVO = new FindByIdRestVO();
	    reqVO.setIdInterestedParty(idInterestedParty);
	    return restTemplate.postForObject(endpoint, reqVO, InterestedParty.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public void logicallyDeleteIp(Long ipId) throws WccException {
	String endpoint = baseUrl + "logicallyDeleteIp";

	try {
	    FindByIdRestVO reqVO = new FindByIdRestVO();
	    reqVO.setIdInterestedParty(ipId);
	    restTemplate.postForObject(endpoint, reqVO, Boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}

    }

    @Override
    public Integer executeMassiveAction(List<Long> idList, MassiveActionsEnum action) throws WccException {

	String endpoint = baseUrl + "executeMassiveAction";

	try {
	    ExecuteMassiveActionRestVO reqVO = new ExecuteMassiveActionRestVO();
	    reqVO.setIdList(idList);
	    reqVO.setAction(action);
	    return restTemplate.postForObject(endpoint, reqVO, Integer.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}

    }

    @Override
    public Long findIpByName(Long nameId) throws WccException {
	String endpoint = baseUrl + "findIpByName";

	try {
	    FindIpByNameRestVO reqVO = new FindIpByNameRestVO();
	    reqVO.setIdName(nameId);
	    return restTemplate.postForObject(endpoint, reqVO, Long.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public InterestedParty convertTaskDetail(IpTaskItemDetail ipTaskItemDetail) throws WccException {
	String endpoint = baseUrl + "convertTaskDetail";

	try {
	    ConvertTaskDetailInIpRestVO reqVO = new ConvertTaskDetailInIpRestVO();
	    reqVO.setIpTaskItemDetail(ipTaskItemDetail);
	    return restTemplate.postForObject(endpoint, reqVO, InterestedParty.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public NameGroupResultVO findGroups(NameSearchVO searchVO) throws WccException {
	String endpoint = baseUrl + "findGroups";

	try {
	    return restTemplate.postForObject(endpoint, searchVO, NameGroupResultVO.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public GroupDTO findGroupById(Long idGroup) throws WccException {
	String endpoint = baseUrl + "findGroupById";
	try {
	    return restTemplate.postForObject(endpoint, idGroup, GroupDTO.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<GroupDetailDTO> findGroupDetailsByIdGroup(Long idGroup) throws WccException {
	String endpoint = baseUrl + "findGroupDetailsByIdGroup";
	try {
	    GroupDetailDTO[] res = restTemplate.postForObject(endpoint, idGroup, GroupDetailDTO[].class);
	    return Arrays.asList(res);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<IpTaskElaborationResult> getIpTaskElaborationResultByItemCode(List<String> itemCodeList) throws WccException {
	String endpoint = baseUrl + "getIpTaskElaborationResultByItemCode";

	try {
	    GetIpTaskElabResultByItemCodeRestVO reqVO = new GetIpTaskElabResultByItemCodeRestVO();
	    reqVO.setItemCodeList(itemCodeList);
	    IpTaskElaborationResult[] results = restTemplate.postForObject(endpoint, reqVO, IpTaskElaborationResult[].class);

	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}

    }

    @Override
    public List<Affiliation> findInterestedPartyAffiliation(Long idInterestedParty) throws WccException {
	String endpoint = baseUrl + "findInterestedPartyAffiliation";

	try {
	    Affiliation[] results = restTemplate.postForObject(endpoint, idInterestedParty, Affiliation[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<Affiliation> explodeIpiRightType(List<Affiliation> affiliationList, boolean removeAd) throws WccException {
	String endpoint = baseUrl + "explodeIpiRightType";

	try {
	    ExplodeIpiRightTypeRestVO reqVO = new ExplodeIpiRightTypeRestVO();
	    reqVO.setAffiliationList(affiliationList);
	    reqVO.setRemoveAd(removeAd);
	    Affiliation[] res = restTemplate.postForObject(endpoint, reqVO, Affiliation[].class);
	    return Arrays.asList(res);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}

    }

    @Override
    public List<IpImport> findIpImport() throws WccException {
	String endpoint = baseUrl + "findIpImport";

	try {
	    IpImport[] results = restTemplate.getForObject(endpoint, IpImport[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public ExportBean getFullExportBean(FileFormatEnum fileFormatEnum) throws WccException {
	String endpoint = baseUrl + "getFullExportBean";

	ExportBean exportBean = null;
	try {
	    FindByIpIdListRestVO reqVO = new FindByIpIdListRestVO();
	    reqVO.setFileFormatEnum(fileFormatEnum);
	    exportBean = restTemplate.postForObject(endpoint, reqVO, ExportBean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}

	return exportBean;
    }

    @Override
    public ExportBean getExportBean(List<Long> idList, ExportTypeEnum exportType, FileFormatEnum fileFormatEnum) throws WccException {
	String endpoint = baseUrl + "getExportBean";

	ExportBean exportBean = null;
	try {
	    FindByIpIdListRestVO reqVO = new FindByIpIdListRestVO();
	    reqVO.getIpList().addAll(idList);
	    reqVO.setExportType(exportType);
	    reqVO.setFileFormatEnum(fileFormatEnum);
	    exportBean = restTemplate.postForObject(endpoint, reqVO, ExportBean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}

	return exportBean;
    }

    @Override
    public String createExportFileByIpList(List<InterestedParty> ipList, ExportTypeEnum exportType) throws WccException, IOException {
	String endpoint = baseUrl + "createExportFileByIpList";

	String filepath = null;
	try {
	    FindByIpListRestVO reqVO = new FindByIpListRestVO();
	    reqVO.getIpList().addAll(ipList);
	    reqVO.setExportType(exportType);

	    filepath = restTemplate.postForObject(endpoint, reqVO, String.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
	return filepath;
    }

    @Override
    public Name getNameByMainId(String nameMainId) throws WccException {
	String endpoint = baseUrl + "getNameByMainId";
	Name name = null;
	try {
	    name = restTemplate.postForObject(endpoint, nameMainId, Name.class);

	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}

	return name;
    }

}
