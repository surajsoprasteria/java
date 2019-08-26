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



package org.wipo.connect.shared.identifierprocessor.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * The Class ImportStatusFlat.
 */
@Entity
@Table(name="import_status")
public class ImportStatusFlat {

    /** The id import status. */
	@Id
	@Column(name="ID_IMPORT_STATUS")
    private Long idImportStatus;

    /** The value. */
	@Column(name="default_value")
    private String value;

    /** The code. */
    private String code;

    /** The sort order. */
    @Column(name="SORT_ORDER")
    private Integer sortOrder;

	public ImportStatusFlat() {
		super();
	}

	public Long getIdImportStatus() {
		return idImportStatus;
	}

	public void setIdImportStatus(Long idImportStatus) {
		this.idImportStatus = idImportStatus;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}



    
}
