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

package org.wipo.connect.shared.exchange.restclient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.wipo.connect.common.crypto.CryptoHelper;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.exception.WccExceptionFactory;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.restclient.BasicAuthRestTemplate;
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
import org.wipo.connect.shared.exchange.vo.AccountSearchVO;

/**
 * The Class AccountBusinessRestClient.
 */
@Service
@Qualifier("accountBusinessRestClient")
public class AccountBusinessRestClient implements AccountBusiness {

    /** The Constant LOGGER. */
    @SuppressWarnings("unused")
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(AccountBusinessRestClient.class);

    /** The Constant PROP_URL. */
    private static final String PROP_URL = "backendUrl";

    /** The Constant PROP_PATH. */
    private static final String PROP_PATH = "restPath";

    /** The Constant CONTROLLER_PATH. */
    private static final String CONTROLLER_PATH = "account";

    /** The base url. */
    private String baseUrl;

    /** The shared remoting properties. */
    @Autowired
    private Properties configProperties;

    private RestTemplate restTemplate;

    @Value("#{configProperties.restWsUser}")
    private String restWsUser;

    @Value("#{configProperties.restWsPass}")
    private String restWsPass;

    @Autowired
    private CryptoHelper cryptoHelper;

    /**
     * Inits the.
     */
    @PostConstruct
    public void init() {
	baseUrl = configProperties.getProperty(PROP_URL) + "/" +
		configProperties.getProperty(PROP_PATH) + "/" +
		CONTROLLER_PATH + "/";

	restWsPass = cryptoHelper.decrypt(restWsPass);
	restTemplate = new BasicAuthRestTemplate(restWsUser, restWsPass);
    }

