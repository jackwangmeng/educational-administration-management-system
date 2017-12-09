package com.fengxing.ems.service.serviceInterface;

import java.util.Map;

import com.fengxing.ems.entity.Student;

public interface StudentService {
	public Student getStudent(String studentId)throws Exception;
	
	public void updatePersonalInfo(Map<String, Object> params)throws Exception;
	
	public void changePassword(String studentId, String oldPassword, String newPassword) throws Exception;
}
