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
import org.wipo.connect.shared.exchange.dto.impl.InterestedPartyIdentifierFlat;
import org.wipo.connect.shared.exchange.enumerator.IdentifierTypeEnum;


/**
 * The Interface InterestedPartyIdentifierFlatMapper.
 */
public interface InterestedPartyIdentifierFlatMapper {

	/**
	 * Find by ip.
	 *
	 * @param idInterestedParty
	 *            the id interested party
	 * @param code
	 *            the code
	 * @return the list
	 */
	public List<InterestedPartyIdentifierFlat> findByIP(@Param("idInterestedParty") Long idInterestedParty, @Param("code") IdentifierTypeEnum code);

	/**
	 * Insert.
	 *
	 * @param idInterestedParty
	 *            the id interested party
	 * @param code
	 *            the code
	 * @param value
	 *            the value
	 * @return the int
	 */
	public int insert(@Param("idInterestedParty") Long idInterestedParty, @Param("code") String code, @Param("value") String value);

	/**
	 * Select by primary key.
	 *
	 * @param id
	 *            the id
	 * @return the interested party identifier flat
	 */
	public InterestedPartyIdentifierFlat selectByPrimaryKey(Long id);

	int deleteIpIdentifierByIp(@Param("idInterestedParty") Long idInterestedParty);

	/**
	 * Find by value and type.
	 *
	 * @param value the value
	 * @param type the type
	 * @return the list
	 */
	List<InterestedPartyIdentifierFlat> findByValueAndType(@Param("value") String value, @Param("type") String type);

	boolean isIdentifierValueAlreadyPresent(@Param("code") String code,@Param("value") String value, @Param("idIp") Long idIp);
}
