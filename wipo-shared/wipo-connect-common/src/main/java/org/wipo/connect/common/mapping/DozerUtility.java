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

package org.wipo.connect.common.mapping;

import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.logging.WipoLoggerFactory;

/**
 * The Class DozerUtility.
 */
@Service
public class DozerUtility {

    @Autowired
    private Mapper dozerMapper;
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(DozerUtility.class);

    /**
     * Map.
     *
     * @param <T>
     *            the generic type
     * @param <U>
     *            the generic type
     * @param source
     *            the source
     * @param destList
     *            the dest list
     * @param typeDest
     *            the type dest
     * @return the list
     */
    public <T, U> List<U> map(List<T> source, List<U> destList, Class<U> typeDest) {
	if (source == null || destList == null || typeDest == null) {
	    LOGGER.error("Input parameter cannot be null");
	    throw new IllegalArgumentException("Input parameter cannot be null");
	}
	for (T element : source) {
	    if (element == null) {
		continue;
	    }
	    destList.add(this.dozerMapper.map(element, typeDest));
	}

	return destList;
    }

    /**
     * Map.
     *
     * @param <T>
     *            the generic type
     * @param <U>
     *            the generic type
     * @param source
     *            the source
     * @param destObj
     *            the dest obj
     * @return the u
     */
    public <T, U> U map(T source, U destObj) {
	if (source == null || destObj == null) {
	    LOGGER.error("Input parameter cannot be null");
	    throw new IllegalArgumentException("Input parameter cannot be null");
	}

	this.dozerMapper.map(source, destObj);

	return destObj;
    }

}
