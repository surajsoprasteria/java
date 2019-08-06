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



package org.wipo.connect.shared.persistence.dao;



import org.wipo.connect.shared.exchange.dto.impl.Title;
import org.wipo.connect.shared.persistence.Dao;




/**
 * The TitleDAO interface provides methods that manipulate the data from the
 * database.
 *
 * @author fumagalli
 *
 */
public interface TitleDAO extends Dao<Title> {

    /**
     * Insert title.
     *
     * @param dto
     *            the title
     * @return the int
     */
    int insert(Title dto);



    /**
     * Update title.
     * 
     * @param dto
     *            the title
     */
    void update(Title dto);

}
