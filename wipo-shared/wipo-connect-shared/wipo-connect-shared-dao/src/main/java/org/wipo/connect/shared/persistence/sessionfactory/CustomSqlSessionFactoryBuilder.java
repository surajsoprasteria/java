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



package org.wipo.connect.shared.persistence.sessionfactory;



import javax.sql.DataSource;

import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * The Class CustomSqlSessionFactoryBuilder.
 */
@Service
public class CustomSqlSessionFactoryBuilder extends SqlSessionFactoryBuilder {

    /** The database id provider. */
    @Autowired
    private VendorDatabaseIdProvider databaseIdProvider;

    /** The data source. */
    @Autowired
    private DataSource dataSource;




    @Override
    public SqlSessionFactory build(Configuration config) {
        return new CustomSqlSessionFactory(config, this.dataSource,
                this.databaseIdProvider);
    }
}