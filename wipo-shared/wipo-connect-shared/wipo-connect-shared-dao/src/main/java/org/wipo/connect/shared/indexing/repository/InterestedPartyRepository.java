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

package org.wipo.connect.shared.indexing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wipo.connect.common.index.repository.BaseSolrRepository;
import org.wipo.connect.shared.exchange.index.doc.InterestedPartyDoc;
import org.wipo.connect.shared.exchange.vo.InterestedPartySearchVO;

public interface InterestedPartyRepository extends BaseSolrRepository<InterestedPartyDoc, Long> {

    Page<InterestedPartyDoc> customSearch(InterestedPartySearchVO searchVO, Pageable pageRequest);

}
