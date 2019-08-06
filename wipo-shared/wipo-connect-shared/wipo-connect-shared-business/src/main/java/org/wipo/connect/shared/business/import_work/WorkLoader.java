package org.wipo.connect.shared.business.import_work;

import java.util.List;

import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.import_model.TransactionTypeEnum;
import org.wipo.connect.common.import_model.WorkRow;
import org.wipo.connect.shared.exchange.dto.impl.DerivedView;
import org.wipo.connect.shared.exchange.dto.impl.DerivedWork;
import org.wipo.connect.shared.exchange.dto.impl.Title;
import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.dto.impl.WorkDate;
import org.wipo.connect.shared.exchange.dto.impl.WorkIdentifierFlat;

public interface WorkLoader {

    void init();

    Work loadRowsEntity(TransactionTypeEnum transaction, List<WorkRow> workRows, String dataOrigin) throws WccWorkImportException, WccException;

    Work loadWork(WorkRow row, TransactionTypeEnum transaction) throws WccWorkImportException, WccException;

    Title loadTitle(WorkRow row) throws WccWorkImportException;

    WorkIdentifierFlat loadIdentifier(WorkRow row) throws WccWorkImportException;

    DerivedView loadShare(WorkRow row) throws WccException, WccWorkImportException;

    WorkDate loadWorkDate(WorkRow row) throws WccException;

    DerivedWork loadWorkComponent(WorkRow row, Work work) throws WccException, WccWorkImportException;

}
