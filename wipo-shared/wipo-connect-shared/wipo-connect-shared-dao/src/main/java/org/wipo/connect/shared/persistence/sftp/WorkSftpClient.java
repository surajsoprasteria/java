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
package org.wipo.connect.shared.persistence.sftp;

import java.util.List;

import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.shared.exchange.dto.impl.WorkImport;

/**
 * The Interface WorkSftpClient.
 */
public interface WorkSftpClient {

    /**
     * Gets the work import list from sftp.
     *
     * @return the work import list from sftp
     * @throws WccException
     *             the wcc exception
     */
    List<WorkImport> getWorkImportListFromSftp() throws WccException;

    /**
     * Read work import file from sftp.
     *
     * @param filename
     *            the filename
     * @return the byte[]
     * @throws WccException
     *             the wcc exception
     */
    byte[] readWorkImportFileFromSftp(String filename) throws WccException;

    /**
     * Write work import result file to sftp.
     *
     * @param filename
     *            the filename
     * @param fileContent
     *            the file content
     * @throws WccException
     *             the wcc exception
     */
    void writeWorkImportResultFileToSftp(String filename, byte[] fileContent) throws WccException;

    /**
     * Gets the work import file from sftp.
     *
     * @param filename
     *            the filename
     * @return the work import file from sftp
     * @throws WccException
     *             the wcc exception
     */
    String getWorkImportFileFromSftp(String filename) throws WccException;

    /**
     * Write work import result file to sftp.
     *
     * @param filename
     *            the filename
     * @param tempFilePath
     *            the temp file path
     * @throws WccException
     *             the wcc exception
     */
    void writeWorkImportResultFileToSftp(String filename, String tempFilePath) throws WccException;

    void writeWorkExportFileToSftp(String filename, byte[] fileContent) throws WccException;

    void writeWorkExportFileToSftp(String filename, String tempFilePath) throws WccException;

    void writeWorkFullExportFileToSftp(String filename, String tempFilePath, String ipFullExportPath) throws WccException;

}