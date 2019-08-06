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
package org.wipo.connect.common.index.doc;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.solr.core.mapping.Score;

/**
 * The Class BaseSolrDoc.
 */
public class BaseSolrDoc implements Serializable {

    private static final long serialVersionUID = 4912564170918878701L;

    public static final String SEP = "||";

    @Score
    protected BigDecimal score;

    @Override
    public String toString() {
	return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }

    public BigDecimal getScore() {
	return score;
    }

    public void setScore(BigDecimal score) {
	this.score = score;
    }

}
