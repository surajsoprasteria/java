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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.wipo.connect.common.utils.ConversionUtils;
import org.wipo.connect.shared.exchange.dto.impl.DerivedView;
import org.wipo.connect.shared.exchange.dto.impl.DerivedViewName;
import org.wipo.connect.shared.exchange.dto.impl.DerivedViewNameShare;
import org.wipo.connect.shared.exchange.dto.impl.Title;
import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.dto.impl.WorkIdentifierFlat;
import org.wipo.connect.shared.exchange.dto.impl.WorkPerformer;
import org.wipo.connect.shared.exchange.enumerator.IdentifierTypeEnum;

/**
 * The Class WorkUtils.
 */
public class WorkUtils {

    private WorkUtils() {
	super();
    }

    /**
     * Merge work shared changes.
     *
     * @param localWork
     *            the local work
     * @param sharedWork
     *            the shared work
     * @return the work
     */
    public static Work mergeWorkSharedChanges(Work localWork, Work sharedWork) {

	// clone objects to be sure to not modify the originals
	localWork = SerializationUtils.clone(localWork);
	sharedWork = SerializationUtils.clone(sharedWork);

	// merge duration
	if (localWork.getDuration() == null || localWork.getDuration().equals(0L)) {
	    localWork.setDuration(sharedWork.getDuration());
	}

	localWork.setGenreCode(sharedWork.getGenreCode());

	// merge work creation date
	if (localWork.getWorkCreationDate() == null) {
	    localWork.setWorkCreationDate(sharedWork.getWorkCreationDate());
	}

	// merge instrument list
	localWork.getInstrumentCodeList().clear();
	localWork.getInstrumentCodeList().addAll(sharedWork.getInstrumentCodeList());

	// add all the performers not present in local
	List<String> perfChkList = generatePerformerCheckCode(localWork);
	for (WorkPerformer shPerf : sharedWork.getWorkPerformerList()) {
	    String shChk = generatePerformerCheckCode(shPerf);
	    if (!perfChkList.contains(shChk)) {
		WorkPerformer loPerf = SerializationUtils.clone(shPerf);
		loPerf.setId(null);
		loPerf.setFkWork(localWork.getId());
		localWork.getWorkPerformerList().add(loPerf);
	    }
	}

	// add all the titles not present in local
	List<String> titleChkList = generateTitleCheckCode(localWork);
	for (Title shTitle : sharedWork.getTitleList()) {
	    String shChk = generateTitleCheckCode(shTitle);
	    if (!titleChkList.contains(shChk)) {
		Title loTitle = SerializationUtils.clone(shTitle);
		loTitle.setId(null);
		localWork.getTitleList().add(loTitle);
	    }
	}

	// add all the identifiers not present in local
	List<String> idChkList = generateIdentifierCheckCode(localWork);
	for (WorkIdentifierFlat shWid : sharedWork.getWorkIdentifierList()) {
	    String shChk = generateIdentifierCheckCode(shWid);
	    if (!idChkList.contains(shChk) && !StringUtils.equals(IdentifierTypeEnum.WIPO_CONNECT_LOCAL_ID.name(), shWid.getCode())) {

		WorkIdentifierFlat loWid = SerializationUtils.clone(shWid);
		loWid.setId(null);
		localWork.getWorkIdentifierList().add(loWid);

	    }
	}

	// remove old derived views and substitute them with shared version
	for (DerivedView dv : localWork.getDerivedViewList()) {
	    dv.setExecDelete(true);
	}
	for (DerivedView sharedDv : sharedWork.getDerivedViewList()) {
	    DerivedView localDv = SerializationUtils.clone(sharedDv);
	    localDv.setId(null);
	    for (DerivedViewName dvn : localDv.getDerivedViewNameList()) {
		dvn.setId(null);
		dvn.getName().setId(null);
		for (DerivedViewNameShare dvns : dvn.getDerivedViewNameShareList()) {
		    dvns.setId(null);
		}
	    }
	    localWork.getDerivedViewList().add(localDv);
	}

	return localWork;

    }

