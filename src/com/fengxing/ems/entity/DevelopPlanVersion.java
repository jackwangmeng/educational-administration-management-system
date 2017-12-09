package com.fengxing.ems.entity;


/*
 * createTime:2017-12-3 18:41
 * 实体类：培养计划的版本
 */
public class DevelopPlanVersion {
	private Major major;
	private Integer grade;
	
	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "DevelopPlanVersion [major=" + major + ", grade=" + grade + "]";
	}
}
