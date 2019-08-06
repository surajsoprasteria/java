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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.import_model.IpRow;
import org.wipo.connect.common.import_model.IpRowTypeEnum;
import org.wipo.connect.common.import_model.TransactionTypeEnum;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.ConstantUtils;
import org.wipo.connect.common.utils.ConversionUtils;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.enumerator.WccIpImportExceptionCodeEnum;
import org.wipo.connect.shared.exchange.dto.impl.Affiliation;
import org.wipo.connect.shared.exchange.dto.impl.AffiliationDomain;
import org.wipo.connect.shared.exchange.dto.impl.Cmo;
import org.wipo.connect.shared.exchange.dto.impl.InterestedParty;
import org.wipo.connect.shared.exchange.dto.impl.InterestedPartyIdentifierFlat;
import org.wipo.connect.shared.exchange.dto.impl.Name;

@Service
public class IpRowLoaderImpl implements IpRowLoader {

    @Autowired
    private IpCodeMapper ipCodeMapper;

    @Autowired
    private ImportIpDAOHelper importIpDAOHelper;

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(IpRowLoaderImpl.class);

    private SimpleDateFormat ft;

    @Override
    @PostConstruct
    public void init() {
	ft = new SimpleDateFormat(ConversionUtils.DATE_STAMP);
	ft.setLenient(false);
    }

    @Override
    public InterestedParty load(TransactionTypeEnum transaction, List<IpRow> entityRows, String dataOrigin) throws WccInterestedPartyImportException, ParseException {

	Map<String, List<IpRow>> toFillMap = entityRows.stream().collect(Collectors.groupingBy(row -> row.getRowType()));

	InterestedParty ip = fillInterestedParty(toFillMap.get(IpRowTypeEnum.MA.name()).get(0), new InterestedParty());
	if (TransactionTypeEnum.INSERT.equals(transaction)) {
	    ip.setCmoOriginCode(dataOrigin);
	}

	for (Entry<String, List<IpRow>> row : toFillMap.entrySet()) {

	    if (StringUtils.equalsIgnoreCase(row.getKey(), IpRowTypeEnum.NA.name())) {
		ip.setNameList(fillName(row.getValue()));
	    } else if (StringUtils.equalsIgnoreCase(row.getKey(), IpRowTypeEnum.ID.name())) {
		ip.getInterestedPartyIdentifierFlatList().addAll(fillIdentifier(row.getValue()));
	    } else if (StringUtils.equalsIgnoreCase(row.getKey(), IpRowTypeEnum.AF.name())) {
		Long idInterestedParty = null;
		if (TransactionTypeEnum.UPDATE.equals(transaction)) {
		    idInterestedParty = importIpDAOHelper.findInterestedPartyIdByMainId(ip.getMainId());
		}
		ip.setAffiliationList(loadAffiliationList(row.getValue(), idInterestedParty));
	    }
	}

	return ip;
    }

