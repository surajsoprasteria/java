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



package org.wipo.connect.shared.persistence.mapping;



import java.util.List;




/**
 * The Interface Mapper.
 *
 * @author Minervini
 * @param <T>
 *            the generic type
 */
public interface Mapper<T> {

    /**
     * Delete by primary key.
     *
     * @param id
     *            the id
     * @return the int
     */
    int deleteByPrimaryKey(Long id);



    /**
     * Find all.
     *
     * @return the list
     */
    List<T> findAll();



    /**
     * Insert.
     *
     * @param record
     *            the record
     * @return the int
     */
    int insert(T record);



    /**
     * Select by primary key.
     *
     * @param id
     *            the id
     * @return the t
     */
    T selectByPrimaryKey(Long id);



    /**
     * Update by primary key.
     *
     * @param object
     *            the object
     * @return the int
     */
    int updateByPrimaryKey(T object);

}
