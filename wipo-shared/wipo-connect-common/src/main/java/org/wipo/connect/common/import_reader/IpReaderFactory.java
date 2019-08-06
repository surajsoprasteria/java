package org.wipo.connect.common.import_reader;

import java.io.IOException;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.wipo.connect.common.import_model.ImportFileTypeEnum;

public class IpReaderFactory implements FactoryBean<IpReader> {

	@Value("#{configProperties['ip_import_file_type']}")
	private String ip_import_file_type;

	@Override
	public IpReader getObject() throws IOException  {
		if (ip_import_file_type.equalsIgnoreCase(ImportFileTypeEnum.CSV.name())) {
			return new IpReaderCsv();
		}
		return new IpReaderExcel();
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
