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
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.wipo.connect.common.import_model.IpRow;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.ConstantUtils;
import org.wipo.connect.common.utils.ConversionUtils;
import org.wipo.connect.enumerator.WccIpImportExceptionCodeEnum;
import org.wipo.connect.shared.exchange.dto.impl.Affiliation;
import org.wipo.connect.shared.exchange.dto.impl.AffiliationDomain;
import org.wipo.connect.shared.exchange.dto.impl.Cmo;
import org.wipo.connect.shared.exchange.enumerator.IpiRightTypeEnum;

public class AffiliationHelper {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(AffiliationHelper.class);

    private final static String MAP_SEP = "\u0000\u0000\u0000";

    public static List<Affiliation> implodeIpiRightType(Collection<Affiliation> affiliationList, IpCodeMapper ipCodeMapper) {

	for (Affiliation affiliation : affiliationList) {
	    Map<String, List<String>> irpMap = new HashMap<>();

	    // Generate map for compare affiliated IpiRightType
	    for (AffiliationDomain ad : affiliation.getAffiliationDomainList()) {
		String key = ad.getCreationClassCode() + "#" + ad.getIpiRoleCode();
		if (irpMap.get(key) != null) {
		    List<String> stringList = irpMap.get(key);
		    stringList.add(ad.getIpiRightTypeCode());
		    irpMap.put(key, stringList);
		} else {
		    List<String> stringList = new ArrayList<>();
		    stringList.add(ad.getIpiRightTypeCode());
		    irpMap.put(key, stringList);
		}
	    }

	    // Compare affiliated IpiRightType and replace with ALL if necessary
	    for (Map.Entry<String, List<String>> entry : irpMap.entrySet()) {
		String key = entry.getKey();
		String cc = key.substring(0, key.indexOf("#"));
		String role = key.substring(key.indexOf("#") + 1, key.length());

		List<String> allIpiRightType = ipCodeMapper.getIrtCodeListByCc(cc);
		List<String> affiliatedIpiRightType = entry.getValue();

		if (affiliatedIpiRightType.containsAll(allIpiRightType)) {
		    Long idCc = ipCodeMapper.getCreationClassIdByCode(cc);
		    Long idIpiRole = ipCodeMapper.getIpiRoleByCode(role);
		    Long idIrtAll = ipCodeMapper.getIdIpiRightTypeAll();

		    // Remove affiliation domain
		    affiliation.getAffiliationDomainList().removeIf(ad -> (ad.getFkCreationClass().equals(idCc) && ad.getFkIpiRole().equals(idIpiRole)));

		    AffiliationDomain ad = new AffiliationDomain();
		    ad.setFkAffiliation(affiliation.getId());
		    ad.setFkCreationClass(idCc);
		    ad.setFkIpiRole(idIpiRole);
		    ad.setFkIpiRightType(idIrtAll);
		    ad.setCreationClassCode(cc);
		    ad.setIpiRoleCode(role);
		    ad.setIpiRightTypeCode(IpiRightTypeEnum.ALL.name());
		    affiliation.getAffiliationDomainList().add(ad);

		}
	    }
	}

	return affiliationList.stream().collect(Collectors.toList());
    }

    public static Affiliation convertRowToAffiliation(IpRow affiliationRow) throws WccInterestedPartyImportException {
	DateFormat sdf = new SimpleDateFormat(ConversionUtils.DATE_STAMP);
	sdf.setLenient(false);
	try {
	    Affiliation affiliation = new Affiliation();

	    Date startDate = StringUtils.isNotEmpty(affiliationRow.getAffiliationFrom()) ? sdf.parse(affiliationRow.getAffiliationFrom()) : null;
	    affiliation.setStartDate(startDate);
	    Date endDate = StringUtils.isNotEmpty(affiliationRow.getAffiliationTo()) ? sdf.parse(affiliationRow.getAffiliationTo()) : null;
	    affiliation.setEndDate(endDate);
	    Date signatureDate = StringUtils.isNotEmpty(affiliationRow.getSignatureDate()) ? sdf.parse(affiliationRow.getSignatureDate()) : null;
	    affiliation.setSignatureDate(signatureDate);

	    affiliation.setShareValue(new BigDecimal(affiliationRow.getShare()));
	    affiliation.setTerritoryFormula(affiliationRow.getTerritory());

	    Cmo cmo = new Cmo();
	    cmo.setCode(affiliationRow.getCmo());
	    affiliation.setCmo(cmo);

	    AffiliationDomain affiliationDomain = new AffiliationDomain();
	    affiliationDomain.setCreationClassCode(affiliationRow.getCreationClass());
	    affiliationDomain.setIpiRightTypeCode(affiliationRow.getRightType());
	    affiliationDomain.setIpiRoleCode(affiliationRow.getRole());

	    affiliation.getAffiliationDomainList().add(affiliationDomain);

	    return affiliation;

	} catch (ParseException e) {
	    LOGGER.error("Error", e);
	    throw new WccInterestedPartyImportException(WccIpImportExceptionCodeEnum.GENERIC_ROW_ERROR);
	}

    }

