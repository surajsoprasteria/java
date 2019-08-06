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
package org.wipo.connect.shared.business.import_ip;

import java.util.List;

import org.wipo.connect.common.import_model.IpRow;
import org.wipo.connect.common.import_model.TransactionTypeEnum;
import org.wipo.connect.shared.exchange.dto.impl.InterestedParty;

/**
 * The Interface ActionManager.
 */
public interface ExecutorIpAction {

    void executeTransaction(TransactionTypeEnum transaction, InterestedParty ip, List<IpRow> ipRowList) throws WccInterestedPartyImportException;

}