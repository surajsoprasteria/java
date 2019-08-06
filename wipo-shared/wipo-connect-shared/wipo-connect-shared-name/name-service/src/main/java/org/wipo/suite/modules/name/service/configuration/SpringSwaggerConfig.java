package org.wipo.suite.modules.name.service.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringSwaggerConfig{
	
    @Bean
    public Docket api() {
	Docket docket = new Docket(DocumentationType.SWAGGER_2)
		.select()
		.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
		.paths(PathSelectors.any())
		.build();

	return docket
		.tags(new Tag("Name", "Wipo Name service"))
		.apiInfo(apiInfo())
		.securitySchemes(securitySchemeList())
		.securityContexts(securityContextList());
    }
    
    private ApiInfo apiInfo() {
	return new ApiInfoBuilder()
		.title("WIPO Connect Name Microservice")
		.description("The name services is used to perform a CURD operation")
		.contact(new Contact("WIPO", "http://www.wipo.int", "test@wipo.int"))
		.license("Apache 2.0")
		.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
		.version("1.0.0")
		.build();
    }
    private List<SecurityScheme> securitySchemeList() {
	return Arrays.asList(new BasicAuth("basicAuth"));
    }
    
    private List<SecurityContext> securityContextList() {

	SecurityContext ctx = SecurityContext
		.builder()
		.securityReferences(defaultAuth())
		.forPaths(PathSelectors.regex("/api/.*"))
		.build();
	return Arrays.asList(ctx);

    }
    private List<SecurityReference> defaultAuth() {
	AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
	AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	authorizationScopes[0] = authorizationScope;
	return Arrays.asList(new SecurityReference("basicHttp", authorizationScopes));
    }
	
	
}
 