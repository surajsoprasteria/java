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
import org.wipo.connect.shared.exchange.dto.impl.GroupDTO;

/**
 * The Class WorkSearchResultVO.
 */
public class NameGroupResultVO extends PageResult<GroupDTO> implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 557286432997536509L;

    public NameGroupResultVO() {
	super();
    }

    public NameGroupResultVO(List<GroupDTO> data, Integer draw, Integer recordsFiltered, Integer recordsTotal) {
	super(data, draw, recordsFiltered, recordsTotal);
    }

    public NameGroupResultVO(List<GroupDTO> data, Integer draw, Integer recordsTotal) {
	super(data, draw, recordsTotal);
    }

}
