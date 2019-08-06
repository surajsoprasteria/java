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

import org.apache.ibatis.annotations.Param;
import org.wipo.connect.shared.exchange.dto.impl.UiLanguage;

/**
 * The Interface UiLanguageMapper.
 */
public interface UiLanguageMapper extends Mapper<UiLanguage>{

    /**
     * Find by account.
     *
     * @param idAccount the id account
     * @return the ui language
     */
    UiLanguage findByAccount(@Param("idAccount") Long idAccount);

}
