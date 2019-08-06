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

import org.wipo.connect.shared.exchange.dto.impl.RightSplit;



/**
 * The Class RightSplitVO.
 */
public class RightSplitVO implements Serializable {

	private static final long serialVersionUID = -7277087749491693L;

	private RightSplit rightSplit;

    private Boolean isToCheckRightType;

	/**
	 * Gets the right split.
	 *
	 * @return the right split
	 */
	public RightSplit getRightSplit() {
		return rightSplit;
	}

	/**
	 * Sets the right split.
	 *
	 * @param rightSplit the new right split
	 */
	public void setRightSplit(RightSplit rightSplit) {
		this.rightSplit = rightSplit;
	}

	/**
	 * Gets the checks if is to check right type.
	 *
	 * @return the checks if is to check right type
	 */
	public Boolean getIsToCheckRightType() {
		return isToCheckRightType;
	}

	/**
	 * Sets the checks if is to check right type.
	 *
	 * @param isToCheckRightType the new checks if is to check right type
	 */
	public void setIsToCheckRightType(Boolean isToCheckRightType) {
		this.isToCheckRightType = isToCheckRightType;
	}

	
}
