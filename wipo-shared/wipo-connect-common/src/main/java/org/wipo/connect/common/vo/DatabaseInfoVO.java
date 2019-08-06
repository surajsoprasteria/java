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
package org.wipo.connect.common.vo;

import java.io.Serializable;
import java.sql.Connection;

import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;

public class DatabaseInfoVO implements Serializable {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(DatabaseInfoVO.class);

    private static final long serialVersionUID = 148801667179094973L;

    private String username;
    private String catalog;
    private String url;
    private String productName;
    private String productVersion;
    private String driverName;
    private String driverVersion;

    public DatabaseInfoVO() {
	super();
    }

    public DatabaseInfoVO(String username, String catalog, String url, String productName, String productVersion, String driverName, String driverVersion) {
	super();
	this.username = username;
	this.catalog = catalog;
	this.url = url;
	this.productName = productName;
	this.productVersion = productVersion;
	this.driverName = driverName;
	this.driverVersion = driverVersion;
    }

    public DatabaseInfoVO(Connection connection) {
	super();

	try {
	    username = connection.getMetaData().getUserName();
	    catalog = connection.getCatalog();
	    url = connection.getMetaData().getURL();
	    productName = connection.getMetaData().getDatabaseProductName();
	    productVersion = connection.getMetaData().getDatabaseProductVersion();
	    driverName = connection.getMetaData().getDriverName();
	    driverVersion = connection.getMetaData().getDriverVersion();
	} catch (Exception e) {
	    LOGGER.error("Error: ", e);
	}

    }

    public String getUsername() {
	return username;
    }

    public String getCatalog() {
	return catalog;
    }

    public String getUrl() {
	return url;
    }

    public String getProductName() {
	return productName;
    }

    public String getProductVersion() {
	return productVersion;
    }

    public String getDriverName() {
	return driverName;
    }

    public String getDriverVersion() {
	return driverVersion;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public void setCatalog(String catalog) {
	this.catalog = catalog;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    public void setProductName(String productName) {
	this.productName = productName;
    }

    public void setProductVersion(String productVersion) {
	this.productVersion = productVersion;
    }

    public void setDriverName(String driverName) {
	this.driverName = driverName;
    }

    public void setDriverVersion(String driverVersion) {
	this.driverVersion = driverVersion;
    }

}
