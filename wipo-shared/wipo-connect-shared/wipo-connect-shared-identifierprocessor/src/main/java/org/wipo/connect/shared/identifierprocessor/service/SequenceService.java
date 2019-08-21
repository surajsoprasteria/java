package org.wipo.connect.shared.identifierprocessor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.shared.identifierprocessor.entity.Sequence;
import org.wipo.connect.shared.identifierprocessor.repository.SequenceRepository;

@Service
public class SequenceService {

	@Autowired
	SequenceRepository<Sequence> sequenceRepository;


	@Transactional
	public List<Sequence> findByName() {
		return (List<Sequence>) sequenceRepository.findAll();
	}


}
