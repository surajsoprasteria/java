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
package org.wipo.connect.shared.business.import_work;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.vo.PairVO;
import org.wipo.connect.shared.exchange.business.WorkBusiness;
import org.wipo.connect.shared.exchange.dto.impl.DerivedWork;
import org.wipo.connect.shared.exchange.dto.impl.Identifier;
import org.wipo.connect.shared.exchange.dto.impl.Name;
import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.dto.impl.WorkImport;
import org.wipo.connect.shared.exchange.dto.impl.WorkImportDetail;
import org.wipo.connect.shared.exchange.dto.impl.WorkImportFile;
import org.wipo.connect.shared.exchange.enumerator.ImportFileTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.ImportStatusEnum;
import org.wipo.connect.shared.exchange.utils.DerivedWorkUtils;
import org.wipo.connect.shared.persistence.dao.DerivedWorkDAO;
import org.wipo.connect.shared.persistence.dao.IdentifierDAO;
import org.wipo.connect.shared.persistence.dao.NameDAO;
import org.wipo.connect.shared.persistence.dao.WorkDAO;
import org.wipo.connect.shared.persistence.dao.WorkIdentifierFlatDAO;
import org.wipo.connect.shared.persistence.dao.WorkImportDAO;
import org.wipo.connect.shared.persistence.dao.WorkImportDetailDAO;
import org.wipo.connect.shared.persistence.sftp.WorkSftpClient;

import net.bull.javamelody.MonitoredWithSpring;

@Service
@MonitoredWithSpring
public class ImportWorkDAOHelperImpl implements ImportWorkDAOHelper {
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(ImportWorkDAOHelperImpl.class);

    @Autowired
    private WorkBusiness workBusinessImpl;
    @Autowired
    private WorkImportDAO workImportDAOImpl;
    @Autowired
    private WorkImportDetailDAO workImportDetailDAOImpl;
    @Autowired
    private WorkDAO workDAOImpl;
    @Autowired
    private WorkIdentifierFlatDAO workIdentifierFlatDAOImpl;
    @Autowired
    private IdentifierDAO identifierDAOImpl;
    @Autowired
    private NameDAO nameDAOImpl;

    @Autowired
    private WorkSftpClient workSftpClient;

    @Value("#{configProperties['import.work.outputTag']}")
    private String workImportOutputTag;

    @Autowired
    private DerivedWorkDAO derivedWorkDAOImpl;

    @Override
    public Work insertOrUpdate(Work work) throws WccException {
	return workBusinessImpl.insertOrUpdate(work);
    }

    @Override
    public void executeDelete(String mainId) throws WccException {
	Long idWork = findWorkIdByMainId(mainId);
	this.workBusinessImpl.logicallyDeleteWork(idWork);
    }

    @Override
    public int updateWorkImportFromStatus(Long idImport, ImportStatusEnum status) {
	int i = workImportDAOImpl.updateStatus(idImport, status);
	if (status.equals(ImportStatusEnum.IN_PROGRESS)) {
	    i = workImportDAOImpl.updateImportStartDate(idImport, new Date());
	}
	if (status.equals(ImportStatusEnum.ERROR) || status.equals(ImportStatusEnum.COMPLETED)) {
	    i = workImportDAOImpl.updateImportEndDate(idImport, new Date());
	}
	return i;
    }

    @Override
    public void saveOutputFile(WorkImport workImport, byte[] fileContent) throws WccException {

	MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();

	String fileName = workImport.getInputFile().getFileName();
	String extensionOutput = StringUtils.join(workImportOutputTag, "." + FilenameUtils.getExtension(fileName));
	String outputFilename = StringUtils.replace(fileName, FilenameUtils.getExtension(fileName), extensionOutput);

	WorkImportFile outFile = new WorkImportFile();
	outFile.setContentType(mimeTypesMap.getContentType(outputFilename));
	outFile.setFileName(outputFilename);
	outFile.setFileSize(Long.valueOf(fileContent.length));
	outFile.setFileType(ImportFileTypeEnum.OUTPUT.name());
	outFile.setFkWorkImport(workImport.getId());

	workSftpClient.writeWorkImportResultFileToSftp(outputFilename, fileContent);
	workImportDAOImpl.insertWorkImportFile(outFile);

    }

    @Override
    public List<WorkImport> findWorkImportFromStatus(ImportStatusEnum... status) {
	LOGGER.debug("Loading Work import in {} status", Arrays.toString(status));
	return workImportDAOImpl.findWorkImportFromStatus(status);
    }

