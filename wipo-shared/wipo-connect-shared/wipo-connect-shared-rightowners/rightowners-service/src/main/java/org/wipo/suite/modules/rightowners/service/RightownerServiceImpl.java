/**
 * Rightowners Service Impl
 * @author Suraj
 *
 */

package org.wipo.suite.modules.rightowners.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.wipo.suite.modules.rightowners.dal.dto.RightownerDTO;
import org.wipo.suite.modules.rightowners.dal.dto.RightownerResponse;
import org.wipo.suite.modules.rightowners.dal.entity.RightOwners;
import org.wipo.suite.modules.rightowners.dal.repositories.RightownerRepository;



@Service
public class RightownerServiceImpl implements RightownerSevice {
	@Autowired
	RightownerRepository rightownerRepository;

	@Override
	public RightOwners getRightOwnersByMainId(String mainId) {
		RightOwners rightOwners = rightownerRepository.findByMainId(mainId);
		return rightOwners;
	}

	@Override
	public String checkavailability() {
		return "Rightowner Service is up and running...";
	}

	@Override
	public List<RightownerResponse> listRightowners() {
		Pageable pageableRequest = PageRequest.of(0, 25);
		Page<RightOwners> users = rightownerRepository.findAll(pageableRequest);
		List<RightOwners> userEntities = users.getContent();
		// Create Conversion Type
		Type listType = new TypeToken<List<RightownerResponse>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<RightownerResponse> returnValue = new ModelMapper().map(userEntities, listType);
		return returnValue;	
	}

	@Override
	public Page<RightOwners> listRightownersWithHirarchy() {
		Pageable pageableRequest = PageRequest.of(0, 25);
		Page<RightOwners> rightOwners = rightownerRepository.findAll(pageableRequest);
		return rightOwners;
	}
	
	@Override
	public RightOwners updateRightOwners(RightOwners rightOwners, String mainid) {
		RightOwners updateRightOwners = getRightOwnersByMainId(mainid);
		updateRightOwners.setSex(rightOwners.getSex());
		updateRightOwners.setType(rightOwners.getType());
		updateRightOwners.setDeathDate(rightOwners.getDeathDate());
		updateRightOwners.setBirthDate(rightOwners.getBirthDate());
		updateRightOwners.setBirthPlace(rightOwners.getBirthPlace());
		updateRightOwners.setBirthState(rightOwners.getBirthState());
		//updateRightOwners.setBirthCountry(rightOwners.getBirthCountry());
		updateRightOwners.setAmendmentTimestamp(rightOwners.getAmendmentTimestamp());
		updateRightOwners.setAccount(rightOwners.getAccount());
		//updateRightOwners.setStatus(rightOwners.getStatus());
		updateRightOwners.setMaritalStatus(rightOwners.getMaritalStatus());
		updateRightOwners.setUserInsert(rightOwners.getUserInsert());
		updateRightOwners.setUserUpdate(rightOwners.getUserUpdate());
		updateRightOwners.setDateUpdate(rightOwners.getDateUpdate());
		updateRightOwners.setCreationDate(rightOwners.getCreationDate());
		updateRightOwners.setDoAffiliation(rightOwners.getDoAffiliation());
		updateRightOwners.setIsAffiliated(rightOwners.getIsAffiliated());
		//updateRightOwners.setFkCmo(rightOwners.getFkCmo());
		updateRightOwners.setDeleted(rightOwners.getDeleted());
		updateRightOwners.setDummy(rightOwners.getDummy());
		updateRightOwners.setDateInsert(rightOwners.getDateInsert());
		updateRightOwners.setSyncVersion(rightOwners.getSyncVersion());
		System.out.println("updateRightOwners"+updateRightOwners);
		rightownerRepository.save(updateRightOwners);

		return updateRightOwners;
	}

	@Override
	public RightownerResponse searchByMainId(RightownerDTO dtoName) {
		return findRightownerByMainId(dtoName.getMain_id());
	}

	private RightownerResponse findRightownerByMainId(String main_id) {
		RightOwners rightOwner;
		rightOwner = rightownerRepository.findByMainId(main_id);
		RightownerResponse rightownerResponse = new RightownerResponse();		
		rightownerResponse.setMainId(rightOwner.getMainId());
		rightownerResponse.setType(rightOwner.getType());
		rightownerResponse.setSex(rightOwner.getSex());
		rightownerResponse.setBirthDate(rightOwner.getBirthDate());
		rightownerResponse.setDeathDate(rightOwner.getDeathDate());
		rightownerResponse.setBirthPlace(rightOwner.getBirthPlace());
		rightownerResponse.setBirthState(rightOwner.getBirthState());
		rightownerResponse.setAmendmentTimestamp(rightOwner.getAmendmentTimestamp());
		rightownerResponse.setMaritalStatus(rightOwner.getMaritalStatus());
		rightownerResponse.setUserInsert(rightOwner.getUserInsert());
		rightownerResponse.setUserUpdate(rightOwner.getUserUpdate());
		rightownerResponse.setDateUpdate(rightOwner.getDateUpdate());
		rightownerResponse.setCreationDate(rightOwner.getCreationDate());
		rightownerResponse.setDoAffiliation(rightOwner.getDoAffiliation());
		rightownerResponse.setIsAffiliated(rightOwner.getIsAffiliated());
		rightownerResponse.setDeleted(rightOwner.getDeleted());
		rightownerResponse.setDummy(rightOwner.getDummy());
		rightownerResponse.setDateInsert(rightOwner.getDateInsert());
		rightownerResponse.setSyncVersion(rightOwner.getSyncVersion());
		return rightownerResponse;
	}

	@Override
	public RightOwners insert(RightOwners rightOwner, String mainId) {
		rightOwner.setDeleted(0);
		rightOwner.setDummy(0);
		rightOwner.setMainId(mainId);
		return rightownerRepository.save(rightOwner);
	}

	@Override
	public void deleteByMainId(RightOwners rightOwner, String mainId) {
		
		System.out.println("mainId"+mainId);
		System.out.println("rightOwner"+rightOwner);
		rightownerRepository.delete(rightOwner);
		}

}
