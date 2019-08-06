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
import org.wipo.connect.shared.exchange.business.WorkBusiness;
import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.dto.impl.WorkImport;
import org.wipo.connect.shared.exchange.dto.impl.WorkStatusFlat;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskDetail;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskElaborationResult;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskItem;
import org.wipo.connect.shared.exchange.enumerator.ExportTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.FileFormatEnum;
import org.wipo.connect.shared.exchange.enumerator.MassiveActionsEnum;
import org.wipo.connect.shared.exchange.restvo.common.ExecuteMassiveActionRestVO;
import org.wipo.connect.shared.exchange.restvo.common.FindByIdListRestVO;
import org.wipo.connect.shared.exchange.restvo.work.ConvertTaskDetailRestVO;
import org.wipo.connect.shared.exchange.restvo.work.FindByIdRestVO;
import org.wipo.connect.shared.exchange.restvo.work.FindByMainIdRestVO;
import org.wipo.connect.shared.exchange.restvo.work.FindByWorkListRestVO;
import org.wipo.connect.shared.exchange.restvo.work.FindWorkRestVO;
import org.wipo.connect.shared.exchange.restvo.work.GetWorkTaskElabResultByItemCodeRestVO;
import org.wipo.connect.shared.exchange.restvo.work.InsertNewImportFileRestVO;
import org.wipo.connect.shared.exchange.restvo.work.InsertRestVO;
import org.wipo.connect.shared.exchange.restvo.work.LogicallyDeleteWorkRestVO;
import org.wipo.connect.shared.exchange.restvo.work.StoreTaskItemDetailsRestVO;
import org.wipo.connect.shared.exchange.utils.DTOUtils;
import org.wipo.connect.shared.exchange.utils.importexport.ExportBean;
import org.wipo.connect.shared.exchange.vo.WorkSearchResultVO;
import org.wipo.connect.shared.exchange.vo.WorkSearchVO;

/**
 * The Class WorkBusinessRestClient.
 */
@Service
@Qualifier("workBusinessRestClient")
public class WorkBusinessRestClient implements WorkBusiness {

    /** The Constant PROP_URL. */
    private static final String PROP_URL = "backendUrl";

    /** The Constant PROP_PATH. */
    private static final String PROP_PATH = "restPath";

