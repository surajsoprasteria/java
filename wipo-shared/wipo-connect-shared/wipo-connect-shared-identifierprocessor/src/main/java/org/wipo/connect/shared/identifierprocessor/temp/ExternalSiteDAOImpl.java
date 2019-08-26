/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Connect.
 *
 *
 * @author Fincons
 *
 

package org.wipo.connect.shared.identifierprocessor.temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.crypto.CryptoHelper;


@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class ExternalSiteDAOImpl implements ExternalSiteDAO {

    @Autowired
    private ExternalSiteMapper externalSiteMapper;

    @Autowired
    private CryptoHelper cryptoHelper;

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    public ExternalSite selectExternalSiteByCode(ExternalSiteEnum code) {
	ExternalSite extSite = externalSiteMapper.selectExternalSiteByCode(code.name());
	if (null != extSite && extSite.getPassword() != null) {
	    extSite = cryptoHelper.decrypt(extSite);
	}
	return extSite;
    }

    @CacheEvict(value = "mainCache", allEntries = true)
    public int updateExternalSitePassword(ExternalSite externalSite) {
	externalSite = cryptoHelper.encrypt(externalSite);
	return externalSiteMapper.updateExternalSitePassword(externalSite);
    }

    @CacheEvict(value = "mainCache", allEntries = true)
    public int updateExternalSiteByCode(ExternalSite externalSite) {
	return externalSiteMapper.updateExternalSiteByCode(externalSite);
    }

}*/