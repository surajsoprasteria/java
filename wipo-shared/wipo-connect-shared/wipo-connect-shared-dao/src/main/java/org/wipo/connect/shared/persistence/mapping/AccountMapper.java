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
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.wipo.connect.shared.exchange.dto.impl.Account;




/**
 * The Interface AccountMapper.
 *
 * @author minervini
 */
public interface AccountMapper extends Mapper<Account> {

    /**
     * Find account.
     *
     * @param map
     *            the map
     * @return the list
     */
    List<Account> findAccount(Map<String, Object> map);



    /**
     * Gets the account by mail.
     *
     * @param email
     *            the email
     * @return the account by mail
     */
    Account getAccountByMail(String email);



    /**
     * Update password.
     *
     * @param account
     *            the account
     */
    void updatePassword(Account account);

    /**
     * Select accounts by id group.
     *
     * @param idSecGroup the id sec group
     * @return the list
     */
    List<Account> selectAccountsByIdGroup(@Param("idSecGroup") Long idSecGroup);

	/**
	 * Insert account sec group.
	 *
	 * @param fkSecGroup the fk sec group
	 * @param fkAccount the fk account
	 * @return the int
	 */
	int insertAccountSecGroup(@Param("fkSecGroup") Long fkSecGroup, @Param("fkAccount") Long fkAccount);

	/**
	 * Delete account sec group.
	 *
	 * @param fkSecGroup the fk sec group
	 * @param fkAccount the fk account
	 * @return the int
	 */
	int deleteAccountSecGroup(@Param("fkSecGroup") Long fkSecGroup, @Param("fkAccount") Long fkAccount);

	/**
	 * Delete account sec group by id sec group.
	 *
	 * @param fkSecGroup the fk sec group
	 * @return the int
	 */
	int deleteAccountSecGroupByIdSecGroup(@Param("fkSecGroup") Long fkSecGroup);

	/**
	 * Delete account sec group by id account.
	 *
	 * @param fkAccount the fk account
	 * @return the int
	 */
	int deleteAccountSecGroupByIdAccount(@Param("fkAccount") Long fkAccount);

	/**
	 * Delete account.
	 *
	 * @param idAccount the id account
	 * @return the int
	 */
	int deleteAccount(@Param("idAccount")Long idAccount);


}
