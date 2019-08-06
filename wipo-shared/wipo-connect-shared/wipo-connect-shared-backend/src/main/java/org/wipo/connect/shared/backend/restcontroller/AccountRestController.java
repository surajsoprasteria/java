/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Copyright Connection (WCC).
 *
 *
 * @author Fincons
 *
 */



package org.wipo.connect.shared.backend.restcontroller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.shared.exchange.business.AccountBusiness;
import org.wipo.connect.shared.exchange.dto.impl.Account;
import org.wipo.connect.shared.exchange.dto.impl.Permission;
import org.wipo.connect.shared.exchange.dto.impl.SecGroup;
import org.wipo.connect.shared.exchange.dto.impl.UiLanguage;
import org.wipo.connect.shared.exchange.restvo.account.DeleteAccountSecGroupRestVO;
import org.wipo.connect.shared.exchange.restvo.account.DeleteSecGroupPermissiontRestVO;
import org.wipo.connect.shared.exchange.restvo.account.FindAccountRestVO;
import org.wipo.connect.shared.exchange.restvo.account.FindByIdRestVO;
import org.wipo.connect.shared.exchange.restvo.account.FindByIdsPermissionRestVO;
import org.wipo.connect.shared.exchange.restvo.account.FindUiLanguageByAccountRestVO;
import org.wipo.connect.shared.exchange.restvo.account.GetAccountByMailRestVO;
import org.wipo.connect.shared.exchange.restvo.account.SaveOrUpdateRestVO;
import org.wipo.connect.shared.exchange.restvo.account.SaveOrUpdateSecGroupRestVO;
import org.wipo.connect.shared.exchange.restvo.account.UpdateAccountsRestVO;
import org.wipo.connect.shared.exchange.restvo.account.UpdatePasswordRestVO;
import org.wipo.connect.shared.exchange.restvo.account.UpdatePermissionsRestVO;



/**
 * The Class AccountRestController.
 */
@RestController
@RequestMapping(value = "/account")
public class AccountRestController extends BaseRestController {

    /** The account business. */
    @Autowired
    @Qualifier("accountBusinessImpl")
    AccountBusiness accountBusiness;



    /**
     * Find by id.
     *
     * @param reqVO the req VO
     * @return the account
     * @throws WccException the wcc exception
     */
    @ResponseBody
    @RequestMapping("findById")
    public Account findById(@RequestBody FindByIdRestVO reqVO)
            throws WccException {

        return accountBusiness.findById(reqVO.getId());

    }



    /**
     * Save or update.
     *
     * @param reqVO the req VO
     * @return the account
     * @throws WccException the wcc exception
     */
    @ResponseBody
    @RequestMapping("saveOrUpdate")
    public Account saveOrUpdate(@RequestBody SaveOrUpdateRestVO reqVO)
            throws WccException {

        return accountBusiness.saveOrUpdate(reqVO.getDto());

    }



    /**
     * Find account.
     *
     * @param reqVO the req VO
     * @return the list
     * @throws WccException the wcc exception
     */
    @ResponseBody
    @RequestMapping("findAccount")
    public List<Account> findAccount(@RequestBody FindAccountRestVO reqVO)
            throws WccException {

        return accountBusiness.findAccount(reqVO.getSearchVO());

    }



    /**
     * Gets the account by mail.
     *
     * @param reqVO the req VO
     * @return the account by mail
     * @throws WccException the wcc exception
     */
    @ResponseBody
    @RequestMapping("getAccountByMail")
    public Account getAccountByMail(
            @RequestBody GetAccountByMailRestVO reqVO)
            throws WccException {

        return accountBusiness.getAccountByMail(reqVO.getEmail());

    }



    /**
     * Update password.
     *
     * @param reqVO the req VO
     * @return the boolean
     * @throws WccException the wcc exception
     */
    @ResponseBody
    @RequestMapping("updatePassword")
    public Boolean updatePassword(@RequestBody UpdatePasswordRestVO reqVO)
            throws WccException {

        accountBusiness.updatePassword(reqVO.getDto());
        return true;

    }

    /**
     * Find ui language by account.
     *
     * @param reqVO the req VO
     * @return the ui language
     * @throws WccException the wcc exception
     */
    @ResponseBody
    @RequestMapping("findUiLanguageByAccount")
    public UiLanguage findUiLanguageByAccount(@RequestBody FindUiLanguageByAccountRestVO reqVO)
            throws WccException {

        return accountBusiness.findUiLanguageByAccount(reqVO.getIdAccount());

    }


    /**
     * Gets the sec group list.
     *
     * @return the sec group list
     * @throws WccException the wcc exception
     */
    @ResponseBody
    @RequestMapping("getSecGroupList")
    public List<SecGroup> getSecGroupList() throws WccException {
        return accountBusiness.getSecGroupList();

    }

    /**
     * Find sec group by id.
     *
     * @param reqVO the req VO
     * @return the sec group
     * @throws WccException the wcc exception
     */
    @ResponseBody
    @RequestMapping("findSecGroupById")
	public SecGroup findSecGroupById(@RequestBody FindByIdRestVO reqVO) throws WccException {
    	return accountBusiness.findSecGroupById(reqVO.getId());
    }

