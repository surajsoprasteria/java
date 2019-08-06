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
package org.wipo.connect.shared.business.import_work;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.i18n.ImportMessagesDecoder;
import org.wipo.connect.common.import_model.TransactionTypeEnum;
import org.wipo.connect.common.import_model.WorkRow;
import org.wipo.connect.common.import_model.WorkRowTypeEnum;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.enumerator.WccWorkImportExceptionCodeEnum;
import org.wipo.connect.shared.business.impl.WorkMergeHelper;
import org.wipo.connect.shared.exchange.dto.impl.DerivedView;
import org.wipo.connect.shared.exchange.dto.impl.DerivedWork;
import org.wipo.connect.shared.exchange.dto.impl.Title;
import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.dto.impl.WorkDate;
import org.wipo.connect.shared.exchange.dto.impl.WorkIdentifierFlat;
import org.wipo.connect.shared.exchange.dto.impl.WorkPerformer;
import org.wipo.connect.shared.exchange.enumerator.TitleTypeCodeEnum;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class ExecutorWorkActionImpl implements ExecutorWorkAction {
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(Logger.class);

    @Autowired
    private ImportWorkDAOHelper importWorkDAOHelper;

    @Autowired
    private ImportMessagesDecoder workImportMessagesDecoder;

    @Autowired
    private WorkCodeMapper workCodeMapper;

    private static final String MAP_SEP = "\u0000\u0000\u0000";

    @Override
    public void executeAction(TransactionTypeEnum transaction, Work work, List<WorkRow> workRows) throws WccWorkImportException {

	LOGGER.info("Execute Action: {}", transaction);

	try {

	    switch (transaction) {
		case INSERT:
		    work = importWorkDAOHelper.insertOrUpdate(work);
		    break;
		case UPDATE:
		    Work oldWork = importWorkDAOHelper.findByMainId(work.getMainId(), true);
		    executeUpdate(work, oldWork);
		    work = importWorkDAOHelper.insertOrUpdate(work);
		    break;
		case DELETE:
		    importWorkDAOHelper.executeDelete(work.getMainId());
		    break;
	    }

	    setStatusIds(workRows, work.getMainId(), transaction);

	} catch (WccException e) {
	    LOGGER.error("Error saving work", e);
	    throw new WccWorkImportException(WccWorkImportExceptionCodeEnum.GENERIC_ERROR);
	}
    }

    private void setStatusIds(List<WorkRow> workRows, String mainId, TransactionTypeEnum transaction) {
	if (transaction.equals(TransactionTypeEnum.DELETE)) {
	    workRows.get(0).setCreatorRef(null);
	    workRows.get(0).setDuration(null);
	    workRows.get(0).setGenre(null);
	    workRows.get(0).setIds(null);
	    workRows.get(0).setInstrument(null);
	    workRows.get(0).setPerformer(null);
	    workRows.get(0).setTerritory(null);
	    workRows.get(0).setStatus(workImportMessagesDecoder.getSuccessMessage(transaction));
	    workRows.get(0).setIds(mainId);
	} else {
	    // check if main row has error code null and other rows have error
	    WorkRow mainRow = workRows.stream().filter(it -> WorkRowTypeEnum.MA.name().equals(it.getRowType())).findFirst().get();
	    List<WorkRow> otherRowsInError = workRows.stream()
		    .filter(it -> !WorkRowTypeEnum.MA.name().equals(it.getRowType()) && !it.getErrorCode().equals(WccWorkImportExceptionCodeEnum.NO_ERROR.getCode())).collect(Collectors.toList());

	    if (mainRow.getErrorCode().equals(WccWorkImportExceptionCodeEnum.NO_ERROR.getCode()) && otherRowsInError.size() > 0) {
		mainRow.setStatus(workImportMessagesDecoder.getWarningMessage(null));
		mainRow.setIds(mainId);
	    } else if (mainRow.getErrorCode().equals(WccWorkImportExceptionCodeEnum.NO_ERROR.getCode()) && otherRowsInError.size() == 0) {
		mainRow.setStatus(workImportMessagesDecoder.getSuccessMessage(transaction));
		mainRow.setIds(mainId);
	    } else {
		mainRow.setStatus(workImportMessagesDecoder.getWarningMessage(mainRow.getErrorCode()));
		mainRow.setIds(mainId);
	    }

	    List<WorkRow> withoutMainRow = workRows.stream().filter(it -> !WorkRowTypeEnum.MA.name().equals(it.getRowType())).collect(Collectors.toList());

	    for (WorkRow row : withoutMainRow) {
		if (StringUtils.isBlank(row.getStatus())) {
		    row.setStatus(workImportMessagesDecoder.getSuccessMessage(transaction));
		    row.setIds(mainId);
		}

	    }
	}

    }

    private Work executeUpdate(Work work, Work oldWork) throws WccException {
	work.setId(oldWork.getId());
	work.setFkStatus(oldWork.getFkStatus());
	work.setCmoOriginCode(oldWork.getCmoOriginCode());

	if (StringUtils.isEmpty(work.getCreationClassCode())) {
	    work.setCreationClassCode(oldWork.getCreationClassCode());
	    work.setFkCreationClass(oldWork.getFkCreationClass());
	}

	if (StringUtils.isEmpty(work.getCategoryCode())) {
	    work.setCategoryCode(oldWork.getCategoryCode());
	}

	if (work.getComponentPerc() == null) {
	    work.setComponentPerc(oldWork.getComponentPerc());
	}

	if (work.getDuration() == null) {
	    work.setDuration(oldWork.getDuration());
	}

	if (StringUtils.isEmpty(work.getTypeCode())) {
	    work.setTypeCode(oldWork.getTypeCode());
	    work.setFkType(oldWork.getFkType());
	}

	if (StringUtils.isEmpty(work.getSourceTypeCode())) {
	    work.setSourceTypeCode(oldWork.getSourceTypeCode());
	    work.setFkSourceType(oldWork.getFkSourceType());
	}

	// TITLE
	Map<String, Title> titleMap = new HashMap<>();
	for (Title t : oldWork.getTitleList()) {
	    titleMap.put(getTitkeKey(t), t);
	}
	work.getTitleList().removeIf(t -> titleMap.containsKey(getTitkeKey(t)));

	for (Title t : work.getTitleList()) {
	    if (StringUtils.equalsIgnoreCase(t.getTypeCode(), TitleTypeCodeEnum.OT.name())) {
		if (StringUtils.isNotEmpty(oldWork.getOriginalTitle())) {
		    t.setTypeCode(TitleTypeCodeEnum.AT.name());
		    t.setFkType(workCodeMapper.getTitleTypeByCode(TitleTypeCodeEnum.AT.name()));
		}
	    }
	}

	// IDENTIFIER
	Map<String, WorkIdentifierFlat> identifierMap = new HashMap<>();
	for (WorkIdentifierFlat i : oldWork.getWorkIdentifierList()) {
	    identifierMap.put(getIdentifierKey(i), i);
	}
	work.getWorkIdentifierList().removeIf(i -> identifierMap.containsKey(getIdentifierKey(i)));

	// PERFORMER
	Map<String, WorkPerformer> performerMap = new HashMap<>();
	for (WorkPerformer p : oldWork.getWorkPerformerList()) {
	    performerMap.put(getPerformerKey(p), p);
	}
	work.getWorkPerformerList().removeIf(p -> performerMap.containsKey(getPerformerKey(p)));

	// COMPONENT WORK
	if (!work.getDerivedWorkList().isEmpty()) {
	    // remove old component only there are new component
	    for (DerivedWork dw : oldWork.getDerivedWorkList()) {
		dw.setExecDelete(true);
		work.getDerivedWorkList().add(dw);
	    }
	}

	// SHARE
	if (work.getDerivedViewList().isEmpty() || work.getDerivedViewList().get(0).getDerivedViewNameList().isEmpty()) {
	    String territory = work.getDerivedViewList().get(0).getTerritoryFormula();
	    work.setDerivedViewList(oldWork.getDerivedViewList());
	    if (StringUtils.isNotEmpty(territory)) {
		work.getDerivedViewList().get(0).setTerritoryFormula(territory);
	    }
	} else {
	    if (StringUtils.isEmpty(work.getDerivedViewList().get(0).getTerritoryFormula())) {
		work.getDerivedViewList().get(0).setTerritoryFormula(oldWork.getDerivedViewList().get(0).getTerritoryFormula());
	    }
	    for (DerivedView dv : oldWork.getDerivedViewList()) {
		dv.setExecDelete(true);
		work.getDerivedViewList().add(dv);
	    }
	}

	// DATE
	Map<String, WorkDate> dateMap = new HashMap<>();
	for (WorkDate d : oldWork.getWorkDateList()) {
	    dateMap.put(getDateKey(d), d);
	}
	for (WorkDate d : work.getWorkDateList()) {
	    String key = getDateKey(d);
	    if (dateMap.containsKey(key)) {
		WorkDate dateToUpdate = dateMap.get(key);
		dateToUpdate.setDateValue(d.getDateValue());
		dateMap.put(key, dateToUpdate);
	    } else {
		dateMap.put(getDateKey(d), d);
	    }
	}
	work.getWorkDateList().clear();
	work.getWorkDateList().addAll(dateMap.values());

	// COMPONENT WORK
	if (!work.getDerivedWorkList().isEmpty()) {
	    // remove old component only there are new component
	    for (DerivedWork dw : oldWork.getDerivedWorkList()) {
		dw.setExecDelete(true);
		work.getDerivedWorkList().add(dw);
	    }
	}

	// INSTRUMENT (No Deletable, so all removed and insered again)
	Set<String> instrumentSet = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
	instrumentSet.addAll(oldWork.getInstrumentCodeList());
	instrumentSet.addAll(work.getInstrumentCodeList());
	work.getInstrumentCodeList().clear();
	work.getInstrumentCodeList().addAll(instrumentSet);

	// ADDITIONAL FIELD
	WorkMergeHelper.mergeAdditionalFields(oldWork, work, true);

	return work;

    }

    private String getTitkeKey(Title t) {
	return StringUtils.trimToNull(t.getDescription()).replace(" ", "_").toLowerCase();
    }

    private String getPerformerKey(WorkPerformer p) {
	return StringUtils.trimToNull(p.getPerformerName()).replace(" ", "_").toLowerCase();
    }

    private String getDateKey(WorkDate d) {
	return (StringUtils.trimToNull(d.getDateTypeCode())).replace(" ", "_").toLowerCase();
    }

    private String getIdentifierKey(WorkIdentifierFlat i) {
	return (StringUtils.trimToNull(i.getCode()) + MAP_SEP + StringUtils.trimToNull(i.getValue())).replace(" ", "_").toLowerCase();
    }

}
