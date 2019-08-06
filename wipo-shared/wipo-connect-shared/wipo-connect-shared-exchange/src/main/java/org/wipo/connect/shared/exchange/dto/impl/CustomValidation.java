/*
 *
 */

package org.wipo.connect.shared.exchange.dto.impl;

import java.io.Serializable;
import java.math.BigDecimal;

import org.wipo.connect.common.customvalidation.ICustomValidation;
import org.wipo.connect.common.dto.Identifiable;

/**
 * The Class CustomValidation.
 */
public class CustomValidation implements Identifiable, Serializable, ICustomValidation {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 4525585943228161214L;

    /** The id custom validation. */
    private Long idCustomValidation;

    /** The section code. */
    private String sectionCode;

    /** The field code. */
    private String fieldCode;

    /** The mandatory. */
    private Boolean mandatory;

    /** The regular expression. */
    private String regularExpression;

    /** The max length. */
    private Integer maxLength;

    /** The max value. */
    private BigDecimal maxValue;

    /** The min value. */
    private BigDecimal minValue;

    /** The max file size. */
    private Long maxFileSize;

    /** The fk field type. */
    private Long fkFieldType;

    /** The field type code. */
    private String fieldTypeCode;

    /** The international. */
    private Boolean international;

    /** The forced. */
    private Boolean forced;

    @Override
    public Long getId() {
	return getIdCustomValidation();
    }

    @Override
    public void setId(Long id) {
	setIdCustomValidation(id);
    }

    /**
     * Gets the id custom validation.
     *
     * @return the id custom validation
     */
    public Long getIdCustomValidation() {
	return idCustomValidation;
    }

    /**
     * Sets the id custom validation.
     *
     * @param idCustomValidation
     *            the new id custom validation
     */
    public void setIdCustomValidation(Long idCustomValidation) {
	this.idCustomValidation = idCustomValidation;
    }

    /**
     * Gets the field code.
     *
     * @return the field code
     */
    public String getFieldCode() {
	return fieldCode;
    }

    /**
     * Sets the field code.
     *
     * @param fieldCode
     *            the new field code
     */
    public void setFieldCode(String fieldCode) {
	this.fieldCode = fieldCode;
    }

    @Override
    public Boolean getMandatory() {
	return mandatory;
    }

    /**
     * Sets the mandatory.
     *
     * @param mandatory
     *            the new mandatory
     */
    public void setMandatory(Boolean mandatory) {
	this.mandatory = mandatory;
    }

    @Override
    public String getRegularExpression() {
	return regularExpression;
    }

    /**
     * Sets the regular expression.
     *
     * @param regularExpression
     *            the new regular expression
     */
    public void setRegularExpression(String regularExpression) {
	this.regularExpression = regularExpression;
    }

    @Override
    public Integer getMaxLength() {
	return maxLength;
    }

    /**
     * Sets the max length.
     *
     * @param maxLength
     *            the new max length
     */
    public void setMaxLength(Integer maxLength) {
	this.maxLength = maxLength;
    }

    @Override
    public BigDecimal getMaxValue() {
	return maxValue;
    }

    /**
     * Sets the max value.
     *
     * @param maxValue
     *            the new max value
     */
    public void setMaxValue(BigDecimal maxValue) {
	this.maxValue = maxValue;
    }

    @Override
    public BigDecimal getMinValue() {
	return minValue;
    }

    /**
     * Sets the min value.
     *
     * @param minValue
     *            the new min value
     */
    public void setMinValue(BigDecimal minValue) {
	this.minValue = minValue;
    }

    /**
     * Gets the fk field type.
     *
     * @return the fk field type
     */
    public Long getFkFieldType() {
	return fkFieldType;
    }

    /**
     * Sets the fk field type.
     *
     * @param fkFieldType
     *            the new fk field type
     */
    public void setFkFieldType(Long fkFieldType) {
	this.fkFieldType = fkFieldType;
    }

    /**
     * Gets the field type code.
     *
     * @return the field type code
     */
    public String getFieldTypeCode() {
	return fieldTypeCode;
    }

    /**
     * Sets the field type code.
     *
     * @param fieldTypeCode
     *            the new field type code
     */
    public void setFieldTypeCode(String fieldTypeCode) {
	this.fieldTypeCode = fieldTypeCode;
    }

    @Override
    public Boolean getInternational() {
	return international;
    }

    /**
     * Sets the international.
     *
     * @param international
     *            the new international
     */
    public void setInternational(Boolean international) {
	this.international = international;
    }

    @Override
    public Boolean getForced() {
	return forced;
    }

    /**
     * Sets the forced.
     *
     * @param forced
     *            the new forced
     */
    public void setForced(Boolean forced) {
	this.forced = forced;
    }

    @Override
    public String getCode() {
	return getFieldCode();
    }

    @Override
    public String getType() {
	return getFieldTypeCode();
    }

    @Override
    public Long getMaxFileSize() {
	return maxFileSize;
    }

    /**
     * Sets the max file size.
     *
     * @param maxFileSize
     *            the new max file size
     */
    public void setMaxFileSize(Long maxFileSize) {
	this.maxFileSize = maxFileSize;
    }

    /**
     * Gets the section code.
     *
     * @return the section code
     */
    public String getSectionCode() {
	return sectionCode;
    }

    /**
     * Sets the section code.
     *
     * @param sectionCode
     *            the new section code
     */
    public void setSectionCode(String sectionCode) {
	this.sectionCode = sectionCode;
    }

    @Override
    public String getPossibleValues() {
	return null;
    }

}
