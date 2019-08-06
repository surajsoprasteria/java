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

package org.wipo.connect.shared.business.impl;

import java.util.List;

import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.shared.exchange.business.ParametersManagementBusiness;
import org.wipo.connect.shared.exchange.dto.impl.NumberFormatParam;
import org.wipo.connect.shared.exchange.dto.impl.PerformerConfiguration;
import org.wipo.connect.shared.persistence.dao.GeneralParametersManagementDAO;

import net.bull.javamelody.MonitoredWithSpring;

/**
 * The Class ParametersManagementBusinessImpl.
 */
@Service
@MonitoredWithSpring
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class ParametersManagementBusinessImpl implements ParametersManagementBusiness {

    /** The logger. */
    @SuppressWarnings("unused")
    private static Logger LOGGER = WipoLoggerFactory.getLogger(ParametersManagementBusinessImpl.class);

    /** The general parameters management dao. */
    @Autowired
    // @Qualifier("generalParametersManagementDAOImpl")
    private GeneralParametersManagementDAO generalParametersManagementDAO;

    @Override
    public NumberFormatParam findNumberFormat() throws WccException {
	return generalParametersManagementDAO.findNumberFormat();
    }

    @Override
    public NumberFormatParam updateNumberFormat(NumberFormatParam dto) throws WccException {
	generalParametersManagementDAO.updateNumberFormat(dto);
	return dto;
    }

    @Override
    public List<PerformerConfiguration> findPerformerConfiguration() throws WccException {
	return generalParametersManagementDAO.findPerformerConfiguration();
    }

    @Override
    public void savePerformerConfiguration(PerformerConfiguration performerConfiguration) throws WccException {
	generalParametersManagementDAO.savePerformerConfiguration(performerConfiguration);
    }

    @Override
    public Boolean getPerformerConfigurationByFkCreationClass(Long fkCreationClass) throws WccException {
	return generalParametersManagementDAO.findPerformerConfigurationByFkCreationClass(fkCreationClass);
    }

}
