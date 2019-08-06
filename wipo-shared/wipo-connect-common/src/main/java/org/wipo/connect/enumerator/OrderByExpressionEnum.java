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

package org.wipo.connect.enumerator;

import org.apache.commons.lang3.StringUtils;

public enum OrderByExpressionEnum {

    /* WORK */
    WORK_MAIN_ID("work.main_id", "mainId"),
    WORK_CREATION_CLASS("creation_class.code", "creationClassCode"),
    WORK_STATUS("work_status.code", "statusCode"),
    WORK_SCORE("score_order", "score"),
    WORK_MAIN_TITLE("title.description", "mainTitle"),

    /* RIGHT OWNER */
    RO_MAIN_ID("interested_party.main_id", "mainId"),
    RO_TYPE("interested_party.type", "type"),
    RO_STATUS("interested_party_status.code", "statusCode"),
    RO_SCORE("score_order", "score"),
    RO_MAIN_NAME("name.name" + Constants.SEP + "name.first_name", "mainName"),

    /* WORK ALLOCATION */
    WA_ESTIMATED_AMOUNT("WA_estimated_amount", ""),
    WA_MAIN_TITLE("title.description", ""),
    WA_RIGHT_CATEGORY("was_rt.code", ""),

    /* NAME */
    NAME_MAIN_NAME("NAME_name" + Constants.SEP + "NAME_first_name", "");

    private final String field;
    private final String solr;

    OrderByExpressionEnum(String field, String solr) {
	this.field = field;
	this.solr = solr;
    }

    public static class Constants {
	public static final String SEP = "||";
	public static final String ASC = " asc";
	public static final String DESC = " desc";
    }

    public String getField() {
	return field;
    }

    public String[] getFieldsSplitted() {
	return StringUtils.split(field, Constants.SEP);
    }

    public String getFieldAsc() {
	return parseField(field, Constants.ASC);
    }

    public String getFieldDesc() {
	return parseField(field, Constants.DESC);
    }

    public String getSolr() {
	return solr;
    }

    public static String parseField(String field, String order) {
	String out = StringUtils.replace(field, Constants.SEP, order + ", ");
	return out + order;
    }

}