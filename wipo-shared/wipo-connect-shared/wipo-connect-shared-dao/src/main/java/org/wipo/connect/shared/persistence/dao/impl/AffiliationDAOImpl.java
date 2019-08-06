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

package org.wipo.connect.shared.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.shared.exchange.dto.impl.Affiliation;
import org.wipo.connect.shared.exchange.dto.impl.AffiliationDomain;
import org.wipo.connect.shared.exchange.dto.impl.AffiliationSplit;
import org.wipo.connect.shared.persistence.BaseDAO;
import org.wipo.connect.shared.persistence.dao.AffiliationDAO;
import org.wipo.connect.shared.persistence.mapping.AffiliationMapper;

@Service
@Qualifier("affiliationDAOImpl")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class AffiliationDAOImpl extends BaseDAO<Affiliation> implements AffiliationDAO {

    @Autowired
    private AffiliationMapper affiliationMapper;

    @Override
    public List<Affiliation> findByInterestedParty(Long idInterestedParty, List<String> ccCodeList) {
	return this.affiliationMapper.findByInterestedParty(idInterestedParty, ccCodeList);
    }

    @Override
    public Affiliation insert(Affiliation affiliation) {
	this.affiliationMapper.insert(affiliation);
	return affiliation;
    }

    @Override
    public AffiliationDomain insertAffiliationDomain(AffiliationDomain affiliationDomain) {
	this.affiliationMapper.insertAffiliationDomain(affiliationDomain);
	return affiliationDomain;
    }

    @Override
    public int updateEndDate(Long idAffiliation, Date endDate) {
	return affiliationMapper.updateEndDate(idAffiliation, endDate);
    }

    @Override
    public int updateDomainExclusion(Long idAffiliationDomain, Boolean isExcluded) {
	return affiliationMapper.updateDomainExclusion(idAffiliationDomain, isExcluded);
    }

    @Override
    public int updateDomainExclusion(List<Long> idAffiliationDomainList, Boolean isExcluded) {
	return affiliationMapper.updateDomainExclusionList(idAffiliationDomainList, isExcluded);
    }

    @Override
    public int updateTerritoryByAffiliation(Long idAffiliation, String territoryFormula) {
	return affiliationMapper.updateTerritoryByAffiliation(idAffiliation, territoryFormula);
    }

    @Override
    public void removeAllAffiliationByIp(Long idInterestedParty) {
	List<Affiliation> affiliationList = affiliationMapper.findByInterestedParty(idInterestedParty, null);
	for (Affiliation affiliation : affiliationList) {
	    affiliationMapper.deleteAffiliationDomainByParent(affiliation.getIdAffiliation());
	    affiliationMapper.deleteAffiliation(affiliation.getId());
	}
    }

    @Override
    public List<AffiliationSplit> findAffiliationSplit(Long idName, String roleCode, String rightTypeCode) {
	return affiliationMapper.findAffiliationSplit(idName, roleCode, rightTypeCode);
    }

    @Override
    public List<AffiliationSplit> findAffiliationSplit(List<Long> idNameList, List<String> roleCodeList,
	    List<String> rightTypeCodeList) throws WccException {

	List<AffiliationSplit> outList = new ArrayList<>();

	try {
	    if (idNameList.size() != roleCodeList.size() || idNameList.size() != rightTypeCodeList.size()) {
		throw new IllegalArgumentException("All the input lists must have the same size");
	    }

	    for (int i = 0; i < idNameList.size(); i++) {
		Long idName = idNameList.get(i);
		String roleCode = roleCodeList.get(i);
		String rightTypeCode = rightTypeCodeList.get(i);

		List<AffiliationSplit> auxList = findAffiliationSplit(idName, roleCode, rightTypeCode);
		outList.addAll(auxList);
	    }

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

	return outList;
    }

    @Override
    public Set<String> findAffiliatedCmosByName(Long idName) {
	return this.affiliationMapper.findAffiliatedCmosByName(idName);
    }

    @Override
    public boolean checkIfExistAffiliationDomain(Long idAffiliation) {
	return this.affiliationMapper.checkIfExistAffiliationDomain(idAffiliation);
    }

    @Override
    public void deleteAffiliationByIp(Long idInterestedParty) {
	affiliationMapper.deleteAffiliationDomainByIp(idInterestedParty);
	affiliationMapper.deleteAffiliationByIp(idInterestedParty);
    }

    @Override
    public void deleteAffiliation(Long idAffiliation) {
	affiliationMapper.deleteAffiliation(idAffiliation);
    }

    @Override
    public void deleteAffiliationDomain(Long idAffiliationDomain) {
	affiliationMapper.deleteAffiliationDomain(idAffiliationDomain);
    }

}