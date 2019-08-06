/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Copyright Connection (WCC).
 *
 *
 * @author Fincons
 *
 */
package org.wipo.connect.shared.exchange.utils.importexport;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.import_model.IpRow;
import org.wipo.connect.common.import_model.IpRowTypeEnum;
import org.wipo.connect.common.import_model.WorkRow;
import org.wipo.connect.common.import_model.WorkRowTypeEnum;
import org.wipo.connect.common.mapping.DozerUtility;
import org.wipo.connect.common.utils.ConstantUtils;
import org.wipo.connect.common.utils.ConversionUtils;
import org.wipo.connect.shared.exchange.business.CommonBusiness;
import org.wipo.connect.shared.exchange.dto.impl.Affiliation;
import org.wipo.connect.shared.exchange.dto.impl.AffiliationDomain;
import org.wipo.connect.shared.exchange.dto.impl.DerivedView;
import org.wipo.connect.shared.exchange.dto.impl.DerivedViewName;
import org.wipo.connect.shared.exchange.dto.impl.DerivedViewNameShare;
import org.wipo.connect.shared.exchange.dto.impl.DerivedWork;
import org.wipo.connect.shared.exchange.dto.impl.InterestedParty;
import org.wipo.connect.shared.exchange.dto.impl.InterestedPartyIdentifierFlat;
import org.wipo.connect.shared.exchange.dto.impl.Name;
import org.wipo.connect.shared.exchange.dto.impl.TerritoryNameIdentifierFlat;
import org.wipo.connect.shared.exchange.dto.impl.Title;
import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.dto.impl.WorkDate;
import org.wipo.connect.shared.exchange.dto.impl.WorkIdentifierFlat;
import org.wipo.connect.shared.exchange.dto.impl.WorkPerformer;

/**
 * The Class ImportExportHelper.
 */
@Service
public class ImportExportHelper {

    /** The dozer utility. */
    @Autowired
    private DozerUtility dozerUtility;

    @Autowired
    private CommonBusiness commonBusinessImpl;

    private DateFormat dateFormat = new SimpleDateFormat(ConversionUtils.DATE_STAMP);

    /**
     * To ip row list.
     *
     * @param ipList
     *            the ip list
     * @return the list
     */
    public List<IpRow> toIpRowList(List<InterestedParty> ipList) {
	List<IpRow> ipRowsFinalList = new ArrayList<>();

	// transform and add each element to the output list
	for (InterestedParty ip : ipList) {
	    List<IpRow> ipRowList = toIpRows(ip);
	    ipRowsFinalList.addAll(ipRowList);
	}

	return ipRowsFinalList;
    }

    /**
     * To ip rows.
     *
     * @param ip
     *            the ip
     * @return the list
     */
    public List<IpRow> toIpRows(InterestedParty ip) {
	List<IpRow> ipRows = new ArrayList<>();

	// type row: MAIN ------------------------------------------------
	IpRow ir = dozerUtility.map(ip, new IpRow()); // all main columns
	ir.setRowType(IpRowTypeEnum.MA.name()); // row type column
	ir.setIdField(ip.getId().toString()); // id field column
	ipRows.add(ir);

	// type row: NAME ------------------------------------------------
	ipRows.addAll(toIpRowsName(ip.getNameList(), ip.getId().toString()));

	// type row: AFFILIATION -----------------------------------------
	ipRows.addAll(toIpRowsAffiliation(ip.getAffiliationList(), ip.getId().toString()));

	// type row: IDENTIFIER ------------------------------------------
	ipRows.addAll(toIpRowsIdentifier(ip.getInterestedPartyIdentifierFlatList(), ip.getId().toString()));

	// type row: CONTACT ---------------------------------------------
	// ipRows.addAll(toIpRowsContact(ip.getContactList(), ip.getMainId()));

	// type row: ADDRESS ---------------------------------------------
	// ipRows.addAll(toIpRowsAddress(ip.getAddressList(), ip.getMainId()));

	// type row: DYNAMIC FIELD ---------------------------------------
	// ipRows.addAll(toIpRowsDynamicField(ip.getDynamicValueMap(), ip.getMainId()));

	return ipRows;
    }

