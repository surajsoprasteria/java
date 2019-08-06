package org.wipo.connect.common.index.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.solr.core.SolrOperations;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class CustomSolrTransactionSynchronizationAdapter extends TransactionSynchronizationAdapter {

	private Map<Integer, CompletionDelegate> delegates = new HashMap<>(2);
	private final SolrOperations solrOperations;

	CustomSolrTransactionSynchronizationAdapter(SolrOperations solrOperations) {
		super();
		this.solrOperations = solrOperations;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.transaction.support.TransactionSynchronizationAdapter#afterCompletion(int)
	 */
	@Override
	public void afterCompletion(int status) {

		CompletionDelegate delegate = this.delegates.get(status);
		if (delegate != null) {
			delegate.execute(this.solrOperations);
		}
	}

	public void register() {
		TransactionSynchronizationManager.registerSynchronization(this);
	}

	public void registerCompletionDelegate(int transactionStatus, CompletionDelegate completionDelegate) {
		this.delegates.put(transactionStatus, completionDelegate);
	}

	public interface CompletionDelegate {

		void execute(SolrOperations solrOperations);

	}

	public static class CommitTransaction implements CompletionDelegate {

		private final String collectionName;

		CommitTransaction(String collectionName) {
			this.collectionName = collectionName;
		}

		@Override
		public void execute(SolrOperations solrOperations) {
			solrOperations.commit(collectionName);
		}

	}

	public static class RollbackTransaction implements CompletionDelegate {

		private final String collectionName;

		RollbackTransaction(String collectionName) {
			this.collectionName = collectionName;
		}

		@Override
		public void execute(SolrOperations solrOperations) {
			solrOperations.rollback(collectionName);
		}

	}

}