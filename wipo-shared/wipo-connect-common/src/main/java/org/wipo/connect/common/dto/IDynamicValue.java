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

package org.wipo.connect.common.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * The Interface IDynamicValue.
 */
public interface IDynamicValue extends Serializable, IDynamicField {

    String RAW_DATE_FORMAT = "yyyyMMdd";

    String getRawValue();

    Object getValue();

    void setRawValue(String rawValue);

    void setValue(Object value);

    Long getFkDynamicField();

    void setFkDynamicField(Long fkDynamicField);

    Long getFkRefEntity();

    void setFkRefEntity(Long fkRefEntity);

    Map<String, String> getPossibleValuesList();

    List<String> getMultipleValuesList();

}