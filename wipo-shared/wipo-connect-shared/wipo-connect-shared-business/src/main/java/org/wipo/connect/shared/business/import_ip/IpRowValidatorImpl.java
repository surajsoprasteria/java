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

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.import_model.IpRow;
import org.wipo.connect.common.import_model.IpRowTypeEnum;
import org.wipo.connect.common.import_model.TransactionTypeEnum;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.ConstantUtils;
import org.wipo.connect.common.utils.ConversionUtils;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.enumerator.TerritoryFormulaValidationResultEnum;
import org.wipo.connect.enumerator.WccIpImportExceptionCodeEnum;
import org.wipo.connect.shared.exchange.dto.impl.Affiliation;
import org.wipo.connect.shared.exchange.dto.impl.AffiliationDomain;
import org.wipo.connect.shared.exchange.dto.impl.IpiRightTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.IpiRoleFlat;
import org.wipo.connect.shared.exchange.dto.impl.Name;
import org.wipo.connect.shared.exchange.enumerator.NameTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.TypeEnum;
import org.wipo.connect.shared.exchange.utils.TerritoryFormulaUtils;
import org.wipo.connect.shared.exchange.vo.TerritoryHierarchyVO;

@Service
public class IpRowValidatorImpl implements IpRowValidator {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(IpRowValidatorImpl.class);

    @Autowired
    private IpCodeMapper ipCodeMapper;

    @Autowired
    private ImportIpDAOHelper importIpDAOHelper;

    @Value("#{configProperties['workGroup.nameType'].split(',')}")
    private List<String> groupNameTypeList;

    private SimpleDateFormat ft;

    @Override
    @PostConstruct
    public void init() {
	ft = new SimpleDateFormat(ConversionUtils.DATE_STAMP);
	ft.setLenient(false);
    }

    @Override
    public TransactionTypeEnum validateTransaction(List<IpRow> ipRowList) throws WccInterestedPartyImportException {
	List<IpRow> mainRow = ipRowList.stream().filter(m -> StringUtils.equals(m.getRowType(), IpRowTypeEnum.MA.name())).collect(Collectors.toList());

	if (mainRow.isEmpty() || mainRow.size() > 1) {
	    throw new WccInterestedPartyImportException(WccIpImportExceptionCodeEnum.ROW_MAIN_NOT_UNIQUE);
	} else if (StringUtils.isEmpty(mainRow.get(0).getTransaction())) {
	    throw new WccInterestedPartyImportException(WccIpImportExceptionCodeEnum.TRANSACTION_EMPTY);
	} else if (!EnumUtils.isValidEnum(TransactionTypeEnum.class, mainRow.get(0).getTransaction())) {
	    throw new WccInterestedPartyImportException(WccIpImportExceptionCodeEnum.TRANSACTION_UNKNOWN);
	}

	TransactionTypeEnum transaction = EnumUtils.getEnum(TransactionTypeEnum.class, mainRow.get(0).getTransaction());

	if (transaction == TransactionTypeEnum.UPDATE) {
	    if (!importIpDAOHelper.checkMainIdIsPresent(mainRow.get(0).getMainId())) {
		transaction = TransactionTypeEnum.INSERT;
	    }
	}

	return transaction;
    }

