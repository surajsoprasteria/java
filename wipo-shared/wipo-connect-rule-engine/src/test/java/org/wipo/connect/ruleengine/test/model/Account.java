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
package org.wipo.connect.ruleengine.test.model;

import java.io.Serializable;

public class Account implements Serializable{
	private static final long serialVersionUID = 4201169403913048382L;

	private String username;
    private String email;
    private Boolean active;

    public Account(String username,
            String email,
            Boolean active) {
        super();
        this.username = username;
        this.email = email;
        this.active = active;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }

}
