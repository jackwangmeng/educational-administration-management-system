package com.fengxing.ems.service.serviceInterface;

import java.util.Map;

/*
 * createTime:2017-12-5 19:51
 */
public interface CourseService {
	public Map<String, Object> studentGetCourse(Map<String, Object> params, Integer pageSize,
			Integer pageNumber)throws Exception;
}
