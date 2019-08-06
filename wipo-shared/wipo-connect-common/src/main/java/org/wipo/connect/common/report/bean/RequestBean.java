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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class RequestBean implements Serializable {
    private static final long serialVersionUID = 9070796563011232224L;
    private Long idRequestBean;
    private Long fkAsyncRequest;
    private String tipoOutput;
    private List<ParamEntry> params;

    public RequestBean() {
    }

    public Map<String, Object> getParamsMap() {
	Map<String, Object> map = new HashMap<String, Object>();
	for (ParamEntry pe : getParams()) {
	    map.put(pe.getKey(), pe.getValue());
	}
	return map;
    }

    public String getParam(String param) {
	for (ParamEntry p : params) {
	    if (StringUtils.equals(param, p.getKey())) {
		return String.valueOf(p.getValue());
	    }
	}

	return "";
    }

    public RequestBean(String tipoOutput, List<ParamEntry> params) {
	this.tipoOutput = tipoOutput;
	this.params = params;
    }

    public String getTipoOutput() {
	return tipoOutput;
    }

    public void setTipoOutput(String tipoOutput) {
	this.tipoOutput = tipoOutput;
    }

    public List<ParamEntry> getParams() {
	if (params == null) {
	    params = new ArrayList<ParamEntry>();
	}
	return params;
    }

    public void setParams(List<ParamEntry> params) {
	this.params = params;
    }

    @Override
    public String toString() {
	return ReflectionToStringBuilder.toString(this);
    }

    public Long getIdRequestBean() {
	return idRequestBean;
    }

    public void setIdRequestBean(Long idRequestBean) {
	this.idRequestBean = idRequestBean;
    }

    public Long getFkAsyncRequest() {
	return fkAsyncRequest;
    }

    public void setFkAsyncRequest(Long fkAsyncRequest) {
	this.fkAsyncRequest = fkAsyncRequest;
    }
}
