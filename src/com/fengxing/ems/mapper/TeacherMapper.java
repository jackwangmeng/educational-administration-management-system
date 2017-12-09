package com.fengxing.ems.mapper;
/*
 * createTime:2017-12-4 16:52
 */

import java.util.List;
import java.util.Map;

import com.fengxing.ems.entity.Teacher;

public interface TeacherMapper {
	//不查出老师的个人信息
	public List<Teacher> selectWithoutPersonalInfo(Map<String, Object> params);
	
	//查出老师的个人信息
	public List<Teacher> selectWithAllInfo(Map<String, Object> params);
	
	//添加一个记录
	public void insert(Teacher teacher);
}
