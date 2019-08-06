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

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.el.CustomFunctions;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.i18n.ImportMessagesDecoder;
import org.wipo.connect.common.import_model.TransactionTypeEnum;
import org.wipo.connect.common.import_model.WorkRow;
import org.wipo.connect.common.import_model.WorkRowTypeEnum;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.ConstantUtils;
import org.wipo.connect.common.utils.ConversionUtils;
import org.wipo.connect.enumerator.WccWorkImportExceptionCodeEnum;
import org.wipo.connect.shared.exchange.dto.impl.DerivedView;
import org.wipo.connect.shared.exchange.dto.impl.DerivedViewName;
import org.wipo.connect.shared.exchange.dto.impl.DerivedViewNameShare;
import org.wipo.connect.shared.exchange.dto.impl.DerivedWork;
import org.wipo.connect.shared.exchange.dto.impl.Name;
import org.wipo.connect.shared.exchange.dto.impl.Title;
import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.dto.impl.WorkDate;
import org.wipo.connect.shared.exchange.dto.impl.WorkIdentifierFlat;
import org.wipo.connect.shared.exchange.dto.impl.WorkPerformer;
import org.wipo.connect.shared.exchange.enumerator.WorkSourceTypeEnum;
import org.wipo.connect.shared.exchange.utils.TerritoryFormulaUtils;

@Service
public class WorkLoaderImpl implements WorkLoader {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(WorkLoaderImpl.class);

    @Autowired
    private WorkRowValidator workRowValidatorImpl;

    @Autowired
    private ImportWorkDAOHelper importWorkDAOHelper;

    @Autowired
    private ImportMessagesDecoder workImportMessagesDecoder;

    @Autowired
    private WorkCodeMapper workCodeMapper;

    private SimpleDateFormat ft;

    @Override
    @PostConstruct
    public void init() {
	ft = new SimpleDateFormat(ConversionUtils.DATE_STAMP);
	ft.setLenient(false);
    }

    @Override
    public Work loadRowsEntity(TransactionTypeEnum transaction, List<WorkRow> entityRows, String dataOrigin) throws WccWorkImportException, WccException {

	Work work = new Work();

	for (WorkRow row : entityRows) {

	    String rowType = row.getRowType();
	    WorkRowTypeEnum rowTypeEnum = WorkRowTypeEnum.UNKNOWN;

	    if (null != rowType && EnumUtils.isValidEnum(WorkRowTypeEnum.class, rowType)) {
		rowTypeEnum = WorkRowTypeEnum.valueOf(rowType);
	    }

	    switch (rowTypeEnum) {
		case MA:
		    LOGGER.info("LoadRowEntity of type: {}", WorkRowTypeEnum.MA);
		    work = loadWork(row, transaction);
		    if (TransactionTypeEnum.INSERT.equals(transaction)) {
			work.setCmoOriginCode(dataOrigin);
		    }
		    break;
		case TI:
		    LOGGER.info("LoadRowEntity of type: {}", WorkRowTypeEnum.TI);
		    Title title = loadTitle(row);
		    if (title != null) {
			work.getTitleList().add(title);
		    } else {
			row.setStatus(workImportMessagesDecoder.getErrorMessage(row.getErrorCode()));
		    }
		    break;
		case ID:
		    LOGGER.info("LoadRowEntity of type: {}", WorkRowTypeEnum.ID);
		    WorkIdentifierFlat identifierFlat = loadIdentifier(row);
		    if (identifierFlat != null) {
			work.getWorkIdentifierList().add(identifierFlat);
		    } else {
			row.setStatus(workImportMessagesDecoder.getErrorMessage(row.getErrorCode()));
		    }
		    break;
		case SH:
		    LOGGER.info("LoadRowEntity of type: {}", WorkRowTypeEnum.SH);
		    DerivedView derivedView = loadShare(row);
		    if (derivedView != null) {
			if (work.getDerivedViewList().isEmpty()) {
			    work.getDerivedViewList().add(derivedView);
			} else {
			    DerivedView aux = work.getDerivedViewList().get(0);
			    aux.getDerivedViewNameList().addAll(derivedView.getDerivedViewNameList());
			}
		    } else {
			row.setStatus(workImportMessagesDecoder.getErrorMessage(row.getErrorCode()));
		    }
		    break;
		case GR:
		    LOGGER.info("LoadRowEntity of type: {}", WorkRowTypeEnum.GR);
		    LOGGER.info("WorkGroup not implemented");
		    break;
		case DA:
		    LOGGER.info("LoadRowEntity of type: {}", WorkRowTypeEnum.DA);
		    WorkDate workDate = loadWorkDate(row);
		    if (workDate != null) {
			work.getWorkDateList().add(workDate);
		    } else {
			row.setStatus(workImportMessagesDecoder.getErrorMessage(row.getErrorCode()));
		    }
		    break;
		case CO:
		    LOGGER.info("LoadRowEntity of type: {}", WorkRowTypeEnum.CO);
		    DerivedWork derivedWork = loadWorkComponent(row, work);
		    if (derivedWork != null) {
			work.getDerivedWorkList().add(derivedWork);
		    } else {
			row.setStatus(workImportMessagesDecoder.getErrorMessage(row.getErrorCode()));
		    }
		    break;
		default:
		    row.setStatus(workImportMessagesDecoder.getErrorMessage(row.getErrorCode()));
		    break;
	    }

	}

	work = workRowValidatorImpl.completeDerivedView(work);
	return work;
    }