    @Override
    public List<IpRow> validate(TransactionTypeEnum transaction, List<IpRow> entityRows) throws WccInterestedPartyImportException {

	Long idInterestedParty = null;
	List<AffiliationDomain> adList = new ArrayList<>();
	Set<String> identifierList = new HashSet<>();
	AffiliationContextMap acm = new AffiliationContextMap(ipCodeMapper.getTerritoryHierarchy());

	for (IpRow row : entityRows) {
	    // /////////////////validation of rowtype///////////////////
	    String rowType = row.getRowType();
	    IpRowTypeEnum rowTypeEnum = IpRowTypeEnum.UNKNOWN;
	    if (null != rowType && EnumUtils.isValidEnum(IpRowTypeEnum.class, rowType)) {
		rowTypeEnum = IpRowTypeEnum.valueOf(rowType);
	    }

	    switch (rowTypeEnum) {
		case MA:
		    LOGGER.info("ValidateRowEntity of type: {}", IpRowTypeEnum.MA);
		    if (StringUtils.isEmpty(row.getErrorCode())) {
			if (TransactionTypeEnum.UPDATE.equals(transaction)) {
			    idInterestedParty = importIpDAOHelper.findInterestedPartyIdByMainId(row.getMainId());
			    if (entityRows.stream()
				    .filter(r -> StringUtils.equals(r.getRowType(), IpRowTypeEnum.AF.name()))
				    .findFirst().isPresent()) {
				List<Affiliation> affiliationList = importIpDAOHelper.findAffiliationByIp(idInterestedParty);
				affiliationList.forEach(a -> {
				    adList.addAll(a.getAffiliationDomainList());
				    AffiliationHelper.checkDomainShare(acm, a);
				});
			    }
			}

			WccIpImportExceptionCodeEnum mainErrorCode = validateIpRow(transaction, row);
			row.setErrorCode(mainErrorCode.getCode());
		    }
		    break;
		case NA:
		    LOGGER.info("ValidateRowEntity of type: {}", IpRowTypeEnum.NA);
		    WccIpImportExceptionCodeEnum nameErrorCode = validateNameRow(transaction, row, idInterestedParty);
		    row.setErrorCode(nameErrorCode.getCode());
		    break;
		case AF:
		    LOGGER.info("ValidateRowEntity of type: {}", IpRowTypeEnum.AF);
		    WccIpImportExceptionCodeEnum affiliationErrorCode = validateAffiliationRow(row, acm, adList);
		    row.setErrorCode(affiliationErrorCode.getCode());
		    break;
		case ID:
		    LOGGER.info("ValidateRowEntity of type: {}", IpRowTypeEnum.ID);
		    WccIpImportExceptionCodeEnum identifierErrorCode = validateIdentifierRow(row, idInterestedParty, identifierList);
		    row.setErrorCode(identifierErrorCode.getCode());
		    break;
		default:
		    WccIpImportExceptionCodeEnum unmanagedRowType = WccIpImportExceptionCodeEnum.ROW_TYPE_EMPTY_ERROR;
		    row.setErrorCode(unmanagedRowType.getCode());
		    break;
	    }
	}

	return entityRows;
    }

