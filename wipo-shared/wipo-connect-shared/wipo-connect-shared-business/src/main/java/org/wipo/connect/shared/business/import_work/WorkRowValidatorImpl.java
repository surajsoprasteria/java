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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.el.CustomFunctions;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.import_model.TransactionTypeEnum;
import org.wipo.connect.common.import_model.WorkRow;
import org.wipo.connect.common.import_model.WorkRowTypeEnum;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.ConstantUtils;
import org.wipo.connect.common.utils.ConversionUtils;
import org.wipo.connect.enumerator.TerritoryFormulaValidationResultEnum;
import org.wipo.connect.enumerator.WccWorkImportExceptionCodeEnum;
import org.wipo.connect.shared.exchange.dto.impl.CreationClassFlat;
import org.wipo.connect.shared.exchange.dto.impl.DerivedView;
import org.wipo.connect.shared.exchange.dto.impl.DerivedViewName;
import org.wipo.connect.shared.exchange.dto.impl.Identifier;
import org.wipo.connect.shared.exchange.dto.impl.Name;
import org.wipo.connect.shared.exchange.dto.impl.RightTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.RoleFlat;
import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.enumerator.TitleTypeCodeEnum;
import org.wipo.connect.shared.exchange.utils.TerritoryFormulaUtils;

@Service
public class WorkRowValidatorImpl implements WorkRowValidator {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(WorkRowValidatorImpl.class);

    @Autowired
    private ImportWorkDAOHelper importWorkDAOHelper;

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
    public TransactionTypeEnum validateTransaction(List<WorkRow> workRows) throws WccWorkImportException {

	List<WorkRow> mainRow = workRows.stream().filter(m -> StringUtils.equals(m.getRowType(), WorkRowTypeEnum.MA.name())).collect(Collectors.toList());

	if (mainRow.size() > 1) {
	    throw new WccWorkImportException(WccWorkImportExceptionCodeEnum.ROW_MAIN_NOT_UNIQUE);
	} else if (mainRow.isEmpty()) {
	    throw new WccWorkImportException(WccWorkImportExceptionCodeEnum.MAIN_ROW_MANDATORY);
	} else if (StringUtils.isEmpty(mainRow.get(0).getTransaction())) {
	    throw new WccWorkImportException(WccWorkImportExceptionCodeEnum.TRANSACTION_EMPTY);
	} else if (!EnumUtils.isValidEnum(TransactionTypeEnum.class, mainRow.get(0).getTransaction())) {
	    throw new WccWorkImportException(WccWorkImportExceptionCodeEnum.TRANSACTION_UNKNOWN);
	}

	TransactionTypeEnum transaction = EnumUtils.getEnum(TransactionTypeEnum.class, mainRow.get(0).getTransaction());

	if (transaction == TransactionTypeEnum.UPDATE) {
	    if (!importWorkDAOHelper.checkMainIdIsPresent(mainRow.get(0).getWorkMainId())) {
		transaction = TransactionTypeEnum.INSERT;
	    }
	}

	return transaction;
    }

