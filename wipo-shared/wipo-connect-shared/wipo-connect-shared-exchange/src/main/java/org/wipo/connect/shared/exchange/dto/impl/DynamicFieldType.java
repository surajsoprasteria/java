/*
 *
 */



package org.wipo.connect.shared.exchange.dto.impl;



import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Identifiable;



/**
 * The Class DynamicFieldType.
 */
public class DynamicFieldType implements Identifiable, Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7801682212969016793L;

    /** The id dynamic field type. */
    private Long idDynamicFieldType;

    /** The type description. */
    private String typeDescription;

    /** The code. */
    private String code;




    @Override
    public Long getId() {
        return getIdDynamicFieldType();
    }



    /**
     * Gets the id dynamic field type.
     *
     * @return the id dynamic field type
     */
    public Long getIdDynamicFieldType() {
        return this.idDynamicFieldType;
    }



    /**
     * Gets the type description.
     *
     * @return the type description
     */
    public String getTypeDescription() {
        return this.typeDescription;
    }



    @Override
    public void setId(Long id) {
        setIdDynamicFieldType(id);
    }



    /**
     * Sets the id dynamic field type.
     *
     * @param idDynamicFieldType
     *            the new id dynamic field type
     */
    public void setIdDynamicFieldType(Long idDynamicFieldType) {
        this.idDynamicFieldType = idDynamicFieldType;
    }



    /**
     * Sets the type description.
     *
     * @param typeDescription
     *            the new type description
     */
    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }



	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}



	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
