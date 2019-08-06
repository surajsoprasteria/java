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
package org.wipo.connect.shared.business.import_ip;

import java.io.IOException;

import org.wipo.connect.common.exception.WccException;

public interface ImportIpService {

    /**
     * Import interested party from CSV.
     *
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws WccException
     *             the wcc exception
     */
    void importInterestedParty() throws IOException, WccException;

    void markAllPendingAsError();

}