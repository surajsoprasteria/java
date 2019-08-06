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
import org.wipo.connect.shared.exchange.dto.impl.Cmo;
import org.wipo.connect.shared.exchange.dto.impl.ReferenceFlat;

/**
 * The Interface CmoMapper.
 *
 * @author fumagalli
 */
public interface CmoMapper extends Mapper<Cmo> {

	/**
	 * Find by affiliated name.
	 *
	 * @param idName
	 *            the id name
	 * @return the list
	 */
	List<Cmo> findByAffiliatedName(@Param("idName") Long idName);

	/**
	 * Find by code.
	 *
	 * @param code
	 *            the code
	 * @return the cmo
	 */
	Cmo findByCode(@Param("code") String code);

	/**
	 * Find by Acronym.
	 *
	 * @param acronym
	 *            the acronym
	 * @return the cmo
	 */
	Cmo findByAcronym(@Param("acronym") String acronym);

	/**
	 * Find data source.
	 *
	 * @return the list
	 */
	List<Cmo> findDataSource();

	/**
	 * Find cmo.
	 *
	 * @param searchMap the search map
	 * @return the list
	 */
	List<Cmo> findCmo(Map<String, Object> searchMap);
	
	/**
	 * Find cmo type.
	 *
	 * @return the list
	 */
	List<ReferenceFlat> findCmoType();
	
	
	/**
	 * Exsist cmo code.
	 *
	 * @param code the code
	 * @param idToExclude the id to exclude
	 * @return the boolean
	 */
	Boolean exsistCmoCode(@Param("code") String code, @Param("idToExclude")Long idToExclude);
}
