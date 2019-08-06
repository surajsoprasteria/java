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
package org.wipo.connect.shared.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.shared.exchange.dto.impl.Title;
import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.dto.impl.WorkDate;
import org.wipo.connect.shared.exchange.dto.impl.WorkIdentifierFlat;
import org.wipo.connect.shared.exchange.dto.impl.WorkPerformer;
import org.wipo.connect.shared.exchange.enumerator.TitleTypeCodeEnum;

public class WorkMergeHelper {

    public static List<Title> mergeTitles(List<Title> wSourceTitles, List<Title> wDestTitles, Map<String, Long> titleTypeMap) {

	Map<Integer, Title> wSourceTitlesMap = new HashMap<Integer, Title>();
	for (Title sourceTitle : wSourceTitles) {
	    wSourceTitlesMap.put(getHashCode(sourceTitle), sourceTitle);
	}

	Map<Integer, Title> wDestTitlesMap = new HashMap<Integer, Title>();
	for (Title destTitle : wDestTitles) {
	    wDestTitlesMap.put(getHashCode(destTitle), destTitle);
	}

	List<Title> titleListToAdd = new ArrayList<Title>();

	for (Integer key : wSourceTitlesMap.keySet()) {
	    Title sourceTitle = wSourceTitlesMap.get(key);
	    Title destTitle = wDestTitlesMap.get(key);
	    // if title not found in destination
	    if (null == destTitle || (null != destTitle && !isEqualTitle(sourceTitle, destTitle))) {
		Title titleToAdd = SerializationUtils.clone(sourceTitle);
		if (sourceTitle.getTypeCode().equals(TitleTypeCodeEnum.OT.name())) {
		    titleToAdd.setFkType(titleTypeMap.get(TitleTypeCodeEnum.AT.name()));
		    titleToAdd.setTypeCode(TitleTypeCodeEnum.AT.name());
		}
		titleToAdd.setId(null);
		titleListToAdd.add(titleToAdd);
	    }
	}
	if (titleListToAdd.size() > 0) {
	    wDestTitles.addAll(titleListToAdd);
	}
	return wDestTitles;
    }

    private static int getHashCode(Title title) {
	String titleStr = null != title.getDescription() ? title.getDescription().toUpperCase() : null;
	return new HashCodeBuilder(17, 37).append(titleStr).append(titleStr).toHashCode();
    }

    private static int getHashCode(WorkIdentifierFlat ident) {
	return new HashCodeBuilder(17, 37).append(ident.getValue()).append(ident.getValue()).toHashCode();
    }

    private static boolean isEqualTitle(Title sourceTitle, Title destTitle) {
	boolean isEqual = false;
	EqualsBuilder eb = new EqualsBuilder();
	eb.append(StringUtils.upperCase(sourceTitle.getDescription()), StringUtils.upperCase(destTitle.getDescription()));
	return isEqual || eb.isEquals();

    }

    public static List<WorkPerformer> mergePerformers(List<WorkPerformer> wPerformerListSource, List<WorkPerformer> wPerformerListDest) {
	if (!wPerformerListSource.isEmpty()) {
	    List<WorkPerformer> newComment = new ArrayList<>();
	    for (WorkPerformer cs : wPerformerListSource) {
		boolean isNew = true;
		for (WorkPerformer cd : wPerformerListDest) {
		    if (StringUtils.equalsIgnoreCase(cd.getPerformerName(), cs.getPerformerName())) {
			isNew = false;
			break;
		    }
		}
		if (isNew) {
		    cs.setIdWorkPerformer(null);
		    newComment.add(cs);
		}
	    }
	    wPerformerListDest.addAll(newComment);
	}
	return wPerformerListDest;
    }

    public static void mergeSectionsWork(Work wSource, Work wDest) throws WccException {
	mergeGeneralInfo(wSource, wDest);
    }

