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



package org.wipo.connect.shared.exchange.dto.impl;



import java.io.Serializable;
import java.util.Locale;

import org.apache.commons.lang3.LocaleUtils;
import org.wipo.connect.common.dto.Identifiable;



/**
 * The Class Account.
 *
 * @author fincons
 */
public class UiLanguage implements Identifiable, Serializable {

    private static final long serialVersionUID = 7950367577045127518L;

    private Long idUiLanguage;
    private Long fkLanguage;
    private String languageCode;
    private String languageDescription;



    @Override
    public Long getId() {
        return getIdUiLanguage();
    }



    @Override
    public void setId(Long id) {
        setIdUiLanguage(id);
    }



    /**
     * Gets the id ui language.
     *
     * @return the id ui language
     */
    public Long getIdUiLanguage() {
        return idUiLanguage;
    }



    /**
     * Sets the id ui language.
     *
     * @param idUiLanguage the new id ui language
     */
    public void setIdUiLanguage(Long idUiLanguage) {
        this.idUiLanguage = idUiLanguage;
    }



    /**
     * Gets the fk language.
     *
     * @return the fk language
     */
    public Long getFkLanguage() {
        return fkLanguage;
    }



    /**
     * Sets the fk language.
     *
     * @param fkLanguage the new fk language
     */
    public void setFkLanguage(Long fkLanguage) {
        this.fkLanguage = fkLanguage;
    }



    /**
     * Gets the language code.
     *
     * @return the language code
     */
    public String getLanguageCode() {
        return languageCode;
    }



    /**
     * Sets the language code.
     *
     * @param languageCode the new language code
     */
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }



    /**
     * Gets the language description.
     *
     * @return the language description
     */
    public String getLanguageDescription() {
        return languageDescription;
    }



    /**
     * Sets the language description.
     *
     * @param languageDescription the new language description
     */
    public void setLanguageDescription(String languageDescription) {
        this.languageDescription = languageDescription;
    }

    /**
     * Gets the ui locale.
     *
     * @return the ui locale
     */
    public Locale getUiLocale(){
        return LocaleUtils.toLocale(languageCode);
    }

}
