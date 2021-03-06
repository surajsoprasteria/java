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

package org.wipo.connect.shared.identifierprocessor.temp;



/**
 * 
 * @author pasquale.minervini
 *
 */
public interface ExternalSiteMapper {

    int updateExternalSitePassword(ExternalSite externalSite);

    int updateExternalSiteByCode(ExternalSite externalSite);

    ExternalSite selectExternalSiteByCode(String code);
}
