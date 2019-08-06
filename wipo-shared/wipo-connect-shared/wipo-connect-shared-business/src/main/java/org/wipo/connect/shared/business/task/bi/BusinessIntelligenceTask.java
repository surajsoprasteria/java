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
package org.wipo.connect.shared.business.task.bi;

import java.io.IOException;

import org.wipo.connect.common.exception.WccException;

public interface BusinessIntelligenceTask {

    void importQueries() throws WccException, IOException;

}
