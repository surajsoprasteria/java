package org.wipo.suite.modules.rightowners.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.wipo.suite.modules.rightowners.dal.dto.RightownerDTO;
import org.wipo.suite.modules.rightowners.dal.dto.RightownerResponse;
import org.wipo.suite.modules.rightowners.dal.entity.RightOwners;
import org.wipo.suite.modules.rightowners.dal.repositories.RightownerRepository;
/**
 * 
 * @author Devdyuti
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class RightownerServiceImplTest {
	
	@Mock
	RightownerRepository rightownerRepository=mock(RightownerRepository.class);
	

	@InjectMocks
	RightownerServiceImpl rightownerServiceImpl;
	
	private List<RightOwners> list_rightowner;
	private List<RightownerResponse> list_rightowner_response;
	private RightOwners rightOwner;
	
	
	@Before
	public void setup() throws Exception{
		MockitoAnnotations.initMocks(this);
	}
		
	
	@Before
	public void prepareRightOwnerResponseList() {
		list_rightowner_response=PrepareRO.getRightownerResponseList();
	}
	
	@Before
	public void prepareRightOwnerList() {
		list_rightowner=PrepareRO.getRighOwnerList();		
	}
	@Before
	public void prepareRightOwner() {
		rightOwner=new RightOwners();
		rightOwner=PrepareRO.getRightOwners();
	}
	
	
	@Test
	public void testGetRightOwnersByMainId() {
		
		RightOwners rightOwners=list_rightowner.get(0);
		
		when(rightownerRepository.findByMainId("1-P")).thenReturn(rightOwners);
		assertThat(rightownerServiceImpl.getRightOwnersByMainId("1-P"), is(notNullValue()));
		assertEquals(String.valueOf(PrepareRO.formatDate("2018-04-17")), String.valueOf(rightOwners.getDateInsert()));
	}
	@Test
	public void testCheckavailability() {
		assertEquals("Rightowner Service is up and running...", rightownerServiceImpl.checkavailability());
	}
	@Test
	public void testListRightowners() {
		Pageable pageableRequest = PageRequest.of(0, 25);
		Page<RightOwners> page_rightowner=new PageImpl<>(list_rightowner, pageableRequest, 71226);
		when(rightownerRepository.findAll(pageableRequest)).thenReturn(page_rightowner);
		assertThat(rightownerServiceImpl.listRightowners(), is(notNullValue()));
		assertEquals(25, page_rightowner.getSize());
	}
	@Test
	public void testListRightownersWithHirarchy() {
		Pageable pageableRequest = PageRequest.of(0, 25);
		Page<RightOwners> page_rightowner=new PageImpl<>(list_rightowner,pageableRequest,71253);
		
		when(rightownerRepository.findAll(pageableRequest)).thenReturn(page_rightowner);
		assertThat(rightownerServiceImpl.listRightownersWithHirarchy(), is(notNullValue()));
		
		assertEquals(25, page_rightowner.getSize());
		assertEquals(2851, page_rightowner.getTotalPages());
	}
	@Test
	public void testUpdateRightOwners() {
		rightOwner.setMainId("1-PX");
		rightOwner.setSex("F");
		when(rightownerRepository.findByMainId("1-P")).thenReturn(rightOwner);
		assertThat(rightownerServiceImpl.updateRightOwners(rightOwner, "1-P"), is(notNullValue()));
		assertNotEquals("1-P", rightOwner.getMainId());
		assertNotEquals("M", rightOwner.getSex());
		
	}
	@Test
	public void testSearchByMainId() {
		RightownerDTO dto_searchCriteria=new RightownerDTO();
		dto_searchCriteria.setMain_id("1-P");
		RightownerResponse rightownerResponse=list_rightowner_response.get(0);
		
		when(rightownerRepository.findByMainId("1-P")).thenReturn(rightOwner);
		assertThat(rightownerServiceImpl.searchByMainId(dto_searchCriteria), is(notNullValue()));
		assertEquals(String.valueOf(PrepareRO.formatDate("2056-01-27")), String.valueOf(rightownerResponse.getBirthDate()));
	}
	@Test
	public void testInsert() {
		rightOwner.setDeleted(0);
		rightOwner.setDummy(0);
		when(rightownerRepository.save(rightOwner)).thenReturn(rightOwner);
		assertThat(rightownerServiceImpl.insert(rightOwner, "P-1088665098"), is(notNullValue()));
		
		long idcmo=rightOwner.getCmo().getIdCmo();
		assertEquals(151L, idcmo);
		assertNotEquals("3", rightOwner.getSyncVersion());
	}
	@Test
	public void testDeleteByMainId() {
		
		doNothing().when(rightownerRepository).delete(rightOwner);
		
		rightownerServiceImpl.deleteByMainId(rightOwner, "P-1088665098");
		verify(rightownerRepository, times(1)).delete(rightOwner);
	}
	
}
