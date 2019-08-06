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


/**
 * The Interface WorkIdentifierFlatMapper.
 */
public interface WorkIdentifierFlatMapper {

	/**
	 * Insert.
	 *
	 * @param idWork
	 *            the id work
	 * @param type
	 *            the type
	 * @param identifierValue
	 *            the identifier value
	 * @return the int
	 */
	public int insert(@Param("idWork") Long idWork, @Param("type") String type, @Param("identifierValue") String identifierValue);

	/**
	 * Checks if is exists ISW identifier value.
	 *
	 * @param value the value
	 * @return true, if is exists ISW identifier value
	 */
	public boolean isExistsISWIdentifierValue(@Param("value") String value);

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the int
	 */
	public int delete(@Param("id")Long id);
	
	public boolean identifierValueAlreadyPresent(@Param("code") String code, @Param("value") String value, @Param("workId") Long workId);

}
