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
package org.wipo.connect.shared.business.import_ip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.enumerator.IdentifierManagerRefTableEnum;
import org.wipo.connect.shared.exchange.business.InterestedPartyBusiness;
import org.wipo.connect.shared.exchange.dto.impl.Affiliation;
import org.wipo.connect.shared.exchange.dto.impl.AffiliationDomain;
import org.wipo.connect.shared.exchange.dto.impl.InterestedParty;
import org.wipo.connect.shared.exchange.dto.impl.IpImport;
import org.wipo.connect.shared.exchange.dto.impl.IpImportDetail;
import org.wipo.connect.shared.exchange.dto.impl.IpImportFile;
import org.wipo.connect.shared.exchange.dto.impl.Name;
import org.wipo.connect.shared.exchange.enumerator.ImportStatusEnum;
import org.wipo.connect.shared.persistence.dao.AffiliationDAO;
import org.wipo.connect.shared.persistence.dao.DerivedViewDAO;
import org.wipo.connect.shared.persistence.dao.IdentifierManagerDAO;
import org.wipo.connect.shared.persistence.dao.InterestedPartyDAO;
import org.wipo.connect.shared.persistence.dao.IpImportDAO;
import org.wipo.connect.shared.persistence.dao.IpImportDetailDAO;
import org.wipo.connect.shared.persistence.dao.NameDAO;

import net.bull.javamelody.MonitoredWithSpring;

/**
 * The Class ImportDAOHelper.
 */
@Service
@MonitoredWithSpring
public class ImportIpDAOHelperImpl implements ImportIpDAOHelper {

    @Autowired
    private IpImportDAO ipImportDAOImpl;
    @Autowired
    private IpImportDetailDAO ipImportDetailDAOImpl;
    @Autowired
    private AffiliationDAO affiliationDAOImpl;
    @Autowired
    private InterestedPartyDAO interestedPartyDAOImpl;
    @Autowired
    private InterestedPartyBusiness interestedPartyBusinessImpl;
    @Autowired
    private DerivedViewDAO derivedViewDAOImpl;
    @Autowired
    private NameDAO nameDAOImpl;
    @Autowired
    private IdentifierManagerDAO identifierManagerDAO;

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(ImportIpDAOHelperImpl.class);

    @Override
    public List<IpImport> findIpImportFromStatus(ImportStatusEnum status) {
	LOGGER.debug("Loading WorkImport in QUEUED status");
	return ipImportDAOImpl.findIpImportFromStatus(status);
    }

    @Override
    public int updateIpImportFromStatus(Long idImport, ImportStatusEnum status) {
	int i = ipImportDAOImpl.updateStatus(idImport, status);
	if (status.equals(ImportStatusEnum.IN_PROGRESS)) {
	    i = ipImportDAOImpl.updateImportStartDate(idImport, new Date());
	}
	if (status.equals(ImportStatusEnum.ERROR) || status.equals(ImportStatusEnum.COMPLETED)) {
	    i = ipImportDAOImpl.updateImportEndDate(idImport, new Date());
	}
	return i;
    }

    @Override
    public InterestedParty findByMainId(String mainId, boolean loadDetail) {
	return interestedPartyDAOImpl.findInterestedPartiesByIpMainId(mainId, true);
    }

    @Override
    public boolean checkIfExistsAnotherIp(String nameMainId, Long idIp) {
	return interestedPartyDAOImpl.checkIfExistsAnotherIp(nameMainId, idIp);
    }

    @Override
    public InterestedParty insertOrUpdate(InterestedParty ip) throws WccException {
	return interestedPartyBusinessImpl.insertOrUpdate(ip);
    }

    @Override
    public int logicallyDeleteIp(Long ipId) throws WccException {
	int ip = this.interestedPartyDAOImpl.logicallyDeleteIp(ipId);
	nameDAOImpl.markAsDeletedByIp(ipId);
	return ip;
    }

    @Override
    public boolean isExistsByNameId(Long ipId) {
	List<Name> names = nameDAOImpl.findNameByIp(ipId);
	boolean isExists = false;
	for (Name name : names) {
	    if (derivedViewDAOImpl.isExistsByNameId(name.getId())) {
		isExists = true;
		break;
	    }
	}
	return isExists;
    }

    @Override
    public boolean isInDerivedView(Long idName) {
	boolean isExists = false;
	if (derivedViewDAOImpl.isExistsByNameId(idName)) {
	    isExists = true;
	}
	return isExists;
    }

    @Override
    public void insertAffiliation(List<Affiliation> affiliationList, Long fkIp) {
	for (Affiliation affiliation : affiliationList) {
	    affiliation.setFkInterestedParty(fkIp);
	    affiliationDAOImpl.insert(affiliation);
	    for (AffiliationDomain affiliationDomain : affiliation.getAffiliationDomainList()) {
		affiliationDomain.setFkAffiliation(affiliation.getId());
		affiliationDomain.setIsExcluded(false);
		affiliationDAOImpl.insertAffiliationDomain(affiliationDomain);

	    }
	}
    }

