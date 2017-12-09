package com.fengxing.ems.entity;

/*
 * createTime:2017-12-3 18:47
 */
public class DevelopPlan {
	private DevelopPlanVersion version;
	private CourseTemple courseTemple;
	private Integer term;
	
	
	public DevelopPlanVersion getVersion() {
		return version;
	}


	public void setVersion(DevelopPlanVersion version) {
		this.version = version;
	}


	public CourseTemple getCourseTemple() {
		return courseTemple;
	}


	public void setCourseTemple(CourseTemple courseTemple) {
		this.courseTemple = courseTemple;
	}


	public Integer getTerm() {
		return term;
	}


	public void setTerm(Integer term) {
		this.term = term;
	}


	@Override
	public String toString() {
		return "DevelopPlan [version=" + version + ", courseTemple=" + courseTemple + ", term=" + term + "]";
	}
	
}
