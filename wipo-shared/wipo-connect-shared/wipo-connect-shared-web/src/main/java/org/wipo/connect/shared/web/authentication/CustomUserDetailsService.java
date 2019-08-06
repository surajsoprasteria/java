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

package org.wipo.connect.shared.web.authentication;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.shared.exchange.business.AccountBusiness;
import org.wipo.connect.shared.exchange.dto.impl.Account;
import org.wipo.connect.shared.exchange.dto.impl.UiLanguage;

/**
 * The Class CustomUserDetailsService.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    /** The Constant logger. */
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(CustomUserDetailsService.class);

    /** The account business. */
    @Autowired
    private AccountBusiness accountBusiness;

    /**
     * Inits the.
     */
    @PostConstruct
    public void init() {
	LOGGER.info("Init CustomUserDetailsService");
    }

    @Loggable
    @ExecutionTimeTrackable
    @Override
    public UserDetails loadUserByUsername(String username) {

	Account account;
	UiLanguage uiLanguage;
	SecurityUserDetail userDetail = null;

	try {
	    account = this.accountBusiness.getAccountByMail(username);

	    if (account == null) {
		throw new UsernameNotFoundException("Unable to find the user '" + username + "'");
	    }

	    uiLanguage = accountBusiness.findUiLanguageByAccount(account.getId());

	    userDetail = new SecurityUserDetail(account, uiLanguage);
	} catch (UsernameNotFoundException e) {
	    LOGGER.warn("Unable to find the user '" + username + "'");
	    throw e;
	} catch (Exception e) {
	    throw new UsernameNotFoundException("Error in " + WccUtils.getMethodName(), e);
	}
	return userDetail;
    }

}
