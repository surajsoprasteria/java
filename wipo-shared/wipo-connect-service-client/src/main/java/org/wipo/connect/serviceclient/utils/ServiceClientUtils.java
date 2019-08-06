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
package org.wipo.connect.serviceclient.utils;

import java.util.List;
import java.util.UUID;

import org.wipo.connect.common.input.ContextType;
import org.wipo.connect.common.utils.DateUtils;
import org.wipo.connect.common.vo.ServiceClientInfoVO;

/**
 * The Class ServiceClientUtils.
 */
public class ServiceClientUtils {

	private ServiceClientUtils(){
		super();
	}



	/**
	 * Generate service context.
	 *
	 * @param clientInfoVO the client info VO
	 * @return the context type
	 */
	public static ContextType generateServiceContext(ServiceClientInfoVO clientInfoVO){
		return generateServiceContext(clientInfoVO.getClientId(), clientInfoVO.getClientKey(), clientInfoVO.getManagedCreationClassCodeList());
	}


	/**
	 * Generate service context.
	 *
	 * @param client the client
	 * @param key the key
	 * @return the context type
	 */
	public static ContextType generateServiceContext(String client, String key, List<String> managedCreationClassCodeList){
		ContextType ct = new ContextType();
		ct.setClientId(client);
		ct.setClientKey(key);
		ct.getManagedCreationClassCodeList().addAll(managedCreationClassCodeList);
		ct.setRequestId(UUID.randomUUID().toString());
		ct.setRequestTimestemp(DateUtils.currentXMLGregorianCalendar());
		return ct;
	}


}
