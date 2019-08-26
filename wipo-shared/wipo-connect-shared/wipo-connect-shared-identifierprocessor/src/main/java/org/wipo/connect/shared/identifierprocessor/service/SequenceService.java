package org.wipo.connect.shared.identifierprocessor.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.shared.identifierprocessor.entity.IpImport;
import org.wipo.connect.shared.identifierprocessor.entity.IpImportFile;
import org.wipo.connect.shared.identifierprocessor.entity.Sequence;
import org.wipo.connect.shared.identifierprocessor.repository.IpImportFileRepository;
import org.wipo.connect.shared.identifierprocessor.repository.IpImportRepository;
import org.wipo.connect.shared.identifierprocessor.repository.SequenceRepository;

@Service
public class SequenceService {

	@Autowired
	SequenceRepository<Sequence> sequenceRepository;

	@Autowired
	IpImportFileRepository<IpImportFile> ipImportFileRepository;

	@Autowired
	IpImportRepository<IpImport> ipImportRepository;

	
	@Transactional
	public List<Sequence> findByName() {
		return (List<Sequence>) sequenceRepository.findAll();
	}

	@Transactional
	public long findByFileName(String fileName) {
		System.out.println("fileName  for counting  from ip_import table :: " + fileName);
		List<IpImportFile> ipImportFile = ipImportFileRepository.findByFileName(fileName);
		System.out.println("ipImportFile" + ipImportFile.toString());	
		System.out.println("size :: " +ipImportFile.size());
		System.out.println("long size :: " +(long)ipImportFile.size());
		return (long)ipImportFile.size();
	}

	@Transactional
	public void findByFileName(Long fkIpImport, String fileType, String fileName, Long fileSize, String contentType) {
		ipImportFileRepository.insertIpImortFile(fkIpImport, fileType, fileName, fileSize, contentType);

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<IpImport> findByCode() {
		return (List<IpImport>) ipImportRepository.findByCode();
	}

	@Transactional
	public void findByName(String importCode, Long fkStatus, Date startDate, Date endDate) {
		ipImportRepository.insertIpImort(importCode, fkStatus, startDate, endDate);
	}

}
