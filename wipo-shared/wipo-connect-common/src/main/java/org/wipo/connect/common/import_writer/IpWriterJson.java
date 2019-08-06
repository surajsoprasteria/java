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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.import_model.EnvironmentEnum;
import org.wipo.connect.common.import_model.ImportFileTypeEnum;
import org.wipo.connect.common.import_model.IpRowJson;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.ConversionUtils;
import org.wipo.connect.common.utils.WccUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SequenceWriter;

public class IpWriterJson implements IpWriter<List<IpRowJson>> {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(IpRowJson.class);

    private static ImportFileTypeEnum importFileTypeEnum = ImportFileTypeEnum.CSV;
    private final ObjectWriter jsonWriter;
    private final EnvironmentEnum env;
    private final String fileName;
    private final boolean isLogWriter;
    private final SequenceWriter sequenceWriter;

    public IpWriterJson(String fileName, EnvironmentEnum env, boolean isLogWriter) throws IOException {
	this.env = env;
	if (this.env.compareTo(EnvironmentEnum.LOCAL) == 0 && isLogWriter) {
	    String extensionOutput = StringUtils.join(SUFFIX_LOCAL, "." + FilenameUtils.getExtension(fileName));
	    this.fileName = StringUtils.replace(fileName, FilenameUtils.getExtension(fileName), extensionOutput);
	} else {
	    SimpleDateFormat sdf = new SimpleDateFormat(ConversionUtils.DATE_TIME_STAMP_MILLI);
	    this.fileName = fileName + "/" + SUFFIX_INTER + "." + sdf.format(new Date()) + ".json";
	}
	this.isLogWriter = isLogWriter;
	ObjectMapper objectMapper = new ObjectMapper();
	jsonWriter = objectMapper.writer(new DefaultPrettyPrinter());
	FileWriter fileWriter = new FileWriter(this.fileName);
	BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	sequenceWriter = jsonWriter.writeValuesAsArray(bufferedWriter);
    }

    @Override
    public void writeRows(List<IpRowJson> ipRows) throws WccException {
	if (isLogWriter) {
	    // TODO skyp log writer fields
	}
	try {

	    sequenceWriter.writeAll(ipRows);

	} catch (JsonGenerationException e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName() + " : " + e.getMessage());
	    throw new WccException(e);
	} catch (JsonMappingException e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName() + " : " + e.getMessage());
	    throw new WccException(e);
	} catch (IOException e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName() + " : " + e.getMessage());
	    throw new WccException(e);
	}

    }

    @Override
    public void flush() throws IOException {
	sequenceWriter.flush();
    }

    @Override
    public void closeStream() throws IOException {
	sequenceWriter.close();
    }

    @Override
    public String getFileName() {
	return fileName;
    }

    @Override
    public ImportFileTypeEnum getImportFileTypeEnum() {
	return importFileTypeEnum;
    }

}
