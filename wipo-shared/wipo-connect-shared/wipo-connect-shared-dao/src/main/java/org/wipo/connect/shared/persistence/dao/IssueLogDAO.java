
package org.wipo.connect.shared.persistence.dao;

import java.util.Date;
import java.util.List;

import org.wipo.connect.common.logging.IssueLog;

public interface IssueLogDAO {

    int insert(IssueLog dto);

    List<IssueLog> findAll();

    IssueLog findAll(Long idIssueLog);

    int deleteAll();

    int deleteOld(Date minDate);

}
