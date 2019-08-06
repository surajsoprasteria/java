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
package org.wipo.connect.shared.exchange.vo;

import java.io.Serializable;
import java.util.List;

import org.wipo.connect.common.querypagination.PageResult;
import org.wipo.connect.shared.exchange.dto.impl.NameVO;

/**
 * The Class WorkSearchResultVO.
 */
public class NameSearchResultVO extends PageResult<NameVO> implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 557286432997536509L;

    private Boolean hasRemoteErrors = false;

    private String remoteErrorMessage;

    public NameSearchResultVO() {
	super();
    }

    public NameSearchResultVO(List<NameVO> data, Integer draw, Integer recordsFiltered, Integer recordsTotal) {
	super(data, draw, recordsFiltered, recordsTotal);
    }

    public NameSearchResultVO(List<NameVO> data, Integer draw, Integer recordsTotal) {
	super(data, draw, recordsTotal);
    }

    public Boolean getHasRemoteErrors() {
	return hasRemoteErrors;
    }

    public void setHasRemoteErrors(Boolean hasRemoteErrors) {
	this.hasRemoteErrors = hasRemoteErrors;
    }

    public String getRemoteErrorMessage() {
	return remoteErrorMessage;
    }

    public void setRemoteErrorMessage(String remoteErrorMessage) {
	this.remoteErrorMessage = remoteErrorMessage;
    }

}
