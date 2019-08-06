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

import org.wipo.connect.common.exception.WccException;
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
import org.wipo.connect.shared.exchange.utils.importexport.ExportBean;
import org.wipo.connect.shared.exchange.vo.InterestedPartySearchResultVO;
import org.wipo.connect.shared.exchange.vo.InterestedPartySearchVO;
import org.wipo.connect.shared.exchange.vo.NameGroupResultVO;
import org.wipo.connect.shared.exchange.vo.NameSearchVO;

public interface InterestedPartyBusiness {

    InterestedParty findById(Long idInterestedParty, String code, List<String> creationClassCodeList) throws WccException;

    InterestedPartySearchResultVO findInterestedParty(InterestedPartySearchVO searchVO) throws WccException;

    InterestedPartySearchResultVO findInterestedPartyForWs(InterestedPartySearchVO searchVO) throws WccException;

    boolean isTaskComplete(String taskCode) throws WccException;

    void storeTaskItemDetails(List<IpTaskItem> ipTaskItemList, String taskCode, String cmoCode) throws WccException;

    InterestedParty insertOrUpdate(InterestedParty ipFromSession) throws WccException;

    IpImport insertNewImportFile(IpImport ipImport) throws WccException;

    List<InterestedPartyStatusFlat> findAllIPStatus() throws WccException;

    Integer rebuildSolrIndex() throws WccException;

    Integer indexQueuedItems() throws WccException;

    InterestedParty findByIdIp(Long idInterestedParty) throws WccException;

    InterestedParty findByMainId(String mainId) throws WccException;

    void logicallyDeleteIp(Long ipId) throws WccException;

    Integer executeMassiveAction(List<Long> idList, MassiveActionsEnum action) throws WccException;

    Long findIpByName(Long nameId) throws WccException;

    InterestedParty convertTaskDetail(IpTaskItemDetail ipTaskItemDetail) throws WccException;

    List<IpTaskElaborationResult> getIpTaskElaborationResultByItemCode(List<String> itemCodeList) throws WccException;;

    NameGroupResultVO findGroups(NameSearchVO searchVO) throws WccException;

    GroupDTO findGroupById(Long idGroup) throws WccException;

    List<GroupDetailDTO> findGroupDetailsByIdGroup(Long idGroup) throws WccException;

    List<Affiliation> findInterestedPartyAffiliation(Long idInterestedParty) throws WccException;

    List<Affiliation> explodeIpiRightType(List<Affiliation> affiliationList, boolean removeAd) throws WccException;

    List<IpImport> findIpImport() throws WccException;

    ExportBean getExportBean(List<Long> idList, ExportTypeEnum exportType, FileFormatEnum fileFormatEnum) throws WccException;

    String createExportFileByIpList(List<InterestedParty> ipList, ExportTypeEnum exportType) throws WccException, IOException;

    ExportBean getFullExportBean(FileFormatEnum fileFormatEnum) throws WccException;

    Name getNameByMainId(String nameMainId) throws WccException;

}
