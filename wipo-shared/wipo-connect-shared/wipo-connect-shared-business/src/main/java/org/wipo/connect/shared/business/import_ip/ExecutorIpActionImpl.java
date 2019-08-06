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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.i18n.ImportMessagesDecoder;
import org.wipo.connect.common.import_model.IpRow;
import org.wipo.connect.common.import_model.IpRowTypeEnum;
import org.wipo.connect.common.import_model.TransactionTypeEnum;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.enumerator.WccIpImportExceptionCodeEnum;
import org.wipo.connect.shared.exchange.dto.impl.InterestedParty;
import org.wipo.connect.shared.exchange.dto.impl.InterestedPartyIdentifierFlat;
import org.wipo.connect.shared.exchange.dto.impl.Name;
import org.wipo.connect.shared.exchange.enumerator.NameTypeEnum;

/**
 * The Class ActionManagerImpl.
 */
@Service
@Qualifier(value = "actionManagerImpl")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class ExecutorIpActionImpl implements ExecutorIpAction {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(ExecutorIpActionImpl.class);

    @Autowired
    private ImportIpDAOHelper importIpDAOHelper;

    @Autowired
    private ImportMessagesDecoder ipImportMessagesDecoder;

    @Value("#{configProperties['workGroup.nameType'].split(',')}")
    private List<String> groupNameTypeList;

    private static final String MAP_SEP = "\u0000\u0000\u0000";

    @Override
    public void executeTransaction(TransactionTypeEnum transaction, InterestedParty ip, List<IpRow> ipRowList) throws WccInterestedPartyImportException {

	try {

	    switch (transaction) {
		case INSERT:
		    ip = executeInsert(ip);
		    break;
		case UPDATE:
		    InterestedParty oldIp = importIpDAOHelper.findByMainId(ip.getMainId(), true);
		    ip = executeUpdate(ip, oldIp);
		    break;
		case DELETE:
		    executeDelete(ip.getMainId());
		    break;
	    }

	    setStatusIds(ipRowList, ip.getMainId(), transaction);

	} catch (WccException e) {
	    LOGGER.error("Error saving ip", e);
	    throw new WccInterestedPartyImportException(WccIpImportExceptionCodeEnum.GENERIC_ERROR);
	}

    }

    private InterestedParty executeInsert(InterestedParty ip) throws WccException {
	List<Name> groupNameList = new ArrayList<>();
	ip.getNameList().removeIf(n -> {
	    if (groupNameTypeList.contains(n.getNameType())) {
		groupNameList.add(n);
		return true;
	    }
	    return false;
	});

	ip = importIpDAOHelper.insertOrUpdate(ip);

	for (Name groupName : groupNameList) {
	    importIpDAOHelper.manageGroup(groupName, ip.getId(), true);
	}

	return ip;
    }

    private InterestedParty executeUpdate(InterestedParty ip, InterestedParty oldIp) throws WccException {
	ip.setId(oldIp.getId());
	ip.setFkStatus(oldIp.getFkStatus());
	ip.setCmoOriginCode(oldIp.getCmoOriginCode());

	if (StringUtils.isBlank(ip.getSex())) {
	    ip.setSex(oldIp.getSex());
	}

	if (ip.getBirthDate() == null) {
	    ip.setBirthDate(oldIp.getBirthDate());
	}

	if (ip.getDeathDate() == null) {
	    ip.setDeathDate(oldIp.getDeathDate());
	}

	if (StringUtils.isBlank(ip.getBirthCountryCode())) {
	    ip.setBirthCountryCode(oldIp.getBirthCountryCode());
	    ip.setFkBirthCountry(oldIp.getFkBirthCountry());
	}

	// TODO: WCONNECT-63 ------------------------------
	if (StringUtils.isBlank(ip.getBirthPlace())) {
	    ip.setBirthPlace(oldIp.getBirthPlace());
	} // ----------------------------------------------

	// CITIZENSHIP: remove duplicate
	ip.getCitizenshipIdList().removeAll(oldIp.getCitizenshipIdList());
	ip.getCitizenshipIdList().addAll(oldIp.getCitizenshipIdList());

	// GROUP NAME
	List<Name> groupNameList = new ArrayList<>();

	// NAME
	Map<String, Name> oldNameMap = new HashMap<>();
	Map<String, Name> newNameMap = new HashMap<>();
	for (Name n : oldIp.getNameList()) {
	    oldNameMap.put(getNameKey(n), n);
	}

	boolean havePatronymName = StringUtils.isNotEmpty(oldIp.getPatronymName());
	for (Name n : ip.getNameList()) {
	    String key = getNameKey(n);

	    if (!oldNameMap.containsKey(key)) {
		boolean similarName = false;
		for (Name oldN : oldIp.getNameList()) {
		    if (StringUtils.equalsIgnoreCase(n.getName(), oldN.getName()) && StringUtils.equalsIgnoreCase(n.getFirstName(), oldN.getFirstName())) {
			similarName = true;
			break;
		    }
		}

		if (!similarName) {
		    if (StringUtils.equals(n.getNameType(), NameTypeEnum.PA.name())) {
			if (havePatronymName) {
			    n.setNameType(NameTypeEnum.PP.name());
			}
			havePatronymName = true;
		    } else if (groupNameTypeList.contains(n.getNameType())) {
			groupNameList.add(n);
			continue;
		    }
		    newNameMap.put(key, n);
		}
	    } else {
		if (StringUtils.equals(n.getNameType(), NameTypeEnum.PA.name())) {
		    if (havePatronymName) {
			n.setNameType(NameTypeEnum.PP.name());
		    }
		    havePatronymName = true;
		} else if (groupNameTypeList.contains(n.getNameType())) {
		    groupNameList.add(n);
		    continue;
		}
		Name nameToUpdate = oldNameMap.get(key);
		nameToUpdate.setName(n.getName());
		nameToUpdate.setFirstName(n.getFirstName());
		if (!StringUtils.equals(nameToUpdate.getNameType(), NameTypeEnum.PA.name())) {
		    nameToUpdate.setNameType(n.getNameType());
		}
		newNameMap.put(key, nameToUpdate);
	    }
	}

	ip.getNameList().clear();
	ip.getNameList().addAll(newNameMap.values());

	// IDENTIFIER
	Map<String, InterestedPartyIdentifierFlat> identifierMap = new HashMap<>();
	for (InterestedPartyIdentifierFlat i : oldIp.getInterestedPartyIdentifierFlatList()) {
	    identifierMap.put(getIdentifierKey(i), i);
	}
	ip.getInterestedPartyIdentifierFlatList().removeIf(i -> identifierMap.containsKey(getIdentifierKey(i)));

	// AFFILIATION
	if (!ip.getAffiliationList().isEmpty()) {
	    importIpDAOHelper.removeAffiliationByIp(ip.getId());
	}

	ip = importIpDAOHelper.insertOrUpdate(ip);

	for (Name groupName : groupNameList) {
	    importIpDAOHelper.manageGroup(groupName, ip.getId(), false);
	}

	return ip;

    }

    private void executeDelete(String mainId) throws WccException, WccInterestedPartyImportException {
	Long ipId = importIpDAOHelper.findInterestedPartyIdByMainId(mainId);

	if (null != ipId) {
	    // If an Interested Party is found with that identifier it is
	    // checked if the Patronym Name Main ID corresponds with the one on
	    // the file:
	    if (importIpDAOHelper.isExistsByNameId(ipId)) {
		LOGGER.error("Error: {}", WccIpImportExceptionCodeEnum.GENERIC_ERROR);
		LOGGER.error("some name is used within non-deleted works");
		throw new WccInterestedPartyImportException(WccIpImportExceptionCodeEnum.DELETE_REJECTED);
	    }
	    importIpDAOHelper.logicallyDeleteIp(ipId);

	} else {
	    LOGGER.error("IP not found");
	    throw new WccInterestedPartyImportException(WccIpImportExceptionCodeEnum.RIGHT_OWNER_NOT_FOUND);
	}
    }

    private String getIdentifierKey(InterestedPartyIdentifierFlat i) {
	return (i.getCode() + MAP_SEP + i.getValue()).replace(" ", "_").toLowerCase();
    }

    private void setStatusIds(List<IpRow> entityRow, String mainId, TransactionTypeEnum transaction) {

	IpRow mainRow = entityRow.stream().filter(it -> IpRowTypeEnum.MA.name().equals(it.getRowType())).findFirst().get();
	long otherRowsInError = entityRow.stream()
		.filter(it -> !IpRowTypeEnum.MA.name().equals(it.getRowType()) && !it.getErrorCode().equals(WccIpImportExceptionCodeEnum.NO_ERROR.getCode())).count();
	if (mainRow.getErrorCode().equals(WccIpImportExceptionCodeEnum.NO_ERROR.getCode()) && otherRowsInError > 0) {
	    mainRow.setStatus(ipImportMessagesDecoder.getWarningMessage(null));
	    mainRow.setIds(mainId);
	} else if (mainRow.getErrorCode().equals(WccIpImportExceptionCodeEnum.NO_ERROR.getCode()) && otherRowsInError == 0) {
	    mainRow.setStatus(ipImportMessagesDecoder.getSuccessMessage(transaction));
	    mainRow.setIds(mainId);
	} else {
	    mainRow.setStatus(ipImportMessagesDecoder.getWarningMessage(mainRow.getErrorCode()));
	    mainRow.setIds(mainId);
	}

	entityRow.stream().filter(it -> !IpRowTypeEnum.MA.name().equals(it.getRowType())).forEach(row -> {
	    if (row.getErrorCode().equalsIgnoreCase(WccIpImportExceptionCodeEnum.NO_ERROR.getCode())) {
		row.setIds(mainId);
		row.setStatus(ipImportMessagesDecoder.getSuccessMessage(transaction));
	    } else {
		row.setStatus(ipImportMessagesDecoder.getErrorMessage(row.getErrorCode()));
	    }

	});

    }

    private String getNameKey(Name n) {
	String key = StringUtils.defaultIfBlank(StringUtils.trimToNull(n.getMainId()), StringUtils.trimToNull(n.getName()) + MAP_SEP + StringUtils.trimToNull(n.getFirstName()));
	return key.replace(" ", "_").toLowerCase();
    }

}
