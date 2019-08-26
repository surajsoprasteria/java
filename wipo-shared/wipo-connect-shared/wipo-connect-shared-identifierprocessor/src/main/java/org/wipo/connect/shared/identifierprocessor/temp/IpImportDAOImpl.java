/*

 
package org.wipo.connect.shared.identifierprocessor.temp;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.utils.ConversionUtils;


*//**
 * The Class IpImportDAOImpl.
 *//*
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class IpImportDAOImpl implements IpImportDAO {

    *//** The Constant IMPORT_PREFIX. *//*
	
    private static final String IMPORT_PREFIX = "IP-";

    *//** The ip import mapper. *//*
    @Autowired
    private IpImportMapper ipImportMapper;

    public IpImport insertIpImport(IpImport ipImport) {
	if (StringUtils.isEmpty(ipImport.getImportCode())) {
	    SimpleDateFormat sdf = new SimpleDateFormat(ConversionUtils.DATE_TIME_STAMP_MILLI);
	    String code = IMPORT_PREFIX + sdf.format(new Date());
	    ipImport.setImportCode(code);
	}
	int rowNum = ipImportMapper.insertIpImport(ipImport);
	Long idIpImport;
	if (0 < rowNum) {
	    idIpImport = ipImport.getIdIpImport();
	    for (IpImportFile ipImportFile : ipImport.getIpImportFileList()) {
		ipImportFile.setFkIpImport(idIpImport);
		ipImportMapper.insertIpImportFile(ipImportFile);
	    }
	}
	return ipImport;
    }
    
    @Override
    public int updateStatus(Long idIpImport, Long idStatus) {
	return ipImportMapper.updateImportStatus(idIpImport, idStatus);
    }

    @Override
    public int updateStatus(Long idIpImport, ImportStatusEnum statusCode) {
	return ipImportMapper.updateImportStatusCode(idIpImport, statusCode);
    }

    @Override
    public List<IpImport> findIpImportFromStatus(ImportStatusEnum status) {
	List<IpImport> ipImportList = ipImportMapper.findIpImportFromStatus(status);
	return ipImportList;
    }

    @Override
    public List<IpImport> findAll() {
	return ipImportMapper.findAll();
    }

    @Override
    public int updateImportStartDate(Long idIpImport, Date startDate) {
	return ipImportMapper.updateImportStartDate(idIpImport, startDate);
    }

    @Override
    public int updateImportEndDate(Long idIpImport, Date endDate) {
	return ipImportMapper.updateImportEndDate(idIpImport, endDate);
    }

    @Override
    public IpImportFile findIpImportFileById(Long id) {
	return ipImportMapper.findIpImportFileById(id);
    }

    @Override
    public int delete(Long id) {
	throw new NotImplementedException("not implemented: " + WccUtils.getMethodName());
    }

    @Override
    public IpImport find(Long id) {
	throw new NotImplementedException("not implemented: " + WccUtils.getMethodName());
    }

    @Override
    public IpImport merge(IpImport obj) {
	throw new NotImplementedException("not implemented: " + WccUtils.getMethodName());
    }

    @Override
    public int insertIpImportFile(IpImportFile ipImportFile) {
	return ipImportMapper.insertIpImportFile(ipImportFile);
    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public ImportStatusFlat findImportStatusByCode(ImportStatusEnum code) {
	return ipImportMapper.findByCode(code);
    }


    public Long countIpImportFileByName(String fileName) {
	return ipImportMapper.countIpImportFileByName(fileName);
    }

    @Override
    public IpImport updateRowResult(IpImport ipImport) {
	ipImportMapper.updateRowResult(ipImport);
	return ipImport;
    }

    @Override
    public int markAllPendingAsError() {
	return ipImportMapper.markAllPendingAsError();
    }
}
*/