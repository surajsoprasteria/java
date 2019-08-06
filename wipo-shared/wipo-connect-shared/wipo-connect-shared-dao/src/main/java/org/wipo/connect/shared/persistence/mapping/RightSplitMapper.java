///*
// * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
// * All Rights Reserved.
// *
// * This file is part of WIPO Connect.
// *
// *
// * @author Fincons
// *
// */
//
//package org.wipo.connect.shared.persistence.mapping;
//
//import java.util.List;
//
//import org.apache.ibatis.annotations.Param;
//import org.wipo.connect.shared.exchange.dto.impl.AffiliationRightSplit;
//import org.wipo.connect.shared.exchange.dto.impl.IpiRightTypeFlat;
//import org.wipo.connect.shared.exchange.dto.impl.RightSplit;
//
///**
// * The Interface RightTypeFlatMapper.
// */
//public interface RightSplitMapper extends Mapper<RightSplit> {
//
//    /**
//     * Find ipi right type by right type.
//     *
//     * @param fkRightType
//     *            the fk right type
//     * @return the list
//     */
//    List<IpiRightTypeFlat> findIpiRightTypeByRightType(@Param(value = "fkRightType") Long fkRightType);
//
//    /**
//     * Find affiliation right split.
//     *
//     * @param idName
//     *            the id interested party
//     * @param idRightType
//     *            the id right type
//     * @return the list
//     */
//    List<AffiliationRightSplit> findAffiliationRightSplit(@Param(value = "idName") Long idName, @Param(value = "idRightType") Long idRightType);
//
//}
