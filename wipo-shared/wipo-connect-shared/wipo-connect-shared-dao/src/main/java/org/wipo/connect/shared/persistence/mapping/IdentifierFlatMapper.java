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
import org.wipo.connect.shared.exchange.dto.impl.IdentifierFlat;
import org.wipo.connect.shared.exchange.enumerator.IdentifierTypeEnum;


/**
 * The Interface IdentifierFlatMapper.
 */
public interface IdentifierFlatMapper extends Mapper<IdentifierFlat> {

	/**
	 * Select by primary key.
	 *
	 * @param idIdentifier the id
	 * @return the identifier flat
	 */
	@Override
	IdentifierFlat selectByPrimaryKey(@Param(value = "idIdentifier") Long idIdentifier);

	/**
	 * Find by code.
	 *
	 * @param code the code
	 * @return the identifier flat
	 */
	IdentifierFlat findByCode(@Param(value = "code") IdentifierTypeEnum code);

	/**
	 * Find all.
	 *
	 * @return the identifier flat list
	 */
	@Override
	List<IdentifierFlat> findAll();

	/**
	 * Delete  identifier.
	 *
	 * @param idIdentifier the id identifier
	 * @return the int
	 */
	int deleteIdentifier(Long idIdentifier);

	/**
	 * Exsist identifier code.
	 *
	 * @param code the code
	 * @param idToExclude the id to exclude
	 * @return the boolean
	 */
	Boolean exsistIdentifierCode(@Param("code") String code, @Param("idToExclude")Long idToExclude);

	/**
	 * Checks if is identifier  used in any entity.
	 *
	 * @param fkIdentifier the fk identifier
	 * @return the boolean
	 */
	Boolean isIdentifierUsedInAnyEntity(Long fkIdentifier);
	
	
	int insertIdentifierCreationClass(@Param("fkIdentifier") Long fkIdentifier, @Param("fkCreationClass")Long fkCreationClass);
}
