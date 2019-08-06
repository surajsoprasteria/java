package org.wipo.suite.modules.rightowners.service.configuration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * 
 * @author Devdyuti
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SpringSwaggerConfigTest {

	@InjectMocks
	SpringSwaggerConfig swaggerConfig;
	
	
	@Before
	public void setup() throws Exception{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testApi() {
		assertThat(swaggerConfig.api(), is(notNullValue()));
	}
	
}
