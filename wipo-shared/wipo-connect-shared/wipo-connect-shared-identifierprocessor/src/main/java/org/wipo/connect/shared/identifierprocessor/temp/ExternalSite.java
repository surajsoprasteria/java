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

package org.wipo.connect.shared.identifierprocessor.temp;

import org.wipo.connect.common.crypto.CryptedField;
import org.wipo.connect.common.dto.Creatable;
import org.wipo.connect.common.dto.Identifiable;
import org.wipo.connect.common.dto.Updatable;


/**
 * The Class ExternalSite.
 * 
 * @author pasquale.minervini
 *
 */
public class ExternalSite extends BaseDTO implements Identifiable, Creatable, Updatable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7785313727406869876L;

    /** The id account. */
    private Long idExternalSite;

    /** The email. */
    private String host;

    /** The password. */
    private Integer port;

    /** The type. */
    private String user;

    @CryptedField
    private String password;

    private ExternalSiteEnum code;

    public Long getIdExternalSite() {
	return idExternalSite;
    }

    public void setIdExternalSite(Long idExternalSite) {
	this.idExternalSite = idExternalSite;
    }

    public String getHost() {
	return host;
    }

    public void setHost(String host) {
	this.host = host;
    }

    public Integer getPort() {
	return port;
    }

    public void setPort(Integer port) {
	this.port = port;
    }

    public String getUser() {
	return user;
    }

    public void setUser(String user) {
	this.user = user;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public ExternalSiteEnum getCode() {
	return code;
    }

    public void setCode(ExternalSiteEnum code) {
	this.code = code;
    }

    public Long getId() {
	return getIdExternalSite();
    }

    public void setId(Long id) {
	setIdExternalSite(id);
    }

}
