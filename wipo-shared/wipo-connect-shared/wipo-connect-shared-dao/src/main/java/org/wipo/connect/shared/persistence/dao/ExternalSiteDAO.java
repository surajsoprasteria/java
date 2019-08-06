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

import org.wipo.connect.shared.exchange.dto.impl.ExternalSite;
import org.wipo.connect.shared.exchange.enumerator.ExternalSiteEnum;

/**
 * The ExternalSiteDAO interface provides methods that manipulate the data from the database.
 * 
 * @author pasquale.minervini
 *
 */
public interface ExternalSiteDAO {

    ExternalSite selectExternalSiteByCode(ExternalSiteEnum code);

    int updateExternalSitePassword(ExternalSite externalSite);

    int updateExternalSiteByCode(ExternalSite externalSite);

}
