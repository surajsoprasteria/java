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



package org.wipo.connect.shared.persistence.mapping;



import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.wipo.connect.shared.exchange.dto.impl.Identifier;




/**
 * The Interface IdentifierMapper.
 */
public interface IdentifierMapper extends Mapper<Identifier> {

    /**
     * Insert identifier.
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
    Identifier findByCode( @Param("code") String code);

	public List<Identifier> selectAllIdentifier();
}
