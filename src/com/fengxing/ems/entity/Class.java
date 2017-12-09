package com.fengxing.ems.entity;
/*
 * createTime:2017-12-3 18:49
 */

import java.util.Date;


public class Class {
	private Integer grade;
	private String createTime;
	private Integer number;
	private Major major;
	private DevelopPlanVersion version;
	private String extra;
	
	public Class() {
		
	}
	
	public Class(Integer grade, Major major, Integer number) {
		this.grade = grade;
		this.major = major;
		this.number = number;
	}
	
	public Integer getGrade() {
		return grade;
	}



	public void setGrade(Integer grade) {
		this.grade = grade;
	}


	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getNumber() {
		return number;
	}



	public void setNumber(Integer number) {
		this.number = number;
	}



	public Major getMajor() {
		return major;
	}



	public void setMajor(Major major) {
		this.major = major;
	}



	public DevelopPlanVersion getVersion() {
		return version;
	}



	public void setVersion(DevelopPlanVersion version) {
		this.version = version;
	}



	public String getExtra() {
		return extra;
	}



	public void setExtra(String extra) {
		this.extra = extra;
	}



	@Override
	public String toString() {
		return "Class [grade=" + grade + ", createTime=" + createTime + ", number=" + number + ", major=" + major
				+ ", version=" + version + ", extra=" + extra + "]";
	}
	
	
}
