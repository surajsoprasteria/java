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
import org.wipo.connect.shared.exchange.business.InterestedPartyBusiness;
import org.wipo.connect.shared.exchange.dto.impl.Affiliation;
import org.wipo.connect.shared.exchange.dto.impl.GroupDTO;
import org.wipo.connect.shared.exchange.dto.impl.GroupDetailDTO;
import org.wipo.connect.shared.exchange.dto.impl.InterestedParty;
import org.wipo.connect.shared.exchange.dto.impl.InterestedPartyStatusFlat;
import org.wipo.connect.shared.exchange.dto.impl.IpImport;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskElaborationResult;
import org.wipo.connect.shared.exchange.dto.impl.Name;
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
import org.wipo.connect.shared.exchange.vo.NameGroupResultVO;
import org.wipo.connect.shared.exchange.vo.NameSearchVO;

/**
 * The Class InterestedPartyRestController.
 */
@RestController
@RequestMapping(value = "/interestedParty")
public class InterestedPartyRestController extends BaseRestController {

    /** The interested party business. */
    @Autowired
    @Qualifier("interestedPartyBusinessImpl")
    InterestedPartyBusiness interestedPartyBusiness;

    @ResponseBody
    @RequestMapping("findById")
    public InterestedParty findById(@RequestBody FindByIdRestVO reqVO) throws WccException {
	return interestedPartyBusiness.findById(reqVO.getIdInterestedParty(), reqVO.getCode(), reqVO.getCreationClassCodeList());
    }

    @ResponseBody
    @RequestMapping("findByMainId")
    public InterestedParty findByMainId(@RequestBody String mainId) throws WccException {
	return interestedPartyBusiness.findByMainId(mainId);
    }

    @ResponseBody
    @RequestMapping("findByIdIp")
    public InterestedParty findByIdIp(@RequestBody FindByIdRestVO reqVO) throws WccException {
	return interestedPartyBusiness.findByIdIp(reqVO.getIdInterestedParty());
    }

    @ResponseBody
    @RequestMapping("isTaskComplete")
    public boolean isTaskComplete(@RequestBody IsTaskCompleteRestVO reqVO) throws WccException {
	return interestedPartyBusiness.isTaskComplete(reqVO.getTaskCode());
    }

    /**
     * Find interested party.
     *
     * @param reqVO
     *            the req VO
     * @return the interested party search result VO
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("findInterestedParty")
    public InterestedPartySearchResultVO findInterestedParty(@RequestBody FindInterestedPartyRestVO reqVO) throws WccException {

	return interestedPartyBusiness.findInterestedParty(reqVO.getSearchVO());

    }

    /**
     * Find interested party for ws.
     *
     * @param reqVO
     *            the req VO
     * @return the interested party search result VO
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("findInterestedPartyForWs")
    public InterestedPartySearchResultVO findInterestedPartyForWs(@RequestBody FindInterestedPartyRestVO reqVO) throws WccException {

	return interestedPartyBusiness.findInterestedPartyForWs(reqVO.getSearchVO());

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

	interestedPartyBusiness.storeTaskItemDetails(reqVO.getIpTaskItemList(), reqVO.getTaskCode(), reqVO.getCmoCode());
	return true;
    }

    /**
     * Insert or update.
     *
     * @param reqVO
     *            the req VO
     * @return the interested party
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("insertOrUpdate")
    public InterestedParty insertOrUpdate(@RequestBody InsertOrUpdateRestVO reqVO) throws WccException {
	return interestedPartyBusiness.insertOrUpdate(reqVO.getInterestedParty());
    }

    /**
     * Insert new import file.
     *
     * @param reqVO
     *            the req VO
     * @return the ip import
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("insertNewImportFile")
    public IpImport insertNewImportFile(@RequestBody InsertNewImportFileRestVO reqVO) throws WccException {
	return interestedPartyBusiness.insertNewImportFile(reqVO.getpImport());
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
	return interestedPartyBusiness.rebuildSolrIndex();
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
	return interestedPartyBusiness.indexQueuedItems();
    }

    /**
     * Find all IP status.
     *
     * @return the list
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("findAllIPStatus")
    public List<InterestedPartyStatusFlat> findAllIPStatus() throws WccException {
	return interestedPartyBusiness.findAllIPStatus();
    }

    /**
     * Logically delete ip.
     *
     * @param reqVO
     *            the req VO
     * @return the boolean
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("logicallyDeleteIp")
    public Boolean logicallyDeleteIp(@RequestBody FindByIdRestVO reqVO) throws WccException {
	interestedPartyBusiness.logicallyDeleteIp(reqVO.getIdInterestedParty());
	return true;
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
	return interestedPartyBusiness.executeMassiveAction(reqVO.getIdList(), reqVO.getAction());
    }

    /**
     * Execute massive action.
     *
     * @param reqVO
     *            the req VO
     * @return the long
     * @throws WccException
     *             the wcc exception
     */
    @ResponseBody
    @RequestMapping("findIpByName")
    public Long executeMassiveAction(@RequestBody FindIpByNameRestVO reqVO) throws WccException {
	return interestedPartyBusiness.findIpByName(reqVO.getIdName());
    }

