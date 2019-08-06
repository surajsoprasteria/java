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
package org.wipo.connect.shared.business.import_ip;

import java.util.List;

import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.shared.exchange.dto.impl.Affiliation;
import org.wipo.connect.shared.exchange.dto.impl.InterestedParty;
import org.wipo.connect.shared.exchange.dto.impl.IpImport;
import org.wipo.connect.shared.exchange.dto.impl.IpImportDetail;
import org.wipo.connect.shared.exchange.dto.impl.IpImportFile;
import org.wipo.connect.shared.exchange.dto.impl.Name;
import org.wipo.connect.shared.exchange.enumerator.ImportStatusEnum;

public interface ImportIpDAOHelper {

    List<IpImport> findIpImportFromStatus(ImportStatusEnum status);

    int updateIpImportFromStatus(Long idImport, ImportStatusEnum status);

    InterestedParty findByMainId(String ipMainId, boolean loadDetail);

    boolean checkIfExistsAnotherIp(String nameMainId, Long idIp);

    InterestedParty insertOrUpdate(InterestedParty ip) throws WccException;

    int logicallyDeleteIp(Long ipId) throws WccException;

    boolean isExistsByNameId(Long nameId);

    boolean isInDerivedView(Long idName);

    boolean isExistsInWorkByNameId(Long ipId);

    List<Affiliation> findAffiliationByIp(Long idInterestedParty);

    void insertAffiliation(List<Affiliation> affiliationList, Long fkIp);

    void removeAffiliationByIp(Long idInterestedParty);

    int insertIpImportFile(IpImportFile ipImportFile) throws WccException;

    @Deprecated
    Long insertIpImportDetail(IpImportDetail ipImportDetail) throws WccException;

    boolean checkMainIdIsPresent(String mainId);

    Long findIdNameByNameMainId(String nameMainId);

    Name findNameByNameMainId(String mainId);

    boolean checkNameMainIdUniqueness(String nameMainId, Long idIp);

    int deleteIpIdentifier(Long idInterestedParty);

    Boolean isIdentifierValueAlreadyPresent(String code, String value, Long idIp);

    Long findInterestedPartyIdByMainId(String ipMainId);

    void createGroup(Name groupName, Long ipId, boolean insertMode) throws WccException;

    boolean checkIfExistNameForIp(String ipMainId, Long idInterestedParty);

    void updateRowResult(IpImport ipImport);

    void markAllPendingAsError();

    void manageGroup(Name groupName, Long ipId, boolean insertMode) throws WccException;

}