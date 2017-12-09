package com.fengxing.ems.entity;


/*
 * createTime:2017-12-3 18:43
 * 课程模板
 */
public class CourseTemple {
	private String id;
	private String name;
	private Integer point;
	private Integer hour;
	private String openCollege;//开课的学院
	private String extra;
	private String courseKind;
	private Integer importance;
	
	public CourseTemple() {
		
	}
	
	public CourseTemple(String id) {
		this.id = id;
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public String getOpenCollege() {
		return openCollege;
	}

	public void setOpenCollege(String openCollege) {
		this.openCollege = openCollege;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getCourseKind() {
		return courseKind;
	}

	public void setCourseKind(String courseKind) {
		this.courseKind = courseKind;
	}
	
	

	public Integer getImportance() {
		return importance;
	}

	public void setImportance(Integer importance) {
		this.importance = importance;
	}

	@Override
	public String toString() {
		return "CourseTemple [id=" + id + ", name=" + name + ", point=" + point + ", hour=" + hour + ", openCollege="
				+ openCollege + ", extra=" + extra + ", courseKind=" + courseKind + ", importance=" + importance + "]";
	}

	

}
