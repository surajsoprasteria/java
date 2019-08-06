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



package org.wipo.connect.shared.integration.authentication;



import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.wipo.connect.common.authentication.ISecurityUserDetail;
import org.wipo.connect.shared.exchange.dto.impl.Account;



/**
 * The Class SecurityUserDetail.
 */
public class SecurityUserDetail implements UserDetails, ISecurityUserDetail {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6520521903873473290L;

    /** The account. */
    private Account account;

    /** The granted authorities. */
    private Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();



    /**
     * Instantiates a new security user detail.
     *
     * @param account
     *            the account
     */
    public SecurityUserDetail(Account account) {
        this.account = account;
    }



    /**
     * Gets the account.
     *
     * @return the account
     */
    public Account getAccount() {
        return this.account;
    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }




    @Override
    public String getPassword() {
        return this.account.getPassword();
    }




    @Override
    public String getUsername() {
        return this.account.getEmail();
    }




    @Override
    public boolean isAccountNonExpired() {
        return true;
    }




    @Override
    public boolean isAccountNonLocked() {
        return true;
    }




    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }




    @Override
    public boolean isEnabled() {
        return this.account.getActive();
    }



    @Override
	public Long getId() {
		return account.getId();
	}

}
