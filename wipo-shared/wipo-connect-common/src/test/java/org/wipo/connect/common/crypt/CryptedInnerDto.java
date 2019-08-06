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
package org.wipo.connect.common.crypt;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.wipo.connect.common.crypto.CryptedField;

public class CryptedInnerDto implements Serializable{

	private static final long serialVersionUID = 1367734561488970461L;

	private String fieldA;

	@CryptedField
	private String fieldB;

	public String getFieldA() {
		return fieldA;
	}

	public void setFieldA(String field1) {
		this.fieldA = field1;
	}

	public String getFieldB() {
		return fieldB;
	}

	public void setFieldB(String field2) {
		this.fieldB = field2;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
