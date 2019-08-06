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
import org.wipo.connect.shared.exchange.dto.impl.RoleFlat;


/**
 * The Interface RoleFlatMapper.
 */
public interface RoleFlatMapper extends Mapper<RoleFlat> {

	/**
	 * Select by primary key.
	 *
	 * @param idRole the id role
	 * @return the RoleFlat
	 */
	RoleFlat selectByPrimaryKey(@Param("idRole") Long idRole);

	/**
	 * Find by code.
	 *
	 * @param code
	 *            the code
	 * @return the role flat
	 */
	public RoleFlat findByCode(@Param("code") String code);

	/**
	 * Find all.
	 *
	 * @return RoleFlat list
	 */
	List<RoleFlat> findAll();
	
	
	/**
	 * Exsist work code.
	 *
	 * @param code the code
	 * @param idToExclude the id to exclude
	 * @return the boolean
	 */
	Boolean exsistWorkCode(@Param("code") String code, @Param("idToExclude")Long idToExclude);

	/**
	 * Find role filtered by cc and ipi_role
	 * @param cc
	 * @return
	 */
	List<RoleFlat> findWorkRoleListByCC(@Param("cc")String cc);

}