    @Override
    public void checkIfRejectAllRow(TransactionTypeEnum transaction, List<IpRow> entityRows) throws WccInterestedPartyImportException {

	IpRow ipMainRow = entityRows.stream().filter(it -> StringUtils.equals(it.getRowType(), IpRowTypeEnum.MA.name())).findFirst().get();

	if (!StringUtils.equalsAny(ipMainRow.getErrorCode(),
		WccIpImportExceptionCodeEnum.COUNTRY_OF_BIRTH_UNKNOWN_ERROR.getCode(),
		WccIpImportExceptionCodeEnum.DEATH_DATE_FORMAT_ERROR.getCode(),
		WccIpImportExceptionCodeEnum.INVALID_DEATH_DATE_ERROR.getCode(),
		WccIpImportExceptionCodeEnum.BIRTH_DATE_FORMAT_ERROR.getCode(),
		WccIpImportExceptionCodeEnum.SEX_UNKNOWN_ERROR.getCode(),
		WccIpImportExceptionCodeEnum.CITIZENSHIP_UNKNOWN_ERROR.getCode(),
		WccIpImportExceptionCodeEnum.NO_ERROR.getCode())) {
	    throw new WccInterestedPartyImportException(WccIpImportExceptionCodeEnum.getEnumByCode(ipMainRow.getErrorCode()));
	}

	if (TransactionTypeEnum.DELETE.equals(transaction)) {
	    Long ipId = importIpDAOHelper.findInterestedPartyIdByMainId(ipMainRow.getMainId());

	    if (null != ipId) {
		if (importIpDAOHelper.isExistsInWorkByNameId(ipId)) {
		    LOGGER.error("Error, some name is used within non-deleted works");
		    throw new WccInterestedPartyImportException(WccIpImportExceptionCodeEnum.DELETE_REJECTED);
		}
	    } else {
		LOGGER.error("IP not found");
		throw new WccInterestedPartyImportException(WccIpImportExceptionCodeEnum.RIGHT_OWNER_NOT_FOUND);
	    }

	    long nameCount = entityRows.stream().filter(it -> StringUtils.equals(it.getRowType(), IpRowTypeEnum.NA.name())).count();
	    long affCount = entityRows.stream().filter(it -> StringUtils.equals(it.getRowType(), IpRowTypeEnum.AF.name())).count();

	    if (Long.compare(nameCount, 1) != 0 || Long.compare(affCount, 0) != 0) {
		LOGGER.error("Too many row found", WccIpImportExceptionCodeEnum.WRONG_EXPECTED_ROWS);
		throw new WccInterestedPartyImportException(WccIpImportExceptionCodeEnum.WRONG_EXPECTED_ROWS);
	    }
	} else if (TransactionTypeEnum.INSERT.equals(transaction)) {
	    if (importIpDAOHelper.checkMainIdIsPresent(ipMainRow.getMainId())) {
		throw new WccInterestedPartyImportException(WccIpImportExceptionCodeEnum.RIGHT_OWNER_ALREADY_PRESENT);
	    }
	}

	// Entity validation on Name Type
	List<IpRow> namePaRows = entityRows.stream()
		.filter(it -> StringUtils.equalsIgnoreCase(NameTypeEnum.PA.name(), it.getNameType()))
		.collect(Collectors.toList());

	if (namePaRows.isEmpty() && !TransactionTypeEnum.UPDATE.equals(transaction)) {
	    WccIpImportExceptionCodeEnum errorCode = WccIpImportExceptionCodeEnum.PA_NOT_PRESENT;
	    throw new WccInterestedPartyImportException(errorCode);
	} else if (namePaRows.size() == 1) {
	    if (StringUtils.equalsIgnoreCase(namePaRows.get(0).getErrorCode(), WccIpImportExceptionCodeEnum.NAME_MAIN_ID_EMPTY_ERROR.getCode())) {
		WccIpImportExceptionCodeEnum errorCode = WccIpImportExceptionCodeEnum.NAME_MAIN_ID_EMPTY_ERROR;
		throw new WccInterestedPartyImportException(errorCode);
	    }
	    if (StringUtils.equalsIgnoreCase(namePaRows.get(0).getErrorCode(), WccIpImportExceptionCodeEnum.NAME_ID_ALREADY_PRESENT.getCode())) {
		WccIpImportExceptionCodeEnum errorCode = WccIpImportExceptionCodeEnum.NAME_ID_ALREADY_PRESENT;
		throw new WccInterestedPartyImportException(errorCode);
	    }
	    if (StringUtils.equalsIgnoreCase(namePaRows.get(0).getErrorCode(), WccIpImportExceptionCodeEnum.LAST_COMPANY_NAME_EMPTY_ERROR.getCode())) {
		WccIpImportExceptionCodeEnum errorCode = WccIpImportExceptionCodeEnum.LAST_COMPANY_NAME_EMPTY_ERROR;
		throw new WccInterestedPartyImportException(errorCode);
	    }
	} else if (namePaRows.size() > 1) {
	    WccIpImportExceptionCodeEnum errorCode = WccIpImportExceptionCodeEnum.TO_MANY_PA_PRESENT;
	    throw new WccInterestedPartyImportException(errorCode);
	}

    }

