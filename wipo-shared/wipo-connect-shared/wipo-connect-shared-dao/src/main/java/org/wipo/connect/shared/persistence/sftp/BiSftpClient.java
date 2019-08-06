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

import org.wipo.connect.common.exception.WccException;

public interface BiSftpClient {

    byte[] readQueryImportFileFromSftp(String filename) throws WccException;

}