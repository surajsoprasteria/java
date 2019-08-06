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

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.shared.exchange.dto.impl.IpImportDetail;
import org.wipo.connect.shared.exchange.enumerator.ImportDetailStatusEnum;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public interface IpImportDetailMapper extends Mapper<IpImportDetail> {

    int insertIpImportDetail(IpImportDetail ipImportDetail);

    List<IpImportDetail> findIpImportDetailFromStatus(ImportDetailStatusEnum status);

    int updateImportDetailStatus(@Param("idIpImportDetail") Long idIpImportDetail, @Param("idStatus") Long idStatus);

    int updateImportDetailStatusCode(@Param("idIpImportDetail") Long idIpImportDetail, @Param("statusCode") ImportDetailStatusEnum statusCode);

    void updateImportDetailByFkIp(@Param("fkIpImport") Long fkIpImport, @Param("fkInterestedParty") Long fkInterestedParty);

    Boolean existFkIp(Long fkIp);

    int deleteByFkIpImport(@Param("fkIpImport") Long fkIpImport);

}