    @Override
    public void validateRows(TransactionTypeEnum transaction, List<WorkRow> entityRows) throws WccWorkImportException {

	Long workId = null;
	CreationClassFlat creationClass = new CreationClassFlat();
	Set<String> wdList = new HashSet<>();
	Set<String> componentsCodes = new HashSet<>();
	Set<String> identifierList = new HashSet<>();

	for (WorkRow row : entityRows) {

	    String rowType = row.getRowType();
	    WorkRowTypeEnum rowTypeEnum = WorkRowTypeEnum.UNKNOWN;

	    if (null != rowType && EnumUtils.isValidEnum(WorkRowTypeEnum.class, rowType)) {
		rowTypeEnum = WorkRowTypeEnum.valueOf(rowType);
	    }

	    switch (rowTypeEnum) {
		case MA:
		    LOGGER.info("ValidateRowEntity of type: {}", WorkRowTypeEnum.MA);
		    if (StringUtils.isEmpty(row.getErrorCode())) {
			WccWorkImportExceptionCodeEnum mainErrorCode = validateMainRow(transaction, row, creationClass);
			row.setErrorCode(mainErrorCode.getCode());
			if (TransactionTypeEnum.UPDATE.equals(transaction)) {
			    workId = importWorkDAOHelper.findWorkIdByMainId(row.getWorkMainId());
			}
		    } else {
			row.setErrorCode(row.getErrorCode());
		    }
		    break;
		case TI:
		    LOGGER.info("ValidateRowEntity of type: {}", WorkRowTypeEnum.TI);
		    if (StringUtils.isEmpty(row.getErrorCode())) {
			WccWorkImportExceptionCodeEnum titleErrorCode = validateTitleRow(row);
			row.setErrorCode(titleErrorCode.getCode());
		    } else {
			row.setErrorCode(row.getErrorCode());
		    }
		    break;
		case ID:
		    LOGGER.info("ValidateRowEntity of type: {}", WorkRowTypeEnum.ID);
		    if (StringUtils.isEmpty(row.getErrorCode())) {
			WccWorkImportExceptionCodeEnum identifierErrorCode = validateIdentifierRow(row, workId, identifierList);
			row.setErrorCode(identifierErrorCode.getCode());
		    } else {
			row.setErrorCode(row.getErrorCode());
		    }
		    break;
		case SH:
		    LOGGER.info("ValidateRowEntity of type: {}", WorkRowTypeEnum.SH);
		    if (StringUtils.isEmpty(row.getErrorCode())) {
			WccWorkImportExceptionCodeEnum sharedErrorCode = validateShareRow(row, creationClass.getCode());
			row.setErrorCode(sharedErrorCode.getCode());
		    } else {
			row.setErrorCode(row.getErrorCode());
		    }
		    break;
		case GR:
		    LOGGER.info("ValidateRowEntity of type: {}", WorkRowTypeEnum.GR);
		    LOGGER.info("GROUP not implemented");
		    break;
		case DA:
		    LOGGER.info("LoadRowEntity of type:{}", WorkRowTypeEnum.DA);
		    if (StringUtils.isEmpty(row.getErrorCode())) {
			WccWorkImportExceptionCodeEnum groupdErrorCode = validateDateRow(row, wdList);
			row.setErrorCode(groupdErrorCode.getCode());
		    } else {
			row.setErrorCode(row.getErrorCode());
		    }
		    break;
		case CO:
		    LOGGER.info("ValidateRowEntity of type: {}", WorkRowTypeEnum.CO);
		    if (StringUtils.isEmpty(row.getErrorCode())) {
			WccWorkImportExceptionCodeEnum groupdErrorCode = validateComponentRow(row, workId, creationClass, componentsCodes);
			row.setErrorCode(groupdErrorCode.getCode());
		    } else {
			row.setErrorCode(row.getErrorCode());
		    }
		    break;
		default:
		    LOGGER.info("RowEntity of type: {} not supported", rowType);
		    WccWorkImportExceptionCodeEnum unmanagedRowType = WccWorkImportExceptionCodeEnum.ROW_TYPE_EMPTY;
		    row.setErrorCode(unmanagedRowType.getCode());
		    break;
	    }

	}
	manageSharesCreatorRef(entityRows);

    }

