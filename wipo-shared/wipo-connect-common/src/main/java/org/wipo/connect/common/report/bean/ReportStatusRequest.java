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
package org.wipo.connect.common.report.bean;

/**
 * The Enum ReportStatusRequest.
 *
 * @author pasquale.minervini
 */
public enum ReportStatusRequest {

    /**
     * The to elaborate.
     */
    TO_ELABORATE("To elaborate"),

    /**
     * The in elaboration.
     */
    IN_ELABORATION("In elaboration"),

    /**
     * The completed.
     */
    COMPLETED("Request completed"),

    /**
     * The report generation error.
     */
    REPORT_GENERATION_ERROR("Error generating report"),

    /**
     * The generic error.
     */
    GENERIC_ERROR("Service unavailable"),

    /**
     * The parameter report error.
     */
    PARAMETER_REPORT_ERROR("Wrong report parameter"),

    PERMISSION_ERROR("Permission to generate Report are missing"),
    JASPER_ERROR("In Report generation, Jasper Templates have not been found"),
    RUNTIME_ERROR("A Runtime error occurred in the generation of Reports"),
    IO_ERROR("Impossible to write statement files");

    private final String description;

    private ReportStatusRequest(String descrizione) {
	this.description = descrizione;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
	return description;
    }

}
