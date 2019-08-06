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

import org.wipo.connect.shared.exchange.dto.impl.AccountIdentifierFlat;
import org.wipo.connect.shared.exchange.enumerator.IdentifierTypeEnum;

public interface AccountIdentifierFlatDAO {

    AccountIdentifierFlat find(Long id);

    List<AccountIdentifierFlat> findByAccount(Long idAccount);

    List<AccountIdentifierFlat> findByAccount(Long idAccount, IdentifierTypeEnum code);

    List<AccountIdentifierFlat> findISWC(Long idAccount);

    AccountIdentifierFlat findWccId(Long idAccount);

    int insertAccountId(Long idAccount, IdentifierTypeEnum type);

    int insertAccountId(Long idAccount, IdentifierTypeEnum type, String wccAccountId);

    int deleteAccountIdentidierByIdAccount(Long idAccount);

}
