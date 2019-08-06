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

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.import_model.ImportFileTypeEnum;
import org.wipo.connect.common.utils.ConversionUtils;
import org.wipo.connect.shared.exchange.dto.impl.ExternalSite;
import org.wipo.connect.shared.exchange.dto.impl.IpImport;
import org.wipo.connect.shared.exchange.dto.impl.IpImportFile;
import org.wipo.connect.shared.exchange.enumerator.ExternalSiteEnum;
import org.wipo.connect.shared.persistence.dao.ExternalSiteDAO;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * The Class IpSftpClientImpl.
 */
@Service
public class IpSftpClientImpl implements IpSftpClient {

    @Value("#{configProperties['path.rightOwner.sftpImport-dir']}")
    private String ipImportSftpPath;

    @Value("#{configProperties['import.rightOwner.outputTag']}")
    private String ipImportOutputTag;

    @Value("#{configProperties['path.temp-dir']}")
    private String importTempDir;

    @Value("#{configProperties['path.rightOwner.sftpSubmit-dir']}")
    private String ipSubmitSftpPath;

    @Autowired
    private ExternalSiteDAO externalSiteDAO;

    @Override
    public byte[] readIpImportFileFromSftp(String filename) throws WccException {

	byte[] fileContent = null;

	try {
	    ExternalSite extSite = externalSiteDAO.selectExternalSiteByCode(ExternalSiteEnum.IP_IMPORT_SFTP);
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
	    channel.cd(ipImportSftpPath);
	    InputStream inputStream = channel.get(filename);
	    fileContent = IOUtils.toByteArray(inputStream);

	    // disconnect channel and session
	    inputStream.close();
	    channel.disconnect();
	    session.disconnect();

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

	return fileContent;
    }

    @Override
    public String getIpImportFileFromSftp(String filename) throws WccException {

	SimpleDateFormat sdf = new SimpleDateFormat(ConversionUtils.DATE_TIME_STAMP_MILLI);
	String tmpFilename = importTempDir + "/ip.in." + sdf.format(new Date()) + "." + FilenameUtils.getExtension(filename);

	try (FileOutputStream fos = new FileOutputStream(tmpFilename)) {
	    ExternalSite extSite = externalSiteDAO.selectExternalSiteByCode(ExternalSiteEnum.IP_IMPORT_SFTP);
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
	    channel.cd(ipImportSftpPath);
	    InputStream is = channel.get(filename);
	    IOUtils.copyLarge(is, fos);
	    fos.flush();

	    // disconnect channel and session
	    channel.disconnect();
	    session.disconnect();

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

	return tmpFilename;
    }

    @Override
    public void writeIpImportResultFileToSftp(String filename, byte[] fileContent) throws WccException {
	try (InputStream is = new ByteArrayInputStream(fileContent)) {
	    writeIpImportResultFileToSftp(filename, is);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    @Override
    public void writeIpImportResultFileToSftp(String filename, String tempFilePath) throws WccException {
	try (InputStream fis = new FileInputStream(tempFilePath); InputStream bis = new BufferedInputStream(fis);) {
	    writeIpImportResultFileToSftp(filename, bis);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    private void writeIpImportResultFileToSftp(String filename, InputStream is) throws JSchException, SftpException {

	ExternalSite extSite = externalSiteDAO.selectExternalSiteByCode(ExternalSiteEnum.IP_IMPORT_SFTP);
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
	channel.cd(ipImportSftpPath);
	channel.put(is, filename, ChannelSftp.OVERWRITE);

	// disconnect channel and session
	channel.disconnect();
	session.disconnect();
    }

    @Override
    public List<IpImport> getIpImportListFromSftp() throws WccException {
	List<IpImport> ipImportList = new ArrayList<>();

	try {
	    MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
	    ExternalSite extSite = externalSiteDAO.selectExternalSiteByCode(ExternalSiteEnum.IP_IMPORT_SFTP);
	    // configure sftp session
	    JSch jsch = new JSch();
	    Session session = jsch.getSession(extSite.getUser(), extSite.getHost(), extSite.getPort());
	    session.setPassword(extSite.getPassword());
	    session.setConfig("StrictHostKeyChecking", "no");
	    session.connect();

	    // open sftp channel
	    ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
	    channel.connect();

	    // query the channel to get the file list
	    channel.cd(ipImportSftpPath);
	    @SuppressWarnings("unchecked")
	    Vector<ChannelSftp.LsEntry> fileCsvList = channel.ls("*." + ImportFileTypeEnum.CSV.getExtension());
	    @SuppressWarnings("unchecked")
	    Vector<ChannelSftp.LsEntry> fileExcelList = channel.ls("*." + ImportFileTypeEnum.EXCEL.getExtension());

	    Vector<ChannelSftp.LsEntry> fileList = new Vector<>();
	    fileList.addAll(fileCsvList);
	    fileList.addAll(fileExcelList);

	    // disconnect channel and session
	    channel.disconnect();
	    session.disconnect();

	    // loop and generate ipImport list
	    for (LsEntry entry : fileList) {
		if (StringUtils.endsWithIgnoreCase(entry.getFilename(), ipImportOutputTag + "." + ImportFileTypeEnum.CSV.getExtension())
			|| StringUtils.endsWithIgnoreCase(entry.getFilename(), ipImportOutputTag + "." + ImportFileTypeEnum.EXCEL.getExtension())) {
		    continue;
		}

		IpImportFile importFile = new IpImportFile();
		importFile.setFileType(org.wipo.connect.shared.exchange.enumerator.ImportFileTypeEnum.INPUT.name());
		importFile.setFileName(entry.getFilename());
		importFile.setFileSize(entry.getAttrs().getSize());
		importFile.setContentType(mimeTypesMap.getContentType(entry.getFilename()));
		IpImport ipImport = new IpImport();
		ipImport.getIpImportFileList().add(importFile);

		ipImportList.add(ipImport);
	    }

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

	return ipImportList;
    }

    @Override
    public void writeIpExportFileSubmitToSftp(String filename, byte[] fileContent) throws WccException {
	try (InputStream is = new ByteArrayInputStream(fileContent)) {
	    writeIpExportFileSubmitToSftp(filename, is);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    @Override
    public void writeIpExportFileSubmitToSftp(String filename, String tempFilePath) throws WccException {
	try (InputStream fis = new FileInputStream(tempFilePath); InputStream bis = new BufferedInputStream(fis);) {
	    writeIpExportFileSubmitToSftp(filename, bis);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    private void writeIpExportFileSubmitToSftp(String filename, InputStream is) throws JSchException, SftpException {
	ExternalSite extSite = externalSiteDAO.selectExternalSiteByCode(ExternalSiteEnum.IP_SUBMISSION_SFTP);
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
	channel.cd(ipSubmitSftpPath);
	channel.put(is, filename, ChannelSftp.OVERWRITE);

	// disconnect channel and session
	channel.disconnect();
	session.disconnect();
    }

    @Override
    public void writeIpFullExportToSftp(String filename, String tempFilePath, String ipFullExportPath) throws WccException {
	try (InputStream fis = new FileInputStream(tempFilePath); InputStream bis = new BufferedInputStream(fis);) {
	    writeIpFullExportToSftp(filename, bis, ipFullExportPath);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    private void writeIpFullExportToSftp(String filename, InputStream is, String ipFullExportPath) throws JSchException, SftpException {
	ExternalSite extSite = externalSiteDAO.selectExternalSiteByCode(ExternalSiteEnum.IP_MASSIVE_EXPORT_SFTP);
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
	channel.cd(ipFullExportPath);
	channel.put(is, filename, ChannelSftp.OVERWRITE);

	// disconnect channel and session
	channel.disconnect();
	session.disconnect();
    }

}
