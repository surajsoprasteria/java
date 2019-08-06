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
package org.wipo.connect.shared.exchange.utils;

import org.wipo.connect.shared.exchange.utils.IdentifierGenerator.WccIdType;

/**
 * The Interface Generator.
 */
public interface Generator {

	/**
	 * Generate wcc identifier.
	 *
	 * @param type the type
	 * @param id the id
	 * @return the string
	 */
	String generateWccIdentifier(WccIdType type, Long id);

}