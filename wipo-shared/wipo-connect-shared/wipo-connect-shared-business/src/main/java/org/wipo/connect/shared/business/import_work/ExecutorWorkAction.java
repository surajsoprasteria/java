package org.wipo.connect.shared.business.import_work;

import java.util.List;

import org.wipo.connect.common.import_model.TransactionTypeEnum;
import org.wipo.connect.common.import_model.WorkRow;
import org.wipo.connect.shared.exchange.dto.impl.Work;

public interface ExecutorWorkAction {

    void executeAction(TransactionTypeEnum transaction, Work work, List<WorkRow> workRows) throws WccWorkImportException;

}
