package com.fengxing.ems.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fengxing.ems.mapper.CourseKindMapper;
import com.fengxing.ems.service.serviceInterface.CourseKindService;
/*
 * createTime: 2017-12-6 14:07
 */
@Service
public class CourseKindServiceImpl implements CourseKindService{
	
	@Autowired
	private CourseKindMapper courseKindMapper;
	
	@Override
	public List<String> getAllCourseKind() throws Exception{
		return courseKindMapper.getCourseKind();
	}
	
}
