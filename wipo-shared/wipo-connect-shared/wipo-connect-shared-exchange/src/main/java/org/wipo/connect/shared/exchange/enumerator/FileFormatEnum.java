package org.wipo.connect.shared.exchange.enumerator;

public enum FileFormatEnum {
	EXCEL("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
	CSV("csv", "text/csv");
	
	private String fileExtention;
	private String contentType;
	
	private FileFormatEnum(String fileExtention, String contentType){
		this.fileExtention = fileExtention;
		this.contentType = contentType;
	}

	public String getFileExtention() {
		return fileExtention;
	}

	public void setFileExtention(String fileExtention) {
		this.fileExtention = fileExtention;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	
}
