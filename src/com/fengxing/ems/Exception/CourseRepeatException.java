package com.fengxing.ems.Exception;

//不能重复选课异常
public class CourseRepeatException extends Exception{
	
	public CourseRepeatException(String message) {
		super(message);
	}
}
