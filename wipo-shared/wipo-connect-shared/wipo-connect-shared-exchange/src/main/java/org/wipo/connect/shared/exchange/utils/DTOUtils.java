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

package org.wipo.connect.shared.exchange.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.dto.Deletable;
import org.wipo.connect.common.dto.Identifiable;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.shared.exchange.dto.impl.InterestedPartyStatusFlat;
import org.wipo.connect.shared.exchange.dto.impl.RoleFlat;
import org.wipo.connect.shared.exchange.dto.impl.WorkStatusFlat;
import org.wipo.connect.shared.exchange.enumerator.WorkStatusEnum;

/**
 * The Class DTOUtils.
 */
public class DTOUtils {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = WipoLoggerFactory.getLogger(DTOUtils.class);



    /**
     * Clean deletable collection.
     *
     * @param inputList            the input list
     * @return the collection<? extends deletable>
     */
    public static Collection<? extends Deletable> cleanDeletableCollection(
            Collection<? extends Deletable> inputList) {

        Iterator<? extends Deletable> it = inputList.iterator();

        while (it.hasNext()) {
            Deletable item = it.next();
            if (BooleanUtils.isTrue(item.getExecDelete())
                    && item.getId() == null) {
                it.remove();
            }
        }

        return inputList;
    }

	/**
	 * Gets the id by code.
	 *
	 * @param ipStatusList
	 *            the ip status list
	 * @param status
	 *            the status
	 * @return the id by code
	 */
	public static Long getIdByCode(List<InterestedPartyStatusFlat> ipStatusList, String status) {
		Long id = null;
		for (InterestedPartyStatusFlat ips : ipStatusList) {
			if (ips.getCode().equalsIgnoreCase(status)) {
				id = ips.getId();
				return id;
			}
		}
		throw new IllegalArgumentException("InterestedPartyStatus id not found");
	}

	/**
	 * Gets the id by code.
	 *
	 * @param statusList
	 *            the status list
	 * @param status
	 *            the status
	 * @return the id by code
	 */
	public static Long getIdByCode(List<WorkStatusFlat> statusList, WorkStatusEnum status) {
		Long id = null;
		for (WorkStatusFlat wrkStatus : statusList) {
			if (wrkStatus.getCode().equalsIgnoreCase(status.name())) {
				id = wrkStatus.getId();
				return id;
			}
		}
		throw new IllegalArgumentException("WorkPartyStatus id not found");
	}

	/**
	 * TOFIX: TOTEST:.
	 *
	 * @param map
	 *            the map
	 * @param value
	 *            the value
	 * @param code
	 *            the code
	 * @return the map key from object property
	 * @throws WccException
	 *             the wcc exception
	 */
	public static String getMapKeyFromObjectProperty(Map<Long, ?> map, Object value, String code) throws WccException {
		try {
			Method readerId = null;
			for (Object obj : map.values()) {
				BeanInfo info = Introspector.getBeanInfo(obj.getClass());
				for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
					if ("id".equalsIgnoreCase(pd.getName())) {
						readerId = pd.getReadMethod();
					}
				}
				for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
					if (!"class".equalsIgnoreCase(pd.getName()) && pd.getName().equalsIgnoreCase(code)) {
						Method reader = pd.getReadMethod();
						if (reader != null) {
							Object valueTotest = reader.invoke(obj);
							if (valueTotest.toString().equalsIgnoreCase(value.toString())) {
								String valueToReturn = readerId.invoke(obj).toString();
								LOGGER.debug("-------------valueToReturn: " + valueToReturn);
								return valueToReturn;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			throw new WccException(WccExceptionCodeEnum.APPLICATION_ERROR, e);
		}
		return null;
	}

	/**
	 * Util method to convert a list to map using as key a object list id.
	 *
	 * @param <T>
	 *            the generic type
	 * @param dtoList
	 *            the dto list
	 * @return the map
	 */
	public static <T extends Identifiable> Map<Long, T> listToMapById(List<T> dtoList) {
		Map<Long, T> keyMap = new HashMap<Long, T>();
		for (T dto : dtoList) {
			keyMap.put(dto.getId(), dto);
		}
		return keyMap;
	}
	
	/**
	 * Util method to convert a list to map using as key the value of the
	 * specified object field name.
	 *
	 * @param <T>
	 *            the generic type
	 * @param dtoList
	 *            the dto list
	 * @param fieldName
	 *            the field name
	 * @return the map
	 * @throws WccException
	 *             the wcc exception
	 */
	public static <T extends Object> Map<Object, T> listToMapByField(List<T> dtoList, String fieldName) throws WccException {
		try {
			Map<Object, T> keyMap = new HashMap<>();

			for (T dto : dtoList) {
				Object key;
				key = PropertyUtils.getProperty(dto, fieldName);
				keyMap.put(key, dto);
			}
			return keyMap;
		} catch (Exception e) {
			throw new WccException(WccExceptionCodeEnum.APPLICATION_ERROR, e);
		}

	}

	/**
	 * List to map role.
	 *
	 * @param dtoList the dto list
	 * @param filter the filter
	 * @return the map
	 */
	public static Map<Long, RoleFlat> listToMapRole(List<RoleFlat> dtoList, String filter) {
		Map<Long, RoleFlat> keyMap = new HashMap<Long, RoleFlat>();
		if (!StringUtils.isBlank(filter)) {
			for (RoleFlat dto : dtoList) {
				if (!dto.getCode().equalsIgnoreCase(filter)) {
					keyMap.put(dto.getId(), dto);
				}
			}
		} else {
			for (RoleFlat dto : dtoList) {
				keyMap.put(dto.getId(), dto);
			}
		}
		return keyMap;
	}
	
	private DTOUtils() {
		super();
	}
	
	

}
