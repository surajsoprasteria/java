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
package org.wipo.connect.shared.business.import_ip;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.collections.map.MultiKeyMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.ConversionUtils;
import org.wipo.connect.shared.exchange.utils.TerritoryFormulaUtils;
import org.wipo.connect.shared.exchange.vo.TerritoryHierarchyVO;

/**
 * The Class AffiliationContextShareServiceMap.
 */
public class AffiliationContextMap {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(AffiliationContextMap.class);

    private final static String MAP_SEP = "\u0000\u0000\u0000";
    private Set<String> contextSet = new HashSet<>();
    private SimpleDateFormat ft = new SimpleDateFormat(ConversionUtils.DATE_STAMP);

    private MultiKeyMap map = MultiKeyMap.decorate(new LinkedMap());
    private TerritoryHierarchyVO th;

    public AffiliationContextMap(TerritoryHierarchyVO th) {
	super();
	this.th = th;
    }

    public void put(String contextChecksum, String domainChecksum, BigDecimal value) {
	if (StringUtils.isAnyEmpty(contextChecksum, domainChecksum)) {
	    throw new IllegalArgumentException("key must be not empty");
	}

	map.put(contextChecksum, domainChecksum, value);

	contextSet.add(contextChecksum);
    }

    public BigDecimal getSumInContext(String contextChecksum, String domainChecksum) {

	BigDecimal value = null;
	BigDecimal sum = BigDecimal.ZERO;
	for (String contextKey : contextSet) {
	    if (isInContext(contextKey, contextChecksum)) {
		value = ((BigDecimal) map.get(contextKey, domainChecksum));
		if (value != null) {
		    sum = sum.add(value);
		}
	    }
	}
	if (sum.compareTo(BigDecimal.ZERO) == 0) {
	    return value;
	}
	return sum;
    }

    public Date getEndDateInContext(String contextChecksum, String domainChecksum) {

	for (String contextKey : contextSet) {
	    if (map.containsKey(contextKey, domainChecksum) && isInContext(contextKey, contextChecksum)) {
		// Cmo = 0; StartDate = 1; EndDate = 2; SignatureDate = 3; TerritoryFormula = 4
		String[] keyArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(contextKey, MAP_SEP);
		return parseDate(keyArray[2]);
	    }
	}
	return null;
    }

    public BigDecimal get(String contextChecksum, String domainChecksum) {
	return (BigDecimal) map.get(contextChecksum, domainChecksum);
    }

    private boolean isInContext(String contextKey, String contextChecksum) {

	// Cmo = 0; StartDate = 1; EndDate = 2; SignatureDate = 3; TerritoryFormula = 4
	String[] keyArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(contextKey, MAP_SEP);
	String[] contextArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(contextChecksum, MAP_SEP);

	Date startDate = parseDate(keyArray[1]);
	Date endDate = parseDate(keyArray[2]);
	String formula = keyArray[4];

	Date inputStartDate = parseDate(contextArray[1]);
	Date inputEndDate = parseDate(contextArray[2]);
	String inputFormula = contextArray[4];

	boolean isOverlapping = TerritoryFormulaUtils.isOverlapping(formula, inputFormula, th);
	if (!isOverlapping) {
	    return false;
	}

	if (endDate == null && inputEndDate == null && isOverlapping) {
	    return true;
	}

	if (endDate == null && inputEndDate != null && startDate.compareTo(inputEndDate) < 1 && isOverlapping) {
	    return true;
	} else if (endDate == null && inputEndDate != null && startDate.compareTo(inputEndDate) >= 0 && isOverlapping) {
	    return false;
	}

	if (inputEndDate == null && endDate != null && inputStartDate.compareTo(endDate) < 1 && isOverlapping) {
	    return true;
	} else if (inputEndDate == null && endDate != null && inputStartDate.compareTo(endDate) >= 0 && isOverlapping) {
	    return false;
	}

	if ((inputStartDate.compareTo(endDate) < 1 && inputEndDate.compareTo(startDate) >= 0 && isOverlapping)
		|| (inputEndDate.compareTo(startDate) > 0 && inputStartDate.compareTo(endDate) < 1 && isOverlapping)) {
	    return true;
	}

	return false;
    }

    private Date parseDate(String date) {
	try {
	    if (StringUtils.isNotEmpty(date)) {
		return ft.parse(date);
	    }
	} catch (ParseException e) {
	    LOGGER.error("Error", e);
	}
	return null;
    }

}
