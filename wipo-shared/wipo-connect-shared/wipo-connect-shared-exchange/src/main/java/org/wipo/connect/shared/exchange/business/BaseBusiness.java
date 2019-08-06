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



package org.wipo.connect.shared.exchange.business;



import org.wipo.connect.common.exception.WccException;



/**
 * The Interface BaseBusiness.
 *
 * @param <T>
 *            the generic type
 */
public interface BaseBusiness<T> {

    /**
     * Find by id.
     *
     * @param id
     *            the id
     * @return the t
     * @throws WccException
     *             the wcc exception
     */
    public T findById(Long id) throws WccException;



    /**
     * Save or update.
     *
     * @param dto
     *            the dto
     * @return the t
     * @throws WccException
     *             the wcc exception
     */
    public T saveOrUpdate(T dto) throws WccException;

}
