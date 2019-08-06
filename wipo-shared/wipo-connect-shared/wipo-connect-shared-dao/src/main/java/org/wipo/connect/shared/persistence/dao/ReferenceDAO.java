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

import org.wipo.connect.shared.exchange.dto.impl.ReferenceFlat;
import org.wipo.connect.shared.exchange.dto.impl.ReferenceTypeFlat;



/**
 * The RightTypeDAO interface provides methods that manipulate the data from the
 * database.
 *
 *
 *
 */
public interface ReferenceDAO {	
		

	
	/**
	 * Find reference items using code parameter.
	 *
	 * @param code
	 *            the code
	 * @return the list
	 */
	List<ReferenceFlat> findReferenceByCode(String code);

	/**
	 * Find reference item using id parameter.
	 *
	 * @param id
	 *            the id
	 * @return the reference
	 */
	ReferenceFlat findReferenceById(Long id);

	/**
	 * Find all reference.
	 *
	 * @return the list
	 */
	List<ReferenceFlat> findAllReference();
	
	
	/**
	 * Find reference type by code.
	 *
	 * @param code the code
	 * @return the reference type flat
	 */
	ReferenceTypeFlat findReferenceTypeByCode(String code);
	
	/**
	 * Find reference type by id.
	 *
	 * @param code the code
	 * @return the reference type flat
	 */
	ReferenceTypeFlat findReferenceTypeById(Long id);
	
	
	/**
	 * Find all reference type.
	 *
	 * @return the list
	 */
	List<ReferenceTypeFlat> findAllReferenceType();
	
	
	/**
	 * Insert reference.
	 *
	 * @param reference the reference
	 * @return the int
	 */
	int insertReference(ReferenceFlat reference);


	/**
	 * Update reference.
	 *
	 * @param reference the reference
	 */
	void updateReference(ReferenceFlat reference);
	
	
	/**
	 * Insert reference type.
	 *
	 * @param reference the reference type
	 * @return the int
	 */
	int insertReferenceType(ReferenceTypeFlat referenceType);

	
	/**
	 * Update reference type.
	 *
	 * @param reference the reference type
	 */
	void updateReferenceType(ReferenceTypeFlat referenceType);
	
	
	/**
	 * Exist reference code.
	 *
	 * @param code the code
	 * @param refCode the ref code
	 * @return true, if successful
	 */
	boolean existReferenceCode(String code, String refCode, Long idToExclude);
	
	
	/**
	 * Exist reference type code.
	 *
	 * @param code the code
	 * @param refCode the ref code
	 * @return true, if successful
	 */
	Boolean exsistReferenceTypeCode(String code, Long idToExclude);
	


}
