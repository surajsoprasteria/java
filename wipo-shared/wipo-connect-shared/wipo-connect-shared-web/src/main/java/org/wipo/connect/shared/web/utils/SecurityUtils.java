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



package org.wipo.connect.shared.web.utils;



import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.wipo.connect.shared.web.authentication.SecurityUserDetail;



/**
 * The Class SecurityUtils.
 */
@Component
public class SecurityUtils {

    /**
     * Gets the logged user.
     *
     * @return the logged user
     */
    public SecurityUserDetail getLoggedUser() {

        SecurityUserDetail loggedUser = null;
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        Object userDetail = authentication.getPrincipal();
        if (userDetail != null && userDetail instanceof SecurityUserDetail) {
            loggedUser = (SecurityUserDetail) userDetail;
        }

        return loggedUser;
    }



    /**
     * Gets the logged username.
     *
     * @return the logged username
     */
    public String getLoggedUsername() {

        SecurityUserDetail loggedUser;
        String loggedUsername = null;

        loggedUser = getLoggedUser();

        if (loggedUser != null) {
            loggedUsername = loggedUser.getUsername();
        }

        return loggedUsername;
    }

}
