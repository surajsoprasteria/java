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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.mapping.DozerUtility;
import org.wipo.connect.common.utils.ConversionUtils;
import org.wipo.connect.shared.exchange.dto.impl.InterestedParty;
import org.wipo.connect.shared.exchange.dto.impl.InterestedPartyIdentifierFlat;
import org.wipo.connect.shared.exchange.dto.impl.Name;
import org.wipo.connect.shared.exchange.dto.impl.Title;
import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.dto.impl.WorkIdentifierFlat;
import org.wipo.connect.shared.exchange.enumerator.NameTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.TitleTypeCodeEnum;
import org.wipo.connect.shared.exchange.index.doc.InterestedPartyDoc;
import org.wipo.connect.shared.exchange.index.doc.WorkDoc;

/**
 * The Class DozerHelper.
 */
@Service
public class DozerHelper {

    /** The dozer utility. */
    @Autowired
    private DozerUtility dozerUtility;

    /**
     * To interested party doc.
     *
     * @param ipList
     *            the ip list
     * @return the list
     */
    public List<InterestedPartyDoc> toInterestedPartyDoc(List<InterestedParty> ipList) {
	List<InterestedPartyDoc> ipDocList = new ArrayList<InterestedPartyDoc>();

	// transform and add each element to the output list
	for (InterestedParty ip : ipList) {
	    InterestedPartyDoc ipDoc = toInterestedPartyDoc(ip);
	    ipDocList.add(ipDoc);
	}

	return ipDocList;
    }

    /**
     * To interested party doc.
     *
     * @param ip
     *            the ip
     * @return the interested party doc
     */
    public InterestedPartyDoc toInterestedPartyDoc(InterestedParty ip) {
	InterestedPartyDoc ipDoc;

	// fild name based mapping using dozer
	ipDoc = dozerUtility.map(ip, new InterestedPartyDoc());

	// add each IP identifier to the ids list
	for (InterestedPartyIdentifierFlat ipid : ip.getInterestedPartyIdentifierFlatList()) {
	    ipDoc.getIdentifiers().add(ipid.getValue());
	}

	if (ip.getMainId() != null) {
	    ipDoc.getIdentifiers().add(ip.getMainId());
	}

	if (ip.getBirthDate() != null) {
	    SimpleDateFormat sdf = new SimpleDateFormat(ConversionUtils.DATE_STAMP_SEP);
	    ipDoc.setBirthDateStr(sdf.format(ip.getBirthDate()));
	}

	if (ip.getStatusCode() != null) {
	    ipDoc.setStatusCode(ip.getStatusCode());
	}

	// deep mapping for each name
	for (Name n : ip.getNameList()) {
	    String firstName = StringUtils.defaultString(n.getFirstName());
	    String lastName = StringUtils.defaultString(n.getName());

	    // compose full name and full name reverse
	    String fullName = StringUtils.trimToNull(firstName + " " + lastName);
	    String fullNameRev = StringUtils.trimToNull(lastName + " " + firstName);

	    if (StringUtils.equals(n.getNameType(), NameTypeEnum.PA.name())) {
		ipDoc.setMainName(fullNameRev);
	    }

	    ipDoc.getFullNames().add(fullName);
	    ipDoc.getFullNameRevs().add(fullNameRev);

	    // add ipin to the list
	    ipDoc.getIdentifiers().add(n.getMainId());
	}

	return ipDoc;
    }

    /**
     * To work doc.
     *
     * @param wList
     *            the w list
     * @return the list
     */
    public List<WorkDoc> toWorkDoc(List<Work> wList) {
	List<WorkDoc> wDocList = new ArrayList<WorkDoc>();

	// transform and add each element to the output list
	for (Work w : wList) {
	    WorkDoc wDoc = toWorkDoc(w);
	    wDocList.add(wDoc);
	}

	return wDocList;
    }

    /**
     * To work doc.
     *
     * @param w
     *            the work
     * @return the work doc
     */
    public WorkDoc toWorkDoc(Work w) {
	WorkDoc wDoc;

	// fild name based mapping using dozer
	wDoc = dozerUtility.map(w, new WorkDoc());
	// set titles
	for (Title t : w.getTitleList()) {
	    String titleString = StringUtils.defaultString(t.getDescription());
	    if (StringUtils.isNotBlank(titleString)) {
		if (StringUtils.equals(t.getTypeCode(), TitleTypeCodeEnum.OT.name())) {
		    wDoc.setMainTitle(titleString);
		}

		wDoc.getTitles().add(titleString);
	    }
	}

	// add each work identifier to the ids list
	for (WorkIdentifierFlat wid : w.getWorkIdentifierList()) {
	    wDoc.getIdentifiers().add(wid.getValue());
	}

	if (w.getMainId() != null) {
	    wDoc.getIdentifiers().add(w.getMainId());
	}
	return wDoc;
    }

}
