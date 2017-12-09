package com.fengxing.ems.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fengxing.ems.Myutils.CourseTimeUtils;
import com.fengxing.ems.entity.Course;
import com.fengxing.ems.mapper.CourseMapper;
import com.fengxing.ems.service.serviceInterface.CourseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/*
 * createTime:2017-12-5 19:56
 */

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseMapper courseMapper;
	
	@Override
	public Map<String, Object> studentGetCourse(Map<String, Object> params, 
			Integer pageSize, Integer pageNumber) throws Exception {
		//开始分页
		Page<Object> pages = PageHelper.startPage(pageNumber, pageSize);
		
		//封装mapper的参数
		//由于数据库中的的上课时间是 1-12week|mon-1;wed-2 格式的字符串，所以查询必须要构造表达式
		Integer day = (Integer)params.remove("day");
		Integer section = (Integer)params.remove("section");
		params.put("courseTimeRegex", CourseTimeUtils.getRegexForQueryCourseTime(day, section));
		
		//调用mapper获取数据
		List<Course> courses = courseMapper.selectWithoutActivity(params);
		for(Course course : courses) {
			System.out.println(course);
		}
		
		//转换上课时间的格式
		for(Course course : courses) {
			course.setTime(CourseTimeUtils.convertDataBaseTimeToUITime(course.getTime()));
		}
		
		//封装结果
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("res", courses);
		result.put("total", pages.getTotal());
		
		return result;
	}	
}
