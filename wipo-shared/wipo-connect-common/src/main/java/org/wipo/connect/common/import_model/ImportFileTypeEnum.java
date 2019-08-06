package org.wipo.connect.common.import_model;

public enum ImportFileTypeEnum {

	CSV("csv"),
	EXCEL("xlsx"),
	JSON("json"),
	XML("xml");
	
	private final String extension;
	
	private ImportFileTypeEnum(String extension){
		this.extension=extension;
	}
	
	public String getExtension(){
		return this.extension;
	}
}
