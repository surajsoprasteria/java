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

package org.wipo.connect.shared.identifierprocessor.temp;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.wipo.connect.shared.identifierprocessor.entity.ImportStatusFlat;





/**
 * The Interface ImportStatusFlatMapper.
 */
public interface ImportStatusFlatMapper extends Mapper<ImportStatusFlat> {


	public List<ImportStatusFlat> findAll();

	/**
	 * Find by code.
	 *
	 * @param code the code
	 * @return the import status flat
	 */
	public ImportStatusFlat findByCode( @Param(value = "code") ImportStatusEnum code);

}
