package org.wipo.connect.shared.identifierprocessor.temp;

import java.util.Date;
import java.util.List;

import org.wipo.connect.shared.identifierprocessor.entity.ImportStatusFlat;

public class IpImportMapperImpl implements IpImportMapper {

	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(IpImport record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public IpImport selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKey(IpImport object) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertIpImport(IpImport ipImport) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertIpImportFile(IpImportFile ipImportFile) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<IpImport> findIpImportFromStatus(ImportStatusEnum status) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IpImport> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateImportStatus(Long idIpImport, Long idStatus) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateImportStatusCode(Long idIpImport, ImportStatusEnum status) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateImportStartDate(Long idIpImport, Date startDate) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateImportEndDate(Long idIpImport, Date endDate) {
		// TODO Auto-generated method stub
		return 0;
	}

	public IpImportFile findIpImportFileById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ImportStatusFlat findByCode(ImportStatusEnum code) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long countIpImportFileByName(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateRowResult(IpImport ipImport) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int markAllPendingAsError() {
		// TODO Auto-generated method stub
		return 0;
	}

}
