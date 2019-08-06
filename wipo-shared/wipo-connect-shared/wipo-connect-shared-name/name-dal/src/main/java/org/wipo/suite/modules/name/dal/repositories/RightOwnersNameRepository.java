package org.wipo.suite.modules.name.dal.repositories;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.wipo.suite.modules.name.dal.entity.RightOwnersName;

@Repository
public interface RightOwnersNameRepository extends PagingAndSortingRepository<RightOwnersName, Long>{
	

}
