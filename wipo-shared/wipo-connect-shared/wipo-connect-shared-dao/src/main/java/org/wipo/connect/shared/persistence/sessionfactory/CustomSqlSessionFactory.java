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

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;

/**
 * A factory for creating CustomSqlSession objects.
 */
public class CustomSqlSessionFactory extends DefaultSqlSessionFactory {

    /** The Constant logger. */
    private static final Logger logger = WipoLoggerFactory.getLogger(CustomSqlSessionFactory.class);

    /** The Constant MYSQL. */
    private static final String MYSQL = "mysql";

    /** The Constant ORACLE. */
    private static final String ORACLE = "oracle";

    /** The database id. */
    private String databaseId;

    /**
     * Instantiates a new custom sql session factory.
     *
     * @param configuration
     *            the configuration
     * @param dataSource
     *            the data source
     * @param databaseIdProvider
     *            the database id provider
     */
    public CustomSqlSessionFactory(Configuration configuration, DataSource dataSource, VendorDatabaseIdProvider databaseIdProvider) {
	super(configuration);
	this.databaseId = databaseIdProvider.getDatabaseId(dataSource);
    }

    /**
     * Alter session.
     *
     * @param session
     *            the session
     */
    protected void alterSession(SqlSession session) {
	try {

	    Statement statement;

	    if (ORACLE.equals(this.databaseId)) {
		logger.trace("Oracle - alterSession");
		statement = session.getConnection().createStatement();
		statement.addBatch("ALTER SESSION SET NLS_COMP = LINGUISTIC");
		statement.addBatch("ALTER SESSION SET NLS_SORT = BINARY_CI");
		statement.executeBatch();
		statement.close();
		logger.debug("Altered newly created session parameters.");
	    } else if (MYSQL.equals(this.databaseId)) {
		logger.trace("MySQL - alterSession");
		statement = session.getConnection().createStatement();
		statement.addBatch("SET NAMES 'utf8mb4' COLLATE 'utf8mb4_unicode_ci'");
		statement.executeBatch();
		statement.close();
	    }

	} catch (SQLException e) {
	    logger.error("Alter session failed!", e);
	}
    }

    @Override
    public SqlSession openSession() {
	SqlSession session = super.openSession();
	alterSession(session);
	return session;
    }

    @Override
    public SqlSession openSession(boolean autoCommit) {
	SqlSession session = super.openSession(autoCommit);
	alterSession(session);
	return session;
    }

    @Override
    public SqlSession openSession(Connection connection) {
	SqlSession session = super.openSession(connection);
	alterSession(session);
	return session;
    }

    @Override
    public SqlSession openSession(ExecutorType execType) {
	SqlSession session = super.openSession(execType);
	alterSession(session);
	return session;
    }

    @Override
    public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
	SqlSession session = super.openSession(execType, autoCommit);
	alterSession(session);
	return session;
    }

    @Override
    public SqlSession openSession(ExecutorType execType, Connection connection) {
	SqlSession session = super.openSession(execType, connection);
	alterSession(session);
	return session;
    }

    @Override
    public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) {
	SqlSession session = super.openSession(execType, level);
	alterSession(session);
	return session;
    }

    @Override
    public SqlSession openSession(TransactionIsolationLevel level) {
	SqlSession session = super.openSession(level);
	alterSession(session);
	return session;
    }
}