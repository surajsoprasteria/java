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
package org.wipo.connect.shared.indexing.repository;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.index.repository.AbstractSolrRepository;
import org.wipo.connect.common.utils.ConstantUtils;
import org.wipo.connect.enumerator.OrderByExpressionEnum;
import org.wipo.connect.shared.exchange.enumerator.InterestedPartyStatusEnum;
import org.wipo.connect.shared.exchange.index.doc.InterestedPartyDoc;
import org.wipo.connect.shared.exchange.vo.InterestedPartySearchVO;

@Repository
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class InterestedPartyRepositoryImpl extends AbstractSolrRepository<InterestedPartyDoc, Long> implements InterestedPartyRepository {

    @Value("#{configProperties['solr.ip-core']}")
    private String solrCoreIp;

    @Autowired
    public SolrOperations solrTemplate;

    @Override
    protected SolrOperations getSolrTemplate() {
	return solrTemplate;
    }

    @Override
    protected Class<InterestedPartyDoc> getEntityClass() {
	return InterestedPartyDoc.class;
    }

    @Override
    protected String getCollectionName() {
	return solrCoreIp;
    }

    @Override
    public Page<InterestedPartyDoc> customSearch(InterestedPartySearchVO searchVO, Pageable pageRequest) {

	Criteria solrCrit = new Criteria();

	String fullText = searchVO.getFullText();
	if (StringUtils.isNotEmpty(fullText)) {

	    fullText = StringUtils.lowerCase(fullText);

	    if (StringUtils.startsWith(fullText, "{") && StringUtils.endsWith(fullText, "}")) {
		fullText = StringUtils.removeStart(fullText, "{");
		fullText = StringUtils.removeEnd(fullText, "}");
		fullText = StringUtils.trimToEmpty(fullText);

		fullText = AbstractSolrRepository.parseSearchString(fullText);
		solrCrit = solrCrit.expression(fullText);
	    } else {
		fullText = AbstractSolrRepository.parseSearchString(fullText);
		for (String word : StringUtils.split(fullText)) {
		    solrCrit = solrCrit.fuzzy(word);
		}
	    }
	} else {
	    solrCrit = new Criteria(Criteria.WILDCARD).expression(Criteria.WILDCARD);
	}

	if (!searchVO.getIdToExcludeList().isEmpty()) {
	    solrCrit = solrCrit.and("id").not().in(searchVO.getIdToExcludeList());
	}

	if (searchVO.getStatusCode() != null) {
	    solrCrit = solrCrit.and("statusCode").in(searchVO.getStatusCode());
	} else if (BooleanUtils.isTrue(searchVO.getIsToShowDeleted())) {
	    solrCrit = solrCrit.and("statusCode").in(InterestedPartyStatusEnum.DELETED.name());
	} else if (searchVO.getStatusCode() == null && BooleanUtils.isFalse(searchVO.getIsToShowDeleted())) {
	    solrCrit = solrCrit.and("statusCode").not().in(InterestedPartyStatusEnum.DELETED.name());
	}

	SimpleQuery dataQuery = new SimpleQuery(solrCrit);
	if (pageRequest != null) {
	    dataQuery.setPageRequest(pageRequest);
	} else {
	    dataQuery.setPageRequest(new SolrPageRequest(0, ConstantUtils.DEFAULT_LIMIT));
	}

	if (StringUtils.equalsIgnoreCase(searchVO.getOrderByExpression(), OrderByExpressionEnum.RO_SCORE.getFieldAsc())) {
	    dataQuery.addSort(new Sort(Sort.Direction.ASC, OrderByExpressionEnum.RO_SCORE.getSolr()));
	} else if (StringUtils.equalsIgnoreCase(searchVO.getOrderByExpression(), OrderByExpressionEnum.RO_SCORE.getFieldDesc())) {
	    dataQuery.addSort(new Sort(Sort.Direction.DESC, OrderByExpressionEnum.RO_SCORE.getSolr()));
	} else if (StringUtils.equalsIgnoreCase(searchVO.getOrderByExpression(), OrderByExpressionEnum.RO_MAIN_ID.getFieldAsc())) {
	    dataQuery.addSort(new Sort(Sort.Direction.ASC, OrderByExpressionEnum.RO_MAIN_ID.getSolr()));
	} else if (StringUtils.equalsIgnoreCase(searchVO.getOrderByExpression(), OrderByExpressionEnum.RO_MAIN_ID.getFieldDesc())) {
	    dataQuery.addSort(new Sort(Sort.Direction.DESC, OrderByExpressionEnum.RO_MAIN_ID.getSolr()));
	} else if (StringUtils.equalsIgnoreCase(searchVO.getOrderByExpression(), OrderByExpressionEnum.RO_TYPE.getFieldAsc())) {
	    dataQuery.addSort(new Sort(Sort.Direction.ASC, OrderByExpressionEnum.RO_TYPE.getSolr()));
	} else if (StringUtils.equalsIgnoreCase(searchVO.getOrderByExpression(), OrderByExpressionEnum.RO_TYPE.getFieldDesc())) {
	    dataQuery.addSort(new Sort(Sort.Direction.DESC, OrderByExpressionEnum.RO_TYPE.getSolr()));
	} else if (StringUtils.equalsIgnoreCase(searchVO.getOrderByExpression(), OrderByExpressionEnum.RO_STATUS.getFieldAsc())) {
	    dataQuery.addSort(new Sort(Sort.Direction.ASC, OrderByExpressionEnum.RO_STATUS.getSolr()));
	} else if (StringUtils.equalsIgnoreCase(searchVO.getOrderByExpression(), OrderByExpressionEnum.RO_STATUS.getFieldDesc())) {
	    dataQuery.addSort(new Sort(Sort.Direction.DESC, OrderByExpressionEnum.RO_STATUS.getSolr()));
	} else if (StringUtils.equalsIgnoreCase(searchVO.getOrderByExpression(), OrderByExpressionEnum.RO_MAIN_NAME.getFieldAsc())) {
	    dataQuery.addSort(new Sort(Sort.Direction.ASC, OrderByExpressionEnum.RO_MAIN_NAME.getSolr()));
	} else if (StringUtils.equalsIgnoreCase(searchVO.getOrderByExpression(), OrderByExpressionEnum.RO_MAIN_NAME.getFieldDesc())) {
	    dataQuery.addSort(new Sort(Sort.Direction.DESC, OrderByExpressionEnum.RO_MAIN_NAME.getSolr()));
	} else {
	    dataQuery.addSort(new Sort(Sort.Direction.DESC, OrderByExpressionEnum.RO_SCORE.getSolr()));
	}

	return getSolrTemplate().queryForGroupPage(getCollectionName(), dataQuery, getEntityClass(), getRequestMethod());

    }

}
