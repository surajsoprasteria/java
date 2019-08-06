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

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.shared.exchange.dto.impl.ExternalSite;
import org.wipo.connect.shared.exchange.enumerator.ExternalSiteEnum;
import org.wipo.connect.shared.persistence.dao.ExternalSiteDAO;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

@Service
public class BiSftpClientImpl implements BiSftpClient {

    @Value("#{configProperties['path.bi.sftpImport-dir']}")
    private String queryImportSftpPath;

    @Autowired
    private ExternalSiteDAO externalSiteDAO;

    @Override
    public byte[] readQueryImportFileFromSftp(String filename) throws WccException {

	try {
	    ExternalSite extSite = externalSiteDAO.selectExternalSiteByCode(ExternalSiteEnum.QUERY_IMPORT_SFTP);
	    // configure sftp session
	    JSch jsch = new JSch();
	    Session session = jsch.getSession(extSite.getUser(), extSite.getHost(), extSite.getPort());
	    session.setPassword(extSite.getPassword());
	    session.setConfig("StrictHostKeyChecking", "no");
	    session.connect();

	    // open sftp channel
	    ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
	    channel.connect();

	    // save the file
	    channel.cd(queryImportSftpPath);
	    InputStream inputStream = channel.get(filename);
	    byte[] fileContent = IOUtils.toByteArray(inputStream);

	    // disconnect channel and session
	    inputStream.close();
	    channel.disconnect();
	    session.disconnect();

	    return fileContent;

	} catch (SftpException e) {
	    if (e.id == ChannelSftp.SSH_FX_NO_SUCH_FILE) {
		return null;
	    } else {
		throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	    }
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

}
