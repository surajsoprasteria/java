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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.shared.exchange.dto.impl.AccountIdentifierFlat;
import org.wipo.connect.shared.exchange.enumerator.IdentifierTypeEnum;
import org.wipo.connect.shared.exchange.utils.IdentifierGenerator;
import org.wipo.connect.shared.exchange.utils.IdentifierGenerator.WccIdType;
import org.wipo.connect.shared.persistence.dao.AccountIdentifierFlatDAO;
import org.wipo.connect.shared.persistence.mapping.AccountIdentifierFlatMapper;

/**
 * The Class AccountIdentifierFlatDAOImpl.
 */
@Service
@Qualifier("accountIdentifierFlatDAOImpl")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class AccountIdentifierFlatDAOImpl implements AccountIdentifierFlatDAO {

	@Autowired
	private AccountIdentifierFlatMapper accountIdentifierFlatMapper;

	@Autowired
	private IdentifierGenerator identifierGenerator;


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public AccountIdentifierFlat find(Long id) {
		return this.accountIdentifierFlatMapper.selectByPrimaryKey(id);
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public List<AccountIdentifierFlat> findByAccount(Long idAccount) {
		return this.accountIdentifierFlatMapper.findByAccount(idAccount, null);
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public List<AccountIdentifierFlat> findByAccount(Long idAccount, IdentifierTypeEnum code) {
		return this.accountIdentifierFlatMapper.findByAccount(idAccount, code);
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public List<AccountIdentifierFlat> findISWC(Long idAccount) {
		return this.accountIdentifierFlatMapper.findByAccount(idAccount, IdentifierTypeEnum.ISWC);
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public AccountIdentifierFlat findWccId(Long idAccount) {
		List<AccountIdentifierFlat> listOut = this.accountIdentifierFlatMapper.findByAccount(idAccount, IdentifierTypeEnum.WCC_ACCOUNT_ID);

		if (listOut.size() != 1) {
			throw new IllegalStateException("Expected 1 result, found " + listOut.size());
		}

		return listOut.get(0);
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public int insertAccountId(Long idAccount, IdentifierTypeEnum type) {
		String wccAccountId = this.identifierGenerator.generateWccIdentifier(WccIdType.ACCOUNT, idAccount);
		return this.accountIdentifierFlatMapper.insert(idAccount, type, wccAccountId);
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public int insertAccountId(Long idAccount, IdentifierTypeEnum type, String wccAccountId) {
		return this.accountIdentifierFlatMapper.insert(idAccount, type, wccAccountId);
	}


	@Override
	public int deleteAccountIdentidierByIdAccount(Long idAccount) {
		return this.accountIdentifierFlatMapper.deleteAccountIdentidierByIdAccount(idAccount);
	}

}