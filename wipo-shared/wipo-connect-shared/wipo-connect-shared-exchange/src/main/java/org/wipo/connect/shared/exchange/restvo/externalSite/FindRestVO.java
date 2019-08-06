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

package org.wipo.connect.shared.exchange.restvo.externalSite;

import java.io.Serializable;

import org.wipo.connect.shared.exchange.enumerator.ExternalSiteEnum;

public class FindRestVO implements Serializable {

    private static final long serialVersionUID = 8284906314618314105L;

    private ExternalSiteEnum code;

    public ExternalSiteEnum getCode() {
	return code;
    }

    public void setCode(ExternalSiteEnum code) {
	this.code = code;
    }

}
