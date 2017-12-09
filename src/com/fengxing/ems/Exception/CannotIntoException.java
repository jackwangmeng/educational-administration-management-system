package com.fengxing.ems.Exception;

//不能进入选课异常
public class CannotIntoException extends Exception {
	public CannotIntoException(String message) {
		super(message);
	}
}
