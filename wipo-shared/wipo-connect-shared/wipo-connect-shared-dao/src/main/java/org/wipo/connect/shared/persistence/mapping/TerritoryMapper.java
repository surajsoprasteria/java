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
import org.wipo.connect.shared.exchange.dto.impl.Territory;
import org.wipo.connect.shared.exchange.enumerator.TerritoryOrderTypeEnum;


/**
 * The Interface TerritoryMapper.
 *
 * @author minervini
 */
public interface TerritoryMapper extends Mapper<Territory> {

	/**
	 * Find all territory with id value.
	 *
	 * @return the list
	 */
	List<Territory> findAllTerritoryWithIdValue();

	/**
	 * Find territories By Country.
	 * 
	 * @return the list of Territory
	 */
	List<Territory> findCountriesFromTerritory();
	
	List<Territory> findCountriesFromTerritoryWithWorld();

	/**
	 * Find all.
	 *
	 * @param territoryOrderType the territory order type
	 * @return the list
	 */
	List<Territory> findAll(@Param(value = "territoryOrderType") TerritoryOrderTypeEnum territoryOrderType);
	
	
	/**
	 * Find all territory (for shared UI).
	 *
	 * @param territoryOrderType the territory order type
	 * @return the list
	 */
	List<Territory> findAllTerritory();

	/**
	 * Find territories all managed by cmo.
	 * 
	 * @return the list of Territory
	 */
	List<Territory> findAllTerritoryManaged();

	/**
	 * Select by primary key.
	 *
	 * @param id
	 *            the id
	 * @return the t
	 */
	@Override
	Territory selectByPrimaryKey(@Param("id") Long id);

	/**
	 * Find territory by parent.
	 *
	 * @param idParent
	 *            the id parent
	 * @return the list
	 */
	List<Territory> findTerritoryByParent(@Param("idParent") Long idParent);

	/**
	 * Find territory by parent code.
	 *
	 * @param parentCode
	 *            the parent code
	 * @return the list
	 */
	List<Territory> findTerritoryByParentTisa(@Param("parentCode") String parentCode);

	/**
	 * Find territory by tisa.
	 *
	 * @param code
	 *            the code
	 * @return the territory
	 */
	Territory findTerritoryByTisa(@Param("code") String code);

	/**
	 * Select territory child.
	 *
	 * @param fkTerritory the fk territory
	 * @return the list
	 */
	List<Long> selectTerritoryChild(Long fkTerritory);
	
	/**
	 * Find territory names by id.
	 *
	 * @param idTerritory the id territory
	 * @return the list
	 */
	List<Territory> findTerritoryNamesById (@Param("idTerritory") Long idTerritory);
	
	
	/**
	 * Exsist code.
	 *
	 * @param code the code
	 * @param idToExclude the id to exclude
	 * @return the boolean
	 */
	Boolean exsistTisnCode(@Param("code") String code, @Param("idToExclude")Long idToExclude);
	
}
