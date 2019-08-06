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

package org.wipo.connect.shared.persistence.dao.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.crypto.CryptoHelper;
import org.wipo.connect.shared.exchange.dto.impl.ClientInfo;
import org.wipo.connect.shared.persistence.BaseDAO;
import org.wipo.connect.shared.persistence.dao.ClientInfoDAO;
import org.wipo.connect.shared.persistence.mapping.ClientInfoMapper;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class ClientInfoDAOImpl extends BaseDAO<ClientInfo> implements ClientInfoDAO {

    @Autowired
    private ClientInfoMapper clientInfoMapper;

    @Autowired
    private CryptoHelper cryptoHelper;

    @Override
    public ClientInfo find(Long id) {
	ClientInfo dto = clientInfoMapper.selectByPrimaryKey(id);
	dto = cryptoHelper.decrypt(dto);
	return dto;
    }

    @Override
    public List<ClientInfo> findAll() {
	List<ClientInfo> dtoList = clientInfoMapper.findAll();
	dtoList = cryptoHelper.decrypt(dtoList);
	return dtoList;
    }

    @Override
    public ClientInfo merge(ClientInfo dto) {
	dto = cryptoHelper.encrypt(dto);
	dto = super.merge(dto);
	dto = cryptoHelper.decrypt(dto);
	return dto;
    }

    @Override
    public ClientInfo findByCode(String code) {
	ClientInfo dto = clientInfoMapper.findByCode(code);
	dto = cryptoHelper.decrypt(dto);
	return dto;
    }

    @Override
    public int updateClientKey(Long idClientInfo, String clientKey) {
	String encKey = cryptoHelper.encrypt(clientKey);
	return clientInfoMapper.updateClientKey(idClientInfo, encKey);
    }

    @Override
    public int deleteClientPermissionByClient(Long idClientInfo) {
	return clientInfoMapper.deleteClientPermissionByClient(idClientInfo);
    }

    @Override
    public int insertClientPermission(Long idClientInfo, Collection<Long> sourceList) {
	int cont = 0;
	for (Long idCmo : sourceList) {
	    clientInfoMapper.insertClientPermission(idClientInfo, idCmo);
	}
	return cont;
    }

    @Override
    public int delete(Long id) {
	clientInfoMapper.deleteClientPermissionByClient(id);
	return super.delete(id);
    }

    @Override
    public Boolean checkCodeUniqueness(String code, Long idClientInfo) {
	return clientInfoMapper.checkCodeUniqueness(code, idClientInfo);
    }

}