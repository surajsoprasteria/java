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

import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.shared.exchange.dto.impl.Name;
import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.dto.impl.WorkImport;
import org.wipo.connect.shared.exchange.dto.impl.WorkImportDetail;
import org.wipo.connect.shared.exchange.dto.impl.WorkImportFile;
import org.wipo.connect.shared.exchange.enumerator.ImportStatusEnum;

public interface ImportWorkDAOHelper {

    Work insertOrUpdate(Work work) throws WccException;

    void executeDelete(String mainId) throws WccException;

    int updateWorkImportFromStatus(Long idImport, ImportStatusEnum status);

    void saveOutputFile(WorkImport workImport, byte[] fileContent) throws WccException;

    List<WorkImport> findWorkImportFromStatus(ImportStatusEnum... status);

    void saveOutputFile(WorkImport workImport, String tempFilePath) throws WccException;

    Work findByMainId(String mainId, boolean loadDetail);

    Long findWorkIdByIdentifier(String identifierValue, String identifierType);

    Long loadIdIdentifier(String code);

    Boolean checkMainIdIsPresent(String mainId);

    Name findByNameMainId(String nameMainId);

    boolean identifierValueAlreadyPresent(String code, String value, Long workId);

    int insertWorkImportFile(WorkImportFile workImportFile) throws WccException;

    @Deprecated
    Long insertWorkImportDetail(WorkImportDetail workImportDetail) throws WccException;

    Boolean checkWorkIsComponent(String mainId);

    void updateRowResult(WorkImport workImport);

    void markAllPendingAsError();

    Long findWorkIdByMainId(String mainId);

    String findCreationClassByWorkMainId(String mainId);

    boolean checkDerivedWorkCycle(Long parentId, Long childrenId);

}