    /** The Constant CONTROLLER_PATH. */
    private static final String CONTROLLER_PATH = "work";

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
    public Work findById(Long id, String code) throws WccException {
	String endpoint = baseUrl + "findByIdAndCode";

	try {
	    FindByIdRestVO reqVO = new FindByIdRestVO();
	    reqVO.setIdWork(id);
	    reqVO.setCode(code);
	    return restTemplate.postForObject(endpoint, reqVO, Work.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public WorkSearchResultVO findWork(WorkSearchVO searchVO) throws WccException {
	String endpoint = baseUrl + "findWork";

	try {
	    FindWorkRestVO reqVO = new FindWorkRestVO();
	    reqVO.setSearchVO(searchVO);
	    WorkSearchResultVO results = restTemplate.postForObject(endpoint, reqVO, WorkSearchResultVO.class);

	    return results;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public void storeTaskItemDetails(List<WorkTaskItem> itemList, String taskCode, String cmoCode) throws WccException {
	String endpoint = baseUrl + "storeTaskItemDetails";

	try {
	    StoreTaskItemDetailsRestVO reqVO = new StoreTaskItemDetailsRestVO();
	    reqVO.setItemList(itemList);
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
    public List<Work> insert(List<WorkTaskDetail> workTaskDetailList) throws WccException {
	String endpoint = baseUrl + "insertWorkFromTaskDetail";

	try {
	    InsertRestVO reqVO = new InsertRestVO();
	    reqVO.setWorkTaskDetailList(workTaskDetailList);

	    Work[] results = restTemplate.postForObject(endpoint, reqVO, Work[].class);

	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<WorkTaskElaborationResult> getWorkTaskElaborationResultByItemCode(List<String> itemCodeList) throws WccException {
	String endpoint = baseUrl + "getWorkTaskElaborationResultByItemCode";

	try {
	    GetWorkTaskElabResultByItemCodeRestVO reqVO = new GetWorkTaskElabResultByItemCodeRestVO();
	    reqVO.setItemCodeList(itemCodeList);
	    WorkTaskElaborationResult[] results = restTemplate.postForObject(endpoint, reqVO, WorkTaskElaborationResult[].class);

	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}

    }

    @Override
    public Work insert(WorkTaskDetail workTaskDetail) throws WccException {
	String endpoint = baseUrl + "insertOneWorkFromTaskDetail";

	try {
	    InsertRestVO reqVO = new InsertRestVO();
	    reqVO.setWorkTaskDetail(workTaskDetail);

	    Work results = restTemplate.postForObject(endpoint, reqVO, Work.class);

	    return results;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public WorkImport insertNewImportFile(WorkImport workImport) throws WccException {
	String endpoint = baseUrl + "insertNewImportFile";

	try {
	    InsertNewImportFileRestVO reqVO = new InsertNewImportFileRestVO();
	    reqVO.setWorkImport(workImport);
	    return restTemplate.postForObject(endpoint, reqVO, WorkImport.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Work insertOrUpdate(Work work) throws WccException {
	String endpoint = baseUrl + "insertOrUpdate";

	try {
	    InsertRestVO reqVO = new InsertRestVO();
	    reqVO.setWork(work);
	    return restTemplate.postForObject(endpoint, reqVO, Work.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public WorkSearchResultVO findWorkForWs(WorkSearchVO searchVO) throws WccException {
	String endpoint = baseUrl + "findWorkForWs";

	try {
	    FindWorkRestVO reqVO = new FindWorkRestVO();
	    reqVO.setSearchVO(searchVO);
	    WorkSearchResultVO results = restTemplate.postForObject(endpoint, reqVO, WorkSearchResultVO.class);

	    return results;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public void logicallyDeleteWork(Long workId) throws WccException {
	String endpoint = baseUrl + "logicallyDeleteWork";

	try {
	    LogicallyDeleteWorkRestVO reqVO = new LogicallyDeleteWorkRestVO();
	    reqVO.setIdWork(workId);
	    restTemplate.postForObject(endpoint, reqVO, Boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Work convertTaskDetail(WorkTaskDetail workTaskDetail) throws WccException {
	String endpoint = baseUrl + "convertTaskDetail";

	try {
	    ConvertTaskDetailRestVO reqVO = new ConvertTaskDetailRestVO();
	    reqVO.setWorkTaskDetail(workTaskDetail);
	    return restTemplate.postForObject(endpoint, reqVO, Work.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Work findById(Long id) throws WccException {
	String endpoint = baseUrl + "findById";

	try {
	    FindByIdRestVO reqVO = new FindByIdRestVO();
	    reqVO.setIdWork(id);
	    return restTemplate.postForObject(endpoint, reqVO, Work.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<WorkStatusFlat> findAllWorkStatus() throws WccException {
	String endpoint = baseUrl + "findAllWorkStatus";

	try {
	    WorkStatusFlat[] results = restTemplate.getForObject(endpoint, WorkStatusFlat[].class);

	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Map<Long, WorkStatusFlat> findAllWorkStatusMap() throws WccException {
	String endpoint = baseUrl + "findAllWorkStatusMap";

	try {
	    WorkStatusFlat[] results = restTemplate.getForObject(endpoint, WorkStatusFlat[].class);
	    List<WorkStatusFlat> temp = new ArrayList<>(Arrays.asList(results));
	    Map<Long, WorkStatusFlat> workStatusFlat = DTOUtils.listToMapById(temp);
	    return workStatusFlat;
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
    public Work findByMainId(String mainId) throws WccException {
	String endpoint = baseUrl + "findByMainId";

	try {
	    FindByMainIdRestVO reqVO = new FindByMainIdRestVO();
	    reqVO.setMainId(mainId);
	    return restTemplate.postForObject(endpoint, reqVO, Work.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public String getImportCodeByWorkId(Long workId) throws WccException {
	String endpoint = baseUrl + "getImportCodeByWorkId";

	try {
	    return restTemplate.postForObject(endpoint, workId, String.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<WorkImport> findWorkImport() throws WccException {
	String endpoint = baseUrl + "findWorkImport";

	try {
	    WorkImport[] results = restTemplate.getForObject(endpoint, WorkImport[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public ExportBean getExportBean(List<Long> idList, ExportTypeEnum exportType, FileFormatEnum fileFormatEnum) throws WccException {
	String endpoint = baseUrl + "getExportBean";

	ExportBean exportBean = null;
	try {
	    FindByIdListRestVO reqVO = new FindByIdListRestVO();
	    reqVO.getIdList().addAll(idList);
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
    public String createExportFileByWorkList(List<Work> workList, FileFormatEnum fileFormatEnum, String filePath) throws WccException, IOException {
	String endpoint = baseUrl + "createExportFileByWorkList";

	String filepath = null;
	try {
	    FindByWorkListRestVO reqVO = new FindByWorkListRestVO();
	    reqVO.getWorkList().addAll(workList);

	    reqVO.setFileFormatEnum(fileFormatEnum);
	    reqVO.setFilePath(filepath);
	    filepath = restTemplate.postForObject(endpoint, reqVO, String.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
	return filepath;
    }

    @Override
    public ExportBean getFullExportBean(FileFormatEnum fileFormatEnum) throws WccException {
	String endpoint = baseUrl + "getFullExportBean";
	ExportBean exportBean = null;
	try {
	    FindByWorkListRestVO reqVO = new FindByWorkListRestVO();
	    reqVO.setFileFormatEnum(fileFormatEnum);
	    exportBean = restTemplate.postForObject(endpoint, reqVO, ExportBean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
	return exportBean;
    }

}
