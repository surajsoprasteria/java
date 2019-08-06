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
package org.wipo.connect.common.authentication;

import java.io.Serializable;

public interface ISecurityUserDetail {

   String getUsername();
   String getPassword();
   Serializable getId();

}
