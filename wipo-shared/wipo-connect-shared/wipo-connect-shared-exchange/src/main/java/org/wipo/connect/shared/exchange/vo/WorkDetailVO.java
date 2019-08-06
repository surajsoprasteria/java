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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.shared.exchange.dto.impl.Work;

public class WorkDetailVO implements Serializable {

    private static final long serialVersionUID = 2716926359204548709L;

    private Work work;

    private Work workInConflict;

    private List<WorkViewVO> workViewList;

    private String importCode;

    public Work getWork() {
	return this.work;
    }

    public void setWork(Work work) {
	this.work = work;
    }

    public Work getWorkInConflict() {
	return workInConflict;
    }

    public void setWorkInConflict(Work workInConflict) {
	this.workInConflict = workInConflict;
    }

    @Override
    public String toString() {
	return ReflectionToStringBuilder.toString(this);
    }

    public String getImportCode() {
	return importCode;
    }

    public void setImportCode(String importCode) {
	this.importCode = importCode;
    }

    public List<WorkViewVO> getWorkViewList() {
	if (null == workViewList) {
	    workViewList = new ArrayList<WorkViewVO>();
	}
	return workViewList;
    }

    public void setWorkViewList(List<WorkViewVO> workViewList) {
	this.workViewList = workViewList;
    }

}