    public static String generateAffiliationChecksum(IpRow affiliationRow, boolean includeDomain, boolean includeShare, boolean includeEndDate) throws WccInterestedPartyImportException {
	return generateAffiliationChecksum(convertRowToAffiliation(affiliationRow), includeDomain, includeShare, includeEndDate);
    }

    public static String generateAffiliationChecksum(Affiliation affiliation, boolean includeDomain, boolean includeShare, boolean includeEndDate) {
	DateFormat sdf = new SimpleDateFormat(ConversionUtils.DATE_STAMP);
	sdf.setLenient(false);
	StringBuilder sb = new StringBuilder();

	sb.append(generateAffiliationString(affiliation, includeEndDate));

	if (includeDomain) {
	    for (AffiliationDomain ad : affiliation.getAffiliationDomainList()) {
		sb.append(generateDomainRowString(ad));
	    }
	}

	if (includeShare && null != affiliation.getShareValue()) {
	    sb.append(affiliation.getShareValue().setScale(2, RoundingMode.HALF_UP));
	    sb.append(MAP_SEP);
	}

	// generate MD5 Checksum of the string
	// String checksum = DigestUtils.md5Hex(sb.toString());
	return sb.toString();
    }

    private static String generateDomainRowString(AffiliationDomain affiliationDomain) {
	StringBuilder sb = new StringBuilder();

	// Append CreationClassCode
	if (affiliationDomain.getCreationClassCode() != null) {
	    sb.append(affiliationDomain.getCreationClassCode());
	}
	sb.append(MAP_SEP);

	// Append IpiRightType
	if (affiliationDomain.getIpiRightTypeCode() != null) {
	    sb.append(affiliationDomain.getIpiRightTypeCode());
	}
	sb.append(MAP_SEP);

	// Append IpiRole
	if (affiliationDomain.getIpiRoleCode() != null) {
	    sb.append(affiliationDomain.getIpiRoleCode());
	}
	sb.append(MAP_SEP);

	return sb.toString();
    }

    private static String generateAffiliationString(Affiliation affiliation, boolean includeEndDate) {
	DateFormat sdf = new SimpleDateFormat(ConversionUtils.DATE_STAMP);
	sdf.setLenient(false);
	StringBuilder sb = new StringBuilder();

	// Append Cmo Acronym
	if (affiliation.getCmo() != null) {
	    sb.append(affiliation.getCmo().getCode());
	}
	sb.append(MAP_SEP);

	// Append StartDate
	if (affiliation.getStartDate() != null) {
	    sb.append(sdf.format(affiliation.getStartDate()));
	}
	sb.append(MAP_SEP);

	// Append EndDate
	if (includeEndDate && affiliation.getEndDate() != null) {
	    sb.append(sdf.format(affiliation.getEndDate()));
	}
	sb.append(MAP_SEP);

	// Append SignatureDate
	if (affiliation.getSignatureDate() != null) {
	    sb.append(sdf.format(affiliation.getSignatureDate()));
	}
	sb.append(MAP_SEP);

	// Append territory formula
	if (StringUtils.isNotEmpty(affiliation.getTerritoryFormula())) {
	    sb.append(StringUtils.defaultString(affiliation.getTerritoryFormula()));
	}
	sb.append(MAP_SEP);

	return sb.toString();
    }

    public static WccIpImportExceptionCodeEnum checkDomainShare(AffiliationContextMap affContextMap, IpRow affiliationRow) {
	try {
	    Affiliation affiliation = convertRowToAffiliation(affiliationRow);
	    return checkDomainShare(affContextMap, affiliation);
	} catch (WccInterestedPartyImportException e) {
	    return WccIpImportExceptionCodeEnum.AFFILIATION_CONFLICT;
	}
    }

    public static WccIpImportExceptionCodeEnum checkDomainShare(AffiliationContextMap affContextMap, Affiliation affiliation) {

	if (affiliation.getAffiliationDomainList().isEmpty()) {
	    return WccIpImportExceptionCodeEnum.AFFILIATION_CONFLICT;
	}

	String affiliationContext = generateAffiliationString(affiliation, true);

	for (AffiliationDomain ad : affiliation.getAffiliationDomainList()) {
	    String domain = generateDomainRowString(ad);
	    BigDecimal sumForDomain = affContextMap.getSumInContext(affiliationContext, domain);
	    BigDecimal sum = BigDecimal.ZERO;
	    if (null != sumForDomain) {
		Date endDateContext = affContextMap.getEndDateInContext(affiliationContext, domain);
		if (ObjectUtils.compare(affiliation.getEndDate(), endDateContext) != 0) {
		    return WccIpImportExceptionCodeEnum.AFFILIATION_CONTEXT_CONFLICT;
		}

		sum = sumForDomain.add(affiliation.getShareValue());
		if (sum.compareTo(ConstantUtils.ONE_HUNDRED) > 0) {
		    return WccIpImportExceptionCodeEnum.AFFILIATION_CONFLICT;
		}
	    }
	    if (null != affContextMap.get(affiliationContext, domain)) {
		affContextMap.put(affiliationContext, domain, sum);
	    } else {
		affContextMap.put(affiliationContext, domain, affiliation.getShareValue());
	    }
	}

	return WccIpImportExceptionCodeEnum.NO_ERROR;

    }

}
