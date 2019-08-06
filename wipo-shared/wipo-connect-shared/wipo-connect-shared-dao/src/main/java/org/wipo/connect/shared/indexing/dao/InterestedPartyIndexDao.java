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
import org.wipo.connect.shared.exchange.index.doc.InterestedPartyDoc;
import org.wipo.connect.shared.exchange.vo.InterestedPartySearchVO;
import org.wipo.connect.shared.indexing.repository.InterestedPartyRepository;

/**
 * The Class InterestedPartyIndexDao.
 */
@Service
public class InterestedPartyIndexDao {

    @Autowired
    private InterestedPartyRepository solrRepository;

    public long count() {
	return solrRepository.count();
    }

    public void delete(Long id) {
	solrRepository.deleteById(id);
    }

    public void delete(InterestedPartyDoc ip) {
	solrRepository.delete(ip);
    }

    public void delete(Iterable<? extends InterestedPartyDoc> ip) {
	solrRepository.deleteAll(ip);
    }

    public void deleteAll() {
	solrRepository.deleteAll();
    }

    public boolean exists(Long id) {
	return solrRepository.existsById(id);
    }

    public Iterable<InterestedPartyDoc> findAll() {
	return solrRepository.findAll();
    }

    public Iterable<InterestedPartyDoc> findAll(Iterable<Long> arg0) {
	return solrRepository.findAllById(arg0);
    }

    public <S extends InterestedPartyDoc> S save(S arg0) {
	return solrRepository.save(arg0);
    }

    public <S extends InterestedPartyDoc> Iterable<S> save(Iterable<S> arg0) {
	if (arg0 == null) {
	    return null;
	} else if (StreamSupport.stream(arg0.spliterator(), false).count() <= 0L) {
	    return null;
	}
	return solrRepository.saveAll(arg0);
    }

    public void forceCommit() {
	solrRepository.forceCommit();
    }

    public List<InterestedPartyDoc> search(String query, Integer maxResults) throws TooManyResultsException {
	Page<InterestedPartyDoc> page = solrRepository.customSearch(query, PageRequest.of(0, maxResults));

	if (maxResults.compareTo((int) page.getTotalElements()) < 0) {
	    throw new TooManyResultsException("Too many results", (int) page.getTotalElements(), maxResults);
	}

	return page.getContent();
    }

    public List<InterestedPartyDoc> search(String query) {
	return solrRepository.customSearch(query).getContent();
    }

    public Page<InterestedPartyDoc> search(InterestedPartySearchVO searchVO, Integer start, Integer lenght) {
	if (start == null || lenght == null) {
	    start = 0;
	    lenght = ConstantUtils.DEFAULT_LIMIT;
	} else if (start != 0) {
	    start = Math.floorDiv(start, lenght);
	}

	return solrRepository.customSearch(searchVO, PageRequest.of(start, lenght));
    }
}
