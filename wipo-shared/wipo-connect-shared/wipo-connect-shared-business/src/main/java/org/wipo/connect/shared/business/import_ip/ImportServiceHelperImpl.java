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

package org.wipo.connect.shared.business.import_ip;

import java.io.File;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.shared.exchange.dto.impl.IpImport;
import org.wipo.connect.shared.exchange.dto.impl.IpImportFile;
import org.wipo.connect.shared.exchange.enumerator.ImportFileTypeEnum;
import org.wipo.connect.shared.persistence.sftp.IpSftpClient;

/**
 * The Class CSVServiceHelper.
 *
 * @author pasquale.minervini
 */
@Service
@Qualifier(value = "csvServiceHelperImpl")
public class ImportServiceHelperImpl implements ImportServiceHelper {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(ImportServiceHelper.class);

    @Autowired
    private ImportIpDAOHelper importDAOHelperImpl;

    @Autowired
    private IpSftpClient ipSftpClient;

    @Value("#{configProperties['import.rightOwner.outputTag']}")
    private String ipImportOutputTag;

    /*
     * (non-Javadoc)
     * 
     * @see org.wipo.connect.shared.business.import_ip.ImportServiceHelper#saveOutputFile(org.wipo.connect.shared.exchange.dto.impl.IpImport, byte[])
     */
    @Override
    public void saveOutputFile(IpImport ipImport, byte[] fileContent) throws WccException {

	MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();

	String fileName = ipImport.getInputFile().getFileName();
	String extensionOutput = StringUtils.join(ipImportOutputTag, "." + FilenameUtils.getExtension(fileName));
	String outputFilename = StringUtils.replace(fileName, FilenameUtils.getExtension(fileName), extensionOutput);
	LOGGER.info("Saving the following {} outputFile on sftp client.", outputFilename);
	IpImportFile outFile = new IpImportFile();
	outFile.setContentType(mimeTypesMap.getContentType(outputFilename));
	outFile.setFileName(outputFilename);
	outFile.setFileSize(Long.valueOf(fileContent.length));
	outFile.setFileType(ImportFileTypeEnum.OUTPUT.name());
	outFile.setFkIpImport(ipImport.getId());

	ipSftpClient.writeIpImportResultFileToSftp(outputFilename, fileContent);
	importDAOHelperImpl.insertIpImportFile(outFile);

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.wipo.connect.shared.business.import_ip.ImportServiceHelper#saveOutputFile(org.wipo.connect.shared.exchange.dto.impl.IpImport, java.lang.String)
     */
    @Override
    public void saveOutputFile(IpImport ipImport, String tempFilePath) throws WccException {

	MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
	String fileName = ipImport.getInputFile().getFileName();
	String extensionOutput = StringUtils.join(ipImportOutputTag, "." + FilenameUtils.getExtension(fileName));
	String outputFilename = StringUtils.replace(fileName, FilenameUtils.getExtension(fileName), extensionOutput);

	IpImportFile outFile = new IpImportFile();
	outFile.setContentType(mimeTypesMap.getContentType(outputFilename));
	outFile.setFileName(outputFilename);
	outFile.setFileSize(new File(tempFilePath).length());
	outFile.setFileType(ImportFileTypeEnum.OUTPUT.name());
	outFile.setFkIpImport(ipImport.getId());

	ipSftpClient.writeIpImportResultFileToSftp(outputFilename, tempFilePath);
	importDAOHelperImpl.insertIpImportFile(outFile);

	try {
	    new File(tempFilePath).delete();
	} catch (Exception e) {
	    LOGGER.error("Error deleting temporary file {} ,", tempFilePath, e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.wipo.connect.shared.business.import_ip.ImportServiceHelper#readFileContent(java.lang.String)
     */
    @Override
    public byte[] readFileContent(String filename) throws WccException {
	return ipSftpClient.readIpImportFileFromSftp(filename);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.wipo.connect.shared.business.import_ip.ImportServiceHelper#getTempFile(java.lang.String)
     */
    @Override
    public String getTempFile(String filename) throws WccException {
	return ipSftpClient.getIpImportFileFromSftp(filename);
    }
}
