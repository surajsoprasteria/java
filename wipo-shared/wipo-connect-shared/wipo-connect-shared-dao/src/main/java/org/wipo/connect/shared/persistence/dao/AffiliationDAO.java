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

package org.wipo.connect.shared.persistence.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.shared.exchange.dto.impl.Affiliation;
import org.wipo.connect.shared.exchange.dto.impl.AffiliationDomain;
import org.wipo.connect.shared.exchange.dto.impl.AffiliationSplit;
import org.wipo.connect.shared.persistence.Dao;

public interface AffiliationDAO extends Dao<Affiliation> {

    List<Affiliation> findByInterestedParty(Long idInterestedParty, List<String> ccCodeList);

    Affiliation insert(Affiliation affiliation);

    AffiliationDomain insertAffiliationDomain(AffiliationDomain affiliationDomain);

    int updateEndDate(Long idAffiliation, Date endDate);

    int updateDomainExclusion(Long idAffiliationDomain, Boolean isExcluded);

    int updateDomainExclusion(List<Long> idAffiliationDomainList, Boolean isExcluded);

    int updateTerritoryByAffiliation(Long idAffiliation, String territoryFormula);

    void removeAllAffiliationByIp(Long idInterestedParty);

    List<AffiliationSplit> findAffiliationSplit(Long idName, String roleCode, String rightTypeCode);

    List<AffiliationSplit> findAffiliationSplit(List<Long> idNameList, List<String> roleCodeList, List<String> rightTypeCodeList) throws WccException;

    Set<String> findAffiliatedCmosByName(Long idName);

    boolean checkIfExistAffiliationDomain(Long idAffiliation);

    void deleteAffiliationByIp(Long idInterestedParty);

    void deleteAffiliation(Long idAffiliation);

    void deleteAffiliationDomain(Long idAffiliationDomain);

}
