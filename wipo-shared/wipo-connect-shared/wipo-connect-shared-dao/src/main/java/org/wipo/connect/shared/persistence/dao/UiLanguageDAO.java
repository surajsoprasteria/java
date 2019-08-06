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



import org.wipo.connect.shared.exchange.dto.impl.UiLanguage;
import org.wipo.connect.shared.persistence.Dao;



/**
 * The UiLanguageDAO interface provides methods that manipulate the data from the
 * database.
 *
 * @author fincons
 *
 */
public interface UiLanguageDAO extends Dao<UiLanguage> {

    /**
     * Find UI Language item related to the account.
     *
     * @param idAccount the id account
     * @return the list
     */
    UiLanguage findByAccount(Long idAccount);


}