    @Override
    public void saveOutputFile(WorkImport workImport, String tempFilePath) throws WccException {

	MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
	String fileName = workImport.getInputFile().getFileName();
	String extensionOutput = StringUtils.join(workImportOutputTag, "." + FilenameUtils.getExtension(fileName));
	String outputFilename = StringUtils.replace(fileName, FilenameUtils.getExtension(fileName), extensionOutput);

	WorkImportFile outFile = new WorkImportFile();
	outFile.setContentType(mimeTypesMap.getContentType(outputFilename));
	outFile.setFileName(outputFilename);
	outFile.setFileSize(new File(tempFilePath).length());
	outFile.setFileType(ImportFileTypeEnum.OUTPUT.name());
	outFile.setFkWorkImport(workImport.getId());

	workSftpClient.writeWorkImportResultFileToSftp(outputFilename, tempFilePath);
	workImportDAOImpl.insertWorkImportFile(outFile);

	try {
	    new File(tempFilePath).delete();
	} catch (Exception e) {
	    LOGGER.error("Error deleting temporary file {} ,", tempFilePath, e);
	}
    }

    @Override
    public Long findWorkIdByIdentifier(String identifierValue, String identifierType) {
	return workDAOImpl.findWorkIdByIdentifier(identifierValue, identifierType);
    }

    @Override
    public Long findWorkIdByMainId(String mainId) {
	if (StringUtils.isEmpty(mainId)) {
	    return null;
	}
	return workDAOImpl.findWorkIdByMainId(mainId, true);
    }

    @Override
    public Long loadIdIdentifier(String code) {
	Identifier identifier = identifierDAOImpl.findByCode(code);
	if (identifier != null) {
	    return identifier.getId();
	}
	return null;
    }

    @Override
    public Boolean checkMainIdIsPresent(String mainId) {
	if (StringUtils.isEmpty(mainId)) {
	    return false;
	}
	if (workDAOImpl.findWorkIdByMainId(mainId, true) != null) {
	    return true;
	}
	return false;
    }

    @Override
    public Work findByMainId(String mainId, boolean loadDetail) {
	if (StringUtils.isEmpty(mainId)) {
	    return null;
	}
	return workDAOImpl.findByMainId(mainId, loadDetail);
    }

    @Override
    public Name findByNameMainId(String nameMainId) {
	Name name = null;
	List<Name> nameList = nameDAOImpl.findByNameMainId(nameMainId);
	if (nameList.size() == 1) {
	    name = new Name();
	    name = nameList.get(0);
	}
	return name;
    }

    @Override
    public boolean identifierValueAlreadyPresent(String code, String value, Long idWork) {
	return workIdentifierFlatDAOImpl.identifierValueAlreadyPresent(code, value, idWork);
    }

    @Override
    public int insertWorkImportFile(final WorkImportFile workImportFile) throws WccException {
	int i;
	try {
	    i = workImportDAOImpl.insertWorkImportFile(workImportFile);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return i;
    }

    @Override
    @Deprecated
    public Long insertWorkImportDetail(final WorkImportDetail workImportDetail) throws WccException {
	try {
	    return workImportDetailDAOImpl.insertWorkImportDetail(workImportDetail);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    @Override
    public Boolean checkWorkIsComponent(String mainId) {
	return derivedWorkDAOImpl.checkWorkIsComponentByMainId(mainId);
    }

    @Override
    public void updateRowResult(WorkImport workImport) {
	workImportDAOImpl.updateRowResult(workImport);
    }

    @Override
    public void markAllPendingAsError() {
	workImportDAOImpl.markAllPendingAsError();
    }

    @Override
    public String findCreationClassByWorkMainId(String mainId) {
	return workDAOImpl.findCreationClassByMainId(mainId);
    }

    @Override
    public boolean checkDerivedWorkCycle(Long parentId, Long childrenId) {
	DerivedWork newDerivedWork = new DerivedWork();
	newDerivedWork.setFkParentWork(parentId);
	newDerivedWork.setFkWork(childrenId);

	List<DerivedWork> dwChain = derivedWorkDAOImpl.findAllChildrenChain(childrenId);

	dwChain.removeIf(dw -> dw.getFkParentWork().equals(parentId));
	dwChain.add(newDerivedWork);

	Set<PairVO<Long, String>> dwCycles = DerivedWorkUtils.detectCycle(dwChain);
	if (!dwCycles.isEmpty()) {
	    return true;
	}
	return false;
    }

}
