package com.fengxing.ems.entity;

import java.io.Serializable;

/*
 * createTime:2017-12-03 18:23
 */
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public static final String STUDENT = "student";
	public static final String TEACHER = "teacher";
	public static final String ADMIN = "admin";
	
	public static final boolean LOCKED = false;
	public static final boolean NOT_LOCAKED = true;
	
	private String id;
	private String password;
	
	private String name;
	private String email;
	private String phone;
	private String adress;
	private String number;
	private Boolean idStatus = true;
	
	private String role;
	
	public User() {
		
	}
	
	public User(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getName() {  
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public Boolean getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Boolean idStatus) {
		this.idStatus = idStatus;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", passowrd=" + password + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", adress=" + adress + ", number=" + number + ", idStatus=" + idStatus + ", role=" + role + "]";
	}
}
