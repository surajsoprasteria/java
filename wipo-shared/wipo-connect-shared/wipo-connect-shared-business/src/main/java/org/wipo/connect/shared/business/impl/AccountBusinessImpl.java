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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.shared.exchange.business.AccountBusiness;
import org.wipo.connect.shared.exchange.dto.impl.Account;
import org.wipo.connect.shared.exchange.dto.impl.Permission;
import org.wipo.connect.shared.exchange.dto.impl.SecGroup;
import org.wipo.connect.shared.exchange.dto.impl.UiLanguage;
import org.wipo.connect.shared.exchange.enumerator.IdentifierTypeEnum;
import org.wipo.connect.shared.exchange.vo.AccountSearchVO;
import org.wipo.connect.shared.persistence.dao.AccountDAO;
import org.wipo.connect.shared.persistence.dao.AccountIdentifierFlatDAO;
import org.wipo.connect.shared.persistence.dao.PermissionDAO;
import org.wipo.connect.shared.persistence.dao.SecGroupDAO;
import org.wipo.connect.shared.persistence.dao.UiLanguageDAO;

import net.bull.javamelody.MonitoredWithSpring;

/**
 * The Class AccountBusinessImpl.
 */
@Service
@MonitoredWithSpring
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class AccountBusinessImpl implements AccountBusiness {

    /** The logger. */
    @SuppressWarnings("unused")
    private static Logger LOGGER = WipoLoggerFactory.getLogger(AccountBusinessImpl.class);

    /** The account dao. */
    @Autowired
    private AccountDAO accountDAO;

    /** The account identifier flat dao. */
    @Autowired
    private AccountIdentifierFlatDAO accountIdentifierFlatDAO;

    @Autowired
    private UiLanguageDAO uiLanguageDAO;

    @Autowired
    private SecGroupDAO secGroupDAO;

    @Autowired
    private PermissionDAO permissionDAO;

    @ExecutionTimeTrackable
    @Loggable(level = "TRACE")
    @Override
    public List<Account> findAccount(AccountSearchVO searchVO) throws WccException {
	List<Account> resList = null;
	try {
	    Map<String, Object> searchMap = WccUtils.objToMap(searchVO);
	    resList = this.accountDAO.findAccount(searchMap);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return resList;
    }

    @ExecutionTimeTrackable
    @Loggable(level = "TRACE")
    @Override
    public Account findById(Long id) throws WccException {
	Account account = null;
	try {
	    account = this.accountDAO.find(id);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return account;
    }

    @ExecutionTimeTrackable
    @Loggable(level = "TRACE")
    @Override
    public Account getAccountByMail(String email) throws WccException {

	Account account;
	try {
	    account = this.accountDAO.getAccountByMail(email);
	    // if (null == account) {
	    // throw new WccException(WccExceptionCodeEnum.VALIDATION_ERROR, "Account cannot be null");
	    // }
	    // } catch (WccException e) {
	    // throw e;
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return account;
    }

    @ExecutionTimeTrackable
    @Loggable(level = "TRACE")
    @Override
    public Account saveOrUpdate(Account account) throws WccException {
	boolean isInsert;
	Account objOut;
	try {
	    isInsert = account.getId() == null;
	    objOut = this.accountDAO.merge(account);

	    if (StringUtils.isNotBlank(objOut.getPassword())) {
		updatePassword(objOut);
	    }

	    if (isInsert) {
		this.accountIdentifierFlatDAO.insertAccountId(objOut.getId(), IdentifierTypeEnum.WCC_ACCOUNT_ID);
	    }

	    objOut = findById(objOut.getId());
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return objOut;
    }

    @ExecutionTimeTrackable
    @Loggable(level = "TRACE")
    @Override
    public void updatePassword(Account account) throws WccException {
	this.accountDAO.updatePassword(account);
    }

    @Override
    public UiLanguage findUiLanguageByAccount(Long idAccount) throws WccException {
	return uiLanguageDAO.findByAccount(idAccount);
    }

    @ExecutionTimeTrackable
    @Loggable(level = "TRACE")
    @Override
    public List<SecGroup> getSecGroupList() throws WccException {
	return secGroupDAO.getSecGroupList();
    }

    @ExecutionTimeTrackable
    @Loggable(level = "TRACE")
    @Override
    public SecGroup findSecGroupById(Long idSecGroup) throws WccException {
	return secGroupDAO.find(idSecGroup);
    }

    @ExecutionTimeTrackable
    @Loggable(level = "TRACE")
    @Override
    public SecGroup saveOrUpdateSecGroup(SecGroup secGroup) throws WccException {
	if (secGroup.getId() != null) {
	    this.permissionDAO.deleteAccountSecGroupByIdSecGroup(secGroup.getId());
	    this.accountDAO.deleteAccountSecGroupByIdSecGroup(secGroup.getId());
	}
	SecGroup secGroupSave = this.secGroupDAO.merge(secGroup);
	for (Permission permission : secGroup.getPermissionList()) {
	    this.permissionDAO.insertSecGroupPermission(secGroup.getId(), permission.getId());
	}
	for (Account account : secGroup.getAccountList()) {
	    this.accountDAO.insertAccountSecGroup(secGroup.getId(), account.getId());
	}
	return secGroupSave;
    }

    @ExecutionTimeTrackable
    @Loggable(level = "TRACE")
    @Override
    public void deleteSecGroup(Long idSecGroup) throws WccException {
	this.accountDAO.deleteAccountSecGroupByIdSecGroup(idSecGroup);
	this.permissionDAO.deleteAccountSecGroupByIdSecGroup(idSecGroup);
	this.secGroupDAO.delete(idSecGroup);
    }

    @ExecutionTimeTrackable
    @Loggable(level = "TRACE")
    @Override
    public List<Account> findUsers() throws WccException {
	return this.accountDAO.findAll();
    }

    @ExecutionTimeTrackable
    @Loggable(level = "TRACE")
    @Override
    public List<Permission> findPermissions() throws WccException {
	return this.permissionDAO.findAll();
    }

    @ExecutionTimeTrackable
    @Loggable(level = "TRACE")
    @Override
    public Long updatePermissionList(List<Long> idsPermission, SecGroup secGroup) throws WccException {
	if (secGroup.getId() == null) {
	    this.secGroupDAO.merge(secGroup);
	}
	for (Long idPermission : idsPermission) {
	    this.permissionDAO.insertSecGroupPermission(secGroup.getId(), idPermission);
	}
	// List<Permission> permissionList=this.permissionDAO.selectPermissionsByIdGroup(secGroup.getId());
	return secGroup.getId();
    }

    @ExecutionTimeTrackable
    @Loggable(level = "TRACE")
    @Override
    public void deleteSecGroupPermission(Long idSecGroup, Long idPermission) {
	this.permissionDAO.deleteSecGroupPermission(idSecGroup, idPermission);
    }

    @ExecutionTimeTrackable
    @Loggable(level = "TRACE")
    @Override
    public List<Permission> findPermissionsNotAlreadyAssociated(List<Long> idsPermission) throws WccException {

	List<Permission> listOfPermissionAssociated = new ArrayList<>();

	for (Long id : idsPermission) {
	    listOfPermissionAssociated.add(this.permissionDAO.find(id));
	}
	List<Permission> listAllPermission = this.findPermissions();
	List<Permission> listPermissionToDisplay = new ArrayList<Permission>();

	for (Permission permTot : listAllPermission) {
	    boolean isAlreadyAssociated = false;
	    for (Permission perm : listOfPermissionAssociated) {
		if (perm.getId().equals(permTot.getId())) {
		    isAlreadyAssociated = true;
		    break;
		}
	    }
	    if (isAlreadyAssociated == false) {
		listPermissionToDisplay.add(permTot);
	    }
	}
	return listPermissionToDisplay;
    }

    //
    // @ExecutionTimeTrackable
    // @Loggable(level = "TRACE")
    // @Override
    // public List<Account> findAccountsNotAlreadyAssociated(AccountSearchVO vo) throws WccException {
    // List<Account> resList = null;
    // List<Account> listOfAccountAssociated=null;
    // try {
    // Map<String,Object> searchMap = WccUtils.objToMap(vo);
    // //all accounts (filter)
    // resList = this.accountDAO.findAccount(searchMap);
    // // accounts already associated
    // listOfAccountAssociated=this.accountDAO.findAccountsByIdGroup(vo.getIdSecGroup());
    // List<Account> listAccountToDisplay=new ArrayList<Account>();
    // for(Account accTot: resList){
    // boolean isAlreadyAssociated=false;
    // for(Account account:listOfAccountAssociated){
    // if(account.getId().equals(accTot.getId())){
    // isAlreadyAssociated=true;
    // break;
    // }
    // }
    // if(isAlreadyAssociated==false){
    // listAccountToDisplay.add(accTot);
    // }
    // }
    // return listAccountToDisplay;
    // } catch (Exception e) {
    // throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
    // }
    // }

    @ExecutionTimeTrackable
    @Loggable(level = "TRACE")
    @Override
    public Long updateAccountList(List<Long> idsAccount, SecGroup secGroup) throws WccException {
	if (secGroup.getId() == null) {
	    this.secGroupDAO.merge(secGroup);
	}
	for (Long idAccount : idsAccount) {
	    this.accountDAO.insertAccountSecGroup(secGroup.getIdSecGroup(), idAccount);
	}
	// List<Account> accountList=this.accountDAO.findAccountsByIdGroup(secGroup.getIdSecGroup());
	return secGroup.getIdSecGroup();
    }

    @ExecutionTimeTrackable
    @Loggable(level = "TRACE")
    @Override
    public void deleteAccountSecGroup(Long idSecGroup, Long idAccount) throws WccException {
	this.accountDAO.deleteAccountSecGroup(idSecGroup, idAccount);
    }

    @Override
    public List<Account> findAccountsForSecGroup(AccountSearchVO vo) throws WccException {
	List<Account> resList = null;
	List<Account> listOfAccountAssociated = new ArrayList<>();
	try {
	    Map<String, Object> searchMap = WccUtils.objToMap(vo);
	    // all accounts (filter)
	    resList = this.accountDAO.findAccount(searchMap);
	    // accounts already associated
	    for (Long id : vo.getIdsAccount()) {
		listOfAccountAssociated.add(this.accountDAO.find(id));
	    }
	    List<Account> listAccountToDisplay = new ArrayList<Account>();
	    for (Account accTot : resList) {
		boolean isAlreadyAssociated = false;
		for (Account account : listOfAccountAssociated) {
		    if (account.getId().equals(accTot.getId())) {
			isAlreadyAssociated = true;
			break;
		    }
		}
		if (isAlreadyAssociated == false) {
		    listAccountToDisplay.add(accTot);
		}
	    }
	    return listAccountToDisplay;
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    @Override
    public List<SecGroup> findSecGroupByIdAccount(Long idAccount) throws WccException {
	return this.secGroupDAO.findSecGroupByIdAccount(idAccount);
    }

    @Override
    public void deleteAccount(Long idAccount) throws WccException {
	this.accountDAO.deleteAccountSecGroupByIdAccount(idAccount);
	this.accountIdentifierFlatDAO.deleteAccountIdentidierByIdAccount(idAccount);
	this.accountDAO.deleteAccount(idAccount);
    }

}