    private List<IpRow> toIpRowsName(List<Name> nameList, String idField) {
	List<IpRow> ipRows = new ArrayList<>();

	for (Name n : nameList) {
	    IpRow ir = dozerUtility.map(n, new IpRow()); // all name columns
	    ir.setRowType(IpRowTypeEnum.NA.name()); // row type column
	    ir.setIdField(idField); // id field column
	    ipRows.add(ir);
	}

	return ipRows;
    }

    private List<IpRow> toIpRowsAffiliation(List<Affiliation> affiliationList, String idField) {
	List<IpRow> ipRows = new ArrayList<>();

	for (Affiliation a : affiliationList) {

	    for (AffiliationDomain affd : a.getAffiliationDomainList()) {
		IpRow ir = dozerUtility.map(a, new IpRow()); // all affiliation columns
		ir = dozerUtility.map(affd, ir); // all affiliation domain columns
		ir.setRowType(IpRowTypeEnum.AF.name()); // row type column
		ir.setIdField(idField); // id field column
		ipRows.add(ir);
	    }

	}

	return ipRows;
    }

    private List<IpRow> toIpRowsIdentifier(List<InterestedPartyIdentifierFlat> interestedPartyIdentifierFlatList, String idField) {
	List<IpRow> ipRows = new ArrayList<>();

	for (InterestedPartyIdentifierFlat ipif : interestedPartyIdentifierFlatList) {
	    IpRow ir = dozerUtility.map(ipif, new IpRow()); // all identifier columns
	    ir.setRowType(IpRowTypeEnum.ID.name()); // row type column
	    ir.setIdField(idField); // id field column
	    ipRows.add(ir);
	}

	return ipRows;
    }

    // private List<IpRow> toIpRowsContact(List<Contact> contactList, String idField) {
    // List<IpRow> ipRows = new ArrayList<IpRow>();
    //
    // for (Contact c : contactList) {
    // IpRow ir = dozerUtility.map(c, new IpRow()); //all contact columns
    // ir.setRowType(RowTypeEnum.CONTACT.name()); //row type column
    // ir.setIdField(idField); //id field column
    // ipRows.add(ir);
    // }
    //
    // return ipRows;
    // }

    // private List<IpRow> toIpRowsAddress(List<Address> addressList, String idField) {
    // List<IpRow> ipRows = new ArrayList<IpRow>();
    //
    // for (Address a : addressList) {
    // IpRow ir = dozerUtility.map(a, new IpRow()); //all address columns
    // ir.setRowType(RowTypeEnum.ADDRESS.name()); //row type column
    // ir.setIdField(idField); //id field column
    // ipRows.add(ir);
    // }
    //
    // return ipRows;
    // }

    // private List<IpRow> toIpRowsDynamicField(Map<String, InterestedPartyDynamicValueFlat> dynamicValueMap, String idField) {
    // List<IpRow> ipRows = new ArrayList<IpRow>();
    //
    // for (Map.Entry<String, InterestedPartyDynamicValueFlat> df : dynamicValueMap.entrySet()){
    // IpRow ir = dozerUtility.map(df.getValue(), new IpRow()); //all dyn field columns
    // ir.setRowType(RowTypeEnum.DYN_FIELD.name()); //row type column
    // ir.setIdField(idField); //id field column
    // ipRows.add(ir);
    // }
    //
    // return ipRows;
    // }

    public List<WorkRow> toWorkRowList(List<Work> workList) throws WccException {
	List<WorkRow> workRowsFinalList = new ArrayList<>();

	// transform and add each element to the output list
	for (Work w : workList) {
	    List<WorkRow> workRowList = toWorkRows(w);
	    workRowsFinalList.addAll(workRowList);
	}

	return workRowsFinalList;
    }

