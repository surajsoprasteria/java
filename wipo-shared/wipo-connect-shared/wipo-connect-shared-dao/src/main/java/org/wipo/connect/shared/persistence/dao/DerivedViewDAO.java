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

import org.wipo.connect.shared.exchange.dto.impl.DerivedView;
import org.wipo.connect.shared.exchange.dto.impl.DerivedViewName;
import org.wipo.connect.shared.exchange.dto.impl.DerivedViewNameShare;


/**
 * The DerivedViewDAO interface provides methods that manipulate the data from
 * the database.
 *
 * @author fumagalli
 *
 */
public interface DerivedViewDAO {
	/**
	 * Delete derive-view.
	 *
	 * @param idDerivedView
	 *            the id derive-view
	 */
	void delete(Long idDerivedView);

	/**
	 * Delete derive-view.name.
	 *
	 * @param idDerivedView
	 *            the id derive-view
	 */
	void deleteDerivedViewNameByDerivedView(Long idDerivedView);

	/**
	 * Delete derive-view-name.
	 *
	 * @param id
	 *            the id
	 */
	void deleteDerivedViewNameById(Long id);

	/**
	 * Delete derive-view-name-share.
	 *
	 * @param idDerivedViewName
	 *            the derive-view-name
	 */
	void deleteDerivedViewNameShareByDerivedViewName(Long idDerivedViewName);

	/**
	 * Find derived-view items using id work parameter.
	 *
	 * @param idWork
	 *            the id work
	 * @return the list
	 */
	List<DerivedView> findByWork(Long idWork);

	/**
	 * Find derived-view-name items using id derived-view parameter.
	 *
	 * @param idDerivedView
	 *            the id derive-view
	 * @return the list
	 */
	List<DerivedViewName> findDerivedViewNameByDerivedView(Long idDerivedView);

	/**
	 * Insert derive-view.
	 *
	 * @param dto
	 *            the derive-view
	 * @return the int
	 */

	int insert(DerivedView dto);

	/**
	 * Insert derive-view-name.
	 *
	 * @param dto
	 *            the derive-view-name
	 * @return the int
	 */
	int insertDerivedViewName(DerivedViewName dto);

	/**
	 * Insert derive-view-name-share.
	 *
	 * @param dto
	 *            the derive-view-name-share
	 * @return the int
	 */
	int insertDerivedViewNameShare(DerivedViewNameShare dto);

	/**
	 * Insert derive-view-territory.
	 *
	 * @param fkDerivedView
	 *            the fk derived view
	 * @param fkTerritory
	 *            the fk territory
	 * @return the int
	 */
	int insertDerivedViewTerritory(Long fkDerivedView, Long fkTerritory);

	/**
	 * Update derive-view.
	 *
	 * @param dto
	 *            the derive-view
	 */
	void update(DerivedView dto);

	/**
	 * Update derive-view-name.
	 *
	 * @param dto
	 *            the derive-view-name
	 */
	void updateDerivedViewName(DerivedViewName dto);

	/**
	 * Update derive-view-name-share.
	 *
	 * @param dto
	 *            the derive-view-name-share
	 */
	void updateDerivedViewNameShare(DerivedViewNameShare dto);

	/**
	 * Checks if is exists by name id.
	 *
	 * @param nameId the name id
	 * @return true, if is exists by name id
	 */
	boolean isExistsByNameId(Long nameId);


	/**
	 * Update Name Reference Derived View Name.
	 *
	 * @param newIdName the new id name
	 * @param oldIdName the old id name
	 * @return the int
	 */
	int updateNameRefDerivedViewName(Long newIdName, Long oldIdName);

	List<Long> findIdNameInWorkByNameMainId(String ipin);

}
