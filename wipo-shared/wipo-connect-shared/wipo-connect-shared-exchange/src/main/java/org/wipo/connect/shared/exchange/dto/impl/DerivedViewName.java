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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.customvalidation.CustomValidatedField;
import org.wipo.connect.common.dto.Deletable;
import org.wipo.connect.common.dto.Identifiable;



/**
 * The Class DerivedViewName.
 */
public class DerivedViewName implements Identifiable, Serializable, Deletable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7640032482133687163L;

    /** The id derived view name. */
    private Long idDerivedViewName;

    /** The fk derived view. */
    private Long fkDerivedView;

    /** The name. */
    private Name name;

    /** The fk role. */
    @CustomValidatedField(fieldCode = "VIEW_ROLE")
    private Long fkRole;

    /** The role code. */
    private String roleCode;

    /** The creator ref index. */
    @CustomValidatedField(fieldCode = "VIEW_CREATOR")
    private Long creatorRefIndex;

    /** The derived view name share list. */
    @CustomValidatedField(innerValidation = true)
    private List<DerivedViewNameShare> derivedViewNameShareList;

    /** The exec delete. */
    private Boolean execDelete = false;

    /** The ref index. */
    private Long refIndex;

    /** The source type. */
    private String sourceType;

    /** The fk source type. */
    private Long fkSourceType;

    /** The souceType code. */
    private String sourceTypeCode;

    private String creatorRefMainId;

    private Set<String> affiliatedCmos;


    /**
     * Gets the creator ref index.
     *
     * @return the creator ref index
     */
    public Long getCreatorRefIndex() {
        return this.creatorRefIndex;
    }



    /**
     * Gets the role code.
     *
     * @return the role code
     */
    public String getRoleCode() {
        return this.roleCode;
    }



    /**
     * Gets the derived view name share list.
     *
     * @return the derived view name share list
     */
    public List<DerivedViewNameShare> getDerivedViewNameShareList() {
        if (this.derivedViewNameShareList == null) {
            this.derivedViewNameShareList = new ArrayList<>();
        }
        return this.derivedViewNameShareList;
    }




    @Override
    public Boolean getExecDelete() {
        return this.execDelete;
    }



    /**
     * Gets the fk role.
     *
     * @return the fk role
     */
    public Long getFkRole() {
        return this.fkRole;
    }



    /**
     * Gets the fk derived view.
     *
     * @return the fk derived view
     */
    public Long getFkDerivedView() {
        return this.fkDerivedView;
    }




    @Override
    public Long getId() {
        return getIdDerivedViewName();
    }



    /**
     * Gets the id derived view name.
     *
     * @return the id derived view name
     */
    public Long getIdDerivedViewName() {
        return this.idDerivedViewName;
    }


    /**
     * Gets the name.
     *
     * @return the name
     */
    public Name getName() {
        return this.name;
    }



    /**
     * Gets the ref index.
     *
     * @return the ref index
     */
    public Long getRefIndex() {
        return this.refIndex;
    }








    /**
     * Sets the creator ref index.
     *
     * @param creatorRefIndex
     *            the new creator ref index
     */
    public void setCreatorRefIndex(Long creatorRefIndex) {
        this.creatorRefIndex = creatorRefIndex;
    }



    /**
     * Sets the role code.
     *
     * @param roleCode
     *            the new role code
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }



    /**
     * Sets the derived view name share list.
     *
     * @param derivedViewNameShareList
     *            the new derived view name share list
     */
    public void setDerivedViewNameShareList(
            List<DerivedViewNameShare> derivedViewNameShareList) {
        this.derivedViewNameShareList = derivedViewNameShareList;
    }




    @Override
    public void setExecDelete(Boolean execDelete) {
        this.execDelete = execDelete;
    }



    /**
     * Sets the fk role.
     *
     * @param fkRole
     *            the new fk role
     */
    public void setFkRole(Long fkRole) {
        this.fkRole = fkRole;
    }



    /**
     * Sets the fk derived view.
     *
     * @param fkDerivedView
     *            the new fk derived view
     */
    public void setFkDerivedView(Long fkDerivedView) {
        this.fkDerivedView = fkDerivedView;
    }




    @Override
    public void setId(Long id) {
        setIdDerivedViewName(id);
    }



    /**
     * Sets the id derived view name.
     *
     * @param idDerivedViewName
     *            the new id derived view name
     */
    public void setIdDerivedViewName(Long idDerivedViewName) {
        this.idDerivedViewName = idDerivedViewName;
    }



    /**
     * Sets the name.
     *
     * @param name
     *            the new name
     */
    public void setName(Name name) {
        this.name = name;
    }



    /**
     * Sets the ref index.
     *
     * @param refIndex
     *            the new ref index
     */
    public void setRefIndex(Long refIndex) {
        this.refIndex = refIndex;
    }



    /**
     * Gets the source type.
     *
     * @return the source type
     */
    public String getSourceType() {
                return sourceType;
        }



        /**
         * Sets the source type.
         *
         * @param sourceType the new source type
         */
        public void setSourceType(String sourceType) {
                this.sourceType = sourceType;
        }


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }








        /**
         * Gets the fk source type.
         *
         * @return the fk source type
         */
        public Long getFkSourceType() {
                return fkSourceType;
        }



        /**
         * Sets the fk source type.
         *
         * @param fkSourceType the new fk source type
         */
        public void setFkSourceType(Long fkSourceType) {
                this.fkSourceType = fkSourceType;
        }



        /**
         * Gets the source type code.
         *
         * @return the source type code
         */
        public String getSourceTypeCode() {
                return sourceTypeCode;
        }



        /**
         * Sets the source type code.
         *
         * @param sourceTypeCode the new source type code
         */
        public void setSourceTypeCode(String sourceTypeCode) {
                this.sourceTypeCode = sourceTypeCode;
        }



		public String getCreatorRefMainId() {
			return creatorRefMainId;
		}



		public void setCreatorRefMainId(String creatorRefMainId) {
			this.creatorRefMainId = creatorRefMainId;
		}


		public Set<String> getAffiliatedCmos() {
			if (affiliatedCmos == null) {
				affiliatedCmos = new HashSet<>();
			}
			return affiliatedCmos;
		}



		public void setAffiliatedCmos(Set<String> affiliatedCmos) {
			this.affiliatedCmos = affiliatedCmos;
		}

}