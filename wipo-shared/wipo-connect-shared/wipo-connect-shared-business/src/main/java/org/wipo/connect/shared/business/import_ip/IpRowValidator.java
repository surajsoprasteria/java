package org.wipo.connect.shared.business.import_ip;

import java.util.List;
import java.util.Set;

import org.wipo.connect.common.import_model.IpRow;
import org.wipo.connect.common.import_model.TransactionTypeEnum;
import org.wipo.connect.enumerator.WccIpImportExceptionCodeEnum;
import org.wipo.connect.shared.exchange.dto.impl.AffiliationDomain;

public interface IpRowValidator {

    void init();

    TransactionTypeEnum validateTransaction(List<IpRow> entityRows) throws WccInterestedPartyImportException;

    List<IpRow> validate(TransactionTypeEnum transaction, List<IpRow> entityRow) throws WccInterestedPartyImportException;

    void checkIfRejectAllRow(TransactionTypeEnum transaction, List<IpRow> ipRowList) throws WccInterestedPartyImportException;

    WccIpImportExceptionCodeEnum validateIpRow(TransactionTypeEnum transaction, IpRow mainRow);

    WccIpImportExceptionCodeEnum validateNameRow(TransactionTypeEnum transaction, IpRow nameRow, Long idInterestedParty);

    WccIpImportExceptionCodeEnum validateIdentifierRow(IpRow row, Long idInterestedParty, Set<String> identifierList);

    WccIpImportExceptionCodeEnum validateAffiliationRow(IpRow affiliationRow, AffiliationContextMap acm, List<AffiliationDomain> adList);

}