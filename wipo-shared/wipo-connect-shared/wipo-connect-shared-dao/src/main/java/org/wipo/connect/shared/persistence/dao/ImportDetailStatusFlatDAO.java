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

import org.wipo.connect.shared.exchange.dto.impl.ImportDetailStatusFlat;
import org.wipo.connect.shared.exchange.enumerator.ImportDetailStatusEnum;
import org.wipo.connect.shared.persistence.Dao;


/**
 * The ImportDetailStatusFlatDAO interface provides methods that manipulate the
 * data from the database.
 *
 * @author Minervini
 *
 */
public interface ImportDetailStatusFlatDAO extends Dao<ImportDetailStatusFlat> {

	/**
	 * Find All.
	 *
	 * @return the list
	 */
	public List<ImportDetailStatusFlat> findAll();

	/**
	 * Find by code.
	 *
	 * @param code            the code
	 * @return the list
	 */
	public ImportDetailStatusFlat findByCode(ImportDetailStatusEnum code);

}
