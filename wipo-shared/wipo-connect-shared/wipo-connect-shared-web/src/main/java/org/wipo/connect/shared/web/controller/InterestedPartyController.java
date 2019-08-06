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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.wipo.connect.common.exception.WccControllerException;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.querypagination.PageInfo;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.shared.exchange.business.CommonBusiness;
import org.wipo.connect.shared.exchange.business.InterestedPartyBusiness;
import org.wipo.connect.shared.exchange.business.ReferenceBusiness;
import org.wipo.connect.shared.exchange.dto.impl.Affiliation;
import org.wipo.connect.shared.exchange.dto.impl.AffiliationDomain;
import org.wipo.connect.shared.exchange.dto.impl.CreationClassFlat;
import org.wipo.connect.shared.exchange.dto.impl.InterestedParty;
import org.wipo.connect.shared.exchange.dto.impl.InterestedPartyStatusFlat;
import org.wipo.connect.shared.exchange.dto.impl.IpiRightTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.IpiRoleFlat;
import org.wipo.connect.shared.exchange.dto.impl.Name;
import org.wipo.connect.shared.exchange.dto.impl.Territory;
import org.wipo.connect.shared.exchange.enumerator.DomainEnum;
import org.wipo.connect.shared.exchange.enumerator.ExportTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.FileFormatEnum;
import org.wipo.connect.shared.exchange.enumerator.GenderTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.InterestedPartyStatusEnum;
import org.wipo.connect.shared.exchange.enumerator.MassiveActionsEnum;
import org.wipo.connect.shared.exchange.enumerator.NameTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.RequestResultTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.TerritoryOrderTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.TypeEnum;
import org.wipo.connect.shared.exchange.exception.IpDeleteErrorException;
import org.wipo.connect.shared.exchange.utils.DTOUtils;
import org.wipo.connect.shared.exchange.utils.importexport.ExportBean;
import org.wipo.connect.shared.exchange.vo.AffiliationIpVO;
import org.wipo.connect.shared.exchange.vo.InterestedPartyDetailVO;
import org.wipo.connect.shared.exchange.vo.InterestedPartySearchResultVO;
import org.wipo.connect.shared.exchange.vo.InterestedPartySearchVO;
import org.wipo.connect.shared.exchange.vo.MassiveActionVO;
import org.wipo.connect.shared.exchange.vo.RequestResultVO;
import org.wipo.connect.shared.web.utils.StatusColorManager;

/**
 * The Class InterestedPartyController.
 */
@Controller
@RequestMapping("interestedParty")
public class InterestedPartyController extends BaseController {

    @Value("#{configProperties['ip_grid.column_link'].split(',')}")
    private List<Integer> ipGridColumnLinkList;

    @Autowired
    private InterestedPartyBusiness interestedPartyBusiness;

    @Autowired
    private CommonBusiness commonBusiness;

    @Autowired
    private ReferenceBusiness referenceBusiness;

    @Autowired
    private StatusColorManager statusColorManager;

