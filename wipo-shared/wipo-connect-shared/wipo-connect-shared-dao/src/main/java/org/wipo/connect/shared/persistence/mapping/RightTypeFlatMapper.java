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
import org.wipo.connect.shared.exchange.dto.impl.RightTypeFlat;



/**
 * The Interface RightTypeFlatMapper.
 */
public interface RightTypeFlatMapper extends Mapper<RightTypeFlat> {

	/**
	 * Select by primary key.
	 *
	 * @param idRightType the id right type
	 * @return the RightTypeFlat
	 */
	@Override
	RightTypeFlat selectByPrimaryKey(@Param("idRightType") Long idRightType);

	/**
	 * Find by code.
	 *
	 * @param code the code
	 * @return the RightTypeFlat
	 */
	RightTypeFlat findByCode(@Param("code") String code);

	/**
	 * Find all.
	 *
	 * @return the RightTypeFlat list
	 */
	@Override
	List<RightTypeFlat> findAll();
	
	
	/**
	 * Exsist Right Type code.
	 *
	 * @param code the code
	 * @param idToExclude the id to exclude
	 * @return the boolean
	 */
	Boolean exsistRightTypeCode(@Param("code") String code, @Param("idToExclude")Long idToExclude);
	
	
	int insertRightTypeCreationClass(@Param("fkRightType") Long fkIpiRightType, @Param("fkCreationClass")Long fkCreationClass);

	/**
	 * 
	 * @param ccCodeList
	 * @return
	 */
	List<RightTypeFlat> findRightTypeListByCC(@Param("ccCodeList")List<String> ccCodeList);

}