    private InterestedParty fillInterestedParty(IpRow ipRow, InterestedParty ip) throws ParseException {
	Date dateMin = null;
	Date dateMax = null;
	ip.setMainId(ipRow.getMainId());
	ip.setType(StringUtils.isEmpty(ipRow.getType()) ? ip.getType() : ipRow.getType());

	if (null != ipRow.getCitizenshipList() && !ipRow.getCitizenshipList().isEmpty()) {
	    for (String code : ipRow.getCitizenshipList()) {
		code = StringUtils.trimToNull(code);
		if (null != code) {
		    Long idTerritory = ipCodeMapper.getTerritoryCountryIdByCode(code);
		    if (idTerritory != null) {
			ip.getCitizenshipIdList().add(idTerritory);
		    }
		}
	    }
	}

	if (!StringUtils.isEmpty(ipRow.getSex())) {
	    String sex = ipRow.getSex();
	    sex = StringUtils.trimToNull(sex);
	    if (null != sex) {
		if ((sex.length() == 1) && (sex.equalsIgnoreCase(ConstantUtils.M) || sex.equalsIgnoreCase(ConstantUtils.F))) {
		    ip.setSex(sex);
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
	if (!StringUtils.isEmpty(ipRow.getBirthDate())) {
	    String birtDateStr = ipRow.getBirthDate();
	    birtDateStr = StringUtils.trimToNull(birtDateStr);
	    try {
		if (null != birtDateStr) {
		    birthDate = ft.parse(birtDateStr);
		    if (WccUtils.isValidDate(birtDateStr) && birthDate.compareTo(dateMin) > 0 && birthDate.compareTo(dateMax) < 0) {
			ip.setBirthDate(birthDate);
		    }
		}
	    } catch (ParseException e) {
		LOGGER.error("Error", e);
	    }
	}

	if (!StringUtils.isEmpty(ipRow.getDeathDate()) && birthDate != null) {
	    String deathDateStr = ipRow.getDeathDate();
	    deathDateStr = StringUtils.trimToNull(deathDateStr);
	    try {
		if (null != deathDateStr) {
		    Date date = ft.parse(deathDateStr);
		    if (WccUtils.isValidDate(deathDateStr) && date.compareTo(dateMin) > 0 && date.compareTo(dateMax) < 0 && date.compareTo(birthDate) > 0) {
			ip.setDeathDate(date);
		    }

		}
	    } catch (ParseException e) {
		LOGGER.error("Error", e);
	    }
	}

	if (!StringUtils.isEmpty(ipRow.getCountryOfBirth())) {
	    String birthcountry = ipRow.getCountryOfBirth();
	    birthcountry = StringUtils.trimToNull(birthcountry);
	    if (null != birthcountry) {
		Long idTerritory = ipCodeMapper.getTerritoryCountryIdByCode(birthcountry);
		if (idTerritory != null) {
		    ip.setFkBirthCountry(idTerritory);
		}
	    }
	}

	// TODO: WCONNECT-63 ------------------------------
	if (StringUtils.isNotEmpty(ipRow.getBirthPlace())) {
	    String birthPlace = StringUtils.trimToNull(ipRow.getBirthPlace());
	    if (null != birthPlace) {
		ip.setBirthPlace(birthPlace);
	    }
	} // ----------------------------------------------

	return ip;
    }

    private List<Name> fillName(List<IpRow> nameRowList) {
	List<Name> nameList = new ArrayList<>();
	nameRowList.forEach(nameRow -> {
	    if (nameRow.getErrorCode().equalsIgnoreCase(WccIpImportExceptionCodeEnum.NO_ERROR.getCode())) {
		Name name = new Name();
		name.setFirstName(nameRow.getFirstName());
		name.setName(nameRow.getLastCompanyName());
		name.setNameType(nameRow.getNameType());
		name.setMainId(nameRow.getMainId());
		nameList.add(name);
	    }
	});

	return nameList;
    }

    private List<InterestedPartyIdentifierFlat> fillIdentifier(List<IpRow> identifierRowList) {
	List<InterestedPartyIdentifierFlat> identifierList = new ArrayList<>();
	identifierRowList.forEach(identifierRow -> {
	    if (identifierRow.getErrorCode().equalsIgnoreCase(WccIpImportExceptionCodeEnum.NO_ERROR.getCode())) {
		InterestedPartyIdentifierFlat identifier = new InterestedPartyIdentifierFlat();
		identifier.setCode(identifierRow.getType());
		identifier.setValue(identifierRow.getValue());
		identifierList.add(identifier);
	    }
	});

	return identifierList;
    }

    private List<Affiliation> loadAffiliationList(List<IpRow> affiliationRowList, Long idInterestedParty) throws WccInterestedPartyImportException {
	List<IpRow> affiliationRowValidList = new ArrayList<>();
	List<IpRow> affiliationRowConflictList = new ArrayList<>();

	affiliationRowList.forEach(r -> {
	    if (StringUtils.equalsIgnoreCase(r.getDummyInfo(), WccIpImportExceptionCodeEnum.AFFILIATION_CONTEXT_CONFLICT.getCode())) {
		affiliationRowConflictList.add(r);
	    } else if (StringUtils.equalsIgnoreCase(r.getErrorCode(), WccIpImportExceptionCodeEnum.NO_ERROR.getCode())) {
		affiliationRowValidList.add(r);
	    }
	});

	if (affiliationRowValidList.isEmpty() && affiliationRowConflictList.isEmpty()) {
	    return new ArrayList<>();
	}

	Map<String, Affiliation> affiliationMap = new HashMap<>();

	if (idInterestedParty != null) {
	    List<Affiliation> affiliationList = importIpDAOHelper.findAffiliationByIp(idInterestedParty);
	    affiliationList.forEach(a -> {
		String checksum = AffiliationHelper.generateAffiliationChecksum(a, false, true, true);
		a.setId(null);
		a.getAffiliationDomainList().forEach(ad -> ad.setId(null));
		affiliationMap.put(checksum, a);

	    });

	    if (!affiliationRowConflictList.isEmpty()) {
		Map<String, Set<String>> affiliationConflictMap = new HashMap<>();
		affiliationMap.forEach((k, v) -> {
		    String checksumConflict = AffiliationHelper.generateAffiliationChecksum(v, false, true, false);
		    Set<String> validSet = affiliationConflictMap.getOrDefault(checksumConflict, new HashSet<>());
		    validSet.add(k);
		    affiliationConflictMap.put(checksumConflict, validSet);
		});

		// List on affiliation with different end date (import update only)
		for (IpRow affiliationRow : affiliationRowConflictList) {

		    String checksumConflict = AffiliationHelper.generateAffiliationChecksum(affiliationRow, false, true, false);

		    if (affiliationConflictMap.containsKey(checksumConflict)) {
			Set<String> checksumSet = affiliationConflictMap.get(checksumConflict);
			for (String checksum : checksumSet) {
			    Affiliation affiliation = affiliationMap.remove(checksum);
			    if (affiliation == null) {
				continue;
			    }

			    affiliation.getAffiliationDomainList().removeIf(ad -> {

				// Create a new affiliation with only one row of affiliation domain when end date has changed
				if (StringUtils.equalsIgnoreCase(ad.getCreationClassCode(), affiliationRow.getCreationClass())
					&& StringUtils.equalsIgnoreCase(ad.getIpiRightTypeCode(), affiliationRow.getRightType())
					&& StringUtils.equalsIgnoreCase(ad.getIpiRoleCode(), affiliationRow.getRole())) {

				    Affiliation splittedAffiliation = SerializationUtils.clone(affiliation);
				    AffiliationDomain splittedAffiliationDomain = SerializationUtils.clone(ad);
				    splittedAffiliation.setId(null);
				    splittedAffiliationDomain.setId(null);
				    splittedAffiliationDomain.setFkAffiliation(null);
				    splittedAffiliation.getAffiliationDomainList().clear();
				    splittedAffiliation.getAffiliationDomainList().add(splittedAffiliationDomain);

				    splittedAffiliation.setEndDate(parseDate(affiliationRow.getAffiliationTo()));

				    String splittedChecksum = AffiliationHelper.generateAffiliationChecksum(splittedAffiliation, true, true, true);
				    affiliationMap.put(splittedChecksum, splittedAffiliation);

				    return true;
				}

				return false;

			    });

			    if (!affiliation.getAffiliationDomainList().isEmpty()) {
				affiliationMap.put(checksum, affiliation);
			    }
			}
		    }
		}
	    }
	}

	for (IpRow affiliationRow : affiliationRowValidList) {
	    String checksum = AffiliationHelper.generateAffiliationChecksum(affiliationRow, false, true, true);
	    Affiliation affiliation;
	    if (affiliationMap.containsKey(checksum)) {
		affiliation = affiliationMap.get(checksum);
		fillAffiliationDomainAndSumShare(affiliationRow, affiliation);
	    } else {
		affiliation = fillAffiliation(affiliationRow);
	    }
	    affiliationMap.put(checksum, affiliation);
	}

	return AffiliationHelper.implodeIpiRightType(affiliationMap.values(), ipCodeMapper);

    }

    private Affiliation fillAffiliation(IpRow affiliationRow) throws WccInterestedPartyImportException {

	Affiliation affiliation = new Affiliation();

	affiliation.setStartDate(parseDate(affiliationRow.getAffiliationFrom()));
	affiliation.setEndDate(parseDate(affiliationRow.getAffiliationTo()));
	affiliation.setSignatureDate(parseDate(affiliationRow.getSignatureDate()));

	affiliation.setShareValue(new BigDecimal(affiliationRow.getShare()));
	affiliation.setTerritoryFormula(affiliationRow.getTerritory());
	Cmo cmo = ipCodeMapper.getCmoByCode(affiliationRow.getCmo());
	affiliation.setCmo(cmo);

	affiliation.setShareValue(new BigDecimal(affiliationRow.getShare()));
	fillAffiliationDomainAndSumShare(affiliationRow, affiliation);

	return affiliation;
    }

    private void fillAffiliationDomainAndSumShare(IpRow affiliationRow, Affiliation affiliation) throws WccInterestedPartyImportException {

	for (AffiliationDomain ad : affiliation.getAffiliationDomainList()) {
	    if (StringUtils.equalsIgnoreCase(ad.getCreationClassCode(), affiliationRow.getCreationClass())
		    && StringUtils.equalsIgnoreCase(ad.getIpiRightTypeCode(), affiliationRow.getRightType())
		    && StringUtils.equalsIgnoreCase(ad.getIpiRoleCode(), affiliationRow.getRole())) {

		return;
	    }
	}

	AffiliationDomain affiliationDomain = new AffiliationDomain();

	affiliationDomain.setCreationClassCode(affiliationRow.getCreationClass());
	affiliationDomain.setIpiRightTypeCode(affiliationRow.getRightType());
	affiliationDomain.setIpiRoleCode(affiliationRow.getRole());

	affiliationDomain.setFkCreationClass(ipCodeMapper.getCreationClassIdByCode(affiliationRow.getCreationClass()));
	affiliationDomain.setFkIpiRightType(ipCodeMapper.getIpiRightTypeByCode(affiliationRow.getRightType()));
	affiliationDomain.setFkIpiRole(ipCodeMapper.getIpiRoleByCode(affiliationRow.getRole()));

	affiliation.getAffiliationDomainList().add(affiliationDomain);
    }

    private Date parseDate(String date) {
	try {
	    return !StringUtils.isEmpty(date) ? ft.parse(date) : null;
	} catch (ParseException e) {
	    LOGGER.error("Error parse date: {}", e.getMessage());
	    return null;
	}
    }

}