    /**
     * Compare local and shared work.
     *
     * @param localWork
     *            the local work
     * @param sharedWork
     *            the shared work
     * @return true, if successful
     */
    public static boolean compareLocalAndSharedWork(Work localWork, Work sharedWork) {

	if (!compareWorkHeaderInfo(localWork, sharedWork)) {
	    return false;
	}

	if (!compareTitleList(localWork, sharedWork)) {
	    return false;
	}

	if (!compareInstrumentList(localWork, sharedWork)) {
	    return false;
	}

	if (!comparePerformerList(localWork, sharedWork)) {
	    return false;
	}

	if (!compareDerivedView(localWork, sharedWork)) {
	    return false;
	}

	return true;
    }

    private static boolean compareTitleList(Work localWork, Work sharedWork) {
	List<String> mainList = generateTitleCheckCode(sharedWork);
	List<String> subList = generateTitleCheckCode(localWork);
	return isSubset(mainList, subList);
    }

    private static boolean comparePerformerList(Work localWork, Work sharedWork) {
	List<String> mainList = generatePerformerCheckCode(sharedWork);
	List<String> subList = generatePerformerCheckCode(localWork);
	return isSubset(mainList, subList);
    }

    private static boolean compareDerivedView(Work localWork, Work sharedWork) {
	List<String> mainList = generateDerivedViewCheckCode(sharedWork);
	List<String> subList = generateDerivedViewCheckCode(localWork);
	return isSubset(mainList, subList);
    }

    private static boolean compareInstrumentList(Work localWork, Work sharedWork) {
	List<String> mainList = generateInstrumentCheckCode(sharedWork);
	List<String> subList = generateInstrumentCheckCode(localWork);
	return isSubset(mainList, subList);
    }

    private static boolean compareWorkHeaderInfo(Work localWork, Work sharedWork) {

	EqualsBuilder eqWork = new EqualsBuilder();

	if (localWork.getDuration() != null && !localWork.getDuration().equals(0L)) {
	    eqWork.append(localWork.getDuration(), sharedWork.getDuration());
	}

	if (!StringUtils.isEmpty(localWork.getGenreCode())) {
	    eqWork.append(localWork.getGenreCode(), sharedWork.getGenreCode());
	}

	if (localWork.getWorkCreationDate() != null) {
	    eqWork.append(localWork.getWorkCreationDate(), sharedWork.getWorkCreationDate());
	}

	return eqWork.isEquals();
    }

    private static List<String> generateTitleCheckCode(Work work) {
	List<String> chkList = new ArrayList<>();

	for (Title t : work.getTitleList()) {
	    chkList.add(generateTitleCheckCode(t));
	}

	return chkList;
    }

    private static String generateTitleCheckCode(Title t) {
	final String SEP = "\u0000\u0000\u0000";
	StringBuilder sb = new StringBuilder();
	sb.append(t.getDescription());
	sb.append(SEP);
	sb.append(t.getTypeCode());

	String aux = sb.toString().toLowerCase();
	String auxMd5 = DigestUtils.md5Hex(aux);
	return auxMd5;
    }

    private static List<String> generateIdentifierCheckCode(Work work) {
	List<String> chkList = new ArrayList<>();

	for (WorkIdentifierFlat wid : work.getWorkIdentifierList()) {
	    chkList.add(generateIdentifierCheckCode(wid));
	}

	return chkList;
    }

    private static String generateIdentifierCheckCode(WorkIdentifierFlat wid) {
	final String SEP = "\u0000\u0000\u0000";
	StringBuilder sb = new StringBuilder();
	sb.append(wid.getValue());
	sb.append(SEP);
	sb.append(wid.getCode());

	String aux = sb.toString().toLowerCase();
	String auxMd5 = DigestUtils.md5Hex(aux);
	return auxMd5;
    }

