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
import org.wipo.connect.shared.exchange.dto.impl.ReferenceFlat;


/**
 * The Interface ReferenceFlatMapper.
 */
public interface ReferenceFlatMapper extends Mapper<ReferenceFlat> {

	/**
	 * Select by primary key.
	 *
	 * @param idReference the id reference
	 * @return the reference flat
	 */
	ReferenceFlat selectByPrimaryKey(@Param("idReference") Long idReference);

	/**
	 * Find by code.
	 *
	 * @param code
	 *            the code
	 * @return the list
	 */
	List<ReferenceFlat> findByCode(@Param("code") String code);


	List<ReferenceFlat> findAll();
	
	/**
	 * Find all reference type.
	 *
	 * @return the list
	 */
	
	boolean existReferenceCode(@Param("code") String code, @Param("refCode") String refCode, @Param("idToExclude")Long idToExclude);
	
}
