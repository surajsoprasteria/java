/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Copyright Connection (WCC).
 *
 *
 * @author Fincons
 *
 */
package org.wipo.connect.shared.persistence.dao;

import java.util.List;

import org.wipo.connect.shared.exchange.dto.impl.DerivedWork;
import org.wipo.connect.shared.persistence.Dao;


/**
 * The Interface DerivedWorkDAO.
 */
public interface DerivedWorkDAO extends Dao<DerivedWork> {
	@Override
	int delete(Long id);

	@Override
	DerivedWork find(Long id);

	/**
	 * Find by work.
	 *
	 * @param idWork the id work
	 * @return the list
	 */
	List<DerivedWork> findByParent(Long idWork);


	List<DerivedWork> findByChild(Long idWork);

	int insert(DerivedWork dto);

	int update(DerivedWork dto);

	List<DerivedWork> findAllChildrenChain(Long parentId);

	List<DerivedWork> findParentWorkByIdChildWork(Long idWork);

	int deleteByParent(Long fkParentWork);

	boolean checkWorkIsComponentByMainId(String mainId);

	boolean checkWorkIsComponentByIdWork(Long idWork);
}
