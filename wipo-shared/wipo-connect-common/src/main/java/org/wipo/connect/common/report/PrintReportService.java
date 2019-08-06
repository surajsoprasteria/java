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

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.el.CustomFunctions;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.report.bean.ReportInfoEnum;
import org.wipo.connect.common.report.bean.ReportTypeEnum;
import org.wipo.connect.common.report.bean.RequestBean;
import org.wipo.connect.common.utils.ConstantUtils;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleCsvExporterConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public abstract class PrintReportService implements ReportService {

    @Value("#{configProperties['report.template-dir']}/")
    protected String PATH_FILE_JASPER;

    @Value("#{configProperties['report.header-file']}")
    protected String HEADER;

    @Value("#{configProperties['report.footer-file']}")
    protected String FOOTER;

    @Value("#{configProperties['report.logo-file']}")
    protected String IMG_PATH;

    protected static final String FIELD_SEPARATOR = ",";

    protected static final int PAGE_SIZE = 200;

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(PrintReportService.class);

    @Override
    public abstract byte[] createReport(ReportInfoEnum reportInfo, String tipoReport, RequestBean requestBean, String outputFileName) throws WccException, IOException;

    protected String getCurrencyLabel() {
	CustomFunctions.init();
	return CustomFunctions.getCurrencyLabel();
    }

    protected abstract int getAmountScale();

    protected abstract String getCurrentCmoCode();

    protected String getNumberFormatPattern(int scale) {
	String pattern = "0.0";
	for (int i = 1; i < scale; i++) {
	    pattern += "0";
	}
	return pattern;
    }

    protected String getNomeFileJasper(String codeReport, String tipoReport) {

	String nameFileJasper = PATH_FILE_JASPER;

	if (StringUtils.equals(tipoReport, ReportTypeEnum.XLS.getValue())) {
	    nameFileJasper += ReportInfoEnum.getTipoOutputByReportCode(codeReport).getReportJasperName() + ReportTypeEnum.XLS.getValue();
	} else if (StringUtils.equals(tipoReport, ReportTypeEnum.CSV.getValue())) {
	    nameFileJasper += ReportInfoEnum.getTipoOutputByReportCode(codeReport).getReportJasperName() + ReportTypeEnum.CSV.getValue();
	} else if (StringUtils.equals(tipoReport, ReportTypeEnum.PDF.getValue())) {
	    nameFileJasper += ReportInfoEnum.getTipoOutputByReportCode(codeReport).getReportJasperName() + ReportTypeEnum.PDF.getValue();
	}

	return nameFileJasper + ".jasper";
    }

    protected byte[] generatePdf(Map<String, Object> applyMap, String nomeFileJasper, List<?> dataSource) throws WccException, FileNotFoundException {

	byte[] out = null;
	JasperPrint jasperPrint = null;
	try {

	    JasperReportsContext jasperReportsContext = DefaultJasperReportsContext.getInstance();
	    JRPropertiesUtil jrPropertiesUtil = JRPropertiesUtil.getInstance(jasperReportsContext);
	    jrPropertiesUtil.setProperty("net.sf.jasperreports.default.font.name", "ArialUnicode");

	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperReportsContext, new File(nomeFileJasper));
	    if (jasperReport == null) {
		LOGGER.error("generatePdf() -NO!!! Compiled report loaded");
		throw new FileNotFoundException();
	    }
	    if (dataSource == null) {
		LOGGER.error("generatePdf() -NO!!! DataSource");
		return out;
	    }
	    if (applyMap == null) {
		applyMap = new HashMap<>();
	    }

	    applyMap.put(JRParameter.REPORT_LOCALE, Locale.UK);
	    applyMap.put("SUBREPORT_DIR", PATH_FILE_JASPER);
	    applyMap.put("HEADER_HTML", FileUtils.readFileToString(new File(HEADER), Charset.defaultCharset()));
	    applyMap.put("IMG_HEADER_PATH", IMG_PATH);
	    jasperPrint = JasperFillManager.fillReport(jasperReport, applyMap, new JRBeanCollectionDataSource(dataSource));

	    // JRPdfExporter exporter = new JRPdfExporter();
	    // exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	    // exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("C:\\Users\\pasquale\\Desktop\\report.pdf"));
	    // SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
	    // configuration.setPermissions(PdfWriter.ALLOW_PRINTING | PdfWriter.ALLOW_COPY);
	    // exporter.setConfiguration(configuration);
	    // exporter.exportReport();

	    out = JasperExportManager.exportReportToPdf(jasperPrint);
	} catch (Exception e) {
	    LOGGER.error("Error in generatePdf: {}", e);
	    if (e.getCause() instanceof FileNotFoundException) {
		throw new FileNotFoundException();
	    } else {
		throw new WccException(e);
	    }
	}
	return out;
    }

    protected byte[] generateXls(String nomeFileJasper, Map<String, Object> applyMap, List<?> dataSource) throws WccException, FileNotFoundException {
	return generateXls(nomeFileJasper, applyMap, dataSource, Boolean.FALSE, null);
    }

    protected byte[] generateXls(String nomeFileJasper, Map<String, Object> applyMap, List<?> dataSource, Boolean singolaPaginaPerFoglio, String[] nomiPagine)
	    throws WccException, FileNotFoundException {
	byte[] out = null;

	try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(new File(nomeFileJasper));
	    if (jasperReport == null) {
		LOGGER.error("generateXls() -NO!!! Compiled report loaded");
		throw new FileNotFoundException();
	    }
	    if (dataSource == null) {
		LOGGER.error("generateXls() -NO!!! DataSource");
		return out;
	    }
	    if (applyMap == null) {
		applyMap = new HashMap<>();
	    }
	    applyMap.put(JRParameter.REPORT_LOCALE, Locale.UK);
	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, applyMap, new JRBeanCollectionDataSource(dataSource));

	    JRXlsxExporter exporterXLSX = new JRXlsxExporter();
	    exporterXLSX.setExporterInput(new SimpleExporterInput(jasperPrint));
	    exporterXLSX.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));

	    SimpleXlsxReportConfiguration configXlsx = new SimpleXlsxReportConfiguration();
	    if (singolaPaginaPerFoglio != null) {
		configXlsx.setOnePagePerSheet(singolaPaginaPerFoglio);
	    } else {
		configXlsx.setOnePagePerSheet(false);
	    }
	    configXlsx.setWhitePageBackground(false);
	    configXlsx.setRemoveEmptySpaceBetweenRows(true);
	    configXlsx.setDetectCellType(true);
	    configXlsx.setCollapseRowSpan(true);
	    if (nomiPagine != null && nomiPagine.length > 0) {
		configXlsx.setSheetNames(nomiPagine);
	    }
	    exporterXLSX.setConfiguration(configXlsx);

	    exporterXLSX.exportReport();
	    out = baos.toByteArray();
	    baos.flush();
	    baos.close();
	} catch (Exception e) {
	    LOGGER.error("Error:{}", e.getMessage());
	    if (e.getCause() instanceof FileNotFoundException) {
		throw new FileNotFoundException();
	    } else {
		throw new WccException(e);
	    }
	}
	return out;
    }

    protected byte[] generateCsv(String nomeFileJasper, Map<String, Object> applyMap, List<?> dataSource) throws WccException, FileNotFoundException {
	byte[] out = null;

	try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(new File(nomeFileJasper));
	    if (jasperReport == null) {
		LOGGER.error("generateCsv() -NO!!! Compiled report loaded");
		throw new FileNotFoundException();
	    }
	    if (dataSource == null) {
		LOGGER.error("generateCsv() -NO!!! DataSource");
		return out;
	    }
	    if (applyMap == null) {
		applyMap = new HashMap<>();
	    }
	    applyMap.put(JRParameter.REPORT_LOCALE, Locale.UK);
	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, applyMap, new JRBeanCollectionDataSource(dataSource));
	    JRCsvExporter csvExporter = new JRCsvExporter();

	    csvExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	    csvExporter.setExporterOutput(new SimpleWriterExporterOutput(baos));

	    SimpleCsvExporterConfiguration configCsv = new SimpleCsvExporterConfiguration();
	    configCsv.setFieldDelimiter(FIELD_SEPARATOR);
	    configCsv.setRecordDelimiter(ConstantUtils.CSV_NEW_LINE_SEPARATOR);
	    csvExporter.setConfiguration(configCsv);

	    csvExporter.exportReport();
	    out = baos.toByteArray();
	    baos.flush();
	    baos.close();
	} catch (Exception e) {
	    LOGGER.error("Error:{}", e.getMessage());
	    if (e.getCause() instanceof FileNotFoundException) {
		throw new FileNotFoundException();
	    } else {
		throw new WccException(e);
	    }
	}
	return out;
    }

    protected CSVPrinter generateCsv(List<?> row, String fileName, CSVPrinter csvPrinter) throws WccException {

	try {

	    if (csvPrinter == null) {
		File file = new File(fileName);
		if (!file.exists()) {
		    File directory = new File(file.getParent());
		    directory.mkdirs();
		    file.createNewFile();
		}

		OutputStreamWriter osw = new OutputStreamWriter((new FileOutputStream(file)), StandardCharsets.UTF_8);
		BufferedWriter bufferedWriter = new BufferedWriter(osw);
		bufferedWriter.write(ConstantUtils.BOM_CHAR); // Write BOM

		CSVFormat csvFormat = CSVFormat.DEFAULT.withRecordSeparator(ConstantUtils.CSV_NEW_LINE_SEPARATOR);
		csvFormat = csvFormat.withIgnoreSurroundingSpaces(true).withNullString("");
		csvPrinter = new CSVPrinter(bufferedWriter, csvFormat);
	    }

	    if (row != null && !row.isEmpty()) {
		csvPrinter.printRecord(row);
	    } else {
		csvPrinter.flush();
		csvPrinter.close();
		csvPrinter = null;
	    }

	    return csvPrinter;

	} catch (Exception e) {

	    if (csvPrinter != null) {
		try {
		    csvPrinter.close();
		    csvPrinter.flush();
		    csvPrinter = null;
		} catch (IOException e1) {
		    csvPrinter = null;
		}
	    }
	    LOGGER.error("Error: {}", e.getMessage());
	    throw new WccException(e);
	}
    }

}
