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
import org.wipo.connect.shared.exchange.dto.impl.TerritoryIdentifierFlat;
import org.wipo.connect.shared.exchange.enumerator.IdentifierTypeEnum;


/**
 * The Interface TerritoryIdentifierFlatMapper.
 */
public interface TerritoryIdentifierFlatMapper {

	/**
	 * Find by ip.
	 *
	 * @param idTerritory
	 *            the id interested party
	 * @param code
	 *            the code
	 * @return the list
	 */
	public List<TerritoryIdentifierFlat> findByIP(@Param("idTerritory") Long idTerritory, @Param("code") IdentifierTypeEnum code);

	/**
	 * Insert.
	 *
	 * @param idTerritory
	 *            the id interested party
	 * @param code
	 *            the code
	 * @param value
	 *            the value
	 * @return the int
	 */
	public int insert(@Param("idTerritory") Long idTerritory, @Param("code") IdentifierTypeEnum code, @Param("value") String value);
	
	public int update(@Param("idTerritory") Long idTerritory, @Param("value") String value);

	/**
	 * Select by primary key.
	 *
	 * @param id
	 *            the id
	 * @return the interested party identifier flat
	 */
	public TerritoryIdentifierFlat selectByPrimaryKey(Long id);

}
