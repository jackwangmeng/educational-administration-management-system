package com.fengxing.ems.mapper;

import java.util.List;
import java.util.Map;

import com.fengxing.ems.entity.Student;

public interface StudentMapper {
	//查出来学生的公开信息
	public List<Student> selectPublicInfo(Map<String, Object> params) throws Exception;
	
	//查询所有信息
	public List<Student> selectAllInfo(Map<String, Object> params)throws Exception;
	
	//更新学生的个人信息 adress, phone, email
	public int updatePersonalInfo(Map<String, Object> params)throws Exception;
	
	//修改密码
	public int updatePassword(Map<String, Object> params)throws Exception;
}
