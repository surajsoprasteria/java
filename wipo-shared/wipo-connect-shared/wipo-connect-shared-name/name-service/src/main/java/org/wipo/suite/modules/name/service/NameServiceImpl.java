package org.wipo.suite.modules.name.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.wipo.suite.modules.name.dal.entity.Name;
import org.wipo.suite.modules.name.dal.entity.RightOwnersName;
import org.wipo.suite.modules.name.dal.repositories.NameRepository;
import org.wipo.suite.modules.name.dal.repositories.RightOwnersNameRepository;
import org.wipo.suite.modules.name.dto.NameDTO;

@Service
public class NameServiceImpl implements NameSevice {
	@Autowired
	NameRepository nameRepository;

	@Autowired
	RightOwnersNameRepository rightOwnersNameRepository;

	@Override
	public Optional<Name> getNameById(Long idName) {
		return nameRepository.findById(idName);
	}

	@Override
	public Page<Name> listNames(Integer pageNo, Integer pageSize, String sortBy) {		
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		
		return nameRepository.findAll(paging);
	}

	@Override
	public Name update(Name name) {
		Name nameEntity = nameRepository.find(name.getMainId());
		nameEntity.setName(name.getName());
		nameEntity.setFirstName(name.getFirstName());
		nameEntity.setMainId(name.getMainId());
		nameEntity.setNameType(name.getNameType());
		nameEntity.setAmendmentTimestamp(name.getAmendmentTimestamp());
		nameEntity.setCreationTimestamp(name.getCreationTimestamp());
		nameEntity.setDeleted(name.getDeleted());
		return nameRepository.save(nameEntity);
	}

	@Override
	public Name searchByMainId(NameDTO dtoName) {
		return findNameBySearchMainId(dtoName.getMainId());
	}

	private Name findNameBySearchMainId(String mainId) {
		return nameRepository.find(mainId);
	}

	@Override
	public String insert(Name name) {
		// nameRepository.save(name);
		if (nameRepository.find(name.getMainId()) != null) {
			// throw new RuntimeException("Duplicate Entry found for ID:
			// "+name.getMainId());
			return "Please provide unique main id to insert";
		} else {
			nameRepository.save(name);
			return "Saved Successfully";
		}

	}

	@Override
	public Boolean deleteByMainId(NameDTO dtoName) {
		return deleteName(dtoName.getMainId());
	}

	private Boolean deleteName(String mainId) {

		
		Name name = nameRepository.find(mainId);
		if (name.getDeleted() == 1) {
			return false;
		} else {
			name.setDeleted(1);
			nameRepository.save(name);
			return true;
		}

	}

	@Override
	public Page<RightOwnersName> listRightOwnerNames() {
		Pageable sortByIdName = PageRequest.of(0, 2);
		return rightOwnersNameRepository.findAll(sortByIdName);
	}
	
	@Override
	public Boolean hardDeleteByMainId(NameDTO dtoName) {
		// interestedpartyName value delete
	    // interestedparty value must be delete
	    // last delete name 
		return hardDeleteName(dtoName.getMainId());
	}

	private Boolean hardDeleteName(String mainId) {
		return false;
	}
}
