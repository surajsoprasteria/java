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

package org.wipo.connect.shared.identifierprocessor.temp;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.shared.identifierprocessor.entity.ImportStatusFlat;

/**
 * The Interface IpImportMapper.
 *
 * @author minervini
 */
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public interface IpImportMapper extends Mapper<IpImport> {

    /**
     * Insert ip import.
     *
     * @param ipImport
     *            the ip import
     * @return the int
     */
    int insertIpImport(IpImport ipImport);

    /**
     * Insert ip import file.
     *
     * @param ipImportFile
     *            the ip import file
     * @return the int
     */
    int insertIpImportFile(IpImportFile ipImportFile);

    /**
     * Find ip import from status.
     *
     * @param status
     *            the status
     * @return the list
     */
    List<IpImport> findIpImportFromStatus(ImportStatusEnum status);

    
    List<IpImport> findAll();

    /**
     * Update import status.
     *
     * @param idIpImport
     *            the id ip import
     * @param idStatus
     *            the id status
     * @return the int
     */
    int updateImportStatus(@Param("idIpImport") Long idIpImport, @Param("idStatus") Long idStatus);

    /**
     * Update import status code.
     *
     * @param idIpImport
     *            the id ip import
     * @param status
     *            the status
     * @return the int
     */
    int updateImportStatusCode(@Param("idIpImport") Long idIpImport, @Param("statusCode") ImportStatusEnum status);

    /**
     * Update import start date.
     *
     * @param idIpImport
     *            the id ip import
     * @param startDate
     *            the start date
     * @return the int
     */
    int updateImportStartDate(@Param("idIpImport") Long idIpImport, @Param("startDate") Date startDate);

    /**
     * Update import end date.
     *
     * @param idIpImport
     *            the id ip import
     * @param endDate
     *            the end date
     * @return the int
     */
    int updateImportEndDate(@Param("idIpImport") Long idIpImport, @Param("endDate") Date endDate);

    /**
     * Find ip import file by id.
     *
     * @param id
     *            the id
     * @return the ip import file
     */
    IpImportFile findIpImportFileById(@Param("id") Long id);

    /**
     * Find by code.
     *
     * @param code
     *            the code
     * @return the import status flat
     */
    ImportStatusFlat findByCode(ImportStatusEnum code);

    /**
     * Count ip import file by name.
     *
     * @param fileName
     *            the file name
     * @return the long
     */
    Long countIpImportFileByName(@Param("fileName") String fileName);

    int updateRowResult(IpImport ipImport);

    int markAllPendingAsError();
}