    @Override
    public WccWorkImportExceptionCodeEnum validateMainRow(TransactionTypeEnum transaction, WorkRow row, CreationClassFlat cc) {
	WccWorkImportExceptionCodeEnum errorCode = WccWorkImportExceptionCodeEnum.NO_ERROR;

	if (StringUtils.isNotEmpty(row.getWorkMainId())) {
	    String mainId = StringUtils.remove(row.getWorkMainId(), '\"');
	    mainId = StringUtils.trimToNull(mainId);
	    if (StringUtils.isEmpty(mainId)) {
		return errorCode = WccWorkImportExceptionCodeEnum.MAIN_ID_EMPTY;
	    }
	} else {
	    return errorCode = WccWorkImportExceptionCodeEnum.MAIN_ID_EMPTY;
	}

	if (transaction == TransactionTypeEnum.DELETE) {
	    return errorCode = WccWorkImportExceptionCodeEnum.NO_ERROR;
	}

	if (StringUtils.isNotEmpty(row.getCreationClass())) {
	    String creationClass = row.getCreationClass();
	    creationClass = StringUtils.trimToNull(creationClass);
	    cc.setCode(creationClass);
	    if (null != creationClass) {
		Long creationClassId = workCodeMapper.getCreationClassIdByCode(creationClass);
		cc.setIdCreationClass(creationClassId);
		if (null == creationClassId) {
		    return errorCode = WccWorkImportExceptionCodeEnum.CREATION_CLASS_NOT_VALID;
		}
	    }
	}

	if (StringUtils.isNotEmpty(row.getCountryOfProduction())) {
	    String country = row.getCountryOfProduction();
	    country = StringUtils.trimToNull(country);
	    if (null != country) {
		if (workCodeMapper.getTerritoryCountryIdByCode(country) == null) {
		    errorCode = WccWorkImportExceptionCodeEnum.COUNTRY_CODE_ERROR;
		}
	    }
	}

	if (StringUtils.isNotEmpty(row.getWeight())) {
	    try {
		BigDecimal weight = new BigDecimal(row.getWeight());
		if (weight.compareTo(ConstantUtils.ZERO) < 0 || weight.compareTo(ConstantUtils.ONE_HUNDRED) > 0) {
		    return errorCode = WccWorkImportExceptionCodeEnum.WORK_PERCENTAGE;
		}
	    } catch (NumberFormatException e) {
		return errorCode = WccWorkImportExceptionCodeEnum.WORK_PERCENTAGE;
	    }
	}

	if (StringUtils.isNotEmpty(row.getTerritory())) {
	    String territory = StringUtils.remove(row.getTerritory(), '\"');
	    territory = StringUtils.trimToNull(territory);
	    try {
		if (territory.startsWith("I")) {
		    territory = TerritoryFormulaUtils.decodeTisnFormula(row.getTerritory(), workCodeMapper.getTerritoryList());
		}
		TerritoryFormulaValidationResultEnum formulaValidRes = TerritoryFormulaUtils.validateTerritoryFormula(workCodeMapper.getTerritoryHierarchy(), territory);

		switch (formulaValidRes) {
		    case INVALID_TERRITORY_CODE:
			return errorCode = WccWorkImportExceptionCodeEnum.TERRITORY_FORMULA_ERROR;
		    case INVALID_FORMULA_SYNTAX:
		    case INVALID_TERRITORY_CHAIN:
			return errorCode = WccWorkImportExceptionCodeEnum.TERRITORY_FORMULA_ERROR;
		    default:
			break;
		}

	    } catch (WccException e) {
		LOGGER.error("Error: ", e);
		return errorCode = WccWorkImportExceptionCodeEnum.GENERIC_ERROR;
	    }
	} else {
	    if (transaction == TransactionTypeEnum.INSERT) {
		return errorCode = WccWorkImportExceptionCodeEnum.TERRITORY_MANDATORY;
	    }
	}

	if (StringUtils.isNotEmpty(row.getDuration())) {
	    String duration = StringUtils.trimToEmpty(row.getDuration());
	    if (StringUtils.isNotEmpty(duration)) {
		if (!duration.matches(CustomFunctions.getRegexTimeCheck())) {
		    try {
			Integer.parseInt(duration);
		    } catch (NumberFormatException e) {
			return errorCode = WccWorkImportExceptionCodeEnum.DURATION_FORMAT_ERROR;
		    }
		}

	    }
	}

	return errorCode;
    }

    @Override
    public WccWorkImportExceptionCodeEnum validateTitleRow(WorkRow row) {
	WccWorkImportExceptionCodeEnum errorCode = WccWorkImportExceptionCodeEnum.NO_ERROR;

	if (StringUtils.isNotEmpty(row.getType())) {
	    String workTitleType = StringUtils.remove(row.getType(), '\"');
	    workTitleType = StringUtils.trimToNull(workTitleType);
	    if (StringUtils.isNotEmpty(workTitleType)) {
		if (!EnumUtils.isValidEnum(TitleTypeCodeEnum.class, workTitleType)) {
		    return errorCode = WccWorkImportExceptionCodeEnum.WORK_TITLE_TYPE_UNKNOWN;
		}
	    }
	} else {
	    return errorCode = WccWorkImportExceptionCodeEnum.WORK_TITLE_TYPE_UNKNOWN;
	}

	if (StringUtils.isNotEmpty(row.getWorkTitle())) {
	    String workTitle = StringUtils.remove(row.getWorkTitle(), '\"');
	    workTitle = StringUtils.trimToNull(workTitle);
	    if (StringUtils.isEmpty(workTitle)) {
		return errorCode = WccWorkImportExceptionCodeEnum.WORK_TITLE_EMPTY;
	    }
	} else {
	    return errorCode = WccWorkImportExceptionCodeEnum.WORK_TITLE_EMPTY;
	}

	return errorCode;
    }

