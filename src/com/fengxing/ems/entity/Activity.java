package com.fengxing.ems.entity;
/*
 * createTime:2017-12-3 19：08
 * 选课活动
 */

import java.io.Serializable;
import java.util.Date;

public class Activity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String startTime;
	private String endTime;
	private Boolean active;
	private Integer targetGrade;
	private String targetCollege;
	private String targetMajorName;
	private String extra;
	
	public Activity() {
		
	}
	
	public Activity(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	
	public Integer getTargetGrade() {
		return targetGrade;
	}

	public void setTargetGrade(Integer targetGrade) {
		this.targetGrade = targetGrade;
	}

	public String getTargetCollege() {
		return targetCollege;
	}
	public void setTargetCollege(String targetCollege) {
		this.targetCollege = targetCollege;
	}
	public String getTargetMajorName() {
		return targetMajorName;
	}
	public void setTargetMajorName(String targetMajorName) {
		this.targetMajorName = targetMajorName;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", active=" + active
				+ ", targetGrade=" + targetGrade + ", targetCollege=" + targetCollege + ", targetMajorName="
				+ targetMajorName + ", extra=" + extra + "]";
	}
	
	
}
