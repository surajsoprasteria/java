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

package org.wipo.connect.shared.exchange.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.shared.exchange.dto.impl.RightTypeFlat;


/**
 * The Class RightTypeVO.
 */
public class RightTypeVO implements Serializable {

	private static final long serialVersionUID = 6374954823681832966L;

	private RightTypeFlat rightType;
	
	private String linkedIpiRightTypeCode;

	
	/**
	 * Gets the right type.
	 *
	 * @return the right type
	 */
	public RightTypeFlat getRightType() {
		return rightType;
	}



	/**
	 * Sets the right type.
	 *
	 * @param rightType the new right type
	 */
	public void setRightType(RightTypeFlat rightType) {
		this.rightType = rightType;
	}



	/**
	 * Gets the linked ipi right type code.
	 *
	 * @return the linked ipi right type code
	 */
	public String getLinkedIpiRightTypeCode() {
		return linkedIpiRightTypeCode;
	}



	/**
	 * Sets the linked ipi right type code.
	 *
	 * @param linkedIpiRightTypeCode the new linked ipi right type code
	 */
	public void setLinkedIpiRightTypeCode(String linkedIpiRightTypeCode) {
		this.linkedIpiRightTypeCode = linkedIpiRightTypeCode;
	}




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
