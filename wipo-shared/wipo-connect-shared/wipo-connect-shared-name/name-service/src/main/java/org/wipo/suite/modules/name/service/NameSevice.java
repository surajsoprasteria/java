package org.wipo.suite.modules.name.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.wipo.suite.modules.name.dal.entity.Name;
import org.wipo.suite.modules.name.dal.entity.RightOwnersName;
import org.wipo.suite.modules.name.dto.NameDTO;

public interface NameSevice {
	
	Optional<Name> getNameById(Long idName);
	
	Page<Name> listNames(Integer pageNo, Integer pageSize, String sortBy);
	Name update(Name name);
	String insert(Name name);
	Name searchByMainId(NameDTO searchCriteria);
	Boolean deleteByMainId(NameDTO dtoName);
	
	//List of Right Owner Names
	Page<RightOwnersName> listRightOwnerNames();
	
	Boolean hardDeleteByMainId(NameDTO dtoName);
}
