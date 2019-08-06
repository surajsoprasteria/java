package org.wipo.suite.modules.rightowners.dal.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.wipo.suite.modules.rightowners.dal.entity.RightOwners;

@Repository
public interface RightownerRepository extends PagingAndSortingRepository<RightOwners, Long> {

/*	@Query("SELECT n FROM RightOwners n WHERE n.mainId = :main_id")
	public List<RightOwners> find(@Param("main_id") String main_id);*/
	
	@Query("SELECT n FROM RightOwners n WHERE n.mainId = :main_id")
	public RightOwners findByMainId(@Param("main_id") String main_id);

	@Query("DELETE FROM RightOwners n WHERE n.mainId = :main_id")
	public Boolean delete(@Param("main_id") String main_id);
}