    @Override
    public WccIpImportExceptionCodeEnum validateIpRow(TransactionTypeEnum transaction, IpRow mainRow) {
	Date dateMin = null;
	Date dateMax = null;
	WccIpImportExceptionCodeEnum errorCode = WccIpImportExceptionCodeEnum.NO_ERROR;

	// IP_MAIN_ID
	if (StringUtils.isNotEmpty(mainRow.getMainId())) {
	    String ipMainId = mainRow.getMainId();
	    ipMainId = StringUtils.trimToNull(ipMainId);
	    if (null == ipMainId) {
		return errorCode = WccIpImportExceptionCodeEnum.IP_MAIN_ID_ERROR;
	    }
	} else {
	    return errorCode = WccIpImportExceptionCodeEnum.IP_MAIN_ID_ERROR;
	}

	if (TransactionTypeEnum.DELETE.equals(transaction)) {
	    return errorCode = WccIpImportExceptionCodeEnum.NO_ERROR;
	}

	// TYPE
	if (StringUtils.isNotEmpty(mainRow.getType())) {
	    String ipType = mainRow.getType();
	    ipType = StringUtils.trimToNull(ipType);
	    if (null != ipType) {
		if (!EnumUtils.isValidEnum(TypeEnum.class, ipType)) {
		    return errorCode = WccIpImportExceptionCodeEnum.TYPE_UNKNOWN_ERROR;
		}
	    } else {
		return errorCode = WccIpImportExceptionCodeEnum.TYPE_EMPTY_ERROR;
	    }
	} else {
	    return errorCode = WccIpImportExceptionCodeEnum.TYPE_EMPTY_ERROR;
	}

	// CITIZENSHIP
	if (null != mainRow.getCitizenshipList() && !mainRow.getCitizenshipList().isEmpty()) {
	    for (String code : mainRow.getCitizenshipList()) {
		code = StringUtils.trimToNull(code);
		if (null != code) {
		    Long idTerritory = ipCodeMapper.getTerritoryCountryIdByCode(code);
		    if (idTerritory == null) {
			errorCode = WccIpImportExceptionCodeEnum.CITIZENSHIP_UNKNOWN_ERROR;
		    }
		}
	    }
	}

	// SEX
	if (StringUtils.isNotEmpty(mainRow.getSex())) {
	    String sex = mainRow.getSex();
	    sex = StringUtils.trimToNull(sex);
	    if (null != sex) {
		if ((sex.length() > 1) || (!sex.equalsIgnoreCase(ConstantUtils.M) && !sex.equalsIgnoreCase(ConstantUtils.F))) {
		    errorCode = WccIpImportExceptionCodeEnum.SEX_UNKNOWN_ERROR;
		}
	    }
	}

	try {
	    dateMin = ft.parse(ConstantUtils.MIN_DATE);
	    dateMax = ft.parse(ConstantUtils.MAX_DATE);
	} catch (ParseException e) {
	    LOGGER.error("Error", e);
	}

	Date birthDate = null;
	if (StringUtils.isNotEmpty(mainRow.getBirthDate())) {
	    String birtDateStr = mainRow.getBirthDate();
	    birtDateStr = StringUtils.trimToNull(birtDateStr);
	    try {
		if (null != birtDateStr) {
		    birthDate = ft.parse(birtDateStr);
		    if (!WccUtils.isValidDate(birtDateStr) || birthDate.compareTo(dateMin) < 0 || birthDate.compareTo(dateMax) > 0) {
			errorCode = WccIpImportExceptionCodeEnum.BIRTH_DATE_FORMAT_ERROR;
		    }
		}
	    } catch (ParseException e) {
		LOGGER.error("Error", e);
		errorCode = WccIpImportExceptionCodeEnum.BIRTH_DATE_FORMAT_ERROR;
	    }
	}

	if (StringUtils.isNotEmpty(mainRow.getDeathDate()) && birthDate != null) {
	    String deathDateStr = mainRow.getDeathDate();
	    deathDateStr = StringUtils.trimToNull(deathDateStr);
	    try {
		if (null != deathDateStr) {
		    Date date = ft.parse(deathDateStr);
		    if (!WccUtils.isValidDate(deathDateStr) || date.compareTo(dateMin) < 0 || date.compareTo(dateMax) > 0) {
			errorCode = WccIpImportExceptionCodeEnum.DEATH_DATE_FORMAT_ERROR;
		    }

		    if (null != birthDate) {
			if (date.compareTo(birthDate) < 0) {
			    errorCode = WccIpImportExceptionCodeEnum.INVALID_DEATH_DATE_ERROR;
			}
		    }
		}
	    } catch (ParseException e) {
		LOGGER.error("Error", e);
		errorCode = WccIpImportExceptionCodeEnum.DEATH_DATE_FORMAT_ERROR;
	    }
	}

	if (StringUtils.isNotEmpty(mainRow.getCountryOfBirth())) {
	    String birthcountry = mainRow.getCountryOfBirth();
	    birthcountry = StringUtils.trimToNull(birthcountry);
	    if (null != birthcountry) {
		Long idTerritory = ipCodeMapper.getTerritoryCountryIdByCode(birthcountry);
		if (idTerritory == null) {
		    errorCode = WccIpImportExceptionCodeEnum.COUNTRY_OF_BIRTH_UNKNOWN_ERROR;
		}
	    }

	}

	return errorCode;
    }

