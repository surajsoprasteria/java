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



package org.wipo.connect.shared.persistence.dao;


import java.util.List;

import org.wipo.connect.shared.exchange.dto.impl.IpiRoleFlat;
import org.wipo.connect.shared.exchange.dto.impl.RoleFlat;



/**
 * The RoleDAO interface provides methods that manipulate the data from the
 * database.
 *
 * @author fumagalli
 *
 */
public interface RoleDAO {	
	/**
	 * Find all role.
	 *
	 * @return the list
	 */
	List<RoleFlat> findAllRole();

	/**
	 * * Find role item using code parameter.
	 *
	 * @param code
	 *            the code
	 * @return the role
	 */
	RoleFlat findRoleByCode(String code);

	/**
	 * Find role item using id parameter.
	 *
	 * @param id
	 *            the id
	 * @return the role
	 */
	RoleFlat findRoleById(Long id);
	
	
	/**
	 * Find ipi role item using id parameter.
	 *
	 * @param id
	 *            the id
	 * @return the role
	 */
	IpiRoleFlat findIpiRoleById(Long id);
	
	
	/**
	 * Insert role.
	 *
	 * @param role the role
	 * @return the int
	 */
	int insertRole(RoleFlat role);


	/**
	 * Update role.
	 *
	 * @param role the role
	 */
	void updateRole(RoleFlat role);
	
	
	
	/**
	 * Insert ipi role.
	 *
	 * @param ipi the ipi
	 * @return the int
	 */
	int insertIpiRole(IpiRoleFlat ipi);


	/**
	 * Update ipi role.
	 *
	 * @param ipi the ipi
	 */
	void updateIpiRole(IpiRoleFlat ipi);
	
	
	Boolean exsistWorkCode(String code, Long idToExclude);
	
	Boolean exsistIpiCode(String code, Long idToExclude);
	
	
	int insertIpiCreationClass(Long fkIpiRole, Long fkCreationClass);


}
