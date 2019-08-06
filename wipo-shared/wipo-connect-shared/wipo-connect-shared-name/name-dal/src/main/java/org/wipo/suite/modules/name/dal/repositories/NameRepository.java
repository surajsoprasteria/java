package org.wipo.suite.modules.name.dal.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.wipo.suite.modules.name.dal.entity.Name;

@Repository
public interface NameRepository extends PagingAndSortingRepository<Name, Long> {

	@Query("SELECT n FROM Name n WHERE n.mainId = :mainId")
	public Name find(@Param("mainId") String mainId);

	@Query("DELETE FROM Name n WHERE n.mainId = :mainId")
	public Boolean delete(@Param("mainId") String mainId);

}
