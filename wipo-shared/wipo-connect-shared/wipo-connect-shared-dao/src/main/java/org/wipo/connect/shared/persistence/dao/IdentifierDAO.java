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



import org.wipo.connect.shared.exchange.dto.impl.Identifier;
import org.wipo.connect.shared.persistence.Dao;




/**
 * The IdentifierDAO interface provides methods that manipulate the data from
 * the database.
 *
 * @author fumagalli
 *
 */
public interface IdentifierDAO extends Dao<Identifier> {
    /**
     * Insert identifier item using identifier parameter.
     *
     * @param identifier
     *            the identifier
     */
    public void insertIdentifier(Identifier identifier);

    /**
     * Find by code.
     *
     * @param code the code
     * @return the identifier
     */
    Identifier findByCode(String code);
}
