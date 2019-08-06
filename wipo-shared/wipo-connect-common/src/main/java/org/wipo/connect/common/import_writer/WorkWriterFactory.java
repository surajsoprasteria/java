package org.wipo.connect.common.import_writer;

import java.io.IOException;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.wipo.connect.common.import_model.EnvironmentEnum;
import org.wipo.connect.common.import_model.ImportFileTypeEnum;
import org.wipo.connect.common.import_reader.IpReader;

@SuppressWarnings("rawtypes")
public class WorkWriterFactory implements FactoryBean<WorkWriter> {

    @Value("#{configProperties['work_import_file_type']}")
    private String work_import_file_type;
    @Value("#{configProperties['env']}")
    private EnvironmentEnum env;
    @Value("#{configProperties['path.temp-dir']}")
    private String importTempDir;

    @Override
    public WorkWriter getObject() throws IOException {
	if (work_import_file_type.equalsIgnoreCase(ImportFileTypeEnum.CSV.name())) {
	    return new WorkWriterCsv(importTempDir, env, true);
	}
	return new WorkWriterExcel(importTempDir, env, true);
    }

    @Override
    public Class<IpReader> getObjectType() {
	return IpReader.class;
    }

    @Override
    public boolean isSingleton() {
	return false;
    }
}
