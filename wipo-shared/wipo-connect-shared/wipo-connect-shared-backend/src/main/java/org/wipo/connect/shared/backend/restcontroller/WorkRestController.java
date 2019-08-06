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

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.shared.exchange.business.WorkBusiness;
import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.dto.impl.WorkImport;
import org.wipo.connect.shared.exchange.dto.impl.WorkStatusFlat;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskElaborationResult;
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
import org.wipo.connect.shared.exchange.utils.importexport.ExportBean;
import org.wipo.connect.shared.exchange.vo.WorkSearchResultVO;

/**
 * The Class WorkRestController.
 */
@RestController
@RequestMapping(value = "/work")
public class WorkRestController extends BaseRestController {

    /** The work business impl. */
    @Autowired
    @Qualifier("workBusinessImpl")
    WorkBusiness workBusiness;

    /**
     * Find by id and code.
     *
     * @param reqVO
     *            the req VO
     * @return the work
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("findByIdAndCode")
    public Work findByIdAndCode(@RequestBody FindByIdRestVO reqVO) throws WccException {

	return workBusiness.findById(reqVO.getIdWork(), reqVO.getCode());

    }

    /**
     * Find by id.
     *
     * @param reqVO
     *            the req VO
     * @return the work
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("findById")
    public Work findById(@RequestBody FindByIdRestVO reqVO) throws WccException {

	return workBusiness.findById(reqVO.getIdWork());

    }

    /**
     * Logically delete work.
     *
     * @param reqVO
     *            the req VO
     * @return the boolean
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("logicallyDeleteWork")
    public Boolean logicallyDeleteWork(@RequestBody LogicallyDeleteWorkRestVO reqVO) throws WccException {
	workBusiness.logicallyDeleteWork(reqVO.getIdWork());
	return true;
    }

    /**
     * Find work.
     *
     * @param reqVO
     *            the req VO
     * @return the work search result VO
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("findWork")
    public WorkSearchResultVO findWork(@RequestBody FindWorkRestVO reqVO) throws WccException {

	return workBusiness.findWork(reqVO.getSearchVO());

    }

    /**
     * Find work for ws.
     *
     * @param reqVO
     *            the req VO
     * @return the work search result VO
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("findWorkForWs")
    public WorkSearchResultVO findWorkForWs(@RequestBody FindWorkRestVO reqVO) throws WccException {

	return workBusiness.findWorkForWs(reqVO.getSearchVO());

    }

    /**
     * Store task item details.
     *
     * @param reqVO
     *            the req VO
     * @return true, if successful
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("storeTaskItemDetails")
    public boolean storeTaskItemDetails(@RequestBody StoreTaskItemDetailsRestVO reqVO) throws WccException {
	workBusiness.storeTaskItemDetails(reqVO.getItemList(), reqVO.getTaskCode(), reqVO.getCmoCode());
	return true;
    }

    /**
     * Insert work from task detail.
     *
     * @param reqVO
     *            the req VO
     * @return the list
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("insertWorkFromTaskDetail")
    public List<Work> insertWorkFromTaskDetail(@RequestBody InsertRestVO reqVO) throws WccException {
	return workBusiness.insert(reqVO.getWorkTaskDetailList());
    }

    /**
     * Insert one work from task detail.
     *
     * @param reqVO
     *            the req VO
     * @return the work
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("insertOneWorkFromTaskDetail")
    public Work insertOneWorkFromTaskDetail(@RequestBody InsertRestVO reqVO) throws WccException {
	return workBusiness.insert(reqVO.getWorkTaskDetail());
    }

    /**
     * Insert new import file.
     *
     * @param reqVO
     *            the req VO
     * @return the work import
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("insertNewImportFile")
    public WorkImport insertNewImportFile(@RequestBody InsertNewImportFileRestVO reqVO) throws WccException {
	return workBusiness.insertNewImportFile(reqVO.getWorkImport());
    }

    @ResponseBody
    @RequestMapping("insertOrUpdate")
    public Work insertOrUpdate(@RequestBody InsertRestVO reqVO) throws WccException {
	return workBusiness.insertOrUpdate(reqVO.getWork());
    }

    /**
     * Convert task detail.
     *
     * @param reqVO
     *            the req VO
     * @return the work
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("convertTaskDetail")
    public Work convertTaskDetail(@RequestBody ConvertTaskDetailRestVO reqVO) throws WccException {

	return workBusiness.convertTaskDetail(reqVO.getWorkTaskDetail());

    }

    /**
     * Gets the work task elaboration result by item code.
     *
     * @param reqVO
     *            the req VO
     * @return the work task elaboration result by item code
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("getWorkTaskElaborationResultByItemCode")
    public List<WorkTaskElaborationResult> getWorkTaskElaborationResultByItemCode(@RequestBody GetWorkTaskElabResultByItemCodeRestVO reqVO) throws WccException {
	return workBusiness.getWorkTaskElaborationResultByItemCode(reqVO.getItemCodeList());
    }

    /**
     * Rebuild solr index.
     *
     * @return the integer
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("rebuildSolrIndex")
    public Integer rebuildSolrIndex() throws WccException {
	return workBusiness.rebuildSolrIndex();
    }

    /**
     * Index queued items.
     *
     * @return the integer
     * @throws WccException
     *             the wcc exception
     */
    @RequestMapping("indexQueuedItems")
    public Integer indexQueuedItems() throws WccException {
	return workBusiness.indexQueuedItems();
    }