    @Override
    public Work loadWork(WorkRow row, TransactionTypeEnum transaction) throws WccWorkImportException, WccException {
	Work work = new Work();

	work.setMainId(row.getWorkMainId());

	String sourceCode = WorkSourceTypeEnum.WRK_SRC_IMPORT.name();
	work.setFkSourceType(workCodeMapper.getImportSourceReferenceByCode(sourceCode));
	work.setSourceTypeCode(sourceCode);

	if (StringUtils.isNotEmpty(row.getGenre())) {
	    work.setGenreCode(row.getGenre());
	}

	String workType = ImportConstant.WORK_TYPE_DEFAULT;
	Long idWorkType = workCodeMapper.getWorkTypeByCode(workType);
	if (idWorkType != null) {
	    work.setFkType(idWorkType);
	    work.setTypeCode(workType);
	}

	if (StringUtils.isNotEmpty(row.getInstrument())) {
	    StringTokenizer st = new StringTokenizer(row.getInstrument(), ConstantUtils.PIPE);
	    while (st.hasMoreElements()) {
		String instr = st.nextElement().toString();
		if (StringUtils.isNotEmpty(instr)) {
		    work.getInstrumentCodeList().add(instr);
		}
	    }
	}

	if (StringUtils.isNotEmpty(row.getPerformer())) {
	    List<WorkPerformer> workPerformers = new ArrayList<>();
	    StringTokenizer st = new StringTokenizer(row.getPerformer(), ConstantUtils.PIPE);
	    while (st.hasMoreElements()) {
		String perf = st.nextElement().toString();
		WorkPerformer wp = new WorkPerformer();
		wp.setPerformerName(perf);
		workPerformers.add(wp);
	    }

	    work.setWorkPerformerList(workPerformers);
	}

	String durationField = StringUtils.trimToEmpty(row.getDuration());
	if (StringUtils.isNotEmpty(durationField)) {
	    try {
		Long duration = null;

		if (durationField.matches(CustomFunctions.getRegexTimeCheck())) {
		    duration = CustomFunctions.hMsToSeconds(durationField);
		} else {
		    duration = Long.parseLong(row.getDuration());
		}
		work.setDuration(duration);
	    } catch (NumberFormatException e) {
		LOGGER.error("Error", e);
	    }
	}

	if (StringUtils.isNotEmpty(row.getCreationClass())) {
	    String creationClass = row.getCreationClass();
	    creationClass = StringUtils.trimToNull(creationClass);
	    Long fkCreationClass = workCodeMapper.getCreationClassIdByCode(creationClass);
	    work.setFkCreationClass(fkCreationClass);
	    work.setCreationClassCode(creationClass);
	}

	if (StringUtils.isNotEmpty(row.getCatalogueNumber())) {
	    work.setCatalogueNumber(row.getCatalogueNumber());
	}

	if (StringUtils.isNotEmpty(row.getSupport())) {
	    work.setSupport(row.getSupport());
	}

	if (StringUtils.isNotEmpty(row.getCountryOfProduction())) {
	    Long fkCountry = workCodeMapper.getTerritoryCountryIdByCode(row.getCountryOfProduction());
	    if (fkCountry != null) {
		work.setFkCountryOfProduction(fkCountry);
		work.setCountryOfProductionCode(row.getCountryOfProduction());
	    }
	}

	if (StringUtils.isNotEmpty(row.getCategory())) {
	    work.setCategoryCode(row.getCategory());
	}

	if (StringUtils.isNotEmpty(row.getLabel())) {
	    work.setLabel(row.getLabel());
	}

	if (StringUtils.isNotEmpty(row.getLanguage())) {
	    work.setLanguage(row.getLanguage());
	}

	if (StringUtils.isNotEmpty(row.getWeight())) {
	    BigDecimal workPerc = new BigDecimal(row.getWeight());
	    BigDecimal componentPerc = ConstantUtils.ONE_HUNDRED.subtract(workPerc);
	    work.setComponentPerc(componentPerc);
	} else {
	    if (TransactionTypeEnum.INSERT.equals(transaction)) {
		work.setComponentPerc(ConstantUtils.ZERO); // 100 - work percentage : 100-100 = 0
	    }
	}

	DerivedView derivedView = new DerivedView();
	String territory = row.getTerritory();
	if (StringUtils.startsWith(territory, "I")) {
	    territory = TerritoryFormulaUtils.decodeTisnFormula(row.getTerritory(), workCodeMapper.getTerritoryList());
	}

	derivedView.setTerritoryFormula(territory);
	work.getDerivedViewList().add(derivedView);

	return work;

    }

