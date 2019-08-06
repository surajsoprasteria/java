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
package org.wipo.connect.common.report.bean;

import java.util.ArrayList;
import java.util.List;

public enum ReportHeaderEnum {

    REFERENCE_NUMBER("Reference Number"),
    ROW_TYPE("Row Type"),
    CREATION_CLASS("Creation Class"),
    TITLE("Title"),
    WORK_ID("Work Id"),
    WORK_INT_ID("Work International Id"),
    LABEL("Label"),
    NAME("Name"),
    FIRST_NAME("First Name"),
    RO_TYPE("RO Type"),
    NAME_ID("Name Id"),
    RO_MAIN_ID("RO Main Id"),
    ROLE("Role"),
    WORK_ALLOCATED_AMOUNT("Work Allocated Amount"),
    RIGHT_CATEGORY("Right Category"),
    RIGHT_TYPE("Right Type"),
    AFFILIATION_CMO("Affiliation CMO"),
    WORK_SHARE("Work Share"),
    AFFILIATION_SHARE("Affiliation Share"),
    GROWTH_AMOUNTS("Growth Amounts"),
    ADMIN_FEE("Sum of Admin Fee"),
    NET_DISTR_AMOUNT("Net Distr Amount"),
    START_DATE("Start Date"),
    END_DATE("End Date"),
    TERRITORY("Territory"),
    COUNT("Count"),
    SOURCE_DETAIL("Source Detail"),
    SOURCE_TYPE("Source Type"),
    TF_TITLE("TF_Title"),
    TF_DURATION("TF_Duration"),
    TF_GENDRE("TF_Genre"),
    TF_CREATOR("TF_Creator"),
    TF_MAIN_ID("TF_MainID"),
    TF_PERFORMER("TF_Performer"),
    TF_CATALOGUE_NUMBER("TF_CatalogueNumber"),
    TF_SUPPORT("TF_Support"),
    TF_COUNTRY_PROD("TF_CountryProd"),
    TF_CATEGORY("TF_Category"),
    TF_LABEL("TF_Label"),
    TF_IDEMTIFIER("TF_Identifier"),
    TF_DYN_FIELD("TF_DynField"),
    REASON("Reason"),
    RESERVED("Reserved");

    private final String header;

    private ReportHeaderEnum(String header) {
	this.header = header;
    }

    public String getHeader() {
	return header;
    }

    public static List<String> getHeaderList(String currency) {

	List<String> headerList = new ArrayList<>();

	for (ReportHeaderEnum value : ReportHeaderEnum.values()) {
	    if (value == WORK_ALLOCATED_AMOUNT || value == GROWTH_AMOUNTS || value == ADMIN_FEE || value == NET_DISTR_AMOUNT) {
		headerList.add(value.getHeader() + " " + currency);
	    } else {
		headerList.add(value.getHeader());
	    }
	}

	return headerList;
    }

}
