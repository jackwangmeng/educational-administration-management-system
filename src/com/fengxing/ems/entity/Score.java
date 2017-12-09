package com.fengxing.ems.entity;
/*
 * createTime 2017-12-03 19:17
 */
public class Score {
	private static final Integer NORMAL = 0;
	private static final Integer SECOND_EXAM = 1;
	
	private float score;
	private Student student;
	private Course course;
	private Integer status;
	private Integer teacherChangeCount;
	
	
	public Score() {
		
	}
	
	public Score(Course course, Student student) {
		this.course = course;
		this.student = student;
	}
	
	public float getScore() {
		return score;
	}



	public void setScore(float score) {
		this.score = score;
	}



	public Student getStudent() {
		return student;
	}



	public void setStudent(Student student) {
		this.student = student;
	}



	public Course getCourse() {
		return course;
	}



	public void setCourse(Course course) {
		this.course = course;
	}



	public Integer getStatus() {
		return status;
	}



	public void setStatus(Integer status) {
		this.status = status;
	}



	public Integer getTeacherChangeCount() {
		return teacherChangeCount;
	}



	public void setTeacherChangeCount(Integer teacherChangeCount) {
		this.teacherChangeCount = teacherChangeCount;
	}



	@Override
	public String toString() {
		return "Score [score=" + score + ", student=" + student + ", course=" + course + ", status=" + status + "]";
	}
	
	
}