    @Override
    public Title loadTitle(WorkRow row) throws WccWorkImportException {
	Title title = null;

	if (row.getErrorCode().equals(WccWorkImportExceptionCodeEnum.NO_ERROR.getCode())) {
	    title = new Title();
	    Long id = workCodeMapper.getTitleTypeByCode(row.getType());
	    title.setFkType(id);
	    title.setDescription(row.getWorkTitle());
	    title.setTypeCode(row.getType());
	}
	return title;
    }

    @Override
    public WorkIdentifierFlat loadIdentifier(WorkRow row) throws WccWorkImportException {
	WorkIdentifierFlat workIdentifierFlat = null;
	if (row.getErrorCode().equals(WccWorkImportExceptionCodeEnum.NO_ERROR.getCode())) {
	    workIdentifierFlat = new WorkIdentifierFlat();
	    String identifierCode = row.getType();
	    String identifierValue = StringUtils.trim(row.getValue());

	    Long idIdentifier = workCodeMapper.getIdentifierByCode(identifierCode).getId();

	    workIdentifierFlat.setCode(identifierCode);
	    workIdentifierFlat.setFkIdentifier(idIdentifier);
	    workIdentifierFlat.setValue(identifierValue);
	}
	return workIdentifierFlat;
    }

    @Override
    public DerivedView loadShare(WorkRow row) throws WccException, WccWorkImportException {
	DerivedView derivedView = null;
	if (row.getErrorCode().equals(WccWorkImportExceptionCodeEnum.NO_ERROR.getCode())) {
	    derivedView = new DerivedView();
	    derivedView.getDerivedViewNameList().add(loadShare(derivedView, row));
	}
	return derivedView;
    }

    private DerivedViewName loadShare(DerivedView derivedView, WorkRow row) throws WccException, WccWorkImportException {

	DerivedViewName derivedViewName = null;
	DerivedViewNameShare derivedViewNameShare;

	derivedViewName = new DerivedViewName();
	Name name = importWorkDAOHelper.findByNameMainId(row.getNameMainId());
	derivedViewName.setName(name);

	if (StringUtils.isNotEmpty(row.getRole())) {
	    Long workRoleId = workCodeMapper.getWorkRoleIdByCode(row.getRole());
	    if (workRoleId != null) {
		derivedViewName.setFkRole(workRoleId);
	    }
	}

	derivedViewNameShare = new DerivedViewNameShare();
	derivedViewNameShare.setFkRightType(workCodeMapper.getRightCategoryMap().get(row.getRightCategory()).getId());
	if (StringUtils.isNotEmpty(row.getValue())) {
	    derivedViewNameShare.setShareValue(new BigDecimal(row.getValue()));
	}

	derivedViewName.getDerivedViewNameShareList().add(derivedViewNameShare);
	derivedViewName.setCreatorRefMainId(row.getCreatorRef());

	return derivedViewName;
    }

    @Override
    public WorkDate loadWorkDate(WorkRow row) throws WccException {
	WorkDate workDate = null;

	if (row.getErrorCode().equals(WccWorkImportExceptionCodeEnum.NO_ERROR.getCode())) {
	    String dateCode = StringUtils.trimToNull(row.getType());
	    String dateValue = StringUtils.trimToNull(row.getValue());

	    try {
		Date date = ft.parse(dateValue);

		workDate = new WorkDate();
		workDate.setDateTypeCode(dateCode);
		workDate.setDateValue(date);
	    } catch (ParseException e) {
		LOGGER.error("Error", e);
	    }
	}

	return workDate;
    }

    @Override
    public DerivedWork loadWorkComponent(WorkRow row, Work work) throws WccException, WccWorkImportException {
	DerivedWork derivedWork = null;

	if (row.getErrorCode().equals(WccWorkImportExceptionCodeEnum.NO_ERROR.getCode())) {
	    derivedWork = new DerivedWork();

	    String componentMainId = StringUtils.trimToNull(row.getWorkMainId());
	    String weight = StringUtils.trimToNull(row.getWeight());
	    Long componentId = importWorkDAOHelper.findWorkIdByMainId(componentMainId);

	    Long weightValue = null;
	    if (StringUtils.isNotEmpty(weight)) {
		weightValue = Long.parseLong(weight);
	    } else {
		String cc = importWorkDAOHelper.findCreationClassByWorkMainId(componentMainId);
		if (StringUtils.equals(work.getCreationClassCode(), cc)) {
		    weightValue = 0L;
		}
	    }

	    derivedWork.setFkWork(componentId);
	    derivedWork.setWeight(weightValue);

	    Long trackNumber = 1L + work.getDerivedWorkList().stream().filter(dw -> BooleanUtils.isNotTrue(dw.getExecDelete())).count();
	    derivedWork.setTrackNumber(trackNumber);
	}

	return derivedWork;
    }

}
