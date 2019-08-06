/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Copyright Connection (WCC).
 *
 *
 * @author Fincons
 *
 */
package org.wipo.connect.common.index.repository;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.RequestMethod;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleFilterQuery;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.Assert;
import org.wipo.connect.common.utils.ConstantUtils;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public abstract class AbstractSolrRepository<T, ID extends Serializable> implements BaseSolrRepository<T, ID> {

    protected abstract SolrOperations getSolrTemplate();

    protected abstract Class<T> getEntityClass();

    protected abstract String getCollectionName();

    protected static final int MATCH_PRECISION = 6;

    private static final String DEFAULT_ID_FIELD = "id";
    private String idFieldName = DEFAULT_ID_FIELD;

    protected RequestMethod getRequestMethod() {
	return RequestMethod.POST;
    }

    @Override
    public Optional<T> findById(ID id) {
	return getSolrTemplate().queryForObject(getCollectionName(), new SimpleQuery(new Criteria(this.idFieldName).is(id)), getEntityClass());
    }

    @Override
    public Iterable<T> findAll() {
	int itemCount = (int) this.count();
	if (itemCount == 0) {
	    return new PageImpl<>(Collections.<T>emptyList());
	}
	return this.findAll(new SolrPageRequest(0, itemCount));
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
	return getSolrTemplate().queryForPage(getCollectionName(), new SimpleQuery(new Criteria(Criteria.WILDCARD).expression(Criteria.WILDCARD)).setPageRequest(pageable),
		getEntityClass());
    }

    @Override
    public Iterable<T> findAll(Sort sort) {
	int itemCount = (int) this.count();
	if (itemCount == 0) {
	    return new PageImpl<>(Collections.<T>emptyList());
	}
	return getSolrTemplate().queryForPage(getCollectionName(),
		new SimpleQuery(new Criteria(Criteria.WILDCARD).expression(Criteria.WILDCARD)).setPageRequest(new SolrPageRequest(0, itemCount)).addSort(sort), getEntityClass());
    }

    @Override
    public Iterable<T> findAllById(Iterable<ID> ids) {
	Criteria inCriteria = null;
	List<ID> bigList = IteratorUtils.toList(ids.iterator());
	List<List<ID>> subLists = ListUtils.partition(bigList, 100);

	for (List<ID> sub : subLists) {
	    if (inCriteria == null) {
		inCriteria = new Criteria(this.idFieldName).in(sub);
	    } else {
		inCriteria = inCriteria.or(new Criteria(this.idFieldName).in(sub));
	    }
	}

	org.springframework.data.solr.core.query.Query query = new SimpleQuery(inCriteria);
	query.setPageRequest(new SolrPageRequest(0, (int) count(query)));

	return getSolrTemplate().queryForPage(getCollectionName(), query, getEntityClass());
    }

    @Override
    public long count() {
	return count(new SimpleQuery(new Criteria(Criteria.WILDCARD).expression(Criteria.WILDCARD)));
    }

    protected long count(org.springframework.data.solr.core.query.Query query) {
	org.springframework.data.solr.core.query.Query countQuery = SimpleQuery.fromQuery(query);
	return getSolrTemplate().count(getCollectionName(), countQuery);
    }

    @Override
    public <S extends T> S save(S entity) {
	Assert.notNull(entity, "Cannot save 'null' entity.");
	registerTransactionSynchronisationIfSynchronisationActive();
	getSolrTemplate().saveBean(getCollectionName(), entity);
	commitIfTransactionSynchronisationIsInactive();
	return entity;
    }

    @Override
    public <S extends T> S save(S entity, Duration commitWithin) {
	Assert.notNull(entity, "Cannot save 'null' entity.");
	registerTransactionSynchronisationIfSynchronisationActive();
	getSolrTemplate().saveBean(getCollectionName(), entity, commitWithin);
	commitIfTransactionSynchronisationIsInactive();
	return entity;
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
	Assert.notNull(entities, "Cannot insert 'null' as a List.");

	if (!(entities instanceof Collection<?>)) {
	    throw new InvalidDataAccessApiUsageException("Entities have to be inside a collection");
	}
	registerTransactionSynchronisationIfSynchronisationActive();
	getSolrTemplate().saveBeans(getCollectionName(), (Collection<? extends T>) entities);
	commitIfTransactionSynchronisationIsInactive();
	return entities;
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities, Duration commitWithin) {
	Assert.notNull(entities, "Cannot insert 'null' as a List.");

	if (!(entities instanceof Collection<?>)) {
	    throw new InvalidDataAccessApiUsageException("Entities have to be inside a collection");
	}
	registerTransactionSynchronisationIfSynchronisationActive();
	getSolrTemplate().saveBeans(getCollectionName(), (Collection<? extends T>) entities, commitWithin);
	commitIfTransactionSynchronisationIsInactive();
	return entities;
    }

    @Override
    public boolean existsById(ID id) {
	return findById(id) != null;
    }

    @Override
    public void deleteById(ID id) {
	Assert.notNull(id, "Cannot delete entity with id 'null'.");
	getSolrTemplate().deleteByIds(getCollectionName(), id.toString());
    }

    @Override
    public void delete(T entity) {
	Assert.notNull(entity, "Cannot delete 'null' entity.");
	registerTransactionSynchronisationIfSynchronisationActive();
	deleteAll(Collections.singletonList(entity));
	commitIfTransactionSynchronisationIsInactive();
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
	Assert.notNull(entities, "Cannot delete 'null' list.");

	ArrayList<String> idsToDelete = new ArrayList<>();
	for (T entity : entities) {
	    idsToDelete.add(extractIdFromBean(entity).toString());
	}

	registerTransactionSynchronisationIfSynchronisationActive();
	getSolrTemplate().deleteByIds(getCollectionName(), idsToDelete);
	commitIfTransactionSynchronisationIsInactive();
    }

    @Override
    public void deleteAll() {
	registerTransactionSynchronisationIfSynchronisationActive();
	getSolrTemplate().delete(getCollectionName(), new SimpleFilterQuery(new Criteria(Criteria.WILDCARD).expression(Criteria.WILDCARD)));
	commitIfTransactionSynchronisationIsInactive();
    }

    private Object extractIdFromBean(T entity) {
	SolrInputDocument solrInputDocument = getSolrTemplate().convertBeanToSolrInputDocument(entity);
	return extractIdFromSolrInputDocument(solrInputDocument);
    }

    private String extractIdFromSolrInputDocument(SolrInputDocument solrInputDocument) {
	Assert.notNull(solrInputDocument.getField(idFieldName), "Unable to find field '" + idFieldName + "' in SolrDocument.");
	Assert.notNull(solrInputDocument.getField(idFieldName).getValue(), "ID must not be 'null'.");

	return solrInputDocument.getField(idFieldName).getValue().toString();
    }

    @Override
    public Page<T> search(String query) {
	return search(query, null);
    }

    @Override
    public Page<T> search(String query, Pageable pageRequest) {
	Criteria solrCrit = null;
	query = StringUtils.lowerCase(query);
	query = parseSearchString(query);
	String[] split = StringUtils.split(query);
	if (split != null && split.length > 0) {
	    solrCrit = new Criteria().contains(split);
	} else {
	    solrCrit = new Criteria(Criteria.WILDCARD).expression(Criteria.WILDCARD);
	}

	SimpleQuery dataQuery = new SimpleQuery(solrCrit);
	if (pageRequest != null) {
	    dataQuery.setPageRequest(pageRequest);
	} else {
	    dataQuery.setPageRequest(new SolrPageRequest(0, ConstantUtils.DEFAULT_LIMIT));
	}
	dataQuery.addSort(new Sort(Sort.Direction.DESC, "score"));
	Page<T> page = getSolrTemplate().queryForGroupPage(getCollectionName(), dataQuery, getEntityClass(), getRequestMethod());

	return page;
    }

    @Override
    public Page<T> searchFuzzy(String query) {
	return searchFuzzy(query, null);
    }

    @Override
    public Page<T> solrSerach(String query) {
	return solrSerach(query, null);
    }

    @Override
    public Page<T> solrSerach(String query, Pageable pageRequest) {
	Criteria solrCrit = null;

	solrCrit = new Criteria().expression(query);

	SimpleQuery dataQuery = new SimpleQuery(solrCrit);
	if (pageRequest != null) {
	    dataQuery.setPageRequest(pageRequest);
	} else {
	    dataQuery.setPageRequest(new SolrPageRequest(0, ConstantUtils.DEFAULT_LIMIT));
	}
	dataQuery.addSort(new Sort(Sort.Direction.DESC, "score"));

	Page<T> page = getSolrTemplate().queryForGroupPage(getCollectionName(), dataQuery, getEntityClass(), getRequestMethod());

	return page;
    }

    @Override
    public Page<T> customSearch(String query) {
	return customSearch(query, null);
    }

    @Override
    public Page<T> customSearch(String query, Pageable pageRequest) {
	if (StringUtils.startsWith(query, "{") && StringUtils.endsWith(query, "}")) {
	    query = StringUtils.removeStart(query, "{");
	    query = StringUtils.removeEnd(query, "}");

	    return solrSerach(query, pageRequest);
	} else {
	    return searchFuzzy(query, pageRequest);
	}
    }

    @Override
    public Page<T> searchFuzzy(String query, Pageable pageRequest) {
	Criteria solrCrit = null;
	query = StringUtils.lowerCase(query);
	query = parseSearchString(query);
	for (String word : StringUtils.split(query)) {
	    if (solrCrit == null) {
		solrCrit = new Criteria().fuzzy(word);
	    } else {
		solrCrit = solrCrit.fuzzy(word);
	    }
	}

	if (solrCrit == null) {
	    solrCrit = new Criteria(Criteria.WILDCARD).expression(Criteria.WILDCARD);
	}

	SimpleQuery dataQuery = new SimpleQuery(solrCrit);
	if (pageRequest != null) {
	    dataQuery.setPageRequest(pageRequest);
	} else {
	    dataQuery.setPageRequest(new SolrPageRequest(0, ConstantUtils.DEFAULT_LIMIT));
	}
	dataQuery.addSort(new Sort(Sort.Direction.DESC, "score"));

	Page<T> page = getSolrTemplate().queryForGroupPage(getCollectionName(), dataQuery, getEntityClass(), getRequestMethod());

	return page;
    }

    @Override
    public void forceCommit() {
	getSolrTemplate().commit(getCollectionName());
    }

    @Override
    public void forceRollback() {
	getSolrTemplate().rollback(getCollectionName());
    }

    private void registerTransactionSynchronisationIfSynchronisationActive() {
	if (TransactionSynchronizationManager.isSynchronizationActive()) {
	    registerTransactionSynchronisationAdapter();
	}
    }

    private void registerTransactionSynchronisationAdapter() {
	TransactionSynchronizationManager
		.registerSynchronization(CustomSolrTransactionSynchronizationAdapterBuilder.forOperations(getSolrTemplate()).onCollection(getCollectionName()).withDefaultBehaviour());
    }

    private void commitIfTransactionSynchronisationIsInactive() {
	if (!TransactionSynchronizationManager.isSynchronizationActive()) {
	    getSolrTemplate().commit(getCollectionName());
	}
    }

    public static String parseSearchString(String searchString) {
	if (StringUtils.isNotEmpty(searchString)) {
	    return QueryParser.escape(searchString);
	}

	return searchString;
    }

}
