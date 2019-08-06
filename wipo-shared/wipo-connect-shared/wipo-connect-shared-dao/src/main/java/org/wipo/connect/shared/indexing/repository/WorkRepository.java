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
import org.wipo.connect.shared.exchange.index.doc.WorkDoc;
import org.wipo.connect.shared.exchange.vo.WorkSearchVO;

public interface WorkRepository extends BaseSolrRepository<WorkDoc, Long> {

    Page<WorkDoc> customSearch(WorkSearchVO searchVO, Pageable pageRequest);

}
