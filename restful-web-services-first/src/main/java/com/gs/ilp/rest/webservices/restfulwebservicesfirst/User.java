package com.gs.ilp.rest.webservices.restfulwebservicesfirst;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="User class")
public class User {
	
	private int Id;
	
	@Size(min=3 ,message = "minimum 3 chars")
	@ApiModelProperty(notes="note minimum 3 chars")
	private String name;
	@Past(message="invalid date")
	@ApiModelProperty(notes ="cannot be of future")
	private Date dateOfBirth;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public User(int id, String name, Date dateOfBirth) {
		super();
		Id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


}