    @Override
    public WccIpImportExceptionCodeEnum validateNameRow(TransactionTypeEnum transaction, IpRow nameRow, Long idInterestedParty) {
	WccIpImportExceptionCodeEnum errorCode = WccIpImportExceptionCodeEnum.NO_ERROR;
	boolean isGroupName = false;
	// LAST_COMPANY_NAME
	if (!StringUtils.isEmpty(nameRow.getLastCompanyName())) {
	    String lastCompanyName = nameRow.getLastCompanyName();
	    lastCompanyName = StringUtils.trimToNull(lastCompanyName);
	    if (null == lastCompanyName) {
		errorCode = WccIpImportExceptionCodeEnum.LAST_COMPANY_NAME_EMPTY_ERROR;
	    }
	} else {
	    errorCode = WccIpImportExceptionCodeEnum.LAST_COMPANY_NAME_EMPTY_ERROR;
	}

	// NAME_TYPE
	if (StringUtils.isNotEmpty(nameRow.getNameType())) {
	    String nameType = nameRow.getNameType();
	    nameType = StringUtils.trimToNull(nameType);
	    if (null != nameType) {
		// Activity: N_10A-B-C: import management for PG name
		if (groupNameTypeList.contains(nameType)) {
		    isGroupName = true;
		    if (StringUtils.isNotEmpty(nameRow.getMainId())) {
			Name name = importIpDAOHelper.findNameByNameMainId(nameRow.getMainId());
			if (null != name && !checkIfExistGroupName(nameRow, name)) {
			    return errorCode = WccIpImportExceptionCodeEnum.GROUP_NAME_CONFLICT;
			}
		    }

		} else if (!EnumUtils.isValidEnum(NameTypeEnum.class, nameType)) {
		    return errorCode = WccIpImportExceptionCodeEnum.NAME_TYPE_UNKNOWN_ERROR;
		}

	    } else {
		return errorCode = WccIpImportExceptionCodeEnum.NAME_TYPE_EMPTY_ERROR;
	    }
	} else {
	    errorCode = WccIpImportExceptionCodeEnum.NAME_TYPE_EMPTY_ERROR;
	}

	if (!isGroupName) {
	    String nameMainId = StringUtils.trimToNull(nameRow.getMainId());
	    if (nameMainId != null) {
		if (!importIpDAOHelper.checkNameMainIdUniqueness(nameRow.getMainId(), idInterestedParty)) {
		    return errorCode = WccIpImportExceptionCodeEnum.NAME_ID_ALREADY_PRESENT;
		}
	    } else {
		return errorCode = WccIpImportExceptionCodeEnum.NAME_MAIN_ID_EMPTY_ERROR;
	    }
	}

	return errorCode;
    }

