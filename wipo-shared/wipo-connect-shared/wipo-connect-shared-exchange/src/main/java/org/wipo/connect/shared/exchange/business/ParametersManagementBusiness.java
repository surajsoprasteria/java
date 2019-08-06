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

import java.util.List;

import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.shared.exchange.dto.impl.NumberFormatParam;
import org.wipo.connect.shared.exchange.dto.impl.PerformerConfiguration;

public interface ParametersManagementBusiness {

    NumberFormatParam updateNumberFormat(NumberFormatParam dto) throws WccException;

    NumberFormatParam findNumberFormat() throws WccException;

    List<PerformerConfiguration> findPerformerConfiguration() throws WccException;

    void savePerformerConfiguration(PerformerConfiguration performerConfiguration) throws WccException;

    Boolean getPerformerConfigurationByFkCreationClass(Long fkCreationClass) throws WccException;

}
