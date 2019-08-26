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

import java.util.List;

import org.wipo.connect.common.exception.WccException;


/**
 * The Interface IpSftpClient.
 */
public interface IpSftpClient {

   

    /**
     * Gets the ip import list from sftp.
     *
     * @return the ip import list from sftp
     * @throws WccException
     *             the wcc exception
     */
    List<IpImport> getIpImportListFromSftp() throws WccException;

   

}