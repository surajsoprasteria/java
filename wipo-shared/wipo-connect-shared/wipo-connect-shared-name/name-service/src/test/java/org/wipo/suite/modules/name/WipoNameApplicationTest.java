package org.wipo.suite.modules.name;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.wipo.suite.modules.name.service.WipoNameApplication;

@RunWith(MockitoJUnitRunner.class)
public class WipoNameApplicationTest {

	@InjectMocks
	WipoNameApplication wipoNameApplication;
	
	@Test
	public void testConfigure() {
		SpringApplicationBuilder builder=new SpringApplicationBuilder(WipoNameApplication.class);
		assertThat(wipoNameApplication.configure(builder), is(notNullValue()));
	}
	
}
