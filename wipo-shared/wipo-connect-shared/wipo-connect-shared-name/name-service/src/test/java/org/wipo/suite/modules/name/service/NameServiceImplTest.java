package org.wipo.suite.modules.name.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import org.wipo.suite.modules.name.dal.repositories.NameRepository;
import org.wipo.suite.modules.name.dal.repositories.RightOwnersNameRepository;
import org.wipo.suite.modules.name.dto.NameDTO;
/**
 * 
 * @author Devdyuti
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class NameServiceImplTest{
	
	@Mock
	NameRepository nameRepository=mock(NameRepository.class);
	@Mock
	RightOwnersNameRepository rightOwnersNameRepository=mock(RightOwnersNameRepository.class);
	
	@InjectMocks
	NameServiceImpl nameServiceImpl;
	
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
	public void testGetNameById() {
		Optional<Name> optional_name=Optional.of(name);
		when(nameRepository.findById(1L)).thenReturn(optional_name);
		assertEquals("test", optional_name.get().getName());			
		assertThat(nameServiceImpl.getNameById(1L), is(notNullValue()));
	}
	@Test
	public void testUpdate() {
		name.setName("nameupdated");
		name.setUserUpdate("user updated");
		name.setDateUpdate(new Date());
				
		when(nameRepository.find(name.getMainId())).thenReturn(name);
		assertThat(nameServiceImpl.update(name), is(nullValue()));
				
		when(nameRepository.save(name)).thenReturn(name);
		assertThat(nameServiceImpl.update(name), is(notNullValue()));
	
		assertNotEquals("mozart", name.getName());
		assertEquals(String.valueOf(new Date()), String.valueOf(name.getDateUpdate()));
	}
	@Test
	public void testSearchByMainId() {		
		NameDTO searchCriteria=new NameDTO();
		searchCriteria.setMainId("N-1031307");
		
		when(nameRepository.find("N-1031307")).thenReturn(name);
		assertThat(nameServiceImpl.searchByMainId(searchCriteria), is(notNullValue()));
		assertEquals("test", name.getName());		
	}
	@Test
	public void testInsert() {			
		when(nameRepository.save(name)).thenReturn(name);
		assertThat(nameServiceImpl.insert(name), is(notNullValue()));
	}
		// testing if block of insert method
		@Test
		public void testGetMainIdNotNull() {
			when(nameRepository.find(name.getMainId())).thenReturn(name);
			assertThat(nameServiceImpl.insert(name), is(notNullValue()));
		}
	@Test
	public void testListNames() {
		Pageable pagable = PageRequest.of(0, 25, Sort.by("idName"));
		Page<Name> page_name=new PageImpl<>(list_names, pagable, 71266);
		
		when(nameRepository.findAll(pagable)).thenReturn(page_name);
		assertThat(nameServiceImpl.listNames(0, 25, "idName"), is(notNullValue()));
		
	}
	@Test
	public void testDeleteByMainId() {
		NameDTO nameDTO=new NameDTO();
		nameDTO.setMainId("N-1031307");
		when(nameRepository.find("N-1031307")).thenReturn(name);
		assertNotEquals(1, name.getDeleted());
		assertThat(nameServiceImpl.deleteByMainId(nameDTO), is(notNullValue()));
	}
		// testing if block of deleteByMainid method
		@Test
		public void testGetDeleted() {
			name.setDeleted(1);
			
			NameDTO nameDTO=new NameDTO();
			nameDTO.setMainId("N-1031307");
			when(nameRepository.find("N-1031307")).thenReturn(name);
			assertThat(nameServiceImpl.deleteByMainId(nameDTO), is(notNullValue()));
		}
	@Test
	public void testListRightOwnerNames() {
		Pageable pagable = PageRequest.of(0, 2);
		
		Page<RightOwnersName> page_rightownername=new PageImpl<>(list_rightOwnersName,pagable,71266);
		
		when(rightOwnersNameRepository.findAll(pagable)).thenReturn(page_rightownername);
		assertThat(nameServiceImpl.listRightOwnerNames(), is(notNullValue()));
	}
	@Test
	public void testHardDeleteByMainId() {
		NameDTO nameDTO=new NameDTO();
		nameDTO.setMainId("I-00021693006");
		assertEquals(false, nameServiceImpl.hardDeleteByMainId(nameDTO));
	}
	
}
