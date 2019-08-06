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
import java.util.Map;

import org.wipo.connect.shared.exchange.dto.impl.Cmo;
import org.wipo.connect.shared.exchange.dto.impl.ReferenceFlat;
import org.wipo.connect.shared.persistence.Dao;




/**
 * The CmoDAO interface provides methods that manipulate the data from the
 * database.
 *
 * @author fumagalli
 *
 */
public interface CmoDAO extends Dao<Cmo> {

    /**
     * Find by affiliated name.
     *
     * @param idName
     *            the id name
     * @return the list
     */
    List<Cmo> findByAffiliatedName(Long idName);



    /**
     * Find by code.
     *
     * @param code
     *            the code
     * @return the cmo
     */
    Cmo findByCode(String code);


    /**
     * Find by acronym.
     *
     * @param acronym
     *            the acronym
     * @return the cmo
     */
	Cmo findByAcronym(String acronym);



	/**
	 * Find data source.
	 *
	 * @return the list
	 */
	List<Cmo> findDataSource();



	/**
	 * Find cmo.
	 *
	 * @param searchMap the search map
	 * @return the list
	 */
	List<Cmo> findCmo(Map<String, Object> searchMap);
	
	List<ReferenceFlat> findCmoType();
	
	/**
	 * Insert cmo.
	 *
	 * @param cmo the cmo
	 * @return the int
	 */
	int insertCmo(Cmo cmo);


	/**
	 * Update cmo.
	 *
	 * @param cmo the cmo
	 */
	void updateCmo(Cmo cmo);
	
	/**
	 * Exsist cmo code.
	 *
	 * @param code the code
	 * @param idToExclude the id to exclude
	 * @return the boolean
	 */
	Boolean exsistCmoCode(String code, Long idToExclude);
	

}
