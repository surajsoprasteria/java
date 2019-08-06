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
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.shared.exchange.business.DataAccessBusiness;
import org.wipo.connect.shared.exchange.dto.impl.ClientInfo;
import org.wipo.connect.shared.exchange.dto.impl.Cmo;
import org.wipo.connect.shared.persistence.dao.ClientInfoDAO;

import net.bull.javamelody.MonitoredWithSpring;

/**
 * The Class ExternalSiteBusinessImpl.
 *
 */
@Service
@MonitoredWithSpring
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class DataAccessBusinessImpl implements DataAccessBusiness {

    /** The logger. */
    @SuppressWarnings("unused")
    private static Logger LOGGER = WipoLoggerFactory.getLogger(DataAccessBusinessImpl.class);

    @Autowired
    private ClientInfoDAO clientInfoDAO;

    @Override
    public List<ClientInfo> findAll() throws WccException {
	try {
	    return clientInfoDAO.findAll();
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    @Override
    public ClientInfo findById(Long idClientInfo) throws WccException {
	try {
	    return clientInfoDAO.find(idClientInfo);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    @Override
    public ClientInfo insertOrUpdate(ClientInfo clientInfo) throws WccException {

	try {
	    clientInfo = clientInfoDAO.merge(clientInfo);

	    if (StringUtils.isNotBlank(clientInfo.getClientKey())) {
		clientInfoDAO.updateClientKey(clientInfo.getId(), clientInfo.getClientKey());
	    }

	    List<Cmo> sourceList = clientInfo.getDataOriginList().stream()
		    .filter(cmo -> BooleanUtils.isNotTrue(cmo.getExecDelete()))
		    .collect(Collectors.toList());

	    clientInfo.setDataOriginList(sourceList);

	    Set<Long> sourceIdList = sourceList.stream()
		    .map(cmo -> cmo.getId())
		    .collect(Collectors.toSet());

	    clientInfoDAO.deleteClientPermissionByClient(clientInfo.getId());

	    clientInfoDAO.insertClientPermission(clientInfo.getId(), sourceIdList);

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

	return clientInfo;
    }

    @Override
    public void delete(Long idClientInfo) throws WccException {
	try {
	    clientInfoDAO.delete(idClientInfo);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    @Override
    public Boolean checkCodeUniqueness(String code, Long idClientInfo) {
	return clientInfoDAO.checkCodeUniqueness(code, idClientInfo);
    }

}