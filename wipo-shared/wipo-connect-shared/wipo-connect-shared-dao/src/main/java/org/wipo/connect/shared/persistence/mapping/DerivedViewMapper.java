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
import org.wipo.connect.shared.exchange.dto.impl.DerivedView;
import org.wipo.connect.shared.exchange.dto.impl.DerivedViewName;
import org.wipo.connect.shared.exchange.dto.impl.DerivedViewNameShare;


/**
 * The Interface DerivedViewMapper.
 */
public interface DerivedViewMapper extends Mapper<DerivedView> {

	/**
	 * Delete derived view name by derived view.
	 *
	 * @param idDerivedView
	 *            the id derived view
	 */
	void deleteDerivedViewNameByDerivedView(Long idDerivedView);

	/**
	 * Delete derived view name by id.
	 *
	 * @param idDerivedViewName
	 *            the id derived view name
	 */
	void deleteDerivedViewNameById(Long idDerivedViewName);

	/**
	 * Delete derived view name share by derived view name.
	 *
	 * @param idDerivedViewName
	 *            the id derived view name
	 */
	void deleteDerivedViewNameShareByDerivedViewName(Long idDerivedViewName);



	/**
	 * Find by work.
	 *
	 * @param idWork
	 *            the id work
	 * @return the list
	 */
	List<DerivedView> findByWork(@Param("idWork") Long idWork);

	/**
	 * Find derived view name by derived view.
	 *
	 * @param idDerivedView
	 *            the id derived view
	 * @return the list
	 */
	List<DerivedViewName> findDerivedViewNameByDerivedView(@Param("idDerivedView") Long idDerivedView);

	/**
	 * Insert derived view name.
	 *
	 * @param dto
	 *            the dto
	 * @return the int
	 */
	int insertDerivedViewName(DerivedViewName dto);

	/**
	 * Insert derived view name share.
	 *
	 * @param dto
	 *            the dto
	 * @return the int
	 */
	int insertDerivedViewNameShare(DerivedViewNameShare dto);

	/**
	 * Insert derived view territory.
	 *
	 * @param fkDerivedView
	 *            the fk derived view
	 * @param fkTerritory
	 *            the fk territory
	 * @return the int
	 */
	int insertDerivedViewTerritory(@Param("fkDerivedView") Long fkDerivedView, @Param("fkTerritory") Long fkTerritory);

	/**
	 * Update derived view name.
	 *
	 * @param dto
	 *            the dto
	 */
	void updateDerivedViewName(DerivedViewName dto);

	/**
	 * Update derived view name share.
	 *
	 * @param dto
	 *            the dto
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
	 * Find id name in work by name main id.
	 *
	 * @param ipin the ipin
	 * @return the list
	 */
	List<Long> findIdNameInWorkByNameMainId(String mainId);
	
	/**
	 * Update name ref derived view name.
	 *
	 * @param newIdName the new id name
	 * @param oldIdName the old id name
	 * @return the int
	 */
	int updateNameRefDerivedViewName(@Param("newIdName") Long newIdName, @Param("oldIdName") Long oldIdName);

}
