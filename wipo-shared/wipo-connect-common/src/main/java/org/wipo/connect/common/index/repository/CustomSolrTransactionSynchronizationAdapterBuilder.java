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
package org.wipo.connect.common.index.repository;


import org.springframework.data.solr.core.SolrOperations;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.util.Assert;

/**
 * @author Christoph Strobl
 * @since 1.2
 */
public class CustomSolrTransactionSynchronizationAdapterBuilder {

	CustomSolrTransactionSynchronizationAdapter adapter;
	private String collectionName;

	/**
	 * @param solrOperations must not be {@literal null}
	 * @return
	 */
	public static CustomSolrTransactionSynchronizationAdapterBuilder forOperations(SolrOperations solrOperations) {

		Assert.notNull(solrOperations, "SolrOperations for transaction syncronisation must not be 'null'");
		CustomSolrTransactionSynchronizationAdapterBuilder builder = new CustomSolrTransactionSynchronizationAdapterBuilder();
		builder.adapter = new CustomSolrTransactionSynchronizationAdapter(solrOperations);
		return builder;
	}

	/**
	 * @param collection
	 * @return
	 */
	public CustomSolrTransactionSynchronizationAdapterBuilder onCollection(String collection) {

		this.collectionName = collection;
		return this;
	}

	/**
	 * Creates a {@link CustomSolrTransactionSynchronizationAdapter} reacting on
	 * {@link TransactionSynchronization#STATUS_COMMITTED} and {@link TransactionSynchronization#STATUS_ROLLED_BACK}.
	 *
	 * @return
	 */
	public CustomSolrTransactionSynchronizationAdapter withDefaultBehaviour() {

		this.adapter.registerCompletionDelegate(TransactionSynchronization.STATUS_COMMITTED,
				new CustomSolrTransactionSynchronizationAdapter.CommitTransaction(this.collectionName));
		this.adapter.registerCompletionDelegate(TransactionSynchronization.STATUS_ROLLED_BACK,
				new CustomSolrTransactionSynchronizationAdapter.RollbackTransaction(this.collectionName));

		return this.adapter;
	}

}