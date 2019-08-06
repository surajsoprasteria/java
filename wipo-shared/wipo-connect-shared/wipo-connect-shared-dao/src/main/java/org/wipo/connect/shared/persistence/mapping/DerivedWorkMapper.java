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
import org.wipo.connect.shared.exchange.dto.impl.DerivedWork;


public interface DerivedWorkMapper extends Mapper<DerivedWork> {

	List<DerivedWork> findByParent(@Param("idWork") Long idWork);

	List<DerivedWork> findByChild(@Param("idWork") Long idWork);

	List<DerivedWork> findParentWorkByIdChildWork(@Param("idWork") Long idWork);

	int deleteByParent(@Param("fkParentWork")Long fkParentWork);

	boolean checkWorkIsComponentByMainId(@Param("mainId") String mainId);

	boolean checkWorkIsComponentByIdWork(@Param("idWork") Long idWork);

}
