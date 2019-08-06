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
import org.wipo.connect.shared.exchange.dto.impl.WorkStatusFlat;
import org.wipo.connect.shared.exchange.enumerator.WorkStatusEnum;


/**
 * The Interface WorkStatusFlatMapper.
 */
public interface WorkStatusFlatMapper extends Mapper<WorkStatusFlat> {

	/**
	 * Select by primary key.
	 *
	 * @param idWorkStatus the id work status
	 * @return the WorkStatusFlat
	 */
	WorkStatusFlat selectByPrimaryKey(@Param("idWorkStatus") Long idWorkStatus);

	/**
	 * Find by code.
	 *
	 * @param code
	 *            the code
	 * @return the work status flat
	 */
	public WorkStatusFlat findByCode(@Param("code") WorkStatusEnum code);

	/**
	 * Find all.
	 *
	 * @return the WorkStatusFlat list
	 */
	List<WorkStatusFlat> findAll();

}
