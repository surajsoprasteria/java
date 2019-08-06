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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.SolrCrudRepository;

// TODO: Auto-generated Javadoc
/**
 * The Interface BaseSolrRepository.
 *
 * @param <T>
 *            the generic type
 * @param <ID>
 *            the generic type
 */
public interface BaseSolrRepository<T, ID extends Serializable> extends SolrCrudRepository<T, ID> {

    void forceCommit();

    void forceRollback();

    Page<T> search(String query);

    Page<T> search(String searchString, Pageable pageRequest);

    Page<T> searchFuzzy(String query, Pageable pageRequest);

    Page<T> searchFuzzy(String query);

    Page<T> solrSerach(String query);

    Page<T> solrSerach(String query, Pageable pageRequest);

    Page<T> customSearch(String query);

    Page<T> customSearch(String query, Pageable pageRequest);

}
