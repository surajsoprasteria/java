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
package org.wipo.connect.common.report;

import java.io.IOException;

import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.report.bean.ReportInfoEnum;
import org.wipo.connect.common.report.bean.RequestBean;

/**
 * The Interface ReportService.
 */
public interface ReportService {

    byte[] createReport(ReportInfoEnum reportInfo, String tipoReport, RequestBean requestBean, String outputFileName) throws WccException, IOException;
}
