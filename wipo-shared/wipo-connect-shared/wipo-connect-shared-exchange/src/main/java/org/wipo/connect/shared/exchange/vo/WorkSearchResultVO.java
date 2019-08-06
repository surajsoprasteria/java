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
package org.wipo.connect.shared.exchange.vo;

import java.io.Serializable;
import java.util.List;

import org.wipo.connect.common.querypagination.PageResult;
import org.wipo.connect.shared.exchange.dto.impl.Work;

/**
 * The Class WorkSearchResultVO.
 */
public class WorkSearchResultVO extends PageResult<Work> implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3884908038097689883L;

    public WorkSearchResultVO() {
	super();
    }

    public WorkSearchResultVO(List<Work> data, Integer draw, Integer recordsFiltered, Integer recordsTotal) {
	super(data, draw, recordsFiltered, recordsTotal);
    }

    public WorkSearchResultVO(List<Work> data, Integer draw, Integer recordsTotal) {
	super(data, draw, recordsTotal);
    }

    // /** The results. */
    // private List<Work> results;
    //
    // /** The has to many results. */
    // private Boolean hasTooManyResults = false;
    //
    // private Integer numberOfResults;
    //
    // /** The has to many remote results. */
    // private Boolean hasTooManySimpleSearchResults = false;
    //
    // private Integer maxSearchResults;

    // /**
    // * Gets the results.
    // *
    // * @return the results
    // */
    // public List<Work> getResults() {
    // if (results == null) {
    // results = new ArrayList<>();
    // }
    // return results;
    // }
    //
    // /**
    // * Sets the results.
    // *
    // * @param results
    // * the new results
    // */
    // public void setResults(List<Work> results) {
    // this.results = results;
    // }
    //
    // /**
    // * Gets the checks for to many results.
    // *
    // * @return the checks for to many results
    // */
    // public Boolean getHasTooManyResults() {
    // return hasTooManyResults;
    // }
    //
    // /**
    // * Sets the checks for to many results.
    // *
    // * @param hasTooManyResults
    // * the new checks for to many results
    // */
    // public void setHasTooManyResults(Boolean hasTooManyResults) {
    // this.hasTooManyResults = hasTooManyResults;
    // }
    //
    // public Integer getNumberOfResults() {
    // return numberOfResults;
    // }
    //
    // public void setNumberOfResults(Integer numberOfResults) {
    // this.numberOfResults = numberOfResults;
    // }
    //
    // public Boolean getHasTooManySimpleSearchResults() {
    // return hasTooManySimpleSearchResults;
    // }
    //
    // public void setHasTooManySimpleSearchResults(Boolean hasTooManySimpleSearchResults) {
    // this.hasTooManySimpleSearchResults = hasTooManySimpleSearchResults;
    // }
    //
    // public Integer getMaxSearchResults() {
    // return maxSearchResults;
    // }
    //
    // public void setMaxSearchResults(Integer maxSearchResults) {
    // this.maxSearchResults = maxSearchResults;
    // }

}
