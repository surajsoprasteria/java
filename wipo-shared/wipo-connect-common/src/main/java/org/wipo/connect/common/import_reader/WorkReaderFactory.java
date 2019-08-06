package org.wipo.connect.common.import_reader;

import java.io.IOException;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.wipo.connect.common.import_model.ImportFileTypeEnum;

public class WorkReaderFactory implements FactoryBean<WorkReader> {

	@Value("#{configProperties['work_import_file_type']}")
	private String work_import_file_type;

	@Override
	public WorkReader getObject() throws IOException {
		if (work_import_file_type.equalsIgnoreCase(ImportFileTypeEnum.CSV.name())) {
			return new WorkReaderCsv();
		}
		return new WorkReaderExcel();
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
