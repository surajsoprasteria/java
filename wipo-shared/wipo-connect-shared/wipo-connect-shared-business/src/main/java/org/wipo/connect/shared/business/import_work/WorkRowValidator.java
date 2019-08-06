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
package org.wipo.connect.shared.business.import_work;

import java.util.List;
import java.util.Set;

import org.wipo.connect.common.import_model.TransactionTypeEnum;
import org.wipo.connect.common.import_model.WorkRow;
import org.wipo.connect.enumerator.WccWorkImportExceptionCodeEnum;
import org.wipo.connect.shared.exchange.dto.impl.CreationClassFlat;
import org.wipo.connect.shared.exchange.dto.impl.Work;

public interface WorkRowValidator {

    void init();

    TransactionTypeEnum validateTransaction(List<WorkRow> workRows) throws WccWorkImportException;

    void validateRows(TransactionTypeEnum transaction, List<WorkRow> workRows) throws WccWorkImportException;

    WccWorkImportExceptionCodeEnum validateMainRow(TransactionTypeEnum transaction, WorkRow row, CreationClassFlat cc);

    WccWorkImportExceptionCodeEnum validateTitleRow(WorkRow row);

    WccWorkImportExceptionCodeEnum validateIdentifierRow(WorkRow row, Long workId, Set<String> identifierList);

    WccWorkImportExceptionCodeEnum validateShareRow(WorkRow row, String ccCode);

    WccWorkImportExceptionCodeEnum validateDateRow(WorkRow row, Set<String> wdList);

    WccWorkImportExceptionCodeEnum validateComponentRow(WorkRow row, Long parentId, CreationClassFlat creationClass, Set<String> componentsCodes);

    void checkIfRejectAllRow(TransactionTypeEnum transaction, List<WorkRow> workRowList) throws WccWorkImportException;

    Work completeDerivedView(Work work) throws WccWorkImportException;

}
