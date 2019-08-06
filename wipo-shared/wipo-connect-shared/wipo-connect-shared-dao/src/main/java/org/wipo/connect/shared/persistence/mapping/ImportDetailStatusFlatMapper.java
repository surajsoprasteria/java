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
import org.wipo.connect.shared.exchange.dto.impl.ImportDetailStatusFlat;
import org.wipo.connect.shared.exchange.enumerator.ImportDetailStatusEnum;


/**
 * The Interface InterestedPartyStatusFlatMapper.
 */
public interface ImportDetailStatusFlatMapper extends Mapper<ImportDetailStatusFlat> {


	public List<ImportDetailStatusFlat> findAll();

	/**
	 * Find by code.
	 *
	 * @param code the code
	 * @return the import detail status flat
	 */
	public ImportDetailStatusFlat findByCode( @Param(value = "code") ImportDetailStatusEnum code);

}
