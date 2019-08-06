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
import org.wipo.connect.shared.exchange.dto.impl.IpiRoleFlat;


/**
 * The Interface IpiRoleFlatMapper.
 */
public interface IpiRoleFlatMapper extends Mapper<IpiRoleFlat> {

	/**
	 * Select by primary key.
	 *
	 * @param idIpiRole the id ipi role
	 * @return the RoleFlat
	 */
	IpiRoleFlat selectByPrimaryKey(@Param("idIpiRole") Long idIpiRole);

	/**
	 * Find by code.
	 *
	 * @param code
	 *            the code
	 * @return the role flat
	 */
	IpiRoleFlat findByCode(@Param("code") String code);

	/**
	 * Find all.
	 *
	 * @return RoleFlat list
	 */
	List<IpiRoleFlat> findAll();
	
	
	/**
	 * Exsist ipi code.
	 *
	 * @param code the code
	 * @param idToExclude the id to exclude
	 * @return the boolean
	 */
	Boolean exsistIpiCode(@Param("code") String code, @Param("idToExclude")Long idToExclude);
	
	

	int insertIpiCreationClass(@Param("fkIpiRole") Long fkIpiRole, @Param("fkCreationClass")Long fkCreationClass);
}
