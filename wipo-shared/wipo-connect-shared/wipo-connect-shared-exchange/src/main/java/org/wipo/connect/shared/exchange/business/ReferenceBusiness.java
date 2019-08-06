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

package org.wipo.connect.shared.exchange.business;

import java.util.List;
import java.util.Map;

import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.shared.exchange.dto.impl.Cmo;
import org.wipo.connect.shared.exchange.dto.impl.CreationClassFlat;
import org.wipo.connect.shared.exchange.dto.impl.IdentifierFlat;
import org.wipo.connect.shared.exchange.dto.impl.IpiRightTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.IpiRoleFlat;
import org.wipo.connect.shared.exchange.dto.impl.ReferenceFlat;
import org.wipo.connect.shared.exchange.dto.impl.ReferenceTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.RightTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.RoleFlat;
import org.wipo.connect.shared.exchange.dto.impl.Territory;
import org.wipo.connect.shared.exchange.dto.impl.TerritoryName;
import org.wipo.connect.shared.exchange.vo.CmoSearchVO;

/**
 * The WorkBusiness interface provides business methods orchestrating the functions made available by the layer DAO.
 *
 * @author fumagalli
 */
public interface ReferenceBusiness {

    // ---------------------------------------------------------------------
    // ---------------- CMO ------------------------------------------------
    // --------------------------------------------------------------------

    Cmo getCmoById(Long idCmo) throws WccException;

    Cmo insertOrUpdateCmo(Cmo cmo) throws WccException;

    List<ReferenceFlat> getCmoTypeList() throws WccException;

    Map<Long, ReferenceFlat> getCmoTypeMap() throws WccException;

    List<Cmo> getCmoList() throws WccException;

    List<Cmo> findCmo(CmoSearchVO searchVO) throws WccException;

    Boolean checkCmoCodeUniqueness(String code, Long id) throws WccException;

    // ---------------------------------------------------------------------
    // ---------------- TERRITORY ------------------------------------------
    // --------------------------------------------------------------------

    List<Territory> getAllTerritoryList() throws WccException;

    Territory findTerritoryById(Long id) throws WccException;

    List<TerritoryName> findTerritoryNamesByCode(String code) throws WccException;

    TerritoryName findTerritoryNamesById(Long id) throws WccException;

    Territory insertOrUpdateTerritory(Territory territory) throws WccException;

    TerritoryName insertOrUpdateTerritoryName(TerritoryName territoryName) throws WccException;

    Boolean checkTisnCodeUniqueness(String code, Long id) throws WccException;

    Boolean checkTisaCodeUniqueness(String code, Long id) throws WccException;

    // ---------------------------------------------------------------------
    // ---------------- CREATION CLASS -------------------------------------
    // --------------------------------------------------------------------

    CreationClassFlat findCreationClassById(Long id) throws WccException;

    CreationClassFlat insertOrUpdateCreationClass(CreationClassFlat creationClass) throws WccException;

    Boolean checkCcCodeUniqueness(String code, Long id) throws WccException;

    // ---------------------------------------------------------------------
    // ---------------- ROLE -----------------------------------------------
    // --------------------------------------------------------------------

    RoleFlat insertOrUpdateRole(RoleFlat role) throws WccException;

    Boolean checkRoleCodeUniqueness(String code, Long id) throws WccException;

    IpiRoleFlat insertOrUpdateIpiRole(IpiRoleFlat ipi) throws WccException;

    IpiRoleFlat findIpiRoleById(Long id) throws WccException;

    Boolean checkIpiRoleCodeUniqueness(String code, Long id) throws WccException;

    // ---------------------------------------------------------------------
    // ---------------- RIGHT TYPE -----------------------------------------
    // --------------------------------------------------------------------

    RightTypeFlat insertOrUpdateRightType(RightTypeFlat rightType) throws WccException;

    Boolean checkRightTypeCodeUniqueness(String code, Long id) throws WccException;

    IpiRightTypeFlat insertOrUpdateIpiRightType(IpiRightTypeFlat irt) throws WccException;

    Boolean checkIpiRightTypeCodeUniqueness(String code, Long id) throws WccException;

    // ---------------------------------------------------------------------
    // ---------------- IDENTIFIER -----------------------------------------
    // --------------------------------------------------------------------

    IdentifierFlat insertOrUpdateIdentifier(IdentifierFlat identifier) throws WccException;

    // ---------------------------------------------------------------------
    // ---------------- REFERENCE ------------------------------------------
    // --------------------------------------------------------------------

    ReferenceFlat insertOrUpdateReference(ReferenceFlat reference) throws WccException;

    ReferenceTypeFlat insertOrUpdateReferenceType(ReferenceTypeFlat referenceType) throws WccException;

    Boolean checkReferenceTypeCodeUniqueness(String code, Long id) throws WccException;

}
