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

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.dto.impl.WorkImport;
import org.wipo.connect.shared.exchange.dto.impl.WorkStatusFlat;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskDetail;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskElaborationResult;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskItem;
import org.wipo.connect.shared.exchange.enumerator.ExportTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.FileFormatEnum;
import org.wipo.connect.shared.exchange.enumerator.MassiveActionsEnum;
import org.wipo.connect.shared.exchange.utils.importexport.ExportBean;
import org.wipo.connect.shared.exchange.vo.WorkSearchResultVO;
import org.wipo.connect.shared.exchange.vo.WorkSearchVO;

/**
 * The WorkBusiness interface provides business methods orchestrating the functions made available by the layer DAO.
 *
 * @author fumagalli
 */
public interface WorkBusiness {

    /**
     * Load a Work detail from shared.
     *
     * @param id
     *            the id
     * @param code
     *            the code
     * @return Work
     * @throws WccException
     *             the wcc exception
     */
    Work findById(Long id, String code) throws WccException;

    /**
     * Load a work list from shared.
     *
     * @param searchVO
     *            the search vo
     * @return List<Work>
     * @throws WccException
     *             the wcc exception
     */
    WorkSearchResultVO findWork(WorkSearchVO searchVO) throws WccException;

    /**
     * Find work for webservices (Use parameter ws-search-limit.work to set max number of results)
     *
     * @param searchVO
     *            the search vo
     * @return the work search result vo
     * @throws WccException
     *             the wcc exception
     */
    WorkSearchResultVO findWorkForWs(WorkSearchVO searchVO) throws WccException;

    /**
     * Store work entity to be registered internationally to task table.
     *
     * @param itemList
     *            the item list
     * @param taskCode
     *            the task code
     * @throws WccException
     *             the wcc exception
     */
    void storeTaskItemDetails(List<WorkTaskItem> itemList, String taskCode, String cmoCode) throws WccException;

    /**
     * Insert.
     *
     * @param workTaskDetailList
     *            the work task detail list
     * @return the list
     * @throws WccException
     *             the wcc exception
     */
    List<Work> insert(List<WorkTaskDetail> workTaskDetailList) throws WccException;

    /**
     * Insert.
     *
     * @param workTaskDetail
     *            the work task detail
     * @return the work
     * @throws WccException
     *             the wcc exception
     */
    Work insert(WorkTaskDetail workTaskDetail) throws WccException;

    /**
     * Gets the work task elaboration result by item code.
     *
     * @param itemCodeList
     *            the item code list
     * @return the work task elaboration result by item code
     * @throws WccException
     *             the wcc exception
     */
    List<WorkTaskElaborationResult> getWorkTaskElaborationResultByItemCode(List<String> itemCodeList) throws WccException;

    /**
     * Insert new import file.
     *
     * @param workImport
     *            the work import
     * @return the work import
     * @throws WccException
     *             the wcc exception
     */
    WorkImport insertNewImportFile(WorkImport workImport) throws WccException;

    /**
     * Insert.
     *
     * @param work
     *            the work
     * @return the work
     * @throws WccException
     *             the wcc exception
     */
    Work insertOrUpdate(Work work) throws WccException;

    /**
     * Logically delete of work (set work status to delete).
     *
     * @param workId
     *            the work id
     * @throws WccException
     *             the wcc exception
     */
    void logicallyDeleteWork(Long workId) throws WccException;

    /**
     * Convert task detail.
     *
     * @param workTaskDetail
     *            the work task detail
     * @return the work
     * @throws WccException
     *             the wcc exception
     */
    Work convertTaskDetail(WorkTaskDetail workTaskDetail) throws WccException;

    /**
     * Find by id.
     *
     * @param id
     *            the id
     * @return the work
     * @throws WccException
     *             the wcc exception
     */
    Work findById(Long id) throws WccException;

    /**
     * Rebuild solr index.
     *
     * @return the integer
     * @throws WccException
     *             the wcc exception
     */
    Integer rebuildSolrIndex() throws WccException;

    /**
     * Index queued items.
     *
     * @return the integer
     * @throws WccException
     *             the wcc exception
     */
    Integer indexQueuedItems() throws WccException;

    /**
     * Find all work status.
     *
     * @return the list
     * @throws WccException
     *             the wcc exception
     */
    List<WorkStatusFlat> findAllWorkStatus() throws WccException;

    /**
     * Find all work status map.
     *
     * @return the map
     * @throws WccException
     *             the wcc exception
     */
    Map<Long, WorkStatusFlat> findAllWorkStatusMap() throws WccException;

    /**
     * Execute massive action.
     *
     * @param idList
     *            the id list
     * @param action
     *            the action
     * @return the integer
     * @throws WccException
     *             the wcc exception
     */
    Integer executeMassiveAction(List<Long> idList, MassiveActionsEnum action) throws WccException;

    Work findByMainId(String mainId) throws WccException;

    String getImportCodeByWorkId(Long workId) throws WccException;

    List<WorkImport> findWorkImport() throws WccException;

    /**
     * 
     * @param fileFormatEnum
     * @return
     * @throws WccException
     */
    ExportBean getFullExportBean(FileFormatEnum fileFormatEnum) throws WccException;

    /**
     * Get export bean to download
     * 
     * @param idList
     * @param exportType
     * @param fileFormatEnum
     * @return
     * @throws WccException
     */
    ExportBean getExportBean(List<Long> idList, ExportTypeEnum exportType, FileFormatEnum fileFormatEnum) throws WccException;

    /**
     * Creates the export file by work list.
     * 
     * @param workList
     * @param fileFormatEnum
     * @param filePath
     * @return
     * @throws WccException
     * @throws IOException
     */
    String createExportFileByWorkList(List<Work> workList, FileFormatEnum fileFormatEnum, String filePath) throws WccException, IOException;
}
