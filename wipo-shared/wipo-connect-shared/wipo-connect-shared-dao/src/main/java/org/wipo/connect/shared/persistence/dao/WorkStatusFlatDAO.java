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

import org.wipo.connect.shared.exchange.dto.impl.WorkStatusFlat;
import org.wipo.connect.shared.exchange.enumerator.WorkStatusEnum;


/**
 * The WorkStatusFlatDAO interface provides methods that manipulate the data
 * from the database.
 *
 * @author fumagalli
 *
 */
public interface WorkStatusFlatDAO {
	/**
	 * Find status item of work using id parameter.
	 *
	 * @param id
	 *            the id status
	 * @return the status
	 */
	WorkStatusFlat find(Long id);

	/**
	 * Find all status of work.
	 *
	 * @return the list
	 */
	List<WorkStatusFlat> findAll();

	/**
	 * Find status of work using code.
	 *
	 * @param code
	 *            the code
	 * @return the status
	 */
	WorkStatusFlat findByCode(WorkStatusEnum code);
}