    /** The message source. */
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "search")
    public ModelAndView search(HttpServletRequest req, @RequestParam(defaultValue = "false") boolean autoSearchMode,
	    InterestedPartySearchVO vo, BindingResult results) throws WccControllerException {
	ModelAndView mv;
	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Error in search - " + results);
	    }

	    mv = new ModelAndView();

	    mv.addObject("autoSearchMode", autoSearchMode);
	    mv.addObject("cmoList", this.referenceBusiness.getCmoList());
	    mv.addObject("territoryList", this.commonBusiness.getCountriesFromTerritoryList(false));
	    mv.addObject("statusList", this.interestedPartyBusiness.findAllIPStatus());
	    mv.addObject("searchVO", vo);
	    mv.addObject("domain", Arrays.asList(DomainEnum.values()));
	    mv.addObject("statusColorMap", getAsJson(statusColorManager.getIpStatusColorMap()));
	    mv.addObject("interestedPartyTypeList", TypeEnum.values());
	    mv.addObject("creationClassList", this.commonBusiness.getCreationClassFlatList());

	    mv.addObject("statusMap", getAsJson(this.interestedPartyBusiness.findAllIPStatus().stream()
		    .collect(Collectors.toMap(InterestedPartyStatusFlat::getCode, InterestedPartyStatusFlat::getValue))));

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
	return mv;
    }

    @RequestMapping("findInterestedParty.json")
    public ModelAndView findInterestedParty(InterestedPartySearchVO vo, HttpServletRequest req, BindingResult results) throws WccControllerException {

	try {

	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Error in " + WccUtils.getMethodName() + " - " + results);
	    }

	    vo.setDisableOriginCheck(true);
	    vo.setLightSearch(true);
	    PageInfo pageInfo = super.extractPageInfo(req);
	    vo.setPaginationStart(pageInfo.getStart());
	    vo.setPaginationLength(pageInfo.getLength());
	    vo.setPaginationDraw(pageInfo.getDraw());
	    vo.setOrderByExpression(pageInfo.getOrderByExpression());
	    InterestedPartySearchResultVO pageResult = this.interestedPartyBusiness.findInterestedParty(vo);

	    ModelAndView mv = new ModelAndView();
	    mv.addAllObjects(pageResult.asMap());

	    Map<String, CreationClassFlat> ccMap = commonBusiness.getCreationClassFlatList().stream().collect(Collectors.toMap(CreationClassFlat::getCode, Function.identity()));
	    mv.addObject("ccMap", ccMap);

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    @RequestMapping("detail")
    public ModelAndView detail(@RequestParam(defaultValue = "false") boolean readOnlyMode, @RequestParam(required = false) Long nameId,
	    InterestedPartySearchVO vo, @RequestParam(defaultValue = "false") boolean showInNewTab, BindingResult results) throws WccControllerException {

	try {

	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Error in " + WccUtils.getMethodName() + " - " + results);
	    }

	    if (nameId != null) {
		Long idIp = this.interestedPartyBusiness.findIpByName(nameId);
		vo.setId(idIp);
	    }

	    ModelAndView mv = new ModelAndView();
	    InterestedParty ip;
	    if (showInNewTab) {
		ip = this.interestedPartyBusiness.findByMainId(vo.getMainId());
	    } else {
		ip = this.interestedPartyBusiness.findByIdIp(vo.getId());
	    }

	    if (StringUtils.equals(ip.getStatusCode(), InterestedPartyStatusEnum.DELETED.name())) {
		readOnlyMode = true;
	    }

	    Map<Long, InterestedPartyStatusFlat> statusMap = DTOUtils.listToMapById(this.interestedPartyBusiness.findAllIPStatus());
	    List<Territory> territoryList = this.commonBusiness.getCountriesFromTerritoryList(false);

	    String cmoDesc = null;

	    if (ip.getAffiliatedCmos().size() == 1) {
		cmoDesc = ip.getAffiliatedCmos().iterator().next();
	    } else if (ip.getAffiliatedCmos().size() > 1) {
		cmoDesc = messageSource.getMessage("interestedPartyAffiliation.multiple", null, getCurrentLocale());
	    } else {
		cmoDesc = messageSource.getMessage("interestedPartyAffiliation.not-affiliated", null, getCurrentLocale());
	    }

	    InterestedPartyDetailVO detailVO = new InterestedPartyDetailVO();

	    nameListSort(ip.getNameList());
	    detailVO.setInterestedParty(ip);

	    mv.addObject("affiliationList", ip.getAffiliationList());
	    mv.addObject("statusMap", statusMap);
	    mv.addObject("territoryList", territoryList);
	    mv.addObject("statusColorMap", statusColorManager.getIpStatusColorMap());

	    mv.addObject("cmoDesc", cmoDesc);
	    mv.addObject("vo", detailVO);
	    mv.addObject("genderList", GenderTypeEnum.values());
	    mv.addObject("nameTypeList", NameTypeEnum.values());
	    mv.addObject("interestedPartyTypeList", TypeEnum.values());
	    mv.addObject("showInNewTab", showInNewTab);
	    mv.addObject("readOnlyMode", readOnlyMode);

	    Map<String, CreationClassFlat> ccMap = commonBusiness.getCreationClassFlatList().stream().collect(Collectors.toMap(CreationClassFlat::getCode, Function.identity()));
	    mv.addObject("ccMap", ccMap);

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    @RequestMapping("deleteIp.json")
    @ResponseBody
    public RequestResultVO deleteIp(@RequestParam Long idIp) {
	RequestResultVO output;

	try {
	    interestedPartyBusiness.logicallyDeleteIp(idIp);
	    output = new RequestResultVO();
	} catch (IpDeleteErrorException e) {
	    output = new RequestResultVO(e);
	    output.setMessage(messageSource.getMessage(e.getMessages().get(0), null, getCurrentLocale()));
	    output.setUseGenericError(false);
	    output.setType(RequestResultTypeEnum.WARN.name());
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	} catch (WccException e) {
	    output = new RequestResultVO(e);
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	}
	return output;
    }

    @RequestMapping("findIpAffiliations.json")
    public ModelAndView findIpAffiliations(@RequestParam Long ipId) throws WccControllerException {

	try {
	    ModelAndView mv = new ModelAndView();

	    List<Affiliation> affiliationList = new ArrayList<>();
	    List<AffiliationIpVO> affiliationListVO = new ArrayList<>();

	    Map<Long, CreationClassFlat> creationClassMap;
	    Map<Long, IpiRightTypeFlat> ipiRightTypeMap;
	    Map<Long, IpiRoleFlat> ipiRoleMap;

	    creationClassMap = DTOUtils.listToMapById(this.commonBusiness.getCreationClassFlatList());
	    ipiRightTypeMap = DTOUtils.listToMapById(this.commonBusiness.getIpiRightTypeList());
	    ipiRoleMap = DTOUtils.listToMapById(this.commonBusiness.getIpiRoleList());

	    affiliationList = interestedPartyBusiness.findInterestedPartyAffiliation(ipId);

	    for (Affiliation affiliation : affiliationList) {
		for (AffiliationDomain affiliationDomain : affiliation.getAffiliationDomainList()) {
		    AffiliationIpVO affiliationVO = new AffiliationIpVO();

		    affiliationVO.setFkInterestedParty(ipId);
		    affiliationVO.setIdAffiliation(affiliation.getId());
		    affiliationVO.setIdAffiliationDomain(affiliationDomain.getId());
		    affiliationVO.setFkCmo(affiliation.getCmo().getIdCmo());
		    affiliationVO.setCmoAcronym(affiliation.getCmo().getAcronym());
		    affiliationVO.setStartDate(affiliation.getStartDate());
		    affiliationVO.setEndDate(affiliation.getEndDate());
		    affiliationVO.setShareValue(affiliation.getShareValue());
		    affiliationVO.setTerritoryFormula(affiliation.getTerritoryFormula());

		    affiliationVO.setFkCreationClass(affiliationDomain.getFkCreationClass());
		    affiliationVO.setFkIpiRole(affiliationDomain.getFkIpiRole());
		    affiliationVO.setFkIpiRightType(affiliationDomain.getFkIpiRightType());

		    affiliationListVO.add(affiliationVO);
		}
	    }

	    mv.addObject("creationClassMap", creationClassMap);
	    mv.addObject("ipiRightTypeMap", ipiRightTypeMap);
	    mv.addObject("roleMap", ipiRoleMap);
	    mv.addObject("data", affiliationListVO);

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    /**
     * Serves the affiliationDetail page.
     *
     * @param ipVO
     *                    the ip vo
     * @param results
     *                    the results
     * @return the model and view
     * @throws WccControllerException
     *                                    the wcc controller exception
     */
    @RequestMapping(value = "affiliationDetail")
    public ModelAndView affiliationDetail(InterestedPartySearchVO ipVO, BindingResult results) throws WccControllerException {
	ModelAndView mv = null;
	Map<Long, CreationClassFlat> creationClassMap;
	Map<Long, IpiRightTypeFlat> ipiRightTypeMap;
	Map<Long, IpiRoleFlat> ipiRoleMap;
	Map<Long, Territory> territoryMap;
	// Cmo currentCmo;

	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Error in search - " + results);
	    }

	    mv = new ModelAndView();
	    // gets affiliation list from ip
	    InterestedParty ip = this.interestedPartyBusiness.findByIdIp(ipVO.getId());
	    mv.addObject("affiliationList", ip.getAffiliationList());

	    // gets creationClass list and transforms it in map
	    creationClassMap = DTOUtils.listToMapById(this.commonBusiness.getCreationClassFlatList());
	    mv.addObject("creationClassMap", creationClassMap);

	    // gets ipiRightType list and transforms it in map
	    ipiRightTypeMap = DTOUtils.listToMapById(this.commonBusiness.getIpiRightTypeList());
	    mv.addObject("ipiRightTypeMap", ipiRightTypeMap);

	    // gets role list and transforms it in map
	    ipiRoleMap = DTOUtils.listToMapById(this.commonBusiness.getIpiRoleList());
	    mv.addObject("roleMap", ipiRoleMap);

	    // gets territory list and transforms it in map
	    territoryMap = DTOUtils.listToMapById(commonBusiness.getTerritoryList(TerritoryOrderTypeEnum.TRASL_name));
	    mv.addObject("territoryMap", territoryMap);

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
	return mv;
    }

    @ResponseBody
    @RequestMapping("massiveAction/delete.json")
    public RequestResultVO massiveActionLogicallyDelete(MassiveActionVO vo, BindingResult results) throws WccControllerException {
	RequestResultVO output;
	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    Integer check = interestedPartyBusiness.executeMassiveAction(vo.getIdList(), MassiveActionsEnum.IP_DELETE);

	    output = new RequestResultVO();
	    if (check.intValue() != vo.getIdList().size()) {
		output.setType(RequestResultTypeEnum.WARN.name());
		output.setUseGenericError(false);
		output.setMessage(messageSource.getMessage("interestedParty.multi-delete-error", new Object[] { check, vo.getIdList().size() }, getCurrentLocale()));
	    }

	} catch (Exception e) {
	    output = new RequestResultVO(e);
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	}
	return output;
    }

    private void nameListSort(List<Name> nameList) {
	Comparator<Name> comparator = new Comparator<Name>() {
	    @Override
	    public int compare(Name n1, Name n2) {
		if (StringUtils.equals(n1.getNameType(), NameTypeEnum.PA.name())) {
		    return -1;
		} else {
		    if (StringUtils.equals(n2.getNameType(), NameTypeEnum.PA.name())) {
			return 1;
		    } else {
			return StringUtils.compareIgnoreCase(n1.getName(), n2.getName());
		    }
		}
	    }
	};

	Collections.sort(nameList, comparator);
    }

    @RequestMapping(value = "/exportIp")
    public ModelAndView exportIp(MassiveActionVO vo, BindingResult results) throws WccControllerException {
	ModelAndView mv;

	if (results.hasErrors()) {
	    throw new IllegalArgumentException("Error in exportIp - " + results);
	}

	try {

	    ExportBean exportBean = interestedPartyBusiness.getExportBean(vo.getIdList(), ExportTypeEnum.IP_LIST_VIEW_EXPORT, FileFormatEnum.CSV);
	    mv = new ModelAndView("download-view");
	    mv.addObject("download", exportBean);
	    return mv;
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    @RequestMapping(value = "/fullExport.json")
    @ResponseBody
    public RequestResultVO fullWorkExport(@RequestParam(required = true) String fileFormat) throws WccControllerException {
	RequestResultVO output = new RequestResultVO();
	try {
	    FileFormatEnum fileFormatEnum = FileFormatEnum.valueOf(fileFormat);
	    interestedPartyBusiness.getFullExportBean(fileFormatEnum);
	} catch (Exception e) {
	    output = new RequestResultVO(e);
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	}
	return output;
    }

}