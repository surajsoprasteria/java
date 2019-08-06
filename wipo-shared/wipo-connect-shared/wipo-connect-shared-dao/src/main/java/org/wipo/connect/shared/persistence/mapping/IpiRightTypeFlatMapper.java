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
import org.wipo.connect.shared.exchange.dto.impl.IpiRightTypeFlat;


/**
 * The Interface IpiRightTypeFlatMapper.
 */
public interface IpiRightTypeFlatMapper extends Mapper<IpiRightTypeFlat> {

	/**
	 * Select by primary key.
	 *
	 * @param idIpiRightType the id ipi right type
	 * @return the IpiRightTypeFlat
	 */
	@Override
	IpiRightTypeFlat selectByPrimaryKey(@Param("idIpiRightType") Long idIpiRightType);

	/**
	 * Find by code.
	 *
	 * @param code the code
	 * @return the IpiRightTypeFlat
	 */
	IpiRightTypeFlat findByCode(@Param("code") String code);

	/**
	 * Find all.
	 *
	 * @return the IpiRightTypeFlat list
	 */
	@Override
	List<IpiRightTypeFlat> findAll();
	
	
	/**
	 * Exsist ipi code.
	 *
	 * @param code the code
	 * @param idToExclude the id to exclude
	 * @return the boolean
	 */
	Boolean exsistIpiRightTypeCode(@Param("code") String code, @Param("idToExclude")Long idToExclude);
	
	
	int insertIpiRightTypeCreationClass(@Param("fkIpiRightType") Long fkIpiRightType, @Param("fkCreationClass")Long fkCreationClass);
	
	
	List<IpiRightTypeFlat> findAllByCc(@Param("idCreationClass") Long idCreationClass);

}
