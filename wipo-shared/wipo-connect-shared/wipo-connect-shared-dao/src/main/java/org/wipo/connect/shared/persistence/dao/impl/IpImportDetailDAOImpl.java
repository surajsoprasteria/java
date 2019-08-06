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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.shared.exchange.dto.impl.IpImportDetail;
import org.wipo.connect.shared.exchange.enumerator.ImportDetailStatusEnum;
import org.wipo.connect.shared.persistence.dao.IpImportDetailDAO;
import org.wipo.connect.shared.persistence.mapping.IpImportDetailMapper;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class IpImportDetailDAOImpl implements IpImportDetailDAO {

    @Autowired
    private IpImportDetailMapper ipImportDetailMapper;

    @Override
    public Long insertIpImportDetail(IpImportDetail ipImportDetail) {
	Long idIpImportDetail = 0l;
	if (ipImportDetail.getFkInterestedParty() != null) {
	    if (!ipImportDetailMapper.existFkIp(ipImportDetail.getFkInterestedParty())) {
		int rowNum = ipImportDetailMapper.insertIpImportDetail(ipImportDetail);
		if (0 < rowNum) {
		    idIpImportDetail = ipImportDetail.getIdIpImportDetail();
		}
	    } else {
		ipImportDetailMapper.updateImportDetailByFkIp(ipImportDetail.getFkIpImport(), ipImportDetail.getFkInterestedParty());
	    }
	}
	return idIpImportDetail;
    }

    @Override
    public List<IpImportDetail> findIpImportDetailFromStatus(ImportDetailStatusEnum status) {
	return ipImportDetailMapper.findIpImportDetailFromStatus(status);
    }

    @Override
    public int delete(Long id) {
	return 0;
    }

    @Override
    public IpImportDetail find(Long id) {
	return null;
    }

    @Override
    public List<IpImportDetail> findAll() {
	return null;
    }

    @Override
    public IpImportDetail merge(IpImportDetail obj) {
	return null;
    }

}
