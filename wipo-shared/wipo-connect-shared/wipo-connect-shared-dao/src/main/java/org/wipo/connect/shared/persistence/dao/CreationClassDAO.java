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

import org.wipo.connect.shared.exchange.dto.impl.CreationClassFlat;
import org.wipo.connect.shared.persistence.Dao;



/**
 * The CreationClassDAO interface provides methods that manipulate the data from the
 * database.
 *
 * @author fumagalli
 *
 */
public interface CreationClassDAO extends Dao<CreationClassFlat> {

	
	
	CreationClassFlat findCreationClassById(Long id);
	
	int insertCreationClass(CreationClassFlat creationClass);
	
	void updateCreationClass(CreationClassFlat creationClass);
	
	Boolean exsistCode(String code, Long idToExclude);
	
	List<CreationClassFlat> findAllCreationClass();

	CreationClassFlat findCreationClassByCode(String code);



}