    /**
     * To ip rows.
     *
     * @param w
     *            the ip
     * @return the list
     */
    public List<WorkRow> toWorkRows(Work w) throws WccException {
	List<WorkRow> workRows = new ArrayList<>();

	// type row: MAIN ------------------------------------------------
	workRows.add(toWorkRowListMain(w));

	// type row: TITLE -----------------------------------------------
	workRows.addAll(toWorkRowsTitle(w.getTitleList(), w.getId().toString()));

	// type row: IDENTIFIER ------------------------------------------
	workRows.addAll(toWorkRowsIdentifier(w.getWorkIdentifierList(), w.getId().toString()));

	// type row: SHARE -----------------------------------------------
	workRows.addAll(toWorkRowsShare(w.getDerivedViewList(), w.getId().toString()));

	// type row: DYNAMIC FIELD ---------------------------------------
	// workRows.addAll(toWorkRowsDynamicField(w.getDynamicValueMap(), w.getMainId()));

	workRows.addAll(toWorkRowsDate(w.getWorkDateList(), w.getId().toString()));

	workRows.addAll(toWorkRowsComponent(w.getDerivedWorkList(), w.getId().toString()));

	return workRows;
    }

    private WorkRow toWorkRowListMain(Work work) throws WccException {
	WorkRow wRow = dozerUtility.map(work, new WorkRow()); // all main columns
	wRow.setRowType(WorkRowTypeEnum.MA.name()); // row type column
	wRow.setId(work.getId().toString()); // id field column

	wRow.setInstrument(getStringListRendered(work.getInstrumentCodeList())); // instrument column
	wRow.setPerformer(getPerformerListRendered(work.getWorkPerformerList())); // performer column
	wRow.setCreationClass(work.getCreationClassCode());
	wRow.setTerritory(work.getDerivedViewList().get(0).getTerritoryFormula());

	addAdditionalFields(wRow, work);

	if (work.getComponentPerc() != null) {
	    BigDecimal componentPerc = ConstantUtils.ONE_HUNDRED.subtract(work.getComponentPerc());
	    wRow.setWeight(componentPerc.toString());
	}

	if (wRow.getWorkType() == null) {
	    wRow.setWorkType("Simple Work"); // default work type
	}

	return wRow;
    }

    private List<WorkRow> toWorkRowsTitle(List<Title> titleList, String idField) {
	List<WorkRow> workRows = new ArrayList<>();

	for (Title t : titleList) {
	    WorkRow wr = dozerUtility.map(t, new WorkRow()); // all dyn field columns
	    wr.setRowType(WorkRowTypeEnum.TI.name()); // row type column
	    wr.setId(idField); // id field column
	    workRows.add(wr);
	}

	return workRows;
    }

    private List<WorkRow> toWorkRowsIdentifier(List<WorkIdentifierFlat> identifierList, String idField) {
	List<WorkRow> workRows = new ArrayList<>();

	for (WorkIdentifierFlat wi : identifierList) {
	    WorkRow wr = dozerUtility.map(wi, new WorkRow()); // all dyn field columns
	    wr.setRowType(WorkRowTypeEnum.ID.name()); // row type column
	    wr.setId(idField); // id field column
	    workRows.add(wr);
	}

	return workRows;
    }

    private List<WorkRow> toWorkRowsShare(List<DerivedView> shareList, String idField) {
	List<WorkRow> workRows = new ArrayList<>();
	Map<Long, String> creatorRefMap = new HashMap<>();

	for (DerivedView dv : shareList) {

	    // boolean isfirstLine = true;
	    // make creatorRefMap <refIndex, nameMainId>
	    int i = 0;
	    for (DerivedViewName dvn : dv.getDerivedViewNameList()) {
		creatorRefMap.put(new Long(++i), dvn.getName().getMainId());
	    }

	    for (DerivedViewName dvn : dv.getDerivedViewNameList()) {
		String creatorRef = null;
		if (dvn.getCreatorRefIndex() != null) {
		    // wr.setCreatorRef(dv.getDerivedViewNameList().get(dvn.getCreatorRefIndex().intValue()-1).getName().getMainId());
		    creatorRef = creatorRefMap.get(dvn.getCreatorRefIndex());
		}

		for (DerivedViewNameShare dvns : dvn.getDerivedViewNameShareList()) {
		    if (dvns.getShareValue() != null) {
			WorkRow wr = new WorkRow();
			wr.setNameMainId(dvn.getName().getMainId());
			wr.setRole(dvn.getRoleCode());
			wr.setCreatorRef(creatorRef);
			wr.setRightCategory(dvns.getRightTypeCode());
			wr.setValue(dvns.getShareValue().toString());

			// if (isfirstLine) {
			// wr.setTerritory(dv.getTerritoryFormula());
			// isfirstLine = false;
			// }

			wr.setRowType(WorkRowTypeEnum.SH.name()); // row type column
			wr.setId(idField); // id field column
			workRows.add(wr);
		    }
		}

	    }
	}

	return workRows;
    }

