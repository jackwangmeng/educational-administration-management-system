package com.fengxing.ems.mapper;

import java.util.List;
import java.util.Map;

import com.fengxing.ems.entity.Course;
/*
 * createTime 2017-12-4
 */
public interface CourseMapper {
	//新开一门课程，并返回自增主键
	public void insert(Course course); 
	
	//查询
	public List<Course> selectAllInfo(Map<String, Object> params);
	
	//查询时不加activity的信息，给学生和老师用
	public List<Course> selectWithoutActivity(Map<String, Object> params)throws Exception;
	
	//不查出任何外键,选课判断时间是否冲突时调用
	public List<Course> selectWithoutAnyForeignKeyInfo(Map<String, Object> params)throws Exception;
	
	//只查询activity的信息,退选判断activity是否过期的时候用
	public List<Course> selectOnlyActivityInfo(Map<String, Object> params)throws Exception;
}
