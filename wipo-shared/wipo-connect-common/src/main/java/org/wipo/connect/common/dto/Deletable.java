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



package org.wipo.connect.common.dto;



/**
 * The Interface Deletable.
 *
 * @author Minervini
 */
public interface Deletable extends Identifiable {

    /**
     * Gets the exec delete.
     *
     * @return the exec delete
     */
    public Boolean getExecDelete();



    /**
     * Sets the exec delete.
     *
     * @param execDelete
     *            the new exec delete
     */
    public void setExecDelete(Boolean execDelete);

}
