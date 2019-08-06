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
import org.wipo.connect.shared.exchange.dto.impl.CreationClassFlat;


/**
 * The Interface CreationClassFlatMapper.
 */
public interface CreationClassFlatMapper extends Mapper<CreationClassFlat> {

	/**
	 * Select by primary key.
	 *
	 * @param idRole the id role
	 * @return the CreationClassFlat
	 */
	CreationClassFlat selectByPrimaryKey(@Param("idCreationClass") Long idRole);

	/**
	 * Find by code.
	 *
	 * @param code
	 *            the code
	 * @return the creation class flat
	 */
	public CreationClassFlat findByCode(@Param("code") String code);

	/**
	 * Find all.
	 *
	 * @return CreationClassFlat list
	 */
	List<CreationClassFlat> findAll();
	
	
	/**
	 * Exsist code.
	 *
	 * @param code the code
	 * @param idToExclude the id to exclude
	 * @return the boolean
	 */
	Boolean exsistCode(@Param("code") String code, @Param("idToExclude")Long idToExclude);

}
