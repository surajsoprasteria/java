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

import org.wipo.connect.shared.exchange.dto.impl.WorkDate;


/**
 * The Interface WorkDateMapper.
 *
 */
public interface WorkDateMapper extends Mapper<WorkDate> {

	/**
	 * Find by work.
	 *
	 * @param idWork
	 *            the id work
	 * @return the list
	 */
	List<WorkDate> findByWork(Long idWork);

	/**
	 * Update.
	 *
	 * @param wd the wd
	 * @return the int
	 */
	int update(WorkDate wd);

}
