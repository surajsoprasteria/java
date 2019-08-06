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

package org.wipo.connect.shared.business.utils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.utils.ConversionUtils;
import org.wipo.connect.shared.exchange.dto.impl.Affiliation;
import org.wipo.connect.shared.exchange.dto.impl.AffiliationDomain;
import org.wipo.connect.shared.exchange.dto.impl.InterestedParty;
import org.wipo.connect.shared.exchange.dto.impl.InterestedPartyIdentifierFlat;
import org.wipo.connect.shared.exchange.dto.impl.Name;
import org.wipo.connect.shared.exchange.enumerator.IdentifierTypeEnum;

public class InterestedPartyUtils {

    /** The Constant KEY_SEP. */
    private static final String KEY_SEP = "\u0000\u0000\u0000";

    /** The decimal formatter. */
    private static NumberFormat decimalFormatter = new DecimalFormat(ConversionUtils.DECIMAL_PATTERN);

    /**
     * Instantiates a new interested party utils.
     */
    private InterestedPartyUtils() {
	super();
    }

    public static boolean compareLocalAndSharedIP(InterestedParty ipLocal, InterestedParty ipShared) throws WccException {

	try {
	    // create a comparator to check i the interested parties are equal
	    EqualsBuilder eqIP = new EqualsBuilder();
	    boolean isNamesOk;

	    // if not null check BirthDate
	    if (ipLocal.getBirthDate() != null) {
		eqIP.append(ipLocal.getBirthDate(), ipShared.getBirthDate());
	    }

	    // if not null check Sex
	    if (ipLocal.getSex() != null) {
		eqIP.append(ipLocal.getSex().toUpperCase(), StringUtils.upperCase(ipShared.getSex()));
	    }

	    // if not null check MaritalStatus
	    if (ipLocal.getMaritalStatus() != null) {
		eqIP.append(ipLocal.getMaritalStatus().toUpperCase(), StringUtils.upperCase(ipShared.getMaritalStatus()));
	    }

	    // if not null check BirthPlace
	    if (ipLocal.getBirthPlace() != null) {
		eqIP.append(ipLocal.getBirthPlace().toUpperCase(), StringUtils.upperCase(ipShared.getBirthPlace()));
	    }

	    // if not null check BirthState
	    if (ipLocal.getBirthState() != null) {
		eqIP.append(ipLocal.getBirthState().toUpperCase(), StringUtils.upperCase(ipShared.getBirthState()));
	    }

	    // if not null check BirthCountry
	    if (ipLocal.getFkBirthCountry() != null) {
		eqIP.append(ipLocal.getFkBirthCountry(), ipShared.getFkBirthCountry());
	    }

	    // if not null check IP type
	    if (ipLocal.getType() != null) {
		eqIP.append(ipLocal.getType(), ipShared.getType());
	    }

	    // deep inspect local and shared collections of names
	    // local name list must be a subset of the shared list
	    if (ipLocal.getNameList().size() <= ipShared.getNameList().size()) {
		isNamesOk = true;
		for (Name nameLocal : ipLocal.getNameList()) {
		    boolean nameFound = false;
		    for (Name nameShared : ipShared.getNameList()) {

			nameFound = StringUtils.equalsIgnoreCase(nameLocal.getName(), nameShared.getName()) && StringUtils.equalsIgnoreCase(nameLocal.getFirstName(), nameShared.getFirstName())
				&& StringUtils.equalsIgnoreCase(nameLocal.getNameType(), nameShared.getNameType());

			if (nameFound) {
			    break;
			}

		    }

		    isNamesOk = isNamesOk && nameFound;
		}
	    } else {
		isNamesOk = false;
	    }

	    // deep inspect local and shared collections of affiliations
	    // local affiliation list must be a subset of the shared list
	    boolean isAffiliationOk = compareLocalSharedAffiliation(ipLocal.getAffiliationList(), ipShared.getAffiliationList());

	    return eqIP.isEquals() && isNamesOk && isAffiliationOk;

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

    }

    private static boolean compareLocalSharedAffiliation(List<Affiliation> affLocalList, List<Affiliation> affSharedList) throws ParseException {

	boolean validAffiliation = true;

	List<String> keyListShared = new ArrayList<>();

	for (Affiliation aff : affSharedList) {
	    for (AffiliationDomain domain : aff.getAffiliationDomainList()) {
		keyListShared.add(getAffiliationKey(aff, domain));
	    }
	}

	affLoop: for (Affiliation aff : affLocalList) {
	    for (AffiliationDomain domain : aff.getAffiliationDomainList()) {
		String localKey = getAffiliationKey(aff, domain);
		if (!keyListShared.contains(localKey)) {
		    validAffiliation = false;
		    break affLoop;
		}
	    }
	}

	return validAffiliation;

    }

    public static InterestedParty mergeIPSharedChanges(InterestedParty ipLocal, InterestedParty ipShared) throws IllegalAccessException, InstantiationException, InvocationTargetException,
	    NoSuchMethodException {

	// clone objects to be sure to not modify the originals
	ipLocal = SerializationUtils.clone(ipLocal);
	ipShared = SerializationUtils.clone(ipShared);

	Iterator<InterestedPartyIdentifierFlat> sharedIdIterator = ipShared.getInterestedPartyIdentifierFlatList().iterator();
	while (sharedIdIterator.hasNext()) {
	    InterestedPartyIdentifierFlat aux = sharedIdIterator.next();
	    if (StringUtils.equals(aux.getCode(), IdentifierTypeEnum.WIPO_CONNECT_LOCAL_ID.name())) {

		sharedIdIterator.remove();
	    }
	}

	if (!StringUtils.equals(ipLocal.getMainId(), ipShared.getMainId())) {
	    InterestedPartyIdentifierFlat oldId = new InterestedPartyIdentifierFlat();
	    oldId.setCode(IdentifierTypeEnum.WIPO_CONNECT_LOCAL_ID.name());
	    oldId.setValue(ipLocal.getMainId());
	    ipShared.getInterestedPartyIdentifierFlatList().add(oldId);

	    ipLocal.setMainId(ipShared.getMainId());
	}

	// if null merge BirthDate shared value
	if (ipLocal.getBirthDate() == null) {
	    ipLocal.setBirthDate(ipShared.getBirthDate());
	}

	// if null merge Sex shared value
	if (ipLocal.getSex() == null) {
	    ipLocal.setSex(ipShared.getSex());
	}

	// if null merge MaritalStatus shared value
	if (ipLocal.getMaritalStatus() == null) {
	    ipLocal.setMaritalStatus(ipShared.getMaritalStatus());
	}

	// if null merge BirthPlace shared value
	if (ipLocal.getBirthPlace() == null) {
	    ipLocal.setBirthPlace(ipShared.getBirthPlace());
	}

	// if null merge BirthState shared value
	if (ipLocal.getBirthState() == null) {
	    ipLocal.setBirthState(ipShared.getBirthState());
	}

	// if null merge BirthCountry shared value
	if (ipLocal.getFkBirthCountry() == null) {
	    ipLocal.setFkBirthCountry(ipShared.getFkBirthCountry());
	}

	// if null merge IP type shared value
	if (ipLocal.getType() == null) {
	    ipLocal.setType(ipShared.getType());
	}

	// merge ip identifiers
	List<InterestedPartyIdentifierFlat> ipNewIds = new ArrayList<>();
	for (InterestedPartyIdentifierFlat sidf : ipShared.getInterestedPartyIdentifierFlatList()) {

	    boolean found = false;
	    for (InterestedPartyIdentifierFlat lidf : ipLocal.getInterestedPartyIdentifierFlatList()) {
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(lidf.getCode(), sidf.getCode());
		eb.append(lidf.getValue(), sidf.getValue());

		found = found || eb.isEquals();
	    }

	    // if the id is present only in the shared version, add it to the local
	    if (!found) {
		InterestedPartyIdentifierFlat auxId = new InterestedPartyIdentifierFlat();
		auxId.setCode(sidf.getCode());
		auxId.setValue(sidf.getValue());
		ipNewIds.add(auxId);
	    }
	}
	ipLocal.getInterestedPartyIdentifierFlatList().addAll(ipNewIds);

	// get new names and new names ids
	mergeNameSharedChanges(ipLocal.getNameList(), ipShared.getNameList());

	// merge affiliations
	ipLocal.getAffiliationList().clear();
	for (Affiliation aff : ipShared.getAffiliationList()) {
	    Affiliation aux = (Affiliation) BeanUtils.cloneBean(aff);
	    aux.setFkInterestedParty(ipLocal.getId());
	    ipLocal.getAffiliationList().add(aux);
	}

	return ipLocal;
    }

    public static void mergeNameSharedChanges(List<Name> localNames, List<Name> sharedNames) {

	Map<String, Name> localNamesMap = new HashMap<>();
	Map<String, Name> sharedNamesMap = new HashMap<>();

	// generate name maps
	for (Name localName : localNames) {
	    localNamesMap.put(getNameKey(localName), localName);
	}
	for (Name sharedlName : sharedNames) {
	    sharedNamesMap.put(getNameKey(sharedlName), sharedlName);
	}

	// find local names to update
	for (String key : localNamesMap.keySet()) {
	    Name localName = localNamesMap.get(key);
	    Name sharedName;
	    if (sharedNamesMap.containsKey(key)) {
		sharedName = sharedNamesMap.get(key);

		// if mainId name of share end local are not equals put in mainId of local the mainId of shared
		if (!sharedName.getMainId().equals(localName.getMainId())) {
		    localName.setMainId(sharedName.getMainId());
		}
	    }
	}

	// find shared names to add
	for (String key : sharedNamesMap.keySet()) {
	    Name localName;
	    Name sharedName = sharedNamesMap.get(key);
	    if (!localNamesMap.containsKey(key)) {
		localName = SerializationUtils.clone(sharedName);
		localNames.add(localName);
	    }
	}

    }

    private static String getAffiliationKey(Affiliation affiliationHeader, AffiliationDomain affiliationDomain) throws ParseException {
	StringBuilder sb = new StringBuilder();

	sb.append(decimalFormatter.format(ObjectUtils.defaultIfNull(affiliationHeader.getShareValue(), BigDecimal.ZERO)));
	sb.append(KEY_SEP);

	sb.append(StringUtils.defaultString(affiliationHeader.getTerritoryFormula()));
	sb.append(KEY_SEP);
	sb.append(KEY_SEP);
	sb.append(KEY_SEP);

	sb.append(affiliationDomain.getFkCreationClass());
	sb.append(KEY_SEP);
	sb.append(affiliationDomain.getFkIpiRightType());
	sb.append(KEY_SEP);
	sb.append(affiliationDomain.getFkIpiRole());

	return DigestUtils.md5Hex(sb.toString().toLowerCase());
    }

    public static String getNameKey(Name name) {
	return StringUtils.defaultString(name.getName() == null ? null : name.getName().toLowerCase()) + KEY_SEP
		+ StringUtils.defaultString(name.getFirstName() == null ? null : name.getFirstName().toLowerCase()) + KEY_SEP + StringUtils.defaultString(name.getNameType().toLowerCase());
    }

    public static boolean compareIps(InterestedParty ipSrc, InterestedParty ipDest, boolean isInternationalCheck) {
	EqualsBuilder eb = new EqualsBuilder();
	if (isInternationalCheck) {

	    eb.append(ipSrc.getBirthDate(), ipDest.getBirthDate());
	    eb.append(ipSrc.getDeathDate(), ipDest.getDeathDate());
	    eb.append(ipSrc.getSex(), ipDest.getSex());
	    eb.append(ipSrc.getMaritalStatus(), ipDest.getMaritalStatus());
	    eb.append(ipSrc.getType(), ipDest.getType());
	    eb.append(ipSrc.getBirthPlace(), ipDest.getBirthPlace());
	    eb.append(ipSrc.getNameList().size(), ipDest.getNameList().size());
	    eb.append(ipSrc.getBirthCountryCode(), ipDest.getBirthCountryCode());
	    eb.append(ipSrc.getCitizenshipCodeList().size(), ipDest.getCitizenshipCodeList().size());

	    for (String code : ipDest.getCitizenshipCodeList()) {

		boolean found = false;
		for (String codeSrc : ipSrc.getCitizenshipCodeList()) {
		    EqualsBuilder ebCitizenship = new EqualsBuilder();
		    ebCitizenship.append(code, codeSrc);
		    ebCitizenship.append(code, codeSrc);

		    found = found || ebCitizenship.isEquals();
		}
		eb.append(found, true);
	    }

	    eb.append(ipSrc.getNameList().size(), ipDest.getNameList().size());

	    for (Name savedName : ipSrc.getNameList()) {
		boolean found = false;
		for (Name toSaveName : ipDest.getNameList()) {
		    EqualsBuilder ebName = new EqualsBuilder();
		    ebName.append(savedName.getName(), toSaveName.getName());
		    ebName.append(savedName.getFirstName(), toSaveName.getFirstName());
		    ebName.append(savedName.getNameType(), toSaveName.getNameType());

		    found = found || ebName.isEquals();
		}
		eb.append(found, true);
	    }

	} else {
	    // TODO: load exclusion field list

	}

	return eb.isEquals();
    }

}
