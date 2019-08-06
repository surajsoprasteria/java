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
package org.wipo.connect.common.import_writer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.import_model.EnvironmentEnum;
import org.wipo.connect.common.import_model.ImportFileTypeEnum;
import org.wipo.connect.common.import_model.WorkRow;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.ConversionUtils;
import org.wipo.connect.common.utils.WccUtils;

public class WorkWriterXml implements WorkWriter<List<WorkRow>> {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(WorkWriterXml.class);

    private static ImportFileTypeEnum importFileTypeEnum = ImportFileTypeEnum.CSV;
    private Marshaller xmlWriter;
    private EnvironmentEnum env;
    private String fileName;
    private final boolean isLogWriter;

    public WorkWriterXml(String importTempDir, EnvironmentEnum env, boolean isLogWriter) throws IOException, JAXBException {
	this.env = env;
	if (this.env.compareTo(EnvironmentEnum.LOCAL) == 0 && isLogWriter) {
	    String extensionOutput = StringUtils.join(SUFFIX_LOCAL, "." + FilenameUtils.getExtension(importTempDir));
	    this.fileName = StringUtils.replace(importTempDir, FilenameUtils.getExtension(importTempDir), extensionOutput);
	} else {
	    SimpleDateFormat sdf = new SimpleDateFormat(ConversionUtils.DATE_TIME_STAMP_MILLI);
	    this.fileName = importTempDir + "/" + SUFFIX_INTER + "." + sdf.format(new Date()) + ".xml";
	}
	this.isLogWriter = isLogWriter;
	JAXBContext jaxbContext = JAXBContext.newInstance(JaxbRootNodeWork.class);
	xmlWriter = jaxbContext.createMarshaller();

	// output pretty printed
	xmlWriter.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

    }

    @Override
    public void writeRows(List<WorkRow> workRows) throws WccException {
	if (isLogWriter) {
	    // TODO skyp log writer fields
	}
	JaxbRootNodeWork worksRoot = new JaxbRootNodeWork(workRows);
	try {
	    xmlWriter.marshal(worksRoot, new File(this.fileName));
	} catch (JAXBException e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName() + " : " + e.getMessage());
	    throw new WccException(e);
	}

    }

    @Override
    public void closeStream() {
	throw new UnsupportedOperationException();
    }

    @Override
    public String getFileName() {
	return fileName;
    }

    @Override
    public void flush() throws IOException {

    }

    @Override
    public ImportFileTypeEnum getImportFileTypeEnum() {
	return importFileTypeEnum;
    }

    @XmlRootElement
    @SuppressWarnings("unused")
    private static class JaxbRootNodeWork {
	private List<WorkRow> workRows;

	public JaxbRootNodeWork() {
	}

	public JaxbRootNodeWork(List<WorkRow> workRows) {
	    this.setWorkRows(workRows);
	}

	public List<WorkRow> getWorkRows() {
	    return workRows;
	}

	public void setWorkRows(List<WorkRow> workRows) {
	    this.workRows = workRows;
	}

    }
}
