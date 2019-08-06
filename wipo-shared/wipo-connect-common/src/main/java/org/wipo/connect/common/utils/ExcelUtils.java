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
package org.wipo.connect.common.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;

public class ExcelUtils {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(ExcelUtils.class);
    private static final String DATE_PATTERN = "yyyyMMdd";

    private ExcelUtils() {
	super();
    }

    public static String readCellValue(Row rowObject, int colNum) {
	String cellValue = "";
	int rowNum = rowObject.getRowNum();

	Cell cell = rowObject.getCell(colNum, MissingCellPolicy.CREATE_NULL_AS_BLANK);
	if (null != cell) {

	    CellType cellType = cell.getCellTypeEnum();

	    if (cellType == CellType.FORMULA) {
		cellType = cell.getCachedFormulaResultTypeEnum();
	    }

	    switch (cellType) {
		case BOOLEAN:
		    cellValue = cell.getBooleanCellValue() + "";
		    break;
		case NUMERIC:
		    if (HSSFDateUtil.isCellDateFormatted(cell)) {
			SimpleDateFormat ft = new SimpleDateFormat(DATE_PATTERN);

			cellValue = ft.format(cell.getDateCellValue());

		    } else {
			DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
			decimalFormatSymbols.setDecimalSeparator('.');
			DecimalFormat nf = new DecimalFormat(ConversionUtils.DECIMAL_PATTERN_OPTIONAL);
			nf.setDecimalFormatSymbols(decimalFormatSymbols);
			cellValue = nf.format(cell.getNumericCellValue());
		    }
		    break;
		case STRING:
		    cellValue = cell.getStringCellValue();
		    break;
		case BLANK:
		    break;

		default:
		    throw new IllegalStateException("Unsupported cell type: " + cellType + " [Row:" + rowNum + " Col:" + colNum + "]");
	    }

	    LOGGER.trace("[Row:" + rowNum + " Col:" + colNum + "Value:" + cellValue + "]");
	}

	return StringUtils.trimToNull(cellValue);
    }

}
