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

import org.wipo.connect.shared.exchange.dto.impl.Territory;
import org.wipo.connect.shared.exchange.dto.impl.TerritoryName;
import org.wipo.connect.shared.exchange.dto.impl.TerritoryNameIdentifierFlat;
import org.wipo.connect.shared.exchange.enumerator.TerritoryOrderTypeEnum;
import org.wipo.connect.shared.persistence.Dao;



/**
 * The TerritoryDAO interface provides methods that manipulate the data from the
 * database.
 *
 * @author fumagalli
 *
 */
public interface TerritoryDAO extends Dao<Territory> {

	/**
	 * Find all territory with id value.
	 *
	 * @return the list
	 */
	List<Territory> findAllTerritoryWithIdValue();

	/**
	 * Find countries from territory.
	 *
	 * @return the list
	 */
	List<Territory> findCountriesFromTerritory();
	
	
	List<Territory> findCountriesFromTerritoryWithWorld();

	/**
	 * Find all.
	 *
	 * @param territoryOrderType the territory order type
	 * @return the list
	 */
	List<Territory> findAll(TerritoryOrderTypeEnum territoryOrderType);
	
	List<Territory> findAllTerritory();
	
	
	List<TerritoryName> findTerritoryNamesByCode(String code);
	
	
	TerritoryName findTerritoryNameById(Long id);
	
	
	Territory findTerritoryById(Long id);
	
	
	int insertTerritory(Territory territory);
	
	void updateTerritory(Territory territory);
	
	int insertTerritoryName(TerritoryName territoryName);
	
	void updateTerritoryName(TerritoryName territoryName);
	
	
	Boolean exsistTisnCode(String code, Long idToExclude);
	
	Boolean exsistTisaCode(String code, Long idToExclude);
	
	TerritoryNameIdentifierFlat findIdentifierFromTerritoryCode(String code);

}
