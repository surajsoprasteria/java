package org.wipo.suite.modules.rightowners.service.configuration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.converter.HttpMessageConverter;
import org.wipo.connect.common.conversion.JacksonObjectMapper;

import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 
 * @author Devdyuti
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SpringRightownersConfigTest {

	@Mock
	JacksonObjectMapper jacksonObjectMapper=mock(JacksonObjectMapper.class);
	
	@InjectMocks
	SpringRightownersConfig springRightownersConfig;  
	
	
	@Before
	public void setup() throws Exception{
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void testInit() {
		when(jacksonObjectMapper.enable(SerializationFeature.INDENT_OUTPUT)).thenReturn(jacksonObjectMapper);
		springRightownersConfig.init();
	}
	
	@Test
	public void testConfigureMessageConverters() {
		List<HttpMessageConverter<?>> converters=new ArrayList<HttpMessageConverter<?>>();
		springRightownersConfig.configureMessageConverters(converters);
	}
	@Test
	public void testLoadExternalSpringBootProperties() {
		springRightownersConfig.loadExternalSpringBootProperties();
	}
	@Test
	public void testSetehCachePersistanceDir() {
		springRightownersConfig.setehCachePersistanceDir();
	}
	
}
