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
import java.util.Map;

import org.wipo.connect.shared.exchange.dto.impl.Account;
import org.wipo.connect.shared.persistence.Dao;




/**
 * The AccountDAO interface provides methods that manipulate the data from the
 * database.
 *
 * @author Minervini
 *
 */
public interface AccountDAO extends Dao<Account> {

    /**
     * Find account items using parameters contained into the input map.
     *
     * @param map
     *            the map
     * @return the list
     */
    List<Account> findAccount(Map<String, Object> map);



    /**
     * Find account item using parameter mail.
     *
     * @param email
     *            the email
     * @return the account
     */
    Account getAccountByMail(String email);



    /**
     * Update the account password.
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
    List<Account> findAccountsByIdGroup(Long idSecGroup);

  	/**
	   * Insert account sec group.
	   *
	   * @param fkSecGroup the fk sec group
	   * @param fkAccount the fk account
	   * @return the int
	   */
	  int insertAccountSecGroup(Long fkSecGroup,Long fkAccount);

  	/**
	   * Delete account sec group.
	   *
	   * @param fkSecGroup the fk sec group
	   * @param fkAccount the fk account
	   * @return the int
	   */
	  int deleteAccountSecGroup(Long fkSecGroup,Long fkAccount);

	  /**
  	 * Delete account sec group by id sec group.
  	 *
  	 * @param fkSecGroup the fk sec group
  	 * @return the int
  	 */
  	int deleteAccountSecGroupByIdSecGroup(Long fkSecGroup);

  	/**
	   * Delete account.
	   *
	   * @param idAccount the id account
	   * @return the int
	   */
	  int deleteAccount(Long idAccount);

  	/**
	   * Delete account sec group by id account.
	   *
	   * @param fkAccount the fk account
	   * @return the int
	   */
	  int deleteAccountSecGroupByIdAccount(Long fkAccount);


}
