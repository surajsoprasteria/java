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

package org.wipo.connect.shared.persistence.dao;

import java.util.List;

import org.wipo.connect.shared.exchange.dto.impl.IpImportDetail;
import org.wipo.connect.shared.exchange.enumerator.ImportDetailStatusEnum;
import org.wipo.connect.shared.persistence.Dao;

public interface IpImportDetailDAO extends Dao<IpImportDetail> {

    Long insertIpImportDetail(IpImportDetail ipImportDetail);

    List<IpImportDetail> findIpImportDetailFromStatus(ImportDetailStatusEnum status);

}
