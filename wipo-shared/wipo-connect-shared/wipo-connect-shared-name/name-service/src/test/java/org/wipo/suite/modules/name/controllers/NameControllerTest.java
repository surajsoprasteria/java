package org.wipo.suite.modules.name.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.util.Date;
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
import org.springframework.data.domain.Sort;
import org.wipo.suite.modules.name.dal.entity.Name;
import org.wipo.suite.modules.name.dal.entity.RightOwnersName;
import org.wipo.suite.modules.name.dto.NameDTO;
import org.wipo.suite.modules.name.service.NameServiceImpl;
import org.wipo.suite.modules.name.service.PrepareName;
import org.wipo.suite.modules.name.service.controllers.NameController;

@RunWith(MockitoJUnitRunner.class)
public class NameControllerTest {
	
	
	@Mock
	NameServiceImpl nameService=mock(NameServiceImpl.class);
	
	@InjectMocks
	NameController nameController;
	
	@Mock
	NameDTO dtoName=mock(NameDTO.class);
	
	@Mock
	HttpServletResponse response=mock(HttpServletResponse.class);
	
	List<Name> list_names;
	Name name;
	List<RightOwnersName> list_rightOwnersName;
	
	@Before
	public void setup() throws Exception{
		MockitoAnnotations.initMocks(this);
	}
	
	@Before
	public void prepareName() {
		name=PrepareName.getNames();		
	}
	
	@Before
	public void prepareListNames() {
		list_names=PrepareName.getNamesList();
	}
	@Before
	public void prepareRightOwnerName() {
		list_rightOwnersName=PrepareName.getRightOwnerName();
	}
	
	@Test
	public void testAvailabilityOfTheService() throws Exception {
		doNothing().when(response).setHeader("Custom-Header", "Rightowner Sevice");
		response.setHeader("Custom-Header", "Rightowner Sevice");
		verify(response, times(1)).setHeader("Custom-Header", "Rightowner Sevice");
		
		doNothing().when(response).setHeader("Status", "Ok");
		response.setHeader("Status", "Ok");
		verify(response, times(1)).setHeader("Status", "Ok");
		
		when(response.getWriter()).thenReturn(new PrintWriter("test"));
		
		nameController.testAvailabilityOfTheService(response);
	}
	@Test
	public void testGetAllNames() throws Exception {
		Pageable pagable = PageRequest.of(0, 25, Sort.by("mainid"));
		Page<Name> page_name=new PageImpl<>(list_names, pagable, 71266);
		
		when(nameService.listNames(0, 25, "mainid")).thenReturn(page_name);
		assertThat(nameController.getAllNames(0, 25, "mainid"), is(notNullValue()));
	}
	@Test(expected=RuntimeException.class)
	public void testGetAllNamesThrowException() throws Exception {
		when(nameService.listNames(0, 25, "mainid")).thenThrow(new RuntimeException("error in getAllNames method"));		
		nameController.getAllNames(0, 25, "mainid");
	}
	@Test
	public void testGetNameByMainId() throws Exception {
		
		doNothing().when(dtoName).setMainId("N-1031307");
		dtoName.setMainId("N-1031307");
		verify(dtoName, times(1)).setMainId("N-1031307");
		
		when(nameService.searchByMainId(dtoName)).thenReturn(name);
		assertThat(nameController.getNameByMainId("N-1031307"), is(notNullValue()));
	}
	@Test(expected=RuntimeException.class)
	public void testGetNameByMainIdThrowException() throws Exception {
		doNothing().when(dtoName).setMainId("N-1031307");
		dtoName.setMainId("N-1031307");
		verify(dtoName, times(1)).setMainId("N-1031307");
		
		when(nameService.searchByMainId(dtoName)).thenThrow(new RuntimeException("error in calling getNameByMainId : {}"));
		nameController.getNameByMainId("N-1031307");
	}
	@Test
	public void testUpdateName() throws Exception {
		name.setName("nameupdated");
		name.setUserUpdate("user updated");
		name.setDateUpdate(new Date());
		
		when(nameService.update(name)).thenReturn(name);
		assertThat(nameController.updateName(name, "N-1031307"), is(notNullValue()));
		
	}
	@Test(expected=RuntimeException.class)
	public void testUpdateNameThrowException() throws Exception{
		name.setName("nameupdated");
		name.setUserUpdate("user updated");
		name.setDateUpdate(new Date());
		
		when(nameService.update(name)).thenThrow(new RuntimeException("error in calling updateName : {}"));
		nameController.updateName(name, "N-1031307");
	}
	@Test
	public void testInsertName() {
		when(nameService.insert(name)).thenReturn("Saved Successfully");
		assertThat(nameController.insertName(name), is(notNullValue()));
	}
	@Test(expected=RuntimeException.class)
	public void testInsertNameThrowException() {
		when(nameService.insert(name)).thenThrow(new RuntimeException("error in calling insertName : {}"));
		nameController.insertName(name);
	}
	@Test
	public void testDeleteName() throws Exception {
		doNothing().when(dtoName).setMainId("N-1031307");
		dtoName.setMainId("N-1031307");
		verify(dtoName, times(1)).setMainId("N-1031307");
		
		when(nameService.deleteByMainId(dtoName)).thenReturn(true);
		assertThat(nameController.deleteName("N-1031307"), is(notNullValue()));
	}
	@Test(expected=RuntimeException.class)
	public void testDeleteNameThrowException() throws Exception{		
		when(nameService.deleteByMainId(dtoName)).thenThrow(new RuntimeException("error in calling deleteName : {}"));
		nameController.deleteName("N-1031307");
	}
	@Test
	public void testGetAllRightOwnerNames() throws Exception {
		Pageable pagable = PageRequest.of(0, 2);
		Page<RightOwnersName> page_rightownername=new PageImpl<>(list_rightOwnersName,pagable,71266);
		
		when(nameService.listRightOwnerNames()).thenReturn(page_rightownername);
		assertThat(nameController.getAllRightOwnerNames(), is(notNullValue()));
	}
	@Test(expected=RuntimeException.class)
	public void testGetAllRightOwnerNamesThrowException() throws Exception {
		when(nameService.listRightOwnerNames()).thenThrow(new RuntimeException("error in calling getAllRightOwnerNames : {}"));
		nameController.getAllRightOwnerNames();
	}
	
}
