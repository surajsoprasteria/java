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
package org.wipo.connect.common.vo;

import org.apache.commons.lang3.tuple.Pair;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PairVO<L, R> extends Pair<L, R> {

	private static final long serialVersionUID = -7150748123837907332L;

	private L left;
	private R right;


	public PairVO(L left, R right) {
		super();
		this.left = left;
		this.right = right;
	}

	@JsonCreator
	protected PairVO(@JsonProperty(value = "left", required = true)L left,
			@JsonProperty(value = "right", required = true)R right,
			@JsonProperty(value = "value", required = true) R value) {
		super();
		this.left = left;
		this.right = right;
	}

	@Override
	public R setValue(R value) {
		right = value;
		return right;
	}

	@Override
	public L getLeft() {
		return left;
	}

	@Override
	public R getRight() {
		return right;
	}

}
