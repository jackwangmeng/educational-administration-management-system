package com.fengxing.ems.service.serviceImpl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fengxing.ems.Myutils.ScheduleUtils;
import com.fengxing.ems.entity.Score;
import com.fengxing.ems.mapper.ScoreMapper;
import com.fengxing.ems.service.serviceInterface.ScheduleService;
import com.fengxing.ems.service.serviceInterface.ScoreService;

/*
 * createTime: 2017-12-8 16:31
 */
@Service
public class ScheduleServiceImpl implements ScheduleService{

	@Autowired
	private ScoreMapper scoreMapper;
	
	@Override
	public Map<String, Object> studentSchedule(Map<String, Object> params) 
			throws Exception {
		//调mapper获取数据
		List<Score> scores = scoreMapper.studentGetSchedule(params);
		
		//将list<score>转化为前端表格能识别的数据结构
		List<Map<String, Object>> list = ScheduleUtils.convertToStudentSchedule(scores);
		
		//封装结果
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("res", list);
		return result;
	}

}
