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
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.shared.exchange.dto.impl.Account;
import org.wipo.connect.shared.persistence.BaseDAO;
import org.wipo.connect.shared.persistence.dao.AccountDAO;
import org.wipo.connect.shared.persistence.mapping.AccountMapper;




/**
 * The Class AccountDAOImpl.
 *
 * @author Minervini
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class AccountDAOImpl extends BaseDAO<Account> implements AccountDAO {

    /** The account mapper. */
    @Autowired
    private AccountMapper accountMapper;




    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<Account> findAccount(Map<String, Object> map) {
        List<Account> resList = null;
        resList = this.accountMapper.findAccount(map);
        return resList;
    }




    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public Account getAccountByMail(String email) {
        Account account = this.accountMapper.getAccountByMail(email);
        return account;
    }




    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public void updatePassword(Account account) {
        this.accountMapper.updatePassword(account);
    }



    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
	public List<Account> findAccountsByIdGroup(Long idSecGroup) {
		return this.accountMapper.selectAccountsByIdGroup(idSecGroup);
	}



    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
	public int insertAccountSecGroup(Long fkSecGroup, Long fkAccount) {
		return this.accountMapper.insertAccountSecGroup(fkSecGroup, fkAccount);
	}



    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
	public int deleteAccountSecGroup(Long fkSecGroup, Long fkAccount) {
		return this.accountMapper.deleteAccountSecGroup(fkSecGroup, fkAccount);
	}



	@Override
	public int deleteAccountSecGroupByIdSecGroup(Long fkSecGroup) {
		return this.accountMapper.deleteAccountSecGroupByIdSecGroup(fkSecGroup);
	}



	@Override
	public int deleteAccount(Long idAccount) {
		return this.accountMapper.deleteAccount(idAccount);
	}



	@Override
	public int deleteAccountSecGroupByIdAccount(Long fkAccount) {
		return this.accountMapper.deleteAccountSecGroupByIdAccount(fkAccount);
	}

}