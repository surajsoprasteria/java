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

package org.wipo.connect.shared.persistence.dao;

import java.util.List;

import org.wipo.connect.shared.exchange.dto.impl.WorkImportDetail;
import org.wipo.connect.shared.exchange.enumerator.ImportDetailStatusEnum;
import org.wipo.connect.shared.persistence.Dao;


/**
 * The WorkImportDetailDAO interface provides methods that manipulate the data
 * from the database.
 *
 * @author minervini
 *
 */
public interface WorkImportDetailDAO extends Dao<WorkImportDetail> {

	/**
	 * Insert work import detail.
	 *
	 * @param WorkImportDetail the work import detail
	 * @return the long
	 */
	Long insertWorkImportDetail(WorkImportDetail WorkImportDetail);

	/**
	 * Find work import detail from status.
	 *
	 * @param status the status
	 * @return the list
	 */
	List<WorkImportDetail> findWorkImportDetailFromStatus(ImportDetailStatusEnum status);

	//int insertWorkImportDuplicate(WorkImportDuplicate WorkImportDuplicate);

}
