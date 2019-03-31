package com.gs.ilp.rest.webservices.restfulwebservicesfirst;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

	// URI versioning 
	@GetMapping("/person/v1")
	public PersonV1 getPersonV1URL() {
		return new PersonV1("Mohit");
	}
	
	@GetMapping("/person/v2")
	public PersonV2 getPersonV2URL() {
		return new PersonV2("Mohit","malhotra");
	}
	
	// URI versioning 
		@GetMapping("/person/v3")
		@Deprecated
		public PersonV1 getPersonV3URL() {
			return new PersonV1("Mohit");
		}
	
	//param 
	@GetMapping(path="/person", params ="version=1" )
	public PersonV1 getPersonV1Param() {
		return new PersonV1("Mohit");
	}
	
	@GetMapping(path="/person", params ="version=2" )
	public PersonV2 getPersonV2Param() {
		return new PersonV2("Mohit","malhotra");
	}
	
	// header
	//header 
		@GetMapping(path="/person/h", headers ="MY-API-VERION=1" )
		public PersonV1 getPersonV1Header() {
			return new PersonV1("Mohit");
		}
		
		@GetMapping(path="/person/h", headers ="MY-API-VERION=2" )
		public PersonV2 getPersonV2Header() {
			return new PersonV2("Mohit","malhotra");
		}
	//MIME
		@GetMapping(path="/person/m", produces ="application/my.company.app-v1+json" )
		public PersonV1 getPersonV1Mime() {
			return new PersonV1("Mohit");
		}
		
		@GetMapping(path="/person/m",produces ="application/my.company.app-v2+json" )
		public PersonV2 getPersonV2Mime() {
			return new PersonV2("Mohit","malhotra");
		}
	
}
