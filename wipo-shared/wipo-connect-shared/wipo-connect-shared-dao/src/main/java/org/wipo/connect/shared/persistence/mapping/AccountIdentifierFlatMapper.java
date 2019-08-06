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
import org.wipo.connect.shared.exchange.dto.impl.AccountIdentifierFlat;
import org.wipo.connect.shared.exchange.enumerator.IdentifierTypeEnum;



/**
 * The Interface AccountIdentifierFlatMapper.
 */
public interface AccountIdentifierFlatMapper {

    /**
     * Find by account.
     *
     * @param idAccount
     *            the id account
     * @param code
     *            the code
     * @return the list
     */
     List<AccountIdentifierFlat> findByAccount(
            @Param("idAccount") Long idAccount,
            @Param("code") IdentifierTypeEnum code);



    /**
     * Insert.
     *
     * @param idAccount
     *            the id account
     * @param type
     *            the type
     * @param value
     *            the value
     * @return the int
     */
     int insert(@Param("idAccount") Long idAccount,
            @Param("type") IdentifierTypeEnum type, @Param("value") String value);



    /**
     * Select by primary key.
     *
     * @param id
     *            the id
     * @return the account identifier flat
     */
     AccountIdentifierFlat selectByPrimaryKey(Long id);

    /**
     * Delete account identidier by id account.
     *
     * @param idAccount the id account
     * @return the int
     */
    int deleteAccountIdentidierByIdAccount(@Param("idAccount") Long idAccount);

}