    /**
     * Save or update sec group.
     *
     * @param reqVO the req VO
     * @return the sec group
     * @throws WccException the wcc exception
     */
    @ResponseBody
    @RequestMapping("saveOrUpdateSecGroup")
	public SecGroup saveOrUpdateSecGroup(@RequestBody SaveOrUpdateSecGroupRestVO reqVO) throws WccException {
    	return this.accountBusiness.saveOrUpdateSecGroup(reqVO.getDto());
    }


    /**
     * Delete sec group.
     *
     * @param reqVO the req VO
     * @throws WccException the wcc exception
     */
    @ResponseBody
    @RequestMapping("deleteSecGroup")
	public void deleteSecGroup(@RequestBody FindByIdRestVO reqVO) throws WccException {
    	this.accountBusiness.deleteSecGroup(reqVO.getId());
    }

    /**
     * Find users.
     *
     * @return the list
     * @throws WccException the wcc exception
     */
    @ResponseBody
    @RequestMapping("findUsers")
    public List<Account> findUsers() throws WccException {
    	return this.accountBusiness.findUsers();
    }

    /**
     * Find permissions.
     *
     * @return the list
     * @throws WccException the wcc exception
     */
    @ResponseBody
    @RequestMapping("findPermissions")
    public List<Permission> findPermissions() throws WccException {
    	return this.accountBusiness.findPermissions();
    }

    /**
     * Update permission list.
     *
     * @param reqVO the req VO
     * @return the long
     * @throws WccException the wcc exception
     */
    @ResponseBody
    @RequestMapping("updatePermissionList")
    public Long updatePermissionList(@RequestBody UpdatePermissionsRestVO reqVO) throws WccException {
    	return this.accountBusiness.updatePermissionList(reqVO.getIdsPermission(),reqVO.getSecGroup());
    }

    /**
     * Delete sec group permission.
     *
     * @param reqVO the req VO
     * @return the boolean
     * @throws WccException the wcc exception
     */
    @ResponseBody
    @RequestMapping("deleteSecGroupPermission")
    public Boolean deleteSecGroupPermission(@RequestBody DeleteSecGroupPermissiontRestVO reqVO) throws WccException {
        accountBusiness.deleteSecGroupPermission(reqVO.getIdSecGroup(),reqVO.getIdPermission());
        return true;
    }

    /**
     * Find permissions not already associated.
     *
     * @param reqVO the req VO
     * @return the list
     * @throws WccException the wcc exception
     */
    @ResponseBody
    @RequestMapping("findPermissionsNotAlreadyAssociated")
    public List<Permission> findPermissionsNotAlreadyAssociated(@RequestBody FindByIdsPermissionRestVO reqVO) throws WccException {
    	return this.accountBusiness.findPermissionsNotAlreadyAssociated(reqVO.getIdsPermission());
    }

    /**
     * Update account list.
     *
     * @param reqVO the req VO
     * @return the long
     * @throws WccException the wcc exception
     */
    @ResponseBody
    @RequestMapping("updateAccountList")
    public Long updateAccountList(@RequestBody UpdateAccountsRestVO reqVO) throws WccException {
    	return this.accountBusiness.updateAccountList(reqVO.getIdsAccount(),reqVO.getSecGroup());
    }


    /**
     * Delete account sec group.
     *
     * @param reqVO the req VO
     * @return the boolean
     * @throws WccException the wcc exception
     */
    @ResponseBody
    @RequestMapping("deleteAccountSecGroup")
    public Boolean deleteAccountSecGroup(@RequestBody DeleteAccountSecGroupRestVO reqVO) throws WccException {
        accountBusiness.deleteAccountSecGroup(reqVO.getIdSecGroup(),reqVO.getIdAccount());
        return true;
    }

    /**
     * Find sec group by id account.
     *
     * @param reqVO the req VO
     * @return the list
     * @throws WccException the wcc exception
     */
    @ResponseBody
    @RequestMapping("findSecGroupByIdAccount")
    public List<SecGroup> findSecGroupByIdAccount(@RequestBody FindByIdRestVO reqVO) throws WccException {
    	return this.accountBusiness.findSecGroupByIdAccount(reqVO.getId());
    }

    /**
     * Find accounts for sec group.
     *
     * @param reqVO the req VO
     * @return the list
     * @throws WccException the wcc exception
     */
    @ResponseBody
    @RequestMapping("findAccountsForSecGroup")
    public List<Account> findAccountsForSecGroup(@RequestBody FindAccountRestVO reqVO) throws WccException {
    	return this.accountBusiness.findAccountsForSecGroup(reqVO.getSearchVO());
    }

    /**
     * Delete account.
     *
     * @param reqVO the req VO
     * @throws WccException the wcc exception
     */
    @ResponseBody
    @RequestMapping("deleteAccount")
	public void deleteAccount(@RequestBody FindByIdRestVO reqVO) throws WccException {
    	this.accountBusiness.deleteAccount(reqVO.getId());
    }

}
