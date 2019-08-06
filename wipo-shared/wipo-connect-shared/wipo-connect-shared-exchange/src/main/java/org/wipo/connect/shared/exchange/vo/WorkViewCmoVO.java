package org.wipo.connect.shared.exchange.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Identifiable;

public class WorkViewCmoVO implements Serializable, Identifiable {

    private static final long serialVersionUID = 5970463049189536283L;

    private Long id;
    private String acronym;
    private String code;
    private String name;

    public String getAcronym() {
	return acronym;
    }

    public void setAcronym(String acronym) {
	this.acronym = acronym;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Override
    public Long getId() {
	return id;
    }

    @Override
    public void setId(Long id) {
	this.id = id;
    }

    @Override
    public String toString() {
	return ReflectionToStringBuilder.toString(this);
    }
}