    @Override
    public WccWorkImportExceptionCodeEnum validateIdentifierRow(WorkRow row, Long workId, Set<String> identifierList) {
	WccWorkImportExceptionCodeEnum errorCode = WccWorkImportExceptionCodeEnum.NO_ERROR;
	String identifierType;
	String identifierValue;

	if (StringUtils.isNotEmpty(row.getType())) {
	    identifierType = StringUtils.trimToNull(row.getType());
	    if (StringUtils.isNotEmpty(identifierType)) {
		Identifier identifier = workCodeMapper.getIdentifierByCode(row.getType());
		if (identifier == null) {
		    return errorCode = WccWorkImportExceptionCodeEnum.IDENTIFIER_CODE_UNKNOWN;
		}
	    } else {
		return errorCode = WccWorkImportExceptionCodeEnum.IDENTIFIER_CODE_EMPTY;
	    }
	} else {
	    return errorCode = WccWorkImportExceptionCodeEnum.IDENTIFIER_CODE_EMPTY;
	}

	if (StringUtils.isNotEmpty(row.getValue())) {
	    identifierValue = StringUtils.trimToNull(row.getValue());
	    if (StringUtils.isNotEmpty(identifierValue)) {
		String identifierKey = identifierType.toLowerCase() + "\u0000\u0000\u0000" + identifierValue.toLowerCase();
		if (identifierList.contains(identifierKey)) {
		    return errorCode = WccWorkImportExceptionCodeEnum.IDENTIFIER_VALUE_TYPE_ALREADY_PRESENT;
		}

		boolean isAlreadyPresent = importWorkDAOHelper.identifierValueAlreadyPresent(row.getType(), row.getValue(), workId);
		if (BooleanUtils.isTrue(isAlreadyPresent)) {
		    return errorCode = WccWorkImportExceptionCodeEnum.IDENTIFIER_VALUE_TYPE_ALREADY_PRESENT;
		} else {
		    identifierList.add(identifierKey);
		}
	    } else {
		return errorCode = WccWorkImportExceptionCodeEnum.IDENTIFIER_VALUE_EMPTY;
	    }
	} else {
	    return errorCode = WccWorkImportExceptionCodeEnum.IDENTIFIER_VALUE_EMPTY;
	}

	return errorCode;
    }

    @Override
    public WccWorkImportExceptionCodeEnum validateShareRow(WorkRow row, String ccCode) {
	WccWorkImportExceptionCodeEnum errorCode = WccWorkImportExceptionCodeEnum.NO_ERROR;

	if (StringUtils.isNotEmpty(row.getNameMainId())) {
	    String nameMainId = StringUtils.remove(row.getNameMainId(), '\"');
	    nameMainId = StringUtils.trimToNull(nameMainId);
	    if (StringUtils.isNotEmpty(nameMainId)) {
		Name name = importWorkDAOHelper.findByNameMainId(nameMainId);
		if (name == null) {
		    return errorCode = WccWorkImportExceptionCodeEnum.NAME_MAIN_ID_NOT_FOUND;
		}
	    }
	} else {
	    return errorCode = WccWorkImportExceptionCodeEnum.NAME_MAIN_ID_NOT_FOUND;
	}

	if (StringUtils.isNotEmpty(row.getRole())) {
	    RoleFlat crf = workCodeMapper.getWorkRoleMap().get(row.getRole());
	    if (crf == null) {
		return errorCode = WccWorkImportExceptionCodeEnum.ROLE_UNKNOWN;
	    } else {
		// Check if role is valid for work creation class
		if (crf.getCreationClassList().stream().filter(it -> it.getCode().equalsIgnoreCase(ccCode)).count() < 1) {
		    return errorCode = WccWorkImportExceptionCodeEnum.ROLE_NOT_VALID_FOR_CREATION_CLASS;
		}
	    }
	} else {
	    return errorCode = WccWorkImportExceptionCodeEnum.ROLE_UNKNOWN;
	}

	// TODO new Validation: for right category
	if (StringUtils.isNotEmpty(row.getRightCategory())) {
	    // Check if right category code is valid
	    RightTypeFlat rightCategory = workCodeMapper.getRightCategoryMap().get(row.getRightCategory());
	    if (null == rightCategory) {
		return errorCode = WccWorkImportExceptionCodeEnum.RIGHT_CATEGORY_ERROR;
	    } else {
		// Check if right category is valid for work creation class
		if (rightCategory.getCreationClassList().stream().filter(it -> StringUtils.equals(it.getCode(), ccCode)).count() < 1) {
		    return errorCode = WccWorkImportExceptionCodeEnum.RIGHT_CATEGORY_VALIDATION_ERROR;
		}
	    }
	} else {
	    return errorCode = WccWorkImportExceptionCodeEnum.RIGHT_CATEGORY_EMPTY;
	}

	// new Validation: for ShareValue
	if (StringUtils.isNotEmpty(row.getValue())) {
	    // Check if right category code is valid
	    // Check if right category is valid for work creation class
	    Pattern patt = Pattern.compile("^[+]?(\\d+([.]\\d+)?|(\\d+([,]\\d+)?))$");
	    Matcher matcher = patt.matcher(row.getValue());
	    if (!matcher.find()) {
		return errorCode = WccWorkImportExceptionCodeEnum.SHARE_ERROR;
	    }
	}
	return errorCode;
    }

