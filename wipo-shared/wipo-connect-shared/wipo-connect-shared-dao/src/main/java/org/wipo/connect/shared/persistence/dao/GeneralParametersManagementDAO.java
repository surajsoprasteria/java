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

import org.wipo.connect.shared.exchange.dto.impl.NumberFormatParam;
import org.wipo.connect.shared.exchange.dto.impl.PerformerConfiguration;

public interface GeneralParametersManagementDAO {

    NumberFormatParam findNumberFormat();

    int updateNumberFormat(NumberFormatParam dto);

    List<PerformerConfiguration> findPerformerConfiguration();

    void savePerformerConfiguration(PerformerConfiguration performerConfiguration);

    Boolean findPerformerConfigurationByFkCreationClass(Long fkCreationClass);
}
