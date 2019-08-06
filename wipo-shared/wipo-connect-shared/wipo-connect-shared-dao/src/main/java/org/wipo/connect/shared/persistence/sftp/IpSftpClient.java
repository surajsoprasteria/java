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
import org.wipo.connect.shared.exchange.dto.impl.IpImport;

/**
 * The Interface IpSftpClient.
 */
public interface IpSftpClient {

    /**
     * Read ip import file from sftp.
     *
     * @param filename
     *            the filename
     * @return the byte[]
     * @throws WccException
     *             the wcc exception
     */
    byte[] readIpImportFileFromSftp(String filename) throws WccException;

    /**
     * Write ip import result file to sftp.
     *
     * @param filename
     *            the filename
     * @param fileContent
     *            the file content
     * @throws WccException
     *             the wcc exception
     */
    void writeIpImportResultFileToSftp(String filename, byte[] fileContent) throws WccException;

    /**
     * Gets the ip import list from sftp.
     *
     * @return the ip import list from sftp
     * @throws WccException
     *             the wcc exception
     */
    List<IpImport> getIpImportListFromSftp() throws WccException;

    void writeIpImportResultFileToSftp(String filename, String tempFilePath) throws WccException;

    String getIpImportFileFromSftp(String filename) throws WccException;

    void writeIpExportFileSubmitToSftp(String filename, byte[] fileContent) throws WccException;

    void writeIpExportFileSubmitToSftp(String filename, String tempFilePath) throws WccException;

    void writeIpFullExportToSftp(String filename, String tempFilePath, String ipFullExportPath) throws WccException;

}