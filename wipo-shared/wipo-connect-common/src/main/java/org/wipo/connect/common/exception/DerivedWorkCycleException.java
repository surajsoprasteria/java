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
package org.wipo.connect.common.exception;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.wipo.connect.common.vo.PairVO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class IpDeleteError.
 */
@JsonIgnoreProperties(ignoreUnknown = true, value = { "detectedCycleId", "detectedCycleMainId" })
public class DerivedWorkCycleException extends WccException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3013931278069453813L;

    private Set<PairVO<Long, String>> detectedCycle;

    /**
     * Instantiates a new ip delete error exception.
     *
     * @param code
     *            the code
     * @param message
     *            the message
     * @param data
     *            the data
     * @param uuid
     *            the uuid
     * @param detectedCycle
     *            the detected cycle
     */
    @JsonCreator
    protected DerivedWorkCycleException(@JsonProperty(value = "code", required = true) WccExceptionCodeEnum code, @JsonProperty(value = "message", required = false) String message,
	    @JsonProperty(value = "data", required = false) Map<String, Object> data, @JsonProperty(value = "uuid", required = true) String uuid,
	    @JsonProperty(value = "detectedCycle", required = true) Set<PairVO<Long, String>> detectedCycle) {
	super(code, message, data, uuid);
	this.detectedCycle = detectedCycle;
    }

    /**
     * Instantiates a new GroupName delete error exception.
     *
     * @param message
     *            the message
     * @param dwCycles
     *            the detected cycle
     */
    public DerivedWorkCycleException(String message, Set<PairVO<Long, String>> dwCycles) {
	super(WccExceptionCodeEnum.APPLICATION_ERROR, message);
	this.detectedCycle = dwCycles;
    }

    /**
     * Det detected cycle.
     *
     * @return the sets the
     */
    public Set<PairVO<Long, String>> getDetectedCycle() {
	if (detectedCycle == null) {
	    detectedCycle = new HashSet<>();
	}
	return detectedCycle;
    }

    public Set<Long> getDetectedCycleId() {
	if (detectedCycle == null) {
	    detectedCycle = new HashSet<>();
	}
	Set<Long> setId = new HashSet<>();
	detectedCycle.forEach(item -> {
	    setId.add(item.getLeft());
	});

	return setId;
    }

    public Set<String> getDetectedCycleMainId() {
	if (detectedCycle == null) {
	    detectedCycle = new HashSet<>();
	}
	Set<String> setId = new HashSet<>();
	detectedCycle.forEach(item -> {
	    setId.add(item.getRight());
	});

	return setId;
    }

    /**
     * Sets the detected cycle.
     *
     * @param detectedCycle
     *            the new detected cycle
     */
    public void setDetectedCycle(Set<PairVO<Long, String>> detectedCycle) {
	this.detectedCycle = detectedCycle;
    }

}
