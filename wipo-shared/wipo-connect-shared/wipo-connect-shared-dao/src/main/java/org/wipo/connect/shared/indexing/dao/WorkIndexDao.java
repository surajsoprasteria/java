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
package org.wipo.connect.shared.indexing.dao;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.exception.TooManyResultsException;
import org.wipo.connect.common.utils.ConstantUtils;
import org.wipo.connect.shared.exchange.index.doc.WorkDoc;
import org.wipo.connect.shared.exchange.vo.WorkSearchVO;
import org.wipo.connect.shared.indexing.repository.WorkRepository;

/**
 * The Class WorkIndexDao.
 */
@Service
public class WorkIndexDao {

    @Autowired
    private WorkRepository solrRepository;

    public long count() {
	return solrRepository.count();
    }

    public void delete(Iterable<? extends WorkDoc> arg0) {
	solrRepository.deleteAll(arg0);
    }

    public void delete(Long arg0) {
	solrRepository.deleteById(arg0);
    }

    public void delete(WorkDoc arg0) {
	solrRepository.delete(arg0);
    }

    public void deleteAll() {
	solrRepository.deleteAll();
    }

    public boolean exists(Long arg0) {
	return solrRepository.existsById(arg0);
    }

    public Iterable<WorkDoc> findAll() {
	return solrRepository.findAll();
    }

    public Iterable<WorkDoc> findAll(Iterable<Long> arg0) {
	return solrRepository.findAllById(arg0);
    }

    public <S extends WorkDoc> Iterable<S> save(Iterable<S> arg0) {
	if (arg0 == null) {
	    return null;
	} else if (StreamSupport.stream(arg0.spliterator(), false).count() <= 0L) {
	    return null;
	}
	return solrRepository.saveAll(arg0);
    }

    public <S extends WorkDoc> S save(S arg0) {
	return solrRepository.save(arg0);
    }

    public void forceCommit() {
	solrRepository.forceCommit();
    }

    public List<WorkDoc> search(String query, Integer maxResults) throws TooManyResultsException {
	Page<WorkDoc> page = solrRepository.customSearch(query, PageRequest.of(0, maxResults));

	if (maxResults.compareTo((int) page.getTotalElements()) < 0) {
	    throw new TooManyResultsException("Too many results", (int) page.getTotalElements(), maxResults);
	}

	return page.getContent();
    }

    public List<WorkDoc> search(String query) {
	return solrRepository.customSearch(query).getContent();
    }

    public List<WorkDoc> search(String query, int start, int length) {
	return solrRepository.customSearch(query, PageRequest.of(start, length)).getContent();
    }

    public Page<WorkDoc> search(WorkSearchVO searchVO, Integer start, Integer length) {

	if (start == null || length == null) {
	    start = 0;
	    length = ConstantUtils.DEFAULT_LIMIT;
	} else if (start != 0) {
	    start = Math.floorDiv(start, length);
	}

	return solrRepository.customSearch(searchVO, PageRequest.of(start, length));
    }
}
