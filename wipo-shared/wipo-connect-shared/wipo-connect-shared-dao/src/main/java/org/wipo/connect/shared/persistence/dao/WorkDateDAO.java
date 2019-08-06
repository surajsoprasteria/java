/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Copyright Connection (WCC).
 *
 *
 * @author Fincons
 *
 */
package org.wipo.connect.shared.persistence.dao;

import java.util.List;

import org.wipo.connect.shared.exchange.dto.impl.WorkDate;
import org.wipo.connect.shared.persistence.Dao;

/**
 * The Interface WorkDateDAO.
 */
public interface WorkDateDAO extends Dao<WorkDate> {

	@Override
	int delete(Long id);

	@Override
	WorkDate find(Long id);

	/**
	 * Find by work.
	 *
	 * @param idWork the id work
	 * @return the list
	 */
	List<WorkDate> findByWork(Long idWork);

	/**
	 * Insert.
	 *
	 * @param dto the dto
	 * @return the int
	 */
	int insert(WorkDate dto);

	/**
	 * Update.
	 *
	 * @param wp the wp
	 * @return the int
	 */
	int update(WorkDate wp);

}