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

public enum IdentifierManagerRefTableEnum {

    WORK("work", "W"),
    INTERESTED_PARTY("interested_party", "R"),
    NAME("name", "N"),
    AGREEMENT("agreement", "A"),
    LICENSEE_ACCOUNT("licensee_account", "U"),
    LICENSE("license", "L"),
    TARIFF_TYPE("tariff_type", "T");

    private final String refTable;
    private final String suffix;

    IdentifierManagerRefTableEnum(String refTable, String suffix) {
	this.refTable = refTable;
	this.suffix = suffix;
    }

    public String getRefTable() {
	return refTable;
    }

    public String getSuffix() {
	return suffix;
    }

}