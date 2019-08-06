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


import org.apache.ibatis.annotations.Param;
import org.wipo.connect.shared.exchange.dto.impl.Translation;


/**
 * The Interface ReferenceFlatMapper.
 */
public interface TranslationFlatMapper extends Mapper<Translation> {

	/**
	 * Select by primary key.
	 *
	 * @param idReference the id reference
	 * @return the reference flat
	 */
	Translation selectByPrimaryKey(@Param("idTranslation") Long idTranslation);
	
	int insert(Translation label);
	
	int updateByPrimaryKey(Translation label);
	
}
