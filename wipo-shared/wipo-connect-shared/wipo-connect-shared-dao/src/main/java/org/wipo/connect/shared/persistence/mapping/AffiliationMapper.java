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

package org.wipo.connect.shared.persistence.mapping;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.wipo.connect.shared.exchange.dto.impl.Affiliation;
import org.wipo.connect.shared.exchange.dto.impl.AffiliationDomain;
import org.wipo.connect.shared.exchange.dto.impl.AffiliationSplit;

public interface AffiliationMapper extends Mapper<Affiliation> {

    List<Affiliation> findByInterestedParty(@Param("idInterestedParty") Long idInterestedParty, @Param("ccCodeList") List<String> ccCodeList);

    int insertAffiliationDomain(AffiliationDomain affiliationDomain);

    int insertAffiliationTerritory(@Param("idAffiliation") Long idAffiliation, @Param("idTerritory") Long idTerritory);

    int updateEndDate(@Param("idAffiliation") Long idAffiliation, @Param("endDate") Date endDate);

    int updateDomainExclusion(@Param("idAffiliationDomain") Long idAffiliationDomain, @Param("isExcluded") Boolean isExcluded);

    int updateDomainExclusionList(@Param("idAffiliationDomainList") List<Long> idAffiliationDomainList, @Param("isExcluded") Boolean isExcluded);

    int updateTerritoryByAffiliation(@Param("idAffiliation") Long idAffiliation, @Param("territoryFormula") String territoryFormula);

    int deleteAffiliationDomainByParent(Long fkAffiliation);

    List<AffiliationSplit> findAffiliationSplit(@Param("idName") Long idName, @Param("roleCode") String roleCode, @Param("rightTypeCode") String rightTypeCode);

    Set<String> findAffiliatedCmosByName(@Param("idName") Long idName);

    boolean checkIfExistAffiliationDomain(@Param("idAffiliation") Long idAffiliation);

    void deleteAffiliationDomainByIp(@Param("idInterestedParty") Long idInterestedParty);

    void deleteAffiliationByIp(@Param("idInterestedParty") Long idInterestedParty);

    void deleteAffiliation(@Param("idAffiliation") Long idAffiliation);

    void deleteAffiliationDomain(@Param("idAffiliationDomain") Long idAffiliationDomain);

}
