/**
 * Rightowners Service
 * @author Suraj
 *
 */


package org.wipo.suite.modules.rightowners.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.wipo.suite.modules.rightowners.dal.dto.RightownerDTO;
import org.wipo.suite.modules.rightowners.dal.dto.RightownerResponse;
import org.wipo.suite.modules.rightowners.dal.entity.RightOwners;

public interface RightownerSevice {
	
	RightOwners getRightOwnersByMainId(String mainId);

	String checkavailability();

	List<RightownerResponse> listRightowners();
	
	Page<RightOwners> listRightownersWithHirarchy();
	
	RightOwners updateRightOwners(RightOwners rightowner, String mainid);

	RightOwners insert(RightOwners rightowner,String mainid);	
	
	RightownerResponse searchByMainId(RightownerDTO searchCriteria);	
	
	void deleteByMainId(RightOwners rightOwner, String mainId);
}
