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

package org.wipo.connect.shared.exchange.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.wipo.connect.shared.exchange.enumerator.RequestResultTypeEnum;

@SuppressWarnings({ "squid:S1948" })
public class RequestResultVO implements Serializable {

    private static final long serialVersionUID = 2523764088151674062L;

    private String type;

    private Boolean useGenericError = true;

    private String message;

    private List<String> messageList;

    private Map<String, Object> data;

    public RequestResultVO() {
	this(RequestResultTypeEnum.INFO);
    }

    public RequestResultVO(RequestResultTypeEnum type) {
	this.type = type.name();
	this.useGenericError = true;
    }

    public RequestResultVO(Exception e) {
	this(RequestResultTypeEnum.ERROR);
	this.message = e.getMessage();
	this.useGenericError = true;
    }

    public RequestResultVO(String errorMessage) {
	this(RequestResultTypeEnum.ERROR);
	this.message = errorMessage;
	this.useGenericError = false;
    }

    public RequestResultVO(RequestResultTypeEnum type, String errorMessage) {
	this(type);
	this.message = errorMessage;
	this.useGenericError = false;
    }

    public Map<String, Object> getData() {
	if (this.data == null) {
	    this.data = new HashMap<>();
	}
	return this.data;
    }

    public String getMessage() {
	return this.message;
    }

    public Boolean getUseGenericError() {
	return this.useGenericError;
    }

    public void setData(Map<String, Object> data) {
	this.data = data;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public void setUseGenericError(Boolean useGenericError) {
	this.useGenericError = useGenericError;
    }

    public String getType() {
	return type;
    }

    public void setType(RequestResultTypeEnum type) {
	this.type = type.name();
    }

    public void setType(String type) {
	this.type = type;
    }

    public List<String> getMessageList() {
	return messageList;
    }

    public void setMessageList(List<String> messageList) {
	if (messageList == null) {
	    messageList = new ArrayList<>();
	}
	this.messageList = messageList;
    }

}
