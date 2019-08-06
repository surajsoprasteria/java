package org.wipo.connect.shared.business.import_ip;

import java.text.ParseException;
import java.util.List;

import org.wipo.connect.common.import_model.IpRow;
import org.wipo.connect.common.import_model.TransactionTypeEnum;
import org.wipo.connect.shared.exchange.dto.impl.InterestedParty;

public interface IpRowLoader {

    public abstract void init();

    public abstract InterestedParty load(TransactionTypeEnum transaction, List<IpRow> entityList, String dataOrigin) throws WccInterestedPartyImportException, ParseException;

}