    @Override
    public List<Affiliation> findAffiliationByIp(Long idInterestedParty) {
	if (idInterestedParty != null) {
	    return affiliationDAOImpl.findByInterestedParty(idInterestedParty, null);
	}
	return new ArrayList<>();
    }

    @Override
    public void removeAffiliationByIp(Long idInterestedParty) {
	affiliationDAOImpl.removeAllAffiliationByIp(idInterestedParty);
    }

    @Override
    public int insertIpImportFile(final IpImportFile ipImportFile) throws WccException {
	try {
	    return ipImportDAOImpl.insertIpImportFile(ipImportFile);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    @Override
    @Deprecated
    public Long insertIpImportDetail(final IpImportDetail ipImportDetail) throws WccException {
	try {
	    return ipImportDetailDAOImpl.insertIpImportDetail(ipImportDetail);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    @Override
    public Long findIdNameByNameMainId(String nameMainId) {
	return nameDAOImpl.findIdByNameMainId(nameMainId);
    }

    @Override
    public boolean checkMainIdIsPresent(String mainId) {
	if (StringUtils.isBlank(mainId)) {
	    return false;
	}

	if (interestedPartyDAOImpl.findInterestedPartyIdByIpMainId(mainId, true) != null) {
	    return true;
	}
	return false;
    }

    @Override
    public Name findNameByNameMainId(String mainId) {
	List<Name> nameList = nameDAOImpl.findByNameMainId(mainId);
	if (!nameList.isEmpty()) {
	    return nameList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public boolean checkNameMainIdUniqueness(String nameMainId, Long idIp) {
	if (idIp == null) {
	    return identifierManagerDAO.checkIdUniqueness(IdentifierManagerRefTableEnum.NAME, nameMainId, null);
	} else {
	    return identifierManagerDAO.checkIdUniqueness(IdentifierManagerRefTableEnum.NAME, nameMainId, Arrays.asList(idIp));
	}
    }

    @Override
    public int deleteIpIdentifier(Long idInterestedParty) {
	return interestedPartyDAOImpl.deleteIpIdentifierByIp(idInterestedParty);
    }

    @Override
    public Boolean isIdentifierValueAlreadyPresent(String code, String value, Long idIp) {
	return interestedPartyDAOImpl.isIdentifierValueAlreadyPresent(code, value, idIp);
    }

    @Override
    public Long findInterestedPartyIdByMainId(String mainId) {
	return interestedPartyDAOImpl.findInterestedPartyIdByIpMainId(mainId, true);
    }

    @Override
    public void createGroup(Name groupName, Long ipId, boolean insertMode) throws WccException {
	Name name = findNameByNameMainId(groupName.getMainId());
	if (name == null) {
	    nameDAOImpl.insertDummy(groupName);
	    nameDAOImpl.merge(groupName);
	    interestedPartyDAOImpl.insertIPName(ipId, groupName.getId());
	    // Update case
	} else {
	    groupName.setId(name.getId());
	    nameDAOImpl.merge(groupName);
	    if (insertMode) {
		interestedPartyDAOImpl.insertIPName(ipId, groupName.getId());
	    }
	}

    }

    @Override
    public boolean isExistsInWorkByNameId(Long ipId) {
	List<Name> names = nameDAOImpl.findNameByIp(ipId);
	for (Name name : names) {
	    if (derivedViewDAOImpl.isExistsByNameId(name.getId())) {
		return true;
	    }
	}

	return false;
    }

    @Override
    public boolean checkIfExistNameForIp(String mainId, Long idInterestedParty) {
	boolean isFound = false;
	Long idIp = interestedPartyDAOImpl.findInterestedPartyIdByIpMainId(mainId, true);
	if (null != idIp && Long.compare(idIp, idInterestedParty) == 0) {
	    return isFound = true;
	} else {
	    return isFound;
	}
    }

    @Override
    public void updateRowResult(IpImport ipImport) {
	ipImportDAOImpl.updateRowResult(ipImport);
    }

    @Override
    public void markAllPendingAsError() {
	ipImportDAOImpl.markAllPendingAsError();
    }

    @Override
    public void manageGroup(Name groupName, Long ipId, boolean insertMode) throws WccException {

	Name name = findNameByNameMainId(groupName.getMainId());
	if (name == null) {
	    if (StringUtils.isEmpty(groupName.getMainId())) {
		String mainId = identifierManagerDAO.getNewMainIdentifier(IdentifierManagerRefTableEnum.NAME);
		groupName.setMainId(mainId);
	    }
	    nameDAOImpl.insertDummy(groupName);
	    nameDAOImpl.merge(groupName);
	    interestedPartyDAOImpl.insertIPName(ipId, groupName.getId());
	    // Update case
	} else {
	    groupName.setId(name.getId());
	    nameDAOImpl.merge(groupName);

	    if (!insertMode) {
		interestedPartyDAOImpl.deleteIPName(ipId, groupName.getId());
	    }
	    interestedPartyDAOImpl.insertIPName(ipId, groupName.getId());
	}

    }

}