    @Override
    public WccIpImportExceptionCodeEnum validateIdentifierRow(IpRow row, Long idInterestedParty, Set<String> identifierList) {
	WccIpImportExceptionCodeEnum errorCode = WccIpImportExceptionCodeEnum.NO_ERROR;

	String identifierType;
	String identifierValue;

	// IDENTIFIER_CODE
	if (StringUtils.isNotEmpty(row.getType())) {
	    identifierType = StringUtils.trimToNull(row.getType());
	    if (StringUtils.isNotEmpty(identifierType)) {
		if (null == ipCodeMapper.getIdentifierByCode(row.getType())) {
		    return errorCode = WccIpImportExceptionCodeEnum.CODE_FORMAT_ERROR;
		}
	    } else {
		return errorCode = WccIpImportExceptionCodeEnum.CODE_EMPTY_ERROR;
	    }
	} else {
	    return errorCode = WccIpImportExceptionCodeEnum.CODE_EMPTY_ERROR;
	}

	// IDENTIFIER_VALUE
	if (StringUtils.isNotEmpty(row.getValue())) {
	    identifierValue = row.getValue();
	    identifierValue = StringUtils.trimToNull(identifierValue);
	    if (StringUtils.isNotEmpty(identifierValue)) {
		String identifierKey = identifierType.toLowerCase() + "\u0000\u0000\u0000" + identifierValue.toLowerCase();
		if (identifierList.contains(identifierKey)) {
		    return errorCode = WccIpImportExceptionCodeEnum.IDENTIFIER_VALUE_TYPE_ALREADY_PRESENT;
		}
		boolean isAlreadyPresent = importIpDAOHelper.isIdentifierValueAlreadyPresent(row.getType(), row.getValue(), idInterestedParty);
		if (BooleanUtils.isTrue(isAlreadyPresent)) {
		    return errorCode = WccIpImportExceptionCodeEnum.IDENTIFIER_VALUE_TYPE_ALREADY_PRESENT;
		} else {
		    identifierList.add(identifierKey);
		}
	    } else {
		return errorCode = WccIpImportExceptionCodeEnum.VALUE_EMPTY_ERROR;
	    }
	} else {
	    return errorCode = WccIpImportExceptionCodeEnum.VALUE_EMPTY_ERROR;
	}

	return errorCode;
    }

