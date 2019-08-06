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

package org.wipo.connect.shared.web.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.wipo.connect.common.exception.TerritoryValidationException;
import org.wipo.connect.common.exception.WccControllerException;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.querypagination.PageInfo;
import org.wipo.connect.common.utils.ConstantUtils;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.enumerator.TerritoryFormulaValidationResultEnum;
import org.wipo.connect.shared.exchange.business.CommonBusiness;
import org.wipo.connect.shared.exchange.business.ParametersManagementBusiness;
import org.wipo.connect.shared.exchange.business.WorkBusiness;
import org.wipo.connect.shared.exchange.dto.impl.Cmo;
import org.wipo.connect.shared.exchange.dto.impl.CreationClassFlat;
import org.wipo.connect.shared.exchange.dto.impl.DerivedView;
import org.wipo.connect.shared.exchange.dto.impl.DerivedViewName;
import org.wipo.connect.shared.exchange.dto.impl.DerivedViewNameShare;
import org.wipo.connect.shared.exchange.dto.impl.Name;
import org.wipo.connect.shared.exchange.dto.impl.ReferenceFlat;
import org.wipo.connect.shared.exchange.dto.impl.RoleFlat;
import org.wipo.connect.shared.exchange.dto.impl.Title;
import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.dto.impl.WorkPerformer;
import org.wipo.connect.shared.exchange.dto.impl.WorkStatusFlat;
import org.wipo.connect.shared.exchange.enumerator.ExportTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.FileFormatEnum;
import org.wipo.connect.shared.exchange.enumerator.MassiveActionsEnum;
import org.wipo.connect.shared.exchange.enumerator.ReferenceTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.RequestResultTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.TitleTypeCodeEnum;
import org.wipo.connect.shared.exchange.enumerator.WorkSourceTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.WorkStatusEnum;
import org.wipo.connect.shared.exchange.enumerator.WorkTypeEnum;
import org.wipo.connect.shared.exchange.exception.WorkAsComponentException;
import org.wipo.connect.shared.exchange.utils.DTOUtils;
import org.wipo.connect.shared.exchange.utils.TerritoryFormulaUtils;
import org.wipo.connect.shared.exchange.utils.importexport.ExportBean;
import org.wipo.connect.shared.exchange.vo.MassiveActionVO;
import org.wipo.connect.shared.exchange.vo.RequestResultVO;
import org.wipo.connect.shared.exchange.vo.RightOwnerSearchVO;
import org.wipo.connect.shared.exchange.vo.RightOwnerVO;
import org.wipo.connect.shared.exchange.vo.TerritoryHierarchyVO;
import org.wipo.connect.shared.exchange.vo.WorkDetailVO;
import org.wipo.connect.shared.exchange.vo.WorkSearchResultVO;
import org.wipo.connect.shared.exchange.vo.WorkSearchVO;
import org.wipo.connect.shared.exchange.vo.WorkViewCmoVO;
import org.wipo.connect.shared.exchange.vo.WorkViewDetailVO;
import org.wipo.connect.shared.exchange.vo.WorkViewShareVO;
import org.wipo.connect.shared.exchange.vo.WorkViewVO;
import org.wipo.connect.shared.web.utils.StatusColorManager;

/**
 * The Class WorkController.
 */
@Controller
@RequestMapping("work")
public class WorkController extends BaseController {

    @Autowired
    private WorkBusiness workBusiness;

    @Autowired
    private CommonBusiness commonBusiness;

    @Autowired
    private StatusColorManager statusColorManager;

    /** The message source. */
    @Autowired
    private MessageSource messageSource;

    /** The parameters management business. */
    @Autowired
    private ParametersManagementBusiness parametersManagementBusiness;

