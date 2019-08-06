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
package org.wipo.connect.common.sftp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpConnectionChecker {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(SftpConnectionChecker.class);

    private SftpConnectionChecker() {
	super();
    }

    public static SftpConnectionResultEnum check(String host, int port, String user, String password, String directory) {
	Session session;
	ChannelSftp channel;

	LOGGER.info("Checking sftp connection: [Host:{} - Port:{} - User:{} - Using Password: {} - Base Dir:{}]", host, port, user, StringUtils.isNotEmpty(password), directory);

	// open sftp channel
	try {
	    // configure sftp session
	    JSch jsch = new JSch();
	    session = jsch.getSession(user, host, port);
	    session.setPassword(password);
	    session.setConfig("StrictHostKeyChecking", "no");
	    session.connect();
	    channel = (ChannelSftp) session.openChannel("sftp");
	    channel.connect();
	} catch (JSchException e) {
	    SftpConnectionResultEnum error = null;
	    if (e.getCause() instanceof JSchException) {
		// TODO: check if return specific message for auth error
		error = SftpConnectionResultEnum.CONNECTION_ERROR;
		LOGGER.warn("Connection checker: error [{}, {}]", SftpConnectionResultEnum.AUTH_ERROR, e.getMessage());
	    } else {
		error = SftpConnectionResultEnum.CONNECTION_ERROR;
		LOGGER.warn("Connection checker: error [{}, {}]", SftpConnectionResultEnum.CONNECTION_ERROR, e.getMessage());
	    }
	    return error;
	}

	// open the directory
	try {
	    channel.cd(directory);
	    channel.ls("*");
	} catch (SftpException e) {
	    LOGGER.warn("Connection checker: error [{}, {}]", SftpConnectionResultEnum.READ_ERROR, e.getMessage());
	    channel.disconnect();
	    session.disconnect();
	    return SftpConnectionResultEnum.READ_ERROR;
	}

	// write and delete dummy file
	String filename = UUID.randomUUID().toString();
	try (InputStream is = new ByteArrayInputStream(filename.getBytes());) {
	    channel.put(is, filename, ChannelSftp.OVERWRITE);
	    channel.rm(filename);
	} catch (IOException e) {
	    LOGGER.warn("Connection checker: error [{}, {}]", SftpConnectionResultEnum.UNKNOWN_ERROR, e.getMessage());
	    channel.disconnect();
	    session.disconnect();
	    return SftpConnectionResultEnum.UNKNOWN_ERROR;
	} catch (SftpException e) {
	    LOGGER.warn("Connection checker: error [{}, {}]", SftpConnectionResultEnum.WRITE_ERROR, e.getMessage());
	    channel.disconnect();
	    session.disconnect();
	    return SftpConnectionResultEnum.WRITE_ERROR;
	}

	// disconnect channel and session
	LOGGER.info("Connection checker: success");
	channel.disconnect();
	session.disconnect();
	return SftpConnectionResultEnum.SUCCESS;
    }

}
