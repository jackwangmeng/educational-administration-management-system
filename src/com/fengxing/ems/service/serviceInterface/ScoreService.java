package com.fengxing.ems.service.serviceInterface;

import java.util.List;
import java.util.Map;


import com.fengxing.ems.entity.Score;
import com.sun.corba.se.spi.ior.ObjectKey;

/*
 * createTime: 2017-12-6 15:39
 */
public interface ScoreService {
	//学生选课
	public void studentAddCourse
		(String activityId ,String studentId, String courseId)throws Exception;
	
	//学生退选
	public void studentDeleteCourse(String studentId, String courseId)throws Exception;
	
	//学生查询已选课程
	public Map<String, Object> studentGetAddedCourse
		(Map<String, Object> params, Integer pageSize, Integer pageNumber)throws Exception; 
	
	//学生查询成绩
	public Map<String, Object> studentGetScore(Map<String, Object> params,
			Integer pageSize, Integer pageNumber)throws Exception;
}
