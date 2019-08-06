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
package org.wipo.connect.shared.persistence.dao;

import java.util.List;

import org.wipo.connect.enumerator.IdentifierManagerRefTableEnum;

public interface IdentifierManagerDAO {

    String getNewMainIdentifier(IdentifierManagerRefTableEnum code);

    boolean checkIdUniqueness(IdentifierManagerRefTableEnum name, String nameMainId);

    boolean checkIdUniqueness(IdentifierManagerRefTableEnum code, String mainId, List<Long> idsToExclude);

}
