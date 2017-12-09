package com.fengxing.ems.service.serviceInterface;

import java.util.Map;

/*
 * createTime: 2017-12-8 16:30
 * 获取课表
 */
public interface ScheduleService {
	public Map<String, Object> studentSchedule(Map<String, Object> params)throws Exception;
}
