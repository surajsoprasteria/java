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
import org.wipo.connect.shared.exchange.dto.impl.ReferenceTypeFlat;


/**
 * The Interface ReferenceTypeFlatMapper.
 */
public interface ReferenceTypeFlatMapper extends Mapper<ReferenceTypeFlat> {

	/**
	 * Select by primary key.
	 *
	 * @param idReference the id reference
	 * @return the reference flat
	 */
	ReferenceTypeFlat selectByPrimaryKey(@Param("idReferenceType") Long idReferenceType);

	/**
	 * Find by code.
	 *
	 * @param code
	 *            the code
	 * @return the list
	 */
	ReferenceTypeFlat findByCode(@Param("code") String code);

	List<ReferenceTypeFlat> findAll();
	
	
	/**
	 * Exsist reference type code.
	 *
	 * @param code the code
	 * @param idToExclude the id to exclude
	 * @return the boolean
	 */
	Boolean exsistReferenceTypeCode(@Param("code") String code, @Param("idToExclude")Long idToExclude);
	
	
}
