package org.wipo.connect.shared.identifierprocessor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.shared.identifierprocessor.entity.ImportStatusFlat;
import org.wipo.connect.shared.identifierprocessor.repository.ImportStatusFlatRepository;


@Service
public class ImportStatusFlatService {

	@Autowired
	ImportStatusFlatRepository<ImportStatusFlat> importStatusFlatRepository;


	@Transactional
	public ImportStatusFlat findByName() { 
		return importStatusFlatRepository.findByCode();
	}


}