    @RequestMapping("search")
    public ModelAndView search(@RequestParam(defaultValue = "false") boolean autoSearchMode, @RequestParam(defaultValue = "false") boolean singleSelectMode,
	    @RequestParam(defaultValue = "false") boolean multipleSelectMode, @RequestParam(defaultValue = "false") boolean showInNewTab, WorkSearchVO vo, BindingResult results)
	    throws WccControllerException {

	try {

	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    ModelAndView mv = new ModelAndView();

	    DTOUtils.cleanDeletableCollection(vo.getRightOwnerList());

	    vo.getRightOwnerListSearch().add(new RightOwnerVO());
	    mv.addObject("searchVO", vo);
	    mv.addObject("autoSearchMode", autoSearchMode);
	    mv.addObject("singleSelectMode", singleSelectMode);
	    mv.addObject("multipleSelectMode", multipleSelectMode);
	    mv.addObject("showInNewTab", showInNewTab);
	    mv.addObject("roleList", commonBusiness.getRoleList());
	    mv.addObject("statusList", workBusiness.findAllWorkStatus());
	    mv.addObject("workTypeList", WorkTypeEnum.values());
	    mv.addObject("sourceTypeList", commonBusiness.getReferenceByCode(ReferenceTypeEnum.WORK_SOURCE_TYPE.name()));

	    mv.addObject("territoryMap", getAsJson(commonBusiness.getCountriesFromTerritoryMap()));
	    mv.addObject("workStatusMap", getAsJson(this.workBusiness.findAllWorkStatusMap()));
	    mv.addObject("statusColorMap", getAsJson(statusColorManager.getWorkStatusColorMap()));

	    mv.addObject("managedCreationClassList", this.commonBusiness.getCreationClassFlatList());
	    mv.addObject("countryOfProductionList", commonBusiness.getCountriesFromTerritoryList(false));

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    @RequestMapping("find.json")
    public ModelAndView find(WorkSearchVO vo, HttpServletRequest req, BindingResult results) throws WccControllerException {

	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    ModelAndView mv = new ModelAndView();
	    if (StringUtils.isNotBlank(vo.getTerritoryFormula())) {
		TerritoryHierarchyVO th = commonBusiness.getTerritoryHierarchy();
		TerritoryFormulaValidationResultEnum tfValid = TerritoryFormulaUtils.validateTerritoryFormula(th, vo.getTerritoryFormula());

		if (!tfValid.equals(TerritoryFormulaValidationResultEnum.VALID)) {
		    throw new TerritoryValidationException("Formula was not valid!");
		}
	    }

	    Map<Long, RoleFlat> roleMap = this.commonBusiness.getRoleMap();
	    mv.addObject("roleMap", roleMap);

	    DTOUtils.cleanDeletableCollection(vo.getRightOwnerList());

	    for (RightOwnerSearchVO ro : vo.getRightOwnerList()) {
		Long idRole = ro.getIdRole();
		if (ro.getIdRole() != null) {
		    ro.setRoleCode(roleMap.get(idRole).getCode().toString());
		}
	    }
	    manageROList(vo);
	    vo.setDisableOriginCheck(true);
	    vo.setLightSearch(true);

	    PageInfo pageInfo = super.extractPageInfo(req);
	    vo.setPaginationStart(pageInfo.getStart());
	    vo.setPaginationLength(pageInfo.getLength());
	    vo.setPaginationDraw(pageInfo.getDraw());
	    vo.setOrderByExpression(pageInfo.getOrderByExpression());

	    WorkSearchResultVO pageResult = this.workBusiness.findWork(vo);
	    mv.addAllObjects(pageResult.asMap());

	    Map<String, CreationClassFlat> ccMap = commonBusiness.getCreationClassFlatList().stream().collect(Collectors.toMap(CreationClassFlat::getCode, Function.identity()));
	    mv.addObject("ccMap", ccMap);

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    private void manageROList(WorkSearchVO vo) {
	List<RightOwnerVO> list = new ArrayList<>();

	for (RightOwnerVO item : vo.getRightOwnerListSearch()) {
	    if (item.getNameMainId() == null && item.getRightOwnerValue() == null) {
		continue;
	    } else {
		if (BooleanUtils.isFalse(item.getExecDelete())) {
		    list.add(item);
		}
	    }

	}

	vo.setRightOwnerListSearch(list);
    }

    @RequestMapping("detail")
    public ModelAndView detail(@RequestParam(defaultValue = "false") boolean readOnlyMode, @RequestParam(defaultValue = "false") boolean showInNewTab,
	    WorkSearchVO vo, BindingResult results, @RequestParam(required = false) Long sharedId) throws WccControllerException {

	try {

	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    ModelAndView mv = new ModelAndView();

	    Work workVO = generateWorkDTO(vo);
	    List<WorkStatusFlat> statusList = this.workBusiness.findAllWorkStatus();
	    List<ReferenceFlat> workTitleTypeList = this.commonBusiness.getReferenceByCode(ReferenceTypeEnum.TITLE_TYPE.name());

	    if (showInNewTab) {
		workVO = this.workBusiness.findByMainId(workVO.getMainId());
	    } else {
		workVO = this.workBusiness.findById(workVO.getId());
	    }

	    WorkDetailVO detailVO = generateWorkDetailVO(workVO, null);
	    // Activity A_53E: new version
	    if (detailVO.getWorkViewList().isEmpty()) {
		WorkViewVO wvVO = new WorkViewVO();
		detailVO.getWorkViewList().add(wvVO);
	    }

	    if (StringUtils.equals(workVO.getStatusCode(), WorkStatusEnum.DELETED.name())) {
		readOnlyMode = true;
	    }

	    List<ReferenceFlat> sourceTypeList = this.commonBusiness.getReferenceByCode(ReferenceTypeEnum.WORK_SOURCE_TYPE.name());

	    // set work source type (in shared is always import)
	    Map<Long, ReferenceFlat> sourceTypeMap = this.commonBusiness.getReferenceMapByCode(ReferenceTypeEnum.WORK_SOURCE_TYPE.name());
	    mv.addObject("sourceTypeMap", sourceTypeMap);
	    if (StringUtils.equals(sourceTypeMap.get(workVO.getFkSourceType()).getCode(), WorkSourceTypeEnum.WRK_SRC_IMPORT.name())) {
		if (workVO.getId() != null) {
		    detailVO.setImportCode(workBusiness.getImportCodeByWorkId(workVO.getId()));
		}
	    }

	    List<ReferenceFlat> workTypeList = this.commonBusiness.getReferenceByCode(ReferenceTypeEnum.WORK_TYPE.name());
	    mv.addObject("workTypeList", workTypeList);

	    if (null == workVO.getFkType()) {
		for (ReferenceFlat flat : workTypeList) {
		    if (flat.getRefCode().equalsIgnoreCase(ConstantUtils.WORK_TYPE_CODE_DEFAULT)) {
			workVO.setFkType(flat.getId());
		    }
		}
	    }

	    mv.addObject("vo", detailVO);

	    mv.addObject("rightTypeList", this.commonBusiness.getRightTypeListByCC(Arrays.asList(workVO.getCreationClassCode())));
	    mv.addObject("sourceTypeList", sourceTypeList);
	    mv.addObject("sourceTypeDefaultValue", ConstantUtils.SRC_TYPE_LOCAL_REGISTRATION);
	    mv.addObject("workTitleTypeMap", DTOUtils.listToMapById(workTitleTypeList));
	    mv.addObject("statusMap", DTOUtils.listToMapById(statusList));
	    mv.addObject("statusColorMap", statusColorManager.getWorkStatusColorMap());
	    mv.addObject("showInNewTab", showInNewTab);

	    List<RoleFlat> roleList = this.commonBusiness.getRoleList();
	    mv.addObject("roleList", roleList);
	    mv.addObject("roleMap", DTOUtils.listToMapRole(roleList, null));
	    mv.addObject("readOnlyMode", readOnlyMode);
	    mv.addObject("excerptTypeList", this.commonBusiness.getReferenceByCode(ReferenceTypeEnum.CWR_EXCERPT_TYPE.name()));
	    mv.addObject("compositeTypeList", this.commonBusiness.getReferenceByCode(ReferenceTypeEnum.CWR_COMPOSITE_TYPE.name()));

	    mv.addObject("countryOfProductionList", commonBusiness.getCountriesFromTerritoryList(false));
	    mv.addObject("managedCreationClassList", this.commonBusiness.getCreationClassFlatList());

	    boolean hidePerformerSection = false;
	    if (detailVO.getWork().getFkCreationClass() != null) {
		hidePerformerSection = parametersManagementBusiness.getPerformerConfigurationByFkCreationClass(detailVO.getWork().getFkCreationClass());
	    }
	    mv.addObject("hidePerformerSection", hidePerformerSection);

	    Map<String, CreationClassFlat> ccMap = commonBusiness.getCreationClassFlatList().stream().collect(Collectors.toMap(CreationClassFlat::getCode, Function.identity()));
	    mv.addObject("ccMap", ccMap);

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    private Work generateWorkDTO(WorkSearchVO searchVO) throws WccException {
	Work work = new Work();
	work.setId(searchVO.getId());

	if (StringUtils.isNotEmpty(searchVO.getMainId())) {
	    work.setMainId(searchVO.getMainId());
	}

	Map<Long, RoleFlat> roleMap = this.commonBusiness.getRoleMap();

	if (StringUtils.isNotEmpty(searchVO.getTitle())) {
	    Title title = new Title();
	    title.setDescription(searchVO.getTitle());
	    title.setTypeCode(TitleTypeCodeEnum.OT.name());
	    work.getTitleList().add(title);
	}

	DerivedView dv = new DerivedView();
	Long refIndex = 1L;
	for (RightOwnerSearchVO ro : searchVO.getRightOwnerList()) {
	    DerivedViewName dvn = new DerivedViewName();
	    Name name = new Name();

	    name.setId(ro.getIdName());
	    name.setName(ro.getName());
	    dvn.setName(name);

	    if (ro.getIdRole() != null) {
		RoleFlat crwRole = roleMap.get(ro.getIdRole());
		dvn.setFkRole(crwRole.getId());
		dvn.setRoleCode(crwRole.getCode().toString());
	    }

	    dvn.setRefIndex(refIndex);
	    refIndex++;

	    dv.getDerivedViewNameList().add(dvn);
	}

	work.getDerivedViewList().clear();
	work.getDerivedViewList().add(dv);

	return work;
    }

    private WorkDetailVO generateWorkDetailVO(Work work, Work workInConflict) throws ParseException, InstantiationException, IllegalAccessException, WccException {
	WorkDetailVO objOut = new WorkDetailVO();

	objOut.setWork(work);
	objOut.setWorkInConflict(workInConflict);
	// Activity A_53E: new version
	List<WorkViewVO> wVOList = generateWorkViewListVO(work.getDerivedViewList());
	objOut.setWorkViewList(wVOList);

	if (work.getTitleList().isEmpty()) {
	    work.getTitleList().add(new Title());
	}

	performerListSort(objOut.getWork().getWorkPerformerList());
	titleListSort(objOut.getWork().getTitleList());

	return objOut;
    }

    private void performerListSort(List<WorkPerformer> performerList) {
	Comparator<WorkPerformer> comparator = new Comparator<WorkPerformer>() {
	    @Override
	    public int compare(WorkPerformer c1, WorkPerformer c2) {
		return c1.getPerformerName().compareTo(c2.getPerformerName());
	    }
	};

	Collections.sort(performerList, comparator);
    }

    // sort title list with OT in first position and the other sort by type code
    private void titleListSort(List<Title> titleList) {
	Comparator<Title> comparator = new Comparator<Title>() {
	    @Override
	    public int compare(Title c1, Title c2) {
		if (c1.getTypeCode().equals(TitleTypeCodeEnum.OT.name())) {
		    return -1;
		} else {
		    if (c2.getTypeCode().equals(TitleTypeCodeEnum.OT.name())) {
			return 1;
		    } else {
			return c1.getTypeCode().compareTo(c2.getTypeCode());
		    }
		}
	    }
	};

	Collections.sort(titleList, comparator);
    }

    /**
     * Delete work.
     *
     * @param idWork
     *                   the id work
     * @return the request result VO
     * @throws WccControllerException
     *                                    the wcc controller exception
     */
    @ResponseBody
    @RequestMapping("deleteWork.json")
    public RequestResultVO deleteWork(@RequestParam Long idWork, Locale locale) throws WccControllerException {
	RequestResultVO requestResult;

	try {

	    workBusiness.logicallyDeleteWork(idWork);
	    requestResult = new RequestResultVO();
	} catch (WorkAsComponentException e) {
	    String errorMsg = "";

	    errorMsg = this.messageSource.getMessage("work.work-used-as-component", null, locale);
	    requestResult = new RequestResultVO(errorMsg);
	    requestResult.setType(RequestResultTypeEnum.WARN.name());
	    LOGGER.error("Work is used as Component in one or more Work", e);
	} catch (Exception e) {
	    requestResult = new RequestResultVO(e);
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	}
	return requestResult;
    }

    /**
     * Massive action logically deleted.
     *
     * @param vo
     *                    the vo
     * @param results
     *                    the results
     * @return the request result VO
     */
    @ResponseBody
    @RequestMapping("massiveAction/delete.json")
    public RequestResultVO massiveActionLogicallyDeleted(MassiveActionVO vo, BindingResult results) {
	RequestResultVO output;
	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    Integer check = workBusiness.executeMassiveAction(vo.getIdList(), MassiveActionsEnum.WORK_DELETE);

	    output = new RequestResultVO();
	    if (check.intValue() != vo.getIdList().size()) {
		output.setType(RequestResultTypeEnum.WARN.name());
		output.setUseGenericError(false);
		output.setMessage(messageSource.getMessage("global.multi-update-count-result", new Object[] { check, vo.getIdList().size() }, getCurrentLocale()));
	    }

	} catch (Exception e) {
	    output = new RequestResultVO(e);
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	}
	return output;
    }

    // Activity A_53E adapting ownershipView to WorkViewVO as contextual view
    private List<WorkViewVO> generateWorkViewListVO(List<DerivedView> derivedViewList) throws WccException {
	List<WorkViewVO> workViewListVO = new ArrayList<>();
	for (DerivedView dv : derivedViewList) {
	    WorkViewVO wvVO = new WorkViewVO();
	    wvVO.setId(dv.getId());
	    if (!StringUtils.isBlank(dv.getTerritoryFormula())) {
		wvVO.setTerritoryFormula(dv.getTerritoryFormula());
	    }
	    for (DerivedViewName dvName : dv.getDerivedViewNameList()) {
		WorkViewDetailVO wvdVO = new WorkViewDetailVO();
		wvdVO.setCreatorRefIndex(dvName.getCreatorRefIndex());
		wvdVO.setCreatorRefMainId(dvName.getCreatorRefMainId());
		wvdVO.setFkRole(dvName.getFkRole());
		wvdVO.setFullName(dvName.getName().getFullName());
		wvdVO.setId(dvName.getId());
		wvdVO.setIdName(dvName.getName().getId());
		// wvdVO.setInterestedPartyType(dvName.getInterestedPartyType());
		wvdVO.setNameMainId(dvName.getName().getMainId());
		wvdVO.setRefIndex(dvName.getRefIndex());
		if (null != dvName.getSourceType()) {
		    wvdVO.setSourceType(dvName.getSourceType());
		} else {
		    wvdVO.setSourceType(ConstantUtils.SRC_TYPE_LOCAL_REGISTRATION);
		}

		for (DerivedViewNameShare dvShare : dvName.getDerivedViewNameShareList()) {
		    WorkViewShareVO wvsVO = new WorkViewShareVO();
		    wvsVO.setShareValue(dvShare.getShareValue());
		    wvsVO.setRightTypeCode(dvShare.getRightTypeCode());
		    for (Cmo cmo : dvShare.getCmoList()) {
			WorkViewCmoVO wvcVO = new WorkViewCmoVO();
			wvcVO.setAcronym(cmo.getAcronym());
			wvcVO.setCode(cmo.getCode());
			wvcVO.setId(cmo.getId());
			wvcVO.setName(cmo.getName());
			wvsVO.getViewCmoVO().add(wvcVO);
		    }
		    wvdVO.getViewShareVOMap().put(wvsVO.getRightTypeCode(), wvsVO);
		}
		wvVO.getViewDetailVO().add(wvdVO);
	    }
	    workViewListVO.add(wvVO);
	}

	for (WorkViewVO wv : workViewListVO) {
	    wv.getViewDetailVO().sort(new Comparator<WorkViewDetailVO>() {
		@Override
		public int compare(WorkViewDetailVO o1, WorkViewDetailVO o2) {
		    Long i1 = ObjectUtils.defaultIfNull(o1.getRefIndex(), 0L);
		    Long i2 = ObjectUtils.defaultIfNull(o2.getRefIndex(), 0L);
		    return i1.compareTo(i2);
		}
	    });
	}

	return workViewListVO;
    }

    @RequestMapping(value = "/exportWork")
    public ModelAndView exportWork(MassiveActionVO vo, BindingResult results) throws WccControllerException {
	ModelAndView mv;

	if (results.hasErrors()) {
	    throw new IllegalArgumentException("Error in exportWork - " + results);
	}

	try {

	    ExportBean exportBean = workBusiness.getExportBean(vo.getIdList(), ExportTypeEnum.WORK_LIST_VIEW_EXPORT, FileFormatEnum.CSV);

	    mv = new ModelAndView("download-view");
	    mv.addObject("download", exportBean);
	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    @RequestMapping(value = "/fullExport.json")
    public RequestResultVO fullWorkExport(@RequestParam(required = true) String fileFormat) throws WccControllerException {
	RequestResultVO output = new RequestResultVO();
	try {
	    FileFormatEnum fileFormatEnum = FileFormatEnum.valueOf(fileFormat);
	    workBusiness.getFullExportBean(fileFormatEnum);

	} catch (Exception e) {
	    output = new RequestResultVO(e);
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	}
	return output;
    }

}