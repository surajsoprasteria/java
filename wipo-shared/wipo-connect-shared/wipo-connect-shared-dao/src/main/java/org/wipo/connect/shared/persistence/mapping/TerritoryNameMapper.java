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
import org.wipo.connect.shared.exchange.dto.impl.TerritoryName;


/**
 * The Interface TerritoryNameMapper.
 *
 * @author
 */
public interface TerritoryNameMapper extends Mapper<TerritoryName> {


	List<TerritoryName> findAll();


	TerritoryName selectByPrimaryKey(@Param("id") Long id);

	
	List<TerritoryName> findTerritoryNamesByCode (@Param("code") String code);
	
	
	/**
	 * Exsist code.
	 *
	 * @param code the code
	 * @param idToExclude the id to exclude
	 * @return the boolean
	 */
	Boolean exsistTisaCode(@Param("code") String code, @Param("idToExclude")Long idToExclude);
	
}
