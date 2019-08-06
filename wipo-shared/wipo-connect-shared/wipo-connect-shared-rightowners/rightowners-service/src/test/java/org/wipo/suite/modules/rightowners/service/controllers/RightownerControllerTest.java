package org.wipo.suite.modules.rightowners.service.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import org.wipo.suite.modules.rightowners.service.PrepareRO;
import org.wipo.suite.modules.rightowners.service.RightownerServiceImpl;

/**
 * 
 * @author Devdyuti
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class RightownerControllerTest {
	
	
	@Mock
	RightownerServiceImpl rightOwnerService=mock(RightownerServiceImpl.class);
	
	@Mock
	RightownerDTO dtoRightOwner=mock(RightownerDTO.class);
	
	@Mock
	HttpServletResponse response=mock(HttpServletResponse.class);
	
	@InjectMocks
	RightownerController rightownerController;
	
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
	public void testCheck() throws IOException {
		doNothing().when(response).setHeader("Custom-Header", "Rightowner Sevice");
		response.setHeader("Custom-Header", "Rightowner Sevice");
		verify(response, times(1)).setHeader("Custom-Header", "Rightowner Sevice");
		
		doNothing().when(response).setHeader("Status", "Ok");
		response.setHeader("Status", "Ok");
		verify(response, times(1)).setHeader("Status", "Ok");
		
		when(response.getWriter()).thenReturn(new PrintWriter("test"));
		
		rightownerController.check(response);
	}
	@Test
	public void testGetRightowners() {		
		when(rightOwnerService.listRightowners()).thenReturn(list_rightowner_response);
		assertThat(rightownerController.getRightowners(), is(notNullValue()));
	}
	@Test
	public void testListRightownersWithHirarchy() {
		Pageable pageableRequest = PageRequest.of(0, 25);
		Page<RightOwners> page_rightowner=new PageImpl<>(list_rightowner,pageableRequest,71253);
		
		when(rightOwnerService.listRightownersWithHirarchy()).thenReturn(page_rightowner);
		assertThat(rightownerController.listRightownersWithHirarchy(), is(notNullValue()));
	}
	@Test
	public void testGetRightownerByMainId() {
		doNothing().when(dtoRightOwner).setMain_id("1-P");
		dtoRightOwner.setMain_id("1-P");
		verify(dtoRightOwner, times(1)).setMain_id("1-P");
		
		RightownerResponse rightownerResponse=list_rightowner_response.get(0);
		
		when(rightOwnerService.searchByMainId(dtoRightOwner)).thenReturn(rightownerResponse);
		assertThat(rightownerController.getRightownerByMainId("1-P"), is(notNullValue()));
	}
	@Test
	public void testUpdateRightOwners() {
		rightOwner.setMainId("1-PX");
		rightOwner.setSex("F");
		
		when(rightOwnerService.updateRightOwners(rightOwner, "1-P")).thenReturn(rightOwner);
		assertThat(rightownerController.updateRightOwners(rightOwner, "1-P"), is(notNullValue()));
	}
	@Test
	public void testInsertRightowner() {
		when(rightOwnerService.insert(rightOwner, "1-P")).thenReturn(rightOwner);
		assertThat(rightownerController.insertRightowner(rightOwner, "1-P"), is(notNullValue()));
	}
	@Test
	public void testDeleteRightowner() {
		doNothing().when(rightOwnerService).deleteByMainId(rightOwner, "1-P");
		rightownerController.deleteRightowner(rightOwner, "1-P");
		verify(rightOwnerService, times(1)).deleteByMainId(rightOwner, "1-P");
		
	}
}
