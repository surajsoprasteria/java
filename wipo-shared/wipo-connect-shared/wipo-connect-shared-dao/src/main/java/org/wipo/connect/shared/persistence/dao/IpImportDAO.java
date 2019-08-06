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

import org.wipo.connect.shared.exchange.dto.impl.ImportStatusFlat;
import org.wipo.connect.shared.exchange.dto.impl.IpImport;
import org.wipo.connect.shared.exchange.dto.impl.IpImportFile;
import org.wipo.connect.shared.exchange.enumerator.ImportStatusEnum;
import org.wipo.connect.shared.persistence.Dao;

/**
 * The IpImportDAO interface provides methods that manipulate the data from the database.
 *
 * @author minervini
 *
 */
public interface IpImportDAO extends Dao<IpImport> {

    /**
     * Insert ip import.
     *
     * @param ipImport
     *            the ip import
     * @return the ip import
     */
    IpImport insertIpImport(IpImport ipImport);

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

    /**
     * Update status.
     *
     * @param idIpImport
     *            the id ip import
     * @param idStatus
     *            the id status
     * @return the int
     */
    int updateStatus(Long idIpImport, Long idStatus);

    /**
     * Update status.
     *
     * @param idIpImport
     *            the id ip import
     * @param statusCode
     *            the status code
     * @return the int
     */
    int updateStatus(Long idIpImport, ImportStatusEnum statusCode);

    /**
     * Update import start date.
     *
     * @param idIpImport
     *            the id ip import
     * @param startDate
     *            the start date
     * @return the int
     */
    int updateImportStartDate(Long idIpImport, Date startDate);

    /**
     * Update import end date.
     *
     * @param idIpImport
     *            the id ip import
     * @param endDate
     *            the end date
     * @return the int
     */
    int updateImportEndDate(Long idIpImport, Date endDate);

    /**
     * Find ip import file by id.
     *
     * @param id
     *            the id
     * @return the ip import file
     */
    IpImportFile findIpImportFileById(Long id);

    /**
     * Find import status by code.
     *
     * @param code
     *            the code
     * @return the import status flat
     */
    ImportStatusFlat findImportStatusByCode(ImportStatusEnum code);

    /**
     * Count ip import file by name.
     *
     * @param fileName
     *            the file name
     * @return the long
     */
    Long countIpImportFileByName(String fileName);

    IpImport updateRowResult(IpImport ipImport);

    int markAllPendingAsError();

}
