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



package org.wipo.connect.shared.web.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.wipo.connect.shared.exchange.enumerator.InterestedPartyStatusEnum;
import org.wipo.connect.shared.exchange.enumerator.WorkStatusEnum;


/**
 * The Class StatusColorManager.
 */
@Service
public class StatusColorManager {

	private static final String DEFAULT_COLOR = "#FFFFFF";

	@Autowired
	@Qualifier("configProperties")
	private Properties configProperties;

	private Map<String,String> workMap;
	private Map<String,String> ipMap;
	private Map<String,String> agreementMap;
	private Map<String,String> licenseeAccountMap;
	private Map<String,String> licenseMap;
	private Map<String,String> collectionRequestMap;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init(){
		// init work color map
		workMap = new HashMap<>();
		workMap.put(WorkStatusEnum.DELETED.toString(), configProperties.getProperty("work_status_color.deleted", DEFAULT_COLOR));
		workMap.put(WorkStatusEnum.VALID.toString(), configProperties.getProperty("work_status_color.valid", DEFAULT_COLOR));

		// init interested party color map
		ipMap = new HashMap<>();
		ipMap.put(InterestedPartyStatusEnum.VALID.toString(), configProperties.getProperty("ip_status_color.valid", DEFAULT_COLOR));
		ipMap.put(InterestedPartyStatusEnum.DELETED.toString(),  configProperties.getProperty("ip_status_color.deleted", DEFAULT_COLOR));
	}


    /**
     * Gets the work status color map.
     *
     * @return the work status color map
     */
    public Map<String,String> getWorkStatusColorMap(){
    	return workMap;
    }

    /**
     * Gets the ip status color map.
     *
     * @return the ip status color map
     */
    public Map<String,String> getIpStatusColorMap(){
    	return ipMap;
    }

    /**
     * Gets the agreement status color map.
     *
     * @return the agreement status color map
     */
    public Map<String,String> getAgreementStatusColorMap(){
    	return agreementMap;
    }

    /**
     * Gets the licensee account status color map.
     *
     * @return the licensee account status color map
     */
    public Map<String,String> getLicenseeAccountStatusColorMap(){
    	return licenseeAccountMap;
    }

    /**
     * Gets the license status color map.
     *
     * @return the license status color map
     */
    public Map<String,String> getLicenseStatusColorMap(){
    	return licenseMap;
    }


	/**
	 * Gets the collection request map.
	 *
	 * @return the collection request map
	 */
	public Map<String, String> getCollectionRequestMap() {
		return collectionRequestMap;
	}

}