    private static void mergeGeneralInfo(Work wSource, Work wDest) {
	if (wDest.getDuration() == null) {
	    wDest.setDuration(wSource.getDuration());
	}
	if (wDest.getGenreCode() == null) {
	    wDest.setGenreCode(wSource.getGenreCode());
	}
	if (wDest.getLanguage() == null) {
	    wDest.setLanguage(wSource.getLanguage());
	}
	if (!wSource.getInstrumentCodeList().isEmpty()) {
	    List<String> newInstr = new ArrayList<>();
	    for (String is : wSource.getInstrumentCodeList()) {
		boolean isNew = true;
		for (String id : wDest.getInstrumentCodeList()) {
		    if (StringUtils.equalsIgnoreCase(id, is)) {
			isNew = false;
			break;
		    }
		}
		if (isNew) {
		    newInstr.add(is);
		}
	    }
	    wDest.getInstrumentCodeList().addAll(newInstr);
	}

    }

    public static List<WorkIdentifierFlat> mergeIdentifiers(List<WorkIdentifierFlat> wIdentListSource, List<WorkIdentifierFlat> wIdentListDest) {

	Map<Integer, WorkIdentifierFlat> wSourceIdentifMap = new HashMap<Integer, WorkIdentifierFlat>();
	for (WorkIdentifierFlat identifier : wIdentListSource) {
	    wSourceIdentifMap.put(getHashCode(identifier), identifier);
	}

	Map<Integer, WorkIdentifierFlat> wDestIdentMap = new HashMap<Integer, WorkIdentifierFlat>();
	for (WorkIdentifierFlat identifier : wIdentListDest) {
	    wDestIdentMap.put(getHashCode(identifier), identifier);
	}

	List<WorkIdentifierFlat> identListToAdd = new ArrayList<WorkIdentifierFlat>();

	for (Integer key : wSourceIdentifMap.keySet()) {
	    WorkIdentifierFlat sourceId = wSourceIdentifMap.get(key);
	    WorkIdentifierFlat destId = wDestIdentMap.get(key);
	    // if title not found in destination
	    if (null == destId || (null != destId && !isEqualIdentifier(sourceId, destId))) {
		WorkIdentifierFlat identToAdd = SerializationUtils.clone(sourceId);
		identToAdd.setId(null);
		identListToAdd.add(identToAdd);
	    }
	}

	if (identListToAdd.size() > 0) {
	    wIdentListDest.addAll(identListToAdd);
	}

	return wIdentListDest;
    }

    private static boolean isEqualIdentifier(WorkIdentifierFlat sourceIdent, WorkIdentifierFlat destIdent) {
	boolean isEqual = false;
	EqualsBuilder eb = new EqualsBuilder();
	eb.append(sourceIdent.getCode(), destIdent.getCode());
	return isEqual || eb.isEquals();

    }

    public static void mergeAdditionalFields(Work wSource, Work wDest, boolean skipDate) throws WccException {
	if (wDest.getCatalogueNumber() == null) {
	    wDest.setCatalogueNumber(wSource.getCatalogueNumber());
	}
	if (wDest.getSupport() == null) {
	    wDest.setSupport(wSource.getSupport());
	}

	if (wDest.getFkCountryOfProduction() == null) {
	    wDest.setFkCountryOfProduction(wSource.getFkCountryOfProduction());
	}

	if (wDest.getCategoryCode() == null) {
	    wDest.setCategoryCode(wSource.getCategoryCode());
	}

	if (wDest.getGenreCode() == null) {
	    wDest.setGenreCode(wSource.getGenreCode());
	}

	if (wDest.getLabel() == null) {
	    wDest.setLabel(wSource.getLabel());
	}

	if (wDest.getLanguage() == null) {
	    wDest.setLanguage(wSource.getLanguage());
	}

	if (!skipDate && !wSource.getWorkDateList().isEmpty()) {
	    List<WorkDate> newDate = new ArrayList<>();
	    for (WorkDate is : wSource.getWorkDateList()) {
		boolean isNew = true;
		for (WorkDate id : wDest.getWorkDateList()) {
		    if (StringUtils.equalsIgnoreCase(id.getDateTypeCode(), is.getDateTypeCode())) {
			isNew = false;
			break;
		    }
		}
		if (isNew) {
		    is.setId(null);
		    newDate.add(is);
		}
	    }
	    wDest.getWorkDateList().addAll(newDate);
	}
    }

}
