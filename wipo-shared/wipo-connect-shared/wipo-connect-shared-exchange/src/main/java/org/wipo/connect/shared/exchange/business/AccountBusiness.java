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
import org.wipo.connect.shared.exchange.dto.impl.Account;
import org.wipo.connect.shared.exchange.dto.impl.Permission;
import org.wipo.connect.shared.exchange.dto.impl.SecGroup;
import org.wipo.connect.shared.exchange.dto.impl.UiLanguage;
import org.wipo.connect.shared.exchange.vo.AccountSearchVO;

public interface AccountBusiness extends BaseBusiness<Account> {

    List<Account> findAccount(AccountSearchVO searchVO) throws WccException;

    Account getAccountByMail(String email) throws WccException;

    void updatePassword(Account account) throws WccException;

    UiLanguage findUiLanguageByAccount(Long idAccount) throws WccException;

    List<SecGroup> getSecGroupList() throws WccException;

    SecGroup findSecGroupById(Long idSecGroup) throws WccException;

    SecGroup saveOrUpdateSecGroup(SecGroup secGroup) throws WccException;

    void deleteSecGroup(Long idSecGroup) throws WccException;

    List<Account> findUsers() throws WccException;

    List<Permission> findPermissions() throws WccException;

    Long updatePermissionList(List<Long> idsPermission, SecGroup secGroup) throws WccException;

    void deleteSecGroupPermission(Long idSecGroup, Long idPermission) throws WccException;

    List<Permission> findPermissionsNotAlreadyAssociated(List<Long> idsPermission) throws WccException;

    Long updateAccountList(List<Long> idsAccount, SecGroup secGroup) throws WccException;

    void deleteAccountSecGroup(Long idSecGroup, Long idAccount) throws WccException;

    List<Account> findAccountsForSecGroup(AccountSearchVO vo) throws WccException;

    List<SecGroup> findSecGroupByIdAccount(Long fkAccount) throws WccException;

    void deleteAccount(Long idAccount) throws WccException;

}
