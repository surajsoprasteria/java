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

package org.wipo.connect.shared.exchange.restvo.account;

import java.io.Serializable;

public class FindUiLanguageByAccountRestVO implements Serializable {

    private static final long serialVersionUID = 8054582310946911723L;

    private Long idAccount;

    public Long getIdAccount() {
	return idAccount;
    }

    public void setIdAccount(Long idAccount) {
	this.idAccount = idAccount;
    }

}
