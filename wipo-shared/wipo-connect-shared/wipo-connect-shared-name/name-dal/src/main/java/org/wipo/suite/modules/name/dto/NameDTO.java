package org.wipo.suite.modules.name.dto;

import org.springframework.stereotype.Component;

@Component
public class NameDTO {
	
	private String mainId;
	
	public NameDTO() {
		// Default constructor
	}

	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}	
	
	
	
}
