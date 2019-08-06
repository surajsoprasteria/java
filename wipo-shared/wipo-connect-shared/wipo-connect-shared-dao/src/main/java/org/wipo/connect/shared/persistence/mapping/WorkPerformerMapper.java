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

import org.wipo.connect.shared.exchange.dto.impl.WorkPerformer;


/**
 * The Interface WorkPerformerMapper.
 *
 * @author pasquale.minervini
 */
public interface WorkPerformerMapper extends Mapper<WorkPerformer> {

	/**
	 * Find by work.
	 *
	 * @param idWork
	 *            the id work
	 * @return the list
	 */
	List<WorkPerformer> findByWork(Long idWork);

	/**
	 * Update.
	 *
	 * @param wp the wp
	 * @return the int
	 */
	int update(WorkPerformer wp);

}