    private static List<String> generatePerformerCheckCode(Work work) {
	List<String> chkList = new ArrayList<>();

	for (WorkPerformer wp : work.getWorkPerformerList()) {
	    chkList.add(generatePerformerCheckCode(wp));
	}

	return chkList;
    }

    private static String generatePerformerCheckCode(WorkPerformer wp) {
	String aux = wp.getPerformerName().toLowerCase();
	String auxMd5 = DigestUtils.md5Hex(aux);
	return auxMd5;
    }

    private static List<String> generateInstrumentCheckCode(Work work) {
	List<String> chkList = new ArrayList<>();

	for (String code : work.getInstrumentCodeList()) {
	    String aux = code.toLowerCase();
	    String auxMd5 = DigestUtils.md5Hex(aux);
	    chkList.add(auxMd5);
	}

	return chkList;
    }

    private static List<String> generateDerivedViewCheckCode(Work work) {
	final String SEP = "\u0000\u0000\u0000";
	final String DUMMY = "#####";
	DecimalFormat decFmt = new DecimalFormat(ConversionUtils.DECIMAL_PATTERN);

	List<String> chkList = new ArrayList<>();

	// loop through DerivedView list and create check strings
	for (DerivedView dv : work.getDerivedViewList()) {
	    // TODO: Resolve territory formula
	    List<String> terrCodeList = new ArrayList<>();
	    terrCodeList.add(dv.getTerritoryFormula());

	    for (String terr : terrCodeList) {
		// if DerivedViewName list is empty create a dummy element
		List<DerivedViewName> dvnList = new ArrayList<>();
		if (!dv.getDerivedViewNameList().isEmpty()) {
		    dvnList.addAll(dv.getDerivedViewNameList());
		} else {
		    dvnList.add(new DerivedViewName());
		}

		for (DerivedViewName dvn : dvnList) {

		    // if DerivedViewNameShare list is empty create a dummy element
		    List<DerivedViewNameShare> dvnsList = new ArrayList<>();
		    if (!dvn.getDerivedViewNameShareList().isEmpty()) {
			dvnsList.addAll(dvn.getDerivedViewNameShareList());
		    } else {
			dvnsList.add(new DerivedViewNameShare());
		    }

		    for (DerivedViewNameShare dvns : dvnsList) {
			StringBuilder chk = new StringBuilder();

			// create check string with the pattern: "TERRITORY||START_DATE||END_DATE||IPIN||ROLE||RIGHT_TYPE||SHARE_VALUE||"

			// Append territory
			chk.append(terr + SEP);

			// Append IPIN code
			if (dvn.getName() != null && dvn.getName().getMainId() != null) {
			    chk.append(dvn.getName().getMainId() + SEP);
			} else {
			    chk.append(DUMMY + SEP);
			}

			// Append role
			if (dvn.getRoleCode() != null) {
			    chk.append(dvn.getRoleCode() + SEP);
			} else {
			    chk.append(DUMMY + SEP);
			}

			// Append right type
			if (dvns.getRightTypeCode() != null) {
			    chk.append(dvns.getRightTypeCode() + SEP);
			} else {
			    chk.append(DUMMY + SEP);
			}

			// Append share value
			if (dvns.getShareValue() != null) {
			    chk.append(decFmt.format(dvns.getShareValue()) + SEP);
			} else {
			    chk.append(DUMMY + SEP);
			}

			// add current check string to the list
			String aux = chk.toString().toLowerCase();
			String auxMd5 = DigestUtils.md5Hex(aux);
			chkList.add(auxMd5);
		    }
		}
	    }
	}

	return chkList;
    }

    private static boolean isSubset(Collection<String> mainCollection, Collection<String> subCollection) {
	boolean isSubSet = true;

	for (String k : subCollection) {
	    if (!mainCollection.contains(k)) {
		isSubSet = false;
		break;
	    }
	}

	return isSubSet;
    }

}