    @Override
    public WccWorkImportExceptionCodeEnum validateDateRow(WorkRow row, Set<String> wdList) {
	WccWorkImportExceptionCodeEnum errorCode = WccWorkImportExceptionCodeEnum.NO_ERROR;

	String dateType = StringUtils.trimToNull(row.getType());
	if (dateType != null) {
	    if (wdList.contains(dateType)) {
		return errorCode = WccWorkImportExceptionCodeEnum.DATE_TYPE_DUPLICATE;
	    } else {
		String dateValue = StringUtils.trimToNull(row.getValue());
		if (dateValue != null) {
		    try {
			ft.parse(dateValue);
		    } catch (Exception e) {
			return errorCode = WccWorkImportExceptionCodeEnum.DATE_NOT_VALID;
		    }
		} else {
		    return errorCode = WccWorkImportExceptionCodeEnum.DATE_NOT_VALID;
		}

		wdList.add(dateType);
	    }
	} else {
	    return errorCode = WccWorkImportExceptionCodeEnum.DATE_TYPE_ERROR;
	}

	return errorCode;
    }

    @Override
    public WccWorkImportExceptionCodeEnum validateComponentRow(WorkRow row, Long parentId, CreationClassFlat creationClass, Set<String> componentsCodes) {
	WccWorkImportExceptionCodeEnum errorCode = WccWorkImportExceptionCodeEnum.NO_ERROR;

	String componentMainId = StringUtils.trimToNull(row.getWorkMainId());
	if (componentMainId != null) {
	    if (componentsCodes.contains(componentMainId)) {
		return errorCode = WccWorkImportExceptionCodeEnum.COMPONENT_DUPLICATE;
	    }

	    componentsCodes.add(componentMainId);

	    Long childrenId = importWorkDAOHelper.findWorkIdByMainId(componentMainId);
	    if (childrenId == null) {
		return errorCode = WccWorkImportExceptionCodeEnum.COMPONENT_NOT_FOUND;
	    } else if (parentId != null) {
		if (importWorkDAOHelper.checkDerivedWorkCycle(parentId, childrenId)) {
		    return errorCode = WccWorkImportExceptionCodeEnum.COMPONENT_CYCLING;
		}
	    }

	    String cc = importWorkDAOHelper.findCreationClassByWorkMainId(componentMainId);
	    if (!StringUtils.equals(creationClass.getCode(), cc) && !StringUtils.isEmpty(row.getWeight())) {
		return errorCode = WccWorkImportExceptionCodeEnum.COMPONENT_WEIGHT_MUST_BE_NULL;
	    }

	    String weight = StringUtils.trimToNull(row.getWeight());
	    if (weight != null) {
		try {
		    Long weightValue = Long.parseLong(weight);
		    if (weightValue < 0) {
			return errorCode = WccWorkImportExceptionCodeEnum.COMPONENT_WEIGHT;
		    }
		} catch (NumberFormatException e) {
		    return errorCode = WccWorkImportExceptionCodeEnum.COMPONENT_WEIGHT;
		}
	    }

	} else {
	    return errorCode = WccWorkImportExceptionCodeEnum.COMPONENT_NOT_EMPTY;
	}

	return errorCode;
    }

