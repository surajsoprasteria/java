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

/**
 * The WorkIdentifierFlatDAO interface provides methods that manipulate the data
 * from the database.
 *
 * @author minervini
 *
 */
public interface WorkIdentifierFlatDAO {

	/**
	 * Insert work-identifier item using id work and code parameters.
	 *
	 * @param idWork
	 *            the id work
	 * @param type
	 *            the identifier code
	 * @return the int
	 */
	int insertWorkId(Long idWork, String type);

	/**
	 * Insert work-identifier item using id license, wcc id, code parameters.
	 *
	 * @param idWork
	 *            the work id
	 * @param type
	 *            the identifier code
	 * @param wccWorkId
	 *            the wcc work id
	 * @return the int
	 */
	int insertWorkId(Long idWork, String type, String wccWorkId);
	
	/**
	 * Checks if is exists ISW identifier value.
	 *
	 * @param value the value
	 * @return true, if is exists ISW identifier value
	 */
	boolean isExistsISWIdentifierValue(String value);

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the int
	 */
	int delete(Long id);
	
	boolean identifierValueAlreadyPresent(String code, String value, Long workId);

}
