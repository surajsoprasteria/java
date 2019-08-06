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
package org.wipo.connect.common.authentication;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "requestOrThread", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ContextUserInfo implements ISecurityUserDetail, Serializable {

    private static final long serialVersionUID = 5029334745144580767L;
    public static final String SYSTEM_USERNAME = "System";
    public static final Long SYSTEM_USERID = -1L;

    private boolean isInitialized = false;
    private Long id;
    private String username;
    private transient String password;

    @PostConstruct
    private void postConstruct() {
	id = SYSTEM_USERID;
	username = SYSTEM_USERNAME;
    }

    public void initialize(Long id, String username, boolean reInitialize) {
	isInitialized = !reInitialize;
	initialize(id, username, null);
    }

    public void initialize(Long id, String username) {
	initialize(id, username, null);
    }

    public void initialize(Long id, String username, String password) {
	if (isInitialized) {
	    throw new IllegalStateException("This object has already been initialized. Old:[" + this.id + "-" + this.username + "] New:[" + id + "-" + username + "]");
	}
	isInitialized = true;
	this.id = id;
	this.username = username;
	this.password = password;
    }

    @Override
    public Long getId() {
	return id;
    }

    @Override
    public String getUsername() {
	return username;
    }

    public boolean isInitialized() {
	return isInitialized;
    }

    @Override
    public String getPassword() {
	return password;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE, false);
    }

}