    @Override
    public void checkIfRejectAllRow(TransactionTypeEnum transaction, List<WorkRow> workRowList) throws WccWorkImportException {

	WorkRow mainWorkRow = workRowList.stream().filter(it -> WorkRowTypeEnum.MA.name().equals(it.getRowType())).findFirst().get();

	if (!StringUtils.equalsAny(mainWorkRow.getErrorCode(),
		WccWorkImportExceptionCodeEnum.DURATION_FORMAT_ERROR.getCode(),
		WccWorkImportExceptionCodeEnum.COUNTRY_CODE_ERROR.getCode(),
		WccWorkImportExceptionCodeEnum.GENRE_ERROR.getCode(),
		WccWorkImportExceptionCodeEnum.INSTRUMENT_UNKNOWN.getCode(),
		WccWorkImportExceptionCodeEnum.NO_ERROR.getCode(),
		WccWorkImportExceptionCodeEnum.AF_CREATION_CLASS.getCode())) {
	    WccWorkImportExceptionCodeEnum errorCode = WccWorkImportExceptionCodeEnum.getEnumByCode(mainWorkRow.getErrorCode());
	    throw new WccWorkImportException(errorCode);
	}

	if (TransactionTypeEnum.DELETE.equals(transaction)) {
	    if (workRowList.size() > 1) {
		throw new WccWorkImportException(WccWorkImportExceptionCodeEnum.DELETE_TRANSACTION_ERROR);
	    } else {

		Boolean isPresent = importWorkDAOHelper.checkMainIdIsPresent(mainWorkRow.getWorkMainId());
		if (BooleanUtils.isFalse(isPresent)) {
		    throw new WccWorkImportException(WccWorkImportExceptionCodeEnum.WORK_NOT_EXIST_ERROR);
		}

		Boolean isComponent = importWorkDAOHelper.checkWorkIsComponent(mainWorkRow.getWorkMainId());
		if (BooleanUtils.isTrue(isComponent)) {
		    throw new WccWorkImportException(WccWorkImportExceptionCodeEnum.WORK_IS_COMPONENT);
		}
	    }
	}

	// check if there are one or main row TITle with title type=OT
	if (!TransactionTypeEnum.DELETE.equals(transaction)) {
	    Boolean isPresent = importWorkDAOHelper.checkMainIdIsPresent(mainWorkRow.getWorkMainId());

	    if (TransactionTypeEnum.INSERT.equals(transaction) && BooleanUtils.isTrue(isPresent)) {
		throw new WccWorkImportException(WccWorkImportExceptionCodeEnum.MAINID_ALREADY_USE_ERROR);
	    } else if (TransactionTypeEnum.UPDATE.equals(transaction) && BooleanUtils.isTrue(isPresent)) {
		// niente
	    } else {
		List<WorkRow> resultTitleRow = workRowList.stream()
			.filter(it -> StringUtils.equals(WorkRowTypeEnum.TI.name(), it.getRowType())
				&& StringUtils.equals(TitleTypeCodeEnum.OT.name(), it.getType()))
			.collect(Collectors.toList());

		if (resultTitleRow.isEmpty()) {
		    throw new WccWorkImportException(WccWorkImportExceptionCodeEnum.WORK_TITLE_OT_MANDATORY);
		} else if (resultTitleRow.size() > 1) {
		    throw new WccWorkImportException(WccWorkImportExceptionCodeEnum.TOO_MANY_TITLE_OT);
		}

		// check if only one title OT has validationError
		if (!resultTitleRow.get(0).getErrorCode().equals(WccWorkImportExceptionCodeEnum.NO_ERROR.getCode())) {
		    WccWorkImportExceptionCodeEnum errorCode = WccWorkImportExceptionCodeEnum.getEnumByCode(resultTitleRow.get(0).getErrorCode());
		    throw new WccWorkImportException(errorCode);
		}
	    }
	}

	// check share
	if (TransactionTypeEnum.INSERT.equals(transaction) || TransactionTypeEnum.UPDATE.equals(transaction)) {

	    Map<String, BigDecimal> rightTypeShareMap = new HashMap<>();

	    for (WorkRow row : workRowList) {

		if (!StringUtils.isEmpty(row.getRowType())) {
		    if (row.getRowType().equals(WorkRowTypeEnum.SH.name()) && row.getErrorCode().equals(WccWorkImportExceptionCodeEnum.NO_ERROR.getCode()) && StringUtils.isNotEmpty(row.getValue())
			    && StringUtils.isNotEmpty(row.getRightCategory())) {

			if (null == rightTypeShareMap.get(row.getRightCategory())) {
			    rightTypeShareMap.put(row.getRightCategory(), new BigDecimal(row.getValue()));
			} else {
			    rightTypeShareMap.get(row.getRightCategory()).add(new BigDecimal(row.getValue()));
			}
		    }
		}

	    }

	}

	// Check if creation class is present in main row
	if (TransactionTypeEnum.INSERT.equals(transaction)) {
	    if (StringUtils.isEmpty(mainWorkRow.getCreationClass())) {
		throw new WccWorkImportException(WccWorkImportExceptionCodeEnum.CREATION_CLASS_EMPTY);
	    }
	} else if (TransactionTypeEnum.UPDATE.equals(transaction)) {
	    if (StringUtils.isBlank(mainWorkRow.getCreationClass())) {
		throw new WccWorkImportException(WccWorkImportExceptionCodeEnum.CREATION_CLASS_EMPTY);
	    } else if (StringUtils.isNotEmpty(mainWorkRow.getWorkMainId())) {
		String cc = importWorkDAOHelper.findCreationClassByWorkMainId(mainWorkRow.getWorkMainId());
		if (StringUtils.isNotBlank(cc) && !StringUtils.equalsIgnoreCase(mainWorkRow.getCreationClass(), cc)) {
		    throw new WccWorkImportException(WccWorkImportExceptionCodeEnum.CREATION_CLASS_DIFFERENT);
		}
	    }
	}

    }

