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
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.wipo.connect.shared.exchange.dto.impl.WorkImport;
import org.wipo.connect.shared.exchange.dto.impl.WorkImportFile;
import org.wipo.connect.shared.exchange.enumerator.ExternalSiteEnum;
import org.wipo.connect.shared.persistence.dao.ExternalSiteDAO;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * The Class WorkSftpClientImpl.
 */
@Service
public class WorkSftpClientImpl implements WorkSftpClient {

    @Value("#{configProperties['path.work.sftpSubmit-dir']}")
    private String workSubmitSftpPath;

    // work import
    @Value("#{configProperties['path.work.sftpImport-dir']}")
    private String workImportSftpPath;

    @Value("#{configProperties['import.work.outputTag']}")
    private String workImportOutputTag;

    @Autowired
    private ExternalSiteDAO externalSiteDAO;

    @Value("#{configProperties['path.temp-dir']}")
    private String importTempDir;

    @Override
    public List<WorkImport> getWorkImportListFromSftp() throws WccException {
	List<WorkImport> workImportList = new ArrayList<>();

	try {
	    MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
	    ExternalSite extSite = externalSiteDAO.selectExternalSiteByCode(ExternalSiteEnum.WORK_IMPORT_SFTP);
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
	    channel.cd(workImportSftpPath);
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

	    // loop and generate workImport list
	    for (LsEntry entry : fileList) {
		if (StringUtils.endsWithIgnoreCase(entry.getFilename(), workImportOutputTag + "." + ImportFileTypeEnum.CSV.getExtension())
			|| StringUtils.endsWithIgnoreCase(entry.getFilename(), workImportOutputTag + "." + ImportFileTypeEnum.EXCEL.getExtension())) {
		    continue;
		}

		WorkImportFile importFile = new WorkImportFile();
		importFile.setFileType(org.wipo.connect.shared.exchange.enumerator.ImportFileTypeEnum.INPUT.name());
		importFile.setFileName(entry.getFilename());
		importFile.setFileSize(entry.getAttrs().getSize());
		importFile.setContentType(mimeTypesMap.getContentType(entry.getFilename()));
		WorkImport workImport = new WorkImport();
		workImport.getWorkImportFileList().add(importFile);

		workImportList.add(workImport);
	    }

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

	return workImportList;
    }

    @Override
    public byte[] readWorkImportFileFromSftp(String filename) throws WccException {

	byte[] fileContent = null;

	try {
	    ExternalSite extSite = externalSiteDAO.selectExternalSiteByCode(ExternalSiteEnum.WORK_IMPORT_SFTP);
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
	    channel.cd(workImportSftpPath);
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
    public String getWorkImportFileFromSftp(String filename) throws WccException {
	SimpleDateFormat sdf = new SimpleDateFormat(ConversionUtils.DATE_TIME_STAMP_MILLI);
	String tmpFilename = importTempDir + "/wrk.in." + sdf.format(new Date()) + "." + FilenameUtils.getExtension(filename);

	try (OutputStream fos = new FileOutputStream(tmpFilename); OutputStream bos = new BufferedOutputStream(fos);) {

	    ExternalSite extSite = externalSiteDAO.selectExternalSiteByCode(ExternalSiteEnum.WORK_IMPORT_SFTP);
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
	    channel.cd(workImportSftpPath);
	    InputStream inputStream = channel.get(filename);
	    IOUtils.copyLarge(inputStream, bos);
	    bos.flush();

	    // disconnect channel and session
	    inputStream.close();
	    channel.disconnect();
	    session.disconnect();

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

	return tmpFilename;
    }

    @Override
    public void writeWorkImportResultFileToSftp(String filename, byte[] fileContent) throws WccException {
	try (InputStream is = new ByteArrayInputStream(fileContent)) {
	    writeWorkImportResultFileToSftp(filename, is);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    @Override
    public void writeWorkImportResultFileToSftp(String filename, String tempFilePath) throws WccException {
	try (InputStream fis = new FileInputStream(tempFilePath); InputStream bis = new BufferedInputStream(fis);) {
	    writeWorkImportResultFileToSftp(filename, bis);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    private void writeWorkImportResultFileToSftp(String filename, InputStream is) throws JSchException, SftpException {
	ExternalSite extSite = externalSiteDAO.selectExternalSiteByCode(ExternalSiteEnum.WORK_IMPORT_SFTP);
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
	channel.cd(workImportSftpPath);
	channel.put(is, filename, ChannelSftp.OVERWRITE);

	// disconnect channel and session
	channel.disconnect();
	session.disconnect();
    }

    @Override
    public void writeWorkExportFileToSftp(String filename, byte[] fileContent) throws WccException {
	try (InputStream is = new ByteArrayInputStream(fileContent)) {
	    writeWorkExportFileToSftp(filename, is);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    @Override
    public void writeWorkExportFileToSftp(String filename, String tempFilePath) throws WccException {
	try (InputStream fis = new FileInputStream(tempFilePath); InputStream bis = new BufferedInputStream(fis);) {
	    writeWorkExportFileToSftp(filename, bis);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    private void writeWorkExportFileToSftp(String filename, InputStream is) throws JSchException, SftpException {
	ExternalSite extSite = externalSiteDAO.selectExternalSiteByCode(ExternalSiteEnum.WORK_SUBMISSION_SFTP);
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
	channel.cd(workSubmitSftpPath);
	channel.put(is, filename, ChannelSftp.OVERWRITE);

	// disconnect channel and session
	channel.disconnect();
	session.disconnect();
    }

    @Override
    public void writeWorkFullExportFileToSftp(String filename, String tempFilePath, String ipFullExportPath) throws WccException {
	try (InputStream fis = new FileInputStream(tempFilePath); InputStream bis = new BufferedInputStream(fis);) {
	    writeWorkFullExportFileToSftp(filename, bis, ipFullExportPath);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    private void writeWorkFullExportFileToSftp(String filename, InputStream is, String ipFullExportPath) throws JSchException, SftpException {
	ExternalSite extSite = externalSiteDAO.selectExternalSiteByCode(ExternalSiteEnum.WORK_MASSIVE_EXPORT_SFTP);
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
