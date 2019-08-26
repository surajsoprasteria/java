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

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.import_model.ImportFileTypeEnum;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * The Class IpSftpClientImpl.
 */
@Service
@PropertySource("classpath:application.properties")
public class IpSftpClientImpl implements IpSftpClient {

    @Value("${path.rightOwner.sftpImport-dir}")
    private String ipImportSftpPath;

    @Value("${import.rightOwner.outputTag}")
    private String ipImportOutputTag;

    @Value("${path.temp-dir}")
    private String importTempDir;

    @Value("${path.rightOwner.sftpSubmit-dir}")
    private String ipSubmitSftpPath;

  /*  @Autowired
    private ExternalSiteDAO externalSiteDAO;*/

    

    public List<IpImport> getIpImportListFromSftp() throws WccException {
	List<IpImport> ipImportList = new ArrayList<org.wipo.connect.shared.identifierprocessor.temp.IpImport>();

	try {
	    MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
	    //ExternalSite extSite = externalSiteDAO.selectExternalSiteByCode(ExternalSiteEnum.IP_IMPORT_SFTP);   ExternalSiteMapper.xml  >>>  selectExternalSiteByCode 
	    System.out.println("A::");
	    ExternalSite extSite = new ExternalSite(); //hardcode 
	    
	    extSite.setPassword("Urwipoconnect@abc");	
	    
	    extSite.setUser("surasing");
	    
	    extSite.setHost("localhost");
	   
	    extSite.setPort(22);
	    
	    // configure sftp session
	    JSch jsch = new JSch();
	    System.out.println("B::");
	    Session session = jsch.getSession(extSite.getUser(), extSite.getHost(), extSite.getPort());
	    session.setPassword(extSite.getPassword());
	    session.setConfig("StrictHostKeyChecking", "no");
	    session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
	    session.connect();
	    System.out.println("C::");
	    // open sftp channel
	    ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
	    channel.connect();

	    // query the channel to get the file list
	    System.out.println("ipImportSftpPath" +ipImportSftpPath);
	    channel.cd(ipImportSftpPath);
	    @SuppressWarnings("unchecked")
	    Vector<ChannelSftp.LsEntry> fileCsvList = channel.ls("*." + ImportFileTypeEnum.CSV.getExtension());
	    System.out.println("D::");
	    @SuppressWarnings("unchecked")
	    Vector<ChannelSftp.LsEntry> fileExcelList = channel.ls("*." + ImportFileTypeEnum.EXCEL.getExtension());
	    System.out.println("E::");

	    Vector<ChannelSftp.LsEntry> fileList = new Vector<LsEntry>();
	    fileList.addAll(fileCsvList);
	    fileList.addAll(fileExcelList);

	    // disconnect channel and session
	    channel.disconnect();
	    session.disconnect();
	    System.out.println("F::");
	    // loop and generate ipImport list
	    for (LsEntry entry : fileList) {
		if (StringUtils.endsWithIgnoreCase(entry.getFilename(), ipImportOutputTag + "." + ImportFileTypeEnum.CSV.getExtension())
			|| StringUtils.endsWithIgnoreCase(entry.getFilename(), ipImportOutputTag + "." + ImportFileTypeEnum.EXCEL.getExtension())) {
		    continue;
		}

		IpImportFile importFile = new IpImportFile();
		importFile.setFileType(org.wipo.connect.shared.identifierprocessor.temp.ImportFileTypeEnum.INPUT.name());
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


}