    // private List<WorkRow> toWorkRowsDynamicField(Map<String, WorkDynamicValueFlat> dynamicValueMap, String idField) {
    // List<WorkRow> workRows = new ArrayList<WorkRow>();
    //
    // for (Map.Entry<String, WorkDynamicValueFlat> df : dynamicValueMap.entrySet()){
    // WorkRow wr = dozerUtility.map(df.getValue(), new WorkRow()); //all dyn field columns
    // wr.setRowType(RowTypeEnum.DYN_FIELD.name()); //row type column
    // wr.setId(idField); //id field column
    // workRows.add(wr);
    // }
    //
    // return workRows;
    // }

    private String getPerformerListRendered(List<WorkPerformer> performerList) {
	String stringOut = "";
	for (WorkPerformer wp : performerList) {
	    stringOut += wp.getPerformerName() + "|";
	}
	return StringUtils.removeEnd(stringOut, "|");
    }

    private String getStringListRendered(List<String> stringList) {
	String stringOut = "";
	for (String string : stringList) {
	    stringOut += string + "|";
	}
	return StringUtils.removeEnd(stringOut, "|");

    }

    private List<WorkRow> toWorkRowsDate(List<WorkDate> workDateList, String idField) {

	List<WorkRow> workRows = new ArrayList<>();

	for (WorkDate wd : workDateList) {
	    WorkRow wr = new WorkRow();
	    wr.setRowType(WorkRowTypeEnum.DA.name()); // row type column
	    wr.setId(idField); // id field column
	    wr.setType(wd.getDateTypeCode());
	    wr.setValue(dateFormat.format(wd.getDateValue()));
	    workRows.add(wr);
	}

	return workRows;
    }

    private List<WorkRow> toWorkRowsComponent(List<DerivedWork> derivedWorkList, String idField) throws WccException {
	List<WorkRow> workRows = new ArrayList<>();

	derivedWorkList.sort(new Comparator<DerivedWork>() {
	    @Override
	    public int compare(DerivedWork o1, DerivedWork o2) {
		Long t1 = ObjectUtils.defaultIfNull(o1.getTrackNumber(), 0L);
		Long t2 = ObjectUtils.defaultIfNull(o2.getTrackNumber(), 0L);
		return Long.compare(t1, t2);
	    }
	});

	for (DerivedWork dw : derivedWorkList) {

	    WorkRow wr = new WorkRow();
	    wr.setRowType(WorkRowTypeEnum.CO.name());
	    wr.setId(idField);
	    wr.setWorkMainId(dw.getMainIdWork());
	    if (dw.getWeight() != null) {
		wr.setWeight(dw.getWeight().toString());
	    } else {
		wr.setWeight("");
	    }
	    workRows.add(wr);
	}

	return workRows;
    }

    private void addAdditionalFields(WorkRow wRow, Work work) throws WccException {

	if (StringUtils.isNotEmpty(work.getCatalogueNumber())) {
	    wRow.setCatalogueNumber(work.getCatalogueNumber());
	}

	if (StringUtils.isNotEmpty(work.getSupport())) {
	    wRow.setSupport(work.getSupport());
	}

	if (StringUtils.isNotEmpty(work.getCountryOfProductionCode())) {
	    TerritoryNameIdentifierFlat tni = commonBusinessImpl.findIdentifierFromTerritoryCode(work.getCountryOfProductionCode());
	    if (tni != null && tni.getValue() != null) {
		wRow.setCountryOfProduction(tni.getValue());
	    }
	}

	if (StringUtils.isNotEmpty(work.getCategoryCode())) {
	    wRow.setCategory(work.getCategoryCode());
	}

	if (StringUtils.isNotEmpty(work.getLabel())) {
	    wRow.setLabel(work.getLabel());
	}

	if (StringUtils.isNotEmpty(work.getLanguage())) {
	    wRow.setLanguage(work.getLanguage());
	}

    }

}
