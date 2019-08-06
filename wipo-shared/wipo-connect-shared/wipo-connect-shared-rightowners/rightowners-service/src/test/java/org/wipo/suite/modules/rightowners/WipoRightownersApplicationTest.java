package org.wipo.suite.modules.rightowners;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 
 * @author Devdyuti
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class WipoRightownersApplicationTest {

	@InjectMocks
	WipoRightownersApplication wipoRightownersApplication;
	
	@Test
	public void testConfigure() {
		SpringApplicationBuilder builder=new SpringApplicationBuilder(WipoRightownersApplication.class);
		assertThat(wipoRightownersApplication.configure(builder), is(notNullValue()));
	}
	
}