    @Override
    public Account findById(Long id) throws WccException {
	String endpoint = baseUrl + "findById";

	try {
	    FindByIdRestVO reqVO = new FindByIdRestVO();
	    reqVO.setId(id);
	    return restTemplate.postForObject(endpoint, reqVO, Account.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Account saveOrUpdate(Account dto) throws WccException {
	String endpoint = baseUrl + "saveOrUpdate";

	try {
	    SaveOrUpdateRestVO reqVO = new SaveOrUpdateRestVO();
	    reqVO.setDto(dto);
	    return restTemplate.postForObject(endpoint, reqVO, Account.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<Account> findAccount(AccountSearchVO searchVO) throws WccException {
	String endpoint = baseUrl + "findAccount";

	try {
	    FindAccountRestVO reqVO = new FindAccountRestVO();
	    reqVO.setSearchVO(searchVO);
	    Account[] results = restTemplate.postForObject(endpoint, reqVO, Account[].class);
	    return new ArrayList<Account>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Account getAccountByMail(String email) throws WccException {
	String endpoint = baseUrl + "getAccountByMail";

	try {
	    GetAccountByMailRestVO reqVO = new GetAccountByMailRestVO();
	    reqVO.setEmail(email);
	    return restTemplate.postForObject(endpoint, reqVO, Account.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public void updatePassword(Account account) throws WccException {
	String endpoint = baseUrl + "updatePassword";

	try {
	    UpdatePasswordRestVO reqVO = new UpdatePasswordRestVO();
	    reqVO.setDto(account);
	    restTemplate.postForObject(endpoint, reqVO, Boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public UiLanguage findUiLanguageByAccount(Long idAccount) throws WccException {
	String endpoint = baseUrl + "findUiLanguageByAccount";

	try {
	    FindUiLanguageByAccountRestVO reqVO = new FindUiLanguageByAccountRestVO();
	    reqVO.setIdAccount(idAccount);
	    return restTemplate.postForObject(endpoint, reqVO, UiLanguage.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<SecGroup> getSecGroupList() throws WccException {
	String endpoint = baseUrl + "getSecGroupList";

	try {
	    SecGroup[] results = restTemplate.getForObject(endpoint, SecGroup[].class);
	    return new ArrayList<SecGroup>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public SecGroup findSecGroupById(Long idSecGroup) throws WccException {
	String endpoint = baseUrl + "findSecGroupById";

	try {
	    FindByIdRestVO reqVO = new FindByIdRestVO();
	    reqVO.setId(idSecGroup);
	    return restTemplate.postForObject(endpoint, reqVO, SecGroup.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public SecGroup saveOrUpdateSecGroup(SecGroup secGroup) throws WccException {
	String endpoint = baseUrl + "saveOrUpdateSecGroup";

	try {
	    SaveOrUpdateSecGroupRestVO reqVO = new SaveOrUpdateSecGroupRestVO();
	    reqVO.setDto(secGroup);
	    return restTemplate.postForObject(endpoint, reqVO, SecGroup.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public void deleteSecGroup(Long idSecGroup) throws WccException {
	String endpoint = baseUrl + "deleteSecGroup";

	try {
	    FindByIdRestVO reqVO = new FindByIdRestVO();
	    reqVO.setId(idSecGroup);
	    restTemplate.postForObject(endpoint, reqVO, Boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<Account> findUsers() throws WccException {
	String endpoint = baseUrl + "findUsers";

	try {
	    Account[] results = restTemplate.getForObject(endpoint, Account[].class);
	    List<Account> list = new ArrayList<Account>(Arrays.asList(results));
	    return list;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<Permission> findPermissions() throws WccException {
	String endpoint = baseUrl + "findPermissions";

	try {
	    Permission[] results = restTemplate.getForObject(endpoint, Permission[].class);
	    List<Permission> list = new ArrayList<Permission>(Arrays.asList(results));
	    return list;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Long updatePermissionList(List<Long> idsPermission, SecGroup secGroup) throws WccException {
	String endpoint = baseUrl + "updatePermissionList";

	try {
	    UpdatePermissionsRestVO reqVO = new UpdatePermissionsRestVO();
	    reqVO.setSecGroup(secGroup);
	    reqVO.setIdsPermission(idsPermission);
	    return restTemplate.postForObject(endpoint, reqVO, Long.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}

    }

    @Override
    public void deleteSecGroupPermission(Long idSecGroup, Long idPermission) throws WccException {
	String endpoint = baseUrl + "deleteSecGroupPermission";

	try {
	    DeleteSecGroupPermissiontRestVO reqVO = new DeleteSecGroupPermissiontRestVO();
	    reqVO.setIdSecGroup(idSecGroup);
	    reqVO.setIdPermission(idPermission);
	    restTemplate.postForObject(endpoint, reqVO, Boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<Permission> findPermissionsNotAlreadyAssociated(List<Long> idsPermission) throws WccException {
	String endpoint = baseUrl + "findPermissionsNotAlreadyAssociated";

	try {
	    FindByIdsPermissionRestVO reqVO = new FindByIdsPermissionRestVO();
	    reqVO.setIdsPermission(idsPermission);
	    Permission[] results = restTemplate.postForObject(endpoint, reqVO, Permission[].class);
	    return new ArrayList<Permission>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Long updateAccountList(List<Long> idsAccount, SecGroup secGroup) throws WccException {

	String endpoint = baseUrl + "updateAccountList";

	try {
	    UpdateAccountsRestVO reqVO = new UpdateAccountsRestVO();
	    reqVO.setSecGroup(secGroup);
	    reqVO.setIdsAccount(idsAccount);
	    return restTemplate.postForObject(endpoint, reqVO, Long.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public void deleteAccountSecGroup(Long idSecGroup, Long idAccount) throws WccException {
	String endpoint = baseUrl + "deleteAccountSecGroup";

	try {
	    DeleteAccountSecGroupRestVO reqVO = new DeleteAccountSecGroupRestVO();
	    reqVO.setIdSecGroup(idSecGroup);
	    reqVO.setIdAccount(idAccount);
	    restTemplate.postForObject(endpoint, reqVO, Boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<Account> findAccountsForSecGroup(AccountSearchVO vo) throws WccException {
	String endpoint = baseUrl + "findAccountsForSecGroup";

	try {
	    FindAccountRestVO reqVO = new FindAccountRestVO();
	    reqVO.setSearchVO(vo);
	    Account[] results = restTemplate.postForObject(endpoint, reqVO, Account[].class);
	    return new ArrayList<Account>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<SecGroup> findSecGroupByIdAccount(Long fkAccount) throws WccException {
	String endpoint = baseUrl + "findSecGroupByIdAccount";

	try {
	    FindByIdRestVO reqVO = new FindByIdRestVO();
	    reqVO.setId(fkAccount);
	    SecGroup[] results = restTemplate.postForObject(endpoint, reqVO, SecGroup[].class);
	    return new ArrayList<SecGroup>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public void deleteAccount(Long idAccount) throws WccException {
	String endpoint = baseUrl + "deleteAccount";

	try {
	    FindByIdRestVO reqVO = new FindByIdRestVO();
	    reqVO.setId(idAccount);
	    restTemplate.postForObject(endpoint, reqVO, Boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

}
