package com.gs.ilp.rest.webservices.restfulwebservicesfirst;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final Contact CONTACT = new Contact("mohit", "www.gs.com", "email@jjj.com");
	private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("my title", "my description", "version 1",
			"www.test.com", CONTACT, "lic", "licUrl");
	
	private static final Set<String> produces = new HashSet<>();
	
	static {
		produces.add("application/json");
		produces.add("application/xml");
	}

	@Bean
	public Docket api() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO).produces(produces).consumes(produces);
		return docket;
	}
	
	@Bean
	public Docket swaggerPersonApiV1() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("person-api-v1")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.gs.ilp.rest.webservices.restfulwebservicesfirst"))
				.paths(PathSelectors.regex("/person/v1*")).build()
				.apiInfo(DEFAULT_API_INFO);
	}
	
	@Bean
	public Docket swaggerPersonApiV2() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("person-api-v2")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.gs.ilp.rest.webservices.restfulwebservicesfirst"))
				.paths(PathSelectors.regex("/person/v2*")).build()
				.apiInfo(DEFAULT_API_INFO);
	}
	
	@Bean
	public Docket swaggerPersonApiV3() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("person-api-v3")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.gs.ilp.rest.webservices.restfulwebservicesfirst"))
				.paths(PathSelectors.regex("/person/v3*")).build()
				.apiInfo(DEFAULT_API_INFO);
	}


}
