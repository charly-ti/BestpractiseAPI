package com.rcggs.sample.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class Userdto {
	
//private long id;
	

	@NotNull
	@NotBlank(message = "Name can not be null")
	@Pattern(regexp="^[a-zA-Z~]*$", message = "Invalid Name")
	@Size(max=60)
	private String name;
	private String organization;
	@NotNull
	@NotBlank(message = "Mail Address can not be null")
	@Email
	@Pattern(regexp=".+@.+\\..+", message="Email must be a well-formed email address")
	@Size(max=60)
	private String email;
	private Double salary;
	@NotNull
	@NotBlank
	@Size(min=1,max=30,message = "size exceeds") 
	@Pattern(regexp="^\\\\?[0-9]{1,30}$", message = "Invalid Phone number")
	private String phoneNo;
//	public long getId() {
//		return id;
//	}
//	public void setId(long id) {
//		this.id = id;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	
}
