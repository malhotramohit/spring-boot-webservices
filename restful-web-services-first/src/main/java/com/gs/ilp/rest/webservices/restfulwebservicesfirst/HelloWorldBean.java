package com.gs.ilp.rest.webservices.restfulwebservicesfirst;

import java.util.Date;

public class HelloWorldBean {
	
	private String message;
	private Date timeOfResp;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTimeOfResp() {
		return timeOfResp;
	}
	public void setTimeOfResp(Date timeOfResp) {
		this.timeOfResp = timeOfResp;
	}
	public HelloWorldBean(String message, Date timeOfResp) {
		super();
		this.message = message;
		this.timeOfResp = timeOfResp;
	}
	public HelloWorldBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
