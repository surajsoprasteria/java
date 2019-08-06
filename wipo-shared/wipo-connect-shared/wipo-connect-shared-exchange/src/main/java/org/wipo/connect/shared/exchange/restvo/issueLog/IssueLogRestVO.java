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

package org.wipo.connect.shared.exchange.restvo.issueLog;

import java.io.Serializable;

import org.wipo.connect.common.logging.IssueLog;

public class IssueLogRestVO implements Serializable {

    private static final long serialVersionUID = -5262567186601495254L;

    private IssueLog dto;
    private Long idIssueLog;

    public IssueLog getDto() {
	return dto;
    }

    public void setDto(IssueLog dto) {
	this.dto = dto;
    }

    public Long getIdIssueLog() {
	return idIssueLog;
    }

    public void setIdIssueLog(Long idIssueLog) {
	this.idIssueLog = idIssueLog;
    }

}
