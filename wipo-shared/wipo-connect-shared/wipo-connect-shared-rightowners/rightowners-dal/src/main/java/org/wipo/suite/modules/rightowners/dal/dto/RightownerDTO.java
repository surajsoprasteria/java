package org.wipo.suite.modules.rightowners.dal.dto;

import org.springframework.stereotype.Component;

@Component
public class RightownerDTO {

	private String main_id;

	public RightownerDTO() {
		// Default constructor
	}

	public String getMain_id() {
		return main_id;
	}

	public void setMain_id(String main_id) {
		this.main_id = main_id;
	}

}