    /**
     * Find all work status.
     *
     * @return the list
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("findAllWorkStatus")
    public List<WorkStatusFlat> findAllWorkStatus() throws WccException {
	return workBusiness.findAllWorkStatus();
    }

    /**
     * Find all work status map.
     *
     * @return the list
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("findAllWorkStatusMap")
    public List<WorkStatusFlat> findAllWorkStatusMap() throws WccException {
	return findAllWorkStatus();
    }

    /**
     * Execute massive action.
     *
     * @param reqVO
     *            the req VO
     * @return the integer
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("executeMassiveAction")
    public Integer executeMassiveAction(@RequestBody ExecuteMassiveActionRestVO reqVO) throws WccException {
	return workBusiness.executeMassiveAction(reqVO.getIdList(), reqVO.getAction());
    }

    /**
     * Find by id.
     *
     * @param reqVO
     *            the req VO
     * @return the work
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("findByMainId")
    public Work findByMainId(@RequestBody FindByMainIdRestVO reqVO) throws WccException {
	return workBusiness.findByMainId(reqVO.getMainId());
    }

    @ResponseBody
    @RequestMapping("getImportCodeByWorkId")
    public String getImportCodeByWorkId(@RequestBody Long workId) throws WccException {
	return workBusiness.getImportCodeByWorkId(workId);
    }

    @ResponseBody
    @RequestMapping("findWorkImport")
    public List<WorkImport> findWorkImport() throws WccException {
	return workBusiness.findWorkImport();
    }

    @ResponseBody
    @RequestMapping("createExportFileByWorkList")
    public String createExportFileByWorkList(@RequestBody FindByWorkListRestVO reqVO) throws WccException, IOException {

	return workBusiness.createExportFileByWorkList(reqVO.getWorkList(), reqVO.getFileFormatEnum(), reqVO.getFilePath());

    }

    @ResponseBody
    @RequestMapping("getExportBean")
    public ExportBean getExportBean(@RequestBody FindByIdListRestVO reqVO) throws WccException, IOException {

	return workBusiness.getExportBean(reqVO.getIdList(), reqVO.getExportType(), reqVO.getFileFormatEnum());

    }

    @ResponseBody
    @RequestMapping("getFullExportBean")
    public ExportBean getFullExportBean(@RequestBody FindByWorkListRestVO reqVO) throws WccException, IOException {

	return workBusiness.getFullExportBean(reqVO.getFileFormatEnum());

    }

}
