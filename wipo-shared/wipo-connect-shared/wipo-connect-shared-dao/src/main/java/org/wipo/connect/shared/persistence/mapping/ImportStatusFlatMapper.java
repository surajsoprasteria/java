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
import org.wipo.connect.shared.exchange.dto.impl.ImportStatusFlat;
import org.wipo.connect.shared.exchange.enumerator.ImportStatusEnum;


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