    @Override
    public Work completeDerivedView(Work work) throws WccWorkImportException {

	if (work.getDerivedViewList().isEmpty()) {
	    work.getDerivedViewList().add(new DerivedView());
	    return work;
	}

	if (work.getDerivedViewList().size() > 1) {
	    throw new IllegalStateException("Derived View should be only 1, found " + work.getDerivedViewList().size());
	}

	DerivedView dv = work.getDerivedViewList().get(0);

	Map<String, Long> refIndexMap = new HashMap<>();

	Map<String, DerivedViewName> mapDvn = new HashMap<>();
	Long refIndex = 1L;
	for (DerivedViewName dvn : dv.getDerivedViewNameList()) {
	    StringBuilder key = new StringBuilder();
	    key.append(dvn.getName().getMainId());
	    key.append("\u0000\u0000\u0000");
	    key.append(dvn.getFkRole());

	    if (mapDvn.get(key.toString()) == null) {
		dvn.setRefIndex(refIndex);
		refIndex++;
		mapDvn.put(key.toString(), dvn);
		refIndexMap.put(dvn.getName().getMainId(), dvn.getRefIndex());
	    } else {
		mapDvn.get(key.toString()).getDerivedViewNameShareList().addAll(dvn.getDerivedViewNameShareList());
	    }
	}

	for (DerivedViewName dvn : dv.getDerivedViewNameList()) {
	    if (StringUtils.isEmpty(dvn.getCreatorRefMainId())) {
		continue;
	    }
	    Long creatorRef = refIndexMap.get(dvn.getCreatorRefMainId());

	    if (creatorRef == null) {
		throw new WccWorkImportException(WccWorkImportExceptionCodeEnum.CREATOR_REF_ERROR);
	    }

	    dvn.setCreatorRefIndex(creatorRef);
	}

	work.getDerivedViewList().get(0).setDerivedViewNameList(new ArrayList<>(mapDvn.values()));
	return work;
    }

    private void manageSharesCreatorRef(List<WorkRow> workRows) {

	// only share rows valid
	List<WorkRow> resultShare = workRows.stream()
		.filter(it -> it.getRowType().equals(WorkRowTypeEnum.SH.name()) && it.getErrorCode().equals(WccWorkImportExceptionCodeEnum.NO_ERROR.getCode()))
		.collect(Collectors.toList());

	for (WorkRow row : resultShare) {
	    if (StringUtils.isNotEmpty(row.getCreatorRef())) {
		List<WorkRow> resultFound = resultShare.stream().filter(it -> StringUtils.isNotEmpty(it.getNameMainId()) && row.getCreatorRef().equals(it.getNameMainId()))
			.collect(Collectors.toList());
		if (resultFound.size() == 0) {
		    row.setErrorCode(WccWorkImportExceptionCodeEnum.CREATOR_REF_ERROR.getCode());
		}
	    }
	}

    }

}