    @Override
    public WccIpImportExceptionCodeEnum validateAffiliationRow(IpRow affiliationRow, AffiliationContextMap acm, List<AffiliationDomain> adList) {
	WccIpImportExceptionCodeEnum errorCode = WccIpImportExceptionCodeEnum.NO_ERROR;

	Long creationClassId = null;
	Long ipiRoleId = null;
	Long ipiRightTypeId = null;

	if (StringUtils.isNotEmpty(affiliationRow.getCreationClass())) {
	    String creationClass = affiliationRow.getCreationClass();
	    creationClass = StringUtils.trimToNull(creationClass);
	    if (null != creationClass) {
		creationClassId = ipCodeMapper.getCreationClassIdByCode(creationClass);
		if (null == creationClassId) {
		    return errorCode = WccIpImportExceptionCodeEnum.UNKNOWN_CREATION_CLASS;
		}
	    }
	} else {
	    return errorCode = WccIpImportExceptionCodeEnum.UNKNOWN_CREATION_CLASS;
	}

	if (StringUtils.isNotEmpty(affiliationRow.getRightType())) {
	    String ipiRightType = affiliationRow.getRightType();
	    ipiRightType = StringUtils.trimToNull(ipiRightType);
	    if (null != ipiRightType) {
		ipiRightTypeId = ipCodeMapper.getIpiRightTypeByCode(ipiRightType);
		if (null == ipiRightTypeId) {
		    return errorCode = WccIpImportExceptionCodeEnum.UNKNOWN_IPI_RIGHT_TYPE;
		}
	    }
	} else {
	    return errorCode = WccIpImportExceptionCodeEnum.UNKNOWN_IPI_RIGHT_TYPE;
	}

	if (StringUtils.isNotEmpty(affiliationRow.getRole())) {
	    String ipiRole = affiliationRow.getRole();
	    ipiRole = StringUtils.trimToNull(ipiRole);
	    if (null != ipiRole) {
		ipiRoleId = ipCodeMapper.getIpiRoleByCode(ipiRole);
		if (null == ipiRoleId) {
		    return errorCode = WccIpImportExceptionCodeEnum.UNKNOWN_IPI_ROLE;
		}
	    }
	} else {
	    return errorCode = WccIpImportExceptionCodeEnum.UNKNOWN_IPI_ROLE;
	}

	// Affiliation Line Context Validation
	IpiRoleFlat ipiRole = ipCodeMapper.getIpiRoleById(ipiRoleId);
	IpiRightTypeFlat ipiRightType = ipCodeMapper.getIpiRightTypeById(ipiRightTypeId);

	if (!ipiRole.getFkCcList().contains(creationClassId)) {
	    return errorCode = WccIpImportExceptionCodeEnum.AFFILIATION_LINE_CONTEXT;
	}

	if (!ipiRightType.getFkCcList().contains(creationClassId)) {
	    return errorCode = WccIpImportExceptionCodeEnum.AFFILIATION_LINE_CONTEXT;
	}

	// Affiliation Mixed scenario Validation
	Long fkIrtAll = ipCodeMapper.getIdIpiRightTypeAll();

	for (AffiliationDomain ad : adList) {
	    if (!ad.getFkCreationClass().equals(creationClassId)) {
		continue;
	    }
	    if (!ad.getFkIpiRole().equals(ipiRoleId)) {
		continue;
	    }

	    if (ipiRightTypeId.equals(fkIrtAll)) {
		if (!ad.getFkIpiRightType().equals(fkIrtAll)) {
		    return errorCode = WccIpImportExceptionCodeEnum.AFFILIATION_MIXED_SCENARIO;
		}
	    } else {
		if (ad.getFkIpiRightType().equals(fkIrtAll)) {
		    return errorCode = WccIpImportExceptionCodeEnum.AFFILIATION_MIXED_SCENARIO;
		}
	    }
	}

	AffiliationDomain newAd = new AffiliationDomain();
	newAd.setFkCreationClass(creationClassId);
	newAd.setFkIpiRole(ipiRoleId);
	newAd.setFkIpiRightType(ipiRightTypeId);
	adList.add(newAd);

	Date dateMin = null;
	Date dateMax = null;

	try {
	    dateMin = ft.parse(ConstantUtils.MIN_DATE);
	    dateMax = ft.parse(ConstantUtils.MAX_DATE);
	} catch (ParseException e) {
	    LOGGER.error("Error", e);
	}

	Date affiliationDate = null;
	if (StringUtils.isNotEmpty(affiliationRow.getAffiliationFrom())) {
	    String startDate = affiliationRow.getAffiliationFrom();
	    startDate = StringUtils.trimToNull(startDate);
	    try {
		if (null != startDate) {
		    affiliationDate = ft.parse(startDate);
		    if (!WccUtils.isValidDate(startDate) || affiliationDate.compareTo(dateMin) < 0 || affiliationDate.compareTo(dateMax) > 0) {
			return errorCode = WccIpImportExceptionCodeEnum.AFFILIATION_FROM_FORMAT_ERROR;
		    }
		}
	    } catch (ParseException e) {
		LOGGER.error("Errore", e);
		return errorCode = WccIpImportExceptionCodeEnum.AFFILIATION_FROM_FORMAT_ERROR;
	    }
	} else {
	    return errorCode = WccIpImportExceptionCodeEnum.AFFILIATION_FROM_FORMAT_ERROR;
	}

	if (StringUtils.isNotEmpty(affiliationRow.getAffiliationTo())) {
	    String endDate = affiliationRow.getAffiliationTo();
	    endDate = StringUtils.trimToNull(endDate);
	    try {
		if (null != endDate) {
		    Date date = ft.parse(endDate);
		    if (date.compareTo(dateMin) < 0 || date.compareTo(dateMax) > 0) {
			return errorCode = WccIpImportExceptionCodeEnum.AFFILIATION_TO_FORMAT_ERROR;
		    }
		    if (null != date) {
			if (date.compareTo(affiliationDate) < 0) {
			    return errorCode = WccIpImportExceptionCodeEnum.INVALID_AFFILIATION_TO;
			}
		    }
		}
	    } catch (ParseException e) {
		LOGGER.error("Error", e);
		return errorCode = WccIpImportExceptionCodeEnum.AFFILIATION_TO_FORMAT_ERROR;
	    }
	}

	if (StringUtils.isNotEmpty(affiliationRow.getSignatureDate())) {
	    String signatureDate = affiliationRow.getSignatureDate();
	    signatureDate = StringUtils.trimToNull(signatureDate);
	    try {
		if (null != signatureDate) {
		    Date date = ft.parse(signatureDate);
		    if (!WccUtils.isValidDate(signatureDate) || date.compareTo(dateMin) < 0 || date.compareTo(dateMax) > 0) {
			return errorCode = WccIpImportExceptionCodeEnum.SIGNATURE_DATE_FORMAT_ERROR;
		    }
		}

	    } catch (ParseException e) {
		LOGGER.error("Error", e);
		return errorCode = WccIpImportExceptionCodeEnum.SIGNATURE_DATE_FORMAT_ERROR;
	    }
	}

	if (StringUtils.isNotEmpty(affiliationRow.getShare())) {
	    String shareStr = affiliationRow.getShare();
	    shareStr = StringUtils.trimToNull(shareStr);
	    try {
		if (null != shareStr) {
		    BigDecimal share = new BigDecimal(shareStr);
		    if (null == share || share.compareTo(ConstantUtils.ONE_HUNDRED) > 0 || share.compareTo(ConstantUtils.ZERO) < 0 || Math.max(0, share.stripTrailingZeros().scale()) > 2) {
			return errorCode = WccIpImportExceptionCodeEnum.SHARE_FORMAT_ERROR;
		    }
		}
	    } catch (NumberFormatException e) {
		return errorCode = WccIpImportExceptionCodeEnum.SHARE_FORMAT_ERROR;
	    }
	}

	if (StringUtils.isNotEmpty(affiliationRow.getTerritory())) {
	    String territory = affiliationRow.getTerritory();
	    territory = StringUtils.trimToNull(territory);
	    try {
		if (territory.startsWith("I")) {
		    territory = TerritoryFormulaUtils.decodeTisnFormula(territory, ipCodeMapper.getTerritoryList());
		}
		TerritoryHierarchyVO terrHierarchy = ipCodeMapper.getTerritoryHierarchy();
		TerritoryFormulaValidationResultEnum formulaValidRes = TerritoryFormulaUtils.validateTerritoryFormula(terrHierarchy, territory);
		affiliationRow.setTerritory(territory);
		switch (formulaValidRes) {
		    case INVALID_TERRITORY_CODE:
			return errorCode = WccIpImportExceptionCodeEnum.TERRITORY_UNKNOWN_ERROR;
		    case INVALID_FORMULA_SYNTAX:
			return errorCode = WccIpImportExceptionCodeEnum.TERRITORY_UNKNOWN_ERROR;
		    case INVALID_TERRITORY_CHAIN:
			return errorCode = WccIpImportExceptionCodeEnum.TERRITORY_CHAIN_ERROR;
		    default:
			break;
		}

	    } catch (WccException e) {
		LOGGER.error("Error in " + WccUtils.getMethodName(), e);
		return errorCode = WccIpImportExceptionCodeEnum.TERRITORY_UNKNOWN_ERROR;
	    }
	}

	if (StringUtils.isNotEmpty(affiliationRow.getCmo())) {
	    String cmo = affiliationRow.getCmo();
	    cmo = StringUtils.trimToNull(cmo);
	    if (null != cmo) {
		Long targetCmoId = ipCodeMapper.getCmoIdByCode(cmo);
		if (null == targetCmoId) {
		    return errorCode = WccIpImportExceptionCodeEnum.CMO_UNKNOWN_ERROR;
		}
	    } else {
		return errorCode = WccIpImportExceptionCodeEnum.CMO_EMPTY_ERROR;
	    }
	} else {
	    return errorCode = WccIpImportExceptionCodeEnum.CMO_EMPTY_ERROR;
	}

	errorCode = AffiliationHelper.checkDomainShare(acm, affiliationRow);

	if (WccIpImportExceptionCodeEnum.AFFILIATION_CONTEXT_CONFLICT.equals(errorCode)) {
	    affiliationRow.setDummyInfo(errorCode.getCode());
	    return WccIpImportExceptionCodeEnum.NO_ERROR;
	}

	return errorCode;
    }

    private boolean checkIfExistGroupName(IpRow groupName, Name name) {
	name.setFirstName(StringUtils.trimToEmpty(name.getFirstName()));
	groupName.setFirstName(StringUtils.trimToEmpty(groupName.getFirstName()));

	if (StringUtils.equalsIgnoreCase(groupName.getFirstName(), name.getFirstName())
		&& StringUtils.equalsIgnoreCase(groupName.getLastCompanyName(), name.getName())
		&& StringUtils.equalsIgnoreCase(groupName.getNameType(), name.getNameType())) {
	    return true;
	}
	return false;
    }
}