    @ResponseBody
    @RequestMapping("convertTaskDetail")
    public InterestedParty convertTaskDetail(@RequestBody ConvertTaskDetailInIpRestVO reqVO) throws WccException {
	return interestedPartyBusiness.convertTaskDetail(reqVO.getIpTaskItemDetail());
    }

    @ResponseBody
    @RequestMapping("findGroups")
    public NameGroupResultVO findGroups(@RequestBody NameSearchVO reqVO) throws WccException {
	return interestedPartyBusiness.findGroups(reqVO);
    }

    @ResponseBody
    @RequestMapping("findGroupById")
    public GroupDTO findGroupById(@RequestBody Long idGroup) throws WccException {
	return interestedPartyBusiness.findGroupById(idGroup);
    }

    @ResponseBody
    @RequestMapping("findGroupDetailsByIdGroup")
    public List<GroupDetailDTO> findGroupDetailsByIdGroup(@RequestBody Long idGroup) throws WccException {
	return interestedPartyBusiness.findGroupDetailsByIdGroup(idGroup);
    }

    @ResponseBody
    @RequestMapping("getIpTaskElaborationResultByItemCode")
    public List<IpTaskElaborationResult> getIpTaskElaborationResultByItemCode(@RequestBody GetIpTaskElabResultByItemCodeRestVO reqVO) throws WccException {
	return interestedPartyBusiness.getIpTaskElaborationResultByItemCode(reqVO.getItemCodeList());
    }

    @ResponseBody
    @RequestMapping("findInterestedPartyAffiliation")
    public List<Affiliation> findInterestedPartyAffiliation(@RequestBody Long ipId) throws WccException {
	return interestedPartyBusiness.findInterestedPartyAffiliation(ipId);
    }

    @ResponseBody
    @RequestMapping("explodeIpiRightType")
    public List<Affiliation> explodeIpiRightType(@RequestBody ExplodeIpiRightTypeRestVO reqVO) throws WccException {
	return interestedPartyBusiness.explodeIpiRightType(reqVO.getAffiliationList(), reqVO.isRemoveAd());
    }

    @ResponseBody
    @RequestMapping("findIpImport")
    public List<IpImport> findIpImport() throws WccException {
	return interestedPartyBusiness.findIpImport();
    }

    @ResponseBody
    @RequestMapping("getFullExportBean")
    public ExportBean getFullExportBean(@RequestBody FindByIpIdListRestVO reqVO) throws WccException, IOException {
	return interestedPartyBusiness.getFullExportBean(reqVO.getFileFormatEnum());

    }

    @ResponseBody
    @RequestMapping("getExportBean")
    public ExportBean getExportBean(@RequestBody FindByIpIdListRestVO reqVO) throws WccException, IOException {
	return interestedPartyBusiness.getExportBean(reqVO.getIpList(), reqVO.getExportType(), reqVO.getFileFormatEnum());
    }

    @ResponseBody
    @RequestMapping("createExportFileByIpList")
    public String createExportFileByIpList(@RequestBody FindByIpListRestVO reqVO) throws WccException, IOException {
	return interestedPartyBusiness.createExportFileByIpList(reqVO.getIpList(), reqVO.getExportType());

    }

    @ResponseBody
    @RequestMapping("getNameByMainId")
    public Name getNameByMainId(@RequestBody String nameMainId) throws WccException, IOException {
	return interestedPartyBusiness.getNameByMainId(nameMainId);
    }

}
