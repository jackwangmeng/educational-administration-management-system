package com.fengxing.ems.entity;
/*
 * createTime 2017-12-3 19:11
 */

import java.util.Date;


public class Course {
	public static final Integer BEFOR_EXAM = 0;
	public static final Integer AFTER_EXAM = 1;
	public static final Integer END = 2;
	
	private Integer id;
	private CourseTemple courseTemple;
	private Teacher teacher;
	private String term;
	private String time;
	private String room;
	private Integer maxCount;
	private Integer usedCount = 0;//数据库默认值为0
	private Integer status = 0;//数据库的默认值为0
	private Activity activity;
	private String extra;
	
	public Course() {
		
	}
	
	public Course(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CourseTemple getCourseTemple() {
		return courseTemple;
	}

	public void setCourseTemple(CourseTemple courseTemple) {
		this.courseTemple = courseTemple;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}


	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public Integer getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}

	public Integer getUsedCount() {
		return usedCount;
	}

	public void setUsedCount(Integer usedCount) {
		this.usedCount = usedCount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", courseTemple=" + courseTemple + ", teacher=" + teacher + ", term=" + term
				+ ", time=" + time + ", room=" + room + ", maxCount=" + maxCount + ", usedCount=" + usedCount
				+ ", status=" + status + ", activity=" + activity + ", extra=" + extra + "]";
	}


}
