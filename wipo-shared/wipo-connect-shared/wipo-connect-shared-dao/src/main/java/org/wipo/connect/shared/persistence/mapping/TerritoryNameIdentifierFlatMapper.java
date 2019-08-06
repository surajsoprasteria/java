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
import org.wipo.connect.shared.exchange.dto.impl.TerritoryNameIdentifierFlat;
import org.wipo.connect.shared.exchange.enumerator.IdentifierTypeEnum;


/**
 * The Interface TerritoryNameIdentifierFlatMapper.
 */
public interface TerritoryNameIdentifierFlatMapper {

	/**
	 * Find by tn.
	 *
	 * @param idTerritoryName
	 *            the id interested party
	 * @param code
	 *            the code
	 * @return the list
	 */
	public List<TerritoryNameIdentifierFlat> findByIP(@Param("idTerritoryName") Long idTerritoryName, @Param("code") IdentifierTypeEnum code);

	/**
	 * Insert.
	 *
	 * @param idTerritoryName
	 *            the id interested party
	 * @param code
	 *            the code
	 * @param value
	 *            the value
	 * @return the int
	 */
	public int insert(@Param("idTerritoryName") Long idTerritoryName, @Param("code") IdentifierTypeEnum code, @Param("value") String value);
	
	public int update(@Param("idTerritoryName") Long idTerritoryName, @Param("value") String value);

	/**
	 * Select by primary key.
	 *
	 * @param id
	 *            the id
	 * @return the interested party identifier flat
	 */
	public TerritoryNameIdentifierFlat selectByPrimaryKey(Long id);
	
	
	public TerritoryNameIdentifierFlat findIdentifierFromTerritoryCode(@Param("territoryCode") String territoryCode, @Param("identifierCode") IdentifierTypeEnum identifierCode);

}
