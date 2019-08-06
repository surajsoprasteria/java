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

package org.wipo.connect.shared.exchange.restvo.interestedParty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.wipo.connect.shared.exchange.dto.impl.InterestedParty;
import org.wipo.connect.shared.exchange.enumerator.ExportTypeEnum;

/**
 * The Class FindByIpListRestVO.
 */
public class FindByIpListRestVO implements Serializable {

    private static final long serialVersionUID = 8361603050099964672L;

    /** The ip list. */
    private List<InterestedParty> ipList;
    private ExportTypeEnum exportType;

    public List<InterestedParty> getIpList() {
	if (ipList == null) {
	    ipList = new ArrayList<>();
	}
	return ipList;
    }

    public void setIpList(List<InterestedParty> ipList) {
	this.ipList = ipList;
    }

    public ExportTypeEnum getExportType() {
	return exportType;
    }

    public void setExportType(ExportTypeEnum exportType) {
	this.exportType = exportType;
    }

}
