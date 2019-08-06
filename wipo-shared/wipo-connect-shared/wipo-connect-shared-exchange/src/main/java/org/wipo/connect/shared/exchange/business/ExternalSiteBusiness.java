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

package org.wipo.connect.shared.exchange.business;

import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.sftp.SftpConnectionResultEnum;
import org.wipo.connect.shared.exchange.dto.impl.ExternalSite;
import org.wipo.connect.shared.exchange.enumerator.ExternalSiteEnum;
import org.wipo.connect.shared.exchange.vo.AdminExternalSiteVO;

public interface ExternalSiteBusiness {

    SftpConnectionResultEnum testSftpConnection(ExternalSite vo) throws WccException;

    ExternalSite selectExternalSiteByCode(ExternalSiteEnum code) throws WccException;

    AdminExternalSiteVO getAllExternalSite() throws WccException;

    void updateExternalSiteFTP(ExternalSite vo) throws WccException;

}
