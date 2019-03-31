package com.gs.ilp.rest.webservices.restfulwebservicesfirst;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController

public class MyFirstController {

	@Autowired
	private UserDaoService userDaoService;

	@Autowired
	private MessageSource messageSource;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/hello")
	public String displayHello(Locale locale) {
		logger.info("lang = " + locale.getLanguage());
		return messageSource.getMessage("hello.world", null, locale);
	}

	@GetMapping("/users")
	public ArrayList<User> getAllUserData() {
		return userDaoService.findAll();
	}

	@PostMapping("/user")
	public ResponseEntity<Object> save(@Valid @RequestBody User user) {
		User savedUser = userDaoService.save(user);

		URI uri = null;

		// 198./...:8080/user/{id}
		uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@GetMapping(path = "/user/{id}")
	public Resource<User> getSpecUser(@PathVariable int id) {

		User user = userDaoService.getSpecUser(id);
		if (user == null) {
			throw new UserNotFoundException("user not found");
		}

		Resource<User> resource = new Resource<User>(user);

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUserData());

		resource.add(linkTo.withRel("display-user"));

		return resource;
	}

	@GetMapping(path = "/userwohat/{id}")
	public Resource<User> getSpecUserWithoutHat(@PathVariable int id) {

		User user = userDaoService.getSpecUser(id);
		if (user == null) {
			throw new UserNotFoundException("user not found");
		}

		Resource<User> resource = new Resource<User>(user);

		resource.add(linkTo(methodOn(this.getClass()).getAllUserData()).withRel("display-all-user"));
		
		resource.add(linkTo(methodOn(this.getClass()).getSpecUser(id)).withSelfRel());

		return resource;
	}

	@GetMapping("/filter1")
	public MappingJacksonValue getFilterData1() {

		Fields ff = new Fields("11", "22", "33");

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");

		FilterProvider filters = new SimpleFilterProvider().addFilter("field-filter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(ff);

		mapping.setFilters(filters);

		return mapping;
	}

	@GetMapping("/filter2")
	public MappingJacksonValue getFilterData2() {

		Fields ff = new Fields("11", "22", "33");

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");

		FilterProvider filters = new SimpleFilterProvider().addFilter("field-filter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(ff);

		mapping.setFilters(filters);

		return mapping;
	}

	@GetMapping("/filter3")
	public MappingJacksonValue getFilterData3() {

		Fields ff = new Fields("11", "22", "33");

		ControllerLinkBuilder linkTO = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getFilterData2());

		Resource<Fields> resource = new Resource<Fields>(ff);

		resource.add(linkTO.withRel("filter2"));

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");

		FilterProvider filters = new SimpleFilterProvider().addFilter("field-filter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(resource);

		mapping.setFilters(filters);

		return mapping;
	}

}
