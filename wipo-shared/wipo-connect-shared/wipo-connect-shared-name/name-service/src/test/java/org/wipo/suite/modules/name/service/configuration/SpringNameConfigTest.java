package org.wipo.suite.modules.name.service.configuration;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
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
public class SpringNameConfigTest {
	
	@Mock
	JacksonObjectMapper jacksonObjectMapper=mock(JacksonObjectMapper.class);
	
	@InjectMocks
	SpringNameConfig springConfig;
	
	
	@Before
	public void setup() throws Exception{
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void testInit() {
		when(jacksonObjectMapper.enable(SerializationFeature.INDENT_OUTPUT)).thenReturn(jacksonObjectMapper);
		springConfig.init();
	}
	
	@Test
	public void testConfigureMessageConverters() {
		List<HttpMessageConverter<?>> converters=new ArrayList<HttpMessageConverter<?>>();
		springConfig.configureMessageConverters(converters);
	}
	@Test
	public void testLoadExternalSpringBootProperties() {
		SpringNameConfig.loadExternalSpringBootProperties();
	}
	@Test
	public void testSetehCachePersistanceDir() {
		SpringNameConfig.setehCachePersistanceDir();
	}

}
