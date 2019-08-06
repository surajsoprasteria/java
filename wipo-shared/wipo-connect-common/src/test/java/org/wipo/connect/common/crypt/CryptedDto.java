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
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.wipo.connect.common.crypto.CryptedField;

public class CryptedDto implements Serializable{
	private static final long serialVersionUID = 7723975059463811023L;

	@CryptedField
	private Integer field1;

	@CryptedField
	private String field2;

	@CryptedField
	private List<String> field3;

	@CryptedField
	private Object field4;

	private String field5;

	@CryptedField
	private CryptedInnerDto field6;

	private CryptedInnerDto field7;

	public Integer getField1() {
		return field1;
	}

	public void setField1(Integer field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public List<String> getField3() {
		return field3;
	}

	public void setField3(List<String> field3) {
		this.field3 = field3;
	}

	public Object getField4() {
		return field4;
	}

	public void setField4(Object field4) {
		this.field4 = field4;
	}

	public String getField5() {
		return field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}

	public CryptedInnerDto getField6() {
		return field6;
	}

	public void setField6(CryptedInnerDto field6) {
		this.field6 = field6;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public CryptedInnerDto getField7() {
		return field7;
	}

	public void setField7(CryptedInnerDto field7) {
		this.field7 = field7;
	}


}
