package com.fengxing.ems.service.serviceInterface;

import java.util.Map;

import com.fengxing.ems.entity.Activity;

/*
 * createTime:2017-12-5 15:46
 */
public interface ActivityService {
	public Map<String, Object> studentGetActivity
		(Map<String, Object> params, int pageSize, int pageNumber)throws Exception;
	
	public Activity checkIfCanIntoActivity(String activityId, String studentId)throws Exception;
}
